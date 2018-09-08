/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class PigeonAdaptor {
    
    public static String generatePigeonString(Module module){
        String pigeonString = "";
        for(Component c:module.getComponents()){
            pigeonString += getPigeonFeatureString(c.getRole(), c.getOrientation());
        }
        return pigeonString;
    }
    
    public static String getPigeonFeatureString(ComponentRole role, Orientation or){
        String featureString = "";
        if(or.equals(Orientation.REVERSE))
            featureString += "<";
        switch(role){
            case PROMOTER_INDUCIBLE:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_ACTIVATABLE:
            case PROMOTER_CONSTITUTIVE:
                featureString += "p ";
                break;
            case RBS:
                featureString += "r ";
                break;
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            //case CDS_REPRESSIBLE_REPRESSOR:
            //case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
                featureString += "c ";
                break;
            case TERMINATOR:
                featureString += "t ";
                break;
        }
        return featureString;
    }
    
}
