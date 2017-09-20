/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.minieugene.MiniEugene;
import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.dom.ComponentType;
import org.cidarlab.minieugene.exception.MiniEugeneException;
import org.cidarlab.minieugene.util.FileUtil;
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
                phoenixModules = componentToModule(solutions, nameRoot);
            } catch (MiniEugeneException ex) {
                Logger.getLogger(MiniEugeneAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                mE.solve(rules, size);
                List<Component[]> solutions = mE.getSolutions();
                phoenixModules = componentToModule(solutions, nameRoot);
            } catch (MiniEugeneException ex) {
                Logger.getLogger(MiniEugeneAdaptor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return phoenixModules;
    }
    
    //This method converts Eugene components to Clotho modules
    public static List<Module> componentToModule(List<Component[]> eugeneDevices, String nameRoot) {
        
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
                phoenixComponent.setRole(findRole(ctype));
                
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
            phoenixModules.add(phoenixModule);             
        }
        
        return phoenixModules;
    }
    
    //Determine primitive role from Eugene component types
    public static org.cidarlab.phoenix.dom.Component.ComponentRole findRole(ComponentType type) {
        
        org.cidarlab.phoenix.dom.Component.ComponentRole role = org.cidarlab.phoenix.dom.Component.ComponentRole.WILDCARD;
        if (type.getName().startsWith("p")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER;
        } else if (type.getName().startsWith("ip")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_INDUCIBLE;
        } else if (type.getName().startsWith("rp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_REPRESSIBLE;
        } else if (type.getName().startsWith("cp")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.PROMOTER_CONSTITUTIVE;
        } else if (type.getName().startsWith("rc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_REPRESSIBLE_REPRESSOR;
        } else if (type.getName().startsWith("fc")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_ACTIVATIBLE_ACTIVATOR;
        } else if (type.getName().startsWith("r")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.RBS;
        } else if (type.getName().startsWith("c")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_ACTIVATOR;
        } else if (type.getName().startsWith("g")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_REPRESSOR;
        } else if (type.getName().startsWith("t")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.TERMINATOR;
        } else if (type.getName().startsWith("unk")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS;
        } else if (type.getName().startsWith("fl")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_FLUORESCENT;
        } else if (type.getName().startsWith("l")) {
            role = org.cidarlab.phoenix.dom.Component.ComponentRole.CDS_LINKER;
        } 
         
        return role;
    }
    
}
