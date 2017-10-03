/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import edu.utah.ece.async.ibiosim.analysis.simulation.flattened.SimulatorODERK;
import edu.utah.ece.async.ibiosim.analysis.simulation.flattened.SimulatorSSADirect;
import edu.utah.ece.async.ibiosim.dataModels.util.dataparser.TSDParser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.tli.Utilities;

/**
 *
 * @author prash
 */
public class IBioSimAdaptor {   
    
    /**
     * Runs an ODE simulation of the model specified in SBMLFileName.
     *
     * @param SBMLFileName - the SBML model to be simulated
     * @param outDir - the output directory
     * @param timeLimit - the time limit of the simulation
     * @param timeStep - the time step of the simulation
     * @param printInterval - how often the simulation data should be written to
     * the output
     * @param rndSeed - a random seed for the simulation
     * @param stoichAmpValue - stoichiometry amplification value
     * @param numSteps - number of steps to make in the simulation
     * @param relError - relative error
     * @param absError - absolute error
     * @throws IOException
     */
    public static void simulateODE(String SBMLFileName, String outDir, double timeLimit,
            double timeStep, double printInterval, long rndSeed, double stoichAmpValue,
            int numSteps, double relError, double absError) throws IOException {
        SimulatorODERK simulator = new SimulatorODERK(SBMLFileName, outDir, 1, timeLimit,
                timeStep, rndSeed, printInterval, stoichAmpValue, new String[0], numSteps,
                relError, absError, "amount");
        simulator.simulate();
        TSDParser tsdParser = new TSDParser(outDir + "run-1.tsd", false);
        tsdParser.outputCSV(outDir + "run-1.csv");
        new File(outDir + "run-1.tsd").delete();
    }

    /**
     * Runs an ODE simulation of the model specified in SBMLFileName.
     *
     * @param SBMLFileName - the SBML model to be simulated
     * @param outDir - the output directory
     * @param timeLimit - the time limit of the simulation
     * @param timeStep - the time step of the simulation
     * @param printInterval - how often the simulation data should be written to
     * the output
     * @throws IOException
     */
    public static void simulateODE(String SBMLFileName, String outDir, double timeLimit,
            double timeStep, double printInterval) throws IOException {
        simulateODE(SBMLFileName, outDir, timeLimit, timeStep, printInterval, ThreadLocalRandom.current().nextLong(), 2.0, 50, 1e-6, 1e-9);
    }

    /**
     * Runs a stochastic simulation of the model specified in SBMLFileName.
     *
     * @param SBMLFileName - the SBML model to be simulated
     * @param outDir - the output directory
     * @param timeLimit - the time limit of the simulation
     * @param timeStep - the time step of the simulation
     * @param printInterval - how often the simulation data should be written to
     * the output
     * @param numRuns - number of runs to perform
     * @param minTimeStep - the minimum time step of the simulation
     * @param rndSeed - a random seed for the simulation
     * @param stoichAmpValue - stoichiometry amplification value
     * @throws IOException
     */
    public static void simulateStocastic(String SBMLFileName, String outDir,
            double timeLimit, double timeStep, double printInterval, int numRuns,
            double minTimeStep, long rndSeed, double stoichAmpValue) throws IOException {
        SimulatorSSADirect simulator = new SimulatorSSADirect(SBMLFileName, outDir, numRuns,
                timeLimit, timeStep, minTimeStep, rndSeed, printInterval, stoichAmpValue,
                new String[0], "amount");
        simulator.simulate();
        TSDParser tsdParser = new TSDParser(outDir + "run-1.tsd", false);
        tsdParser.outputCSV(outDir + "run-1.csv");
        for (int i = 2; i <= numRuns; i ++) {
            simulator = new SimulatorSSADirect(SBMLFileName, outDir, numRuns,
                timeLimit, timeStep, minTimeStep, rndSeed, printInterval, stoichAmpValue,
                new String[0], "amount");
            simulator.setupForNewRun(i);
            simulator.simulate();
            tsdParser = new TSDParser(outDir + "run-" + i + ".tsd", false);
            tsdParser.outputCSV(outDir + "run-" + i + ".csv");
            new File(outDir + "run-" + i + ".tsd").delete();
        }
        new File(outDir + "run-1.tsd").delete();
    }

    /**
     * Runs a stochastic simulation of the model specified in SBMLFileName.
     *
     * @param SBMLFileName - the SBML model to be simulated
     * @param outDir - the output directory
     * @param timeLimit - the time limit of the simulation
     * @param timeStep - the time step of the simulation
     * @param printInterval - how often the simulation data should be written to
     * the output
     * @param numRuns - number of runs to perform
     * @throws IOException
     */
    public static void simulateStocastic(String SBMLFileName, String outDir,
            double timeLimit, double timeStep, double printInterval, int numRuns) throws IOException {
        simulateStocastic(SBMLFileName, outDir, timeLimit, timeStep, printInterval, numRuns, 0.0, ThreadLocalRandom.current().nextLong(), 2.0);
    }
    
    public static Map<String,Signal> getSignals(String filepath){
        List<String[]> lines = Utilities.getCSVFileContentAsList(filepath);
        String[] header = lines.get(0);
        Map<Integer,String> sigMap = new HashMap<>();
        Map<String,List<Double>> vals = new HashMap<>();
        for(int i=0;i<header.length;i++){
            sigMap.put(i, header[i].trim());
            vals.put(header[i].trim(), new ArrayList<Double>());
        }
        for(int i=1;i<lines.size();i++){
            String[] line = lines.get(i);
            for(int j=0;j<line.length;j++){
                vals.get(sigMap.get(j)).add(Double.valueOf(line[j]));
            }
        }
        Map<String,Signal> finalsignals = new HashMap<>();
        Map<String,List<Double>> signalVals = new HashMap<>();
        List<Double> timepoints = new ArrayList<>();
        for(String key:vals.keySet()){
            if(key.equals("time")){
                timepoints.addAll(vals.get(key));
            } else {
                signalVals.put(key, vals.get(key));
            }
        }
        for(String sig:signalVals.keySet()){
            List<Point> points = new ArrayList<>();
            for(int i=0;i<timepoints.size();i++){
                points.add(new Point(timepoints.get(i),"time",signalVals.get(sig).get(i),sig));
            }
            finalsignals.put(sig, new Signal(points));
        }
        return finalsignals;
    }
    
}
