/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.stream.XMLStreamException;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.simulation.AbstractSimulation;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent.CompositeType;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;

/**
 *
 * @author prash
 */
public class AssignmentNode {
    
    @Getter
    private List<Component> components;
    
    @Getter
    private List<String> smallMolecules;
    
    @Getter
    private Map<String,CandidateComponent> assignment;
   
    @Getter
    private Map<String,Double> concs;
    
    @Getter
    private Map<String,URI> smURImap;
    
    @Getter
    private Map<String,String> indSMmap;
    
    @Getter
    private Map<String,String> ioc;
    
    @Getter
    @Setter
    private int moduleIndex;
    
    @Getter
    @Setter
    private int assignmentIndex;
    
    @Getter
    @Setter
    private double score;
    
    public int hammingDistance(AssignmentNode _node){
        int count = 0;
        for(Component c:this.components){
            if(_node.assignment.containsKey(c.getName())){
                URI thisuri = this.assignment.get(c.getName()).getCandidate().getComponentDefintion();
                URI nodeuri = _node.assignment.get(c.getName()).getCandidate().getComponentDefintion();
                if(!thisuri.equals(nodeuri)){
                    count++;
                } 
            } else {
                count++;
            }
        }
        
        for(String sm:this.smallMolecules){
            if(_node.concs.containsKey(sm)){
                double thisconc = this.concs.get(sm);
                double nodeconc = _node.concs.get(sm);
                if(thisconc != nodeconc){
                    count++;
                }
            } else {
                count++;
            }
        }
        return count;
    }
    
    public AssignmentNode(AssignmentNode _node){
        this.components = new ArrayList<>(_node.components);
        this.smallMolecules = new ArrayList<>(_node.smallMolecules);
        this.assignment = new HashMap<>(_node.assignment);
        this.concs = new HashMap<>(_node.concs);
        this.smURImap = new HashMap<>(_node.smURImap);
        this.indSMmap = new HashMap<>(_node.indSMmap);
        this.ioc = new HashMap<>(_node.ioc);
    } 
    
    public AssignmentNode(Module module, Map<String,CandidateComponent> _candidate, Library library){
        
        this.assignment = _candidate;
        this.components = new ArrayList<>();
        
        for(Component c:module.getComponents()){
            this.components.add(c);
        }
            
        this.ioc = AbstractSimulation.getIOCmap(module, assignment, library);
        this.indSMmap = AbstractSimulation.getIndSMmap(module, assignment, ioc, library);
        
        this.smallMolecules = new ArrayList<>();
        this.smURImap = new HashMap<>();
        
        for(String sm:this.indSMmap.keySet()){
            //System.out.println("SM : " + sm);
            this.smallMolecules.add(sm);
        }
        
        for(URI smURI:library.getSmallMolecules().keySet()){
            SmallMoleculeComponent smc = library.getSmallMolecules().get(smURI);
            if(this.smallMolecules.contains(smc.getName())){
                this.smURImap.put(smc.getName(),smURI);
            }
        }
        
        this.concs = new HashMap<>();
        
    }
    
    public AssignmentNode(Module module, Map<String,CandidateComponent> _candidate, Map<String, String> _ioc, Library library){
        
        this.assignment = _candidate;
        this.components = new ArrayList<>();
        
        for(Component c:module.getComponents()){
            this.components.add(c);
        }
            
        this.ioc = _ioc;
        this.indSMmap = new HashMap<>();
        
        this.smallMolecules = new ArrayList<>();
        this.smURImap = new HashMap<>();
        this.concs = new HashMap<>();
        
    }
    
    public AssignmentNode(Module module, Map<String,CandidateComponent> _candidate, Map<String, String> _ioc, Map<String, Double> _concs, Library library){
        
        this.assignment = _candidate;
        this.components = new ArrayList<>();
        
        for(Component c:module.getComponents()){
            this.components.add(c);
        }
            
        this.ioc = _ioc;
        
        this.smallMolecules = new ArrayList<>();
        this.smURImap = new HashMap<>();
        this.concs = new HashMap<>();
        
        this.indSMmap = AbstractSimulation.getIndSMmap(module, assignment, ioc, library);
        
        
        for(String sm:this.indSMmap.keySet()){
            //System.out.println("SM : " + sm);
            this.smallMolecules.add(sm);
        }
        
        for(URI smURI:library.getSmallMolecules().keySet()){
            SmallMoleculeComponent smc = library.getSmallMolecules().get(smURI);
            if(this.smallMolecules.contains(smc.getName())){
                this.smURImap.put(smc.getName(),smURI);
            }
        }
        
        for(String smName:indSMmap.keySet()){
            this.concs.put(smName, _concs.get( indSMmap.get(smName)));
        }
        
        
    }
    
    
    
    public void assignRandomConcentrations(Library library){
        for(String sm:smallMolecules){
            SmallMoleculeComponent smc = library.getSmallMolecules().get(smURImap.get(sm));
            double conc = smc.getValues().get(Utilities.getRandom(0,(smc.getValues().size()-1)));
            this.concs.put(sm, conc);
        }
    }
    
    public int getRandomPosition(){
        int total = components.size() + smallMolecules.size();
        return Utilities.getRandom(0, total-1);
    }
    
    public void changeConc(String sm, double conc){
        this.concs.put(sm, conc);
    }
    
