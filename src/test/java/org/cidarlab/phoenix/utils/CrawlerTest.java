/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

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
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.gridtli.tli.TemporalLogicInference;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author prash
 */
public class CrawlerTest {

    private final static String baseResultsfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "circuits" + Utilities.getSeparater();
    private final static String basePulsefp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "pulse_circuit" + Utilities.getSeparater();
    
    private final static String oneTUfp = baseResultsfp + "1tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
    private final static String twoTUfp = baseResultsfp + "2tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
    private final static String threeTUfp = baseResultsfp + "3tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
    private final static String pulsefp = basePulsefp  + "allRuns" + Utilities.getSeparater();
    
    private final static String oneTUdetfp = oneTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final static String oneTUstofp = oneTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    
    private final static String twoTUdetfp = twoTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final static String twoTUstofp = twoTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    
    private final static String threeTUdetfp = threeTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private final static String threeTUstofp = threeTUfp + "StochasticRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    
    private final static String pulsedetfp = pulsefp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    
    
    private static Map<Integer, String> onetudet;
    private static Map<Integer, String> twotudet;
    private static Map<Integer, String> threetudet;
    private static Map<Integer, String> pulsedet;
    
    
    private static Map<Integer, String> onetusto;
    private static Map<Integer, String> twotusto;
    private static Map<Integer, String> threetusto;
    
    private static Map<String, Integer> onetustorev;
    private static Map<String, Integer> twotustorev;
    private static Map<String, Integer> threetustorev;
    
