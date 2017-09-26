/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import edu.utah.ece.async.ibiosim.analysis.simulation.flattened.SimulatorODERK;
import edu.utah.ece.async.ibiosim.analysis.simulation.flattened.SimulatorSSADirect;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

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
        JProgressBar progress = new JProgressBar();
        JFrame running = new JFrame();

        SimulatorODERK simulator = new SimulatorODERK(SBMLFileName, outDir, timeLimit,
                timeStep, rndSeed, progress, printInterval, stoichAmpValue, running,
                new String[0], numSteps, relError, absError, "amount");
        simulator.simulate();
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
     * @param numRuns - Number of runs 
     * @param minTimeStep - the minimum time step of the simulation
     * @param rndSeed - a random seed for the simulation
     * @param stoichAmpValue - stoichiometry amplification value
     * @throws IOException
     */
    public static void simulateStocastic(String SBMLFileName, String outDir,
            double timeLimit, double timeStep, double printInterval, int numRuns,
            double minTimeStep, long rndSeed, double stoichAmpValue) throws IOException {

        JProgressBar progress = new JProgressBar();
        JFrame running = new JFrame();

        SimulatorSSADirect simulator = new SimulatorSSADirect(SBMLFileName, outDir,
                timeLimit, timeStep, minTimeStep, rndSeed, progress, printInterval,
                stoichAmpValue, running, new String[0], "amount");
        for (int i = 2; i <= numRuns; i ++) {
            simulator.simulate();
            simulator.setupForNewRun(i);
        }

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
}
