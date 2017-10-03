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
import hyness.stl.Trace;
import hyness.stl.TreeNode;
import hyness.stl.grammar.STLAbstractSyntaxTreeExtractor;
import hyness.stl.grammar.STLLexer;
import hyness.stl.grammar.STLParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.tli.Validation;
import org.cidarlab.gridtli.visualize.JavaPlotAdaptor;
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
    
    public static double getRobustness(TreeNode stl,String filepath, String result){
        Map<String,TreeNode> stlmap = getSignalSTLMap(stl);
        Map<String,Signal> signalMap = IBioSimAdaptor.getSignals(filepath);
        double rob = Double.MAX_VALUE;
        for(String key:stlmap.keySet()){
            if(signalMap.containsKey(key)){
                Signal s = signalMap.get(key);
                List<Signal> signals = new ArrayList<>();
                signals.add(s);
                Grid g = new Grid(signals,5,5,100,0,100,0);
                TreeNode stlnode = stlmap.get(key);
                double val = Validation.getRobustness(stlnode, s);
                
                if(val < rob){
                    rob = val;
                }
                System.out.println("Robustness for Signal " + key + " is " + val);
                String plotfp = result + key + ".png";
                JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGridwithoutCover(g), plotfp);
            } else {
                System.err.println("Error in naming convention.");
                System.exit(-1);
            }
        }
        return rob;
    }
    
    
}
