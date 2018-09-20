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
import org.junit.Test;

/**
 *
 * @author prash
 */
public class AnalyticsTest {
    
    private final String basefp = Utilities.getLibFilepath() + "computationalTests" + Utilities.getSeparater();
    
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
    
    
    public void executeScript(String script) throws IOException, InterruptedException{
        StringBuilder commandBuilder = null;
        if(Utilities.isLinux()){
            commandBuilder = new StringBuilder("/usr/bin/python " + script);
        }
        else {
            System.out.println("Not supported yet. Program exiting");
            System.exit(-1);
        }
        String command = commandBuilder.toString();
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        proc.waitFor();
        
        InputStream is = proc.getInputStream();
        InputStream es = proc.getErrorStream();
        OutputStream os = proc.getOutputStream();
        is.close();
        es.close();
        os.close();
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
                
                executeScript(assignmentfp + "oneTUscores.py");
                executeScript(assignmentfp + "twoTUscores.py");
                
                executeScript(assignmentfp + "scatter.py");
            }
            
        }
        
    }
    
    
    public static void main(String[] args) throws IOException, InterruptedException{
        
        double xthresh = 50;
        double ythresh = 1000;
        
        AnalyticsTest analytics = new AnalyticsTest();
        analytics.generateFiguresTest(xthresh,ythresh);
        
        
        
    }
    
}
