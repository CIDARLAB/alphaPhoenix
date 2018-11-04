/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.simulation;

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
import javax.xml.stream.XMLStreamException;
import org.apache.commons.io.FileUtils;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor.Axis;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.DnaPlotlibAdaptor;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.dom.AssignmentNode;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import static org.cidarlab.phoenix.utils.Args.Simulation.DETERMINISTIC;
import static org.cidarlab.phoenix.utils.Args.Simulation.STOCHASTIC;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLWriter;

/**
 *
 * @author prash
 */
public class ExhaustiveSimulation extends AbstractSimulation {
    
    public static List<AssignmentNode> run(Module module, Library library, TreeNode stl, Args args, int moduleIndex, String fp) throws URISyntaxException, MalformedURLException, XMLStreamException, FileNotFoundException, IOException, TLIException, InterruptedException {
        List<AssignmentNode> nodes = new ArrayList<>();
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
                //System.out.println("Current Assignment : " + count + " has no small molecules.");
                //printAssignment(module,assignment);
                
                String ifp = fp + count + Utilities.getSeparater();
                Utilities.makeDirectory(ifp);
                
                SBMLWriter writer = new SBMLWriter();
                String modelFile = ifp + "model.xml";
                writer.write(module.getModel().getSbml(), modelFile);
                
                //double score = 0;
                double score = runSimulation(module, assignment, ioc, library, stl, modelFile, args, ifp);
                //System.out.println("Score = " + score);
                boolean result = (score >= args.getThreshold());
                if(result){
                    AssignmentNode an = new AssignmentNode(module, assignment, ioc, library);
                    an.setModuleIndex(moduleIndex);
                    an.setAssignmentIndex(count);
                    an.setScore(score);
                    
                    Utilities.writeToFile(ifp + "assignmentDetails.json", an.getDetails(library).toString());
                    
                    //an.setFilepath(ifp);
                    nodes.add(an);
                    System.out.println("Current Assignment : " + count);
                    System.out.println(an.toString(library));
                } else {
                    FileUtils.deleteDirectory(new File(ifp));
                }
                
                
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
                    
                    //System.out.println("Current Assignment : " + count);
                    //printAssignment(module, assignment);
                    
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
                    
                    //double  score = 0.0;
                    double  score = runSimulation(module, assignment, ioc, library, stl, modelFile, args, ifp);
                    boolean result = (score >= args.getThreshold());
                    if(result){
                        AssignmentNode an = new AssignmentNode(module, assignment, ioc, conc, library);
                        an.setModuleIndex(moduleIndex);
                        an.setAssignmentIndex(count);
                        an.setScore(score);
                        
                        Utilities.writeToFile(ifp + "assignmentDetails.json", an.getDetails(library).toString());
                    
                        //an.setFilepath(ifp);
                        nodes.add(an);
                        System.out.println("Current Assignment : " + count);
                        System.out.println(an.toString(library));
                
                    } else {
                        FileUtils.deleteDirectory(new File(ifp));
                    }
                    
                    count++;
                }
            }
        }
        
        System.out.println("Total number of assignments      : " + count);
        System.out.println("Number of assignments with SM    : " + hasSM);
        System.out.println("Number of assignments with no SM : " + noSM);
        System.out.println("SM Counts");
        for(Integer i:smCounts.keySet()){
            System.out.println("Number of assignments with " + i + " SM(s) : " + smCounts.get(i));
        }
        return nodes;
    }
    
    
    
    private static double runSimulation(Module module, Map<String, CandidateComponent> assignment, Map<String, String> ioc, Library library, TreeNode stl, String modelFile, Args args, String ifp) throws InterruptedException, IOException, TLIException {
        
        String dnaplotlibfp = ifp + "visualsbol.py";
        String dnaplotlibscript = DnaPlotlibAdaptor.generateScript(module, assignment, ioc, new HashMap<String, String>(), library, ifp + "circuit");
        Utilities.writeToFile(dnaplotlibfp, dnaplotlibscript);
        Utilities.runPythonScript(dnaplotlibfp);
        
        double stlmaxtime = (STLAdaptor.getMaxTime(stl));
        double maxtime = stlmaxtime + 600;
        
        Map<String, TreeNode> stlmap = STLAdaptor.getSignalSTLMap(stl);
        Map<String, List<Signal>> allsignals = new HashMap<>();
                
        
        if (args.getSimulation().equals(STOCHASTIC)) {
            IBioSimAdaptor.simulateStocastic(modelFile, ifp, maxtime, 10, 10, args.getRunCount());
            for (int i = 1; i <= args.getRunCount(); i++) {
                String tsdfp = ifp + "run-" + i + ".csv";
                Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
                for (String key : stlmap.keySet()) {
                    if (signalMap.containsKey(key)) {
                        Signal s = getSteadyState(signalMap.get(key));
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
            
        } else if (args.getSimulation().equals(DETERMINISTIC)) {
            IBioSimAdaptor.simulateODE(modelFile, ifp, maxtime, 10, 10);
            String tsdfp = ifp + "run-1.csv";
            Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
            for (String key : stlmap.keySet()) {
                if (signalMap.containsKey(key)) {
                    Signal s = getSteadyState(signalMap.get(key));
                    if (allsignals.containsKey(key)) {
                        allsignals.get(key).add(s);
                    } else {
                        allsignals.put(key, new ArrayList<Signal>());
                        allsignals.get(key).add(s);
                    }
                }
            }    
        }
        
        double score = Double.MAX_VALUE;
        
        
        
        
        for (String key : allsignals.keySet()) {
            if (args.getSimulation().equals(DETERMINISTIC)) {
                double perc = STLAdaptor.getRobustness(stl, allsignals.get(key).get(0),0);
                if (perc < score) {
                    score = perc;
                }
            } else if (args.getSimulation().equals(STOCHASTIC)) {
                double perc = STLAdaptor.computeSatisfyingPercent(allsignals.get(key), stl);
                if (perc < score) {
                    score = perc;
                }
            }
            
            Utilities.writeSignalsToCSV(allsignals.get(key), ifp + key + ".csv");
            List<String> pylines = PyPlotAdaptor.generateSignalPlotScript(allsignals.get(key), ifp + key + ".png", 0, stlmaxtime, 0, 1000000, Axis.LINEAR, Axis.SYMLOG, "MEFL");
            Utilities.writeToFile(ifp + key + "_signals.py", pylines);
            PyPlotAdaptor.runScript(ifp + key + "_signals.py");
        }
        return score;
        
       
    }
    
}
