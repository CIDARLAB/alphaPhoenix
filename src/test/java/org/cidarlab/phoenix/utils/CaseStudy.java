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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.SimulatedAnnealing;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.examples.PulseCircuitTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit1tuTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit2tuTest;
import org.cidarlab.phoenix.examples.exhaustive.deterministic.Circuit3tuTest;
import static org.cidarlab.phoenix.utils.CrawlerTest.oneTUdetfp;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class CaseStudy {
    
    private static double[] lowmin = {350, 550, 350, 1500};
    private static double[] lowmax = {450, 650, 850, 2500};

    private static double[] highmin = {1500, 2000, 2000, 5000, 50000};
    private static double[] highmax = {2500, 5000, 10000, 10000, 100000};
    
    public static void main(String[] args) throws URISyntaxException, SBOLValidationException, SynBioHubException, XMLStreamException, IOException, MalformedURLException, FileNotFoundException, TLIException, InterruptedException {
        Circuit1tuTest c1tu = new Circuit1tuTest();
        Circuit2tuTest c2tu = new Circuit2tuTest();
        Circuit3tuTest c3tu = new Circuit3tuTest();
        PulseCircuitTest pulse = new PulseCircuitTest();
    
        //c1tu.testExhaustiveAssignment();
        //c2tu.testExhaustiveAssignment();
        
        //pulse.testPulseDeterministic();
        
        //c3tu.testExhaustiveAssignment();
        
        //caseStudyLevels();
        //caseStudyLowHigh();
        //caseStudyPulse();
        
        
        //caseStudySimulatedAnnealing("twoTU");
        //caseStudySimulatedAnnealing("threeTU");
        
        CrawlerTest ct = new CrawlerTest();
        
        
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
            EventNode en = new EventNode(new AlwaysNode(bound,0,240),30,60);
            AlwaysNode an = new AlwaysNode(bound,60,300);
            
            Utilities.writeToFile(afp + "stl.txt", an.toString());
            Utilities.writeToFile(efp + "stl.txt", en.toString());
            
            ct.getSTLScores(afp, an, alwaysPlotScript(an,0),0,600);
            ct.getSTLScores(efp, en, eventAlwaysPlotScript(en,0),0,600);
            
        }
        
        
    }
    
    public static void caseStudyLowHigh() throws TLIException, InterruptedException, IOException{
        CrawlerTest ct = new CrawlerTest();
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String lowhighfp = caseStudyfp + "lowToHigh" + Utilities.getSeparater();
        Utilities.makeDirectory(lowhighfp);
        
        
        for(int i=0;i<lowmin.length;i++){
            String lowfp = lowhighfp + "low_" + lowmin[i] + "_" + lowmax[i] + Utilities.getSeparater();
            Utilities.makeDirectory(lowfp);
            
            ConjunctionNode low = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",lowmax[i]),new LinearPredicateLeaf(RelOperation.GE,"out0",lowmin[i]));
            for(int j=0;j<highmin.length;j++){
                String fp = lowfp + "high_" + highmin[j] + "_" + highmax[j] + Utilities.getSeparater();
                Utilities.makeDirectory(fp);
                AlwaysNode an1 = new AlwaysNode(new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",highmax[j]),new LinearPredicateLeaf(RelOperation.GE,"out0",highmin[j])),0,120);
                EventNode en1 = new EventNode(an1,60,180);
                ConjunctionNode stl = new ConjunctionNode(low,en1);
                Utilities.writeToFile(fp + "stl.txt", stl.toString());
                
                AlwaysNode an0 = new AlwaysNode(low,0,1);
                List<String> stlplot = new ArrayList<>();
                stlplot.addAll(alwaysPlotScript(an0,0));
                stlplot.addAll(eventAlwaysPlotScript(en1,1));
                ct.getSTLScores(fp, stl, stlplot,0,300);
                
            }
        }
                
    }
    
    public static void caseStudyPulse() throws TLIException, InterruptedException, IOException{
        
        CrawlerTest ct = new CrawlerTest();
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String pulsefp = caseStudyfp + "pulse" + Utilities.getSeparater();
        Utilities.makeDirectory(pulsefp);
    
        ConjunctionNode start = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE, "out0", 0),new LinearPredicateLeaf(RelOperation.LE,"out0",150));
        ConjunctionNode mid = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE, "out0", 1600),new LinearPredicateLeaf(RelOperation.LE,"out0",2000));
        ConjunctionNode end = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.GE, "out0", 600),new LinearPredicateLeaf(RelOperation.LE,"out0",800));
        
        AlwaysNode anmid = new AlwaysNode(mid,0,60);
        AlwaysNode anend = new AlwaysNode(end,120,180);
        
        EventNode en = new EventNode(new ConjunctionNode(anmid,anend),60,120);
        
        List<String> stlplot = new ArrayList<>();
        
        stlplot.addAll(alwaysPlotScript(new AlwaysNode(start,0,1),0));
        stlplot.addAll(eventAlwaysPlotScript(new EventNode(anmid,60,120),1));
        stlplot.addAll(alwaysPlotScript(new AlwaysNode(end,240,300),1));
        
        ConjunctionNode stl = new ConjunctionNode(start,en);
        
        Utilities.writeToFile(pulsefp + "stl.txt", stl.toString());
                
        
        ct.getSTLScores(pulsefp, stl, stlplot,0,300,0,5000);
        
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
        
        if(stl.child instanceof ConjunctionNode){
            ConjunctionNode ancon = (ConjunctionNode)stl.child;
            AlwaysNode anleft = (AlwaysNode)ancon.left;
            AlwaysNode anright = (AlwaysNode)ancon.right;
            
            lines.addAll(eventAlwaysPlotScript(new EventNode(anleft,stl.low,stl.high),count));
            count += 2;
            lines.addAll(eventAlwaysPlotScript(new EventNode(anright,stl.low,stl.high),count));
            
            
        } else {
            AlwaysNode an = (AlwaysNode) stl.child;
            ConjunctionNode cn = (ConjunctionNode) an.child;

            LinearPredicateLeaf lt = (LinearPredicateLeaf) cn.left;
            LinearPredicateLeaf gt = (LinearPredicateLeaf) cn.right;

            String x = "bx" + count + " = [" + stl.low + "," + stl.high + "," + stl.high + "," + stl.low + "," + stl.low + "]";
            String y = "by" + count + " = [" + lt.threshold + "," + lt.threshold + "," + gt.threshold + "," + gt.threshold + "," + lt.threshold + "]";

            lines.add(x);
            lines.add(y);

            lines.add("plt.fill(bx" + count + ",by" + count + ",facecolor='g',alpha=0.4)");
            lines.add("plt.plot(bx" + count + ",by" + count + ",color='black')");

            count++;
            lines.addAll(alwaysPlotScript(new AlwaysNode(cn, an.low + stl.high, an.high + stl.high), count));
        }
        
        
        
        
        return lines;
    }
    
    private static void caseStudySimulatedAnnealing(String satype) throws TLIException, InterruptedException, IOException, URISyntaxException, SynBioHubException, SBOLValidationException{
        
        CrawlerTest ct = new CrawlerTest();
        
        int threads = 1;
        int runCount = 20;
        
        String caseStudyfp = Utilities.getLibFilepath() + "caseStudy" + Utilities.getSeparater();
        Utilities.makeDirectory(caseStudyfp);
        
        String safp = caseStudyfp + "simulatedAnnealing" + Utilities.getSeparater();
        Utilities.makeDirectory(safp);
        
        String typefp = safp + satype + Utilities.getSeparater();
        Utilities.makeDirectory(typefp);
        
        
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixParts/PhoenixParts_collection/1";

        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T, typefp);
        
        
        
        for(int i=0;i<lowmin.length;i++){
            String lowfp = typefp + "low_" + lowmin[i] + "_" + lowmax[i] + Utilities.getSeparater();
            Utilities.makeDirectory(lowfp);
            
            ConjunctionNode low = new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",lowmax[i]),new LinearPredicateLeaf(RelOperation.GE,"out0",lowmin[i]));
            for(int j=0;j<highmin.length;j++){
                String fp = lowfp + "high_" + highmin[j] + "_" + highmax[j] + Utilities.getSeparater();
                Utilities.makeDirectory(fp);
                AlwaysNode an1 = new AlwaysNode(new ConjunctionNode(new LinearPredicateLeaf(RelOperation.LE,"out0",highmax[j]),new LinearPredicateLeaf(RelOperation.GE,"out0",highmin[j])),0,120);
                EventNode en1 = new EventNode(an1,60,180);
                ConjunctionNode stl = new ConjunctionNode(low,en1);
                Utilities.writeToFile(fp + "stl.txt", stl.toString());
                
                AlwaysNode an0 = new AlwaysNode(low,0,1);
                List<String> stlplot = new ArrayList<>();
                stlplot.addAll(alwaysPlotScript(an0,0));
                stlplot.addAll(eventAlwaysPlotScript(en1,1));
               
                String resultsfp = "";
                int componentLength = 0;
                String eugfp = "";
                if(satype.equals("twoTU")){
                    componentLength = 8;
                    resultsfp = CrawlerTest.twoTUdetfp;
                    eugfp = safp + "twoTU.eug";
                } else if(satype.equals("threeTU")){
                    componentLength = 12;
                    resultsfp = CrawlerTest.threeTUdetfp;
                    eugfp = safp + "threeTU.eug";
                } else if(satype.equals("pulse")){
                    componentLength = 16;
                    resultsfp = CrawlerTest.pulsedetfp;
                    eugfp = safp + "pulseCircuitSpecific.eug";
                }
                Map<Integer, Double> exhaustiveResults = Crawler.getRobustness(stl, resultsfp);
                
                List<Integer> keys = new ArrayList<>(exhaustiveResults.keySet());
                
                double maxScore = exhaustiveResults.get(keys.get(0));
                for(int key:exhaustiveResults.keySet()){
                    if(exhaustiveResults.get(key) > maxScore){
                        maxScore = exhaustiveResults.get(key);
                    }
                }
                
                
                SimulatedAnnealing sa;
                List<Module> modules;
                List<Module> decomposed;
                
                for (int k = 0; k < threads; k++) {
                    Args args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.STOCHASTIC, runCount, 0.99, 0.5, Args.Assignment.SIMULATED_ANNEALING);
                    String iterationfp = fp + "iteration" + k + Utilities.getSeparater();
                    Utilities.makeDirectory(iterationfp);
                    args.setProjectFolder(iterationfp);

                    modules = MiniEugeneAdaptor.getStructures(eugfp, componentLength, "sa");
                    decomposed = new ArrayList<>();
                    for (Module m : modules) {
                        decomposed.add(Controller.decompose(m, Args.Decomposition.PR_C_T));
                    }

                    sa = new SimulatedAnnealing();
                    sa.solve(decomposed, lib, stl, args);
                }
                generateSAplot(maxScore,fp);
            }
        }
        
    }
    
    
    public static void generateSAplot(double maxScore, String fp) throws InterruptedException, IOException{
        List<String> lines = new ArrayList<>();
        lines.add("import matplotlib\n" 
                + "matplotlib.use('agg',warn=False, force=True)\n" 
                + "from matplotlib import pyplot as plt\n" 
                + "from matplotlib import patches as patches\n" 
                + "\n" 
                + "fig = plt.figure()\n\n");
        
        File[] files = (new File(fp)).listFiles();
        int assignmentcount = 0;
        int filecount = 0;
        for(File f:files){
            if(f.getName().startsWith("iteration")){
                String resultscsvfp = f.getAbsolutePath();
                if(!resultscsvfp.endsWith("" + Utilities.getSeparater())){
                    resultscsvfp += Utilities.getSeparater();
                }
                resultscsvfp += "saResults" + Utilities.getSeparater() + "results.csv";
                List<String[]> resultlines = Utilities.getCSVFileContentAsList(resultscsvfp);
                assignmentcount = resultlines.size();
                
                String x = "sx" + filecount  +" = [0";
                String y = "sy" + filecount  +" = [" + Double.valueOf(resultlines.get(0)[1]);
                
                for(int i=1;i<resultlines.size();i++){
                    x += (","  + i); 
                    y += ("," + Double.valueOf(resultlines.get(i)[1]));
                }
                x += "]";
                y += "]";
                
                lines.add(x);
                lines.add(y);
                lines.add("plt.plot(sx" + filecount + ",sy" + filecount +  ", linewidth=0.5, color='#0000FF',linestyle='solid')");
                
                filecount++;
            }
        }
        
        
        lines.add("plt.plot([0," + (assignmentcount-1) + "],[" + maxScore + "," + maxScore + "],'k-')");
        
        lines.add("plt.xlabel(\"Assignment Index\")\n" 
                + "plt.ylabel(\"Robustness\")\n");
        lines.add("fig.savefig('" + fp + "scoreplot.png', dpi=300)");
        
        
        Utilities.writeToFile(fp + "scoreplot.py", lines);
        Utilities.runPythonScript(fp + "scoreplot.py");
    
        
        
    }
    
    
}
