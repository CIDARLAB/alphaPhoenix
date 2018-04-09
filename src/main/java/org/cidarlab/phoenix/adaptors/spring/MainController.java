/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbolstandard.core2.SBOLConversionException;
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

    private static boolean userExists(String username) {
        String resultsFP = Utilities.getResultsFilepath();
        File root = new File(resultsFP);
        File[] list = root.listFiles();
        for (File f : list) {
            if (f.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void createUser(String username) {
        String userFP = Utilities.getResultsFilepath() + username;
        Utilities.makeDirectory(userFP);
    }

    private static String getUsername(String token) {
        return "prash";
    }

    private static boolean projectExists(String username, String projectName) {
        String userFP = Utilities.getResultsFilepath() + username + Utilities.getSeparater();
        File root = new File(userFP);
        File[] list = root.listFiles();
        for (File f : list) {
            if (f.getName().equals(projectName)) {
                System.out.println("PROJECT EXISTS!!");
                return true;
                
            }
        }
        return false;
    }
    
    private static JSONArray createProject(String username, String projectName, String stl, String eugeneCode, String registry, String collection) throws IOException, SBOLConversionException{
        String root = Utilities.getResultsFilepath() + username + Utilities.getSeparater();
        String rootfp = Utilities.getResultsFilepath() + username + Utilities.getSeparater();
        PhoenixProject proj = new PhoenixProject(rootfp, projectName, stl, eugeneCode, registry, collection);
        JSONArray arr = new JSONArray();
        
        return arr;
    }
    

    //<editor-fold desc="LOGIN">
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String username = jsonreq.getString("username");
        String password = jsonreq.getString("password");
        System.out.println("Username : " + username);
        
        String token = "0000";
        PrintWriter writer;
        boolean loginError = false;
        JSONObject res = new JSONObject();
        res.put("token", token);

        if (!userExists(username)) {
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

        String username = jsonreq.getString("username");
        String password = jsonreq.getString("password");
        String email = jsonreq.getString("email");

        System.out.println("Username : " + username);

        String token = "0000";
        PrintWriter writer;
        boolean loginError = false;
        JSONObject res = new JSONObject();
        res.put("token", token);

        if (userExists(username)) {
            loginError = true;
        }

        try {

            if (loginError) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                createUser(username);
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                writer.print(res.toString());
                writer.flush();
            }

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
        
        String username = getUsername(token);
        boolean error = projectExists(username, projectName);
        
        
        
        try {
            if(error){
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                writer = response.getWriter();
                writer.write("Project name already exists");
                writer.flush();
            } else {
                
                try {
                    response.setStatus(HttpServletResponse.SC_OK);
                    JSONArray designJSON = createProject(username,projectName,stl,eugeneCode,registry,collection);
                    writer = response.getWriter();
                    writer.write(designJSON.toString());
                    writer.flush();
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
}
