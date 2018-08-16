/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.simulation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.sbml.jsbml.SBMLDocument;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.SBOLDocument;

/**
 *
 * @author prash
 */
public abstract class AbstractSimulation {
    
    public static String getAssignmentString(Module module, Map<String, CandidateComponent> assignment){
        String s = "";
        for (Component c : module.getComponents()) {
            s += (assignment.get(c.getName()).getCandidate().getName() + ";");
        }
        return s;
    }
    
    public static void printAssignment(Module module, Map<String, CandidateComponent> assignment) {

        for (Component c : module.getComponents()) {
            System.out.print(assignment.get(c.getName()).getCandidate().getName() + ";");
        }
        System.out.println("");
    }
    
    
    protected static Map<URI,SBMLDocument> downloadAllModels(Library library, String fp) throws URISyntaxException, MalformedURLException{
        Map<URI,SBMLDocument> modelmap = new HashMap<>();
        SBOLDocument sbol = library.getSbol();
        for(URI u:library.getAllLibraryComponents().keySet()){
            LibraryComponent lc = library.getAllLibraryComponents().get(u);
            for(URI mduri:lc.getModuleDefinitions()){
                ModuleDefinition md = sbol.getModuleDefinition(mduri);
                List<org.sbolstandard.core2.Model> sbolmodels = new ArrayList<>(md.getModels());
                for(org.sbolstandard.core2.Model sbolmodel:sbolmodels){
                    URI key = sbolmodel.getSource();
                    URI modeldownload = new URI(key.toString() + "/download");
                    if(!modelmap.containsKey(key)) {
                        //System.out.println("Downloaded : " + modeldownload.toString());
                        SBMLDocument sbml = SynbiohubAdaptor.getModel(modeldownload.toURL(), fp);
                        modelmap.put(key, sbml);
                    } else {
                        System.out.println("This is not supposed to happen.");
                    }
                }
            }
        }
        return modelmap;
    }
    
    public static Map<String,String> getIndSMmap(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        Map<String,String> map = new HashMap<>();
        for(Component c:m.getComponents()){
            if(c.isPromoter()){
                String cname = c.getName();
                LibraryComponent cc = assignment.get(cname).getCandidate();
                PromoterComponent promcomp = null;
                if(cc instanceof PromoterComponent){
                    promcomp = (PromoterComponent) cc;
                } else if(cc instanceof CompositeComponent){
                    CompositeComponent composite = (CompositeComponent)cc;
                    promcomp = library.getAllPromoters().get(composite.getChildren().get(0));
                }
                for(LibraryComponent tf:promcomp.getTranscriptionFactors()){
                    if(tf instanceof ComplexComponent){
                        ComplexComponent complex = (ComplexComponent)tf;
                        SmallMoleculeComponent smc = library.getSmallMolecules().get(complex.getSmallMolecule());
                        if(!map.containsKey(smc.getName())){
                            map.put(smc.getName(), "ind_" + ioc.get(cname));
                        }
                    }
                }
            }
        }
        return map;
    }
    
    protected static List<Map<String,Double>> getSmallMoleculeConcentration(Module m, Map<String, CandidateComponent> assignment, Map<String,String> ioc, Library library){
        List<Map<String,Double>> smMap = new ArrayList<>();
        Map<String,List<Double>> smConcentrations = new HashMap<>();
        for(Component c:m.getComponents()){
            String cname = c.getName();
            if (c.isPromoter()) {
                LibraryComponent lc = assignment.get(cname).getCandidate();
                PromoterComponent promcomp = null;
                boolean isPromoter = false;
                if (lc instanceof PromoterComponent) {
                    promcomp = (PromoterComponent) lc;
                    isPromoter = true;
                } else if (lc instanceof CompositeComponent) {
                    CompositeComponent compcomp = (CompositeComponent) lc;
                    if (compcomp.getType().equals(CompositeComponent.CompositeType.PR)) {
                        promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                        isPromoter = true;
                    }
                }
                if (isPromoter) {
                    for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                        if (tf instanceof ComplexComponent) {
                            ComplexComponent complex = (ComplexComponent) tf;
                            SmallMoleculeComponent smc = library.getSmallMolecules().get(complex.getSmallMolecule());

                            String indName = "ind_" + ioc.get(cname);
                            if (!smConcentrations.containsKey(indName)) {
                                smConcentrations.put(indName, smc.getValues());
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Small Molecule Concentrations:");
        System.out.println(smConcentrations);
        List<Map<String,Double>> temp = new ArrayList<>();
        for(String ind:smConcentrations.keySet()){
            List<Double> indConc = smConcentrations.get(ind);
            if(smMap.isEmpty()){
                for(Double conc:indConc){
                    Map<String,Double> map = new HashMap<>();
                    map.put(ind, conc);
                    smMap.add(map);
                }
            } else {
                for(Map<String,Double> hconcs: smMap){
                    for(Double conc:indConc){
                        Map<String,Double> map = new HashMap<>();
                        map.putAll(hconcs);
                        map.put(ind, conc);
                        temp.add(map);
                    }
                }
                smMap = new ArrayList<>();
                smMap.addAll(temp);
                temp = new ArrayList<>();
            }
        }
        
        return smMap;
    }
    
    
    
}
