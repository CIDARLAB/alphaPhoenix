/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.List;
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
public class MiniEuegeneAdaptorTest {
    
    public MiniEuegeneAdaptorTest() {
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
    
   
    public static void main(String[] args) {
    
        String basefp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "miniEugeneScripts" + Utilities.getSeparater();
        
        String example1fp = basefp + "example1.eug";
        
        
        List<Module> modules = MiniEugeneAdaptor.getStructures(example1fp, 8, "inverter");
        System.out.println("Example 1 size = " + modules.size());
    }
    
    
    
    
}
