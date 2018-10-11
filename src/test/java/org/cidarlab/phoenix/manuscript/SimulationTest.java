/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.manuscript;

import hyness.stl.AlwaysNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.simulation.AbstractSimulation;
import static org.cidarlab.phoenix.utils.Args.Simulation.DETERMINISTIC;
import static org.cidarlab.phoenix.utils.Args.Simulation.STOCHASTIC;
import org.cidarlab.phoenix.utils.CrawlerTest;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class SimulationTest {
    
    private static void runStochastic(String ifp, String modelFile, TreeNode stl, int runCount) throws TLIException, IOException, InterruptedException{
        
        double stlmaxtime = (STLAdaptor.getMaxTime(stl));
        double maxtime = stlmaxtime + 600;

        Map<String, TreeNode> stlmap = STLAdaptor.getSignalSTLMap(stl);
        Map<String, List<Signal>> allsignals = new HashMap<>();

        IBioSimAdaptor.simulateStocastic(modelFile, ifp, maxtime, 10, 10, runCount);
        for (int i = 1; i <= runCount; i++) {
            String tsdfp = ifp + "run-" + i + ".csv";
            Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
            for (String key : stlmap.keySet()) {
                if (signalMap.containsKey(key)) {
                    Signal s = AbstractSimulation.getSteadyState(signalMap.get(key));
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
            List<String> pylines = PyPlotAdaptor.generateSignalPlotScript(allsignals.get(key), ifp + key + ".png", 0, stlmaxtime, 0, 1000000, PyPlotAdaptor.Axis.LINEAR, PyPlotAdaptor.Axis.SYMLOG, "MEFL");
            Utilities.writeToFile(ifp + key + "_signals.py", pylines);
            PyPlotAdaptor.runScript(ifp + key + "_signals.py");
        }
        
        

    }
    
    
    public static void main(String[] args) throws TLIException, IOException, InterruptedException {
        String libfp = Utilities.getLibFilepath();
        String casestudyfp = libfp + "caseStudy" + Utilities.getSeparater();
        
        String nsffp = casestudyfp + "nsfReviewMeeting" + Utilities.getSeparater();
        Utilities.makeDirectory(nsffp);
        
        
        int runCount = 100;
        
        String motivationfp = nsffp + "motivationfp" + Utilities.getSeparater();
        Utilities.makeDirectory(motivationfp);
        
        
        String pulsebasefp = Utilities.getLibFilepath() 
                + "examples" + Utilities.getSeparater() 
                + "tested_circuits" + Utilities.getSeparater() 
                + "pulse_circuit" + Utilities.getSeparater() 
                + "allRuns" + Utilities.getSeparater()
                + "DeterministicRed0" + Utilities.getSeparater()
                + "0" + Utilities.getSeparater();
        
        int[] indicesPulse = {8,15,45,46,47,62};
        
        AlwaysNode stl = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GE,"out0",0),0,600);
        
        /*for(int i:indicesPulse){
            String ifp = motivationfp + i + Utilities.getSeparater();
            Utilities.makeDirectory(ifp);
            
            String modelfpsrc = pulsebasefp + i + Utilities.getSeparater() + "model.xml";
            
            String modelfp = ifp + "model.xml";
            
            FileUtils.copyFile(new File(modelfpsrc), new File(modelfp));
            
            System.out.println(modelfp);
            runStochastic(ifp,modelfp,stl,runCount);
        }*/
        
        
        String twoTUbasefp = Utilities.getLibFilepath() 
                + "examples" + Utilities.getSeparater() 
                + "tested_circuits" + Utilities.getSeparater() 
                + "circuits" + Utilities.getSeparater() 
                + "2tu" + Utilities.getSeparater() 
                + "allRuns" + Utilities.getSeparater()
                + "DeterministicRed0" + Utilities.getSeparater()
                + "0" + Utilities.getSeparater();
        String oneTUbasefp = Utilities.getLibFilepath() 
                + "examples" + Utilities.getSeparater() 
                + "tested_circuits" + Utilities.getSeparater() 
                + "circuits" + Utilities.getSeparater() 
                + "1tu" + Utilities.getSeparater() 
                + "allRuns" + Utilities.getSeparater()
                + "DeterministicRed0" + Utilities.getSeparater()
                + "0" + Utilities.getSeparater();
        
        String expfp = nsffp + "experimental" + Utilities.getSeparater();
        Utilities.makeDirectory(expfp);
        
        stl = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GE,"out0",0),0,300);
        
        
        int[] indices2tu = {143,145};
        
        for(int i:indices2tu){
            String ifp = expfp + "2tu_" + i + Utilities.getSeparater();
            Utilities.makeDirectory(ifp);
            
            String modelfpsrc = twoTUbasefp + i + Utilities.getSeparater() + "model.xml";
            
            String modelfp = ifp + "model.xml";
            
            FileUtils.copyFile(new File(modelfpsrc), new File(modelfp));
            
            System.out.println(modelfp);
            runStochastic(ifp,modelfp,stl,runCount);
        }
        
        
        int[] indices1tu = {0,1,2,3,4};
        
        for(int i:indices1tu){
            String ifp = expfp + "1tu_" + i + Utilities.getSeparater();
            Utilities.makeDirectory(ifp);
            
            String modelfpsrc = oneTUbasefp + i + Utilities.getSeparater() + "model.xml";
            
            String modelfp = ifp + "model.xml";
            
            FileUtils.copyFile(new File(modelfpsrc), new File(modelfp));
            
            System.out.println(modelfp);
            runStochastic(ifp,modelfp,stl,runCount);
        }
        
    }
    
}
