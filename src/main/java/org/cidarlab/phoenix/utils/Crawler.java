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
import java.util.List;
import java.util.Set;
import lombok.Setter;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.gridtli.tli.TemporalLogicInference;

/**
 *
 * @author prash
 */
public class Crawler {
    
    @Setter
    private double steadyState;
    
    @Setter
    private double xthreshold;
    
    @Setter
    private double ythreshold;
    
    @Setter
    private double maxtime;
    
    public Crawler(){
        this.steadyState = 600;
        this.xthreshold = 10;
        this.ythreshold = 10;
        this.maxtime = 1500;
    }
    
    public void crawl(Set<CrawlerTask> tasks, String rootfp) throws TLIException, InterruptedException, IOException{
        File root = new File(rootfp);
        File[] filelist = root.listFiles();
        
        for(File f:filelist){
            List<TreeNode> stlList = new ArrayList<TreeNode>();
            List<TreeNode> steadystlList = new ArrayList<TreeNode>();
            
            String fp = f.getAbsolutePath();
            if(!fp.endsWith("" + Utilities.getSeparater())){
                fp += Utilities.getSeparater();
            }
            File[] files = f.listFiles();
            for(File file:files){
                if(file.getName().endsWith(".csv") && file.getName().startsWith("out")){
                    List<Signal> signals = Utilities.readSignalsFromCSV(file.getAbsolutePath());
                    String varname = file.getName();
                    varname = varname.substring(0,varname.lastIndexOf(".csv"));
                    
                    
                    //Generate Log Plots
                    if(tasks.contains(CrawlerTask.LOG_PLOT)){
                        List<String> pylines = PyPlotAdaptor.generateSignalPlotScript(signals, fp + varname + "_log.png", 0, maxtime, 0.00001, 100000, false, true);
                        Utilities.writeToFile(fp + varname + "_log_singals.py", pylines);
                        PyPlotAdaptor.runScript(fp + varname + "_log_singals.py");

                    }
                    
                    if(tasks.contains(CrawlerTask.STL_STEADYSTATE)){
                        List<Signal> steadysigs = new ArrayList<>();
                        for (Signal s : signals) {
                            List<Point> points = new ArrayList<>();
                            for (Point p : s.getPoints()) {
                                if (p.getX() >= this.steadyState) {
                                    points.add(p);
                                }
                            }
                            steadysigs.add(new Signal(points));
                        }
                        Grid steadyGrid = new Grid(steadysigs, this.xthreshold, this.ythreshold);
                        TreeNode steadystl = TemporalLogicInference.getSTL(steadyGrid, ythreshold);
                        Utilities.writeToFile(fp + varname + "_steady_stl.txt", steadystl.toString());
                        steadystlList.add(steadystl);
                    }
                    
                    
                    if(tasks.contains(CrawlerTask.STL)){
                        Grid grid = new Grid(signals, this.xthreshold, this.ythreshold);
                        TreeNode stl = TemporalLogicInference.getSTL(grid, ythreshold);
                        Utilities.writeToFile(fp + varname + "_stl.txt", stl.toString());
                        stlList.add(stl);
                    }
                    
                }
            }
            if(stlList.size() > 1){
                if(tasks.contains(CrawlerTask.STL)){
                    TreeNode finalstl = TemporalLogicInference.reduceToSingleConjunction(stlList);
                    Utilities.writeToFile(fp + "stl.txt", finalstl.toString());
                }
                if(tasks.contains(CrawlerTask.STL_STEADYSTATE)){
                    TreeNode finalSteadystl = TemporalLogicInference.reduceToSingleConjunction(steadystlList);
                    Utilities.writeToFile(fp + "steady_stl.txt", finalSteadystl.toString());
                }
            }
        }
        
    }
    
    
    public enum CrawlerTask{
        LOG_PLOT,
        STL,
        STL_STEADYSTATE
    }
    
}
