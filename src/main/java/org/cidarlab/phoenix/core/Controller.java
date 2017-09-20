/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class Controller {
    
    public static Module decompose(PhoenixMode mode, Module root){
        switch (mode){
            case MODULE:
                return null;
            case PARTS:
                //Forward Strand
                boolean started = false;
                int forwardCount = 0;
                List<Component> components = null;
                for(Component c:root.getComponents()){
                    if(!started){
                        if(c.getOrientation().equals(Orientation.FORWARD)){
                            if(isPromoter(c)){
                                started = true;
                                components = new ArrayList<Component>();
                                components.add(c);
                            }
                            else{
                                //Throw an error?
                                continue;
                            }
                        } 
                        else {
                            //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                            continue;
                        }
                    }
                    //Started Forward strand
                    else{
                        if(c.getOrientation().equals(Orientation.FORWARD)){
                            components.add(c);
                            if(isTerminator(c)){
                                Module child = new Module("TU_F" + (forwardCount++));
                                child.setComponents(components);
                                child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                                child.setRoot(false);
                                root.addChild(child);
                                started = false;
                            }                         
                        } else{
                            components.add(c); //Make this a wildcard?
                        }
                    }
                }
                //Reverse Strand
                int reverseCount = 0;
                started = false;
                components = null;
                for(int i = (root.getComponents().size()-1); i >= 0; i--){
                    Component c = root.getComponents().get(i);
                    if(!started){
                        if(c.getOrientation().equals(Orientation.REVERSE)){
                            if(isPromoter(c)){
                                started = true;
                                components = new ArrayList<Component>();
                                components.add(c);
                            }
                            else{
                                //Throw an error?
                                continue;
                            }
                        } 
                        else {
                            //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                            continue;
                        }
                    }
                    //Started Forward strand
                    else{
                        if(c.getOrientation().equals(Orientation.REVERSE)){
                            components.add(c);
                            if(isTerminator(c)){
                                Module child = new Module("TU_R" + (reverseCount++));
                                child.setComponents(components);
                                child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                                child.setRoot(false);
                                root.addChild(child);
                                started = false;
                            }                         
                        } else{
                            components.add(c); //Make this a wildcard?
                        }
                    }
                }
                return root;
            default:
                return null;
        }
    }
    
    private static boolean isTerminator(Component c){
        switch(c.getRole()){
            case TERMINATOR:
                return true;
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default: 
                return false;
                
        }
    }
    
    private static boolean isPromoter(Component c){
        switch(c.getRole()){
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
                return true;
            case RBS:
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case TERMINATOR:
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return false;
            default: 
                return false;
                
        }
    }
    
    
    
}
