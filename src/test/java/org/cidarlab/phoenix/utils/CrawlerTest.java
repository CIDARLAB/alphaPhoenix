/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import com.google.common.io.Files;
import hyness.stl.TreeNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testCircuitsToTest() throws IOException{
        
        int[] indices = {30,31,32,33,34,35,36,37,127,128,129,130,131,132,133,134,118};
        //String thresh = "50.0_10000.0";
        String thresh = "10.0_10000.0";
        //String thresh = "50.0_1000.0";
        
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String threshfp = basefp + thresh + Utilities.getSeparater();
        String totestfp = basefp + "circuitsToTest" + Utilities.getSeparater() + thresh + Utilities.getSeparater();
        Utilities.makeDirectory(totestfp);
        
        String csvfp = basefp + "csv" + Utilities.getSeparater();
        
        List<String[]> tu1circuitsLines = Utilities.getCSVFileContentAsList(csvfp + "Circuit1TUStochasticReduced0.csv");
        List<String[]> tu2circuitsLines = Utilities.getCSVFileContentAsList(csvfp + "Circuit2TUStochasticReduced0.csv");
        
        Map<Integer,String> oneTUs = getCircuitMap(tu1circuitsLines);
        Map<Integer,String> twoTUs = getCircuitMap(tu2circuitsLines);
        
        String basesimfp = basefp + "twoTUstoch" + Utilities.getSeparater();
        
        for(int z=0;z<indices.length;z++){
            int index = indices[z];
            
            
            String simindexfp = basesimfp + index + Utilities.getSeparater();
            String totestresultfp = totestfp + index + Utilities.getSeparater();
            Utilities.makeDirectory(totestresultfp);
        
            String scoresfp = threshfp + index + Utilities.getSeparater();
        
            List<String[]> onetuscores = Utilities.getCSVFileContentAsList(scoresfp + "oneTU_" + index + "Rob.csv");
            List<String[]> twotuscores = Utilities.getCSVFileContentAsList(scoresfp + "twoTU_" + index + "Rob.csv");
            
            Files.copy(new File(simindexfp + "circuit.png" ), new File(totestresultfp + "circuit.png"));
            Files.copy(new File(simindexfp + "out0.png" ), new File(totestresultfp + "simulation.png"));
            
            if(Utilities.validFilepath(simindexfp + "assignedSM.json")){
                Files.copy(new File(simindexfp + "assignedSM.json" ), new File(totestresultfp + "smConcentration.json"));
            }
            
            Set<Double> scores = new HashSet<>();
            for (String[] pieces : onetuscores) {
                scores.add(Double.valueOf(pieces[1]));
            }

            for (String[] pieces : twotuscores) {
                scores.add(Double.valueOf(pieces[1]));
            }

            List<Double> sorted = new ArrayList<>(scores);
            Collections.sort(sorted, Collections.reverseOrder());
            
            List<String> topcsv = new ArrayList<>();
            topcsv.add("Score,Circuit Configuration");
            List<String> bottomcsv = new ArrayList<>();
            bottomcsv.add("Score,Circuit Configuration");
            
            for (int i = 0; i < 5; i++) {
                for(int j=0;j<onetuscores.size();j++){
                    String[] pieces = onetuscores.get(j);
                    if(Double.valueOf(pieces[1]).equals(sorted.get(i))){
                        topcsv.add(sorted.get(i) + "," + oneTUs.get(j));
                    }
                }
                for(int j=0;j<twotuscores.size();j++){
                    String[] pieces = twotuscores.get(j);
                    if(Double.valueOf(pieces[1]).equals(sorted.get(i))){
                        topcsv.add(sorted.get(i) + "," + twoTUs.get(j));
                    }
                }
            }
            
            
            for(int i=sorted.size()-1; i>= sorted.size()-5;i--){
                for(int j=0;j<onetuscores.size();j++){
                    String[] pieces = onetuscores.get(j);
                    if(Double.valueOf(pieces[1]).equals(sorted.get(i))){
                        bottomcsv.add(sorted.get(i) + "," + oneTUs.get(j));
                    }
                }
                for(int j=0;j<twotuscores.size();j++){
                    String[] pieces = twotuscores.get(j);
                    if(Double.valueOf(pieces[1]).equals(sorted.get(i))){
                        bottomcsv.add(sorted.get(i) + "," + twoTUs.get(j));
                    }
                }
            }
            
            Utilities.writeToFile(totestresultfp + "topFiveScores.csv", topcsv);
            Utilities.writeToFile(totestresultfp + "bottomFiveScores.csv", bottomcsv);
            
        }
        
        
    }
   
    public static Map<Integer,String> getCircuitMap(List<String[]> lines){
        Map<Integer,String> map = new HashMap<>();
        
        for(String[] pieces:lines){
            int index = Integer.valueOf(pieces[0]);
            String circuitString = pieces[1];
            if(circuitString.endsWith(";")){
                circuitString = circuitString.substring(0, circuitString.lastIndexOf(";"));
            }
            
            String[] components = circuitString.split(";");
            String newStr = "";
            for(int i=0;i<((4 * (components.length/4)));i+=4){
                
                newStr += (components[i].split("_"))[0] + ";";
                newStr += (components[i+1].split("_"))[1] + ";";
                newStr += (components[i+2]) + ";";
                newStr += (components[i+3]) + ";";
            }
            int lastIndx = (4 * (components.length/4));
            for(int i=lastIndx;i<components.length;i++){
                newStr += (components[i]) + ";";
            }
            
            map.put(index, newStr);
            
        }
        
        return map;
    }
    
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

    //@Test
    public void fullScoreTest() throws TLIException, InterruptedException, IOException{
        
        double xthresh = 10;
        double ythresh = 10000;
        
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String resultfp = basefp + xthresh + "_" + ythresh + Utilities.getSeparater();
        Utilities.makeDirectory(resultfp);
        
        String tu2stochfp = basefp + "twoTUstoch" + Utilities.getSeparater();
        String tu2deterfp = basefp + "twoTUdeter" + Utilities.getSeparater();
        String tu1deterfp = basefp + "oneTUdeter" + Utilities.getSeparater();
        String tu1stochfp = basefp + "oneTUstoch" + Utilities.getSeparater();
        
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
    
            Map<Integer, Double> tu2rob = Crawler.getRobustness(stl, tu2deterfp);
            Map<Integer, Double> tu2smc = Crawler.getComputeSatisfyingPercent(stl, tu2stochfp);
            Map<Integer, Double> tu1rob = Crawler.getRobustness(stl, tu1deterfp);
            Map<Integer, Double> tu1smc = Crawler.getComputeSatisfyingPercent(stl, tu1stochfp);
            
            
            List<String> tu2combined = new ArrayList<>();

            List<String> tu2robcsv = new ArrayList<>();
            for (int key : tu2rob.keySet()) {
                tu2robcsv.add(key + "," + tu2rob.get(key));
            }
            List<String> tu2smccsv = new ArrayList<>();
            for (int key : tu2smc.keySet()) {
                tu2smccsv.add(key + "," + tu2smc.get(key));
                tu2combined.add(key + "," + tu2rob.get(key) + "," + tu2smc.get(key));
            }
            
            List<String> tu1combined = new ArrayList<>();
            List<String> tu1robcsv = new ArrayList<>();
            for (int key : tu1rob.keySet()) {
                tu1robcsv.add(key + "," + tu1rob.get(key));
            }
            List<String> tu1smccsv = new ArrayList<>();
            for (int key : tu1smc.keySet()) {
                tu1smccsv.add(key + "," + tu1smc.get(key));
                tu1combined.add(key + "," + tu1rob.get(key) + "," + tu1smc.get(key));
            }
            
            allRob.add(tu2rob);
            allSmc.add(tu2smc);
            Utilities.writeToFile(indexfp + "twoTU_" + simIndex + "Rob.csv", tu2robcsv);
            Utilities.writeToFile(indexfp + "twoTU_" + simIndex + "SatPer.csv", tu2smccsv);
            Utilities.writeToFile(indexfp + "twoTU_" + simIndex + "Comb.csv", tu2combined);
            Utilities.writeToFile(indexfp + "oneTU_" + simIndex + "Rob.csv", tu1robcsv);
            Utilities.writeToFile(indexfp + "oneTU_" + simIndex + "SatPer.csv", tu1smccsv);
            Utilities.writeToFile(indexfp + "oneTU_" + simIndex + "Comb.csv", tu1combined);
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
        
        String csvfp = basefp + "csv" + Utilities.getSeparater();
        
        outParser(csvfp, red1TUdeterOut, "Circuit1TUDeterministicReduced0");
        outParser(csvfp, red2TUdeterOut, "Circuit2TUDeterministicReduced0");
        outParser(csvfp, red1TUstochOut, "Circuit1TUStochasticReduced0");
        outParser(csvfp, red2TUstochOut, "Circuit2TUStochasticReduced0");
        
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
