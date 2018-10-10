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
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.cidarlab.gridtli.dom.TLIException;
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
    
    
    private static boolean projectExists(String userId, String projectName) {
        JSONArray projects = PhoenixProject.getProjects(userId);
        for(Object obj:projects){
            JSONObject json = (JSONObject)obj;
            if(json.get("projectName").equals(projectName)){
                return true;
            }
        }
        return false;
    }
    
    private static PhoenixProject createProject(String userId, String projectName, String stl, String eugeneCode, String registry, String collection, int runCount, double confidence, double threshold) throws IOException, SBOLConversionException, SBOLValidationException, InterruptedException, URISyntaxException{
        PhoenixProject proj = new PhoenixProject(userId, projectName, stl, eugeneCode, registry, collection, runCount, confidence, threshold);
        proj.design();
        return proj;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="USER ROUTES">
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
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public void forgot(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String email = jsonreq.getString("email");
        
        try {
            User user = User.getUserByEmail(email);
            if(user != null) {
                
                String key = new ObjectId().toString();
                user.setForgotPasswordKey(key);
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer;
                writer = response.getWriter();
                System.out.println("Send this key via email:" + key);
                writer.print("Check your email to reset password");
                writer.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                PrintWriter writer;
                writer = response.getWriter();
                writer.print("User not found");
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @ResponseBody
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public void reset(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String email = jsonreq.getString("email");
        String password = jsonreq.getString("password");
        String key = jsonreq.getString("key");
        
        try {
            User user = User.getUserByEmail(email);
            if(user != null) {
                if(user.checkForgotPasswordKey(key)) {
                    Session.deleteSessionForUser(user);
                    user.setPassword(password);
                    user.setForgotPasswordKey(null);
                    Database.getInstance().getDatastore().save(user);
                    response.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter writer;
                    writer = response.getWriter();
                    writer.print("Password Updated");
                    writer.flush();
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    PrintWriter writer;
                    writer = response.getWriter();
                    writer.print("Incorrect Key, Check your email link, or resubmit forgot password form");
                    writer.flush();
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                PrintWriter writer;
                writer = response.getWriter();
                writer.print("User not found");
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @ResponseBody
    @RequestMapping(value = "/verify/{id}", method = RequestMethod.GET)
    public void verifyEmail(@PathVariable(value="id") String id, HttpServletResponse response) throws IOException {
        
        User user = User.findByVerifyId(id);
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if(user.isVerfied()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            user.verifyUser();
            response.setStatus(HttpServletResponse.SC_OK);
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
    
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void updateUser(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        boolean advUser = jsonreq.getBoolean("advUser");
        String emailOptions = jsonreq.getString("emailOption");
        String[] registires = jsonreq.getJSONArray("registries").toString().replace("},{", " ,").split(" ");
        
        try {
            Session session = Session.findByCredentials(sessionId, token);
            if(session != null) {
                User user = Session.getUser(session);
                user.setAdvancedUser(advUser);
                user.setEmailOptions(emailOptions);
                user.setRegistries(registires);
                user.save();

                PrintWriter writer;
                writer = response.getWriter();
                writer.print("User Updated");
                writer.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                PrintWriter writer;
                writer = response.getWriter();
                writer.print("User not found");
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/removeProject", method = RequestMethod.POST)
    public void removeProject(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {

        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");
        
        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            
            PrintWriter writer;
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                
                String projectfp = Utilities.getResultsFilepath() + user.getId().toString() + Utilities.getSeparater() + projectId + Utilities.getSeparater();
                FileUtils.deleteDirectory(new File(projectfp));
                JSONObject res = new JSONObject();
                res.put("project", projectId);
                res.put("status", "deleted");
                writer.write(res.toString());
                writer.flush();
            } catch (IOException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    //</editor-fold>

    //<editor-fold desc="SPEC">
    @ResponseBody
    @RequestMapping(value = "/specification", method = RequestMethod.POST)
    public void specification(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException, URISyntaxException {

        PrintWriter writer;

        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectName = jsonreq.getString("project");
        String eugeneCode = jsonreq.getString("eugene");
        String stl = jsonreq.getString("stl");
        String registry = jsonreq.getString("registry");
        String collection = jsonreq.getString("collection");
        
        double top = jsonreq.getDouble("top")/100.00;
        double confidence = jsonreq.getDouble("confidence");
        double threshold = jsonreq.getDouble("threshold");
        int runCount = jsonreq.getInt("runCount");
        
        try {
        
            Session session = Session.findByCredentials(sessionId, token);
            if(session != null) {
                User user = Session.getUser(session);

                if(projectExists(user.getId().toString(), projectName)){
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                    writer = response.getWriter();
                    JSONObject res = new JSONObject();
                    res.append("message", "Project name already exists");
                    writer.write(res.toString());
                    writer.flush();
                } else {
                    try {
                        PhoenixProject proj = createProject(user.getId().toString(), projectName, stl, eugeneCode, registry, collection, runCount, confidence, threshold);
                        proj.executeBasicProject();
                        
                        response.setStatus(HttpServletResponse.SC_OK);
                        JSONObject res = new JSONObject();
                        res.append("projectID", proj.getJobId());
                        res.append("message", "Project created.");
                        writer = response.getWriter();
                        writer.write(res.toString());
                        writer.flush();
                    } catch (SBOLValidationException | SBOLConversionException |InterruptedException ex) {
                        response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                        writer = response.getWriter();
                        writer.write(ex.toString());
                        writer.flush();
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (XMLStreamException | TLIException ex) {
                        response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                        writer = response.getWriter();
                        writer.write(ex.toString());
                        writer.flush();
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch(IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/viewSpecification", method = RequestMethod.POST)
    public void viewSpecification(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException, URISyntaxException {

        
        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");

        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            
            PrintWriter writer;
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                JSONObject project = PhoenixProject.getSpecificationObjects(user.getId().toString(), projectId);
                if(project != null) {
                    writer.write(project.toString());
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("Project not found");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                writer.flush();
            } catch (IOException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
    
    
    //</editor-fold>
    
    //<editor-fold desc="DESIGN">
    @ResponseBody
    @RequestMapping(value = "/design", method = RequestMethod.POST)
    public void design(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");

        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            
            PrintWriter writer;
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                JSONArray project = PhoenixProject.getDesignArray(user.getId().toString(), projectId);
                if(project != null) {
                    
                    writer.write(project.toString());
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("Project not found");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                writer.flush();
            } catch (IOException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="RESULTS">
    @ResponseBody
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public void results(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");

        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            
            PrintWriter writer;
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                JSONArray results = PhoenixProject.getResultsArray(user.getId().toString(), projectId);
                if(results != null) {
                    writer.write(results.toString());
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("Project not found");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                writer.flush();
            } catch (IOException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/assignment", method = RequestMethod.POST)
    public void assignment(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");
        int moduleId = jsonreq.getInt("moduleId");
        int assignmentId = jsonreq.getInt("assignmentId");
        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);
            
            PrintWriter writer;
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                writer = response.getWriter();
                JSONObject results = PhoenixProject.getAssignmentObject(user.getId().toString(), projectId, moduleId, assignmentId);
                if(results.length() > 0) {
                    writer.write(results.toString());
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("Assignment not found");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                writer.flush();
            } catch (IOException | TLIException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/downloadAssignment", method = RequestMethod.POST, produces="application/zip")
    public void downloadAssignment(@RequestBody String request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        JSONObject jsonreq = new JSONObject(request);

        String sessionId = jsonreq.getString("id");
        String token = jsonreq.getString("token");
        String projectId = jsonreq.getString("project");
        int moduleId = jsonreq.getInt("moduleId");
        int assignmentId = jsonreq.getInt("assignmentId");
        
        Session session = Session.findByCredentials(sessionId, token);
        if(session != null) {
            User user = Session.getUser(session);

            ZipOutputStream zipOutputStream;
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                zipOutputStream = new ZipOutputStream(response.getOutputStream());
                String zipfp = PhoenixProject.downloadAssignment(user.getId().toString(), projectId, moduleId, assignmentId);
                File file = new File(zipfp);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream fileInputStream = new FileInputStream(file);

                IOUtils.copy(fileInputStream, zipOutputStream);
                fileInputStream.close();
                zipOutputStream.closeEntry();

                zipOutputStream.close();
            } catch (IOException ex) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    @RequestMapping(value = "/sbol/{userid}/{projectid}/{moduleid}/{assignmentid}/{name}", method = RequestMethod.GET)
    public void getImageAsByteArray(
            @PathVariable(value="userid") String userid,
            @PathVariable(value="projectid") String projectid, 
            @PathVariable(value="moduleid") String moduleid, 
            @PathVariable(value="assignmentid") String assignmentid, 
            @PathVariable(value="name") String name, 
            HttpServletResponse response) throws IOException {
        
        String imagefp = Utilities.getResultsFilepath() 
                + userid + Utilities.getSeparater() 
                + projectid +  Utilities.getSeparater() 
                + "results" + Utilities.getSeparater()
                + moduleid +  Utilities.getSeparater() 
                + assignmentid +  Utilities.getSeparater()+ name + ".png";
        
        InputStream in = new FileInputStream(new File(imagefp));
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
    
    //</editor-fold>
    
    
    
}
