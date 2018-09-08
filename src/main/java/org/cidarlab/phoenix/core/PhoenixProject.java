/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.SBOLAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.adaptors.UIAdaptor;
import org.cidarlab.phoenix.core.assignment.AbstractAssignment;
import org.cidarlab.phoenix.core.assignment.Exhaustive;
import org.cidarlab.phoenix.core.assignment.SimulatedAnnealing;
import org.cidarlab.phoenix.dom.AssignmentNode;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.SMC;
import org.cidarlab.phoenix.failuremode.FailureModeGrammar;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Args.Assignment;
import org.cidarlab.phoenix.utils.Args.Decomposition;
import org.cidarlab.phoenix.utils.Args.Simulation;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLValidationException;
import org.sbolstandard.core2.SBOLWriter;

/**
 *
 * @author prash
 */
public class PhoenixProject {

    @Getter
    private String userid;

    @Getter
    private String projectFolder;

    @Getter
    private String jobId;
    
    @Getter
    private Args args;
    
    public String getJobFolder() {
        if (projectFolder.endsWith("" + Utilities.getSeparater())) {
            return (projectFolder + jobId);
        } else {
            return projectFolder + Utilities.getSeparater() + jobId;
        }
    }

    private void createCLIfolder() {
        this.projectFolder = Utilities.getResultsFilepath() + "cli" + Utilities.getSeparater();
        if (!Utilities.isDirectory(this.projectFolder)) {
            Utilities.makeDirectory(this.projectFolder);
        }
    }

    public PhoenixProject() {
        createCLIfolder();
        createJob();
    }

    public PhoenixProject(String _resultsFolder) {
        if (!Utilities.isDirectory(_resultsFolder)) {
            System.err.println("Error! Directory " + _resultsFolder + " does not exist");
            System.exit(-1);
        }
        this.projectFolder = _resultsFolder;
        createJob();
    }

