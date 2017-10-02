/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import hyness.stl.grammar.STLAbstractSyntaxTreeExtractor;
import hyness.stl.grammar.STLLexer;
import hyness.stl.grammar.STLParser;
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
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.LibraryComponent;
import org.cidarlab.phoenix.dom.Model;
import org.cidarlab.phoenix.dom.ModelPart;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.library.Library;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Controller {

    public static TreeNode getSTL(String filepath) {
        List<String> stlFileContent = Utilities.getFileContentAsStringList(filepath);
        String stlString = "";
        for (String str : stlFileContent) {
            stlString += str;
        }
        STLLexer lexer = new STLLexer(new ANTLRInputStream(stlString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        STLParser parser = new STLParser(tokens);
        ParserRuleContext t = parser.property();
        return new STLAbstractSyntaxTreeExtractor().visit(t);
    }

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
            loopAssignments = new ArrayList<>();
            loopAssignments.addAll(tempAssignments);
            //System.out.println("At the end of i  = " + i + " number of assignments are :: " + loopAssignments.size() );
        }
        return loopAssignments;
    }

    public static void assignPromCandidates(Module module, Library lib, SBOLDocument doc, Orientation o) {
        List<List<CandidateComponent>> tempAssignments;
        List<List<CandidateComponent>> loopAssignments = new ArrayList<>();
        for (int i = 0; i < module.getComponents().size(); i++) {
            tempAssignments = new ArrayList<>();
            Component c = module.getComponents().get(i);
            if (c.getOrientation().equals(getOppositeOrientation(o))) {
                //Assign test here for all Reverse strand weirdness.
                if (isPromoter(c)) {
                    for (List<CandidateComponent> lassignment : loopAssignments) {
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.addAll(lassignment);
                        assignment.add(new CandidateComponent(c, lib.getPromoterTest()));
                        tempAssignments.add(assignment);
                    }

                } else if (isRBS(c)) {
                    for (List<CandidateComponent> lassignment : loopAssignments) {
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.addAll(lassignment);
                        assignment.add(new CandidateComponent(c, lib.getRbsTest()));
                        tempAssignments.add(assignment);
                    }
                } else if (isCDS(c)) {
                    for (List<CandidateComponent> lassignment : loopAssignments) {
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.addAll(lassignment);
                        assignment.add(new CandidateComponent(c, lib.getCdsTest()));
                        tempAssignments.add(assignment);
                    }

                } else if (isTerminator(c)) {
                    for (List<CandidateComponent> lassignment : loopAssignments) {
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.addAll(lassignment);
                        assignment.add(new CandidateComponent(c, lib.getTerTest()));
                        tempAssignments.add(assignment);
                    }
                }
            } else {
                if (loopAssignments.isEmpty()) {
                    for (LibraryComponent lc : c.getCandidates()) {
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.add(new CandidateComponent(c, lc));
                        tempAssignments.add(assignment);
                    }
                } else {

                    for (List<CandidateComponent> lassignment : loopAssignments) {
                        //Do checks to see if you can assign this specific Component....
                        if (isCDS(c) || (c.getRole().equals(ComponentRole.TESTING))) {
                            List<CandidateComponent> assignment = new ArrayList<>();
                            assignment.addAll(lassignment);
                            assignment.add(new CandidateComponent(c, lib.getCdsTest()));
                            tempAssignments.add(assignment);
                        } else {
                            for (LibraryComponent lc : c.getCandidates()) {
                                List<CandidateComponent> assignment = new ArrayList<>();
                                assignment.addAll(lassignment);
                                assignment.add(new CandidateComponent(c, lc));
                                tempAssignments.add(assignment);
                            }
                        }
                    }
                }
            }
            loopAssignments = new ArrayList<>();
            loopAssignments.addAll(tempAssignments);

        }
        module.setAssignments(loopAssignments);
    }

    public static void assignCDSCandidates(Module module) {
        List<List<CandidateComponent>> assignments = new ArrayList<>();
        Component cds = module.getComponents().get(0);
        for (LibraryComponent c : cds.getCandidates()) {
            List<CandidateComponent> assignment = new ArrayList<>();
            assignment.add(new CandidateComponent(cds, c));
            assignments.add(assignment);
        }
        module.setAssignments(assignments);
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

    public static void assignTUCandidates(Module module, Library lib, SBOLDocument doc, Orientation o) {

        Module promModule = module.getChildren().get(0);
        Module cdsModule = module.getChildren().get(1);
        int indx = getCDSindex(promModule);
        Component c = promModule.getComponents().get(indx);
        boolean selfLoop = false;
        boolean rep = false;
        if (c.getRole().equals(ComponentRole.CDS_ACTIVATOR)) {
            Component prom = module.getComponents().get(0);
            if (prom.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                for (Interaction inter : c.getInteractions()) {
                    if (inter.getTo().equals(prom)) {
                        selfLoop = true;
                        rep = false;
                        break;
                    }
                }
            }
        }
        if (c.getRole().equals(ComponentRole.CDS_REPRESSOR)) {
            Component prom = module.getComponents().get(0);
            if (prom.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)) {
                for (Interaction inter : c.getInteractions()) {
                    if (inter.getTo().equals(prom)) {
                        selfLoop = true;
                        rep = true;
                        break;
                    }
                }
            }
        }
        List<List<CandidateComponent>> assignments = new ArrayList<>();
        List<CandidateComponent> cdscc = new ArrayList<>();
        for (List<CandidateComponent> lassignment : cdsModule.getAssignments()) {
            cdscc.add(lassignment.get(0));
        }
        for (List<CandidateComponent> lassignment : promModule.getAssignments()) {
            for (CandidateComponent cc : cdscc) {
                if (selfLoop) {
                    LibraryComponent cdslc = cc.getCandidate();
                    LibraryComponent promlc = lassignment.get(indx).getCandidate();
                    if (rep) {
                        if (represses(cdslc, promlc, doc)) {
                            List<CandidateComponent> assignment = new ArrayList<>();
                            assignment.addAll(lassignment);
                            assignment.set(indx, cc);
                            assignments.add(assignment);
                        }
                    } else {
                        if (activates(cdslc, promlc, doc)) {
                            List<CandidateComponent> assignment = new ArrayList<>();
                            assignment.addAll(lassignment);
                            assignment.set(indx, cc);
                            assignments.add(assignment);
                        }
                    }
                } else {
                    List<CandidateComponent> assignment = new ArrayList<>();
                    assignment.addAll(lassignment);
                    assignment.set(indx, cc);
                    assignments.add(assignment);
                }
            }
        }
        module.setAssignments(assignments);
    }

    public static void assignLeafCandidates(Module module, Library lib) {
        if (module.getRole().equals(ModuleRole.PROMOTER)) {
            for (Component component : module.getComponents()) {
                //if(!assigned.containsKey(component.getName())){
                if (component.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)) {
                    component.setCandidates(new ArrayList<>(lib.getConstitutivePromoters().values()));
                } else if (component.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)) {
                    component.setCandidates(new ArrayList<>(lib.getRepressiblePromoters().values()));
                } else if (component.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                    component.setCandidates(new ArrayList<>(lib.getActivatiblePromoters().values()));
                } else if (component.getRole().equals(ComponentRole.RBS)) {
                    component.setCandidates(new ArrayList<>(lib.getRbs().values()));
                } else if (component.getRole().equals(ComponentRole.TESTING)) {
                    component.setCandidates(new ArrayList<>(lib.getTesters().values()));
                } else if (component.getRole().equals(ComponentRole.TERMINATOR)) {
                    component.setCandidates(new ArrayList<>(lib.getTerminators().values()));
                }
                //assigned.put(component.getName(), component);
                //}
            }
        } else if (module.getRole().equals(ModuleRole.CDS)) {
            System.out.println("Assigning Candidates to CDS. Orientation : " + module.getOrientation() + ". Module Component Count : " + module.getComponents().size());
            System.out.println("Component Role :: " + module.getComponents().get(0).getRole());
            System.out.println("Component Name :: " + module.getComponents().get(0).getName());
            for (Component component : module.getComponents()) {
                //if(!assigned.containsKey(component.getName())){
                if (component.getRole().equals(ComponentRole.CDS_ACTIVATOR)) {
                    component.setCandidates(new ArrayList<>(lib.getActivatorCDS().values()));
                } else if (component.getRole().equals(ComponentRole.CDS_REPRESSOR)) {
                    component.setCandidates(new ArrayList<>(lib.getRepressorCDS().values()));
                } else if (component.getRole().equals(ComponentRole.CDS_FLUORESCENT) || component.getRole().equals(ComponentRole.CDS)) {
                    System.out.println("All output CDS count :: " + lib.getOutputCDS().size());
                    component.setCandidates(new ArrayList<>(lib.getOutputCDS().values()));
                } else {
                    System.out.println("Not supported yet.");
                }
                //assigned.put(component.getName(), component);
                //}
            }
        } else {
            for (Module child : module.getChildren()) {
                assignLeafCandidates(child, lib);
            }
        }
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
    
    public static List<Map<String,CandidateComponent>> assign(Module module, Library lib, SBOLDocument doc){
        assignLeafCandidates(module,lib);
        List<Module> tus = module.getChildren();
        for(Module tu:tus){
            for(Module leaf:tu.getChildren()){
                if(leaf.getRole().equals(ModuleRole.PROMOTER)){
                    assignPromCandidates(leaf,lib,doc,leaf.getOrientation());
                } else if(leaf.getRole().equals(ModuleRole.CDS)){
                    assignCDSCandidates(leaf);
                }
            }
        }
        for(Module tu:tus){
            assignTUCandidates(tu,lib,doc,tu.getOrientation());
        }
        return assignCircuitCandidates(module,lib,doc);
    }
    
    //<editor-fold desc="Decompose a Phoenix Module based on the mode">
    public static Module decompose(PhoenixMode mode, Module root) {
        boolean started = false;
        List<Component> components = null;
        switch (mode) {
            //<editor-fold desc="BioCPS decomposition">
            case BIOCPS:
                //Forward Strand
                started = false;
                int forInp = 0;
                int forMod = 0;
                int forOut = 0;

                ModuleRole role = ModuleRole.BIOCPS_INPUT;
                for (Component c : root.getComponents()) {
                    if (c.getOrientation().equals(Orientation.FORWARD)) {
                        if (!started) {
                            if (c.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                                role = ModuleRole.BIOCPS_INPUT;
                                started = true;
                                components = new ArrayList<>();
                                components.add(c);
                            } else if (isCDS(c)) {
                                if (isBioCPSModule(c)) {
                                    //Create a BioCPS Module
                                    role = ModuleRole.BIOCPS_MODULE;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                } else {
                                    //Create a BioCPS Output
                                    role = ModuleRole.BIOCPS_OUTPUT;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                }
                            } else {
                                //This means wrong grammar.
                                continue;
                            }

                        } else {
                            //In the middle of a module
                            components.add(c);
                            switch (role) {
                                case BIOCPS_INPUT:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module inp = new Module("In" + (forInp++));
                                        inp.setRole(role);
                                        inp.setComponents(components);
                                        inp.setRoot(false);
                                        root.addChild(inp);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_MODULE:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module mod = new Module("Mod" + (forMod++));
                                        mod.setRole(role);
                                        mod.setComponents(components);
                                        mod.setRoot(false);
                                        root.addChild(mod);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_OUTPUT:
                                    if (c.getRole().equals(ComponentRole.TERMINATOR)) {
                                        Module out = new Module("Out" + (forOut++));
                                        out.setRole(role);
                                        out.setComponents(components);
                                        out.setRoot(false);
                                        root.addChild(out);
                                        started = false;
                                    }
                                    break;
                                default:
                                    System.err.println("Unexpected module type");
                                    System.exit(-1);
                            }
                        }
                    } else {
                        //Do any of these make sense in the Forward TU units?
                        continue;
                    }
                }
                //Reverse Strand
                int revInp = 0;
                int revMod = 0;
                int revOut = 0;
                started = false;
                components = null;
                for (int i = (root.getComponents().size() - 1); i >= 0; i--) {
                    Component c = root.getComponents().get(i);
                    if (c.getOrientation().equals(Orientation.REVERSE)) {
                        if (!started) {
                            if (c.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                                role = ModuleRole.BIOCPS_INPUT;
                                started = true;
                                components = new ArrayList<>();
                                components.add(c);
                            } else if (isCDS(c)) {
                                if (isBioCPSModule(c)) {
                                    //Create a BioCPS Module
                                    role = ModuleRole.BIOCPS_MODULE;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                } else {
                                    //Create a BioCPS Output
                                    role = ModuleRole.BIOCPS_OUTPUT;
                                    started = true;
                                    components = new ArrayList<>();
                                    components.add(c);
                                }
                            } else {
                                //This means wrong grammar.
                                continue;
                            }

                        } else {
                            //In the middle of a module
                            components.add(c);
                            switch (role) {
                                case BIOCPS_INPUT:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module inp = new Module("In" + (forInp++));
                                        inp.setRole(role);
                                        inp.setComponents(components);
                                        inp.setRoot(false);
                                        root.addChild(inp);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_MODULE:
                                    if (c.getRole().equals(ComponentRole.RBS)) {
                                        Module mod = new Module("Mod" + (forMod++));
                                        mod.setRole(role);
                                        mod.setComponents(components);
                                        mod.setRoot(false);
                                        root.addChild(mod);
                                        started = false;
                                    }
                                    break;
                                case BIOCPS_OUTPUT:
                                    if (c.getRole().equals(ComponentRole.TERMINATOR)) {
                                        Module out = new Module("Out" + (forOut++));
                                        out.setRole(role);
                                        out.setComponents(components);
                                        out.setRoot(false);
                                        root.addChild(out);
                                        started = false;
                                    }
                                    break;
                                default:
                                    System.err.println("Unexpected module type");
                                    System.exit(-1);
                            }
                        }
                    } else {
                        //Do any of these make sense in the Forward TU units?
                        continue;
                    }
                }
                return root;
            //</editor-fold>
            case MM:
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
                                decomposeTU(child);
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
                                decomposeTU(child);
                                root.addChild(child);
                                started = false;
                            }
                        } else {
                            components.add(c); //Make this a wildcard?
                        }
                    }
                }
                return root;
            default:
                return null;
        }
    }

    private static void decomposeTU(Module module) {
        List<Component> components = new ArrayList<>();
        Module cds = null;
        for (Component c : module.getComponents()) {
            if (isCDS(c) && (c.getOrientation().equals(module.getOrientation()))) {
                cds = new Module("CDS");
                cds.addComponent(c);
                cds.setRole(ModuleRole.CDS);
                cds.setOrientation(module.getOrientation());

                Component test = new Component();
                test.setOrientation(c.getOrientation());
                test.setRole(ComponentRole.TESTING);
                test.setName(c.getName() + "_test");
                components.add(test);
            } else {
                components.add(c);
            }
        }
        Module prom = new Module("Prom");
        prom.setRole(ModuleRole.PROMOTER);
        prom.setComponents(components);
        prom.setOrientation(module.getOrientation());

        module.addChild(prom);
        module.addChild(cds);
    }
    //</editor-fold>

    //<editor-fold desc="Identify the Component Type">
    private static boolean isBioCPSModule(Component c) {
        switch (c.getRole()) {
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
                return true;
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
            case CDS:
            case TERMINATOR:
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
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

    private static boolean isCDS(Component c) {
        switch (c.getRole()) {
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
            case TESTING:
                return true;
            case TERMINATOR:
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
            case RBS:
            case ORIGIN:
            case VECTOR:
            case MARKER:
            case WILDCARD:
                return false;
            default:
                return false;

        }
    }

    private static boolean isRBS(Component c) {
        switch (c.getRole()) {
            case RBS:
                return true;
            case TERMINATOR:
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
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

    private static boolean isTerminator(Component c) {
        switch (c.getRole()) {
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

    private static boolean isPromoter(Component c) {
        switch (c.getRole()) {
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
    //</editor-fold>
    
    public static void assignLeafModels(PhoenixMode mode, Module root, String jobid, SBOLDocument doc, Map<String, CandidateComponent> assignment) {
        switch (mode) {
            case BIOCPS:
                break;
            case MM:
                assignPartLeafModels(root, jobid, doc, assignment);
                break;
            default:
                break;

        }
    }

    public static void assignPartLeafModels(Module root, String jobid, SBOLDocument doc, Map<String, CandidateComponent> assignment) {
        if (root.getRole().equals(ModuleRole.PROMOTER)) {

            try {
                Component prom = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(prom.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");
                System.out.println("Prom Model :: " + uri.toString());
                String fp = Utilities.getResultsFilepath() + jobid;
                Model model = new ModelPart(SynbiohubAdaptor.getModel(uri.toURL(), fp));
                root.setModel(model);
            } catch (MalformedURLException | URISyntaxException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (root.getRole().equals(ModuleRole.CDS)) {
            try {
                Component cds = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(cds.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");
                System.out.println("CDS Model :: " + uri.toString());
                String fp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
                Model model = new ModelPart(SynbiohubAdaptor.getModel(uri.toURL(), fp));
                root.setModel(model);
            } catch (MalformedURLException | URISyntaxException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            for (Module child : root.getChildren()) {
                assignPartLeafModels(child, jobid, doc, assignment);
            }
        } 
        
    }

    public static void composeModels(PhoenixMode mode, Module root, String jobid, Map<String, CandidateComponent> assignment) {
        switch (mode) {
            case BIOCPS:
                break;
            case MM:
                composePartModels(root, jobid, assignment);
                break;
            default:
                break;
        }
    }

    private static void composePartModels(Module root, String jobid, Map<String, CandidateComponent> assignment) {

        if (!isOverriden(root, jobid, assignment)) {
            List<org.sbml.jsbml.Model> modelList = new ArrayList<>();
            for (Module child : root.getChildren()) {
                composePartModels(child, jobid, assignment);
                modelList.add(child.getModel().getSbml().getModel());
            }
            if((!root.getRole().equals(ModuleRole.PROMOTER)) && (!root.getRole().equals(ModuleRole.CDS))) {
                Model composedModel = new ModelPart(SBMLAdaptor.composeModels(modelList));
                System.out.println("Composed Model at " + root.getRole());
                root.setModel(composedModel);
            }
        } else {
            try {
                String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
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
