/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent.CompositeType;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class Exhaustive extends AbstractAssignment {

    @Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {
        for(Module m:modules){
            solve(m,library,stl,args);
            
        }
        for(int i=0;i<modules.size();i++){
            String simfp = args.getProjectFolder() + i + Utilities.getSeparater();
            Utilities.makeDirectory(simfp);
            Module m = modules.get(i);
            
            try {
                Simulation.run(modules.get(i),library,stl,args,simfp);
            } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | TLIException | InterruptedException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void solve(Module module, Library library, TreeNode stl, Args args){
        for(Module tu:module.getChildren()){
            assignTUchildCandidates(tu,library);
            assignTUcandidates(tu,args);
        }
        assignCircuitCandidates(module,library, stl);
    }
    
    private void assignCircuitCandidates(Module module, Library library, TreeNode stl){;
        Set<String> abstractComponents = new HashSet<String>();
        Set<String> specificComponents = new HashSet<String>();
        for(Component c:module.getComponents()){
            switch(c.getRole()){
                case GENERIC_PROMOTER:
                case GENERIC_RBS:
                case GENERIC_CDS:
                case GENERIC_TERMINATOR:
                    abstractComponents.add(c.getName());
                    break;
                default:
                    specificComponents.add(c.getName());
                    break;
            }
        }
        
        List<Map<String,CandidateComponent>> circuitAssignments = new ArrayList<>();
        List<Map<String,CandidateComponent>> tempAssignments = new ArrayList<>();
        
        Map<String,Component> cmap = new HashMap<>();
        for(Component c:module.getComponents()){
            cmap.put(c.getName(), c);
        }
        
        
        for(Module tu:module.getChildren()){
            if(circuitAssignments.isEmpty()){
                circuitAssignments.addAll(tu.getAssignments());
            } else {
                for(Map<String,CandidateComponent> hass:circuitAssignments){
                    
                    for(Map<String,CandidateComponent> tuass: tu.getAssignments()){
                        boolean canAdd = true;
                        for(String cname:tuass.keySet()){
                            Component component = cmap.get(cname);
                            
                            //<editor-fold desc="Check to see if component is specific and was previously assigned. If so, ensure that it matches.">
                            if(specificComponents.contains(cname) && hass.containsKey(cname)){
                                LibraryComponent cc = hass.get(cname).getCandidate();
                                if(cc instanceof CompositeComponent){
                                    CompositeComponent hcomp = (CompositeComponent) hass.get(cname).getCandidate();
                                    CompositeComponent tcomp = (CompositeComponent) tuass.get(cname).getCandidate();
                                    
                                    if(hcomp.getType().equals(CompositeType.PR)){
                                        if(component.isPromoter()){
                                            if(!hcomp.getChildren().get(0).equals(tcomp.getChildren().get(0))){
                                                canAdd = false;
                                                break;
                                            }
                                        } else if(component.isRBS()){
                                            if(!hcomp.getChildren().get(1).equals(tcomp.getChildren().get(1))){
                                                canAdd = false;
                                                break;
                                            }
                                        }
                                    } else if(hcomp.getType().equals(CompositeType.RC)){
                                        if(component.isRBS()){
                                            if(!hcomp.getChildren().get(0).equals(tcomp.getChildren().get(0))){
                                                canAdd = false;
                                                break;
                                            }
                                        } else if(component.isCDS()){
                                            if(!hcomp.getChildren().get(1).equals(tcomp.getChildren().get(1))){
                                                canAdd = false;
                                                break;
                                            }
                                        }
                                    }
                                    
                                    
                                } else {
                                    if(!hass.get(cname).equals(tuass.get(cname))){
                                        canAdd = false;
                                        break;
                                    }
                                }
                            }
                            //</editor-fold>
                            
                            //If The component is a Promoter or CDS that has an interaction..
                            if(!component.getInteractions().isEmpty()){
                                for(Interaction i:component.getInteractions()){
                                    if(component.isPromoter()){
                                        Component cds = i.getFrom();
                                        if(hass.containsKey(cds.getName())){
                                            //Check to see if cds and prom share a protein..
                                            CDSComponent cdscomp = (CDSComponent)hass.get(cds.getName()).getCandidate();
                                            URI protein = cdscomp.getProtein();
                                            PromoterComponent promcomp = null;
                                            LibraryComponent lc = tuass.get(cname).getCandidate();
                                            if(lc instanceof PromoterComponent){
                                                promcomp = (PromoterComponent)lc;
                                            } else if(lc instanceof CompositeComponent){
                                                CompositeComponent compcomp = (CompositeComponent)lc;
                                                promcomp = library.getAllInduciblePromoters().get(compcomp.getChildren().get(0));
                                            }
                                            for(LibraryComponent tf : promcomp.getTranscriptionFactors()){
                                                if(tf instanceof PrimitiveComponent){
                                                    if(!tf.getComponentDefintion().equals(protein)){
                                                        canAdd = false;
                                                        break;
                                                    }
                                                } else if(tf instanceof ComplexComponent){
                                                    ComplexComponent complexComp = (ComplexComponent) tf;
                                                    if(complexComp.getProtein().equals(protein)){
                                                        canAdd = false;
                                                        break;
                                                    }
                                                }
                                            }
                                            if(!canAdd){
                                                break;
                                            }
                                        }
                                    } else if(component.isCDS()){
                                        Component prom = i.getTo();
                                        if(hass.containsKey(prom.getName())){
                                            CDSComponent cdscomp = (CDSComponent)tuass.get(component.getName()).getCandidate();
                                            URI protein = cdscomp.getProtein();
                                            PromoterComponent promcomp = null;
                                            LibraryComponent lc = hass.get(prom.getName()).getCandidate();
                                            if(lc instanceof PromoterComponent){
                                                promcomp = (PromoterComponent)lc;
                                            } else if(lc instanceof CompositeComponent){
                                                CompositeComponent compcomp = (CompositeComponent)lc;
                                                promcomp = library.getAllInduciblePromoters().get(compcomp.getChildren().get(0));
                                            }
                                            for(LibraryComponent tf : promcomp.getTranscriptionFactors()){
                                                if(tf instanceof PrimitiveComponent){
                                                    if(!tf.getComponentDefintion().equals(protein)){
                                                        canAdd = false;
                                                        break;
                                                    }
                                                } else if(tf instanceof ComplexComponent){
                                                    ComplexComponent complexComp = (ComplexComponent) tf;
                                                    if(complexComp.getProtein().equals(protein)){
                                                        canAdd = false;
                                                        break;
                                                    }
                                                }
                                            }
                                            if(!canAdd){
                                                break;
                                            }
                                        }
                                    }
                                }
                            } 
                        }
                        
                        if(canAdd){
                            Map<String,CandidateComponent> assignment = new HashMap<>();
                            assignment.putAll(hass);
                            assignment.putAll(tuass);
                            tempAssignments.add(assignment);
                        }
                    }
                }
                circuitAssignments = new ArrayList<>();
                circuitAssignments.addAll(tempAssignments);
                tempAssignments = new ArrayList<>();
            }
        }
        System.out.println("Assignments before Validation of design : " + circuitAssignments.size());
        module.setAssignments(validateAssignments(module, circuitAssignments, cmap, library, stl));
        System.out.println("Assignments after Validation of design : " + module.getAssignments().size());
    }
    
    private List<Map<String,CandidateComponent>> validateAssignments(Module module, List<Map<String,CandidateComponent>> assignments, Map<String,Component> cmap,  Library library, TreeNode stl){
        List<Map<String,CandidateComponent>> finalAssignments = new ArrayList<>();
        Set<Set<String>> strSet = new HashSet<>();
        for(Map<String,CandidateComponent> assignment:assignments){
            if(validAssignment(assignment,cmap,library,stl)){
                Set<String> str = generateAssignmentStringSet(module, assignment);
                if(!strSet.contains(str)){
                    strSet.add(str);
                    finalAssignments.add(assignment);
                }
            }
        }
        return finalAssignments;
    }
    
    
    
    private void assignTUcandidates(Module module, Args args){
        switch(args.getDecomposition()){
            case PR_C_T:
                assignPR_C_T(module);
                break;
            case P_RC_T:
                break;
            case P_R_C_T:
                break;
        }
    }
    
    private void assignPR_C_T(Module module){
        Module pr = module.getChildren().get(0);
        Module c = module.getChildren().get(1);
        Module t = module.getChildren().get(2);
        
        Component pcomp = module.getComponents().get(0);
        Component rcomp = module.getComponents().get(1);
        Component ccomp = module.getComponents().get(2);
        Component tcomp = module.getComponents().get(3);
        
        for(CandidateComponent prcc: pr.getCandidates()){
            for(CandidateComponent ccc:c.getCandidates()){
                for(CandidateComponent tcc:t.getCandidates()){
                    Map<String,CandidateComponent> tuMap = new HashMap<>();
                    tuMap.put(pcomp.getName(), prcc);
                    tuMap.put(rcomp.getName(), prcc);
                    tuMap.put(ccomp.getName(), ccc);
                    tuMap.put(tcomp.getName(), tcc);
                    module.getAssignments().add(tuMap);
                    //module.getAssignments().add(tuAssignment);
                }
            }
        }
        
    }
    
    
    
}
