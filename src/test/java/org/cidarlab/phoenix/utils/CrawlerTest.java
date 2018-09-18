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
import org.apache.commons.io.FileUtils;
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

    private final String baseResultsfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "circuits" + Utilities.getSeparater();

    private final String oneTUfp = baseResultsfp + "1tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
    private final String twoTUfp = baseResultsfp + "2tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
    private final String threeTUfp = baseResultsfp + "3tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();

    private final String oneTUdetfp = oneTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final String oneTUdetRes = oneTUfp + "DeterministicRed0.txt";
    private final String oneTUstofp = oneTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final String oneTUstoRes = oneTUfp + "StochasticRed0.txt";

    private final String twoTUdetfp = twoTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final String twoTUdetRes = twoTUfp + "DeterministicRed0.txt";
    private final String twoTUstofp = twoTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final String twoTUstoRes = twoTUfp + "StochasticRed0.txt";

    private final String threeTUdetfp = threeTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final String threeTUdetRes = threeTUfp + "DeterministicRed0.txt";
    //private final String threeTUstofp = threeTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    //private final String threeTUstoRes = threeTUfp + "StochasticRed0.txt";

    private Map<Integer, String> onetudet;
    private Map<Integer, String> onetusto;
    private Map<Integer, String> twotudet;
    private Map<Integer, String> twotusto;
    private Map<Integer, String> threetudet;

    private Map<String, Integer> onetudetrev;
    private Map<String, Integer> onetustorev;
    private Map<String, Integer> twotudetrev;
    private Map<String, Integer> twotustorev;
    private Map<String, Integer> threetudetrev;

    //private Map<Integer, String> threetusto;
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

        onetudet = readResultFile(oneTUdetRes);
        onetusto = readResultFile(oneTUstoRes);
        twotudet = readResultFile(twoTUdetRes);
        twotusto = readResultFile(twoTUstoRes);
        threetudet = readResultFile(threeTUdetRes);

        onetudetrev = readResultFileRev(oneTUdetRes);
        onetustorev = readResultFileRev(oneTUstoRes);
        twotudetrev = readResultFileRev(twoTUdetRes);
        twotustorev = readResultFileRev(twoTUstoRes);
        threetudetrev = readResultFileRev(threeTUdetRes);
        //threetustorev = readResultFile(threeTUstoRes);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of crawl method, of class Crawler.
     */
    /**
     * Test of crawl method, of class Crawler.
     *
     * @throws org.cidarlab.gridtli.dom.TLIException
     * @throws java.lang.InterruptedException
     * @throws java.io.IOException
     */
    //@Test
    public void fullScoreTest() throws TLIException, InterruptedException, IOException {

        double xthresh = 50.00;
        double ythresh = 1000.00;

        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String resultfp = basefp + xthresh + "_" + ythresh + Utilities.getSeparater();
        Utilities.makeDirectory(resultfp);

        String onetufp = resultfp + "1tu" + Utilities.getSeparater();
        Utilities.makeDirectory(onetufp);
        getScores(oneTUstofp, onetufp, xthresh, ythresh);

        String twotufp = resultfp + "2tu" + Utilities.getSeparater();
        Utilities.makeDirectory(twotufp);
        getScores(twoTUstofp, twotufp, xthresh, ythresh);

    }

    private void getScores(String stofp, String basefp, double xthresh, double ythresh) throws TLIException, InterruptedException, IOException {
        File[] filelist = (new File(stofp)).listFiles();

        for (File f : filelist) {
            String fp = basefp + f.getName() + Utilities.getSeparater();
            Utilities.makeDirectory(fp);

            List<Signal> signals = Utilities.readSignalsFromCSV(stofp + f.getName() + Utilities.getSeparater() + "out0.csv");
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
            TreeNode stl = TemporalLogicInference.getSTL(grid, ythresh);

            Utilities.writeToFile(fp + "stl.txt", stl.toString());

            FileUtils.copyFile(new File(stofp + f.getName() + Utilities.getSeparater() + "circuit.png"), new File(fp + "circuit.png"));
            FileUtils.copyFile(new File(stofp + f.getName() + Utilities.getSeparater() + "out0.png"), new File(fp + "simulation.png"));

            Map<Integer, Double> oneTURob = Crawler.getRobustness(stl, oneTUdetfp);
            Map<Integer, Double> oneTUsmc = Crawler.getComputeSatisfyingPercent(stl, oneTUstofp);
            Map<Integer, Double> twoTURob = Crawler.getRobustness(stl, twoTUdetfp);
            Map<Integer, Double> twoTUsmc = Crawler.getComputeSatisfyingPercent(stl, twoTUstofp);
            Map<Integer, Double> threeTURob = Crawler.getRobustness(stl, threeTUdetfp);

            List<String> oneTURobLines = new ArrayList<>();
            List<String> oneTUsmcLines = new ArrayList<>();
            List<String> twoTURobLines = new ArrayList<>();
            List<String> twoTUsmcLines = new ArrayList<>();
            List<String> threeTURobLines = new ArrayList<>();

            List<String> oneTUscores = new ArrayList<>();
            List<String> twoTUscores = new ArrayList<>();

            for (Integer i : oneTURob.keySet()) {
                oneTURobLines.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i));
                oneTUscores.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i) + "," + oneTUsmc.get(onetustorev.get(onetudet.get(i))));
            }

            for (Integer i : oneTUsmc.keySet()) {
                oneTUsmcLines.add(i + "," + onetusto.get(i) + "," + oneTUsmc.get(i));
            }

            for (Integer i : twoTURob.keySet()) {
                twoTURobLines.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i));
                twoTUscores.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i) + "," + twoTUsmc.get(twotustorev.get(twotudet.get(i))));
            }

            for (Integer i : twoTUsmc.keySet()) {
                twoTUsmcLines.add(i + "," + twotusto.get(i) + "," + twoTUsmc.get(i));
            }

            for (Integer i : threeTURob.keySet()) {
                threeTURobLines.add(i + "," + threetudet.get(i) + "," + threeTURob.get(i));
            }

            Utilities.writeToFile(fp + "1tuRobustness.csv", oneTURobLines);
            Utilities.writeToFile(fp + "1tuSMC.csv", oneTUsmcLines);
            Utilities.writeToFile(fp + "2tuRobustness.csv", twoTURobLines);
            Utilities.writeToFile(fp + "2tuSMC.csv", twoTUsmcLines);
            Utilities.writeToFile(fp + "3tuRobustness.csv", threeTURobLines);

            Utilities.writeToFile(fp + "1tuScores.csv", oneTUscores);
            Utilities.writeToFile(fp + "2tuScores.csv", twoTUscores);

            List<String> topcsv = new ArrayList<>();
            topcsv.add("Score,Circuit Configuration");
            List<String> bottomcsv = new ArrayList<>();
            bottomcsv.add("Score,Circuit Configuration");

            Set<Double> scores = new HashSet<>();
            scores.addAll(oneTURob.values());
            scores.addAll(twoTURob.values());
            scores.addAll(threeTURob.values());

            List<Double> sorted = new ArrayList<>(scores);
            Collections.sort(sorted, Collections.reverseOrder());
            for (int i = 0; i < 5; i++) {
                double currScore = sorted.get(i);
                for (Integer index : oneTURob.keySet()) {
                    if (oneTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + onetudet.get(index));
                    }
                }
                for (Integer index : twoTURob.keySet()) {
                    if (twoTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + twotudet.get(index));
                    }
                }
                for (Integer index : threeTURob.keySet()) {
                    if (threeTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + threetudet.get(index));
                    }
                }
            }

            for (int i = sorted.size() - 1; i >= sorted.size() - 5; i--) {
                double currScore = sorted.get(i);
                for (Integer index : oneTURob.keySet()) {
                    if (oneTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + onetudet.get(index));
                    }
                }
                for (Integer index : twoTURob.keySet()) {
                    if (twoTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + twotudet.get(index));
                    }
                }
                for (Integer index : threeTURob.keySet()) {
                    if (threeTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + threetudet.get(index));
                    }
                }
            }

            Utilities.writeToFile(fp + "top.csv", topcsv);
            Utilities.writeToFile(fp + "bottom.csv", bottomcsv);

        }
    }

    private void getSTLScores(String basefp, TreeNode stl) throws TLIException, InterruptedException, IOException {
        
        Map<Integer, Double> oneTURob = Crawler.getRobustness(stl, oneTUdetfp);
        Map<Integer, Double> oneTUsmc = Crawler.getComputeSatisfyingPercent(stl, oneTUstofp);
        Map<Integer, Double> twoTURob = Crawler.getRobustness(stl, twoTUdetfp);
        Map<Integer, Double> twoTUsmc = Crawler.getComputeSatisfyingPercent(stl, twoTUstofp);
        Map<Integer, Double> threeTURob = Crawler.getRobustness(stl, threeTUdetfp);

        List<String> oneTURobLines = new ArrayList<>();
        List<String> oneTUsmcLines = new ArrayList<>();
        List<String> twoTURobLines = new ArrayList<>();
        List<String> twoTUsmcLines = new ArrayList<>();
        List<String> threeTURobLines = new ArrayList<>();

        List<String> oneTUscores = new ArrayList<>();
        List<String> twoTUscores = new ArrayList<>();

        for (Integer i : oneTURob.keySet()) {
            oneTURobLines.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i));
            oneTUscores.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i) + "," + oneTUsmc.get(onetustorev.get(onetudet.get(i))));
        }

        for (Integer i : oneTUsmc.keySet()) {
            oneTUsmcLines.add(i + "," + onetusto.get(i) + "," + oneTUsmc.get(i));
        }

        for (Integer i : twoTURob.keySet()) {
            twoTURobLines.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i));
            twoTUscores.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i) + "," + twoTUsmc.get(twotustorev.get(twotudet.get(i))));
        }

        for (Integer i : twoTUsmc.keySet()) {
            twoTUsmcLines.add(i + "," + twotusto.get(i) + "," + twoTUsmc.get(i));
        }

        for (Integer i : threeTURob.keySet()) {
            threeTURobLines.add(i + "," + threetudet.get(i) + "," + threeTURob.get(i));
        }

        Utilities.writeToFile(basefp + "1tuRobustness.csv", oneTURobLines);
        Utilities.writeToFile(basefp + "1tuSMC.csv", oneTUsmcLines);
        Utilities.writeToFile(basefp + "2tuRobustness.csv", twoTURobLines);
        Utilities.writeToFile(basefp + "2tuSMC.csv", twoTUsmcLines);
        Utilities.writeToFile(basefp + "3tuRobustness.csv", threeTURobLines);

        Utilities.writeToFile(basefp + "1tuScores.csv", oneTUscores);
        Utilities.writeToFile(basefp + "2tuScores.csv", twoTUscores);

        List<String> topcsv = new ArrayList<>();
        topcsv.add("Score,Circuit Configuration");
        List<String> bottomcsv = new ArrayList<>();
        bottomcsv.add("Score,Circuit Configuration");

        Set<Double> scores = new HashSet<>();
        scores.addAll(oneTURob.values());
        scores.addAll(twoTURob.values());
        scores.addAll(threeTURob.values());

        List<Double> sorted = new ArrayList<>(scores);
        Collections.sort(sorted, Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + onetudet.get(index));
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + twotudet.get(index));
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + threetudet.get(index));
                }
            }
        }

        for (int i = sorted.size() - 1; i >= sorted.size() - 5; i--) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + onetudet.get(index));
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + twotudet.get(index));
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + threetudet.get(index));
                }
            }
        }

        Utilities.writeToFile(basefp + "top.csv", topcsv);
        Utilities.writeToFile(basefp + "bottom.csv", bottomcsv);

        
    }

    
    public Map<String, Integer> readResultFileRev(String fp) {
        String startStr = "Current Assignment : ";
        Map<String, Integer> map = new HashMap<>();
        List<String> lines = Utilities.getFileContentAsStringList(fp);
        for (int i = 0; i < lines.size(); i++) {
            String indexLine = lines.get(i);
            String assignmentLine = lines.get(i + 1);
            indexLine = indexLine.substring(startStr.length());
            map.put(assignmentLine, Integer.valueOf(indexLine));
            i++;

        }

        return map;
    }

    public Map<Integer, String> readResultFile(String fp) {
        String startStr = "Current Assignment : ";
        Map<Integer, String> map = new HashMap<>();
        List<String> lines = Utilities.getFileContentAsStringList(fp);
        for (int i = 0; i < lines.size(); i++) {
            String indexLine = lines.get(i);
            String assignmentLine = lines.get(i + 1);
            indexLine = indexLine.substring(startStr.length());
            map.put(Integer.valueOf(indexLine), assignmentLine);
            i++;

        }

        return map;
    }

    
    @Test
    public void testSTL() throws TLIException, InterruptedException, IOException{
        String folder = "stl1";
        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater() + "stl" + Utilities.getSeparater() + folder + Utilities.getSeparater();
        String stlfp = basefp + "stl.txt";
        TreeNode stl = STLAdaptor.getSTL(stlfp);
        getSTLScores(basefp,stl);
    }
    
}
