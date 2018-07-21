/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.utils.Args;

/**
 *
 * @author prash
 */
public abstract class AbstractAssignment {
    
    abstract public void solve(List<Module> modules, Library library, TreeNode stl, Args args);
    
    
    
    protected boolean validAssignment(Map<String,CandidateComponent> assignment, Map<String,Component> cmap,  Library library, TreeNode stl){
        
        Set<URI> cdsProteins = new HashSet<>();
        Set<URI> promProteins = new HashSet<>();
        Set<URI> outputCDS = new HashSet<>();
        int outcount = getOutputCount(stl);

        for (String key : assignment.keySet()) {
            if (cmap.get(key).isCDS()) {
                if (library.getOutputCDS().containsKey(assignment.get(key).getCandidate().getComponentDefintion())) {
                    outputCDS.add(assignment.get(key).getCandidate().getComponentDefintion());
                } else if (library.getConnectorCDS().containsKey(assignment.get(key).getCandidate().getComponentDefintion())) {
                    CDSComponent cdscomp = (CDSComponent) assignment.get(key).getCandidate();
                    cdsProteins.add(cdscomp.getProtein());
                }
            } else if (cmap.get(key).isPromoter()) {
                LibraryComponent lc = assignment.get(key).getCandidate();
                PromoterComponent promcomp = null;
                if (lc instanceof CompositeComponent) {
                    CompositeComponent compcomp = (CompositeComponent) lc;
                    promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                } else {
                    promcomp = (PromoterComponent) lc;
                }
                for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                    if (tf instanceof PrimitiveComponent) {
                        promProteins.add(tf.getComponentDefintion());
                    } else if (tf instanceof ComplexComponent) {
                        ComplexComponent complexComponent = (ComplexComponent) tf;
                        promProteins.add(complexComponent.getProtein());
                    }
                }
            }
        }
        
        if (outputCDS.size() != outcount) {
            return false;
        }

        for (URI pp : promProteins) {
            if (!cdsProteins.contains(pp)) {
                return false;
            }
        }
        
        for (URI cp : cdsProteins) {
            if (!promProteins.contains(cp)) {
                return false;
            }
        }
        
        return true;

        
    }
    
    private int getOutputCount(TreeNode stl){
        Set<String> signals = STLAdaptor.getSignalSTLMap(stl).keySet();
        int outcount = 0;
        for(String s:signals){
            if(s.startsWith("out")){
                outcount++;
            }
        }
        return outcount;
    }
    
    protected Set<String> generateAssignmentStringSet(Module module, Map<String,CandidateComponent> assignment){
        Set<String> set = new HashSet<>();
        for(Module tu:module.getChildren()){
            String str = "";
            for(Component c:tu.getComponents()){
                str += (assignment.get(c.getName()).getCandidate().getName() + ";");
            }
            set.add(str);
        }
        return set;
    }
    
    //<editor-fold desc="Assign TU Child Candidates">
    protected void assignTUchildCandidates(Module module, Library library){
        
        for(Module child:module.getChildren()){
            switch(child.getRole()){
                case PROMOTER:
                    assignPcandidates(child,library);
                    break;
                case PROMOTER_RBS:
                    assignPRcandidates(child,library);
                    break;
                case RBS_CDS:
                    assignRCcandidates(child,library);
                    break;
                case CDS:
                    assignCcandidates(child,library);
                    break;
                case TERMINATOR:
                    assignTcandidates(child,library);
                    break;
            }
        }
    }
    
    private void assignPRcandidates(Module module, Library library){
        Component p = module.getComponents().get(0);
        Map<URI,CompositeComponent> prCandidates = null;
        if(p.getRole().equals(Component.ComponentRole.GENERIC_PROMOTER)){
            prCandidates = library.getPr();
        } else {
            prCandidates = library.getPR(p.getRole());
        }
        
        for (URI uri : prCandidates.keySet()) {
            CandidateComponent candidate = new CandidateComponent(prCandidates.get(uri));
            candidate.setOrientation(module.getOrientation());
            module.getCandidates().add(candidate);
        }
    }
    
    private void assignPcandidates(Module module, Library library){
        
    }
    
    private void assignRCcandidates(Module module, Library library){
        
    }
    
    private void assignCcandidates(Module module, Library library){
        Component c = module.getComponents().get(0);
        Map<URI,CDSComponent> cCandidates  = new HashMap<>();
        if(c.getRole().equals(Component.ComponentRole.GENERIC_CDS)){
            cCandidates.putAll(library.getConnectorCDS());
            cCandidates.putAll(library.getOutputCDS());
        } else {
            if(c.getRole().equals(Component.ComponentRole.CDS_FLUORESCENT)){
                cCandidates.putAll(library.getOutputCDS());
            } else if(c.getRole().equals(Component.ComponentRole.CDS_ACTIVATOR)){
                cCandidates.putAll(library.getConnectorCDS());                
            } else if(c.getRole().equals(Component.ComponentRole.CDS_REPRESSOR)){
                cCandidates.putAll(library.getConnectorCDS());                
            }
        }
        
        for (URI uri : cCandidates.keySet()) {
            CandidateComponent candidate = new CandidateComponent(cCandidates.get(uri));
            candidate.setOrientation(module.getOrientation());
            module.getCandidates().add(candidate);
        }        
    }
    
    private void assignTcandidates(Module module, Library library){
        Component t = module.getComponents().get(0);
        for (URI uri : library.getTerminators().keySet()) {
            CandidateComponent candidate = new CandidateComponent(library.getTerminators().get(uri));
            candidate.setOrientation(module.getOrientation());
            module.getCandidates().add(candidate);
        }
    }
    //</editor-fold>
    
}
