/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.EventNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import java.util.List;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class Serializer {
    
    //<editor-fold desc="STL to JSON for GUI">
    public static JSONArray stlToJSON(TreeNode stl){
        JSONArray arr = new JSONArray();
        if(stl instanceof DisjunctionNode){
            DisjunctionNode orNode = (DisjunctionNode) stl;
            TreeNode left = orNode.left;
            TreeNode right = orNode.right;
            for(Object obj:(stlToJSON(left))){
                arr.put(obj);
            }
            for(Object obj:(stlToJSON(right))){
                arr.put(obj);
            }
        } else {
            arr.put(stlNodeToJSON(stl));
        }
        return arr;
    }
    
    private static JSONArray stlNodeToJSON(TreeNode node){
        JSONArray arr = new JSONArray();
        if(node instanceof ConjunctionNode){
            ConjunctionNode andNode = (ConjunctionNode) node;
            TreeNode left = andNode.left;
            TreeNode right = andNode.right;
            for(Object obj:stlNodeToJSON(left)){
                arr.put(obj);
            }
            for(Object obj:stlNodeToJSON(right)){
                arr.put(obj);
            }
        } else if(node instanceof AlwaysNode){
            
            JSONObject obj = new JSONObject();
            AlwaysNode alnode = (AlwaysNode)node;
            if(alnode.child instanceof LinearPredicateLeaf){
                String tempBounds = "[" +  alnode.low + "," + alnode.high + "]";
                obj.put("G", tempBounds);
                LinearPredicateLeaf lpl = (LinearPredicateLeaf) alnode.child;
                obj.put(lpl.variable, (opToString(lpl.rop) + " " + lpl.threshold));
                arr.put(obj);
            } else {
                System.err.println("Not supported yet");
            }
            
        } else if(node instanceof EventNode){
            
            JSONObject obj = new JSONObject();
            AlwaysNode alnode = (AlwaysNode)node;
            if(alnode.child instanceof LinearPredicateLeaf){
                String tempBounds = "[" +  alnode.low + "," + alnode.high + "]";
                obj.put("F", tempBounds);
                LinearPredicateLeaf lpl = (LinearPredicateLeaf) alnode.child;
                obj.put(lpl.variable, (opToString(lpl.rop) + " " + lpl.threshold));
                arr.put(obj);
            } else {
                System.err.println("Not supported yet");
            }
            
        }
        
        return arr;
    }
    
    private static String opToString(RelOperation rop){
        switch(rop){
            case EQ:
                return "=";
            case LT:
                return "<";
            case LE:
                return "<=";
            case GT:
                return ">";
            case GE:
                return ">=";
            case NOP:
                return "nop";
            default: 
                return null;
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="Signals to JSON for GUI">
    public static JSONArray signalsToJSON(List<Signal> signals){
        JSONArray arr = new JSONArray();
        for(Signal s:signals){
            arr.put(signalToJSON(s));
        }
        return arr;
    }
    
    private static JSONArray signalToJSON(Signal signal){
        JSONArray arr = new JSONArray();
        for(Point p:signal.getPoints()){
            JSONObject obj = new JSONObject();
            obj.put(p.getXSignal(), p.getX());
            obj.put(p.getYSignal(), p.getY());
            arr.put(obj);
        }
        return arr;
    }
    //</editor-fold>
    
}
