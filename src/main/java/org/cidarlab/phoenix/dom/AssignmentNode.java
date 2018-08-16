/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import org.cidarlab.phoenix.core.simulation.ExhaustiveSimulation;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.cidarlab.phoenix.utils.Utilities;

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
    
    public AssignmentNode(Module module, Map<String,CandidateComponent> _candidate, Library library){
        assignment = _candidate;
        components = new ArrayList<>();
        
        for(Component c:module.getComponents()){
            components.add(c);
        }
            
        Map<String, String> ioc = ExhaustiveSimulation.getIOCmap(module, assignment, library);
        Map<String,String> indSMmap = ExhaustiveSimulation.getIndSMmap(module, assignment, ioc, library);
        
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
    
    
    @Override
    public String toString(){
        String str = getComponentString();
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
            str += assignment.get(c.getName()).getCandidate().getName() + ";";
        } 
        return str;
    }
    
}