    public double robustness(Module module, TreeNode stl, Library library, Map<URI,SBMLDocument> modelmap, Args.Decomposition decomposition, String fp) throws URISyntaxException, MalformedURLException, XMLStreamException, FileNotFoundException, IOException, TLIException{
        double rob = 0.0;
        
        double maxtime = STLAdaptor.getMaxTime(stl);
        
        AbstractSimulation.assignLeafModels(module, assignment, library.getSbol(), modelmap, decomposition);
        AbstractSimulation.renameSpecies(module, ioc, library, decomposition);
        AbstractSimulation.composeModels(module, decomposition);
            
        SBMLDocument sbml = new SBMLDocument(module.getModel().getSbml());
        SBMLWriter writer = new SBMLWriter();
        String modelFile = fp + "model.xml";
        for(String ind : concs.keySet()) {
            SBMLAdaptor.addEvent(sbml, indSMmap.get(ind), 600.00, concs.get(ind));
        }
        writer.write(sbml, modelFile);
        
        IBioSimAdaptor.simulateODE(modelFile, fp, maxtime, 10, 10);
        String tsdfp = fp + "run-1.csv";
        Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
        Map<String, TreeNode> stlmap = STLAdaptor.getSignalSTLMap(stl);
        Map<String, Signal> allsignals = new HashMap<>();
        
        for (String key : stlmap.keySet()) {
            if (signalMap.containsKey(key)) {
                Signal s = signalMap.get(key);
                if (!allsignals.containsKey(key)) {
                    allsignals.put(key, s);
                }
            }
        }
        
        List<String> signalList = new ArrayList<>(allsignals.keySet());
        rob = STLAdaptor.signalRobustness(stl,allsignals.get(signalList.get(0)));
        
        for(int i=1;i<signalList.size();i++){
            double currentRob = STLAdaptor.signalRobustness(stl,allsignals.get(signalList.get(i)));
            if(currentRob < rob){
                rob = currentRob;
            }
        }
        
        return rob;
    }
    
    
    @Override
    public String toString(){
        String str = getComponentString();
        for(String sm:concs.keySet()){
            str += sm + "=" + concs.get(sm) + ";";
        }
        return str;
    }
    

    public String toString(Library library){
        String str = getComponentString(library);
        for(String sm:concs.keySet()){
            str += sm + "=" + concs.get(sm) + ";";
        }
        return str;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof AssignmentNode){
            AssignmentNode an = (AssignmentNode)o;
            if(an.getComponentString().equals(this.getComponentString())){
                if(an.getConcs().equals(this.getConcs())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.toString());
        return hash;
    }
    
    public String getComponentString(){
        String str = "";
        for(Component c:components){
            
            LibraryComponent lc = assignment.get(c.getName()).getCandidate();
            if(lc instanceof CompositeComponent){
                
            }
            
            str += assignment.get(c.getName()).getCandidate().getName() + ";";
        } 
        return str;
    }
    
    public String getComponentString(Library library){
        String str = "";
        for(Component c:components){
            
            LibraryComponent lc = assignment.get(c.getName()).getCandidate();
            if(lc instanceof CompositeComponent){
                CompositeComponent composite = (CompositeComponent)lc;
                if(composite.getType().equals(CompositeType.PR)){
                    if(c.isPromoter()){
                        str += library.getAllLibraryComponents().get(composite.getChildren().get(0)).getName() + ";";
                    } else if(c.isRBS()){
                        str += library.getAllLibraryComponents().get(composite.getChildren().get(1)).getName() + ";";
                    }
                } else if(composite.getType().equals(CompositeType.RC)){
                    if(c.isRBS()){
                        str += library.getAllLibraryComponents().get(composite.getChildren().get(0)).getName() + ";";
                    } else if(c.isCDS()){
                        str += library.getAllLibraryComponents().get(composite.getChildren().get(1)).getName() + ";";
                    }
                }
            } else {
                str += lc.getName() + ";";
            }
             
            
        } 
        return str;
    }
    
    public void modifyAssignment(Module module, Map<String,CandidateComponent> _assignment, Map<String,Double> oldConcs, Library library){
        this.assignment.putAll(_assignment); 
        this.ioc = AbstractSimulation.getIOCmap(module, assignment, library);
        indSMmap = AbstractSimulation.getIndSMmap(module, assignment, ioc, library);
        
        smallMolecules = new ArrayList<>();
        smURImap = new HashMap<>();
        
        for(String sm:indSMmap.keySet()){
            //System.out.println("SM : " + sm);
            smallMolecules.add(sm);
        }
        
        for(URI smURI:library.getSmallMolecules().keySet()){
            SmallMoleculeComponent smc = library.getSmallMolecules().get(smURI);
            if(smallMolecules.contains(smc.getName())){
                smURImap.put(smc.getName(),smURI);
            }
        }
        
        this.concs = new HashMap<>();
        for(String oldSm:oldConcs.keySet()){
            if(smallMolecules.contains(oldSm)){
                this.concs.put(oldSm, oldConcs.get(oldSm));
            }
        }
        for(String newSm:smallMolecules){
            if(!this.concs.containsKey(newSm)){
                SmallMoleculeComponent smc = library.getSmallMolecules().get(smURImap.get(newSm));
                double conc = smc.getValues().get(Utilities.getRandom(0, (smc.getValues().size() - 1)));
                this.concs.put(newSm, conc);
            }
        }
    }
    
    
    
}
