/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.Trace;
import hyness.stl.TreeNode;
import hyness.stl.UntilNode;
import hyness.stl.grammar.STLAbstractSyntaxTreeExtractor;
import hyness.stl.grammar.STLLexer;
import hyness.stl.grammar.STLParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.cidarlab.gridtli.adaptors.JavaPlotAdaptor;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.gridtli.tli.Validation;
import org.cidarlab.phoenix.core.CLI;
import org.cidarlab.phoenix.dom.SMC;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class STLAdaptor {

    public static TreeNode getSTL(String filepath) {
        List<String> stlFileContent = Utilities.getFileContentAsStringList(filepath);
        String stlString = "";
        for (String str : stlFileContent) {
            stlString += str;
        }
        STLLexer lexer = new STLLexer(new ANTLRInputStream(stlString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        STLParser parser = new STLParser(tokens);
        ParserRuleContext t = parser.property();
        return new STLAbstractSyntaxTreeExtractor().visit(t);
    }
    
    public static Map<String,TreeNode> getSignalSTLMap(TreeNode stl){
        Map<String,TreeNode> map = new HashMap<>();
        Map<String,TreeNode> result = getSignalSTLMap(stl,map);
        
        if(!checkSTL(result)){
            System.err.println("Malformed STL");
            System.exit(-1);
        }
        
        for(String key:result.keySet()){
            if(key.startsWith("in")){
                String num = key.substring(2);
                if (!isNum(num)) {
                    System.err.println("STL Signal variables can only start with 'in' and 'out' followed by a positive integer. E.g. 'in3' or 'out0'.");
                    System.exit(-1);
                }
            } else if(key.startsWith("out")){
                String num = key.substring(3);
                if (!isNum(num)) {
                    System.err.println("STL Signal variables can only start with 'in' and 'out' followed by a positive integer. E.g. 'in3' or 'out0'.");
                    System.exit(-1);
                }
            } else {
                System.err.println("STL Signal variables can only start with 'in' and 'out' followed by a positive integer. E.g. 'in3' or 'out0'.");
                System.exit(-1);
            }
            
        }
        return result;
    }
    
    private static boolean isNum(String str){
        boolean flag = false;
        for(char c:str.toCharArray()){
            switch(c){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    flag = true;
                    break;
                default :
                    flag = false;
                    break;
            }
            if(!flag){
                return false;
            }
        }
        return true;
    }
    
    private static boolean checkSTL(Map<String,TreeNode> map){
        for(String sig:map.keySet()){
            if(!singleSTL(sig,map.get(sig))){
                return false;
            }
        }
        return true;
    }
    
    private static boolean singleSTL(String sig,TreeNode stl){
        if(stl instanceof ConjunctionNode){
            ConjunctionNode cn = (ConjunctionNode)stl;
            return (singleSTL(sig,cn.left) && singleSTL(sig,cn.right));
        } else if(stl instanceof DisjunctionNode){
            DisjunctionNode dn = (DisjunctionNode)stl;
            return (singleSTL(sig,dn.left) && singleSTL(sig,dn.right));
        } else if(stl instanceof AlwaysNode){
            AlwaysNode an = (AlwaysNode) stl;
            return (singleSTL(sig,an.child));
        } else if(stl instanceof EventNode){
            EventNode en = (EventNode)stl;
            return (singleSTL(sig,en.child));
        } else if(stl instanceof LinearPredicateLeaf){
            LinearPredicateLeaf lpf = (LinearPredicateLeaf)stl;
            return (lpf.variable.equals(sig));
        }
        System.err.println("This type of STL TreeNode is not supported yet.");
        System.exit(-1);
        return false;
    
    }
    
    private static Map<String,TreeNode> getSignalSTLMap(TreeNode stl, Map<String,TreeNode> map){
        
        Map<String,TreeNode> newMap = new HashMap<String,TreeNode>();
        if(stl instanceof ConjunctionNode){
            ConjunctionNode cn = (ConjunctionNode) stl;
            if(sameSignals(cn)){
                String sig = getSignal(cn);
                if(map.containsKey(sig)){
                    System.err.println("Malformed STL. Map already contains : " + sig);
                    System.exit(-1);
                }
                newMap.put(getSignal(cn), stl);
            } else {
                Map<String,TreeNode> left = getSignalSTLMap(cn.left,map);
                Map<String,TreeNode> right = getSignalSTLMap(cn.right,map);
                for(String key:left.keySet()){
                    if (map.containsKey(key)) {
                        System.err.println("Malformed STL. Map already contains : " + key);
                        System.exit(-1);
                    }
                    newMap.put(key, cn.left);
                }
                for(String key:right.keySet()){
                    if (map.containsKey(key)) {
                        System.err.println("Malformed STL. Map already contains : " + key);
                        System.exit(-1);
                    }
                    newMap.put(key, cn.right);
                }
            }
        } else {
            String sig = getSignal(stl);
            if (map.containsKey(sig)) {
                System.err.println("Malformed STL. Map already contains : " + sig);
                System.exit(-1);
            }
            newMap.put(getSignal(stl), stl);
        }
        return newMap;
    }
    
    private static boolean sameSignals(ConjunctionNode cn){
        return getSignal(cn.left).equals(getSignal(cn.right));
    }
    
    private static String getSignal(TreeNode node){
        if(node instanceof LinearPredicateLeaf){
            LinearPredicateLeaf lpf = (LinearPredicateLeaf) node;
            return lpf.variable;
        }
        else if(node instanceof ConjunctionNode){
            ConjunctionNode cn = (ConjunctionNode) node;
            return getSignal(cn.left);
        }
        else if(node instanceof DisjunctionNode){
            DisjunctionNode dn = (DisjunctionNode) node;
            return getSignal(dn.left);
        }
        else if(node instanceof AlwaysNode){
            AlwaysNode an = (AlwaysNode) node;
            return getSignal(an.child);
        }
        else if(node instanceof EventNode){
            EventNode en = (EventNode) node;
            return getSignal(en.child);
        }
        return null;
    }
    
    public static Trace signalToTrace(Signal s){
        List<Double> data = new ArrayList<>();
        List<Double> timepoints = new ArrayList<>();
        List<String> variables  = new ArrayList<>();
        if(s.getPoints().isEmpty()){
            return new Trace(new ArrayList<String>(), new ArrayList<Double>(), new ArrayList<List<Double>>());
        }
        else{
            variables.add(s.getPoints().get(0).getYSignal());
            for(Point p:s.getPoints()){
                timepoints.add(p.getX());
                data.add(p.getY());
            }
            List<List<Double>> allData = new ArrayList<>();
            allData.add(data);
            return new Trace(variables,timepoints,allData);
        }
    }
    
    public static double getMaxTime(TreeNode stl){
        if(stl instanceof ConjunctionNode){
            ConjunctionNode cn = (ConjunctionNode)stl;
            return max(getMaxTime(cn.left),getMaxTime(cn.right));
        } else if(stl instanceof DisjunctionNode){
            DisjunctionNode dn = (DisjunctionNode) stl;
            return max(getMaxTime(dn.left),getMaxTime(dn.right));
        } else if(stl instanceof LinearPredicateLeaf){
            return 0;
        } else if(stl instanceof AlwaysNode){
            AlwaysNode always = (AlwaysNode)stl;
            return (always.high + getMaxTime(always.child));
        } else if(stl instanceof EventNode){
            EventNode event = (EventNode) stl;
            return (event.high + getMaxTime(event.child));
        } else if(stl instanceof UntilNode){
            UntilNode until = (UntilNode)stl;
            return (until.high + max(getMaxTime(until.left),getMaxTime(until.right)));
        } 
         
        return 0;
    }
    
    private static double max(double v1,double v2){
        if(v1 > v2){
            return v1;
        } else {
            return v2;
        }
    }
    
    private static double min(double v1,double v2){
        if(v1 < v2){
            return v1;
        } else {
            return v2;
        }
    }
    
    public static double signalRobustness(TreeNode stl, Signal s){
        Trace trace = signalToTrace(s);
        double rob = 0;
        boolean first = true;
        for(Double time:trace.getTimePoints()){
            double r = stl.robustness(trace, time);
            if(first){
                rob = r;
                first = false;
            } else {
                if (r < rob) {
                    rob = r;
                }
            }
            
        }
        return rob;
        
    }
    
    private static void printSignal(Signal s){
        for(Point p:s.getPoints()){
            System.out.println(p);
        }
    }
    
    public static SMC smc(TreeNode stl,String folderpath,boolean plot, int numofruns, double confidence) throws TLIException{
        
        Map<String,List<Signal>> allsignals = new HashMap<String,List<Signal>>();
        int satisfycount = 0;
        Map<String, TreeNode> stlmap = getSignalSTLMap(stl);
        for(int i=1;i<=numofruns;i++){
            double rob = 0;
            
            boolean first = true;
            String tsdfp = folderpath + "run-" + i + ".csv";
            Map<String, Signal> signalMap = IBioSimAdaptor.getSignals(tsdfp);
            for(String key:stlmap.keySet()){
                if (signalMap.containsKey(key)) {
                    Signal s = signalMap.get(key);
                    if (allsignals.containsKey(key)) {
                        allsignals.get(key).add(s);
                    } else {
                        allsignals.put(key, new ArrayList<Signal>());
                        allsignals.get(key).add(s);
                    }
                    TreeNode stlnode = stlmap.get(key);
                    double val = getRobustness(stlnode, s);
                    //double val = signalRobustness(stlnode,s);
                    if (first) {
                        rob = val;
                        first = false;
                    } else {
                        if (val < rob) {
                            rob = val;
                        }
                    }
                } else {
                    System.err.println("Error in naming convention.");
                    System.exit(-1);
                }
            }
            if(rob >= 0){
                satisfycount++;
            }
        }
        
        if(plot){
            for(String key:allsignals.keySet()){
                String plotfp = folderpath + key + ".png";
                Grid g = new Grid(allsignals.get(key),100,50);
                JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGridwithoutCover(g), plotfp);
            }
        }
        double satisfy = ((double) satisfycount) / ((double) numofruns);
        double error = computeError(satisfy, numofruns, confidence);
        SMC smc = new SMC(satisfy,error,allsignals);
        return smc;
    }
    
    public static double getRobustness(TreeNode stl,String filepath, String result, boolean plot) throws TLIException{
        Map<String,TreeNode> stlmap = getSignalSTLMap(stl);
        Map<String,Signal> signalMap = IBioSimAdaptor.getSignals(filepath);
        //System.out.println("IBioSim Signals : " + filepath);
        double rob = 0;
        boolean first = true;
        for(String key:stlmap.keySet()){
            if(signalMap.containsKey(key)){
                Signal s = signalMap.get(key);
                
                List<Signal> signals = new ArrayList<>();
                signals.add(s);
                Grid g = new Grid(signals,100,50);
                TreeNode stlnode = stlmap.get(key);
                double val = getRobustness(stlnode, s);
                //double val = signalRobustness(stlnode,s);
                if(first){
                    rob = val;
                    first = false;
                } else {
                    if (val < rob) {
                        rob = val;
                    }
                }
                
                if (plot) {
                    String plotfp = result + key + ".png";
                    JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGridwithoutCover(g), plotfp);
                }
                
            } else {
                System.err.println("Error in naming convention.");
                System.exit(-1);
            }
        }
        return rob;
    }
    
    public static List<Signal> getSignalsFromFile(String filepath) throws TLIException{
        String filename = FilenameUtils.getName(filepath);
        String ext = FilenameUtils.getExtension(filename);
        if(filename.endsWith(".csv")){
            String signame = filename.substring(0,filename.lastIndexOf(".csv"));
            List<String[]> lines = Utilities.getCSVFileContentAsList(filepath);
            if(lines.size() < 2){
                CLI.wrongFormatMessage("Error." + filename + " should have atleast 1 Trace. First line should be time points and second line onwards should be trace values for the signal.");
                System.exit(-1);
                return null;
            } else {
                List<Double> timepoints = new ArrayList<Double>();
                for(String str:lines.get(0)){
                    timepoints.add(Double.valueOf(str));
                }
                List<Signal> signals = new ArrayList<Signal>();
                for(int i = 1;i<lines.size();i++){
                    List<Point> points = new ArrayList<Point>();
                    String[] line = lines.get(i);
                    for(int j=0;j<line.length;j++){
                        points.add(new Point(timepoints.get(j),"t",Double.valueOf(line[j]),signame));
                    }
                    signals.add(new Signal(points));
                }
                return signals;
            }
        } else{
            CLI.wrongFormatMessage("File format error. CSV expected instead of " + ext);
            System.exit(-1);
            return null;
        }
        
    }
    
    
    public static double getRobustness(TreeNode stl, Signal s){
        double r = 0;
        boolean first = true;
        if(stl instanceof ConjunctionNode){
            ConjunctionNode cn = (ConjunctionNode) stl;
            return min( getRobustness(cn.left,s),getRobustness(cn.right,s));
        } else if(stl instanceof DisjunctionNode){
            DisjunctionNode dn = (DisjunctionNode) stl;
            return max(getRobustness(dn.left,s),getRobustness(dn.right,s));
        } else if(stl instanceof AlwaysNode){
            AlwaysNode an = (AlwaysNode) stl;
            return getAlwaysNodeRobustness(an,s);
        } else {
            System.out.println("Sorry. Current formats of STL supported are Conjucntion, Disjunction and Always. Other formats are currently under test");
            System.exit(-1);
        }
        
//        List<TreeNode> disjunctions = getDisjunctionLeaves(stl);
//        for(TreeNode node:disjunctions){
//            double val = getConjunctionNodeRobustness(node,s);
//            
//            //Get max
//            if(first){
//                first = false;
//                r = val;
//            } else {
//                if(val > r){
//                    r = val;
//                }
//            }
//        }
        return r;
    }
    
    /*
    private static double getConjunctionNodeRobustness(TreeNode stl, Signal s){
        double r =0;
        boolean first = true;
        List<TreeNode> alwaysNodes = new ArrayList<TreeNode>();
        alwaysNodes = getConjunctionLeaves(stl);
        //System.out.println("Number of always Nodes :: " + alwaysNodes.size());
        for(TreeNode node:alwaysNodes){
            AlwaysNode always = (AlwaysNode)node;
            double val = getAlwaysNodeRobustness(always,s);
            //System.out.println("Always Node            :: " + always);
            //System.out.println("Always Node Robustness :: " + val);
                
            if(first){
                r = val;
                first = false;
            } else {
                if(val < r){
                    r = val;
                }
            }
        }
        
        return r;
    }
    */
    private static double getAlwaysNodeRobustness(AlwaysNode stl, Signal s){
        
        if(!(stl.child instanceof LinearPredicateLeaf)){
            System.out.println("Sorry. This format of STL is not yet supported. An always node can only have a single Linear Predicate as the child.");
            System.exit(-1);
        }
        
        List<Point> covering = getCoveringPoints(stl,s);
        if (covering.get(0).getX() < stl.low) {
            covering.set(0, getInterpolation(covering.get(0), covering.get(1), stl.low));
        } 
        if(covering.get(covering.size()-1).getX() > stl.high){
            covering.set(covering.size()-1, getInterpolation(covering.get(covering.size()-2),covering.get(covering.size()-1), stl.high));
        }
        double r=0;
        boolean first = true;
        for(Point p:covering){
            double val =0;
            LinearPredicateLeaf lpf = (LinearPredicateLeaf) stl.child;
            if(lpf.rop.equals(RelOperation.LE) || lpf.rop.equals(RelOperation.LT)){
                val = lpf.threshold - p.getY();
            } else if(lpf.rop.equals(RelOperation.GE) || lpf.rop.equals(RelOperation.GT)){
                val = p.getY() - lpf.threshold;
            }
            
            if(first){
                r = val;
                first = false;
            } else {
                if(val<r){
                    r = val;
                }
            }
        }
        //System.out.println(r);
        return r;
    }
    
    private static Point getInterpolation(Point p1, Point p2, double x){
        double slope = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        double y = ((x-p1.getX()) * slope)  + p1.getY();
        return new Point(x,y);
    }
    
    private static List<Point> getCoveringPoints(AlwaysNode stl, Signal s){
        double low = stl.low;
        double high = stl.high;
        //System.out.println("Low ::" + low);
        //System.out.println("High ::" + high);
        //System.out.println(s.getPoints());
        List<Point> points = new ArrayList<Point>();
        boolean started = false;
        Point lastP = null;
        for(Point p:s.getPoints()){
            if(started){
                if(p.getX() >= high){
                    points.add(p);
                    break;
                }
                points.add(p);
            } else {
                if(p.getX() < low){
                    lastP = p;
                } else {
                    started = true;
                    if (p.getX() > low) {
                        if (lastP != null) {
                            points.add(lastP);
                        }
                    }
                    points.add(p);
                    if(p.getX() >= high){
                        break;
                    }
                }
            }
        }
        if(!started){
            points.add(lastP);
        }
        
        return points;
    }
    
    /*
    private static List<TreeNode> getConjunctionLeaves(TreeNode stl){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        getConjunctionLeaves(stl,nodes);
        return nodes;
    }
    */
    
    /*
    private static void getConjunctionLeaves(TreeNode stl, List<TreeNode> list){
        if(stl instanceof ConjunctionNode){
            ConjunctionNode cnode = (ConjunctionNode) stl;
            getConjunctionLeaves(cnode.left,list);
            getConjunctionLeaves(cnode.right,list);
        } else {
            list.add(stl);
        }
    }
    */
    
    /*
    private static List<TreeNode> getDisjunctionLeaves(TreeNode stl){
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        getDisjunctionLeaves(stl,nodes);
        return nodes;
    }
    */
    
    /*
    private static void getDisjunctionLeaves(TreeNode stl, List<TreeNode> list){
        if(stl instanceof DisjunctionNode){
            DisjunctionNode cnode = (DisjunctionNode) stl;
            getDisjunctionLeaves(cnode.left,list);
            getDisjunctionLeaves(cnode.right,list);
        } else {
            list.add(stl);
        }
    }
    */
    
    public static double computeSatisfyingPercent(List<Signal> signals, TreeNode stl) throws IOException {
        int sat = 0;
        for (Signal s : signals) {
            if (getRobustness(stl,s) >= 0) {
                sat++;
            }
        }
        return ((double) sat) / ((double) signals.size());
    }

    
    
    public static double computeError(double satisfyingPercent, int numTraces, double confidenceInterval) {
        NormalDistribution distribution = new NormalDistribution(0, 1);
        double confidenceValue = distribution.inverseCumulativeProbability(confidenceInterval + ((1 - confidenceInterval) / 2));
        return confidenceValue * Math.sqrt((1 - satisfyingPercent) / (satisfyingPercent * numTraces));
    }    
    
    
}
