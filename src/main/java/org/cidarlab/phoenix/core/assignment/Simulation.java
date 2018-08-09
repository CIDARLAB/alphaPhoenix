/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor.Axis;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.DnaPlotlibAdaptor;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Model;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent.CompositeType;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Args.Decomposition;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public class Simulation {
    
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
    
    public static void run(Module module, Library library, TreeNode stl, Args args, String fp) throws URISyntaxException, MalformedURLException, XMLStreamException, FileNotFoundException, IOException, TLIException, InterruptedException {
        String tempfp = fp + "temp" + Utilities.getSeparater();
        Utilities.makeDirectory(tempfp);
        int count = 0;
        
        
        int hasSM = 0;
        int noSM = 0;
        
        Map<URI,SBMLDocument> modelmap = downloadAllModels(library, tempfp);
        File tempdir = new File(tempfp);
        tempdir.delete();
        Map<Integer,Integer> smCounts = new HashMap<>();
        for (Map<String, CandidateComponent> assignment : module.getAssignments()) {
            
            Map<String, String> ioc = getIOCmap(module, assignment, library);
            
            assignLeafModels(module, assignment, library.getSbol(), modelmap, args.getDecomposition());
            renameSpecies(module, ioc, library, args.getDecomposition());
            composeModels(module, args.getDecomposition());
            
            Map<String,String> indSMmap =  getIndSMmap(module, assignment, ioc, library);
            
            if(indSMmap.isEmpty()){
                
                noSM++;
                System.out.println("Current Assignment : " + count + " has no small molecules.");
                printAssignment(module,assignment);
                
                String ifp = fp + count + Utilities.getSeparater();
                Utilities.makeDirectory(ifp);
                
                SBMLWriter writer = new SBMLWriter();
                String modelFile = ifp + "model.xml";
                writer.write(module.getModel().getSbml(), modelFile);
                
                //runSimulation(module, assignment, ioc, library, stl, modelFile, args, ifp);
                count++;
                
            } else {
                hasSM++;
                
                if(!smCounts.containsKey(indSMmap.size())){
                    smCounts.put(indSMmap.size(), 0);
                } else {
                    int smcount = smCounts.get(indSMmap.size());
                    smcount++;
                    smCounts.put(indSMmap.size(),smcount);
                }
                
                
                List<Map<String, Double>> concList = getSmallMoleculeConcentration(module, assignment, ioc, library);
                for (Map<String, Double> conc : concList) {
                    
                    System.out.println("Current Assignment : " + count);
                    printAssignment(module, assignment);
                    
                    JSONObject smevents = new JSONObject();
                    
                    String ifp = fp + count + Utilities.getSeparater();
                    Utilities.makeDirectory(ifp);
                    
                    SBMLDocument sbml = new SBMLDocument(module.getModel().getSbml());
                    SBMLWriter writer = new SBMLWriter();
                    String modelFile = ifp + "model.xml";
                    String smfp = ifp + "assignedSM.json";
                    for(String ind:conc.keySet()){
                        smevents.put(ind, conc.get(ind));
                        SBMLAdaptor.addEvent(sbml, ind, 600.00, conc.get(ind));
                    }
                    writer.write(sbml, modelFile);
                    Utilities.writeToFile(smfp, smevents.toString(2));
                    //runSimulation(module, assignment, ioc, library, stl, modelFile, args, ifp);
                    
                    count++;
                }
            }
            
            //*/

        }
        
        System.out.println("Total number of assignments      : " + count);
        System.out.println("Number of assignments with SM    : " + hasSM);
        System.out.println("Number of assignments with no SM : " + noSM);
        System.out.println("SM Counts");
        for(Integer i:smCounts.keySet()){
            System.out.println("Number of assignments with " + i + " SM(s) : " + smCounts.get(i));
        }
        
    }
    
    private static void runSimulation(Module module, Map<String, CandidateComponent> assignment, Map<String, String> ioc, Library library, TreeNode stl, String modelFile, Args args, String ifp) throws InterruptedException, IOException, TLIException {
        
        String dnaplotlibfp = ifp + "visualsbol.py";
        String dnaplotlibscript = DnaPlotlibAdaptor.generateScript(module, assignment, ioc, new HashMap<String, String>(), library, ifp + "circuit");
        Utilities.writeToFile(dnaplotlibfp, dnaplotlibscript);
        DnaPlotlibAdaptor.runScript(dnaplotlibfp);
        
        double maxtime = STLAdaptor.getMaxTime(stl);
        IBioSimAdaptor.simulateStocastic(modelFile, ifp, STLAdaptor.getMaxTime(stl), 10, 10, args.getRunCount());
        Map<String, TreeNode> stlmap = STLAdaptor.getSignalSTLMap(stl);
        Map<String, List<Signal>> allsignals = new HashMap<>();
        for (int i = 1; i <= args.getRunCount(); i++) {
            String tsdfp = ifp + "run-" + i + ".csv";
            Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
            for (String key : stlmap.keySet()) {
                if (signalMap.containsKey(key)) {
                    Signal s = signalMap.get(key);
                    if (allsignals.containsKey(key)) {
                        allsignals.get(key).add(s);
                    } else {
                        allsignals.put(key, new ArrayList<Signal>());
                        allsignals.get(key).add(s);
                    }
                }
            }
            File f = new File(tsdfp);
            f.delete();
        }
        
        for (String key : allsignals.keySet()) {
            Utilities.writeSignalsToCSV(allsignals.get(key), ifp + key + ".csv");
            List<String> pylines = PyPlotAdaptor.generateSignalPlotScript(allsignals.get(key), ifp + key + ".png", 0, maxtime, 0, 100000, Axis.LINEAR, Axis.SYMLOG);
            Utilities.writeToFile(ifp + key + "_signals.py", pylines);
            PyPlotAdaptor.runScript(ifp + key + "_signals.py");
        }
        
                
    }
    
    private static Map<URI,SBMLDocument> downloadAllModels(Library library, String fp) throws URISyntaxException, MalformedURLException{
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
    
    private static Map<String,String> getIndSMmap(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        Map<String,String> map = new HashMap<>();
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
                            map.put(smc.getName(), "ind_" + ioc.get(cname));
                        }
                    }
                }
            }
        }
        return map;
    }
    
    private static List<Map<String,Double>> getSmallMoleculeConcentration(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        List<Map<String,Double>> smMap = new ArrayList<>();
        Map<String,List<Double>> smConcentrations = new HashMap<>();
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
                    if (compcomp.getType().equals(CompositeType.PR)) {
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
                                if (!smc.getValues().isEmpty()) {
                                    smConcentrations.put(indName, smc.getValues());
                                } else {
                                    double min = smc.getMin();
                                    double max = smc.getMax();
                                    //double inc = (max - min) * 0.05;
                                    //double inc = (max - min) * 0.1;
                                    List<Double> concs = new ArrayList<>();
                                    concs.add(min);
                                    
                                    for(int i=-6;i<=0;i++){
                                        double mult = Math.pow(10, i);
                                        double val = max * mult;
                                        if(val > min){
                                            concs.add(val);
                                        }
                                    }
                                    smConcentrations.put(indName, concs);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Small Molecule Concentrations:");
        System.out.println(smConcentrations);
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
    
    private static void composeModels(Module module, Decomposition decomposition) {
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
                if (child.getRole().equals(ModuleRole.PROMOTER_RBS) || child.getRole().equals(ModuleRole.CDS)) {
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

    private static void assignLeafModels(Module module, Map<String, CandidateComponent> assignment, SBOLDocument doc, Map<URI,SBMLDocument> modelmap, Decomposition decomposition) throws URISyntaxException, MalformedURLException {
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

    public static void renameSpecies(Module module, Map<String, String> ioc, Library library, Decomposition decomposition) {
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
            /*System.out.println("###########################################");
            System.out.println("Prom Model");
            System.out.println("-------------------------------------------");
            SBMLWriter pw = new SBMLWriter();
            try {
                System.out.println(pw.writeSBMLToString(prModule.getModel().getSbml()));
            } catch (XMLStreamException | SBMLException ex) {
                Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("###########################################");
            System.out.println("CDS Model");
            System.out.println("-------------------------------------------");
            SBMLWriter cw = new SBMLWriter();
            try {
                System.out.println(cw.writeSBMLToString(cdsModule.getModel().getSbml()));
            } catch (XMLStreamException | SBMLException ex) {
                Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("###########################################");
                    */
        }
    }

    private static void assignLeafModelsPR_C_T(Module module, Map<String, CandidateComponent> assignment, SBOLDocument doc, Map<URI,SBMLDocument> modelmap) throws URISyntaxException, MalformedURLException {
        for (Module child : module.getChildren()) {
            if (child.getRole().equals(ModuleRole.PROMOTER_RBS)) {
                Component prom = child.getComponents().get(0);
                CandidateComponent cc = assignment.get(prom.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = sbolmodels.get(0).getSource();
                child.setModel(new Model(new SBMLDocument(modelmap.get(uri))));
            } else if (child.getRole().equals(ModuleRole.CDS)) {
                Component cds = child.getComponents().get(0);
                CandidateComponent cc = assignment.get(cds.getName());
                ModuleDefinition md = doc.getModuleDefinition(cc.getCandidate().getModuleDefinitions().get(0));
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                URI uri = sbolmodels.get(0).getSource();
                child.setModel(new Model(new SBMLDocument(modelmap.get(uri))));
            }
        }

    }

    private static Map<String, String> getIOCmap(Module module, Map<String, CandidateComponent> assignment, Library library) {
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

}
