/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class AnalyticsTest {
    
    private final static String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
    
    private void generateRobSMCScript(String fp, String assignmentfp, String filename){
        List<String[]> lines = Utilities.getCSVFileContentAsList(fp);
        List<String> script = new ArrayList<>();
        
        script.add("import matplotlib.pyplot as plt");
        script.add("fig = plt.figure()");
        
        String x = "";
        String y1 = "";
        String y2 = "";
        
        for(int i=0;i<lines.size()-1;i++){
            x += lines.get(i)[0] + ",";
            y1 += lines.get(i)[3] + ",";
            y2 += lines.get(i)[2] + ",";
        }
        x += lines.get(lines.size()-1)[0];
        y1 += lines.get(lines.size()-1)[3];
        y2 += lines.get(lines.size()-1)[2];
        
        
        script.add("x = [" + x + "]");
        script.add("y1 = ["+ y1 + "]");
        script.add("y2 = ["+ y2 + "]");
        script.add("ax1 = fig.add_subplot(111)");
        script.add("ax2 = ax1.twinx()");
 
        script.add("ln1 = ax1.plot(x,y1,'g-', label = 'SMC')");
        script.add("ln2 = ax2.plot(x,y2,'b-', label = 'Rob')");
        
        script.add("ax2.spines['bottom'].set_position('zero')");
        script.add("ax2.spines['bottom'].set_linestyle(':')");
        
        script.add("ax1.set_xlabel('Assignment Index')");
        script.add("ax1.set_ylabel('Satisfying Percentage')");
        script.add("ax2.set_ylabel('Robustness')");
        script.add("lns = ln1 + ln2");
        script.add("labs = [l.get_label() for l in lns]");
        script.add("ax1.legend(lns,labs,loc=0)");
        script.add("fig.savefig('" + assignmentfp + filename + ".png', dpi=300)");
        
        Utilities.writeToFile(assignmentfp + filename + ".py", script);
        
    }
    
    
    private void generateScoreScatterPlot(List<String> fplist, String assignmentfp){
        
        List<String> script = new ArrayList<>();
        
        script.add("import matplotlib.pyplot as plt");
        script.add("fig = plt.figure()");
        List<String[]> lines = new ArrayList<>();
        
        for(String fp:fplist){
             lines.addAll(Utilities.getCSVFileContentAsList(fp));
        }
        
        String x = "";
        String y = "";
        
        for(int i=0;i<lines.size()-1;i++){
            x += lines.get(i)[3] + ",";
            y += lines.get(i)[2] + ",";
        }
        x += lines.get(lines.size()-1)[3];
        y += lines.get(lines.size()-1)[2];
        
        script.add("x = [" + x + "]");
        script.add("y = ["+ y + "]");
        script.add("ax = fig.add_subplot(111)");
 
        script.add("ln = ax.plot(x,y,'b.')");
        //script.add("ln = ax.scatter(x,y)");
        
        //script.add("ax.spines['bottom'].set_position('zero')");
        //script.add("ax.spines['bottom'].set_linestyle('-')");
        
        script.add("ax.set_xlabel('Satisfying Percentage')");
        script.add("ax.set_ylabel('Robustness')");
        script.add("fig.savefig('" + assignmentfp + "scatter.png', dpi=300)");
        
        Utilities.writeToFile(assignmentfp + "scatter.py", script);
        
    }
    
    
    public void generateFiguresTest(double xthresh, double ythresh) throws IOException, InterruptedException{
        
        
        String resultsfp = basefp + (xthresh + "_" + ythresh);
       
        
        File[] tufilelist = (new File(resultsfp)).listFiles();
        for(File tuf:tufilelist){
            File[] assignmentFilelist = tuf.listFiles();
            for(File f:assignmentFilelist){
                System.out.println( tuf.getName() + "-" + f.getName());
                String assignmentfp = f.getAbsolutePath();
                if(!assignmentfp.endsWith("" + Utilities.getSeparater())){
                    assignmentfp += Utilities.getSeparater();
                }
                generateRobSMCScript(assignmentfp + "1tuScores.csv", assignmentfp, "oneTUscores");
                generateRobSMCScript(assignmentfp + "2tuScores.csv", assignmentfp, "twoTUscores");
                
                List<String> fplist = new ArrayList<>();
                fplist.add(assignmentfp + "1tuScores.csv");
                fplist.add(assignmentfp + "2tuScores.csv");
                
                generateScoreScatterPlot(fplist,assignmentfp);
                
                Utilities.runPythonScript(assignmentfp + "oneTUscores.py");
                Utilities.runPythonScript(assignmentfp + "twoTUscores.py");
                
                Utilities.runPythonScript(assignmentfp + "scatter.py");
                
            }
            
        }
        
    }
    
    public static void copySimulations() throws IOException{
        String simfp = basefp + "simulations" + Utilities.getSeparater();
        Utilities.makeDirectory(simfp);
        String onetusimfp = simfp + "1tu" + Utilities.getSeparater();
        String twotusimfp = simfp + "2tu" + Utilities.getSeparater();
        String threetusimfp = simfp + "3tu" + Utilities.getSeparater();
        
        Utilities.makeDirectory(onetusimfp);
        Utilities.makeDirectory(twotusimfp);
        Utilities.makeDirectory(threetusimfp);
        
        String baseResultsfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "circuits" + Utilities.getSeparater();

        String oneTUfp = baseResultsfp + "1tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        String twoTUfp = baseResultsfp + "2tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        String threeTUfp = baseResultsfp + "3tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        

        String oneTUdetfp = oneTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        String twoTUdetfp = twoTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        String threeTUdetfp = threeTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        
        File[] onetulist = (new File(oneTUdetfp)).listFiles();
        File[] twotulist = (new File(twoTUdetfp)).listFiles();
        File[] threetulist = (new File(threeTUdetfp)).listFiles();
        
        for(File f:onetulist){
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            if(Utilities.validFilepath(fp + "out0.png")){
                FileUtils.copyFile(new File(fp + "out0.png"), new File(onetusimfp + f.getName() + ".png"));
            }
        }
        
        for(File f:twotulist){
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            if(Utilities.validFilepath(fp + "out0.png")){
                FileUtils.copyFile(new File(fp + "out0.png"), new File(twotusimfp + f.getName() + ".png"));
            }
        }
        
        for(File f:threetulist){
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            if(Utilities.validFilepath(fp + "out0.png")){
                FileUtils.copyFile(new File(fp + "out0.png"), new File(threetusimfp + f.getName() + ".png"));
            }
        }
        
    }
    
    public void findConcaves() throws TLIException, InterruptedException, IOException{

        String concavefp = basefp + "concaves" + Utilities.getSeparater();
        Utilities.makeDirectory(concavefp);
        
        
        String baseResultsfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "circuits" + Utilities.getSeparater();

        String oneTUfp = baseResultsfp + "1tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        String twoTUfp = baseResultsfp + "2tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        String threeTUfp = baseResultsfp + "3tu" + Utilities.getSeparater() + "allRuns" + Utilities.getSeparater();
        

        String oneTUdetfp = oneTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        String twoTUdetfp = twoTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        String threeTUdetfp = threeTUfp + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        
        File[] onetulist = (new File(oneTUdetfp)).listFiles();
        File[] twotulist = (new File(twoTUdetfp)).listFiles();
        File[] threetulist = (new File(threeTUdetfp)).listFiles();
        
        printConcaves(concavefp, "1tu",onetulist);
        printConcaves(concavefp, "2tu",twotulist);
        printConcaves(concavefp, "3tu",threetulist);
        
        
    }
    
    private static void printConcaves(String concavefp, String tu, File[] filelist) throws TLIException, InterruptedException, IOException{
        
        //System.out.println("Started print Concaves function");
        for(File f:filelist){
            
            String fp = f.getAbsolutePath();
            
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            
            Signal s = Utilities.readSignalsFromCSV(fp + "out0.csv").get(0);
            List<Point> points = new ArrayList<>(s.getPoints());
            boolean found = false;
            
            boolean increasing = false;
            double startVal = 0;
            double peakTime = points.get(0).getX();
            double peakVal = points.get(0).getY();
            double endVal = 0;
            for(int i=0;i<points.size()-1;i++){
                
                if(points.get(i).getX() <= 330){
                    endVal = points.get(i).getY();
                }
                
                if(points.get(i).getY() > peakVal){
                    peakVal = points.get(i).getY();
                    peakTime = points.get(i).getX();
                }
                
                if(increasing){
                    if (points.get(i + 1).getY() < points.get(i).getY()) {
                        found = true;
                        //break;
                    }
                } else {
                    if (points.get(i + 1).getY() > points.get(i).getY()) {
                        increasing = true;
                    }
                }
            }
            
            boolean lims = false;
            if(found){
                startVal = points.get(0).getY();
                if( (peakVal-startVal) > 1000 ){
                    if(peakTime < 250){
                        System.out.println("Peak Val " + peakVal);
                        System.out.println("End Val " + endVal);
                        lims = true;
                        
                        /*if( (peakVal - endVal) > 100){
                        }*/
                    }
                }
                
            }
            
            if(found && lims){
                System.out.println("Concave   :: " + tu + " :: " + f.getName());
                System.out.println("Peak time :: " + peakTime);
                System.out.println("Start Val :: " + startVal);
                System.out.println("Peak Val  :: " + peakVal);
                System.out.println("End Val   :: " + endVal);
                System.out.println("------------------------");
                List<String> script = new ArrayList<>();
                script.add("import matplotlib\n" 
                        + "matplotlib.use('agg',warn=False, force=True)\n" 
                        + "from matplotlib import pyplot as plt\n" 
                        + "from matplotlib import patches as patches\n" 
                        + "\n" 
                        + "fig = plt.figure()");
                String x = "sx = [";
                String y = "sy = [";
                
                for(int i=0;i<points.size()-1;i++){
                    x += points.get(i).getX() + ",";
                    y += points.get(i).getY() + ",";
                }
                Point last = points.get(points.size()-1);
                x += last.getX() + "]";
                y += last.getY() + "]";
                
                script.add(x);
                script.add(y);
                script.add("plt.plot(sx,sy,color='black',linestyle='solid')");
                
                script.add("plt.xlabel(\"time\")\n" 
                        + "plt.ylabel(\"out0\")");
                script.add("plt.xlim(0.0,900.0)");
                
                script.add("fig.savefig('" + concavefp + tu + "_" + f.getName() + ".png', dpi=300)");
                
                Utilities.writeToFile(concavefp + tu + "_" + f.getName() + ".py", script);
                Utilities.runPythonScript(concavefp + tu + "_" + f.getName() + ".py");
                
            }
            
            
        }
    }
    
    
    public static void main(String[] args) throws IOException, InterruptedException, TLIException{
    
        
        AnalyticsTest analytics = new AnalyticsTest();
        //analytics.generateFiguresTest(50.00,1000.00);
        //analytics.generateFiguresTest(50.00,10000.00);
        
        //analytics.copySimulations();
        //analytics.findConcaves();
        
        String pulsefp = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater() + "pulse_circuit" + Utilities.getSeparater();
        String pulseconcavefp = pulsefp + "concaves" + Utilities.getSeparater();
        Utilities.makeDirectory(pulseconcavefp);
        String pulseresults = pulsefp + "allRuns" + Utilities.getSeparater() + "DeterministicRed0" + Utilities.getSeparater() + "0" + Utilities.getSeparater();
        File[] pulselist = (new File(pulseresults)).listFiles();
        printConcaves(pulseconcavefp, "pulse",pulselist);
        
        //copySimulations();
        
        //analytics.findConcaves();
    }
    
}
