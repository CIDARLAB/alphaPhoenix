/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.utils.Crawler.CrawlerTask;
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
public class CrawlerTest {
    
    private static final String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static String sampleCircuitsFP = tested_circuitsFP + "circuits" + Utilities.getSeparater();
    
    private static final String one_tuFP = sampleCircuitsFP + "1tu" + Utilities.getSeparater();
    private static final String one_tu_results = one_tuFP + "results" + Utilities.getSeparater();
    
    public CrawlerTest() {
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
     * Test of crawl method, of class Crawler.
     */
    
    
    
    
    @Test
    public void testCrawl() throws TLIException, InterruptedException, IOException {
        Set<CrawlerTask> tasks = new HashSet<>();
        tasks.add(CrawlerTask.STL);
        tasks.add(CrawlerTask.STL_STEADYSTATE);
        tasks.add(CrawlerTask.LOG_PLOT);
        Crawler c = new Crawler();
        c.setXthreshold(10.0);
        c.setYthreshold(10.0);
        c.setSteadyState(600);
        c.crawl(tasks, one_tu_results + "0" + Utilities.getSeparater());
            
    }

    
    
}
