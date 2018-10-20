/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
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
            //resultsObj.put("img", "/sbol/" + this.userid + "/" + this.jobId + "/" + an.getModuleIndex() + "/" + an.getAssignmentIndex() + "/" + "circuit");
            resultsObj.put("moduleid", an.getModuleIndex());
            resultsObj.put("assignmentid", an.getAssignmentIndex());
            resultsObj.put("name", "Module " + an.getModuleIndex() + " Assignment " + an.getAssignmentIndex());
            resultsObj.put("score", an.getScore());
            resultsObj.put("imageSize", an.getComponents().size());
            
            //JSONArray tracesArr = new JSONArray();
            //resultsObj.put("traces", tracesArr);
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
        throw new UnsupportedOperationException("Not supported yet.");
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
    
    public static JSONObject getSpecificationObjects(String username, String projectname){
        JSONObject obj = new JSONObject();
        String projectfp = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        
        if((!Utilities.validFilepath(projectfp + "library.json")) || (!Utilities.validFilepath(projectfp + "structure.eug")) || (!Utilities.validFilepath(projectfp + "stl.txt"))){
            return null;
        }
        String libJsonText = Utilities.getFileContentAsString(projectfp + "library.json").trim();
        JSONObject lib = new JSONObject(libJsonText);
        obj.put("registry", lib.get("registry"));
        obj.put("collection", lib.get("collection"));
        obj.put("database", lib.get("database"));
        obj.put("stl", Utilities.getFileContentAsString(projectfp + "stl.txt"));
        obj.put("eugene", Utilities.getFileContentAsString(projectfp + "structure.eug"));
        return obj;
    }
    
    public static JSONArray getDesignArray(String username, String projectname) {
        String jobfolder = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        String projectString = Utilities.getFileContentAsString(jobfolder + "design.json");
        if (!projectString.equals("")) {
            return new JSONArray(projectString);
        }
        return null;
    }
    
    public static JSONArray getResultsArray(String username, String projectname) {
        String jobfolder = Utilities.getResultsFilepath() + username + Utilities.getSeparater() + projectname + Utilities.getSeparater();
        String projectString = Utilities.getFileContentAsString(jobfolder + "results.json");
        if (!projectString.equals("")) {
            return new JSONArray(projectString);
        }
        return null;
    }
    
    static SecureRandom rnd = new SecureRandom();
    
    public static String generateNewColor(List<String> colors){
        String newColor = "";
        String AB = "0123456789ABCDEF";
        do{
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(AB.charAt(rnd.nextInt(AB.length())));
            }
            newColor = sb.toString() + "AA";
        }while(colors.contains(newColor));
    
        return newColor;
    }
    
    
    public static String downloadAssignment(String userId, String projectId, int moduleId, int assignmentId) throws FileNotFoundException, IOException  {
        
        
        String afp = Utilities.getResultsFilepath() + userId + Utilities.getSeparater() + projectId + Utilities.getSeparater() + "results" + Utilities.getSeparater() + moduleId + Utilities.getSeparater() + assignmentId + Utilities.getSeparater();
        
        String zfilename = afp + "assignment" + assignmentId + ".zip";
        
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(new File(zfilename)));
        
        File[] filelist = (new File(afp)).listFiles();
        for (File f : filelist) {
            if(f.getName().equals("assignment" + assignmentId + ".zip")){
                continue;
            }
            ZipEntry ze = new ZipEntry(f.getName());
            zout.putNextEntry(ze);
            byte[] bytes = Files.readAllBytes(f.toPath());
            zout.write(bytes, 0, bytes.length);
            zout.closeEntry();
        }
        zout.close();
        
        
        return zfilename;
    }

    
    
    
    public static JSONObject getAssignmentObject(String userId, String projectId, int moduleId, int assignmentId) throws TLIException {
        List<String> colors = new ArrayList<>();
        colors.add("#2196F3AA");
        colors.add("#009900AA");
        colors.add("#FF0000AA");
        colors.add("#FF9900AA");
        colors.add("#00FFCCAA");
        
        
        
        
        String afp = Utilities.getResultsFilepath() + userId + Utilities.getSeparater() + projectId + Utilities.getSeparater() + "results" + Utilities.getSeparater() + moduleId + Utilities.getSeparater() + assignmentId + Utilities.getSeparater();
        JSONObject obj = new JSONObject();
        obj.put("img", "/sbol/" + userId + "/" + projectId + "/" + moduleId + "/" + assignmentId + "/circuit" );
        
        JSONObject details = new JSONObject(Utilities.getFileContentAsString(afp + "assignmentDetails.json"));
        
        obj.put("score", details.get("score"));
        obj.put("components", details.get("components"));
        
        JSONArray concarr = new JSONArray();
        JSONObject concs = ((JSONObject)details.get("smc"));
        for(String smc: concs.keySet()){
            
            concarr.put(smc  + " = " + concs.get(smc));
        }
        
        
        obj.put("scm", concarr);
        
        
        
        File[] filelist = (new File(afp)).listFiles();
        int outCount = 0;
        Map<String,String> signalColorMap = new HashMap<>();
        JSONArray tracesArr = new JSONArray();
        for(File f:filelist){
            int traceCount = 0;
            if(f.getName().startsWith("out") && f.getName().endsWith(".csv")){
                String signalName = f.getName();
                signalName = signalName.substring(0, signalName.lastIndexOf(".csv"));
                
                String currentColor;
                if(outCount < colors.size()){
                   currentColor = colors.get(outCount); 
                } else {
                   currentColor = generateNewColor(colors);
                   colors.add(currentColor);
                }
                outCount++;
                
                List<Signal> signals = Utilities.readSignalsFromCSV(f.getAbsolutePath());
                for(Signal s:signals){
                    JSONObject traceObj = new JSONObject();
                            //traceObj.put("name", "trace" + traceCount++);
                    traceObj.put("name", signalName);
                    traceObj.put("legendgroup", signalName);
                    traceObj.put("type", "scatter");
                    JSONArray xarr = new JSONArray();
                    JSONArray yarr = new JSONArray();
                    for (Point point : s.getPoints()) {
                        xarr.put(point.getX());
                        yarr.put(point.getY());
                    }
                    traceObj.put("x", xarr);
                    traceObj.put("y", yarr);
                    traceObj.put("showlegend", traceCount == 0);
                    JSONObject lineobj = new JSONObject();
                    lineobj.put("color", currentColor);
                    traceObj.put("line", lineobj);

                    tracesArr.put(traceObj);
                    traceCount++;
                }
                
            }
        }
        obj.put("traces", tracesArr);
        
        return obj;
        
        
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
