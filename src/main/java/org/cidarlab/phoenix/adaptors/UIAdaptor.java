/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class UIAdaptor {
    
    public static JSONObject getModuleJSON(Module module){
        JSONObject obj = new JSONObject();
        JSONArray tus = new JSONArray();
        for(Module tu:module.getChildren()){
            tus.put(getTUJSON(tu));
        }
        obj.put("tus", tus);
        return obj;
    }
    
    private static JSONObject getTUJSON(Module module){
        JSONObject obj = new JSONObject();
        List<String> promCandidates = new ArrayList<String>();
        List<String> cdsCandidates = new ArrayList<String>();
        for(Module child:module.getChildren()){
            switch(child.getRole()){
                case PROMOTER:
                    for (List<CandidateComponent> candidates : child.getAssignments()) {
                        promCandidates.add(getCandidateComponentString(child.getRole(), candidates));
                    }
                    break;
                case CDS:
                    for (List<CandidateComponent> candidates : child.getAssignments()) {
                        cdsCandidates.add(getCandidateComponentString(child.getRole(), candidates));
                    }
                    break;
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
        
        JSONArray cdsCandidateList = new JSONArray();
        for(String cdsCandidate:cdsCandidates){
            JSONObject cdsCObj = new JSONObject();
            cdsCObj.put("active", true);
            cdsCObj.put("candidate", cdsCandidate);
            cdsCandidateList.put(cdsCObj);
        }
        cdsObj.put("candidates", cdsCandidateList);
        
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
                        case CDS_REPRESSIBLE_REPRESSOR:
                        case CDS_ACTIVATIBLE_ACTIVATOR:
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
