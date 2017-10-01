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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
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
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Controller {

    public static TreeNode getSTL(String filepath){
        List<String> stlFileContent = Utilities.getFileContentAsStringList(filepath);
        String stlString = "";
        for(String str:stlFileContent){
            stlString += str;
        }
        STLLexer lexer = new STLLexer(new ANTLRInputStream(stlString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        STLParser parser = new STLParser(tokens);
        ParserRuleContext t = parser.property();
        return new STLAbstractSyntaxTreeExtractor().visit(t);
    }
    
    private static Orientation getOppositeOrientation(Orientation o){
        switch(o){
            case FORWARD:
                return Orientation.REVERSE;
            case REVERSE:
                return Orientation.FORWARD;
            default:
                return null;
        }
    }
    
    public static void getTUCandidates(Module module, Library lib, SBOLDocument doc, Orientation o){
        List<List<CandidateComponent>> assignments = new ArrayList<>();
        int revCount = 0;
        int totCount = 1;
        for(Component c:module.getComponents()){
            if(c.getOrientation().equals(getOppositeOrientation(o))){
                revCount++;
            } else {
                totCount *= c.getCandidates().size();
            }
        }
        List<List<CandidateComponent>> tempAssignments = new ArrayList<>();
        List<List<CandidateComponent>> loopAssignments = new ArrayList<>();
        for(int i=0;i<module.getComponents().size();i++){
            Component c = module.getComponents().get(i);
            if(c.getOrientation().equals(getOppositeOrientation(o))){
                //Assign test here.....
                if(isPromoter(c)){
                    List<CandidateComponent> assignment = new ArrayList<>();
                    assignment.add(new CandidateComponent(c,lib.getPromoterTest()));
                    tempAssignments.add(assignment);
                } else if(isRBS(c)){
                    List<CandidateComponent> assignment = new ArrayList<>();
                    assignment.add(new CandidateComponent(c,lib.getRbsTest()));
                    tempAssignments.add(assignment);
                } else if(isCDS(c)){
                    List<CandidateComponent> assignment = new ArrayList<>();
                    assignment.add(new CandidateComponent(c,lib.getRbsTest()));
                    tempAssignments.add(assignment);
                } else if(isTerminator(c)){
                    List<CandidateComponent> assignment = new ArrayList<>();
                    assignment.add(new CandidateComponent(c,lib.getTerTest()));
                    tempAssignments.add(assignment);
                }
            } else {
                if(loopAssignments.isEmpty()){
                    for(LibraryComponent lc:c.getCandidates()){
                        List<CandidateComponent> assignment = new ArrayList<>();
                        assignment.add(new CandidateComponent(c,lc));
                        tempAssignments.add(assignment);
                    }
                } else {
                    boolean selfLoop = false;
                    boolean rep = false;
                    if(c.getRole().equals(ComponentRole.CDS_ACTIVATOR)){
                        Component prom = module.getComponents().get(0);
                        if(prom.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)){
                            for(Interaction inter:c.getInteractions()){
                                if(inter.getTo().equals(prom)){
                                    selfLoop = true;
                                    rep = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(c.getRole().equals(ComponentRole.CDS_REPRESSOR)){
                        Component prom = module.getComponents().get(0);
                        if(prom.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)){
                            for(Interaction inter:c.getInteractions()){
                                if(inter.getTo().equals(prom)){
                                    selfLoop = true;
                                    rep = true;
                                    break;
                                }
                            }
                        }
                    }
                    for(List<CandidateComponent> lassignment:loopAssignments){
                        //Do checks to see if you can assign this specific Component....
                        for (LibraryComponent lc : c.getCandidates()) {
                            LibraryComponent promlc = lassignment.get(0).getCandidate();
                            if (selfLoop) {
                                if(rep){
                                    if(represses(lc,promlc,doc)){
                                        List<CandidateComponent> assignment = new ArrayList<>();
                                        assignment.addAll(lassignment);
                                        assignment.add(new CandidateComponent(c, lc));
                                        tempAssignments.add(assignment);
                                    }
                                } else {
                                    if(activates(lc,promlc,doc)){
                                        List<CandidateComponent> assignment = new ArrayList<>();
                                        assignment.addAll(lassignment);
                                        assignment.add(new CandidateComponent(c, lc));
                                        tempAssignments.add(assignment);
                                    }
                                }
                            } else {
                                List<CandidateComponent> assignment = new ArrayList<>();
                                assignment.addAll(lassignment);
                                assignment.add(new CandidateComponent(c,lc));
                                tempAssignments.add(assignment);
                            }
                        }
                    }
                }
            }
            loopAssignments = new ArrayList<>(tempAssignments);
            tempAssignments = new ArrayList<>();
        }
    }
    
    
    public static void assignLeafCandidates(Module module, Library lib, Map<String,Component> assigned){
        if(module.getRole().equals(ModuleRole.PROMOTER)){
            for(Component component:module.getComponents()){
                if(!assigned.containsKey(component.getName())){
                    if(component.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)){
                        component.setCandidates(new ArrayList<>(lib.getConstitutivePromoters().values()));
                    } else if(component.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)){
                        component.setCandidates(new ArrayList<>(lib.getRepressiblePromoters().values()));
                    } else if(component.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)){
                        component.setCandidates(new ArrayList<>(lib.getActivatiblePromoters().values()));
                    } else if(component.getRole().equals(ComponentRole.RBS)){
                        component.setCandidates(new ArrayList<>(lib.getRbs().values()));
                    } else if(component.getRole().equals(ComponentRole.TESTING)){
                        component.setCandidates(new ArrayList<>(lib.getTesters().values()));
                    } else if(component.getRole().equals(ComponentRole.TERMINATOR)){
                        component.setCandidates(new ArrayList<>(lib.getTerminators().values()));
                    }
                    assigned.put(component.getName(), component);
                }
            }
        } else if(module.getRole().equals(ModuleRole.CDS)){
            for(Component component:module.getComponents()){
                if(!assigned.containsKey(component.getName())){
                    if(component.getRole().equals(ComponentRole.CDS_ACTIVATOR)){
                        component.setCandidates(new ArrayList<>(lib.getActivatorCDS().values()));
                    } else if(component.getRole().equals(ComponentRole.CDS_REPRESSOR)){
                        component.setCandidates(new ArrayList<>(lib.getRepressorCDS().values()));
                    } else if(component.getRole().equals(ComponentRole.CDS_FLUORESCENT)){
                        component.setCandidates(new ArrayList<>(lib.getOutputCDS().values()));
                    } else {
                        System.out.println("Not supported yet.");
                    }
                    assigned.put(component.getName(), component);
                }
            }
        }
        else {
            for(Module child:module.getChildren()){
                assignLeafCandidates(child,lib, assigned);
            }
        }
    }
    
    private static boolean shareProtein(ModuleDefinition cds, ModuleDefinition prom){
        URI cdsuri = null;
        URI promuri = null;
        for(FunctionalComponent f:cds.getFunctionalComponents()){
            if(Library.getRole(f.getDefinition()).equals(ComponentRole.PROTEIN)){
                cdsuri = f.getDefinitionURI();
            }
        }
        for(FunctionalComponent f:prom.getFunctionalComponents()){
            if(Library.getRole(f.getDefinition()).equals(ComponentRole.PROTEIN)){
                promuri = f.getDefinitionURI();
            }
        }
        if(cdsuri == null || promuri == null){
            return false;
        }
        if(cdsuri.equals(promuri)){
            return true;
        }
        
        return false;
    }
    
    private static boolean activates(LibraryComponent cds, LibraryComponent prom, SBOLDocument doc){
        for(URI pmduri: prom.getModuleDefinitions()){
            ModuleDefinition mdprom = doc.getModuleDefinition(pmduri);
            if(isActivation(mdprom)){
                for(URI cmduri:cds.getModuleDefinitions()){
                    ModuleDefinition mdcds = doc.getModuleDefinition(cmduri);
                    if(isProduction(mdcds)){
                        if(shareProtein(mdcds,mdprom)){
                            return true;
                        }
                    }
                }
            }
        }  
        return false;
    }
    
    private static boolean represses(LibraryComponent cds, LibraryComponent prom, SBOLDocument doc){
        for(URI pmduri: prom.getModuleDefinitions()){
            ModuleDefinition mdprom = doc.getModuleDefinition(pmduri);
            if(isRepression(mdprom)){
                for(URI cmduri:cds.getModuleDefinitions()){
                    ModuleDefinition mdcds = doc.getModuleDefinition(cmduri);
                    if(isProduction(mdcds)){
                        if(shareProtein(mdcds,mdprom)){
                            return true;
                        }
                    }
                }
            }
        }  
        return false;
    }
    
    private static boolean isProduction(ModuleDefinition md){
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String productionSBO = sbo + "SBO:0000589";;
            URI productionURI = new URI(productionSBO);
            for(org.sbolstandard.core2.Interaction inter:md.getInteractions()){
                for(URI type:inter.getTypes()){
                    if(type.equals(productionURI)){
                        return true;
                    }
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static boolean isRepression(ModuleDefinition md){
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String inhibitionSO = sbo + "SBO:0000169";
            URI inhibitionURI = new URI(inhibitionSO);
            for(org.sbolstandard.core2.Interaction inter:md.getInteractions()){
                for(URI type:inter.getTypes()){
                    if(type.equals(inhibitionURI)){
                        return true;
                    }
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static boolean isActivation(ModuleDefinition md){
        try {
            String sbo = "http://identifiers.org/biomodels.sbo/";
            String stimulationSO = sbo + "SBO:0000170";
            URI stimulationURI = new URI(stimulationSO);
            for(org.sbolstandard.core2.Interaction inter:md.getInteractions()){
                for(URI type:inter.getTypes()){
                    if(type.equals(stimulationURI)){
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
    public static Module decompose(PhoenixMode mode, Module root) {
        boolean started = false;
        List<Component> components = null;
        switch (mode) {
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
                            //Reverse orientation. Wouldn't really make it a TU unless it is a part of the TU.
                            continue;
                        }
                    } //Started Forward strand
                    else {
                        if (c.getOrientation().equals(Orientation.REVERSE)) {
                            components.add(c);
                            if (isTerminator(c)) {
                                Module child = new Module("TU_R" + (reverseCount++));
                                child.setComponents(components);
                                child.setRole(ModuleRole.TRANSCRIPTIONAL_UNIT);
                                child.setRoot(false);
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
            if (isCDS(c)) {
                cds = new Module("CDS");
                cds.addComponent(c);
                cds.setRole(ModuleRole.CDS);

                Component test = new Component();
                test.setOrientation(c.getOrientation());
                test.setRole(ComponentRole.TESTING);
                test.setName(c.getName()+"_test");
                components.add(test);
            } else {
                components.add(c);
            }
        }
        Module prom = new Module("Prom");
        prom.setRole(ModuleRole.PROMOTER);
        prom.setComponents(components);

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
                return true;
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

    public static void composeModels(PhoenixMode mode, Module root) {
        switch (mode) {
            case BIOCPS:
                break;
            case MM:
                composePartModels(root);      
                break;
            default:
                break;
        }
    }
    
    private static void composePartModels(Module root){
        
        if(!root.getModel().isOverriden()){
            List<org.sbml.jsbml.Model> modelList = new ArrayList<>();
            for (Module child : root.getChildren()) {
                composePartModels(child);
                modelList.add(child.getModel().getSbml().getModel());
            }
            Model composedModel = new ModelPart(SBMLAdaptor.composeModels(modelList));
            root.setModel(composedModel);
        } 
    }

}
