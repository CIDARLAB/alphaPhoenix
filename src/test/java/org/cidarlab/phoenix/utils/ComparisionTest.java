/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.SimulatedAnnealing;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Utilities;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class ComparisionTest {
    
    private static final String basefp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater() + "comparision" + Utilities.getSeparater();
    private static final String testedcircfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater(); 
    
    private static final String oneTUeug = testedcircfp + "circuits" + Utilities.getSeparater() + "1tu" + Utilities.getSeparater() + "singleTU.eug"; 
    private static final String twoTUeug = testedcircfp + "circuits" + Utilities.getSeparater() + "2tu" + Utilities.getSeparater() + "doubleTU.eug";
    private static final String threeTUeug = testedcircfp + "circuits" + Utilities.getSeparater() + "3tu" + Utilities.getSeparater() + "tripleTU.eug";
    private static final String pulseEug = testedcircfp + "pulse_circuit" + Utilities.getSeparater() + "pulseCircuitSpecific.eug";
    
    
    private static final String oneTUresults = testedcircfp + "circuits" + Utilities.getSeparater() + "1tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater() + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private static final String twoTUresults = testedcircfp + "circuits" + Utilities.getSeparater() + "2tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater() + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private static final String threeTUresults = testedcircfp + "circuits" + Utilities.getSeparater() + "3tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater() + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    private static final String pulseResults = testedcircfp + "pulse_circuit" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater() + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
    
    //Pick 2 TU/3 TU. Find exhaustive result for a specific STL. 
    //Then do 100 iterations of simulated annealing. 
    
    public static void main(String[] args) throws URISyntaxException, SBOLValidationException, MalformedURLException, SynBioHubException, TLIException, InterruptedException, IOException {
        //runComparisionTest();
        hammAnalyze();
    }
    
    
    private static void hammAnalyze(){
        String noHammfp = basefp + "noHamm" + Utilities.getSeparater();
        String withHammfp = basefp + "withHamm" + Utilities.getSeparater();
        
        System.out.println("Example 0 =====================================");
        double ex0withH = getAvgMax(withHammfp + "ex0" + Utilities.getSeparater());
        double ex0noH = getAvgMax(noHammfp + "ex0" + Utilities.getSeparater());
        double ex0withHsd = getsd(withHammfp + "ex0" + Utilities.getSeparater(),ex0withH);
        double ex0noHsd = getsd(noHammfp + "ex0" + Utilities.getSeparater(),ex0noH);
        System.out.println("With Hamming Distance Average :: " + ex0withH + ". SD = " + ex0withHsd);
        System.out.println("No Hamming Distance Average :: " + ex0noH + ". SD = " + ex0noHsd);
        System.out.println("-----------------------------------------------");
        
        System.out.println("Example 1 =====================================");
        double ex1withH = getAvgMax(withHammfp + "ex1" + Utilities.getSeparater());
        double ex1noH = getAvgMax(noHammfp + "ex1" + Utilities.getSeparater());
        double ex1withHsd = getsd(withHammfp + "ex1" + Utilities.getSeparater(),ex1withH);
        double ex1noHsd = getsd(noHammfp + "ex1" + Utilities.getSeparater(),ex1noH);
        System.out.println("With Hamming Distance Average :: " + ex1withH + ". SD = " + ex1withHsd);
        System.out.println("No Hamming Distance Average :: " + ex1noH + ". SD = " + ex1noHsd);
        System.out.println("-----------------------------------------------");
        
        System.out.println("Example 2 =====================================");
        double ex2withH = getAvgMax(withHammfp + "ex2" + Utilities.getSeparater());
        double ex2noH = getAvgMax(noHammfp + "ex2" + Utilities.getSeparater());
        double ex2withHsd = getsd(withHammfp + "ex2" + Utilities.getSeparater(),ex2withH);
        double ex2noHsd = getsd(noHammfp + "ex2" + Utilities.getSeparater(),ex2noH);
        System.out.println("With Hamming Distance Average :: " + ex2withH + ". SD = " + ex2withHsd);
        System.out.println("No Hamming Distance Average :: " + ex2noH + ". SD = " + ex2noHsd);
        System.out.println("-----------------------------------------------");
        
        System.out.println("Example 3 =====================================");
        double ex3withH = getAvgMax(withHammfp + "ex3" + Utilities.getSeparater());
        double ex3noH = getAvgMax(noHammfp + "ex3" + Utilities.getSeparater());
        double ex3withHsd = getsd(withHammfp + "ex3" + Utilities.getSeparater(),ex3withH);
        double ex3noHsd = getsd(noHammfp + "ex3" + Utilities.getSeparater(),ex3noH);
        System.out.println("With Hamming Distance Average :: " + ex3withH + ". SD = " + ex3withHsd);
        System.out.println("No Hamming Distance Average :: " + ex3noH + ". SD = " + ex3noHsd);
        System.out.println("-----------------------------------------------");
        
    }
    
    private static double getAvgMax(String fp){
        File[] filelist = (new File(fp)).listFiles();
        double scoreTotal = 0.0;
        for(File f:filelist){
            String scorefilefp = f.getAbsolutePath() + Utilities.getSeparater() + "scores.csv";
            List<String[]> lines = Utilities.getCSVFileContentAsList(scorefilefp);
            double maxScore = Double.valueOf(lines.get(lines.size()-1)[2]);
            scoreTotal += maxScore;
        }
        return (scoreTotal/filelist.length);
    }
    
    private static double getsd(String fp, double avg){
        File[] filelist = (new File(fp)).listFiles();
        double scoreTotal = 0.0;
        for(File f:filelist){
            String scorefilefp = f.getAbsolutePath() + Utilities.getSeparater() + "scores.csv";
            List<String[]> lines = Utilities.getCSVFileContentAsList(scorefilefp);
            double maxScore = Double.valueOf(lines.get(lines.size()-1)[2]);
            double sqr = Math.pow((maxScore - avg),2);
            scoreTotal += sqr;
        }
        return Math.sqrt(scoreTotal/filelist.length);
    }
    
    private static void runComparisionTest() throws URISyntaxException, SBOLValidationException, MalformedURLException, SynBioHubException, TLIException, InterruptedException, IOException{
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixReduced/PhoenixReduced_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T,basefp);
        
        //Example 0 - Low to High
        ConjunctionNode ex0cn0 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",200),new LinearPredicateLeaf(RelOperation.LE,"out0",500));
        ConjunctionNode ex0cn1 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",2000),new LinearPredicateLeaf(RelOperation.LE,"out0",5000));
        AlwaysNode ex0an0 = new AlwaysNode(ex0cn1,120,300);
        TreeNode stl0 = new ConjunctionNode(ex0cn0,ex0an0);
        
        String ex0basefp = basefp + "ex0" + Utilities.getSeparater();
        Utilities.makeDirectory(ex0basefp);
        Args ex0args = new Args();
        runComparision(threeTUresults,stl0,threeTUeug, 12,lib,ex0args,ex0basefp);
        
        
        //Example 1 - Low to very High
        ConjunctionNode ex1cn0 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",200),new LinearPredicateLeaf(RelOperation.LE,"out0",500));
        ConjunctionNode ex1cn1 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",1000),new LinearPredicateLeaf(RelOperation.LE,"out0",50000));
        AlwaysNode ex1an0 = new AlwaysNode(ex1cn1,120,300);
        TreeNode stl1 = new ConjunctionNode(ex1cn0,ex1an0);
        
        String ex1basefp = basefp + "ex1" + Utilities.getSeparater();
        Utilities.makeDirectory(ex1basefp);
        Args ex1args = new Args();
        runComparision(threeTUresults,stl1,threeTUeug, 12,lib,ex1args,ex1basefp);
        
        
        //Example 2 - Pulse
        ConjunctionNode ex2cn0 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",0),new LinearPredicateLeaf(RelOperation.LE,"out0",200));
        ConjunctionNode ex2cn1 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",1500),new LinearPredicateLeaf(RelOperation.LE,"out0",2000));
        ConjunctionNode ex2cn2 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",500),new LinearPredicateLeaf(RelOperation.LE,"out0",1000));
        AlwaysNode ex2an0 = new AlwaysNode(ex2cn1,60,120);
        AlwaysNode ex2an1 = new AlwaysNode(ex2cn2,240,300);
        ConjunctionNode ex2cn3 = new ConjunctionNode(ex2an0,ex2an1);
        TreeNode stl2 = new ConjunctionNode(ex2cn0,ex2cn3);
        
        String ex2basefp = basefp + "ex2" + Utilities.getSeparater();
        Utilities.makeDirectory(ex2basefp);
        Args ex2args = new Args();
        
        runComparision(threeTUresults,stl2,threeTUeug, 12,lib,ex2args,ex2basefp);
        
        //Example 3 - Big block of STL
        ConjunctionNode ex3cn0 = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE,"out0",100),new LinearPredicateLeaf(RelOperation.LE,"out0",100000));
        TreeNode stl3 = new AlwaysNode(ex3cn0,0,300);
        
        String ex3basefp = basefp + "ex3" + Utilities.getSeparater();
        Utilities.makeDirectory(ex3basefp);
        Args ex3args = new Args();
        runComparision(pulseResults,stl3, pulseEug, 16,lib,ex3args,ex3basefp);
        
        
        
    }
    
    private static void runComparision(String resultfp, TreeNode stl, String eugfp, int size, Library lib, Args argsremove, String exbasefp) throws TLIException, InterruptedException, IOException{
        
        
        
        /*
        String exhaustivebestfp = exbasefp + "exhaustive.txt";
        Map<Integer,Double> exhaustiveScores = Crawler.getRobustness(stl, resultfp);
        double maxScore = exhaustiveScores.get(0);
        for(Integer i:exhaustiveScores.keySet()){
            if(exhaustiveScores.get(i) > maxScore){
                maxScore = exhaustiveScores.get(i);
            }
        }
        Utilities.writeToFile(exhaustivebestfp, "" + maxScore);*/
        
        
        for(int i=0;i<100;i++){
            List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, size, "someModule");
            List<Module> decomposed = new ArrayList<>();
            for (Module m : modules) {
                decomposed.add(Controller.decompose(m, Args.Decomposition.PR_C_T));
            }
            Args args = new Args();
            args.setProjectFolder(exbasefp + i + Utilities.getSeparater());
            SimulatedAnnealing sa = new SimulatedAnnealing();
            sa.solve(decomposed, lib, stl, args);
        }
        
        /*double starting = 10000;
        double coolingrate = 0.045;
        int count = 0;
        while(starting > 1){
            starting *= (1-coolingrate);
            count++;
        }
        System.out.println("Count :: " + count);*/
    }
    
    
    
}
