/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.util.HashMap;
import java.util.Map;
import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class CascadeMinTest {
    private static final String rootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
    private static final String expRootFolder = rootFolder + "cascade_min" + Utilities.getSeparater();
    private static String eugFilepath = expRootFolder + "cascadeMin.eug";
    private static String stlFilepath = expRootFolder + "stl.txt";
    private static String libFilepath = expRootFolder + "lib.json";
    
    @Test
    public void testCascadeMinDeterministic(){
        String eugfp = eugFilepath;
        int eugCircSize = 16;
        Integer eugNumSolutions = 10;
        String stlfp = stlFilepath;
        String libraryfp = libFilepath;
        PhoenixProject.Simulation simulation = PhoenixProject.Simulation.DETERMINISTIC;
        int runCount = 100;
        double confidence = 0.0;
        double threshold = 0.0;
        Map<String,Double> inputMap = new HashMap<String,Double>(); 
        boolean plot = true;
        
        PhoenixProject newProj = new PhoenixProject( eugfp,  eugCircSize,  eugNumSolutions,  stlfp, libraryfp, simulation, runCount, confidence, threshold,  inputMap, plot);
        
    }
    
    @Test
    public void testCascadeMinStochastic(){
        String eugfp = eugFilepath;
        int eugCircSize = 16;
        Integer eugNumSolutions = 10;
        String stlfp = stlFilepath;
        String libraryfp = libFilepath;
        PhoenixProject.Simulation simulation = PhoenixProject.Simulation.STOCHASTIC;
        int runCount = 100;
        double confidence = 0.5;
        double threshold = 0.5;
        Map<String,Double> inputMap = new HashMap<String,Double>(); 
        boolean plot = true;
        
        PhoenixProject newProj = new PhoenixProject( eugfp,  eugCircSize,  eugNumSolutions,  stlfp, libraryfp, simulation, runCount, confidence, threshold,  inputMap, plot);
        
    }
    
    
}
