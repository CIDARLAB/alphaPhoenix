/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.failuremode.FailureMode;

/**
 *
 * @author prash
 */
public class Module {
    
    @Getter
    @Setter
    private List<FailureMode> failureModes = new ArrayList<>();
    
    @Getter
    @Setter
    private List<List<CandidateComponent>> assignments = new ArrayList<>();
    
    @Getter
    @Setter
    private Orientation orientation;
    
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
    
    public void printTree(){
        printTree(0);
    }
    
    public void setIOCNames(){
        int in = 0;
        int out = 0;
        int conn = 0;
        Set<String> completed = new HashSet<>();
        for(Component c:this.components){
            if(!completed.contains(c.getName())){
                if(Controller.isPromoter(c)){
                    if(c.getInteractions().isEmpty()){
                        if(c.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)){
                            
                        } else {
                            c.setIOCname("in" + in);
                            in++;
                        }
                    } else {
                        for(Interaction i:c.getInteractions()){
                            i.getFrom().setIOCname("conn" + conn);
                            i.getTo().setIOCname("conn" + conn);
                        }
                        conn++;
                    }
                    
                } else if(Controller.isCDS(c)){
                    if(c.getInteractions().isEmpty()){
                        c.setIOCname("out" + out);
                        out++;
                    } else {
                        for(Interaction i:c.getInteractions()){
                            i.getFrom().setIOCname("conn" + conn);
                            i.getTo().setIOCname("conn" + conn);
                        }
                        conn++;
                    }
                }
            }
        }
        
    }
    
    private void printTree(int indent){
        for(int i=0;i<indent;i++){
            System.out.print("-");
        }
        System.out.print(this.role.toString() + " :: " + this.getComponentString() + "\n");
        for(Module child:this.children){
            child.printTree(indent+1);
        }
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
