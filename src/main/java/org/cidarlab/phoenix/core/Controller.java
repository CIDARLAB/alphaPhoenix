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
import org.cidarlab.phoenix.dom.ModelPart;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.dom.library.Library;
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


    private static Orientation getOppositeOrientation(Orientation o) {
        switch (o) {
            case FORWARD:
                return Orientation.REVERSE;
            case REVERSE:
                return Orientation.FORWARD;
            default:
                return null;
        }
    }

    public static List<Map<String, CandidateComponent>> assignCircuitCandidates(Module module, Library lib, SBOLDocument doc) {
        List<Map<String, CandidateComponent>> tempAssignments;
        List<Map<String, CandidateComponent>> loopAssignments = new ArrayList<>();
        for (int i = 0; i < module.getComponents().size(); i++) {
            tempAssignments = new ArrayList<>();
            Component c = module.getComponents().get(i);

            if (loopAssignments.isEmpty()) {
                for (LibraryComponent lc : c.getCandidates()) {
                    Map<String, CandidateComponent> assignment = new HashMap<>();
                    assignment.put(c.getName(), new CandidateComponent(c, lc));
                    tempAssignments.add(assignment);
                }
            } else {

                for (Map<String, CandidateComponent> lassignment : loopAssignments) {
                    //Do checks to see if you can assign this specific Component....
                    if (isCDS(c)) {
                        if (c.getInteractions().isEmpty()) {
                            for (LibraryComponent lc : c.getCandidates()) {
                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                assignment.putAll(lassignment);
                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                tempAssignments.add(assignment);
                            }
                        } else {
                            for (Interaction inter : c.getInteractions()) {
                                Component prom = inter.getTo();
                                if (lassignment.containsKey(prom.getName())) {
                                    CandidateComponent promcc = lassignment.get(prom.getName());
                                    for (LibraryComponent lc : c.getCandidates()) {//This is CDS
                                        if (inter.getType().equals(InteractionType.INDUCES)) {
                                            if (activates(lc, promcc.getCandidate(), doc)) {
                                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                                assignment.putAll(lassignment);
                                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                                tempAssignments.add(assignment);
                                            }
                                        } else if (inter.getType().equals(InteractionType.REPRESSES)) {
                                            if (represses(lc, promcc.getCandidate(), doc)) {
                                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                                assignment.putAll(lassignment);
                                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                                tempAssignments.add(assignment);
                                            }
                                        } else {
                                            System.err.println("Not supported yet");
                                        }
                                    }
                                } else {
                                    for (LibraryComponent lc : c.getCandidates()) {
                                        CandidateComponent cc = new CandidateComponent(c, lc);
                                        if (!lassignment.values().contains(cc)) {
                                            Map<String, CandidateComponent> assignment = new HashMap<>();
                                            assignment.putAll(lassignment);
                                            assignment.put(c.getName(), cc);
                                            tempAssignments.add(assignment);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (isPromoter(c)) {
                        if (c.getInteractions().isEmpty()) {
                            for (LibraryComponent lc : c.getCandidates()) {
                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                assignment.putAll(lassignment);
                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                tempAssignments.add(assignment);
                            }
                        } else {
                            for (Interaction inter : c.getInteractions()) {
                                Component cds = inter.getFrom();
                                if (lassignment.containsKey(cds.getName())) {
                                    CandidateComponent cdscc = lassignment.get(cds.getName());
                                    for (LibraryComponent lc : c.getCandidates()) {//This is CDS
                                        if (inter.getType().equals(InteractionType.INDUCES)) {
                                            if (activates(cdscc.getCandidate(), lc, doc)) {
                                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                                assignment.putAll(lassignment);
                                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                                tempAssignments.add(assignment);
                                            }
                                        } else if (inter.getType().equals(InteractionType.REPRESSES)) {
                                            if (represses(cdscc.getCandidate(), lc, doc)) {
                                                Map<String, CandidateComponent> assignment = new HashMap<>();
                                                assignment.putAll(lassignment);
                                                assignment.put(c.getName(), new CandidateComponent(c, lc));
                                                tempAssignments.add(assignment);
                                            }
                                        } else {
                                            System.err.println("Not supported yet");
                                        }
                                    }
                                } else {
                                    for (LibraryComponent lc : c.getCandidates()) {
                                        CandidateComponent cc = new CandidateComponent(c, lc);
                                        if (!lassignment.values().contains(cc)) {
                                            Map<String, CandidateComponent> assignment = new HashMap<>();
                                            assignment.putAll(lassignment);
                                            assignment.put(c.getName(), cc);
                                            tempAssignments.add(assignment);
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        if (!lassignment.containsKey(c.getName())) {
                            for (LibraryComponent lc : c.getCandidates()) {
                                CandidateComponent cc = new CandidateComponent(c, lc);
                                if (!lassignment.values().contains(cc)) {
                                    Map<String, CandidateComponent> assignment = new HashMap<>();
                                    assignment.putAll(lassignment);
                                    assignment.put(c.getName(), cc);
                                    tempAssignments.add(assignment);
                                }
                            }
                        }
                    }
                }
            }
            
            if(!tempAssignments.isEmpty()){
                loopAssignments = new ArrayList<>();
                loopAssignments.addAll(tempAssignments);
            }
            //System.out.println("At the end of i  = " + i + " number of assignments are :: " + loopAssignments.size() );
        }
        return loopAssignments;
    }

    private static int getCDSindex(Module module) {
        for (int i = 0; i < module.getComponents().size(); i++) {
            Component c = module.getComponents().get(i);
            if (isCDS(c) && (c.getOrientation().equals(module.getOrientation()))) {
                return i;
            }
        }
        return -1;
    }

    
    private static boolean shareProtein(ModuleDefinition cds, ModuleDefinition prom) {
        URI cdsuri = null;
        URI promuri = null;
        for (FunctionalComponent f : cds.getFunctionalComponents()) {
            if (Library.getRole(f.getDefinition()).equals(ComponentRole.PROTEIN)) {
                cdsuri = f.getDefinitionURI();
            }
        }
        for (FunctionalComponent f : prom.getFunctionalComponents()) {
            if (Library.getRole(f.getDefinition()).equals(ComponentRole.PROTEIN)) {
                promuri = f.getDefinitionURI();
            }
        }
        if (cdsuri == null || promuri == null) {
            return false;
        }
        if (cdsuri.equals(promuri)) {
            return true;
        }

        return false;
    }

    private static boolean activates(LibraryComponent cds, LibraryComponent prom, SBOLDocument doc) {
        for (URI pmduri : prom.getModuleDefinitions()) {
            ModuleDefinition mdprom = doc.getModuleDefinition(pmduri);
            if (isActivation(mdprom)) {
                for (URI cmduri : cds.getModuleDefinitions()) {
                    ModuleDefinition mdcds = doc.getModuleDefinition(cmduri);
                    if (isProduction(mdcds)) {
                        if (shareProtein(mdcds, mdprom)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean represses(LibraryComponent cds, LibraryComponent prom, SBOLDocument doc) {
        for (URI pmduri : prom.getModuleDefinitions()) {
            ModuleDefinition mdprom = doc.getModuleDefinition(pmduri);
            if (isRepression(mdprom)) {
                for (URI cmduri : cds.getModuleDefinitions()) {
                    ModuleDefinition mdcds = doc.getModuleDefinition(cmduri);
                    if (isProduction(mdcds)) {
                        if (shareProtein(mdcds, mdprom)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isProduction(ModuleDefinition md) {
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String productionSBO = sbo + "SBO:0000589";;
            URI productionURI = new URI(productionSBO);
            for (org.sbolstandard.core2.Interaction inter : md.getInteractions()) {
                for (URI type : inter.getTypes()) {
                    if (type.equals(productionURI)) {
                        return true;
                    }
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static boolean isRepression(ModuleDefinition md) {
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String inhibitionSO = sbo + "SBO:0000169";
            URI inhibitionURI = new URI(inhibitionSO);
            for (org.sbolstandard.core2.Interaction inter : md.getInteractions()) {
                for (URI type : inter.getTypes()) {
                    if (type.equals(inhibitionURI)) {
                        return true;
                    }
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static boolean isActivation(ModuleDefinition md) {
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String stimulationSO = sbo + "SBO:0000170";
            URI stimulationURI = new URI(stimulationSO);
            for (org.sbolstandard.core2.Interaction inter : md.getInteractions()) {
                for (URI type : inter.getTypes()) {
                    if (type.equals(stimulationURI)) {
                        return true;
                    }
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
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
                    if (isPromoter(c)) {
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
                    if (isTerminator(c)) {
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
                    if (isPromoter(c)) {
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
                    if (isTerminator(c)) {
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
                if (isRBS(c)) {
                    prCreated = true;
                }
            } else {
                if(isTerminator(c)){
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

    //<editor-fold desc="Identify the Component Type">
    
    public static boolean isCDS(Component c) {
        switch (c.getRole()) {
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
            case TESTING:
            case GENERIC_CDS:
                return true;
            default:
                return false;

        }
    }

    private static boolean isRBS(Component c) {
        switch (c.getRole()) {
            case RBS:
            case GENERIC_RBS:
                return true;
            default:
                return false;

        }
    }

    private static boolean isTerminator(Component c) {
        switch (c.getRole()) {
            case TERMINATOR:
            case GENERIC_TERMINATOR:
                return true;
            default:
                return false;

        }
    }

    public static boolean isPromoter(Component c) {
        switch (c.getRole()) {
            case PROMOTER_INDUCIBLE:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_ACTIVATABLE:
            case PROMOTER_CONSTITUTIVE:
            case GENERIC_PROMOTER:
                return true;
            default:
                return false;

        }
    }
    //</editor-fold>
    
    public static void assignLeafModels(Module root, String jobfp, SBOLDocument doc, Map<String, CandidateComponent> assignment) {
        
        assignPartLeafModels(root, jobfp, doc, assignment);
        renameSpecies(root);

    }
    
    public static void renameSpecies(Module root){
        for(Module tu:root.getChildren()){
            Module promModule = tu.getChildren().get(0);
            Module cdsModule = tu.getChildren().get(1);
            Component prom = promModule.getComponents().get(0);
            Component cds = cdsModule.getComponents().get(0);
            if(prom.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)){
                SBMLAdaptor.renameSpecies(promModule.getModel().getSbml(), "out", cds.getIOCname());
            } else {
                if(promModule.getModel().getSbml().getModel().containsSpecies("ind")){
                    SBMLAdaptor.renameSpecies(promModule.getModel().getSbml(), "ind", "ind_" + prom.getIOCname());
                }
                SBMLAdaptor.renameSpecies(promModule.getModel().getSbml(), "conn", prom.getIOCname());
                SBMLAdaptor.renameSpecies(promModule.getModel().getSbml(), "out", cds.getIOCname());
            
            }
            if(cds.getInteractions().isEmpty()){
                //Output CDS
                SBMLAdaptor.renameSpecies(cdsModule.getModel().getSbml(), "out", cds.getIOCname());
                } else {
                //Connector CDS
                SBMLAdaptor.renameSpecies(cdsModule.getModel().getSbml(), "conn", cds.getIOCname());
            }
        }
    }
    
    public static void assignPartLeafModels(Module root, String jobfp, SBOLDocument doc, Map<String, CandidateComponent> assignment) {
        if (root.getRole().equals(ModuleRole.PROMOTER)) {

            try {
                Component prom = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(prom.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");
                SBMLDocument sbml = SynbiohubAdaptor.getModel(uri.toURL(), jobfp);
                Model model = new ModelPart(sbml);
                root.setModel(model);
            } catch (MalformedURLException | URISyntaxException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (root.getRole().equals(ModuleRole.CDS)) {
            try {
                Component cds = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(cds.getName());
                System.out.println("DEBUGGING CDS :: "  + cds.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");
               
                SBMLDocument sbml = SynbiohubAdaptor.getModel(uri.toURL(), jobfp);
                Model model = new ModelPart(sbml);
                root.setModel(model);
            } catch (MalformedURLException | URISyntaxException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            for (Module child : root.getChildren()) {
                assignPartLeafModels(child, jobfp, doc, assignment);
            }
        } 
        
    }

    public static void composeModels(Module root, String jobfp, Map<String, CandidateComponent> assignment) {
        composePartModels(root, jobfp, assignment);
    }

    private static void composePartModels(Module root, String jobfp, Map<String, CandidateComponent> assignment) {

        if (!isOverriden(root, jobfp, assignment)) {
            if((!root.getRole().equals(ModuleRole.PROMOTER)) && (!root.getRole().equals(ModuleRole.CDS))) {
                List<org.sbml.jsbml.Model> modelList = new ArrayList<>();
                for (Module child : root.getChildren()) {
                    composePartModels(child, jobfp, assignment);
                    modelList.add(child.getModel().getSbml().getModel());
                }
                Model composedModel = new ModelPart(SBMLAdaptor.composeModels(modelList));
                root.setModel(composedModel);
            }
        } else {
            try {
                String ofile = jobfp + "override.json";
                String filecontent = Utilities.getFileContentAsString(ofile);
                JSONObject json = new JSONObject(filecontent);
                String sbmlfp = jobfp + json.getString(getAssignmentComponentString(root, assignment));
                SBMLDocument doc = SBMLReader.read(new File(sbmlfp));
                Model overridenModel = new ModelPart(doc);
                root.setModel(overridenModel);
            } catch (XMLStreamException | IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
