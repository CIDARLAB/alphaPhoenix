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
import org.bson.types.ObjectId;
import org.cidarlab.phoenix.core.PhoenixProject;
import org.cidarlab.phoenix.schemas.Session;
import org.cidarlab.phoenix.schemas.User;
import org.cidarlab.phoenix.utils.Database;
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
    
    private static void createUserFolder(User user) {
        String userFP = Utilities.getResultsFilepath() + user.getId().toString();
        Utilities.makeDirectory(userFP);
        String userlistfp = Utilities.getResultsFilepath() + "users.json";
        JSONObject users = new JSONObject(Utilities.getFileContentAsString(userlistfp));
        users.put(user.getEmail(), user.getId().toString());
        Utilities.writeToFile(userlistfp, users.toString());
    }

    private static boolean projectExists(String username, String projectName) {
        JSONArray projects = PhoenixProject.getProjects(username);
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

        String email = jsonreq.getString("email");
        String password = jsonreq.getString("password");
        
        User user = User.findByCredentials(email, password);
        
        try {
            if(user != null) {
                ObjectId key = new ObjectId();
                Session session = new Session(user, key);
                Database.getInstance().save(session);

                PrintWriter writer;
                JSONObject res = new JSONObject();
                res.put("session", new JSONObject(session));
                res.put("token", key.toString());
                res.put("id", session.getId().toString());
                res.put("user", new JSONObject(user));

                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                writer.print(res.toString());
                writer.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
        String name = jsonreq.getString("name");
        
        try {
            if(User.userExists(email)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            } else {
                User user = new User(name,email,password,institution);
                Database.getInstance().save(user);
                ObjectId key = new ObjectId();
                Session session = new Session(user, key);
                Database.getInstance().save(session);
                
                createUserFolder(user);
                
                PrintWriter writer;
                JSONObject res = new JSONObject();
                res.put("session", new JSONObject(session));
                res.put("token", key.toString());
                res.put("id", session.getId().toString());
                res.put("user", new JSONObject(user));
                
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
        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            JSONArray projectList = PhoenixProject.getProjects(user.getId().toString());
            PrintWriter writer;
            
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                writer.print(projectList.toString());
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    //</editor-fold>

    //<editor-fold desc="SPEC">
    /*
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
    */
    //</editor-fold>
    
    //<editor-fold desc="DESIGN">
    /*
    @ResponseBody
    @RequestMapping(value = "/design", method = RequestMethod.POST)
    public void design(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String token = jsonreq.getString("token");
        String projectName = jsonreq.getString("project");
        String username = getUserUUID(token);
        
        PrintWriter writer;
        
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            writer = response.getWriter();
            writer.write(PhoenixProject.getDesignArray(username, projectName).toString());
            writer.flush();
        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    */
    //</editor-fold>
    
    @RequestMapping(value = "/sbol/{id}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable(value="id") String imageId, HttpServletResponse response) throws IOException {
        String imagefp = Utilities.getDnaFiguresPlotsFilepath() + imageId + ".png";
        InputStream in = new FileInputStream(new File(imagefp));
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
    
}
