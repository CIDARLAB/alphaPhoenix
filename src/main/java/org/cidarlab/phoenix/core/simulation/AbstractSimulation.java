/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.simulation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Model;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.cidarlab.phoenix.utils.Args;
import org.sbml.jsbml.SBMLDocument;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public abstract class AbstractSimulation {
    
    public static String getAssignmentString(Module module, Map<String, CandidateComponent> assignment){
        String s = "";
        for (Component c : module.getComponents()) {
            s += (assignment.get(c.getName()).getCandidate().getName() + ";");
        }
        return s;
    }
    
    public static void printAssignment(Module module, Map<String, CandidateComponent> assignment) {

        for (Component c : module.getComponents()) {
            System.out.print(assignment.get(c.getName()).getCandidate().getName() + ";");
        }
        System.out.println("");
    }
    
    
    public static Map<URI,SBMLDocument> downloadAllModels(Library library, String fp) throws URISyntaxException, MalformedURLException{
        Map<URI,SBMLDocument> modelmap = new HashMap<>();
        SBOLDocument sbol = library.getSbol();
        for(URI u:library.getAllLibraryComponents().keySet()){
            LibraryComponent lc = library.getAllLibraryComponents().get(u);
            for(URI mduri:lc.getModuleDefinitions()){
                ModuleDefinition md = sbol.getModuleDefinition(mduri);
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                for(org.sbolstandard.core2.Model sbolmodel:sbolmodels){
                    URI key = sbolmodel.getSource();
                    URI modeldownload = new URI(key.toString() + "/download");
                    if(!modelmap.containsKey(key)) {
                        //System.out.println("Downloaded : " + modeldownload.toString());
                        SBMLDocument sbml = SynbiohubAdaptor.getModel(modeldownload.toURL(), fp);
                        modelmap.put(key, sbml);
                    } else {
                        System.out.println("This is not supposed to happen.");
                    }
                }
            }
        }
        return modelmap;
    }
    
    public static Map<String,String> getIndSMmap(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        Map<String,String> map = new HashMap<>();
        Set<URI> cdsprots = new HashSet<>();
        Map<String,URI> promprots = new HashMap<>();
        for(Component c:m.getComponents()){
            if(c.isPromoter()){
                String cname = c.getName();
                LibraryComponent cc = assignment.get(cname).getCandidate();
                PromoterComponent promcomp = null;
                if(cc instanceof PromoterComponent){
                    promcomp = (PromoterComponent) cc;
                } else if(cc instanceof CompositeComponent){
                    CompositeComponent composite = (CompositeComponent)cc;
                    promcomp = library.getAllPromoters().get(composite.getChildren().get(0));
                }
                for(LibraryComponent tf:promcomp.getTranscriptionFactors()){
                    if(tf instanceof ComplexComponent){
                        ComplexComponent complex = (ComplexComponent)tf;
                        SmallMoleculeComponent smc = library.getSmallMolecules().get(complex.getSmallMolecule());
                        if(!map.containsKey(smc.getName())){
                            promprots.put(smc.getName(), complex.getProtein());
                            map.put(smc.getName(), "ind_" + ioc.get(cname));
                        }
                    }
                }
            } else if(c.isCDS()){
                String cname = c.getName();
                CDSComponent cdscomp = (CDSComponent) assignment.get(cname).getCandidate();
                cdsprots.add(cdscomp.getProtein());
             } 
        }
        
        Map<String,String> finalmap = new HashMap<>();
        for(String smc:map.keySet()){
            if(cdsprots.contains(promprots.get(smc))){
                finalmap.put(smc, map.get(smc));
            }
        }
        
        return finalmap;
    }
    
    protected static List<Map<String,Double>> getSmallMoleculeConcentration(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        List<Map<String,Double>> smMap = new ArrayList<>();
        Map<String,List<Double>> smConcentrations = new HashMap<>();
        Map<String,List<Double>> nameConcMap = new HashMap<>();
        
        for(Component c:m.getComponents()){
            String cname = c.getName();
            if (c.isPromoter()) {
                LibraryComponent lc = assignment.get(cname).getCandidate();
                PromoterComponent promcomp = null;
                boolean isPromoter = false;
                if (lc instanceof PromoterComponent) {
                    promcomp = (PromoterComponent) lc;
                    isPromoter = true;
                } else if (lc instanceof CompositeComponent) {
                    CompositeComponent compcomp = (CompositeComponent) lc;
                    if (compcomp.getType().equals(CompositeComponent.CompositeType.PR)) {
                        promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                        isPromoter = true;
                    }
                }
                if (isPromoter) {
                    for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                        if (tf instanceof ComplexComponent) {
                            ComplexComponent complex = (ComplexComponent) tf;
                            SmallMoleculeComponent smc = library.getSmallMolecules().get(complex.getSmallMolecule());

                            String indName = "ind_" + ioc.get(cname);
                            if (!smConcentrations.containsKey(indName)) {
                                smConcentrations.put(indName, smc.getValues());
                                nameConcMap.put(smc.getName(), smc.getValues());
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Small Molecule Concentrations:");
        System.out.println(nameConcMap);
        List<Map<String,Double>> temp = new ArrayList<>();
        for(String ind:smConcentrations.keySet()){
            List<Double> indConc = smConcentrations.get(ind);
            if(smMap.isEmpty()){
                for(Double conc:indConc){
                    Map<String,Double> map = new HashMap<>();
                    map.put(ind, conc);
                    smMap.add(map);
                }
            } else {
                for(Map<String,Double> hconcs: smMap){
                    for(Double conc:indConc){
                        Map<String,Double> map = new HashMap<>();
                        map.putAll(hconcs);
                        map.put(ind, conc);
                        temp.add(map);
                    }
                }
                smMap = new ArrayList<>();
                smMap.addAll(temp);
                temp = new ArrayList<>();
            }
        }
        
        return smMap;
    }
    
    public static Map<String, String> getIOCmap(Module module, Map<String, CandidateComponent> assignment, Library library) {
        int inCount = 0;
        int outCount = 0;
        int connCount = 0;
        Map<URI, String> protmap = new HashMap<>();
        Map<String, String> ioc = new HashMap<>();
        for (Component c : module.getComponents()) {
            if (c.isRBS() || c.isTerminator()) {
                continue;
            }
            if (!ioc.containsKey(c.getName())) {
                CandidateComponent cc = assignment.get(c.getName());
                if (library.getPr().containsKey(cc.getCandidate().getComponentDefintion())) {
                    CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
                    if (library.getConstitutivePromoters().containsKey(compcomp.getChildren().get(0))) {
                        ioc.put(c.getName(), "in" + inCount);
                        inCount++;
                    } else {
                        if(c.isPromoter()){
                            LibraryComponent lc = assignment.get(c.getName()).getCandidate();
                            PromoterComponent pc = library.getAllInduciblePromoters().get(compcomp.getChildren().get(0));
                            for (LibraryComponent tf : pc.getTranscriptionFactors()) {
                                URI promprot = null;
                                if (tf instanceof ComplexComponent) {
                                    ComplexComponent complex = (ComplexComponent) tf;
                                    promprot = complex.getProtein();
                                } else if (tf instanceof PrimitiveComponent) {
                                    promprot = tf.getComponentDefintion();
                                }
                                //Need to deal with multiple instances??
                                if (protmap.containsKey(promprot)) {
                                    ioc.put(c.getName(), protmap.get(promprot));
                                } else {
                                    String conn = "conn" + connCount;
                                    ioc.put(c.getName(), conn);
                                    protmap.put(promprot, conn);
                                    connCount++;
                                }
                                break;//
                                //Should other proteins be renamed? 
                            }
                        }
                    }
                } else if (library.getConstitutivePromoters().containsKey(cc.getCandidate().getComponentDefintion())) {
                    ioc.put(c.getName(), "in" + inCount);
                    inCount++;
                } else if (library.getOutputCDS().containsKey(cc.getCandidate().getComponentDefintion())) {
                    ioc.put(c.getName(), "out" + outCount);
                    outCount++;
                } else {
                    if (c.isPromoter()) {
                        LibraryComponent lc = assignment.get(c.getName()).getCandidate();
                        PromoterComponent pc = null;
                        if (lc instanceof PromoterComponent) {
                            pc = (PromoterComponent) lc;

                        } else if (lc instanceof CompositeComponent) {
                            CompositeComponent compcomp = (CompositeComponent) lc;
                            pc = library.getAllInduciblePromoters().get(compcomp.getChildren().get(0));
                        }
                        for (LibraryComponent tf : pc.getTranscriptionFactors()) {
                            URI promprot = null;
                            if (tf instanceof ComplexComponent) {
                                ComplexComponent complex = (ComplexComponent) tf;
                                promprot = complex.getProtein();
                            } else if (tf instanceof PrimitiveComponent) {
                                promprot = tf.getComponentDefintion();
                            }
                            //Need to deal with multiple instances??
                            if (protmap.containsKey(promprot)) {
                                ioc.put(c.getName(), protmap.get(promprot));
                            } else {
                                String conn = "conn" + connCount;
                                ioc.put(c.getName(), conn);
                                protmap.put(promprot, conn);
                                connCount++;
                            }
                            break;//
                            //Should other proteins be renamed? 
                        }

                    } else if (c.isCDS()) {
                        CDSComponent cdscomp = (CDSComponent) assignment.get(c.getName()).getCandidate();
                        if (protmap.containsKey(cdscomp.getProtein())) {
                            ioc.put(c.getName(), protmap.get(cdscomp.getProtein()));
                        } else {
                            String conn = "conn" + connCount;
                            ioc.put(c.getName(), conn);
                            protmap.put(cdscomp.getProtein(), conn);
                            connCount++;
                        }
                    }
                }
            }
        }
        //System.out.println("IOC :: " + ioc);
        return ioc;
    }
    
    public static void assignLeafModels(Module module, Map<String, CandidateComponent> assignment, SBOLDocument doc, Map<URI,SBMLDocument> modelmap, Args.Decomposition decomposition) throws URISyntaxException, MalformedURLException {
        switch (decomposition) {
            case PR_C_T:
                for (Module tu : module.getChildren()) {
                    assignLeafModelsPR_C_T(tu, assignment, doc, modelmap);
                }
                break;
            case P_RC_T:
                break;
            case P_R_C_T:
                break;
        }
    }

    public static void renameSpecies(Module module, Map<String, String> ioc, Library library, Args.Decomposition decomposition) {
        switch (decomposition) {
            case PR_C_T:
                renameSpeciesPR_C_T(module, ioc, library);
                break;
            case P_RC_T:
                break;
            case P_R_C_T:
                break;
        }
    }

    public static void renameSpeciesPR_C_T(Module module, Map<String, String> ioc, Library library) {

        for (Module tu : module.getChildren()) {
            Module prModule = tu.getChildren().get(0);
            Module cdsModule = tu.getChildren().get(1);
            Component prom = prModule.getComponents().get(0);
            Component cds = cdsModule.getComponents().get(0);
            
            if (ioc.get(prom.getName()).startsWith("in")) {
                SBMLAdaptor.renameSpecies(prModule.getModel().getSbml(), "out", ioc.get(cds.getName()));
            } else if (ioc.get(prom.getName()).startsWith("conn")) {
                if (prModule.getModel().getSbml().getModel().containsSpecies("ind")) {
                    SBMLAdaptor.renameSpecies(prModule.getModel().getSbml(), "ind", "ind_" + ioc.get(prom.getName()));
                }
                SBMLAdaptor.renameSpecies(prModule.getModel().getSbml(), "conn", ioc.get(prom.getName()));
                SBMLAdaptor.renameSpecies(prModule.getModel().getSbml(), "out", ioc.get(cds.getName()));
            }
            if (ioc.get(cds.getName()).startsWith("conn")) {
                SBMLAdaptor.renameSpecies(cdsModule.getModel().getSbml(), "conn", ioc.get(cds.getName()));
            } else if (ioc.get(cds.getName()).startsWith("out")) {
                SBMLAdaptor.renameSpecies(cdsModule.getModel().getSbml(), "out", ioc.get(cds.getName()));
            }
        }
    }

    protected static void assignLeafModelsPR_C_T(Module module, Map<String, CandidateComponent> assignment, SBOLDocument doc, Map<URI,SBMLDocument> modelmap) throws URISyntaxException, MalformedURLException {
        for (Module child : module.getChildren()) {
            if (child.getRole().equals(Module.ModuleRole.PROMOTER_RBS)) {
                Component prom = child.getComponents().get(0);
                CandidateComponent cc = assignment.get(prom.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = sbolmodels.get(0).getSource();
                child.setModel(new Model(new SBMLDocument(modelmap.get(uri))));
            } else if (child.getRole().equals(Module.ModuleRole.CDS)) {
                Component cds = child.getComponents().get(0);
                CandidateComponent cc = assignment.get(cds.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = sbolmodels.get(0).getSource();
                child.setModel(new Model(new SBMLDocument(modelmap.get(uri))));
            }
        }

    }
    
    public static void assignPartLeafModels(Module root, String jobfp, SBOLDocument doc, Map<String, CandidateComponent> assignment) {
        if (root.getRole().equals(Module.ModuleRole.PROMOTER)) {

            try {
                Component prom = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(prom.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");
                SBMLDocument sbml = SynbiohubAdaptor.getModel(uri.toURL(), jobfp);
                Model model = new Model(sbml);
                root.setModel(model);
            } catch (MalformedURLException | URISyntaxException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (root.getRole().equals(Module.ModuleRole.CDS)) {
            try {
                Component cds = root.getComponents().get(0);
                CandidateComponent cc = assignment.get(cds.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = new URI(sbolmodels.get(0).getSource().toString() + "/download");

                SBMLDocument sbml = SynbiohubAdaptor.getModel(uri.toURL(), jobfp);
                Model model = new Model(sbml);
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
    
    
    public static void composeModels(Module module, Args.Decomposition decomposition) {
        switch (decomposition) {
            case PR_C_T:
                composeModelsPR_C_T(module);
                break;
            case P_RC_T:
                break;
            case P_R_C_T:
                break;
        }
    }

    private static void composeModelsPR_C_T(Module module) {
        List<org.sbml.jsbml.Model> modelList = new ArrayList<>();
        int count = 0;
        for (Module tu : module.getChildren()) {
            List<org.sbml.jsbml.Model> childModelList = new ArrayList<>();
            for (Module child : tu.getChildren()) {
                if (child.getRole().equals(Module.ModuleRole.PROMOTER_RBS) || child.getRole().equals(Module.ModuleRole.CDS)) {
                    childModelList.add(child.getModel().getSbml().getModel());
                }
            }
            Model tuModel = new Model(SBMLAdaptor.composeModels(childModelList));
            tu.setModel(tuModel);
            modelList.add(tuModel.getSbml().getModel());
        }
        Model modulemodel = new Model(SBMLAdaptor.composeModels(modelList));
        module.setModel(modulemodel);
    }
    
    
}