    public PhoenixProject(String eugfp, int eugCircSize, Integer eugNumSolutions, String stlfp, String libraryfp, Simulation simulation, Decomposition decomposition, int runCount, double confidence, double threshold, Map<String, Double> inputMap, boolean plot) throws URISyntaxException {
        createCLIfolder();
        try {
            createJob();

            //Filepath of the current JOB
            String jobfp = Utilities.getResultsFilepath() + this.jobId + Utilities.getSeparater();
            File f = new File(jobfp);
            System.out.println(f.getAbsoluteFile() + " directory created for current run.");
            //Copy Eugene file to Job filepath
            FileUtils.copyFile(new File(eugfp), new File(jobfp + "structure.eug"));

            //Create Eugene files
            JSONObject euginfo = new JSONObject();
            if (!(eugNumSolutions == null)) {
                euginfo.put("solutions", eugNumSolutions);
            }
            euginfo.put("size", eugCircSize);
            Utilities.writeToFile(jobfp + "eug.json", euginfo.toString());

            //Create STL file
            FileUtils.copyFile(new File(stlfp), new File(jobfp + "stl.txt"));

            //Create Library file
            FileUtils.copyFile(new File(libraryfp), new File(jobfp + "library.json"));

            String libfilecontent = Utilities.getFileContentAsString(jobfp + "library.json");
            JSONObject lib = new JSONObject(libfilecontent);
            if (lib.has("database")) {
                if (lib.getString("database").equals("synbiohub")) {
                    if ((!lib.has("registry")) || (!lib.has("collection"))) {
                        System.out.println(libraryfp + " is missing certain fields.");
                        System.exit(-1);
                    } else {
                        String registry = lib.getString("registry");
                        String collection = lib.getString("collection");
                        SBOLDocument sbol = SynbiohubAdaptor.getSBOL(registry, collection);
                        SBOLWriter.write(sbol, (jobfp + "sbol.xml"));
                    }
                } else {
                    System.out.println("Currently only synbiohub repositories are supported.");
                    System.exit(-1);
                }
            } else {
                System.out.println(libraryfp + " is missing certain fields.");
                System.exit(-1);
            }

            PhoenixProject.execute(jobId, simulation, decomposition, runCount, confidence, threshold, plot, inputMap);

        } catch (IOException | SBOLConversionException | TLIException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PhoenixProject(String eugfp, int eugCircSize, Integer eugNumSolutions, String stlfp, String libraryfp) {
        createCLIfolder();
        try {
            createJob();

            //Filepath of the current JOB
            String jobfp = Utilities.getResultsFilepath() + this.jobId + Utilities.getSeparater();
            File f = new File(jobfp);
            System.out.println(f.getAbsoluteFile() + " directory created for current run.");
            //Copy Eugene file to Job filepath
            FileUtils.copyFile(new File(eugfp), new File(jobfp + "structure.eug"));

            //Create Eugene files
            JSONObject euginfo = new JSONObject();
            if (!(eugNumSolutions == null)) {
                euginfo.put("solutions", eugNumSolutions);
            }
            euginfo.put("size", eugCircSize);
            Utilities.writeToFile(jobfp + "eug.json", euginfo.toString());

            //Create STL file
            FileUtils.copyFile(new File(stlfp), new File(jobfp + "stl.txt"));

            //Create Library file
            FileUtils.copyFile(new File(libraryfp), new File(jobfp + "library.json"));

            String libfilecontent = Utilities.getFileContentAsString(jobfp + "library.json");
            JSONObject lib = new JSONObject(libfilecontent);
            if (lib.has("database")) {
                if (lib.getString("database").equals("synbiohub")) {
                    if ((!lib.has("registry")) || (!lib.has("collection"))) {
                        System.out.println(libraryfp + " is missing certain fields.");
                        System.exit(-1);
                    } else {
                        String registry = lib.getString("registry");
                        String collection = lib.getString("collection");
                        SBOLDocument sbol = SynbiohubAdaptor.getSBOL(registry, collection);
                        SBOLWriter.write(sbol, (jobfp + "sbol.xml"));
                    }
                } else {
                    System.out.println("Currently only synbiohub repositories are supported.");
                    System.exit(-1);
                }
            } else {
                System.out.println(libraryfp + " is missing certain fields.");
                System.exit(-1);
            }

        } catch (IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void executeBasicProject(int runCount, double confidence, double threshold) throws IOException, SBOLValidationException, SBOLConversionException, XMLStreamException, TLIException, InterruptedException, URISyntaxException {
        String jobfp = this.projectFolder + this.jobId + Utilities.getSeparater();
        SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T, jobfp);
        String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
        JSONObject eug = new JSONObject(eugfilecontent);
        int eugCircSize;
        Integer eugNumSolutions = null;

        if (eug.has("solutions")) {
            eugNumSolutions = eug.getInt("solutions");
        }
        eugCircSize = eug.getInt("size");
        String eugfp = jobfp + "structure.eug";
        TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");
        boolean first = true;
        double bestval = 0;
        int bestindex = 0;

        List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, this.jobId);

        List<String> eugDesignList = new ArrayList<String>();
        String eugdesignSpace = "";
        for (Module m : modules) {
            eugdesignSpace += (m.getComponentString() + "\n");
        }

        Utilities.writeToFile(jobfp + "designspace.txt", eugdesignSpace);

        List<Module> decomposedModules = new ArrayList<Module>();
        for (Module m : modules) {
            decomposedModules.add(Controller.decompose(m, args.getDecomposition()));
        }
        JSONArray results = new JSONArray();
        int resultCount = 0;
        
        
        String jobresultsfp;
        jobresultsfp = jobfp + "results" + Utilities.getSeparater();
        if (!Utilities.validFilepath(jobresultsfp)) {
            Utilities.makeDirectory(jobresultsfp);
        }
        
        
        for (Module decomposedModule : decomposedModules) {
            
            List<Map<String, CandidateComponent>> assignments = new ArrayList<>();
            System.out.println("");
            System.out.println(assignments.size() + " candidate assignments found for design " + resultCount);
            String designfp = jobresultsfp + "design" + resultCount + Utilities.getSeparater();
            Utilities.makeDirectory(designfp);
            
            List<String> storeslines = new ArrayList<String>();
            storeslines.add("Assignment Index,Percentage Satisfying, Error");
            
            String moduleImage = decomposedModule.getAbstractSBOLVisual(new HashMap<String,String>());
            
            for (int i = 0; i < assignments.size(); i++) {
                
                System.out.println("Assignment Number : " + i);
                for(String key:assignments.get(i).keySet()){
                    System.out.println(key + "::" + assignments.get(i).get(key).getCandidate().getName());
                }
                
                String assignmentfp;
                assignmentfp = designfp + i + Utilities.getSeparater();
                Utilities.makeDirectory(assignmentfp);
                
                Map<String, CandidateComponent> assignment = assignments.get(i);
                Controller.assignLeafModels(decomposedModule, assignmentfp, sbol, assignment);
                Controller.composeModels(decomposedModule, assignmentfp, assignment);
                
                
                String modelFile;
                SBMLWriter writer = new SBMLWriter();
                Map<String, Double> eventvalues = new HashMap<>();

                System.out.println("Starting Stochastic Simulation for assignment " + i);
                modelFile = assignmentfp + "model.xml";
                

                //This is where you add events.
                for (Component c : decomposedModule.getComponents()) {
                    switch (c.getRole()) {
                        case PROMOTER_INDUCIBLE:
                        case PROMOTER_REPRESSIBLE:
                        case PROMOTER_ACTIVATABLE:
                            CandidateComponent assignmentcc = assignment.get(c.getName());
                            if (assignmentcc.getCandidate().getName().equals("pLas_RBS30")) {
                                eventvalues.put("ind_" + c.getIOCname(), 1.00);
                            } else if (assignmentcc.getCandidate().getName().equals("pBAD_RBS30")) {
                                eventvalues.put("ind_" + c.getIOCname(), 0.00);
                            }
                            break;
                        default:
                            //System.out.println("NOTHING");
                            break;
                    }
                }
                for (String indkey : eventvalues.keySet()) {
                    SBMLAdaptor.addEvent(decomposedModule.getModel().getSbml(), indkey, 600.00, eventvalues.get(indkey));
                }
                //End of horrible hard coding.. :( 

                writer.write(decomposedModule.getModel().getSbml(), modelFile);
                IBioSimAdaptor.simulateStocastic(modelFile, assignmentfp, STLAdaptor.getMaxTime(jobstl), 1, 1, runCount);
                SMC smc = STLAdaptor.smc(jobstl, assignmentfp, false, runCount, confidence);
                double perc = smc.getSatisfactionPercentage();
                double error = smc.getError();
                
                double lower = perc - error;
                storeslines.add(i + "," + perc + "," + error);
                System.out.println("Lower : " + lower);
                System.out.println("Threshold : " + threshold);
                if (perc >= threshold) {
                //if (true) {    
                    JSONObject resultObj = new JSONObject();
                    resultObj.put("name", ("Result " + resultCount++));
                    resultObj.put("img", moduleImage);
                    resultObj.put("score", perc);
                    
                    JSONArray tracesArr = new JSONArray();
                    
                    int traceCount = 0;
                    for(String signalkey:smc.getSimulations().keySet()){
                        
                        for(Signal sig:smc.getSimulations().get(signalkey)){
                            JSONObject traceObj = new JSONObject();
                            //traceObj.put("name", "trace" + traceCount++);
                            traceObj.put("name", signalkey);
                            traceObj.put("legendgroup", signalkey);
                            traceObj.put("type", "scatter");
                            JSONArray xarr = new JSONArray();
                            JSONArray yarr = new JSONArray();
                            for(Point point:sig.getPoints()){
                               xarr.put(point.getX());
                               yarr.put(point.getY());
                            }
                            traceObj.put("x", xarr);
                            traceObj.put("y", yarr);
                            traceObj.put("showlegend",traceCount == 0);
                            JSONObject lineobj = new JSONObject();
                            lineobj.put("color", "#2196F3AA");
                            traceObj.put("line", lineobj);
                            
                            tracesArr.put(traceObj);
                            traceCount++;
                        }
                        
                    }
                    resultObj.put("traces", tracesArr);
                    
                    results.put(resultObj);
                }
            }

            

        }
        
        //System.out.println(results.toString());
        Utilities.writeToFile(jobfp + "results.json", results.toString());

    }
    
    
    public void executeBasicProject() throws IOException, SBOLValidationException, SBOLConversionException, XMLStreamException, TLIException, InterruptedException, URISyntaxException {
        
        String jobfp = this.projectFolder + this.jobId + Utilities.getSeparater();
        args.setProjectFolder(jobfp);
        
        SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T, jobfp);
        
        String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
        JSONObject eug = new JSONObject(eugfilecontent);
        int eugCircSize;
        Integer eugNumSolutions = null;

        if (eug.has("solutions")) {
            eugNumSolutions = eug.getInt("solutions");
        }
        
        eugCircSize = eug.getInt("size");
        String eugfp = jobfp + "structure.eug";
        TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");
        
        boolean first = true;
        double bestval = 0;
        int bestindex = 0;

        List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, this.jobId);
        
        System.out.println("Number of modules from Eugene :: " + modules.size());
        
        List<String> eugDesignList = new ArrayList<>();
        String eugdesignSpace = "";
        for (Module m : modules) { 
           eugdesignSpace += (m.getComponentString() + "\n");
        }
        Utilities.writeToFile(jobfp + "designspace.txt", eugdesignSpace);
        
        List<Module> decomposedModules = new ArrayList<>();
        for (Module m : modules) {
            decomposedModules.add(Controller.decompose(m, args.getDecomposition()));
        }
        
        List<AssignmentNode> assignments = new ArrayList<>();
        switch(args.getAssignment()){
            case SIMULATED_ANNEALING:
                SimulatedAnnealing sa = new SimulatedAnnealing();
                sa.solve(decomposedModules, lib, jobstl, args);
                break;
            case EXHAUSTIVE:
                Exhaustive exhaustive = new Exhaustive();
                assignments = exhaustive.solve(decomposedModules, lib, jobstl, args);
                break;
        }
        
        
        
        JSONArray results = new JSONArray();
        int resultCount = 0;
        
        
        String jobresultsfp;
        jobresultsfp = jobfp + "results" + Utilities.getSeparater();
        if (!Utilities.validFilepath(jobresultsfp)) {
            Utilities.makeDirectory(jobresultsfp);
        }
        List<String> resultLines = new ArrayList<>();
        resultLines.add("Module Index, Assignment Index, Score");
        for(AssignmentNode an:assignments){
            resultLines.add(an.getModuleIndex() + "," + an.getAssignmentIndex() + "," + an.getScore());
            JSONObject resultsObj = new JSONObject();
            resultsObj.put("img", "/sbol/" + this.userid + "/" + this.jobId + "/" + an.getModuleIndex() + "/" + an.getAssignmentIndex() + "/" + "circuit");
            resultsObj.put("name", "Module " + an.getModuleIndex() + " Assignment " + an.getAssignmentIndex());
            resultsObj.put("score", an.getScore());
            
            JSONArray tracesArr = new JSONArray();
            resultsObj.put("traces", tracesArr);
            results.put(resultsObj);
        }
        
        //System.out.println(results.toString());
        Utilities.writeToFile(jobfp + "results.json", results.toString());

    }
    
