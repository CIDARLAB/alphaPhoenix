/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.Model;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.failuremode.FailureModeGrammar;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Controller {

    //<editor-fold desc="Decompose a Phoenix Module based on the mode">
    public static Module decompose(Module root, Args.Decomposition decomposition) {
        boolean started = false;
        List<Component> components = null;

        //Forward Strand
        started = false;
        int forwardCount = 0;
        for (Component c : root.getComponents()) {
            if (!started) {
                if (c.getOrientation().equals(Orientation.FORWARD)) {
                    if (c.isPromoter()) {
                        started = true;
                        components = new ArrayList<>();
                        components.add(c);
                    } else {
                        //Throw an error?
                        continue;
                    }
                } else {
                    //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                    continue;
                }
            } //Started Forward strand
            else {
                if (c.getOrientation().equals(Orientation.FORWARD)) {
                    components.add(c);
                    if (c.isTerminator()) {
                        Module child = new Module("TU_F" + (forwardCount++));
                        child.setComponents(components);
                        child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                        child.setRoot(false);
                        child.setOrientation(Orientation.FORWARD);
                        decomposeTU(child, decomposition);
                        root.addChild(child);
                        started = false;
                    }
                } else {
                    components.add(c); //Make this a wildcard?
                }
            }
        }
        //Reverse Strand
        int reverseCount = 0;
        started = false;
        components = null;
        for (int i = (root.getComponents().size() - 1); i >= 0; i--) {
            Component c = root.getComponents().get(i);
            if (!started) {
                if (c.getOrientation().equals(Orientation.REVERSE)) {
                    if (c.isPromoter()) {
                        started = true;
                        components = new ArrayList<Component>();
                        components.add(c);
                    } else {
                        //Throw an error?
                        continue;
                    }
                } else {
                    //Forward orientation. Wouldn't really make it a TU unless it is a part of the TU.
                    continue;
                }
            } //Started reverse strand
            else {
                if (c.getOrientation().equals(Orientation.REVERSE)) {
                    components.add(c);
                    if (c.isTerminator()) {
                        Module child = new Module("TU_R" + (reverseCount++));
                        child.setComponents(components);
                        child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                        child.setRoot(false);
                        child.setOrientation(Orientation.REVERSE);
                        decomposeTU(child, decomposition);
                        root.addChild(child);
                        started = false;
                    }
                } else {
                    components.add(c); //Make this a wildcard?
                }
            }
        }
        //root.setIOCNames();
        
        //System.out.println("IOC Names for current Module :: ");
        //root.printIOCNames();
        
        return root;

    }

    private static void decomposeTU(Module module, Args.Decomposition decomposition) {
        
        switch(decomposition){
            case PR_C_T:
                decomposePR_C_T(module);
                break;
            case P_RC_T:
                decomposeP_RC_T(module);
                break;
        }
    }
    
    private static void decomposePR_C_T(Module module){
        
        Module pr = new Module("PR");
        Module cds = new Module("C");
        Module ter = new Module("T");
        
        boolean prCreated = false;
        boolean cdsCreated = false;
        List<Component> prComponents = new ArrayList<>();
        List<Component> cdsComponents = new ArrayList<>();
        List<Component> terComponents = new ArrayList<>();
        
        for(Component c:module.getComponents()){
            if(!prCreated){
                if(c.getOrientation().equals(module.getOrientation())){
                    prComponents.add(c);
                } else {
                    Component wc = new Component();
                    wc.setRole(ComponentRole.WILDCARD);
                    wc.setName(c.getName() + "_Test");
                    wc.setOrientation(c.getOrientation());
                    prComponents.add(wc);
                }
                if (c.isRBS()) {
                    prCreated = true;
                }
            } else {
                if(c.isTerminator()){
                    terComponents.add(c);
                    cdsCreated = true;
                }
                if (!cdsCreated) {
                    if (c.getOrientation().equals(module.getOrientation())) {
                        cdsComponents.add(c);
                    } else {
                        Component wc = new Component();
                        wc.setRole(ComponentRole.WILDCARD);
                        wc.setName(c.getName() + "_Test");
                        wc.setOrientation(c.getOrientation());
                        cdsComponents.add(wc);
                    }
                    
                }
            }
        }
        pr.setOrientation(module.getOrientation());
        pr.setRole(ModuleRole.PROMOTER_RBS);
        pr.setComponents(prComponents);
        
        cds.setOrientation(module.getOrientation());
        cds.setRole(ModuleRole.CDS);
        cds.setComponents(cdsComponents);
        
        ter.setOrientation(module.getOrientation());
        ter.setRole(ModuleRole.TERMINATOR);
        ter.setComponents(terComponents);
        
        module.addChild(pr);
        module.addChild(cds);
        module.addChild(ter);
        
        
    }
    
    private static void decomposeP_RC_T(Module module){
        
    }

    //</editor-fold>
    
    
    private static boolean isOverriden(Module root, String jobid, Map<String, CandidateComponent> assignment) {
        String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
        String ofile = jobfp + "override.json";
        if (!Utilities.validFilepath(ofile)) {
            return false;
        } else {
            String filecontent = Utilities.getFileContentAsString(ofile);
            JSONObject json = new JSONObject(filecontent);
            if (json.has(getAssignmentComponentString(root, assignment))) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static String getAssignmentComponentString(Module root, Map<String, CandidateComponent> assignment) {
        String compString = "";
        for (Component c : root.getComponents()) {
            compString += (assignment.get(c.getName()).getCandidate().getDisplayId() + ";");
        }
        return compString;
    }

    

}
