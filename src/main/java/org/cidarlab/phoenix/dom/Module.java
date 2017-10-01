/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author prash
 */
public class Module {
    
    @Getter
    @Setter
    private Model model;

    //This is the part where it gets decomposed
    @Getter
    @Setter
    private List<Module> children = new ArrayList<>();
    
    @Getter
    @Setter
    private Module parent; //Should this be a list as well?
    
    @Getter
    @Setter
    private List<Component> components = new ArrayList<>();
        
    @Getter
    @Setter
    private String id;
    
    @Getter
    @Setter
    private ModuleRole role;
    
    @Getter
    @Setter
    private boolean root;
    
    //Get all Interactions
    
    public String getComponentString(){
        String cstring = "";
        for(Component c: this.components){
            if(c.getOrientation().equals(Orientation.REVERSE)){
                cstring += "r";
            } else {
                cstring += "f";
            }
            cstring += (c.getRole().toString() + ";");
        }
        return cstring;
    }
    
    
    public Component findComponent(String componentName){
        for(Component c:this.components){
            if(c.getName().equals(componentName)){
                return c;
            }
        }
        return null;
    }
    
    public void addComponent(Component c){
        this.components.add(c);
    }
    
    public void addChild(Module child){
        this.children.add(child);
    }
    
    public Module(String _id){
        this.id = _id;
    }
    
    
    public static enum ModuleRole{
        HIGHER_FUNCTION,
        TRANSCRIPTIONAL_UNIT,
        PROMOTER,
        CDS, 
        BIOCPS_INPUT,
        BIOCPS_MODULE,
        BIOCPS_OUTPUT
    }
    
    
}