    public static void executeAssignment(String jobid, Decomposition decomposition) throws InterruptedException, URISyntaxException {
        try {
            String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
            if (!Utilities.validFilepath(jobfp)) {
                System.out.println("Sorry. " + jobid + " does not exist. Please ensure that the job has been created.");
                System.exit(-1);
            }
            SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
            Library lib = new Library(sbol, decomposition,jobfp);
            String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
            JSONObject eug = new JSONObject(eugfilecontent);
            int eugCircSize;
            Integer eugNumSolutions = null;

            if (eug.has("solutions")) {
                eugNumSolutions = eug.getInt("solutions");
            }
            eugCircSize = eug.getInt("size");
            String eugfp = jobfp + "structure.eug";
            TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");
            boolean first = true;
            double bestval = 0;
            int bestindex = 0;

            List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, jobid);

            List<String> eugDesignList = new ArrayList<String>();
            String eugdesignSpace = "";
            for (Module m : modules) {
                eugdesignSpace += (m.getComponentString() + "\n");
            }

            Utilities.writeToFile(jobfp + "designspace.txt", eugdesignSpace);

            List<Module> decomposedModules = new ArrayList<Module>();

            for (Module m : modules) {
                decomposedModules.add(Controller.decompose(m, decomposition));
            }

            JSONArray arr = new JSONArray();

            for (Module m : decomposedModules) {
                //Controller.assignTUCandidates(m, lib, sbol);
            }

            for (Module m : decomposedModules) {
                arr.put(UIAdaptor.getModuleJSON(m));
            }

            Utilities.writeToFile(jobfp + "design.json", arr.toString(2));

        } catch (SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void execute(String jobid, Simulation simulation, Decomposition decomposition, int runCount, double confidence, double threshold, boolean plot, Map<String, Double> inputMap) throws TLIException, URISyntaxException {
        try {
            String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
            if (!Utilities.validFilepath(jobfp)) {
                System.out.println("Sorry. " + jobid + " does not exist. Please ensure that the job has been created.");
                System.exit(-1);
            }
            SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
            Library lib = new Library(sbol, Args.Decomposition.PR_C_T,jobfp);
            String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
            JSONObject eug = new JSONObject(eugfilecontent);
            int eugCircSize;
            Integer eugNumSolutions = null;

            if (eug.has("solutions")) {
                eugNumSolutions = eug.getInt("solutions");
            }
            eugCircSize = eug.getInt("size");
            String eugfp = jobfp + "structure.eug";
            TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");
            boolean first = true;
            double bestval = 0;
            int bestindex = 0;

            List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, jobid);

            List<String> eugDesignList = new ArrayList<String>();
            String eugdesignSpace = "";
            for (Module m : modules) {
                eugdesignSpace += (m.getComponentString() + "\n");
            }

            Utilities.writeToFile(jobfp + "designspace.txt", eugdesignSpace);

            Module best = getBestModule(modules);
            Utilities.writeToFile(jobfp + "bestdesign.txt", best.getComponentString());

            Module decomposedModule = Controller.decompose(best, decomposition);
            List<Map<String, CandidateComponent>> assignments = new ArrayList<>();
                    //Controller.assign(decomposedModule, lib, sbol);

            List<Integer> bestlist = new ArrayList<Integer>();

            System.out.println("");
            System.out.println(assignments.size() + " candidate assignments found.");
            int robcount = 0;
            int smccount = 0;
            List<String> detreslines = new ArrayList<String>();
            detreslines.add("Assignment Index,Robustness Value");
            List<String> storeslines = new ArrayList<String>();
            storeslines.add("Assignment Index,Percentage Satisfying, Error");
            for (int i = 0; i < assignments.size(); i++) {
                Map<String, CandidateComponent> assignment = assignments.get(i);

                Controller.assignLeafModels(decomposedModule, jobid, sbol, assignment);
                Controller.composeModels(decomposedModule, jobid, assignment);
                String jobresultsfp;
                jobresultsfp = jobfp + "results" + Utilities.getSeparater();
                if (!Utilities.validFilepath(jobresultsfp)) {
                    Utilities.makeDirectory(jobresultsfp);
                }
                String assignmentfp;
                String modelFile;
                SBMLWriter writer = new SBMLWriter();
                Map<String, TreeNode> stlsigmap = STLAdaptor.getSignalSTLMap(jobstl);
                Map<String, Double> eventvalues = new HashMap<String, Double>();
                switch (simulation) {
                    case DETERMINISTIC:

                        System.out.println("Starting Deterministic Simulation for assignment " + i);
                        String deterministic = jobresultsfp + "deterministic" + Utilities.getSeparater();
                        Utilities.makeDirectory(deterministic);
                        assignmentfp = deterministic + i + Utilities.getSeparater();
                        Utilities.makeDirectory(assignmentfp);
                        SBOLAdaptor.writeCircuitSBOL(best, assignment, assignmentfp, jobid, i);
                        modelFile = assignmentfp + "model.xml";
                        for (String sig : inputMap.keySet()) {
                            if (stlsigmap.containsKey(sig)) {
                                SBMLAdaptor.setValue(decomposedModule.getModel().getSbml(), sig, inputMap.get(sig));
                            }
                        }
                        //This is where you add events.

                        for (Component c : decomposedModule.getComponents()) {
                            switch (c.getRole()) {
                                case PROMOTER_INDUCIBLE:
                                case PROMOTER_REPRESSIBLE:
                                case PROMOTER_ACTIVATABLE:
                                    CandidateComponent assignmentcc = assignment.get(c.getName());
                                    if (assignmentcc.getCandidate().getName().equals("pLas_RBS30")) {
                                        eventvalues.put("ind_" + c.getIOCname(), 0.00);
                                    } else if (assignmentcc.getCandidate().getName().equals("pBAD_RBS30")) {
                                        eventvalues.put("ind_" + c.getIOCname(), 0.00);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        for (String indkey : eventvalues.keySet()) {
                            SBMLAdaptor.addEvent(decomposedModule.getModel().getSbml(), indkey, 600.00, eventvalues.get(indkey));
                        }
                        //End of horrible hard coding.. :( 

                        writer.write(decomposedModule.getModel().getSbml(), modelFile);
                        double maxtime = STLAdaptor.getMaxTime(jobstl);
                        IBioSimAdaptor.simulateODE(modelFile, assignmentfp, maxtime, 1.0, 1.0);
                        String tsdfp = assignmentfp + "run-1.csv";
                        double robval = STLAdaptor.getRobustness(jobstl, tsdfp, assignmentfp, plot);
                        detreslines.add(i + "," + robval);
                        if (first) {
                            bestval = robval;
                            first = false;
                        } else {
                            if (robval > bestval) {
                                bestval = robval;
                                bestindex = i;
                            }
                        }
                        if (robval >= 0) {
                            robcount++;
                            bestlist.add(i);
                        }
                        break;
                    case STOCHASTIC:
                        System.out.println("Starting Stochastic Simulation for assignment " + i);
                        String stochastic = jobresultsfp + "stochastic" + Utilities.getSeparater();
                        Utilities.makeDirectory(stochastic);
                        assignmentfp = stochastic + i + Utilities.getSeparater();
                        Utilities.makeDirectory(assignmentfp);
                        SBOLAdaptor.writeCircuitSBOL(best, assignment, assignmentfp, jobid, i);
                        modelFile = assignmentfp + "model.xml";
                        for (String sig : inputMap.keySet()) {
                            if (stlsigmap.containsKey(sig)) {
                                SBMLAdaptor.setValue(decomposedModule.getModel().getSbml(), sig, inputMap.get(sig));
                            }
                        }

                        //This is where you add events.
                        for (Component c : decomposedModule.getComponents()) {
                            switch (c.getRole()) {
                                case PROMOTER_INDUCIBLE:
                                case PROMOTER_REPRESSIBLE:
                                case PROMOTER_ACTIVATABLE:
                                    CandidateComponent assignmentcc = assignment.get(c.getName());
                                    if (assignmentcc.getCandidate().getName().equals("pLas_RBS30")) {
                                        eventvalues.put("ind_" + c.getIOCname(), 0.00);
                                    } else if (assignmentcc.getCandidate().getName().equals("pBAD_RBS30")) {
                                        eventvalues.put("ind_" + c.getIOCname(), 0.00);
                                    }
                                    break;
                                default:
                                    //System.out.println("NOTHING");
                                    break;
                            }
                        }
                        for (String indkey : eventvalues.keySet()) {
                            SBMLAdaptor.addEvent(decomposedModule.getModel().getSbml(), indkey, 600.00, eventvalues.get(indkey));
                        }
                        //End of horrible hard coding.. :( 

                        writer.write(decomposedModule.getModel().getSbml(), modelFile);
                        IBioSimAdaptor.simulateStocastic(modelFile, assignmentfp, STLAdaptor.getMaxTime(jobstl), 1, 1, runCount);
                        SMC smc = STLAdaptor.smc(jobstl, assignmentfp, plot, runCount, confidence);
                        double perc = smc.getSatisfactionPercentage();
                        double error = smc.getError();
                        double lower = perc - error;
                        storeslines.add(i + "," + perc + "," + error);
                        if (lower >= threshold) {
                            bestlist.add(i);
                            smccount++;
                        }
                        break;
                }
            }
            switch (simulation) {
                case DETERMINISTIC:
                    System.out.println(robcount + " assignments satisfy the performance specification");
                    System.out.println("List of assignments that satisfy the performance spec: " + bestlist);
                    System.out.println("Best Result is at assignment: " + bestindex + " with robustness = " + bestval);
                    String detresfp = jobfp + "results" + Utilities.getSeparater() + "deterministic" + Utilities.getSeparater();
                    System.out.println("All assignments and results can be found in :" + (detresfp));
                    Utilities.writeToFile(detresfp + "result.csv", detreslines);
                    break;
                case STOCHASTIC:
                    System.out.println(smccount + " assignments have a score greater than or equal to : " + threshold);
                    System.out.println("List of assignments with a score greater than or equal to : " + threshold + " : " + bestlist);
                    String storesfp = (jobfp + "results" + Utilities.getSeparater() + "stochastic" + Utilities.getSeparater());
                    System.out.println("All assignments and results can be found in :" + storesfp);
                    Utilities.writeToFile(storesfp + "result.csv", storeslines);
                    break;
            }

        } catch (SBOLValidationException | IOException | SBOLConversionException | XMLStreamException | SBMLException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Module getBestModule(List<Module> modules) {
        List<Module> sorted = FailureModeGrammar.sortByFailureModes(modules);
        return sorted.get(0);
    }
    
    private int getNextPositiveId() {
        int id = ThreadLocalRandom.current().nextInt();
        while (id <= 0) {
            id = ThreadLocalRandom.current().nextInt();
        }
        return id;
    }

    private void createJob() {

        String id = "job" + getNextPositiveId();
        String jobFolder = (this.projectFolder + id);
        while (Utilities.isDirectory(jobFolder)) {
            id = "job" + getNextPositiveId();
            jobFolder = (this.projectFolder + id);
        }
        Utilities.makeDirectory(jobFolder);
        this.jobId = id;
    }

    public static JSONArray getProjects(String userid) {
        JSONArray projects = new JSONArray();
        String userFP = Utilities.getResultsFilepath() + userid + Utilities.getSeparater();
        File root = new File(userFP);
        File[] list = root.listFiles();
        for (File f : list) {
            if (!f.getName().equals(".DS_Store")) { // Compatiable for mac
                String projfp = f.getAbsolutePath();
                if (!projfp.endsWith("" + Utilities.getSeparater())) {
                    projfp += Utilities.getSeparater();
                }
                projects.put(new JSONObject(Utilities.getFileContentAsString(projfp + "details.json")));
            }
        }
        return projects;
    }

    private String createJobUUID(String userid) {
        String userFP = Utilities.getResultsFilepath() + userid + Utilities.getSeparater();
        String uuid = Utilities.randomString(8);
        while (Utilities.validFilepath(userFP + uuid)) {
            uuid = Utilities.randomString(8);
        }
        Utilities.makeDirectory(userFP + uuid);
        return uuid;
    }

    //<editor-fold desc="Constructors and functions for webapp">
    public PhoenixProject(String _userid, String projectName, String stl, String eugeneCode, String registry, String collection, int runCount, double confidence, double threshold) throws IOException, SBOLConversionException {

        this.userid = _userid;
        this.jobId = createJobUUID(_userid);
        String userRootFP = Utilities.getResultsFilepath() + _userid + Utilities.getSeparater();
        this.projectFolder = userRootFP;
        String jobfp = this.projectFolder + this.jobId + Utilities.getSeparater();

        JSONObject details = new JSONObject();
        details.put("id", this.jobId);
        details.put("projectName", projectName);
        details.put("createdOn", Instant.now().toString());
        details.put("step", Step.SPECIFY);
        details.put("state", State.INPROGRESS);
        Utilities.writeToFile(jobfp + "details.json", details.toString());

        JSONObject lib = new JSONObject();
        lib.put("database", "synbiohub");
        lib.put("registry", registry);
        lib.put("collection", collection);

        Utilities.writeToFile(jobfp + "stl.txt", stl);
        Utilities.writeToFile(jobfp + "library.json", lib.toString());
        Utilities.writeToFile(jobfp + "structure.eug", eugeneCode);

        List<String> eugfilelines = Utilities.getFileContentAsStringList(jobfp + "structure.eug");
        JSONObject eug = new JSONObject();

        // I don't like this at all.....
        if (eugfilelines.size() > 1) {
            String line0 = eugfilelines.get(0);
            if (line0.startsWith("//")) {
                line0 = line0.substring(2);
                if (line0.trim().toLowerCase().startsWith("size=")) {
                    line0 = line0.substring(5);
                    eug.put("size", Integer.valueOf(line0.trim()));
                }
                if (line0.trim().toLowerCase().startsWith("solutions=")) {
                    line0 = line0.substring(10);
                    eug.put("solutions", Integer.valueOf(line0.trim()));
                }
            } else {
                //???
            }
            String line1 = eugfilelines.get(1);
            if (line1.startsWith("//")) {
                line1 = line1.substring(2);
                if (line1.trim().toLowerCase().startsWith("size=")) {
                    line1 = line1.substring(5);
                    eug.put("size", Integer.valueOf(line1.trim()));
                }
                if (line1.trim().toLowerCase().startsWith("solutions=")) {
                    line1 = line1.substring(10);
                    eug.put("solutions", Integer.valueOf(line1.trim()));
                }
            } else {
                //???
            }
        } else {
            //???
        }
        Utilities.writeToFile(jobfp + "eug.json", eug.toString());

        SBOLDocument sbol = SynbiohubAdaptor.getSBOL(registry, collection);
        SBOLWriter.write(sbol, (jobfp + "sbol.xml"));
        
        this.args = new Args(Decomposition.PR_C_T,Simulation.STOCHASTIC, runCount, confidence, threshold, Assignment.EXHAUSTIVE);
        
        details.put("state", State.COMPLETED);
        Utilities.writeToFile(jobfp + "details.json", details.toString());
    }

    public void design() throws IOException, SBOLValidationException, SBOLConversionException, InterruptedException, URISyntaxException {
        String jobfp = this.projectFolder + this.jobId + Utilities.getSeparater();

        JSONObject details = new JSONObject(Utilities.getFileContentAsString(jobfp + "details.json"));
        details.put("step", Step.DESIGN);
        details.put("state", State.INPROGRESS);
        Utilities.writeToFile(jobfp + "details.json", details.toString());

        SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
        Library lib = new Library(sbol, this.args.getDecomposition(),jobfp);
        String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
        JSONObject eug = new JSONObject(eugfilecontent);
        int eugCircSize;
        Integer eugNumSolutions = null;

        if (eug.has("solutions")) {
            eugNumSolutions = eug.getInt("solutions");
        }
        eugCircSize = eug.getInt("size");
        String eugfp = jobfp + "structure.eug";
        TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");

        List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, this.jobId);

        List<Module> decomposedModules = new ArrayList<Module>();
        for (Module m : modules) {
            decomposedModules.add(Controller.decompose(m, args.getDecomposition()));
        }

        JSONArray arr = new JSONArray();

        for (Module m : decomposedModules) {
            //Controller.assignTUCandidates(m, lib, sbol);
            arr.put(UIAdaptor.getModuleJSON(m));
        }

        Utilities.writeToFile(jobfp + "design.json", arr.toString());
        details.put("state", State.COMPLETED);
        Utilities.writeToFile(jobfp + "details.json", details.toString());
    }

    public static JSONArray getDesignArray(String username, String projectname) {
        String jobfolder = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        String projectString = Utilities.getFileContentAsString(jobfolder + "design.json");
        if (projectString != "") {
            return new JSONArray(projectString);
        }
        return null;
    }
    
    public static JSONArray getResultsArray(String username, String projectname) {
        String jobfolder = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        String projectString = Utilities.getFileContentAsString(jobfolder + "results.json");
        if (projectString != "") {
            return new JSONArray(projectString);
        }
        return null;
    }

    //</editor-fold>
    
    

    public static enum State {

        COMPLETED,
        INPROGRESS,
        ERROR
    }

    public static enum Step {

        SPECIFY,
        DESIGN,
        RESULTS
    }

}