    //private Map<Integer, String> threetusto;
    public CrawlerTest() {
    
        onetudet = getIndexAssignmentMap(oneTUdetfp);
        twotudet = getIndexAssignmentMap(twoTUdetfp);
        threetudet = getIndexAssignmentMap(threeTUdetfp);
        pulsedet = getIndexAssignmentMap(pulsedetfp);
        generateAssignmentsFile(onetudet, oneTUfp + "DeterministicRed0.csv");
        generateAssignmentsFile(twotudet, twoTUfp + "DeterministicRed0.csv");
        generateAssignmentsFile(threetudet, threeTUfp + "DeterministicRed0.csv");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        //threetustorev = getIndexAssignmentMap(threeTUstoRes);
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
    public void fullScoreTest(double xthresh, double ythresh) throws TLIException, InterruptedException, IOException {

        String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
        String resultfp = basefp + xthresh + "_" + ythresh + Utilities.getSeparater();
        Utilities.makeDirectory(resultfp);

        String onetufp = resultfp + "1tu" + Utilities.getSeparater();
        Utilities.makeDirectory(onetufp);
        System.out.println("Starting 1 TU ########################################");
        getScores(oneTUstofp, onetufp, xthresh, ythresh);

        String twotufp = resultfp + "2tu" + Utilities.getSeparater();
        Utilities.makeDirectory(twotufp);
        System.out.println("Starting 2 TU ########################################");
        getScores(twoTUstofp, twotufp, xthresh, ythresh);
        
        String threetufp = resultfp + "3tu" + Utilities.getSeparater();
        Utilities.makeDirectory(threetufp);
        System.out.println("Starting 3 TU ########################################");
        getScores(threeTUstofp, threetufp, xthresh, ythresh);

    }

    private void getScores(String stofp, String basefp, double xthresh, double ythresh) throws TLIException, InterruptedException, IOException {
        File[] filelist = (new File(stofp)).listFiles();

        for (File f : filelist) {
            System.out.println(f.getName());
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
            Map<Integer, Double> threeTUsmc = Crawler.getRobustness(stl, threeTUstofp);
            
            List<String> oneTURobLines = new ArrayList<>();
            List<String> oneTUsmcLines = new ArrayList<>();
            List<String> twoTURobLines = new ArrayList<>();
            List<String> twoTUsmcLines = new ArrayList<>();
            List<String> threeTURobLines = new ArrayList<>();
            List<String> threeTUsmcLines = new ArrayList<>();
            
            
            List<String> oneTUscores = new ArrayList<>();
            List<String> twoTUscores = new ArrayList<>();
            List<String> threeTUscores = new ArrayList<>();

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
                threeTUscores.add(i + "," + threetudet.get(i) + "," + threeTURob.get(i) + "," + threeTUsmc.get(threetustorev.get(threetudet.get(i))));
            
            }
            
            for (Integer i : threeTUsmc.keySet()) {
                threeTUsmcLines.add(i + "," + threetusto.get(i) + "," + threeTUsmc.get(i));
            }

            Utilities.writeToFile(fp + "1tuRobustness.csv", oneTURobLines);
            Utilities.writeToFile(fp + "1tuSMC.csv", oneTUsmcLines);
            Utilities.writeToFile(fp + "2tuRobustness.csv", twoTURobLines);
            Utilities.writeToFile(fp + "2tuSMC.csv", twoTUsmcLines);
            Utilities.writeToFile(fp + "3tuRobustness.csv", threeTURobLines);
            Utilities.writeToFile(fp + "3tuSMC.csv", threeTUsmcLines);

            Utilities.writeToFile(fp + "1tuScores.csv", oneTUscores);
            Utilities.writeToFile(fp + "2tuScores.csv", twoTUscores);
            Utilities.writeToFile(fp + "3tuScores.csv", threeTUscores);
            
            List<String> topcsv = new ArrayList<>();
            topcsv.add("Score,Circuit Configuration");
            List<String> bottomcsv = new ArrayList<>();
            bottomcsv.add("Score,Circuit Configuration");

            Set<Double> scores = new HashSet<>();
            scores.addAll(oneTURob.values());
            scores.addAll(twoTURob.values());
            scores.addAll(threeTURob.values());

            List<String> topScriptLines = new ArrayList<>();
            List<String> bottomScriptLines = new ArrayList<>();

            topScriptLines.add("import matplotlib\n"
                    + "matplotlib.use('agg',warn=False, force=True)\n"
                    + "from matplotlib import pyplot as plt\n"
                    + "from matplotlib import patches as patches\n"
                    + "\n"
                    + "fig = plt.figure()\n");

            bottomScriptLines.add("import matplotlib\n"
                    + "matplotlib.use('agg',warn=False, force=True)\n"
                    + "from matplotlib import pyplot as plt\n"
                    + "from matplotlib import patches as patches\n"
                    + "\n"
                    + "fig = plt.figure()\n");

            topScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));
            bottomScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));

            topScriptLines.add("\n");
            bottomScriptLines.add("\n");

            List<String> colors = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                String color = "";
                do {
                    color = Utilities.generateRandomColor();
                } while (colors.contains(color));
                colors.add(color);
            }
            int sigcount = 0;
            List<Double> sorted = new ArrayList<>(scores);
            Collections.sort(sorted, Collections.reverseOrder());
            for (int i = 0; i < 5; i++) {
                double currScore = sorted.get(i);
                for (Integer index : oneTURob.keySet()) {
                    if (oneTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + onetudet.get(index));
                        topScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(i), onetudet.get(index), sigcount));
                        sigcount++;
                    }
                }
                for (Integer index : twoTURob.keySet()) {
                    if (twoTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + twotudet.get(index));
                        topScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(i), twotudet.get(index), sigcount));
                        sigcount++;
                    }
                }
                for (Integer index : threeTURob.keySet()) {
                    if (threeTURob.get(index).equals(currScore)) {
                        topcsv.add(currScore + "," + threetudet.get(index));
                        topScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(i), threetudet.get(index), sigcount));
                        sigcount++;
                    }
                }
            }

            //colors = new HashSet<>();
            sigcount = 0;
            int colorCount = 0;
            for (int i = sorted.size() - 1; i >= sorted.size() - 5; i--) {
                double currScore = sorted.get(i);
                for (Integer index : oneTURob.keySet()) {
                    if (oneTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + onetudet.get(index));
                        bottomScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(colorCount), onetudet.get(index), sigcount));
                        sigcount++;
                    }
                }
                for (Integer index : twoTURob.keySet()) {
                    if (twoTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + twotudet.get(index));
                        bottomScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(colorCount), twotudet.get(index), sigcount));
                        sigcount++;
                    }
                }
                for (Integer index : threeTURob.keySet()) {
                    if (threeTURob.get(index).equals(currScore)) {
                        bottomcsv.add(currScore + "," + threetudet.get(index));
                        bottomScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(colorCount), threetudet.get(index), sigcount));
                        sigcount++;
                    }
                }
                colorCount++;
            }

            Utilities.writeToFile(fp + "top.csv", topcsv);
            Utilities.writeToFile(fp + "bottom.csv", bottomcsv);

            topScriptLines.add("plt.xlabel(\"time\")\n"
                    + "plt.ylabel(\"out0\")\n"
                    + "plt.xlim(600.0,1500.0)\n"
                    + "plt.ylim(0.0,1000000.0)\n"
                    + "plt.yscale('symlog')");

            bottomScriptLines.add("plt.xlabel(\"time\")\n"
                    + "plt.ylabel(\"out0\")\n"
                    + "plt.xlim(600.0,1500.0)\n"
                    + "plt.ylim(0.0,1000000.0)\n"
                    + "plt.yscale('symlog')");

            /*
            topScriptLines.add("art = []\n"
                    + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                    + "art.append(lgd)");

            bottomScriptLines.add("art = []\n"
                    + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                    + "art.append(lgd)");

            topScriptLines.add("fig.savefig('" + fp + "topFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");
            bottomScriptLines.add("fig.savefig('" + fp + "bottomFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");
            */
            topScriptLines.add("fig.savefig('" + fp + "topFig.png" + "', dpi=300)");
            bottomScriptLines.add("fig.savefig('" + fp + "bottomFig.png" + "', dpi=300)");
            
            
            Utilities.writeToFile(fp + "topFig.py", topScriptLines);
            Utilities.writeToFile(fp + "bottomFig.py", bottomScriptLines);

            Utilities.runPythonScript(fp + "topFig.py");
            Utilities.runPythonScript(fp + "bottomFig.py");

        }
    }

    private List<String> generateSignalScript(String tufp, int index, String color, String label, int count) throws TLIException {
        List<String> lines = new ArrayList<>();
        String fp = tufp + index + Utilities.getSeparater();
        Signal signal = Utilities.readSignalsFromCSV(fp + "out0.csv").get(0);

        List<Point> points = new ArrayList<>(signal.getPoints());

        

        /*
         String color = "";
         do{
         color = Utilities.generateRandomColor();
         } while(colors.contains(color));
         */
        String x = "sx" + count + " = [";
        String y = "sy" + count + " = [";
        for (int i = 0; i < points.size() - 1; i++) {
            x += points.get(i).getX() + ",";
            y += points.get(i).getY() + ",";

        }
        Point lastPoint = points.get(points.size() - 1);
        x += lastPoint.getX() + "]";
        y += lastPoint.getY() + "]";
        lines.add(x);
        lines.add(y);
        lines.add("plt.plot(sx" + count + ",sy" + count + ",color='#" + color + "',linestyle='solid', label = '" + label + "')");
        return lines;
    }
    
    public void getSTLScores(String basefp, TreeNode stl, List<String> stlPlotScript, double timemin, double timemax) throws TLIException, InterruptedException, IOException {

        Map<Integer, Double> oneTURob = Crawler.getRobustness(stl, oneTUdetfp);
        //Map<Integer, Double> oneTUsmc = Crawler.getComputeSatisfyingPercent(stl, oneTUstofp);
        Map<Integer, Double> twoTURob = Crawler.getRobustness(stl, twoTUdetfp);
        //Map<Integer, Double> twoTUsmc = Crawler.getComputeSatisfyingPercent(stl, twoTUstofp);
        Map<Integer, Double> threeTURob = Crawler.getRobustness(stl, threeTUdetfp);
        //Map<Integer, Double> threeTURob = new HashMap<>();
        
        Map<Integer,Double> pulseRob = Crawler.getRobustness(stl, pulsedetfp);
        
        List<String> oneTURobLines = new ArrayList<>();
        //List<String> oneTUsmcLines = new ArrayList<>();
        List<String> twoTURobLines = new ArrayList<>();
        //List<String> twoTUsmcLines = new ArrayList<>();
        List<String> threeTURobLines = new ArrayList<>();
        
        List<String> pulseRobLines = new ArrayList<>();

        //List<String> oneTUscores = new ArrayList<>();
        //List<String> twoTUscores = new ArrayList<>();

        for (Integer i : oneTURob.keySet()) {
            oneTURobLines.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i));
            //oneTUscores.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i) + "," + oneTUsmc.get(onetustorev.get(onetudet.get(i))));
        }

        //for (Integer i : oneTUsmc.keySet()) {
        //    oneTUsmcLines.add(i + "," + onetusto.get(i) + "," + oneTUsmc.get(i));
        //}

        for (Integer i : twoTURob.keySet()) {
            twoTURobLines.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i));
            //twoTUscores.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i) + "," + twoTUsmc.get(twotustorev.get(twotudet.get(i))));
        }

        //for (Integer i : twoTUsmc.keySet()) {
        //    twoTUsmcLines.add(i + "," + twotusto.get(i) + "," + twoTUsmc.get(i));
        //}

        for (Integer i : threeTURob.keySet()) {
            threeTURobLines.add(i + "," + threetudet.get(i) + "," + threeTURob.get(i));
        }
        
        for (Integer i : pulseRob.keySet()) {
            pulseRobLines.add(i + "," + pulsedet.get(i) + "," + pulseRob.get(i));
        }

        Utilities.writeToFile(basefp + "1tuRobustness.csv", oneTURobLines);
        //Utilities.writeToFile(basefp + "1tuSMC.csv", oneTUsmcLines);
        Utilities.writeToFile(basefp + "2tuRobustness.csv", twoTURobLines);
        //Utilities.writeToFile(basefp + "2tuSMC.csv", twoTUsmcLines);
        Utilities.writeToFile(basefp + "3tuRobustness.csv", threeTURobLines);
        
        Utilities.writeToFile(basefp + "pulseRobustness.csv", pulseRobLines);

        //Utilities.writeToFile(basefp + "1tuScores.csv", oneTUscores);
        //Utilities.writeToFile(basefp + "2tuScores.csv", twoTUscores);

        List<String> topcsv = new ArrayList<>();
        topcsv.add("Score,Circuit Configuration");
        List<String> bottomcsv = new ArrayList<>();
        bottomcsv.add("Score,Circuit Configuration");

        Set<Double> scores = new HashSet<>();
        scores.addAll(oneTURob.values());
        scores.addAll(twoTURob.values());
        scores.addAll(threeTURob.values());
        scores.addAll(pulseRob.values());

        List<String> topScriptLines = new ArrayList<>();
        List<String> bottomScriptLines = new ArrayList<>();

        topScriptLines.add("import matplotlib\n"
                + "matplotlib.use('agg',warn=False, force=True)\n"
                + "from matplotlib import pyplot as plt\n"
                + "from matplotlib import patches as patches\n"
                + "\n"
                + "fig = plt.figure()\n");

        bottomScriptLines.add("import matplotlib\n"
                + "matplotlib.use('agg',warn=False, force=True)\n"
                + "from matplotlib import pyplot as plt\n"
                + "from matplotlib import patches as patches\n"
                + "\n"
                + "fig = plt.figure()\n");

        //topScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));
        //bottomScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));
        
        topScriptLines.addAll(stlPlotScript);
        bottomScriptLines.addAll(stlPlotScript);

        topScriptLines.add("\n");
        bottomScriptLines.add("\n");

        List<Double> sorted = new ArrayList<>(scores);

        List<String> colors = new ArrayList<>();
        /*for (int i = 0; i < 5; i++) {
            String color = "";
            do {
                color = Utilities.generateRandomColor();
            } while (colors.contains(color));
            colors.add(color);
        }*/
        colors.add("D4AC0D");
        colors.add("FF0000");
        colors.add("047504");
        colors.add("FF5733");
        colors.add("900C3F");
        
        int sigcount = 0;

        Collections.sort(sorted, Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + onetudet.get(index));
                    topScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(i), onetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + twotudet.get(index));
                    topScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(i), twotudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + threetudet.get(index));
                    topScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(i), threetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : pulseRob.keySet()) {
                if (pulseRob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + pulsedet.get(index));
                    topScriptLines.addAll(generateSignalScript(pulsedetfp, index, colors.get(i), pulsedet.get(index), sigcount));
                    sigcount++;
                }
            }
        }

        
        sigcount = 0;
        int colorCount = 0;
        for (int i = sorted.size() - 1; i >= sorted.size() - 5; i--) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + onetudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(colorCount), onetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + twotudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(colorCount), twotudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + threetudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(colorCount), threetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : pulseRob.keySet()) {
                if (pulseRob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + pulsedet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(pulsedetfp, index, colors.get(i), pulsedet.get(index), sigcount));
                    sigcount++;
                }
            }
            colorCount++;
        }

        Utilities.writeToFile(basefp + "top.csv", topcsv);
        Utilities.writeToFile(basefp + "bottom.csv", bottomcsv);

        topScriptLines.add("plt.xlabel(\"time\")\n"
                + "plt.ylabel(\"MEFL\")\n"
                + "plt.xlim(" + timemin + "," + timemax + ")\n"
                + "plt.ylim(0.0,1000000.0)\n"
                + "plt.yscale('symlog')");

        bottomScriptLines.add("plt.xlabel(\"time\")\n"
                + "plt.ylabel(\"MEFL\")\n"
                + "plt.xlim(" + timemin + "," + timemax + ")\n"
                + "plt.ylim(0.0,1000000.0)\n"
                + "plt.yscale('symlog')");

        topScriptLines.add("art = []\n"
                + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                + "art.append(lgd)");

        bottomScriptLines.add("art = []\n"
                + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                + "art.append(lgd)");

        topScriptLines.add("fig.savefig('" + basefp + "topFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");
        bottomScriptLines.add("fig.savefig('" + basefp + "bottomFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");

        Utilities.writeToFile(basefp + "topFig.py", topScriptLines);
        Utilities.writeToFile(basefp + "bottomFig.py", bottomScriptLines);

        Utilities.runPythonScript(basefp + "topFig.py");
        Utilities.runPythonScript(basefp + "bottomFig.py");

    }
    
    
    public void getSTLScores(String basefp, TreeNode stl, List<String> stlPlotScript, double timemin, double timemax, double ymin, double ymax) throws TLIException, InterruptedException, IOException {

        Map<Integer, Double> oneTURob = Crawler.getRobustness(stl, oneTUdetfp);
        //Map<Integer, Double> oneTUsmc = Crawler.getComputeSatisfyingPercent(stl, oneTUstofp);
        Map<Integer, Double> twoTURob = Crawler.getRobustness(stl, twoTUdetfp);
        //Map<Integer, Double> twoTUsmc = Crawler.getComputeSatisfyingPercent(stl, twoTUstofp);
        Map<Integer, Double> threeTURob = Crawler.getRobustness(stl, threeTUdetfp);
        //Map<Integer, Double> threeTURob = new HashMap<>();
        
        Map<Integer,Double> pulseRob = Crawler.getRobustness(stl, pulsedetfp);
        
        List<String> oneTURobLines = new ArrayList<>();
        //List<String> oneTUsmcLines = new ArrayList<>();
        List<String> twoTURobLines = new ArrayList<>();
        //List<String> twoTUsmcLines = new ArrayList<>();
        List<String> threeTURobLines = new ArrayList<>();
        
        List<String> pulseRobLines = new ArrayList<>();

        //List<String> oneTUscores = new ArrayList<>();
        //List<String> twoTUscores = new ArrayList<>();

        for (Integer i : oneTURob.keySet()) {
            oneTURobLines.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i));
            //oneTUscores.add(i + "," + onetudet.get(i) + "," + oneTURob.get(i) + "," + oneTUsmc.get(onetustorev.get(onetudet.get(i))));
        }

        //for (Integer i : oneTUsmc.keySet()) {
        //    oneTUsmcLines.add(i + "," + onetusto.get(i) + "," + oneTUsmc.get(i));
        //}

        for (Integer i : twoTURob.keySet()) {
            twoTURobLines.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i));
            //twoTUscores.add(i + "," + twotudet.get(i) + "," + twoTURob.get(i) + "," + twoTUsmc.get(twotustorev.get(twotudet.get(i))));
        }

        //for (Integer i : twoTUsmc.keySet()) {
        //    twoTUsmcLines.add(i + "," + twotusto.get(i) + "," + twoTUsmc.get(i));
        //}

        for (Integer i : threeTURob.keySet()) {
            threeTURobLines.add(i + "," + threetudet.get(i) + "," + threeTURob.get(i));
        }
        
        for (Integer i : pulseRob.keySet()) {
            pulseRobLines.add(i + "," + pulsedet.get(i) + "," + pulseRob.get(i));
        }

        Utilities.writeToFile(basefp + "1tuRobustness.csv", oneTURobLines);
        //Utilities.writeToFile(basefp + "1tuSMC.csv", oneTUsmcLines);
        Utilities.writeToFile(basefp + "2tuRobustness.csv", twoTURobLines);
        //Utilities.writeToFile(basefp + "2tuSMC.csv", twoTUsmcLines);
        Utilities.writeToFile(basefp + "3tuRobustness.csv", threeTURobLines);
        
        Utilities.writeToFile(basefp + "pulseRobustness.csv", pulseRobLines);

        //Utilities.writeToFile(basefp + "1tuScores.csv", oneTUscores);
        //Utilities.writeToFile(basefp + "2tuScores.csv", twoTUscores);

        List<String> topcsv = new ArrayList<>();
        topcsv.add("Score,Circuit Configuration");
        List<String> bottomcsv = new ArrayList<>();
        bottomcsv.add("Score,Circuit Configuration");

        Set<Double> scores = new HashSet<>();
        scores.addAll(oneTURob.values());
        scores.addAll(twoTURob.values());
        scores.addAll(threeTURob.values());
        scores.addAll(pulseRob.values());

        List<String> topScriptLines = new ArrayList<>();
        List<String> bottomScriptLines = new ArrayList<>();

        topScriptLines.add("import matplotlib\n"
                + "matplotlib.use('agg',warn=False, force=True)\n"
                + "from matplotlib import pyplot as plt\n"
                + "from matplotlib import patches as patches\n"
                + "\n"
                + "fig = plt.figure()\n");

        bottomScriptLines.add("import matplotlib\n"
                + "matplotlib.use('agg',warn=False, force=True)\n"
                + "from matplotlib import pyplot as plt\n"
                + "from matplotlib import patches as patches\n"
                + "\n"
                + "fig = plt.figure()\n");

        //topScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));
        //bottomScriptLines.addAll(PyPlotAdaptor.generateSTLCoverScript(stl));
        
        topScriptLines.addAll(stlPlotScript);
        bottomScriptLines.addAll(stlPlotScript);

        topScriptLines.add("\n");
        bottomScriptLines.add("\n");

        List<Double> sorted = new ArrayList<>(scores);

        List<String> colors = new ArrayList<>();
        /*for (int i = 0; i < 5; i++) {
            String color = "";
            do {
                color = Utilities.generateRandomColor();
            } while (colors.contains(color));
            colors.add(color);
        }*/
        colors.add("D4AC0D");
        colors.add("FF0000");
        colors.add("047504");
        colors.add("FF5733");
        colors.add("900C3F");
        
        int sigcount = 0;

        Collections.sort(sorted, Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + onetudet.get(index));
                    topScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(i), onetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + twotudet.get(index));
                    topScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(i), twotudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + threetudet.get(index));
                    topScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(i), threetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : pulseRob.keySet()) {
                if (pulseRob.get(index).equals(currScore)) {
                    topcsv.add(currScore + "," + pulsedet.get(index));
                    topScriptLines.addAll(generateSignalScript(pulsedetfp, index, colors.get(i), pulsedet.get(index), sigcount));
                    sigcount++;
                }
            }
        }

        
        sigcount = 0;
        int colorCount = 0;
        for (int i = sorted.size() - 1; i >= sorted.size() - 5; i--) {
            double currScore = sorted.get(i);
            for (Integer index : oneTURob.keySet()) {
                if (oneTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + onetudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(oneTUdetfp, index, colors.get(colorCount), onetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : twoTURob.keySet()) {
                if (twoTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + twotudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(twoTUdetfp, index, colors.get(colorCount), twotudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : threeTURob.keySet()) {
                if (threeTURob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + threetudet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(threeTUdetfp, index, colors.get(colorCount), threetudet.get(index), sigcount));
                    sigcount++;
                }
            }
            for (Integer index : pulseRob.keySet()) {
                if (pulseRob.get(index).equals(currScore)) {
                    bottomcsv.add(currScore + "," + pulsedet.get(index));
                    bottomScriptLines.addAll(generateSignalScript(pulsedetfp, index, colors.get(i), pulsedet.get(index), sigcount));
                    sigcount++;
                }
            }
            colorCount++;
        }

        Utilities.writeToFile(basefp + "top.csv", topcsv);
        Utilities.writeToFile(basefp + "bottom.csv", bottomcsv);

        topScriptLines.add("plt.xlabel(\"time\")\n"
                + "plt.ylabel(\"MEFL\")\n"
                + "plt.xlim(" + timemin + "," + timemax + ")\n"
                + "plt.ylim(" + ymin + "," + ymax + ")\n"
                + "plt.yscale('symlog')");

        bottomScriptLines.add("plt.xlabel(\"time\")\n"
                + "plt.ylabel(\"MEFL\")\n"
                + "plt.xlim(" + timemin + "," + timemax + ")\n"
                + "plt.ylim(" + ymin + "," + ymax + ")\n"
                + "plt.yscale('symlog')");

        topScriptLines.add("art = []\n"
                + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                + "art.append(lgd)");

        bottomScriptLines.add("art = []\n"
                + "lgd = plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=2)\n"
                + "art.append(lgd)");

        topScriptLines.add("fig.savefig('" + basefp + "topFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");
        bottomScriptLines.add("fig.savefig('" + basefp + "bottomFig.png" + "', dpi=300, additional_artists=art, bbox_inches=\"tight\")");

        Utilities.writeToFile(basefp + "topFig.py", topScriptLines);
        Utilities.writeToFile(basefp + "bottomFig.py", bottomScriptLines);

        Utilities.runPythonScript(basefp + "topFig.py");
        Utilities.runPythonScript(basefp + "bottomFig.py");

    }
    
    
    private static Map<String, Integer> getAssignmentIndexMap(String folderpath) {
        Map<String, Integer> map = new HashMap<>();
        
        File[] filelist = (new File(folderpath)).listFiles();
        for(File f:filelist){
            int index = Integer.valueOf(f.getName());
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            String detailsfp = fp + "assignmentDetails.json";
            JSONObject details = new JSONObject(Utilities.getFileContentAsString(detailsfp));
            map.put(details.getString("assignment"),index);
        }
        
        return map;
    }

    private static Map<Integer, String> getIndexAssignmentMap(String folderpath) {
        Map<Integer, String> map = new HashMap<>();
        
        File[] filelist = (new File(folderpath)).listFiles();
        for(File f:filelist){
            int index = Integer.valueOf(f.getName());
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            String detailsfp = fp + "assignmentDetails.json";
            JSONObject details = new JSONObject(Utilities.getFileContentAsString(detailsfp));
            map.put(index, details.getString("assignment"));
        }
        
        return map;
    }

    

    public static void generateAssignmentsFile(Map<Integer, String> indexAssignment, String fp){
        
        List<String> lines = new ArrayList<>();
        List<Integer> indices = new ArrayList<>(indexAssignment.keySet());
        Collections.sort(indices);
        for(int i:indices){
            lines.add(i + "," + indexAssignment.get(i));
        }
        Utilities.writeToFile(fp, lines);
    }
    
    
    public static void main(String[] args) throws TLIException, InterruptedException, IOException {

        CrawlerTest ct = new CrawlerTest();

        
        
        //ct.fullScoreTest(50.00, 1000.00);
        //ct.fullScoreTest(50.00, 10000.00);
        //ct.fullScoreTest(10.00, 1000.00);

        //Test for a specific STL
        //ct.testSTL();
    }

}
