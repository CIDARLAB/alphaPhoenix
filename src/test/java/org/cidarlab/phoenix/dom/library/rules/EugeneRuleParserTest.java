/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library.rules;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prash
 */
public class EugeneRuleParserTest {
    
    public EugeneRuleParserTest() {
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
     * Test of parse method, of class EugeneRuleParser.
     */
    @Test
    public void testParse() {
        
        String rule0 = "LuxR NOTWITH LasR";
        EugeneRule erule = ERuleParser.parse(rule0);
        
        assertEquals(erule.getLeft(),"LuxR");
        assertEquals(erule.getRight(),"LasR");
        assertEquals(erule.getErule(),"NOTWITH");
        
    }
    
}
