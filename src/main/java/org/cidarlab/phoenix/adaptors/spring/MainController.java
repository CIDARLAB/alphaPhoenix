/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.core.PhoenixMode;

import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.library.Library;
import org.cidarlab.phoenix.utils.Serializer;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author prash
 */
@Controller
public class MainController {

    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public void createProject(HttpServletRequest request, HttpServletResponse response) {
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

    @RequestMapping(value = "/specifications", method = RequestMethod.POST)
    public void createSpecifications(HttpServletRequest request, HttpServletResponse response) {

        try {
            String job = request.getParameter("jobid");
            String stl = request.getParameter("stl");
            String eug = request.getParameter("eug");
            String registry = request.getParameter("registry");
            registry = registry.substring(0, registry.lastIndexOf("/rootCollections"));
            String collection = request.getParameter("collection").replaceAll("%3A", ":").replaceAll("%2F", "/");
            String numSol = request.getParameter("numSol");
            String solSize = request.getParameter("solSize");


            String filepath = Utilities.getResultsFilepath() + job + Utilities.getSeparater();
            Utilities.writeToFile(filepath + "stl.txt", stl);
            Utilities.writeToFile(filepath + "structure.eug", eug);

            JSONObject euginfo = new JSONObject();
            euginfo.put("numSol", numSol);
            euginfo.put("solSize", solSize);
            Utilities.writeToFile(filepath + "eug.json", euginfo.toString());

            JSONObject shubinfo = new JSONObject();
            shubinfo.put("registry", registry);
            shubinfo.put("collection", collection);
            Utilities.writeToFile(filepath + "reg.json", shubinfo.toString());

            SBOLDocument sbol = SynbiohubAdaptor.getSBOL(registry, collection);
            SBOLWriter.write(sbol, (filepath + "sbol.xml"));
            Library lib = new Library(sbol);
            List<Module> modules = MiniEugeneAdaptor.getStructures((filepath + "structure.eug"), Integer.valueOf(solSize), Integer.valueOf(numSol), job);
            
            int index = 0;
            Module test = org.cidarlab.phoenix.core.Controller.decompose(PhoenixMode.MM, modules.get(index));
            
            List<Map<String,CandidateComponent>> assignments = org.cidarlab.phoenix.core.Controller.assign(test, lib, sbol);
            Map<String,CandidateComponent> assignment = assignments.get(0);
            org.cidarlab.phoenix.core.Controller.assignLeafModels(PhoenixMode.MM,test,job,sbol,assignment);
            org.cidarlab.phoenix.core.Controller.composeModels(PhoenixMode.MM, test, job, assignment);
            
            JSONObject resp = Serializer.rootModuleToJSON(test,assignments, 0);
            JSONArray arr = new JSONArray();
            arr.put(resp);
            PrintWriter writer;
            writer = response.getWriter();
            System.out.println(arr.toString());
            writer.print(arr.toString(1));
            writer.flush();
        } catch (IOException | SBOLConversionException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
