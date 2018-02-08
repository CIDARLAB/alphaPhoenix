/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class DnaPlotlibAdaptor {
    
    public static String getUniqueString(List<Component> components){
        
        String str = "";
        for(int k=0;k<components.size();k++){
            Component c = components.get(k);
            str += getOrientationCharacter(c.getOrientation());
            str += getRoleCharacter(c.getRole());
            str += k;
            if (Controller.isCDS(c)) {
                str += ":";
                List<Integer> indices = new ArrayList<Integer>();
                List<String> intStrings = new ArrayList<String>();
                for (Interaction i : c.getInteractions()) {
                    if(!i.getType().equals(InteractionType.INDUCES) && !i.getType().equals(InteractionType.REPRESSES)){
                        continue;
                    }
                    
                    int indx = components.indexOf(i.getTo());
                    if(intStrings.isEmpty()){
                        indices.add(indx);
                        intStrings.add(getInteractionString(i.getType())+"p"+indx);
                    } else {
                        int insertAt = indices.size();
                        for (int j = 0; j < indices.size(); j++) {
                            if(indx < indices.get(j)){
                                insertAt = j;
                                break;
                            }
                        }
                        indices.add(insertAt,indx);
                        intStrings.add(insertAt,getInteractionString(i.getType())+"p"+indx);
                    }
                    
                }
                
                for(String intString:intStrings){
                    str += (intString + ":");
                }
            }
            
            
            str += ";";
        }
        
        return str;
    
    }
    
    private static String getInteractionString(InteractionType type){
        switch(type){
            case INDUCES:
                return "act:";
            case REPRESSES:
                return "rep:";
            default: 
                System.out.println("Probably throw an error here?");
                return "";
        }
    }
    
    private static char getOrientationCharacter(Orientation o){
        switch(o){
            case FORWARD:
                return '+';
            case REVERSE:
                return '-';
            default: 
                return '+';
        } 
    }
    
    private static char getRoleCharacter(ComponentRole role){
        switch(role){
            case PROTEIN:
                return 'w';
            case INDUCER:
                return 'w';
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
                return 'p';
            case RBS:
                return 'r';
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
                return 'c';
            case TERMINATOR:
                return 't';
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return 'w';
            default:
                return 'w';
        }
    }
    
    
}
