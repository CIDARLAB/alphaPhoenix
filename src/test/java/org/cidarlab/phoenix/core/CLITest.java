/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.gridtli.dom.TLIException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class CLITest {
    
    public CLITest() {
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
     * Test of main method, of class CLI.
     */
    @Test
    public void testMain() throws URISyntaxException {
        String[] args = new String[2];
        args[0] = "--gridtli";
        args[1] = "-help";
        try {
            CLI.main(args);
        } catch (TLIException ex) {
            Logger.getLogger(CLITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
