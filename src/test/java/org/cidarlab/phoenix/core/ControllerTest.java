/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decompose method, of class Controller.
     */
    @Test
    public void testDecompose() {
        
        String eug = Utilities.getResourcesFilepath() + "miniEugeneFiles" + Utilities.getSeparater() + "inverter.eug";
        int size = 8;
        List<Module> modules = MiniEugeneAdaptor.getStructures(eug, size, "inverter");
        Module test = Controller.decompose(PhoenixMode.BIOCPS, modules.get(1));
        System.out.println("End of Test");
    }
    
}
