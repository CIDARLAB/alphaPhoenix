/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.examples.PulseCircuitTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit1tuTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit2tuTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit3tuTest;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;

/**
 *
 * @author prash
 */
public class CaseStudy {
    
    public static void main(String[] args) throws URISyntaxException, SBOLValidationException, SynBioHubException, XMLStreamException, IOException, MalformedURLException, FileNotFoundException, TLIException, InterruptedException {
        Circuit1tuTest c1tu = new Circuit1tuTest();
        Circuit2tuTest c2tu = new Circuit2tuTest();
        Circuit3tuTest c3tu = new Circuit3tuTest();
        PulseCircuitTest pulse = new PulseCircuitTest();
    
        //pulse.testPulseDeterministic();
        
        //c1tu.testExhaustiveAssignment();
        //c2tu.testExhaustiveAssignment();
        //c3tu.testExhaustiveAssignment();
        
        //caseStudyLevels();
        caseStudyLowHigh();
        
    }
    
    
    public static void caseStudyLevels() throws TLIException, InterruptedException, IOException{
        
        CrawlerTest ct = new CrawlerTest();
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String levelsfp = caseStudyfp + "levels" + Utilities.getSeparater();
        Utilities.makeDirectory(levelsfp);
        
        double[] highlist  =  {0  , 0   , 100, 500 , 2000, 5000 , 10000, 20000, 50000 };
        double[] lowlist   =  {100, 1000, 500, 2000, 5000, 10000, 20000, 50000, 100000};
        
        String eventalwaysfp = levelsfp + "eventAlways" + Utilities.getSeparater();
        String alwaysfp = levelsfp + "always" + Utilities.getSeparater();
        
        Utilities.makeDirectory(eventalwaysfp);
        Utilities.makeDirectory(alwaysfp);
        
        
        for(int i=0;i<lowlist.length;i++){
            double high = highlist[i];
            double low = lowlist[i];
            
            String efp = eventalwaysfp + low + "_" + high + Utilities.getSeparater();
            String afp = alwaysfp + low + "_" + high + Utilities.getSeparater();
            
            Utilities.makeDirectory(efp);
            Utilities.makeDirectory(afp);
            
            LinearPredicateLeaf lessthan = new LinearPredicateLeaf(RelOperation.LE,"out0",high);
            LinearPredicateLeaf greaterthan = new LinearPredicateLeaf(RelOperation.GE,"out0",low);
            ConjunctionNode bound = new ConjunctionNode(lessthan,greaterthan);
            EventNode en = new EventNode(new AlwaysNode(bound,0,400),700,800);
            AlwaysNode an = new AlwaysNode(bound,700,900);
            
            Utilities.writeToFile(afp + "stl.txt", an.toString());
            Utilities.writeToFile(efp + "stl.txt", en.toString());
            
            ct.getSTLScores(afp, an, alwaysPlotScript(an,0),600,1500);
            ct.getSTLScores(efp, en, eventAlwaysPlotScript(en,0),600,1500);
            
        }
        
        
    }
    
    public static void caseStudyLowHigh() throws TLIException, InterruptedException, IOException{
        CrawlerTest ct = new CrawlerTest();
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String lowhighfp = caseStudyfp + "lowToHigh" + Utilities.getSeparater();
        Utilities.makeDirectory(lowhighfp);
        double[] lowmin = {350, 550, 350, 1500};
        double[] lowmax = {450, 650, 850, 2500};
        
        double[] highmin = {1500, 2000, 2000 , 5000 , 50000 };
        double[] highmax = {2500, 5000, 10000, 10000, 100000};
        
        for(int i=0;i<lowmin.length;i++){
            String lowfp = lowhighfp + "low_" + lowmin[i] + "_" + lowmax[i] + Utilities.getSeparater();
            Utilities.makeDirectory(lowfp);
            
            AlwaysNode an0 = new AlwaysNode(new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",lowmax[i]),new LinearPredicateLeaf(RelOperation.GE,"out0",lowmin[i])),580,600);
            for(int j=0;j<highmin.length;j++){
                String fp = lowfp + "high_" + highmin[j] + "_" + highmax[j] + Utilities.getSeparater();
                Utilities.makeDirectory(fp);
                AlwaysNode an1 = new AlwaysNode(new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",highmax[j]),new LinearPredicateLeaf(RelOperation.GE,"out0",highmin[j])),900,1200);
                
                ConjunctionNode stl = new ConjunctionNode(an0,an1);
                Utilities.writeToFile(fp + "stl.txt", stl.toString());
                
                List<String> stlplot = new ArrayList<>();
                stlplot.addAll(alwaysPlotScript(an0,0));
                stlplot.addAll(alwaysPlotScript(an1,1));
                ct.getSTLScores(fp, stl, stlplot,580,1500);
                
            }
        }
                
    }
    
    public static void caseStudyPulse(){
        
    }
    
    private static List<String> alwaysPlotScript(AlwaysNode stl, int count){
        List<String> lines = new ArrayList<>();
        ConjunctionNode cn = (ConjunctionNode) stl.child;
        
        LinearPredicateLeaf lt = (LinearPredicateLeaf)cn.left;
        LinearPredicateLeaf gt = (LinearPredicateLeaf)cn.right;
        
        String x = "bx" + count + " = [" + stl.low      + "," + stl.high     + "," + stl.high     + "," + stl.low      + "," + stl.low      + "]";
        String y = "by" + count + " = [" + lt.threshold + "," + lt.threshold + "," + gt.threshold + "," + gt.threshold + "," + lt.threshold + "]";
        
        lines.add(x);
        lines.add(y);
        
        lines.add("plt.fill(bx" + count + ",by" + count + ",facecolor='b',alpha=0.6)");
        lines.add("plt.plot(bx" + count + ",by" + count + ",color='black')");
        
        return lines;
    }
    
    private static List<String> eventAlwaysPlotScript(EventNode stl, int count){
        List<String> lines = new ArrayList<>();
        AlwaysNode an = (AlwaysNode)stl.child;
        ConjunctionNode cn = (ConjunctionNode) an.child;
        
        LinearPredicateLeaf lt = (LinearPredicateLeaf)cn.left;
        LinearPredicateLeaf gt = (LinearPredicateLeaf)cn.right;
        
        String x = "bx" + count + " = [" + stl.low      + "," + stl.high     + "," + stl.high     + "," + stl.low      + "," + stl.low      + "]";
        String y = "by" + count + " = [" + lt.threshold + "," + lt.threshold + "," + gt.threshold + "," + gt.threshold + "," + lt.threshold + "]";
        
        lines.add(x);
        lines.add(y);
        
        lines.add("plt.fill(bx" + count + ",by" + count + ",facecolor='g',alpha=0.4)");
        lines.add("plt.plot(bx" + count + ",by" + count + ",color='black')");
        
        count++;
        lines.addAll(alwaysPlotScript(new AlwaysNode(cn,an.low + stl.high, an.high + stl.high), count));
        
        return lines;
    }
    
    
    
}
