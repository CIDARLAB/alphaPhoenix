/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import hyness.stl.grammar.sharp.STLSharp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.library.Library;
import org.cidarlab.phoenix.utils.Utilities;
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
    private String resultsFolder;
    
    @Getter
    private String jobId;
    
    @Setter
    @Getter
    private Module structure;
    
    @Setter
    @Getter
    private STLSharp stl;
    
    public String getJobFolder(){
        return (resultsFolder + jobId);
    }
    
    public PhoenixProject(){
        this.resultsFolder = Utilities.getFilepath() + Utilities.getSeparater() + "results" + Utilities.getSeparater();
        if(!Utilities.isDirectory(this.resultsFolder)){
            Utilities.makeDirectory(this.resultsFolder);
        }
        createJob();
    }
    
    public PhoenixProject(String _resultsFolder){
        if(!Utilities.isDirectory(_resultsFolder)){
            System.err.println("Error! Directory " + _resultsFolder + " does not exist");
            System.exit(-1);
        }
        this.resultsFolder = _resultsFolder;
        createJob();
    }
    
    public PhoenixProject(String eugfp, int eugCircSize, Integer eugNumSolutions, String stlfp,String libraryfp, Simulation simulation, boolean plot){
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
            if(! (eugNumSolutions == null)){
                euginfo.put("numSol", eugNumSolutions);
            } 
            euginfo.put("solSize", eugCircSize);
            Utilities.writeToFile(jobfp + "eug.json", euginfo.toString());
            
            //Create STL file
            FileUtils.copyFile(new File(stlfp), new File(jobfp + "stl.txt"));
            
            //Create Library file
            FileUtils.copyFile(new File(libraryfp), new File(jobfp + "library.json"));
            
            String libfilecontent = Utilities.getFileContentAsString(jobfp + "library.json");
            JSONObject lib = new JSONObject(libfilecontent);
            if(lib.has("database")){
                if(lib.getString("database").equals("synbiohub")){
                    if((!lib.has("registry")) || (!lib.has("collection"))){
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
                System.out.println(libraryfp  + " is missing certain fields.");
                System.exit(-1);
            }
            
            PhoenixProject.execute(jobId,simulation, plot);
            
        } catch (IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void execute(String jobid, Simulation simulation, boolean plot){
        try {
            String jobfp = Utilities.getResultsFilepath() + jobid + Utilities.getSeparater();
            if(!Utilities.validFilepath(jobfp)){
                System.out.println("Sorry. " + jobid + " does not exist. Please ensure that the job has been created.");
                System.exit(-1);
            }
            SBOLDocument sbol = SBOLReader.read(jobfp + "sbol.xml");
            Library lib = new Library(sbol);
            String eugfilecontent = Utilities.getFileContentAsString(jobfp + "eug.json");
            JSONObject eug = new JSONObject(eugfilecontent);
            int eugCircSize; 
            Integer eugNumSolutions = null;
            
            if(eug.has("numSol")){
                eugNumSolutions = eug.getInt("numSol");
            }
            eugCircSize = eug.getInt("solSize");
            String eugfp = jobfp + "structure.eug";
            TreeNode jobstl = STLAdaptor.getSTL(jobfp + "stl.txt");
            
            List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, eugNumSolutions, jobid);
            Module best = getBestModule(modules);
            Module decomposedModule = Controller.decompose(PhoenixMode.MM, best);
            List<Map<String,CandidateComponent>> assignments = Controller.assign(decomposedModule, lib, sbol);
            System.out.println("");
            double bestval = Double.MIN_VALUE;
            int bestindex = 0;
            System.out.println(assignments.size() + " possible assignments found.");
            for (int i = 0; i < assignments.size(); i++) {
                Map<String, CandidateComponent> assignment = assignments.get(i);
                System.out.print(i + ":");
//                for (Component c : decomposedModule.getComponents()) {
//                    System.out.print(assignment.get(c.getName()).getCandidate().getDisplayId() + ";");
//                }
//                System.out.println("");
            

                Controller.assignLeafModels(PhoenixMode.MM, decomposedModule, jobid, sbol, assignment);
                Controller.composeModels(PhoenixMode.MM, decomposedModule, jobid, assignment);
                String jobresultsfp;
                jobresultsfp = jobfp + "results" + Utilities.getSeparater();
                if (!Utilities.validFilepath(jobresultsfp)) {
                    System.out.println("Results will be stored in : " + jobresultsfp);
                    Utilities.makeDirectory(jobresultsfp);
                }
                String assignmentfp;
                String modelFile;
                SBMLWriter writer = new SBMLWriter();
                switch(simulation){
                    case DETERMINISTIC:
                        System.out.println("Starting Deterministic Simulation for assignment " + i);
                         
                        String deterministic = jobresultsfp + "deterministic" + Utilities.getSeparater();
                        Utilities.makeDirectory(deterministic);
                        assignmentfp = deterministic + i + Utilities.getSeparater();
                        Utilities.makeDirectory(assignmentfp);
                        modelFile = assignmentfp + "model.xml";
                        writer.write(decomposedModule.getModel().getSbml(), modelFile);
                        double maxtime = STLAdaptor.getMaxTime(jobstl);
                        IBioSimAdaptor.simulateODE(modelFile, assignmentfp, maxtime, 1, 1);
                        String tsdfp = assignmentfp + "run-1.csv";
                        double robval = STLAdaptor.getRobustness(jobstl, tsdfp, assignmentfp, plot);
                        System.out.println("Robustness at "  + i + " is " + robval);
                        if (robval > bestval) {
                            bestval = robval;
                            bestindex = i;
                        }
                        break;
                    case STOCHASTIC:
                        String stochastic = jobresultsfp + "deterministic" + Utilities.getSeparater();
                        Utilities.makeDirectory(stochastic);
                        assignmentfp = stochastic + i + Utilities.getSeparater();
                        Utilities.makeDirectory(assignmentfp);
                        modelFile = assignmentfp + "model.xml";
                        writer.write(decomposedModule.getModel().getSbml(), modelFile);
                        IBioSimAdaptor.simulateStocastic(modelFile, assignmentfp, STLAdaptor.getMaxTime(jobstl), 1, 1,100);
                        break;
                }
                
            
            }
            switch(simulation){
                case DETERMINISTIC:
                    System.out.println("Best Result is at " + bestindex +  " with robustness = " + bestval);
                    break;
                case STOCHASTIC:
                    System.out.println("Best Result is at " + bestindex +  " with robustness = " + bestval);
                    break;
            }
            
            
        } catch (SBOLValidationException | IOException | SBOLConversionException | XMLStreamException | SBMLException ex) {
            Logger.getLogger(PhoenixProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Module getBestModule(List<Module> module){
        //Change this!
        return module.get(0);
    }
    
    private int getNextPositiveId(){
        int id = ThreadLocalRandom.current().nextInt();
        while(id <= 0){
            id = ThreadLocalRandom.current().nextInt();
        }
        return id;
    }
    
    private void createJob(){
        
        String id = "job" + getNextPositiveId();
        String jobFolder = (this.resultsFolder + id);
        while(Utilities.isDirectory(jobFolder)){
            id = "job" + getNextPositiveId();
            jobFolder = (this.resultsFolder + id);
        }
        Utilities.makeDirectory(jobFolder);
        this.jobId = id;
    }
    
    public static enum Simulation{
        DETERMINISTIC,
        STOCHASTIC
    }
    
}
