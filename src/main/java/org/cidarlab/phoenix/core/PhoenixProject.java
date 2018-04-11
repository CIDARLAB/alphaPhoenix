/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.SBOLAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.adaptors.UIAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.failuremode.FailureModeGrammar;
import org.cidarlab.phoenix.library.Library;
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

    public String getJobFolder() {
        if(projectFolder.endsWith("" + Utilities.getSeparater())){
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

    public PhoenixProject(String eugfp, int eugCircSize, Integer eugNumSolutions, String stlfp, String libraryfp, Simulation simulation, int runCount, double confidence, double threshold, Map<String, Double> inputMap, boolean plot) {
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

            PhoenixProject.execute(jobId, simulation, runCount, confidence, threshold, plot, inputMap);

        } catch (IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TLIException ex) {
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

    public static void executeAssignment(String jobid) throws InterruptedException {
        try {
            String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
            if (!Utilities.validFilepath(jobfp)) {
                System.out.println("Sorry. " + jobid + " does not exist. Please ensure that the job has been created.");
                System.exit(-1);
            }
            SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
            Library lib = new Library(sbol);
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
                decomposedModules.add(Controller.decompose(m));
            }

            JSONArray arr = new JSONArray();


            
            for(Module m:decomposedModules){
                Controller.assignTUCandidates(m,lib,sbol);
            }         
            
            for(Module m:decomposedModules){
                arr.put(UIAdaptor.getModuleJSON(m));
            }

            Utilities.writeToFile(jobfp + "design.json", arr.toString(2));

        } catch (SBOLValidationException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SBOLConversionException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void execute(String jobid, Simulation simulation, int runCount, double confidence, double threshold, boolean plot, Map<String, Double> inputMap) throws TLIException {
        try {
            String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
            if (!Utilities.validFilepath(jobfp)) {
                System.out.println("Sorry. " + jobid + " does not exist. Please ensure that the job has been created.");
                System.exit(-1);
            }
            SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
            Library lib = new Library(sbol);
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

            Module decomposedModule = Controller.decompose(best);
            List<Map<String, CandidateComponent>> assignments = Controller.assign(decomposedModule, lib, sbol);

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

                Controller.assignLeafModels(PhoenixMode.MM, decomposedModule, jobid, sbol, assignment);
                Controller.composeModels(PhoenixMode.MM, decomposedModule, jobid, assignment);
                String jobresultsfp;
                jobresultsfp = jobfp + "results" + Utilities.getSeparater();
                if (!Utilities.validFilepath(jobresultsfp)) {
                    Utilities.makeDirectory(jobresultsfp);
                }
                String assignmentfp;
                String modelFile;
                SBMLWriter writer = new SBMLWriter();
                Map<String, TreeNode> stlsigmap = STLAdaptor.getSignalSTLMap(jobstl);
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
                        writer.write(decomposedModule.getModel().getSbml(), modelFile);
                        IBioSimAdaptor.simulateStocastic(modelFile, assignmentfp, STLAdaptor.getMaxTime(jobstl), 1, 1, runCount);
                        Map<String, Double> smc = STLAdaptor.smc(jobstl, assignmentfp, plot, runCount, confidence);
                        double perc = smc.get("perc");
                        double error = smc.get("error");
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
    
    public static JSONArray getProjects(String userid){
        JSONArray projects = new JSONArray();
        String userFP = Utilities.getResultsFilepath() + userid + Utilities.getSeparater();
        File root = new File(userFP);
        File[] list = root.listFiles();
        for (File f : list) {
            String projfp = f.getAbsolutePath();
            if(!projfp.endsWith("" + Utilities.getSeparater())){
                projfp += Utilities.getSeparater();
            }
            projects.put(new JSONObject(Utilities.getFileContentAsString(projfp + "details.json")));
        }
        return projects;
    }
    
    private String createJobUUID(String userid){
        String userFP = Utilities.getResultsFilepath() + userid + Utilities.getSeparater();
        String uuid = Utilities.randomString(8);
        while(Utilities.validFilepath(userFP + uuid)){
            uuid = Utilities.randomString(8);
        }
        Utilities.makeDirectory(userFP + uuid);
        return uuid;
    }
    
    //<editor-fold desc="Constructors and functions for webapp">
    public PhoenixProject(String userid, String projectName, String stl, String eugeneCode, String registry, String collection) throws IOException, SBOLConversionException {
        
        this.jobId = createJobUUID(userid);
        String userRootFP = Utilities.getResultsFilepath() + userid + Utilities.getSeparater();
        this.projectFolder = userRootFP;
        String jobfp = userRootFP + this.jobId + Utilities.getSeparater();
        
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
        
        details.put("state",State.COMPLETED);
        Utilities.writeToFile(jobfp + "details.json", details.toString());
    }

    public void design() throws IOException, SBOLValidationException, SBOLConversionException, InterruptedException {
        String jobfp = this.projectFolder + this.jobId + Utilities.getSeparater();
        
        JSONObject details = new JSONObject(Utilities.getFileContentAsString(jobfp + "details.json"));
        details.put("step", Step.DESIGN);
        details.put("state", State.INPROGRESS);
        Utilities.writeToFile(jobfp + "details.json", details.toString());
        
        SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
        Library lib = new Library(sbol);
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
            decomposedModules.add(Controller.decompose(m));
        }
        
        JSONArray arr = new JSONArray();

        for (Module m : decomposedModules) {
            Controller.assignTUCandidates(m, lib, sbol);
            arr.put(UIAdaptor.getModuleJSON(m));
        }
        
        Utilities.writeToFile(jobfp + "design.json", arr.toString());
        details.put("state", State.COMPLETED);
        Utilities.writeToFile(jobfp + "details.json", details.toString());
    }
    
    public static JSONArray getDesignArray(String username, String projectname){
        String jobfolder = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        return new JSONArray(Utilities.getFileContentAsString(jobfolder + "design.json"));
    }

    //</editor-fold>
    
    public static enum Simulation {

        DETERMINISTIC,
        STOCHASTIC
    }
    
    public static enum State{
        COMPLETED,
        INPROGRESS,
        ERROR
    }
    
    public static enum Step{
        SPECIFY,
        DESIGN,
        RESULTS
    }

}
