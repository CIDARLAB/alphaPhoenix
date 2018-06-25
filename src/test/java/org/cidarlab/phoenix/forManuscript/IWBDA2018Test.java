/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.forManuscript;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.gridtli.adaptors.PyPlotAdaptor;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class IWBDA2018Test {
    
    
    private String rootfp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "forManuscript" + Utilities.getSeparater() + "iwbda2018" + Utilities.getSeparater();
    
    
    private String confp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "forManuscript" + Utilities.getSeparater() + "constitutiveStoch" + Utilities.getSeparater();
    
    
    //@Test
    public void testResults() throws TLIException{
        JSONArray results = new JSONArray(Utilities.getFileContentAsString(rootfp + "results.json"));
        int count = 0;
        for(Object obj:results){
            JSONObject result = (JSONObject) obj;
            JSONArray traces = result.getJSONArray("traces");
            List<Signal> signals = new ArrayList<Signal>();
            for(Object traceObj:traces){
                JSONObject trace = (JSONObject)traceObj;
                JSONArray xArr = trace.getJSONArray("x");
                JSONArray yArr = trace.getJSONArray("y");
                List<Point> points = new ArrayList<Point>();
                for(int i=0;i<xArr.length();i++){
                    double x = xArr.getDouble(i);
                    double y = yArr.getDouble(i);
                    points.add(new Point(x,"t",y,"out0"));
                }
                signals.add(new Signal(points));
            }
            Grid g = new Grid(signals,100,100);
            Utilities.writeToFile(rootfp + "pyscript" + count + ".py", PyPlotAdaptor.generatePlotScript(g, "grid" + count));
            count++;
         }
        
    }
    
    
    //@Test
    public void testConResults() throws TLIException{
        JSONArray results = new JSONArray(Utilities.getFileContentAsString(confp + "results.json"));
        int count = 0;
        for(Object obj:results){
            JSONObject result = (JSONObject) obj;
            JSONArray traces = result.getJSONArray("traces");
            List<Signal> signals = new ArrayList<Signal>();
            for(Object traceObj:traces){
                JSONObject trace = (JSONObject)traceObj;
                JSONArray xArr = trace.getJSONArray("x");
                JSONArray yArr = trace.getJSONArray("y");
                List<Point> points = new ArrayList<Point>();
                for(int i=0;i<xArr.length();i++){
                    double x = xArr.getDouble(i);
                    double y = yArr.getDouble(i);
                    points.add(new Point(x,"t",y,"out0"));
                }
                signals.add(new Signal(points));
            }
            Grid g = new Grid(signals,100,100);
            Utilities.writeToFile(confp + "pyscript" + count + ".py", PyPlotAdaptor.generatePlotScript(g, "grid" + count));
            count++;
         }
        
    }
    
    
    
    //@Test
    public void testCascade_0_0() throws TLIException{
        
        String fp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "forManuscript" + Utilities.getSeparater() + "cascade_0_0" + Utilities.getSeparater();
    
        JSONArray results = new JSONArray(Utilities.getFileContentAsString(fp + "results.json"));
        int count = 0;
        for(Object obj:results){
            JSONObject result = (JSONObject) obj;
            JSONArray traces = result.getJSONArray("traces");
            List<Signal> signals = new ArrayList<Signal>();
            for(Object traceObj:traces){
                JSONObject trace = (JSONObject)traceObj;
                JSONArray xArr = trace.getJSONArray("x");
                JSONArray yArr = trace.getJSONArray("y");
                List<Point> points = new ArrayList<Point>();
                for(int i=0;i<xArr.length();i++){
                    double x = xArr.getDouble(i);
                    double y = yArr.getDouble(i);
                    points.add(new Point(x,"t",y,"out0"));
                }
                signals.add(new Signal(points));
            }
            Grid g = new Grid(signals,100,100);
            Utilities.writeToFile(fp + "pyscript" + count + ".py", PyPlotAdaptor.generatePlotScript(g, "grid" + count));
            count++;
         }
        
    }
    
    
    //@Test
    public void testCascade_1_100() throws TLIException{
        
        String fp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "forManuscript" + Utilities.getSeparater() + "cascade_1_100" + Utilities.getSeparater();
    
        JSONArray results = new JSONArray(Utilities.getFileContentAsString(fp + "results.json"));
        int count = 0;
        for(Object obj:results){
            JSONObject result = (JSONObject) obj;
            JSONArray traces = result.getJSONArray("traces");
            List<Signal> signals = new ArrayList<Signal>();
            for(Object traceObj:traces){
                JSONObject trace = (JSONObject)traceObj;
                JSONArray xArr = trace.getJSONArray("x");
                JSONArray yArr = trace.getJSONArray("y");
                List<Point> points = new ArrayList<Point>();
                for(int i=0;i<xArr.length();i++){
                    double x = xArr.getDouble(i);
                    double y = yArr.getDouble(i);
                    points.add(new Point(x,"t",y,"out0"));
                }
                signals.add(new Signal(points));
            }
            Grid g = new Grid(signals,100,100);
            Utilities.writeToFile(fp + "pyscript" + count + ".py", PyPlotAdaptor.generatePlotScript(g, "grid" + count));
            count++;
         }
        
    }
    
    
    
    @Test
    public void testCascade_1_0() throws TLIException{
        
        String fp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "forManuscript" + Utilities.getSeparater() + "cascade_1_0" + Utilities.getSeparater();
    
        JSONArray results = new JSONArray(Utilities.getFileContentAsString(fp + "results.json"));
        int count = 0;
        for(Object obj:results){
            JSONObject result = (JSONObject) obj;
            JSONArray traces = result.getJSONArray("traces");
            List<Signal> signals = new ArrayList<Signal>();
            for(Object traceObj:traces){
                JSONObject trace = (JSONObject)traceObj;
                JSONArray xArr = trace.getJSONArray("x");
                JSONArray yArr = trace.getJSONArray("y");
                List<Point> points = new ArrayList<Point>();
                for(int i=0;i<xArr.length();i++){
                    double x = xArr.getDouble(i);
                    double y = yArr.getDouble(i);
                    points.add(new Point(x,"t",y,"out0"));
                }
                signals.add(new Signal(points));
            }
            Grid g = new Grid(signals,100,100);
            Utilities.writeToFile(fp + "pyscript" + count + ".py", PyPlotAdaptor.generatePlotScript(g, "grid" + count));
            count++;
         }
        
    }
    
    
    
}
