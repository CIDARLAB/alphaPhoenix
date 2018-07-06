/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class SampleCircuit3TUTest {
    
    private static String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static String sampleCircuitsFP = tested_circuitsFP + "sample_circuits" + Utilities.getSeparater();
    
    private static String one_tuFP = sampleCircuitsFP + "1tu" + Utilities.getSeparater();
    private static String two_tuFP = sampleCircuitsFP + "2tu" + Utilities.getSeparater();
    private static String three_tuFP = sampleCircuitsFP + "3tu" + Utilities.getSeparater();
    
    
    private static String three_tu_eug = three_tuFP + "tripleTU.eug";

}
