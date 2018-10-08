/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.cidarlab.phoenix.adaptors.DnaPlotlibAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.failuremode.FailureMode;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class Module {
    
    @Getter
    @Setter
    private List<FailureMode> failureModes = new ArrayList<>();
    
    @Getter
    @Setter
    private List<CandidateComponent> candidates = new ArrayList<>();
    
    @Getter
    @Setter
    private List<Map<String, CandidateComponent>> assignments = new ArrayList<>();
    
    @Getter
    @Setter
    private Orientation orientation;
    
    @Getter
    @Setter
    private Model model;

    //This is the part where it gets decomposed
    @Getter
    @Setter
    private List<Module> children = new ArrayList<>();
    
    @Getter
    @Setter
    private List<Component> components = new ArrayList<>();
        
    @Getter
    @Setter
    private String id;
    
    @Getter
    @Setter
    private ModuleRole role;
    
    @Getter
    @Setter
    private boolean root;
    
    public static Component getPromInPR(Module pr){
        return pr.getComponents().get(0);
    }
    
    public static Component getRbsInPR(Module pr){
        return pr.getComponents().get(1);
    }
    
    public static Component getRbsInRC(Module rc){
        return rc.getComponents().get(0);
    }
    
    public static Component getCDSInRC(Module rc){
        return rc.getComponents().get(1);
    }
    
    //Get all Interactions
    
    public String getComponentString(){
        String cstring = "";
        for(Component c: this.components){
            if(c.getOrientation().equals(Orientation.REVERSE)){
                cstring += "r";
            } else {
                cstring += "f";
            }
            cstring += (c.getRole().toString() + ";");
        }
        return cstring;
    }
    
    
    public Component findComponent(String componentName){
        for(Component c:this.components){
            if(c.getName().equals(componentName)){
                return c;
            }
        }
        return null;
    }
    
    public void addComponent(Component c){
        this.components.add(c);
    }
    
    public void addChild(Module child){
        this.children.add(child);
    }
    
    public Module(String _id){
        this.id = _id;
    }
    
    public void printTree(){
        printTree(0);
    }
    
    public void printIOCNames(){
        for(Component c:this.components){
            if(c.getIOCname() != null){
                System.out.println(c.getName() + ":" + c.getIOCname()); 
            }
        }
    }
    
    public void setIOCNames(){
        int in = 0;
        int out = 0;
        int conn = 0;
        Set<String> completed = new HashSet<>();
        for(Component c:this.components){
            if(!completed.contains(c.getName())){
                if(c.isPromoter()){
                    if(c.getInteractions().isEmpty()){
                        if(c.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)){
                            //Shouldn't this be in_x++? 
                            
                        } else {
                            c.setIOCname("in" + in);
                            in++;
                        }
                    } else {
                        for(Interaction i:c.getInteractions()){
                            i.getFrom().setIOCname("conn" + conn);
                            i.getTo().setIOCname("conn" + conn);
                        }
                        conn++;
                    }
                    
                } else if(c.isCDS()){
                    if(c.getInteractions().isEmpty()){
                        c.setIOCname("out" + out);
                        out++;
                    } else {
                        for(Interaction i:c.getInteractions()){
                            i.getFrom().setIOCname("conn" + conn);
                            i.getTo().setIOCname("conn" + conn);
                        }
                        conn++;
                    }
                }
            }
        }
        
    }
    
    private static String generateSBOLVisualName(){
        JSONObject figlist = new JSONObject(Utilities.getFileContentAsString(Utilities.getDnaFiguresFilepath() + "figlist.json"));
        Set<String> filenames = figlist.keySet();
        
        String filename = Utilities.randomString(8);
        while(filenames.contains(filename)){
            filename = Utilities.randomString(8);
        }
        return filename;
    }
    
    public String getAbstractSBOLVisual(Map<String,String> colorMap) throws InterruptedException, IOException{
        
        String dplString = DnaPlotlibAdaptor.getUniqueString(this.components);
        JSONObject figmap = new JSONObject(Utilities.getFileContentAsString(Utilities.getDnaFiguresFilepath() + "figmap.json"));
        if(figmap.has(dplString)){
            String plotname = figmap.getString(dplString);
            return "/sbol/" + plotname + ".png";
        } else {
            
            String fn = generateSBOLVisualName();
            
            String scriptFp = Utilities.getDnaFiguresScriptsFilepath() + fn + ".py";
            String plotFp = Utilities.getDnaFiguresPlotsFilepath() + fn;
            String script = DnaPlotlibAdaptor.generateScript(this.components, false, colorMap, plotFp);
            Utilities.writeToFile(scriptFp, script);
            DnaPlotlibAdaptor.runWebAppScript(scriptFp);
            
            JSONObject figlist = new JSONObject(Utilities.getFileContentAsString(Utilities.getDnaFiguresFilepath() + "figlist.json"));
            figlist.put(fn, fn);
            Utilities.writeToFile(Utilities.getDnaFiguresFilepath() + "figlist.json", figlist.toString());
            
            figmap.put(dplString, fn);
            Utilities.writeToFile(Utilities.getDnaFiguresFilepath() + "figmap.json", figmap.toString());
            return "/sbol/" + fn + ".png";
        }
        
    }
    
    private void printTree(int indent){
        for(int i=0;i<indent;i++){
            System.out.print("-");
        }
        System.out.print(this.role.toString() + " :: " + this.getComponentString() + "\n");
        for(Module child:this.children){
            child.printTree(indent+1);
        }
    }
    
    public static enum ModuleRole{
        HIGHER_FUNCTION,
        TRANSCRIPTIONAL_UNIT,
        PROMOTER,
        PROMOTER_RBS,
        RBS_CDS,
        CDS, 
        TERMINATOR,
    }
    
    
}
