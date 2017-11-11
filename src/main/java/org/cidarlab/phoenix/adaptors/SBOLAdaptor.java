/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.OrientationType;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;
import org.sbolstandard.core2.SequenceAnnotation;

/**
 *
 * @author prash
 */
public class SBOLAdaptor {
    
    public static void writeCircuitSBOL(Module m, Map<String,CandidateComponent> assignment, String filepath, String jobid, int assignmentindex){
        try {
            SBOLDocument sbol = new SBOLDocument();
            String so = "http://identifiers.org/so/";
            String engineeredRegionSO = so + "SO:0000804";
            String urlprefix = "https://phoenix.org/results/" + jobid + "/";
            String displayid = "circuit" + assignmentindex;
            String version = "0.1";
            URI engineeredRegionURI = new URI(engineeredRegionSO);
            ComponentDefinition cd = sbol.createComponentDefinition(urlprefix, displayid, version, engineeredRegionURI);
            int i=0;
            for(Component c:m.getComponents()){
                
                org.sbolstandard.core2.Component sbolcomponent = cd.createComponent(c.getName(), AccessType.PRIVATE, assignment.get(c.getName()).getCandidate().getComponentDefintion());
                String saId = "SequenceAnnotation" + i;
                String locationId = "Location" + i;
                if(c.getOrientation().equals(Orientation.FORWARD)){
                    SequenceAnnotation sa = cd.createSequenceAnnotation(saId, locationId, OrientationType.INLINE);
                    sa.setComponent(sbolcomponent.getIdentity());
                } else {
                    SequenceAnnotation sa = cd.createSequenceAnnotation(saId, locationId, OrientationType.REVERSECOMPLEMENT);
                    sa.setComponent(sbolcomponent.getIdentity());
                }
                i++;
            }
            
            String sbolfp = filepath + "sbol.xml";
            SBOLWriter.write(sbol, sbolfp);
            
        } catch (URISyntaxException | SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(SBOLAdaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
