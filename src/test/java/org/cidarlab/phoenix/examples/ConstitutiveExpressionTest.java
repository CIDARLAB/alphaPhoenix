/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.core.PhoenixProject.Simulation;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class ConstitutiveExpressionTest {
    
    
    private static String rootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
    private static String expRootFolder = rootFolder + "constitutive_expression" + Utilities.getSeparater();
    private static String sburl = "https://synbiohub.cidarlab.org/public/AlphaTest/AlphaTest_collection/1";
    private static String eugFilepath = expRootFolder + "constitutiveExpression.eug";
    private static String stlFilepath = expRootFolder + "stl.txt";
    private static String libFilepath = expRootFolder + "lib.json";
        
    
    @Test
    public void testConstitutiveExpression(){
        String eugfp = eugFilepath;
        int eugCircSize = 4;
        Integer eugNumSolutions = null;
        String stlfp = stlFilepath;
        String libraryfp = libFilepath;
        Simulation simulation = PhoenixProject.Simulation.DETERMINISTIC;
        int runCount = 100;
        double confidence = 0.0;
        double threshold = 0.0;
        Map<String,Double> inputMap = new HashMap<String,Double>(); 
        boolean plot = true;
        
        PhoenixProject newProj = new PhoenixProject( eugfp,  eugCircSize,  eugNumSolutions,  stlfp, libraryfp, simulation, runCount, confidence, threshold,  inputMap, plot);
        
    }
    
    
    @Test
    public void testMiniEugeneResult(){
        
        List<Module> modules = MiniEugeneAdaptor.getStructures(eugFilepath, 4, "constExpression");
        assertEquals(modules.size(),1);
        Module m = modules.get(0);
        
        Component p = new Component();
        p.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        p.setOrientation(Orientation.FORWARD);
        
        Component r = new Component();
        r.setRole(Component.ComponentRole.RBS);
        r.setOrientation(Orientation.FORWARD);
        
        Component c = new Component();
        c.setRole(Component.ComponentRole.CDS);
        c.setOrientation(Orientation.FORWARD);
        
        Component t = new Component();
        t.setRole(Component.ComponentRole.TERMINATOR);
        t.setOrientation(Orientation.FORWARD);
        
        assertEquals(m.getComponents().get(0).getRole(),Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        assertEquals(m.getComponents().get(1).getRole(),Component.ComponentRole.RBS);
        assertEquals(m.getComponents().get(2).getRole(),Component.ComponentRole.CDS);
        assertEquals(m.getComponents().get(3).getRole(),Component.ComponentRole.TERMINATOR);
        
        String str = "f" + p.getRole().toString() + ";" + "f" + r.getRole().toString() + ";" + "f" +  c.getRole().toString() + ";" + "f" + t.getRole().toString() + ";";
        
        assertEquals(str,m.getComponentString());
    }
    
    
    
}
