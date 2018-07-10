/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class UIAdaptor {
    
    public static JSONObject getModuleJSON(Module module) throws InterruptedException, IOException{
        JSONObject obj = new JSONObject();
        JSONArray tus = new JSONArray();
        for(Module tu:module.getChildren()){
            tus.put(getTUJSON(tu));
        }
        String img = module.getAbstractSBOLVisual(new HashMap<String,String>());
        obj.put("tus", tus);
        obj.put("img", img);
        return obj;
    }
    
    private static Map<String,String> getPromoterColorMap(Module m){
        Map<String,String> colorMap = new HashMap<String,String>();
        for(Component c:m.getComponents()){
            if(c.isCDS()){
                colorMap.put(c.getName(), "(1.00,1.00,1.00)");
            }
        }
        
        return colorMap;
    }
    
    private static JSONObject getTUJSON(Module module) throws InterruptedException, IOException{
        JSONObject obj = new JSONObject();
        List<String> promCandidates = new ArrayList<String>();
        List<String> cdsCandidates = new ArrayList<String>();
        String tuImg = module.getAbstractSBOLVisual(new HashMap<String,String>());
        String promImg = "";
        String cdsImg = "";
        for(Module child:module.getChildren()){
            switch(child.getRole()){
                /*case PROMOTER:
                    for (List<CandidateComponent> candidates : child.getAssignments()) {
                        promCandidates.add(getCandidateComponentString(child.getRole(), candidates));
                        promImg = child.getAbstractSBOLVisual(getPromoterColorMap(child));
                    }
                    break;
                case CDS:
                    for (List<CandidateComponent> candidates : child.getAssignments()) {
                        cdsCandidates.add(getCandidateComponentString(child.getRole(), candidates));
                        cdsImg = child.getAbstractSBOLVisual(new HashMap<String,String>());
                    }
                    break;*/
                default:
                    System.err.println("Unexpected child for a Transcriptional Unit Module");
            }
        }
        JSONArray tuCandidateList = new JSONArray();
        for(String tuCandidate:getCrossProduct(promCandidates,cdsCandidates)){
            JSONObject tuObj = new JSONObject();
            tuObj.put("active", true);
            tuObj.put("candidate", tuCandidate);
            tuCandidateList.put(tuObj);
        }
        obj.put("candidates", tuCandidateList);
        obj.put("img", tuImg);
        
        JSONObject promObj = new JSONObject();
        JSONObject cdsObj = new JSONObject();
        
        JSONArray promCandidateList = new JSONArray();
        for(String promCandidate:promCandidates){
            JSONObject promCObj = new JSONObject();
            promCObj.put("active", true);
            promCObj.put("candidate", promCandidate);
            promCandidateList.put(promCObj);
        }
        promObj.put("candidates", promCandidateList);
        promObj.put("img", promImg);
        
        JSONArray cdsCandidateList = new JSONArray();
        for(String cdsCandidate:cdsCandidates){
            JSONObject cdsCObj = new JSONObject();
            cdsCObj.put("active", true);
            cdsCObj.put("candidate", cdsCandidate);
            cdsCandidateList.put(cdsCObj);
        }
        cdsObj.put("candidates", cdsCandidateList);
        cdsObj.put("img", cdsImg);
        
        obj.put("promoter", promObj);
        obj.put("cds", cdsObj);
        
        
        return obj;
    }
    
    private static List<String> getCrossProduct(List<String> prom,List<String> cds){
        List<String> prod = new ArrayList<String>();
        
        for(String p:prom){
            for(String c:cds){
                prod.add(p + c);
            }
        }
        return prod;
    }
    
    
    
    
    private static String getCandidateComponentString(ModuleRole role,List<CandidateComponent> candidate){
        String str = "";
        switch(role){
            case PROMOTER:
                for(CandidateComponent c:candidate){
                    boolean cdsFlag = false;
                    switch (c.getRole()) {
                        case CDS:
                        case CDS_REPRESSOR:
                        case CDS_ACTIVATOR:
                        //case CDS_REPRESSIBLE_REPRESSOR:
                        //case CDS_ACTIVATIBLE_ACTIVATOR:
                        case CDS_LINKER:
                        case CDS_TAG:
                        case CDS_RESISTANCE:
                        case CDS_FLUORESCENT:
                        case CDS_FLUORESCENT_FUSION:
                        case TESTING:
                            cdsFlag = true;
                            break;
                        default: 
                            cdsFlag = false;
                    }
                    if(cdsFlag){
                        break;
                    }
                    str += c.getCandidate().getName() + ";";
                }
                return str;
            case CDS:
                if(candidate.size() > 1){
                    System.err.println("Error! Can this happen?");
                }
                return candidate.get(0).getCandidate().getName() + ";";
            default: return null;
        }
    }
    
    
}
