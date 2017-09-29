/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;

import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author prash
 */
@Controller
public class MainController {
    
    @RequestMapping(value="/createProject", method=RequestMethod.GET)
    public void createProject(HttpServletRequest request, HttpServletResponse response){
        PhoenixProject newProj = new PhoenixProject();
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(newProj.getJobId());
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value="/specifications", method=RequestMethod.POST)
    public void createSpecifications(HttpServletRequest request, HttpServletResponse response){
        String job = request.getParameter("jobid");
        String stl = request.getParameter("stl");
        String eug = request.getParameter("eug");
        String registry = request.getParameter("registry");
        String collection = request.getParameter("collection").replaceAll("%3A", ":").replaceAll("%2F", "/");
        String numSol = request.getParameter("numSol");
        String solSize = request.getParameter("solSize");
        
        System.out.println("JOB :: " + job);
        System.out.println("STL ::" + stl);
        System.out.println("Eug :: " + eug);
        System.out.println("Registry :: " + registry);
        System.out.println("Collection :: " + collection);
        System.out.println("Number of Solutions :: " + numSol);
        System.out.println("Solution size :: " + solSize);
        
        String filepath = Utilities.getResultsFilepath() + job + Utilities.getSeparater();
        Utilities.writeToFile(filepath + "stl.txt", stl);
        Utilities.writeToFile(filepath + "structure.eug", eug);
        
        JSONObject shubinfo = new JSONObject();
        shubinfo.put("registry", registry);
        shubinfo.put("collection",collection);
        Utilities.writeToFile(filepath + "reg.json", shubinfo.toString());
        
        JSONObject euginfo = new JSONObject();
        euginfo.put("numSol", numSol);
        euginfo.put("solSize", solSize);
        Utilities.writeToFile(filepath + "eug.json", euginfo.toString());
        
        
        
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print("");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    @RequestMapping(value="/performance", method=RequestMethod.POST)
    public void createSTL(HttpServletRequest request, HttpServletResponse response){
        String job = request.getParameter("jobid");
        String stl = request.getParameter("stl");
        
        System.out.println("JOB :: " + job);
        System.out.println("STL ::" + stl);
    }
    
    @RequestMapping(value="/structure", method=RequestMethod.POST)
    public void createEugene(HttpServletRequest request, HttpServletResponse response){
        String job = request.getParameter("jobid");
        String eug = request.getParameter("eug");
        
        System.out.println("JOB :: " + job);
        System.out.println("Eug :: " + eug);
    }
    
    @RequestMapping(value="/parts", method=RequestMethod.POST)
    public void createLibrary(HttpServletRequest request, HttpServletResponse response){
        String job = request.getParameter("jobid");
        String registry = request.getParameter("registry");
        System.out.println("JOB :: " + job);
        System.out.println("Registry :: " + registry);
        
    } */
    
    
}
