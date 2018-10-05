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
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent.CompositeType;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public abstract class AbstractAssignment {
    
    //abstract public void solve(List<Module> modules, Library library, TreeNode stl, Args args);
    
    protected static boolean constitutiveCandidate(CandidateComponent cc, Library lib){
        URI promuri = getPRprom(cc);
        URI rbsuri = getPRrbs(cc);
        return lib.getConstitutivePromoters().containsKey(promuri);
        
    }
    
    protected static URI getPRprom(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(0);
    }
    
    protected static URI getPRrbs(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(1);
    }
    
    protected static URI getRCrbs(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(0);
    }

    protected static URI getRCcds(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(1);
    }
    
    private Map<String, CandidateComponent> getStartingPoint(List<Module> leafModules, Library library){
        Map<String, CandidateComponent> assignment = new HashMap<>();
        
        for (Module leaf : leafModules) {

            CandidateComponent cc = null;

            //<editor-fold desc="Assign CandidateComponent for Promoter_RBS">
            if (leaf.getRole().equals(ModuleRole.PROMOTER_RBS)) {
                Component prom = Module.getPromInPR(leaf);
                Component rbs = Module.getRbsInPR(leaf);

                if (assignment.containsKey(prom.getName())) {
                    if (assignment.containsKey(rbs.getName())) {
                        continue;
                    } else {
                        //Contains Prom & Does not contain RBS
                        CandidateComponent promcand = assignment.get(prom.getName());
                        URI promuri = getPRprom(promcand);
                        List<CandidateComponent> subset = new ArrayList<>();
                        for (CandidateComponent cand : leaf.getCandidates()) {
                            if (promuri.equals(getPRprom(cand))) {
                                subset.add(cand);
                            }
                        }
                        cc = subset.get(Utilities.getRandom(0, subset.size() - 1));
                    }
                } else {
                    if (assignment.containsKey(rbs.getName())) {
                        //Does not contain Prom & Contains RBS
                        CandidateComponent rbscand = assignment.get(rbs.getName());
                        URI rbsuri = getPRrbs(rbscand);
                        //System.out.println("RBS URI : " + rbsuri);
                        List<CandidateComponent> subset = new ArrayList<>();
                        for (CandidateComponent cand : leaf.getCandidates()) {
                            if (rbsuri.equals(getPRrbs(cand))) {
                                subset.add(cand);
                            }
                        }
                        cc = subset.get(Utilities.getRandom(0, subset.size() - 1));

                    } else {
                        //Does not contain Prom & Does not contain RBS
                        cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));

                    }
                }

                assignment.put(prom.getName(), cc);
                assignment.put(rbs.getName(), cc);
            } 
            //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for Promoter - Do this later">
            else if (leaf.getRole().equals(ModuleRole.PROMOTER)) {
                cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));
                Component prom = leaf.getComponents().get(0);

                //Do this later..
                assignment.put(prom.getName(), cc);
            } 
            //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for Terminator">
            else if (leaf.getRole().equals(ModuleRole.TERMINATOR)) {
                Component ter = leaf.getComponents().get(0);
                cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));
                assignment.put(ter.getName(), cc);
            }
            //</editor-fold>

        }

        for (Module leaf : leafModules) {

            CandidateComponent cc = null;

            //<editor-fold desc="Assign CandidateComponent for CDS">
            if (leaf.getRole().equals(ModuleRole.CDS)) {
                Component cds = leaf.getComponents().get(0);
                if (assignment.containsKey(cds.getName())) {
                    continue;
                }
                if (!cds.getInteractions().isEmpty()) {
                    List<CandidateComponent> subset = new ArrayList<>();
                    boolean toAssigned = false;
                    for(Interaction i:cds.getInteractions()){
                        Component to = i.getTo();
                        if(assignment.containsKey(to.getName())){
                            toAssigned = true;
                            CandidateComponent promCandidate = assignment.get(to.getName());
                            PromoterComponent promcomp = null;
                            if(promCandidate.getCandidate() instanceof CompositeComponent){
                                CompositeComponent composite = (CompositeComponent)promCandidate.getCandidate();
                                promcomp  =  (PromoterComponent)library.getAllLibraryComponents().get(composite.getChildren().get(0));
                                
                            } else if(promCandidate.getCandidate() instanceof PromoterComponent){
                                promcomp = (PromoterComponent)promCandidate.getCandidate();
                            }
                            for(LibraryComponent tf: promcomp.getTranscriptionFactors()){
                                URI promprot = null;
                                if(tf instanceof PrimitiveComponent){
                                    promprot = tf.getComponentDefintion();
                                } else if(tf instanceof ComplexComponent){
                                    ComplexComponent complex = (ComplexComponent)tf;
                                    promprot = complex.getProtein();
                                }
                                
                                for(CandidateComponent cdscand:leaf.getCandidates()){
                                    CDSComponent cdscomp = (CDSComponent)cdscand.getCandidate();
                                    if(cdscomp.getProtein().equals(promprot)){
                                        if(!subset.contains(cdscand)){
                                            subset.add(cdscand);
                                        }
                                    }
                                }
                                
                                
                            }
                            
                            
                        }
                    }
                    if(toAssigned){
                        if(subset.isEmpty()){
                            cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));
                            System.out.println("Probably going to fail anyway. Will this happen though?");
                        } else {
                            cc = subset.get(Utilities.getRandom(0, subset.size() - 1));
                        }
                    } else {
                        cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));
                    }
                    
                } else {
                    cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));
                }
                assignment.put(cds.getName(), cc);
            } //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for RBS_CDS - Do this later">
            else if (leaf.getRole().equals(ModuleRole.RBS_CDS)) {
                cc = leaf.getCandidates().get(Utilities.getRandom(0, leaf.getCandidates().size() - 1));

                Component rbs = Module.getRbsInRC(leaf);
                Component cds = Module.getCDSInRC(leaf);
                assignment.put(rbs.getName(), cc);
                assignment.put(cds.getName(), cc);
            }
            //</editor-fold>

        }

        return assignment;
    }

    
    protected Map<String, CandidateComponent> getRandomAssignment(Module module, Library library, TreeNode stl) {

        //Find out all Leaf modules.
        List<Module> leafModules = new ArrayList<>();
        for (Module tu : module.getChildren()) {
            leafModules.addAll(tu.getChildren());
        }
        Map<String, CandidateComponent> assignment = new HashMap<>();
        assignment = getStartingPoint(leafModules, library);
        int loopCount = 0;
        while(!validAssignment(module, assignment, library, stl)){
            assignment = getStartingPoint(leafModules, library);
            loopCount++;
        }
        System.out.println("Had to go through the loop " + loopCount + " times to generate the first assignment.");
        return assignment;
        
    }
    
    
    /**
     *
     * @param module
     * @param assignment
     * @param library
     * @param stl
     * @return
     */
    protected boolean validAssignment(Module module, Map<String,CandidateComponent> assignment, Library library, TreeNode stl){
        
        Set<URI> cdsProteins = new HashSet<>();
        Set<URI> promProteins = new HashSet<>();
        Set<URI> outputCDS = new HashSet<>();
        int outcount = getOutputCount(stl);
        Map<String, Component> cmap = new HashMap<>();
        for (Component c : module.getComponents()) {
            cmap.put(c.getName(), c);
        }

        
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

        //This is what you want to comment out..
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
        
        for(int i=0;i<module.getComponents().size();i++){
            CandidateComponent current = assignment.get(module.getComponents().get(i).getName());
            if(!current.getCandidate().getNotWith().isEmpty()){
                for(int j=0;j<module.getComponents().size();j++){
                    if(j==i){
                        continue;
                    }
                    Component compj = module.getComponents().get(j);
                    if(current.getCandidate().getNotWith().contains(getAssignmentURI(compj , assignment.get(compj.getName()).getCandidate()    ))){
                        return false;
                    }
                }
            }
            if(!current.getCandidate().getAfter().isEmpty()){
                for(int j=i+1;j<module.getComponents().size();j++){
                    Component compj = module.getComponents().get(j);
                    if(current.getCandidate().getAfter().contains(getAssignmentURI(compj , assignment.get(compj.getName()).getCandidate()    ))){
                        return false;
                    }
                }
            }
            if(!current.getCandidate().getBefore().isEmpty()){
                for(int j=0;j<i;j++){
                    Component compj = module.getComponents().get(j);
                    if(current.getCandidate().getBefore().contains(getAssignmentURI(compj , assignment.get(compj.getName()).getCandidate()    ))){
                        return false;
                    }
                }
            }
        }
        
        //<editor-fold desc="Check if current assignment has all unique assignments taken care of."> 
        Set<URI> specificAssignment = new HashSet<>();
        Set<String> specComp = new HashSet<>();
        
        for(Component c:module.getComponents()){
            if(!c.isGeneric()){
                if(specComp.contains(c.getName())){
                    continue;
                } 
                specComp.add(c.getName());
                CandidateComponent candidatecomp = assignment.get(c.getName());
                URI currentURI = null;
                if(candidatecomp.getCandidate() instanceof CompositeComponent){
                    CompositeComponent composite = (CompositeComponent)candidatecomp.getCandidate();
                    if(composite.getType().equals(CompositeType.PR)){
                        if(c.isPromoter()){
                            currentURI = composite.getChildren().get(0);
                        } else if(c.isRBS()){
                            currentURI = composite.getChildren().get(1);
                        }
                        
                    } else if(composite.getType().equals(CompositeType.RC)){
                        //Do this later.....
                    }
                } else {
                    //Incase it is a CDS or Terminator (under the current assumptions).
                    currentURI = candidatecomp.getCandidate().getComponentDefintion();
                }
                
                if(specificAssignment.contains(currentURI)){
                    return false;
                }
                
                specificAssignment.add(currentURI);
            }
        }
        //</editor-fold>
        
        
        //Do the self loop check here....
        //<editor-fold desc="Self loop check - Commented out">
        /*
        for(int i=0;i<module.getChildren().size();i++){
            Module tu = module.getChildren().get(i);
            Set<URI> promprots = new HashSet<>();
            URI cdsprot = null;
            for(Component c:tu.getComponents()){
                if(c.isPromoter()){
                    String cname = c.getName();
                    LibraryComponent lc = assignment.get(cname).getCandidate();
                    PromoterComponent promcomp = null;
                    if (lc instanceof CompositeComponent) {
                        CompositeComponent compcomp = (CompositeComponent) lc;
                        promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                    } else {
                        promcomp = (PromoterComponent) lc;
                    }
                    for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                        if (tf instanceof PrimitiveComponent) {
                            promprots.add(tf.getComponentDefintion());
                        } else if (tf instanceof ComplexComponent) {
                            ComplexComponent complexComponent = (ComplexComponent) tf;
                            promprots.add(complexComponent.getProtein());
                        }
                    }
                } else if(c.isCDS()){
                    String cname = c.getName();
                    CDSComponent cdscomp = (CDSComponent)assignment.get(cname).getCandidate();
                    cdsprot = cdscomp.getProtein();
                }
            }
            if( (cdsprot != null)) {
                if (promprots.contains(cdsprot)) {
                    //System.out.println("SELF LOOP");
                    //AbstractSimulation.printAssignment(module, assignment);
                    boolean selfloop = true;
                    for (int j = 0; j < module.getChildren().size(); j++) {
                        if (i != j) {
                            Module outertu = module.getChildren().get(j);
                            Set<URI> outerpromprots = new HashSet<>();
                            URI outercdsprot = null;
                            for (Component c : outertu.getComponents()) {
                                if (c.isPromoter()) {
                                    String cname = c.getName();
                                    LibraryComponent lc = assignment.get(cname).getCandidate();
                                    PromoterComponent promcomp = null;
                                    if (lc instanceof CompositeComponent) {
                                        CompositeComponent compcomp = (CompositeComponent) lc;
                                        promcomp = library.getAllPromoters().get(compcomp.getChildren().get(0));
                                    } else {
                                        promcomp = (PromoterComponent) lc;
                                    }
                                    for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                                        if (tf instanceof PrimitiveComponent) {
                                            outerpromprots.add(tf.getComponentDefintion());
                                        } else if (tf instanceof ComplexComponent) {
                                            ComplexComponent complexComponent = (ComplexComponent) tf;
                                            outerpromprots.add(complexComponent.getProtein());
                                        }
                                    }
                                } else if (c.isCDS()) {
                                    String cname = c.getName();
                                    CDSComponent cdscomp = (CDSComponent) assignment.get(cname).getCandidate();
                                    outercdsprot = cdscomp.getProtein();
                                }
                            }
                            if (!outercdsprot.equals(cdsprot)) {
                                if (outerpromprots.contains(cdsprot)) {
                                    selfloop = false;
                                    break;
                                }
                            }
                            
                        }
                    }
                    if(selfloop){
                        //System.out.println("There is a self loop here.. :( ");
                        return false;
                    }
                    //System.out.println("--------------------------------");
        
                }
            }
            
        }
        */
        //</editor-fold>
        
        if(redundantAssignment(module,assignment,library)){
            return false;
        }
        
        
                    
        return true;
    }
    
    private void traverseRegulationPR(URI protein, List<Module> tulist, Set<URI> activeCDS, Map<String,CandidateComponent> assignment, Library library){
        //Set<URI> regulating = new HashSet<>();
        for(Module tu:tulist){
            Component cds = tu.getChildren().get(1).getComponents().get(0);
            if(!activeCDS.contains(assignment.get(cds.getName()).getCandidate().getComponentDefintion())){
                CDSComponent cdscomp = (CDSComponent) (assignment.get(cds.getName()).getCandidate());
                if (cdscomp.getProtein().equals(protein)) {
                    activeCDS.add(assignment.get(cds.getName()).getCandidate().getComponentDefintion());
                    Module pr = tu.getChildren().get(0);
                    Component prom = Module.getPromInPR(pr);
                    CompositeComponent promlc = (CompositeComponent) assignment.get(prom.getName()).getCandidate();
                    PromoterComponent promcomp = library.getAllPromoters().get(promlc.getChildren().get(0));
                    for(LibraryComponent tf:promcomp.getTranscriptionFactors()){
                        URI promprot;
                        if(tf instanceof ComplexComponent){
                            ComplexComponent complex = (ComplexComponent)tf;
                            promprot = complex.getProtein();
                        } else {
                            promprot = tf.getComponentDefintion();
                        }
                        traverseRegulationPR(promprot,tulist,activeCDS,assignment,library);
                    }
                }
            }
            
        }
            
        
        //return regulating;
    }
    
    private boolean redundantAssignment(Module m, Map<String,CandidateComponent> assignment, Library library){
        
        List<Module> tulist = new ArrayList<>(m.getChildren());
        Set<URI> allCDS = new HashSet<>();
        Set<URI> activeCDS = new HashSet<>();
        for (Component c : m.getComponents()) {
            if (c.isCDS()) {
                LibraryComponent lc = assignment.get(c.getName()).getCandidate();
                allCDS.add(lc.getComponentDefintion());
                if (library.getOutputCDS().containsKey(lc.getComponentDefintion())) {
                    activeCDS.add(lc.getComponentDefintion());
                } 
            }
        }
        
        for(Module tu:tulist){
            Module cds = tu.getChildren().get(1);
            if(cds.getRole().equals(ModuleRole.CDS)){
                Component c = cds.getComponents().get(0);
                if(activeCDS.contains(assignment.get(c.getName()).getCandidate().getComponentDefintion())){
                    //This TU contains an output CDS
                    Module pr = tu.getChildren().get(0);
                    Component prom = Module.getPromInPR(pr);
                    CompositeComponent promlc = (CompositeComponent)assignment.get(prom.getName()).getCandidate();
                    PromoterComponent promcomp = library.getAllPromoters().get(promlc.getChildren().get(0));
                    for(LibraryComponent tf:promcomp.getTranscriptionFactors()){
                        URI promprot;
                        if(tf instanceof ComplexComponent){
                            ComplexComponent complex = (ComplexComponent)tf;
                            promprot = complex.getProtein();
                        } else {
                            promprot = tf.getComponentDefintion();
                        }
                        traverseRegulationPR(promprot,tulist,activeCDS,assignment,library);
                    }
                    
                     
                }
            } else if(cds.getRole().equals(ModuleRole.RBS_CDS)){
                throw new UnsupportedOperationException("P_RC_T not supported yet.");
            }
        }
        
        if(activeCDS.containsAll(allCDS)){
            return false;
        }
        
        return true;
    }
    
    
    private URI getAssignmentURI(Component c, LibraryComponent lc){
        
        if(c.isTerminator()){
            return lc.getComponentDefintion();
        }
        
        
        if(c.isPromoter()){
            if(lc instanceof CompositeComponent){
                CompositeComponent cc = (CompositeComponent) lc;
                return cc.getChildren().get(0);
            } else if(lc instanceof PromoterComponent){
                return lc.getComponentDefintion();
            }
        }
        
        if(c.isRBS()){
            if(lc instanceof CompositeComponent){
                CompositeComponent cc = (CompositeComponent) lc;
                if(cc.getType().equals(CompositeType.PR)){
                    return cc.getChildren().get(1);
                } else if(cc.getType().equals(CompositeType.RC)){
                    return cc.getChildren().get(0);
                }
            } else if(lc instanceof PrimitiveComponent){
                return lc.getComponentDefintion();
            }
        }
        
        if(c.isCDS()){
            if(lc instanceof CompositeComponent){
                CompositeComponent cc = (CompositeComponent) lc;
                return cc.getChildren().get(1);
            } else if(lc instanceof CDSComponent){
                return lc.getComponentDefintion();
            }
        }
        
        
        
        return null;
    }
    
    protected int getOutputCount(TreeNode stl){
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
    
    protected void assignLeafCandidates(Module module, Library library){
        
        for(Module tu:module.getChildren()){
            assignTUchildCandidates(tu,library);
        }
        
        if(!validateCandidates(module)){
            System.out.println("THROW AN ERROR");
        }
        
    }
    
    //<editor-fold desc="Assign TU Child Candidates">
    protected void assignTUchildCandidates(Module tu, Library library){
        
        for(Module child:tu.getChildren()){
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
            if(p.getInteractions().isEmpty()){
                prCandidates = library.getPr();
            } else {
                prCandidates = library.getPR(ComponentRole.PROMOTER_INDUCIBLE);
            }
            
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
                cCandidates.putAll(library.getActivatingCDS());
                //cCandidates.putAll(library.getConnectorCDS());                
            } else if(c.getRole().equals(Component.ComponentRole.CDS_REPRESSOR)){
                cCandidates.putAll(library.getRepressingCDS());
                //cCandidates.putAll(library.getConnectorCDS());                
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
    
    private boolean validateCandidates(Module module){
        List<Module> prList = new ArrayList<>();
        List<Module> promList = new ArrayList<>();
        List<Module> rcList = new ArrayList<>();
        List<Module> cdsList = new ArrayList<>();
        List<Module> terList = new ArrayList<>(); 
        
        for (Module tu : module.getChildren()) {
            for (Module child : tu.getChildren()) {
                switch (child.getRole()) {
                    case PROMOTER:
                        promList.add(child);
                        break;
                    case PROMOTER_RBS:
                        prList.add(child);
                        break;
                    case RBS_CDS:
                        rcList.add(child);
                        break;
                    case CDS:
                        cdsList.add(child);
                        break;
                    case TERMINATOR:
                        terList.add(child);
                        break;
                }
            }
        }
        
        
        System.out.println("PR List size ::  " + prList.size());
        for(Module ipr:prList){
            Component prom = Module.getPromInPR(ipr); 
            Component rbs = Module.getRbsInPR(ipr);
            
            if(prom.getRole().equals(ComponentRole.GENERIC_PROMOTER) && rbs.getRole().equals(ComponentRole.GENERIC_RBS)){
                    continue;
            }
            
            List<CandidateComponent> toAdd = new ArrayList<>();
            for(CandidateComponent cc:ipr.getCandidates()){
                URI promCandidate = getAssignmentURI(prom, cc.getCandidate());
                URI rbsCandidate = getAssignmentURI(rbs, cc.getCandidate());
                if(existsInAllPR(prom,rbs,promCandidate, rbsCandidate, prList)){
                    toAdd.add(cc);
                }
            }
            if(toAdd.isEmpty()){
                return false;
            }
            ipr.setCandidates(toAdd);
        }
        
        
        return true;
    }
    
    private boolean existsInAllPR(Component promcomp, Component rbscomp, URI promuri, URI rbsuri, List<Module> prList){
        
        Set<String> cp = new HashSet<>();
        Set<String> rp = new HashSet<>();
        Set<String> ap = new HashSet<>();
        Set<String> ip = new HashSet<>();
        Set<String> gp = new HashSet<>();
        
        Set<String> gr = new HashSet<>();
        Set<String> sr = new HashSet<>();
        
        for(Module m:prList){
            Component prom = Module.getPromInPR(m);
            if (prom.getName().equals(promcomp.getName())) {
                if (prom.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)) {
                    cp.add(prom.getName());
                } else if (prom.getRole().equals(ComponentRole.PROMOTER_ACTIVATABLE)) {
                    ap.add(prom.getName());
                } else if (prom.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)) {
                    rp.add(prom.getName());
                } else if (prom.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                    ip.add(prom.getName());
                } else if (prom.getRole().equals(ComponentRole.GENERIC_PROMOTER)) {
                    gp.add(prom.getName());
                }
            }
            Component rbs = Module.getRbsInPR(m);
            if (rbs.getName().equals(rbscomp.getName())) {
                if (rbs.getRole().equals(ComponentRole.RBS)) {
                    sr.add(rbs.getName());
                } else if (rbs.getRole().equals(ComponentRole.GENERIC_RBS)) {
                    gr.add(rbs.getName());
                }
            }
        }
        
        for(Module pr:prList){
            Component prom = Module.getPromInPR(pr);
            Component rbs = Module.getRbsInPR(pr);
            if (!prom.getRole().equals(ComponentRole.GENERIC_PROMOTER)) {
                if (prom.getName().equals(promcomp.getName())) {
                    int count = 0;
                    for (CandidateComponent cc : pr.getCandidates()) {
                        URI promCandidate = getAssignmentURI(prom, cc.getCandidate());
                        if (promCandidate.equals(promuri)) {
                            count++;
                        }
                    }
                    if (count == 0) {
                        return false;
                    }
                    if (rbscomp.getRole().equals(ComponentRole.RBS)) {
                        if (count < sr.size()) {
                            return false;
                        }
                    }
                }
            }
            
            if(!rbs.getRole().equals(ComponentRole.GENERIC_RBS)){
                if(rbs.getName().equals(rbscomp.getName())){
                    int count = 0;
                    for(CandidateComponent cc:pr.getCandidates()){
                        URI rbscandidate = getAssignmentURI(rbs, cc.getCandidate());
                        if(rbscandidate.equals(rbsuri)){
                            count++;
                        }
                    }
                    if(count == 0) {
                        return false;
                    }
                    if (promcomp.getRole().equals(ComponentRole.PROMOTER_CONSTITUTIVE)) {
                        if (count < cp.size()) {
                            return false;
                        }
                    } else if (promcomp.getRole().equals(ComponentRole.PROMOTER_ACTIVATABLE)) {
                        if (count < ap.size()) {
                            return false;
                        }
                    } else if (promcomp.getRole().equals(ComponentRole.PROMOTER_REPRESSIBLE)) {
                        if (count < rp.size()) {
                            return false;
                        }
                    } else if (promcomp.getRole().equals(ComponentRole.PROMOTER_INDUCIBLE)) {
                        if (count < ip.size()) {
                            return false;
                        }
                    }
                }
            }            
        }
        
        return true;
    }
    
    
    
    //</editor-fold>
    
}
