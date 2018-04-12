/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLValidationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prash
 */
@RestController
public class MainController {
    
    //<editor-fold desc="HELPER FUNCTIONS">
    
    private static boolean userExists(String email) {
        String fp = Utilities.getResultsFilepath() + "users.json";
        JSONObject users = new JSONObject(Utilities.getFileContentAsString(fp));
        return users.keySet().contains(email);
        /*String resultsFP = Utilities.getResultsFilepath();
        File root = new File(resultsFP);
        File[] list = root.listFiles();
        for (File f : list) {
            if (f.getName().equals(username)) {
                return true;
            }
        }
        return false;*/
    }

    private static boolean folderExists(String folder){
        String resultsFP = Utilities.getResultsFilepath();
        File root = new File(resultsFP);
        File[] list = root.listFiles();
        for (File f : list) {
            if (f.getName().equals(folder)) {
                return true;
            }
        }
        return false;
    }
    
    private static void createUser(String email) {
        String uuid = Utilities.randomString(8);
        while(folderExists(uuid)){
            uuid = Utilities.randomString(8);
        }
        String userFP = Utilities.getResultsFilepath() + uuid;
        Utilities.makeDirectory(userFP);
        String userlistfp = Utilities.getResultsFilepath() + "users.json";
        JSONObject users = new JSONObject(Utilities.getFileContentAsString(userlistfp));
        users.put(email, uuid);
        Utilities.writeToFile(userlistfp, users.toString());
    }

    private static String getUserUUID(String token) {
        String userlistfp = Utilities.getResultsFilepath() + "users.json";
        JSONObject users = new JSONObject(Utilities.getFileContentAsString(userlistfp));
        for(String email:users.keySet()){
            return users.getString(email);
        }
        return null;
        //return "prash";
    }
    
    private static String getProjectUUID(String userUUID, String projectName){
        JSONArray projects = PhoenixProject.getProjects(userUUID);
        
        for(Object obj:projects){
            JSONObject json = (JSONObject)obj;
            if(json.getString("projectName").equals(projectName)){
                return json.getString("id");
            }
        }
        return null;
    }
    
    private static boolean projectExists(String userUUID, String projectName) {
        JSONArray projects = PhoenixProject.getProjects(userUUID);
        for(Object obj:projects){
            JSONObject json = (JSONObject)obj;
            if(json.get("projectName").equals(projectName)){
                return true;
            }
        }
        return false;
    }
    
    private static void createProject(String username, String projectName, String stl, String eugeneCode, String registry, String collection) throws IOException, SBOLConversionException, SBOLValidationException, InterruptedException{
        PhoenixProject proj = new PhoenixProject(username, projectName, stl, eugeneCode, registry, collection);
        proj.design();
    }
    
    //</editor-fold>
    
    //<editor-fold desc="LOGIN">
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String email = jsonreq.getString("username");
        String password = jsonreq.getString("password");
        System.out.println("Username : " + email);
        
        String token = "0000";
        PrintWriter writer;
        boolean loginError = false;
        JSONObject res = new JSONObject();
        res.put("token", token);
        
        if (!userExists(email)) {
            loginError = true;
        }

        try {

            if (loginError) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                writer.print(res.toString());
                writer.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signup(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String institution = jsonreq.getString("institution");
        String password = jsonreq.getString("password");
        String email = jsonreq.getString("email");

        System.out.println("Username : " + email);

        String token = "0000";
        PrintWriter writer;
        boolean loginError = false;
        JSONObject res = new JSONObject();
        res.put("token", token);
        
        if (userExists(email)) {
            loginError = true;
        }

        try {

            if (loginError) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                createUser(email);
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                writer.print(res.toString());
                writer.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public void loadProjects(@RequestBody String request, HttpServletResponse response){
        JSONObject jsonreq = new JSONObject(request);
        String token = jsonreq.getString("token");
        String userUUID = getUserUUID(token);
        JSONArray projectList = PhoenixProject.getProjects(userUUID);
        PrintWriter writer;
        
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            writer = response.getWriter();
             writer.print(projectList.toString());
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //</editor-fold>

    //<editor-fold desc="SPEC">
    @ResponseBody
    @RequestMapping(value = "/specification", method = RequestMethod.POST)
    public void specification(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        PrintWriter writer;

        JSONObject jsonreq = new JSONObject(request);

        String token = jsonreq.getString("token");
        String projectName = jsonreq.getString("project");
        String eugeneCode = jsonreq.getString("eugene");
        String stl = jsonreq.getString("stl");
        String registry = jsonreq.getString("registry");
        String collection = jsonreq.getString("collection");
        
        boolean error = false;
        String username = getUserUUID(token);
        if(username == null){
            error = true;
        }
        if(!error){
            error = projectExists(username, projectName);
        }
        
        try {
            if(error){
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                writer = response.getWriter();
                writer.write("Project name already exists");
                writer.flush();
            } else {
                
                try {
                    try {
                        response.setStatus(HttpServletResponse.SC_OK);
                        createProject(username, projectName, stl, eugeneCode, registry, collection);
                        writer = response.getWriter();
                        writer.write("Project created.");
                        writer.flush();
                    } catch (SBOLValidationException | InterruptedException ex) {
                        response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                        writer = response.getWriter();
                        writer.write(ex.toString());
                        writer.flush();
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } catch (SBOLConversionException ex) {
                    response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                    writer = response.getWriter();
                    writer.write(ex.toString());
                    writer.flush();
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //</editor-fold>
    
    //<editor-fold desc="DESIGN">
    @ResponseBody
    @RequestMapping(value = "/design", method = RequestMethod.POST)
    public void design(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String token = jsonreq.getString("token");
        String projectName = jsonreq.getString("project");
        String userUUID = getUserUUID(token);
        String projectUUID = getProjectUUID(userUUID,projectName);
        
        PrintWriter writer;
        
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            writer = response.getWriter();
            writer.write(PhoenixProject.getDesignArray(userUUID, projectUUID).toString());
            writer.flush();
        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

        
    //</editor-fold>
    
    @RequestMapping(value = "/sbol/{id}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable(value="id") String imageId, HttpServletResponse response) throws IOException {
        String imagefp = Utilities.getDnaFiguresPlotsFilepath() + imageId + ".png";
        InputStream in = new FileInputStream(new File(imagefp));
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
    
}
