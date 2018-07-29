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
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
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
public class SimulatedAnnealing extends AbstractAssignment {

    private double coolingRate = 0.003;
    private double temperature = 1000;
    
    @Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {
        for(Module m:modules){
            solve(m,library,stl,args);
        }
        for(int i=0;i<modules.size();i++){
            String simfp = args.getProjectFolder() + i + Utilities.getSeparater();
            Utilities.makeDirectory(simfp);
            Module m = modules.get(i);
            
            /*try {
                Simulation.run(modules.get(i),library,stl,args,simfp);
            } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | TLIException | InterruptedException ex) {
                Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
    
    private void solve(Module module, Library library, TreeNode stl, Args args){
        for(Module tu:module.getChildren()){
            assignTUchildCandidates(tu,library);
        }
        int count = 0;
        int outCount = getOutputCount(stl);
        
        List<Module> children = new ArrayList<>();
        for(Module tu:module.getChildren()){
            children.addAll(tu.getChildren());
        }
        
        Map<String,CandidateComponent> first = generateFirstAssignment(module,library,outCount);
        
        System.out.println("First Assignment : "  + assignmentToString(module, first));
        
        while(temperature > 1){
            //System.out.println("Temperature :: " + temperature);
            temperature *= (1-coolingRate);
            count++;
        }
        
        System.out.println("Total :: " + count);
    }
    
    private String assignmentToString(Module module, Map<String,CandidateComponent> assignment){
        String str = "";
        for(Component c:module.getComponents()){
            if(assignment.containsKey(c.getName())){
                str += (c.getName() + ":" + assignment.get(c.getName()).getCandidate().getName() + ";");
            }
        }
        
        return str;
    }
    
    private int getRandom(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
    
    private Map<String,CandidateComponent> generateFirstAssignment(Module module, Library library, int outCount){
        Map<String,CandidateComponent> assignment = new HashMap<>();
        Set<URI> uris = new HashSet<>();
        
        //<editor-fold desc="Find Component Type Count">
        int genericCDS = 0;
        int genericRBS = 0;
        int genericProm = 0;
        int genericTer = 0;
        
        int cp=0;
        int ip=0;
        int rp=0;
        int ap=0;
        
        int specOutCDS = 0;
        int specConnCDS = 0;
        int specActCDS = 0;
        int specRepCDS = 0;
        
        int specRBS = 0;
        int specTer = 0;
        
        Set<String> specComp = new HashSet<String>();
        for(Component c:module.getComponents()){
            if(specComp.contains(c.getName())){
                continue;
            }
            switch(c.getRole()){
                case GENERIC_PROMOTER:
                    genericProm++;
                    break;
                case PROMOTER_INDUCIBLE:
                    ip++;
                    specComp.add(c.getName());
                    break;
                case PROMOTER_REPRESSIBLE:
                    rp++;
                    specComp.add(c.getName());
                    break;
                case PROMOTER_ACTIVATABLE:
                    ap++;
                    specComp.add(c.getName());
                    break;
                case PROMOTER_CONSTITUTIVE:
                    cp++;
                    specComp.add(c.getName());
                    break;
                
                case GENERIC_RBS:
                    genericRBS++;
                    break;
                case RBS:
                    specRBS++;
                    specComp.add(c.getName());
                    break;
                
                case GENERIC_CDS:
                    genericCDS++;
                    break;
                case CDS_REPRESSOR:
                    specRepCDS++;
                    specConnCDS++;
                    specComp.add(c.getName());
                    break;
                case CDS_ACTIVATOR:
                    specActCDS++;
                    specConnCDS++;
                    specComp.add(c.getName());
                    break;
                case CDS_FLUORESCENT:
                    specOutCDS++;
                    specComp.add(c.getName());
                    break;
                
                case GENERIC_TERMINATOR:
                    genericTer++;
                    break;
                case TERMINATOR:
                    specTer++;
                    specComp.add(c.getName());
                    break;
            }
        }
        //</editor-fold>
        
        List<Module> prModule = new ArrayList<>();
        List<Module> promModule = new ArrayList<>();
        List<Module> cdsModule = new ArrayList<>();
        List<Module> rcModule = new ArrayList<>();
        List<Module> terModule = new ArrayList<>();
        List<Module> children = new ArrayList<>();
        for(Module tu:module.getChildren()){
            children.addAll(tu.getChildren());
            for(Module child:tu.getChildren()){
                if(child.getRole().equals(ModuleRole.PROMOTER_RBS)){
                    prModule.add(child);
                } else if(child.getRole().equals(ModuleRole.TERMINATOR)){
                    terModule.add(child);
                } else if(child.getRole().equals(ModuleRole.RBS_CDS)){
                    rcModule.add(child);
                } else if(child.getRole().equals(ModuleRole.CDS)){
                    cdsModule.add(child);
                } else if(child.getRole().equals(ModuleRole.PROMOTER)){
                    promModule.add(child);
                }
            }
        }
        System.out.println("Out Count : " + outCount);
        System.out.println("Specific Out CDS : " + specOutCDS);
        System.out.println("Specific Terminators : " + specTer);
        int remainingOut = outCount - specOutCDS;
        
        //Start Assinging Output CDS
        //<editor-fold desc="Assign Specific Output CDS and Specific Terminators">
        while( (specOutCDS > 0) || (specTer > 0) ){
            boolean cdsAssigned = false;
            boolean terAssigned = false;
            for(Module m:children){
                if(m.getRole().equals(ModuleRole.CDS)){
                    if (!cdsAssigned && (specOutCDS > 0)) {
                        Component comp = m.getComponents().get(0);
                        if (!assignment.containsKey(comp.getName())) {
                            if (comp.getRole().equals(ComponentRole.CDS_FLUORESCENT)) {
                                //Add here.
                                int r = getRandom(0, m.getCandidates().size() - 1);
                                CandidateComponent cc = m.getCandidates().get(r);
                                while (uris.contains(cc.getCandidate().getComponentDefintion())) {
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                }
                                uris.add(cc.getCandidate().getComponentDefintion());
                                assignment.put(comp.getName(), cc);
                                specOutCDS--;
                                cdsAssigned = true;
                            }
                        }
                    }
                } else if(m.getRole().equals(ModuleRole.RBS_CDS)){
                    //Do this later...
                } else if(m.getRole().equals(ModuleRole.TERMINATOR)){
                    if( (!terAssigned) && (specTer > 0)){
                        Component comp = m.getComponents().get(0);
                        if(!assignment.containsKey(comp.getName())){
                            if(comp.getRole().equals(ComponentRole.TERMINATOR)){
                                int r = getRandom(0, m.getCandidates().size() - 1);
                                CandidateComponent cc = m.getCandidates().get(r);
                                while (uris.contains(cc.getCandidate().getComponentDefintion())) {
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                }
                                uris.add(cc.getCandidate().getComponentDefintion());
                                assignment.put(comp.getName(), cc);
                                specTer--;
                                terAssigned = true;
                            }
                        }
                    }
                }
                
                if(terAssigned && cdsAssigned){
                    break;
                }
            }
        }
        //</editor-fold>
        
        //<editor-fold desc="Now Assign Remaining Output CDS in Generic CDS"> 
        while(remainingOut > 0){
            for(Module m:children){
                if(m.getRole().equals(ModuleRole.CDS)){
                    Component comp = m.getComponents().get(0);
                    if(!assignment.containsKey(comp.getName())){
                        if(comp.getRole().equals(ComponentRole.GENERIC_CDS) && comp.getInteractions().isEmpty()){ //Check if Assignment is 
                            int r = getRandom(0,m.getCandidates().size()-1);
                            CandidateComponent cc = m.getCandidates().get(r);
                            while(uris.contains(cc.getCandidate().getComponentDefintion()) || (!library.getOutputCDS().containsKey(cc.getCandidate().getComponentDefintion()))){
                                r = getRandom(0,m.getCandidates().size()-1);
                                cc = m.getCandidates().get(r);
                            }
                            uris.add(cc.getCandidate().getComponentDefintion());
                            assignment.put(comp.getName(), cc);
                            remainingOut--;
                            break;
                        }
                    }
                } else if(m.getRole().equals(ModuleRole.RBS_CDS)){
                    //Do this later.
                }
            }
        }
        //</editor-fold> 
        
        //<editor-fold desc="Assign Generic Terminators">
        for(Module m:terModule){
            Component comp = m.getComponents().get(0);
            if(!assignment.containsKey(comp.getName())  && comp.getRole().equals(ComponentRole.GENERIC_TERMINATOR)){
                int r = getRandom(0,m.getCandidates().size()-1);
                CandidateComponent cc = m.getCandidates().get(r);
                uris.add(cc.getCandidate().getComponentDefintion());
                assignment.put(comp.getName(), cc);
            }
        }
        //</editor-fold> 
        
        //<editor-fold desc="Search through P-Rs for interactions and assign them"> 
        for(Module m:prModule){
            Component prom = m.getComponents().get(0); //Change to make sure this is infact the promoter
            Component rbs = m.getComponents().get(1); //Change to make sure this is infact the rbs.
            //<editor-fold desc="PR has interactions">
            if(!prom.getInteractions().isEmpty()){
                for(Interaction i:prom.getInteractions()){
                    Component from = i.getFrom(); //This is the CDS. Should get assigned based on the PR chosen here. Which is simpler because 1 CDS to many PR
                    
                    if(assignment.containsKey(rbs.getName())){
                        if(assignment.containsKey(prom.getName())){
                            //assignment contains both prom and rbs. So no need to do anything here. We all good here boiiiii...
                        } else {
                            //assignment contains rbs. no prom though. Find a prom now. Make the cds proud.. 
                            int r = getRandom(0,m.getCandidates().size() - 1);
                            CandidateComponent cc = m.getCandidates().get(r);
                            CompositeComponent compcomp = (CompositeComponent)cc.getCandidate();
                            URI rbsuri = compcomp.getChildren().get(1);
                            if(prom.getRole().equals(ComponentRole.GENERIC_PROMOTER)){
                                while(!rbsuri.equals(getPRrbs(assignment.get(rbs.getName())))){
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                    compcomp = (CompositeComponent) cc.getCandidate();
                                    rbsuri = compcomp.getChildren().get(1);
                                }
                                uris.add(getPRprom(cc));
                                assignment.put(prom.getName(), cc);
                            } else {
                                while( (!rbsuri.equals(getPRrbs(assignment.get(rbs.getName())))) || (uris.contains(compcomp.getChildren().get(0))) ){
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                    compcomp = (CompositeComponent) cc.getCandidate();
                                    rbsuri = compcomp.getChildren().get(1);
                                }
                                uris.add(getPRprom(cc));
                                assignment.put(prom.getName(), cc);
                            }
                        }
                    }
                    else {
                        if(assignment.containsKey(prom.getName())){
                            //assignment contains prom. no rbs.
                            int r = getRandom(0,m.getCandidates().size() - 1);
                            CandidateComponent cc = m.getCandidates().get(r);
                            CompositeComponent compcomp = (CompositeComponent)cc.getCandidate();
                            URI promuri = compcomp.getChildren().get(0);
                            if(prom.getRole().equals(ComponentRole.GENERIC_RBS)){
                                while(!promuri.equals(getPRprom(assignment.get(prom.getName())))){
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                    compcomp = (CompositeComponent) cc.getCandidate();
                                    promuri = compcomp.getChildren().get(0);
                                }
                                uris.add(getPRrbs(cc));
                                assignment.put(rbs.getName(), cc);
                            } else {
                                while( (!promuri.equals(getPRprom(assignment.get(prom.getName())))) || (uris.contains(compcomp.getChildren().get(1))) ){
                                    r = getRandom(0, m.getCandidates().size() - 1);
                                    cc = m.getCandidates().get(r);
                                    compcomp = (CompositeComponent) cc.getCandidate();
                                    promuri = compcomp.getChildren().get(0);
                                }
                                uris.add(getPRrbs(cc));
                                assignment.put(rbs.getName(), cc);
                            }
                            
                        } else {
                            //assignment has no prom no rbs. Choose whatever you like boiii 
                            System.out.println("ap1 and r1 should appear here first.." + prom.getName() + "," + rbs.getName());
                            System.out.println("Number of candidates for m = " + m.getCandidates().size());
                            int r = getRandom(0,m.getCandidates().size() - 1);
                            CandidateComponent cc = m.getCandidates().get(r);
                            if(prom.getRole().equals(ComponentRole.GENERIC_PROMOTER)){
                                if(rbs.getRole().equals(ComponentRole.GENERIC_RBS)){
                                    
                                } else {
                                    //RBS is not generic.
                                    while(uris.contains(getPRrbs(cc))){
                                        r = getRandom(0, m.getCandidates().size() - 1);
                                        cc = m.getCandidates().get(r);
                                    }
                                }
                            } else {
                                if(rbs.getRole().equals(ComponentRole.GENERIC_RBS)){
                                    while(uris.contains(getPRprom(cc))){
                                        r = getRandom(0, m.getCandidates().size() - 1);
                                        cc = m.getCandidates().get(r);
                                    }
                                } else {
                                    while(uris.contains(getPRprom(cc)) || uris.contains(getPRrbs(cc))){
                                        r = getRandom(0, m.getCandidates().size() - 1);
                                        cc = m.getCandidates().get(r);
                                    }
                                }
                            }
                            uris.add(getPRrbs(cc));
                            uris.add(getPRprom(cc));
                            assignment.put(rbs.getName(), cc);
                            assignment.put(prom.getName(), cc);
                        }
                    }
                    
                    if(!assignment.containsKey(from.getName())) {
                        CompositeComponent compcomp = (CompositeComponent) (assignment.get(prom.getName()).getCandidate());
                        PromoterComponent promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                        URI protein = null;
                        for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                            if (tf instanceof PrimitiveComponent) {
                                PrimitiveComponent pc = (PrimitiveComponent) tf;
                                protein = pc.getComponentDefintion();
                            } else if (tf instanceof ComplexComponent) {
                                ComplexComponent complex = (ComplexComponent) tf;
                                protein = complex.getProtein();
                            }
                            break;
                        }
                        for(Module cm:cdsModule){
                            Component cds = cm.getComponents().get(0); //Change this obviously..
                            if(assignment.containsKey(cds.getName())){
                                continue;
                            }
                            if(cds.getName().equals(from.getName())){
                                int r = getRandom(0,cm.getCandidates().size() - 1);
                                CandidateComponent cc = cm.getCandidates().get(r);
                                CDSComponent cdscomp = (CDSComponent)cc.getCandidate();
                                //Generic vs specific
                                while(!cdscomp.getProtein().equals(protein)){
                                    r = getRandom(0,cm.getCandidates().size() - 1);
                                    cc = cm.getCandidates().get(r);
                                    cdscomp = (CDSComponent)cc.getCandidate();
                                }
                                uris.add(cc.getCandidate().getComponentDefintion());
                                assignment.put(from.getName(), cc);
                            }
                        }
                    }
                    
                    
                }
            }
            //</editor-fold>
            //<editor-fold desc="PR has NO interactions">
            else {
                if(!prom.getRole().equals(ComponentRole.GENERIC_PROMOTER)){
                    //We are not talking about a generic promoter here...
                    //This is just for all those 
                    
                    for(Module cds:cdsModule){
                        
                    }
                }
            }
            //</editor-fold>
            
        }
        //</editor-fold> 
        
        //<editor-fold desc=""> 
        //</editor-fold> 
        
        //<editor-fold desc=""> 
        //</editor-fold> 
        
        return assignment;
    }
    
    
    private URI getPRrbs(CandidateComponent cc){
        CompositeComponent compcomp = (CompositeComponent)cc.getCandidate();
        return compcomp.getChildren().get(1);
    }
    
    private URI getPRprom(CandidateComponent cc){
        CompositeComponent compcomp = (CompositeComponent)cc.getCandidate();
        return compcomp.getChildren().get(0);
    }
    
    
    private Map<String,CandidateComponent> findNeighbour(){
        Map<String,CandidateComponent> neighbour = new HashMap<>();
        
        return neighbour;
    }
    
    
    
    
}
