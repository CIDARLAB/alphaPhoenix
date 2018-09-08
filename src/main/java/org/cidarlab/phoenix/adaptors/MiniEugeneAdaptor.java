/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.predicates.interaction.Interaction;
import org.cidarlab.minieugene.predicates.interaction.Participation;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class MiniEugeneAdaptor {
    
    
    //This method returns all structures for a given miniEugene file (assuming numSolutions is not mentioned)    
    public static List<Module> getStructures(String input, int size, String nameRoot) {
        return getStructures(input,size,null,nameRoot);
    }
    
    //This method returns all structures for a given miniEugene file    
    public static List<Module> getStructures(String input, int size, Integer numSolutions, String nameRoot)  {
       
        //Remove lines that are comments or empty lines
        ArrayList<String> rulesList = new ArrayList<>();
        for (String line : Utilities.getFileContentAsStringList(input)) {
            line = line.trim();
            
            //Add lines to rules list
            if (!(line.startsWith("//") || line.isEmpty())) {
                rulesList.add(line);
            }
        }
        String[] rules = rulesList.toArray(new String[rulesList.size()]);
        
        //Make miniEugene object and find solutions
        MiniEugene mE = new MiniEugene();
        List<Module> phoenixModules = null;
        
        //Return number of solutions based upon input
        //If numSolutions is negative, return all, if it is greater than solution size, also return all
        if (numSolutions != null) {
            try {
                mE.solve(rules, size, numSolutions);
                List<Component[]> solutions = mE.getSolutions();
                Set<Interaction> interactions = mE.getInteractions();
                phoenixModules = componentToModule(solutions, interactions, nameRoot);
            } catch (MiniEugeneException ex) {
                Logger.getLogger(MiniEugeneAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                mE.solve(rules, size);
                List<Component[]> solutions = mE.getSolutions();
                Set<Interaction> interactions = mE.getInteractions();
                phoenixModules = componentToModule(solutions, interactions, nameRoot);
            } catch (MiniEugeneException ex) {
                Logger.getLogger(MiniEugeneAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return phoenixModules;
    }
    
    //This method converts Eugene components to Clotho modules
    public static List<Module> componentToModule(List<Component[]> eugeneDevices, Set<Interaction> interactions, String nameRoot) {
        
        List<Module> phoenixModules = new ArrayList<>();
        int count = 0;
        //For each device, translate all components to features
        //These will be features without a sequence, only a type and direction
        for (Component[] eugeneDevice : eugeneDevices) {
            
            Module phoenixModule = new Module(nameRoot + "_" + count++);
            for (Component c : eugeneDevice) {
                
                //Determine if this component is a coding sequence
                String type = c.getType().getName();
                
                //Create a new feature and add it to module features
                ComponentType ctype = new ComponentType(type);
                
                org.cidarlab.phoenix.dom.Component phoenixComponent = new org.cidarlab.phoenix.dom.Component();
                phoenixComponent.setName(c.getName());
                phoenixComponent.setRole(findRole(c.getName()));
                
                //Primitive orientation                
                if (c.isForward()) {
                    phoenixComponent.setOrientation(Orientation.FORWARD);
                } else {
                    phoenixComponent.setOrientation(Orientation.REVERSE);
                }
                
                phoenixModule.addComponent(phoenixComponent);
            }
            
            phoenixModule.setRole(ModuleRole.HIGHER_FUNCTION);
            phoenixModule.setRoot(true);
            addInteractions(phoenixModule, interactions);
            renameGenericComponents(phoenixModule);
            phoenixModules.add(phoenixModule);             
        }
        
        return phoenixModules;
    }
    
    private static void renameGenericComponents(Module module){
        
        Set<String> specific = new HashSet<String>();
        int ap = 0;
        int ar = 0;
        int ac = 0;
        int at = 0;
         for(org.cidarlab.phoenix.dom.Component c:module.getComponents()){
            switch(c.getRole()){
                case GENERIC_PROMOTER:
                case GENERIC_RBS:
                case GENERIC_CDS:
                case GENERIC_TERMINATOR:
                    break;
                default:
                    specific.add(c.getName());
                    break;        
                    
            }
         }
        for(org.cidarlab.phoenix.dom.Component c:module.getComponents()){
            switch(c.getRole()){
                case GENERIC_PROMOTER:
                    String pname = c.getName() + ap;
                    ap++;
                    c.setName(pname);
                    break;
                case GENERIC_RBS:
                    String rname = c.getName() + ar;
                    ar++;
                    while(specific.contains(rname)){
                        rname = c.getName() + ar;
                        ar++;
                    }
                    c.setName(rname);
                    break;
                case GENERIC_CDS:
                    String cname = c.getName() + ac;
                    ac++;
                    c.setName(cname);
                    break;
                case GENERIC_TERMINATOR:
                    String tname = c.getName() + at;
                    at++;
                    while(specific.contains(tname)){
                        tname = c.getName() + at;
                        at++;
                    }
                    c.setName(tname);
                    break;
            }
        }
    }
    
    private static void addInteractions(Module module, Set<Interaction> interactions){
        for(Interaction i:interactions){
            org.cidarlab.phoenix.dom.Component from = null;
            org.cidarlab.phoenix.dom.Component to = null;
            for(Participation p:i.getParticipations()){
                if(p.getRole().equals(Participation.Role.INDUCER) || p.getRole().equals(Participation.Role.REPRESSOR)){
                    from = module.findComponent(p.getParticipant().getName());
                } else if (p.getRole().equals(Participation.Role.INDUCEE) || p.getRole().equals(Participation.Role.REPRESSEE)){
                    to = module.findComponent(p.getParticipant().getName());
                }
                else{
                    //Throw an error for now?
                    System.err.println(p.getRole().toString() + " not supported yet.");
                }
            }
            if(from == null || to == null){
                System.err.println("From or To node is null");
                System.exit(-1);
            }
            org.cidarlab.phoenix.dom.Interaction phoenixInteraction = new org.cidarlab.phoenix.dom.Interaction(from,to,i.getType());
            from.addInteraction(phoenixInteraction);
            to.addInteraction(phoenixInteraction);
        } 
    }
    
    
    //Determine primitive role from Eugene component name
    public static org.cidarlab.phoenix.dom.Component.ComponentRole findRole(String type) {
        
        org.cidarlab.phoenix.dom.Component.ComponentRole role = org.cidarlab.phoenix.dom.Component.ComponentRole.WILDCARD;
        
        if(type.equals("p")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_PROMOTER;
        } else if(type.equals("r")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_RBS;  
        } else if(type.equals("c")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_CDS;  
        } else if(type.equals("t")){
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_TERMINATOR;
        } else if (type.startsWith("cp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_CONSTITUTIVE;
        } else if (type.startsWith("ap")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_ACTIVATABLE;
        } else if (type.startsWith("rp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_REPRESSIBLE;
        } else if (type.startsWith("ac")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_ACTIVATOR;
        } else if (type.startsWith("rc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_REPRESSOR;
        } else if (type.startsWith("fc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_FLUORESCENT;
        } else if (type.startsWith("t")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.TERMINATOR;
        } else if (type.startsWith("r")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.RBS;
        }  
        return role;
    }
    
    
    //Determine primitive role from Eugene component types
    public static org.cidarlab.phoenix.dom.Component.ComponentRole findRole(ComponentType type) {
        
        org.cidarlab.phoenix.dom.Component.ComponentRole role = org.cidarlab.phoenix.dom.Component.ComponentRole.WILDCARD;
        
        if(type.getName().equals("p")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_PROMOTER;
        } else if(type.getName().equals("r")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_RBS;  
        } else if(type.getName().equals("c")){
          role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_CDS;  
        } else if(type.getName().equals("t")){
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.GENERIC_TERMINATOR;
        } else if (type.getName().startsWith("cp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_CONSTITUTIVE;
        } else if (type.getName().startsWith("ap")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_ACTIVATABLE;
        } else if (type.getName().startsWith("rp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_REPRESSIBLE;
        } else if (type.getName().startsWith("ac")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_ACTIVATOR;
        } else if (type.getName().startsWith("rc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_REPRESSOR;
        } else if (type.getName().startsWith("fc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_FLUORESCENT;
        } else if (type.getName().startsWith("t")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.TERMINATOR;
        } else if (type.getName().startsWith("r")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.RBS;
        }  
        return role;
    }
    
}
