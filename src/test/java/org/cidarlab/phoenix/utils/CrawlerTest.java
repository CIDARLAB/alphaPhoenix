/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.TreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.gridtli.tli.TemporalLogicInference;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.utils.Crawler.CrawlerTask;
import org.json.JSONArray;
import org.json.JSONObject;
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
    
    
    
    
    //@Test
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

    @Test
    public void fullScoreTest() throws TLIException, InterruptedException, IOException{
        
        
        
        double xthresh = 50;
        double ythresh = 10000;
        
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String resultfp = basefp + xthresh + "_" + ythresh + Utilities.getSeparater();
        Utilities.makeDirectory(resultfp);
        
        String tu2stochfp = basefp + "twoTUstoch" + Utilities.getSeparater();
        String tu2deterfp = basefp + "twoTUdeter" + Utilities.getSeparater();
        List<Map<Integer,Double>> allRob = new ArrayList<>();
        List<Map<Integer,Double>> allSmc = new ArrayList<>();
            
        for(int i=0;i<301;i++){
            int simIndex = i;
            String indexfp = resultfp + i + Utilities.getSeparater();
            Utilities.makeDirectory(indexfp);
            
            List<Signal> signals = Utilities.readSignalsFromCSV(basefp + "twoTUstoch" + Utilities.getSeparater() + simIndex + Utilities.getSeparater() + "out0.csv");
            List<Signal> steadysigs = new ArrayList<>();
            for (Signal s : signals) {
                List<Point> points = new ArrayList<>();
                for (Point p : s.getPoints()) {
                    if (p.getX() >= 600) {
                        points.add(p);
                    }
                }
                steadysigs.add(new Signal(points));
            }

            Grid grid = new Grid(steadysigs, xthresh, ythresh);
            TreeNode stl = TemporalLogicInference.getSTL(grid, 100);
            Utilities.writeToFile(indexfp + "twoTUstoch" + simIndex + "_" + xthresh + "_" + ythresh + "_STL.txt", stl.toString());
    
            Map<Integer, Double> rob = Crawler.getRobustness(stl, tu2deterfp);
            Map<Integer, Double> smc = Crawler.getComputeSatisfyingPercent(stl, tu2stochfp);
            List<String> combined = new ArrayList<>();

            List<String> robcsv = new ArrayList<>();
            for (int key : rob.keySet()) {
                robcsv.add(key + "," + rob.get(key));
            }
            List<String> smccsv = new ArrayList<>();
            for (int key : smc.keySet()) {
                smccsv.add(key + "," + smc.get(key));
                combined.add(key + "," + rob.get(key) + "," + smc.get(key));
            }
            allRob.add(rob);
            allSmc.add(smc);
            Utilities.writeToFile(indexfp + "twoTUstoch" + simIndex + "Rob.csv", robcsv);
            Utilities.writeToFile(indexfp + "twoTUstoch" + simIndex + "SatPer.csv", smccsv);
            Utilities.writeToFile(indexfp + "twoTUstoch" + simIndex + "Comb.csv", combined);
        }
        String combinedfp = resultfp + "combined" + Utilities.getSeparater();
        Utilities.makeDirectory(combinedfp);
        
        List<String> combRob = new ArrayList<>();
        List<String> combSMC = new ArrayList<>();
        List<String> combcomb = new ArrayList<>();
        
        for(int i=0;i<301;i++){
            String robstr = i + ",";
            String smcstr = i + ",";
            String combstr = i + ",";
            for(int j=0;j<300;j++){
                robstr += (allRob.get(i).get(j) + ",");
                smcstr += (allSmc.get(i).get(j) + ",");
                combstr += (allRob.get(i).get(j) + "," + allSmc.get(i).get(j) + ",");
            }
            robstr += (allRob.get(i).get(300));
            smcstr += (allSmc.get(i).get(300));
            combstr += (allRob.get(i).get(300) + "," + allSmc.get(i).get(300));
            combRob.add(robstr);
            combSMC.add(smcstr);
            combcomb.add(combstr);
        }
        
        Utilities.writeToFile(combinedfp + "twoTUstoch_Rob.csv", combRob);
        Utilities.writeToFile(combinedfp + "twoTUstoch_SMC.csv", combSMC);
        Utilities.writeToFile(combinedfp + "twoTUstoch_Comb.csv", combcomb);
        
        
        
        
    }
    
    
    //@Test
    public void getSTL() throws TLIException{
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        List<Signal> signals = Utilities.readSignalsFromCSV(basefp + "twoTUstoch" + Utilities.getSeparater() + "35" + Utilities.getSeparater() + "out0.csv");
        List<Signal> steadysigs = new ArrayList<>();
        for (Signal s : signals) {
            List<Point> points = new ArrayList<>();
            for (Point p : s.getPoints()) {
                if (p.getX() >= 600) {
                    points.add(p);
                }
            }
            steadysigs.add(new Signal(points));
        }

        Grid grid = new Grid(steadysigs, 50, 10000);
        TreeNode stl = TemporalLogicInference.getSTL(grid, 100);
        Utilities.writeToFile(basefp + "two2tuStoch35STL.txt", stl.toString());
    }
    
    //@Test
    public void getRobustness() throws TLIException, InterruptedException, IOException{
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String stlfp = basefp + "two2tuStoch35STL.txt";
        TreeNode stl = STLAdaptor.getSTL(stlfp);
        String tu2stochfp = basefp + "twoTUstoch" + Utilities.getSeparater();
        String tu2deterfp = basefp + "twoTUdeter" + Utilities.getSeparater();
        
        Map<Integer,Double> rob = Crawler.getRobustness(stl, tu2deterfp);
        Map<Integer,Double> smc = Crawler.getComputeSatisfyingPercent(stl, tu2stochfp);
        List<String> combined = new ArrayList<>();
        
        List<String> robcsv = new ArrayList<>();
        for(int key:rob.keySet()){
            robcsv.add(key + "," + rob.get(key));
        }
        List<String> smccsv = new ArrayList<>();
        for(int key:smc.keySet()){
            smccsv.add(key + "," + smc.get(key));
            combined.add(key + "," + rob.get(key) + "," + smc.get(key));
        }
        
        Utilities.writeToFile(basefp + "two2tuStoch35Rob.csv", robcsv);
        Utilities.writeToFile(basefp + "two2tuStoch35SatPer.csv", smccsv);
        Utilities.writeToFile(basefp + "two2tuStoch35Comb.csv", combined);
        
        
    }
    
    
    //@Test
    public void convert2CSV(){
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        Utilities.makeDirectory(basefp);
        
        String red1TUdeterOut = basefp + "Circuit1TUDeterministicReduced0.txt";
        String red2TUdeterOut = basefp + "Circuit2TUDeterministicReduced0.txt";
        
        String red1TUstochOut = basefp + "Circuit1TUStochasticReduced0.txt";
        String red2TUstochOut = basefp + "Circuit2TUStochasticReduced0.txt";
        
        
        outParser(basefp, red1TUdeterOut, "Circuit1TUDeterministicReduced0");
        outParser(basefp, red2TUdeterOut, "Circuit2TUDeterministicReduced0");
        outParser(basefp, red1TUstochOut, "Circuit1TUStochasticReduced0");
        outParser(basefp, red2TUstochOut, "Circuit2TUStochasticReduced0");
        
    }
    
    public static void outParser(String base, String fp, String filename){
        List<String> lines = Utilities.getFileContentAsStringList(fp);
        List<String> csv = new ArrayList<>();
        
        for(int i=0;i<lines.size();i++){
            
            String str = lines.get(i);
            if(str.equals("Small Molecule Concentrations:")){
                String concs = lines.get(i+1);
                concs = concs.substring(11, concs.lastIndexOf("}"));
                JSONArray vals = new JSONArray(concs);
                for(int j=0;j<8;j++){
                    int k = i + 2 + (2*j);
                    String smstr = lines.get(k);
                    smstr = smstr.substring(21);
                    int indx = Integer.valueOf(smstr);
                    csv.add(indx + "," + lines.get(k+1) + "ind_conn0=" + vals.get(j));
                    
                }
                
                i+= 17;
                
            } else {
                if (str.endsWith("has no small molecules.")) {
                    str = str.substring(21, str.indexOf("has no small molecules."));
                    str = str.trim();
                } else {
                    str = str.substring(21);
                    str = str.trim();
                }
                int indx = Integer.valueOf(str);
                csv.add(indx + "," + lines.get(i+1));
                
                i+= 1;
            }
            
            Utilities.writeToFile(base + filename + ".csv", csv);
            
        }
        
    }
    
    
    
    
}
