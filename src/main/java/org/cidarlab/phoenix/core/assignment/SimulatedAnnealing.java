/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.core.simulation.AbstractSimulation;
import org.cidarlab.phoenix.dom.AssignmentNode;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
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
import org.cidarlab.phoenix.dom.library.SmallMoleculeComponent;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.sbml.jsbml.SBMLDocument;

/**
 *
 * @author prash
 */
public class SimulatedAnnealing extends AbstractAssignment {

    private final double coolingRate = 0.003;
    private double temperature = 1000;

    //@Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {
        for (Module m : modules) {
            try {
                solve(m, library, stl, args);
            } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | TLIException ex) {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < modules.size(); i++) {
            String simfp = args.getProjectFolder() + i + Utilities.getSeparater();
            Utilities.makeDirectory(simfp);
            Module m = modules.get(i);

            /*try {
             Simulation.run(modules.get(i),library,stl,args,simfp);
             } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
             Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException | TLIException | InterruptedException ex) {
             Logger.getLogger(Exhaustive.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        }
    }

    private void solve(Module module, Library library, TreeNode stl, Args args) throws URISyntaxException, MalformedURLException, XMLStreamException, FileNotFoundException, IOException, TLIException {
        this.assignLeafCandidates(module, library);

        int count = 0;
        int outCount = getOutputCount(stl);
        List<Module> children = new ArrayList<>();
        for (Module tu : module.getChildren()) {
            children.addAll(tu.getChildren());
        }

        /*System.out.println("Candidate Assignments for each Leaf Module:");
        for (Module child : children) {
            //System.out.println("Module : " + child.getId());
            System.out.print("Module : ");
            for (Component c : child.getComponents()) {
                System.out.print(c.getName() + " ");
            }
            System.out.println("\nCandidates ::");
            for (CandidateComponent cc : child.getCandidates()) {
                System.out.println("-- " + cc.getCandidate().getComponentDefintion());
            }
        }*/
        
        String testfp = args.getProjectFolder() + "test" + Utilities.getSeparater();
        Utilities.makeDirectory(testfp);
        Map<URI,SBMLDocument> modelmap = AbstractSimulation.downloadAllModels(library, testfp);
        Map<String, CandidateComponent> currentAssignment = generateFirstAssignment(module, library, stl);
        
        AssignmentNode currentNode = new AssignmentNode(module,currentAssignment,library);
        currentNode.assignRandomConcentrations(library);
        
        System.out.println("First Assignment : " + currentNode.toString());
        //System.out.println("First Assignment : " + assignmentToString(module, first));
        
        double currentScore = currentNode.robustness(module, stl, library, modelmap, args.getDecomposition(), testfp);
        double newScore = 0.0;
        
        System.out.println("Current Score : " + currentScore);
        
        while (temperature > 1) {
            //System.out.println("Temperature :: " + temperature);
            AssignmentNode newNode = findNeighbor(module,currentNode,library);
            while(newNode == null){
                newNode = findNeighbor(module,currentNode,library);
            }
            newScore = newNode.robustness(module, stl, library, modelmap, args.getDecomposition(), testfp);
            
            double ap = acceptanceProbability(currentScore,newScore,temperature,1);
            double random = Math.random();
            if(ap > random){
                currentScore = newScore;
                currentNode = newNode; // or make this new AssignmentNode(newNode);
            }
            
            temperature *= (1 - coolingRate);
            count++;
        }

        System.out.println("Total :: " + count);
    }
    
    
    private boolean repeatRandom(int pos, Module module, AssignmentNode current, Library lib){
        
        if(pos < current.getComponents().size()){
            //Change the component
            Component curr = current.getComponents().get(pos);
            //curr's assignment needs to change. 
            List<Module> subsets = new ArrayList<>();
            for(Module tu:module.getChildren()){
                for(Module leaf:tu.getChildren()){
                    for(Component c:leaf.getComponents()){
                        if(c.getName().equals(curr.getName())){
                            subsets.add(leaf);
                            break;
                        }
                    }
                }
            }
            
            for(Module subset:subsets){
                if(subset.getCandidates().size() == 1){
                    return true;
                }
                
                switch(subset.getRole()){
                    case PROMOTER_RBS:
                        
                        if(curr.isPromoter()){
                            Set<URI> promURIs = new HashSet<>();
                            for(CandidateComponent cc:subset.getCandidates()){
                                promURIs.add(getPRprom(cc));
                            }
                            if(promURIs.size() == 1){
                                return true;
                            }
                        } else if(curr.isRBS()){
                            Set<URI> rbsURIs = new HashSet<>();
                            for(CandidateComponent cc:subset.getCandidates()){
                                rbsURIs.add(getPRrbs(cc));
                            }
                            if(rbsURIs.size() == 1){
                                return true;
                            }
                        }
                        
                        
                        break;
                    case CDS:
                        //doesn't matter.. Already taken care of?
                        break;
                    case TERMINATOR:
                        //doesn't matter.. Already taken care of?
                        break;
                    case PROMOTER:
                        //doesn't matter.. Already taken care of?
                        break;
                    case RBS_CDS:
                        //Handle this later?
                        break;
                }
                
            }
           
            
        } else {
            //Change the Small Molecule Concentration.
            int smIndex = pos - current.getComponents().size();
            String sm = current.getSmallMolecules().get(smIndex);
            SmallMoleculeComponent smc = lib.getSmallMolecules().get(current.getSmURImap().get(sm));
            if(smc.getValues().size() == 1){
                return true;
            }
        }
        
        
        
        return false;
        
    }
    
            
    public AssignmentNode findNeighbor(Module module, AssignmentNode current, Library lib){
        
        AssignmentNode nextNode = new AssignmentNode(current);
        int pos = current.getRandomPosition();
        
        while(repeatRandom(pos, module, current,lib)){
            pos = current.getRandomPosition();
        }
        
        List<Module> prList = new ArrayList<>();
        List<Module> rcList = new ArrayList<>();
        List<Module> promList = new ArrayList<>();
        List<Module> rbsList = new ArrayList<>();
        List<Module> cdsList = new ArrayList<>();
        List<Module> terList = new ArrayList<>();
        
        
        for (Module tu : module.getChildren()) {
            for (Module leaf : module.getChildren()) {
                switch(leaf.getRole()){
                    case PROMOTER_RBS:
                        prList.add(leaf);
                        break;
                    case PROMOTER:
                        promList.add(leaf);
                        break;
                    case RBS_CDS:
                        rcList.add(leaf);
                        break;
                    case CDS:
                        cdsList.add(leaf);
                        break;
                    case TERMINATOR:
                        terList.add(leaf);
                        break;
                }
            }
        }
        
        
        
        if(pos < current.getComponents().size()){
            //Change the component
            Component curr = current.getComponents().get(pos);
            List<Module> subsets = new ArrayList<>();
            for(Module tu:module.getChildren()){
                for(Module leaf:tu.getChildren()){
                    for(Component c:leaf.getComponents()){
                        if(c.getName().equals(curr.getName())){
                            subsets.add(leaf);
                            break;
                        }
                    }
                }
            }
            
            for(Module subset:subsets){
                switch(subset.getRole()){
                    case PROMOTER_RBS:
                        
                        
                        break;
                    case CDS:
                        
                        
                        break;
                    case TERMINATOR:
                        List<CandidateComponent> tercc = subset.getCandidates();
                        URI currentTer = nextNode.getAssignment().get(curr.getName()).getCandidate().getComponentDefintion();
                        CandidateComponent newTercc = tercc.get(Utilities.getRandom(0, (tercc.size()-1) ));
                        while(newTercc.getCandidate().getComponentDefintion().equals(currentTer)){
                            newTercc = tercc.get(Utilities.getRandom(0, (tercc.size()-1) ));
                        }
                        Map<String,CandidateComponent> newAssignmentTer = new HashMap<>();
                        newAssignmentTer.put(curr.getName(), newTercc);
                        if(curr.isGeneric()){
                            //Do nothing?
                        } else {
                            //This terminator is not generic. So find if the nextTer clashes with another instance of terList which is not generic, and has the same assignment as nextTer. 
                            while(clashes(module, newAssignmentTer,nextNode.getAssignment())){
                                Set<URI> assignmentURIs = getAssignmentURIs(module, newAssignmentTer);
                                List<Module> specTer = new ArrayList<>();
                                for(Module ter:terList){
                                    for(Component c:ter.getComponents()){
                                        if(!newAssignmentTer.containsKey(c.getName())){
                                            if(!c.isGeneric()){
                                                if(c.isTerminator()){
                                                    URI cAss = nextNode.getAssignment().get(c.getName()).getCandidate().getComponentDefintion();
                                                    if(assignmentURIs.contains(cAss)){
                                                        specTer.add(ter);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                for(Module ter:specTer){
                                    
                                    Component terComp = ter.getComponents().get(0);
                                    List<CandidateComponent> candidateSubset = new ArrayList<>();
                                    for(CandidateComponent cc:ter.getCandidates()){
                                        URI cAss = cc.getCandidate().getComponentDefintion();
                                        if(!assignmentURIs.contains(cAss)){
                                            candidateSubset.add(cc);
                                        }
                                    }
                                    
                                    if(candidateSubset.isEmpty()){
                                        return null;
                                    } else {
                                        newAssignmentTer.put(terComp.getName(), candidateSubset.get(Utilities.getRandom(0,(candidateSubset.size()-1))) );
                                    }
                                }
                            }
                            
                        }
                        
                        break;
                    case PROMOTER:
                        break;
                    case RBS_CDS:
                        break;
                }
            }
            
            
            
        } else {
            //Change the Small Molecule Concentration.
            int smIndex = pos - current.getComponents().size();
            String sm = current.getSmallMolecules().get(smIndex);
            double currentConc = current.getConcs().get(sm);
            
            SmallMoleculeComponent smc = lib.getSmallMolecules().get(current.getSmURImap().get(sm));
            double newConc = smc.getValues().get(Utilities.getRandom(0, (smc.getValues().size()-1) ));
            while(newConc == currentConc){
                newConc = smc.getValues().get(Utilities.getRandom(0, (smc.getValues().size()-1) ));
            }
            
            nextNode.changeConc(sm, newConc);
            
        }
        
        
        
        
        return nextNode;
    }
    
    private static Set<URI> getAssignmentURIs(Module m, Map<String,CandidateComponent> assignment){
        Set<URI> uris = new HashSet<>();
        for(Component c:m.getComponents()){
            CandidateComponent cc = assignment.get(c.getName());
            
            if(cc.getCandidate() instanceof CompositeComponent){
                CompositeComponent composite = (CompositeComponent) cc.getCandidate();
                if(c.isPromoter()){
                    uris.add(getPRprom(cc));
                } else if(c.isRBS()){
                    if(composite.getType().equals(CompositeType.PR)){
                        uris.add(getPRrbs(cc));
                    } else if(composite.getType().equals(CompositeType.RC)){
                        uris.add(getRCrbs(cc));
                    }
                } else if(c.isCDS()){
                    uris.add(getRCcds(cc));
                }
            } else {
                uris.add(cc.getCandidate().getComponentDefintion());
            }
            
        }
        
        return uris;
    }
    
    private static boolean clashes(Module module, Map<String,CandidateComponent> newAssignment, Map<String,CandidateComponent> currAssignment){
        
        Set<String> newSpec = new HashSet<>();
        Set<String> currSpec = new HashSet<>();
        Set<String> prom = new HashSet<>();
        Set<String> rbs = new HashSet<>();
        Set<String> cds = new HashSet<>();
        Set<String> ter = new HashSet<>();
        
        for(Component c:module.getComponents()){
            if(!c.isGeneric()){
                if(newAssignment.containsKey(c.getName())){
                    newSpec.add(c.getName());
                }
                if(currAssignment.containsKey(c.getName())){
                    currSpec.add(c.getName());
                }
                if(c.isPromoter()){
                    prom.add(c.getName());
                } else if(c.isRBS()){
                    rbs.add(c.getName());
                } else if(c.isCDS()){
                    cds.add(c.getName());
                } else if(c.isTerminator()){
                    ter.add(c.getName());
                }
            }
        }
        
        for(String newComp:newSpec){
            for(String currComp:currSpec){
                if (!newComp.equals(currComp)) {
                    if (prom.contains(newComp) && prom.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if(newcc.getCandidate() instanceof CompositeComponent){
                            if(getPRprom(newcc).equals(getPRprom(currcc))){
                                return true; 
                            }
                        } else {
                            if(newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())){
                                return true;
                            }
                        }
                        
                    } else if (rbs.contains(newComp) && rbs.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if(newcc.getCandidate() instanceof CompositeComponent){
                            
                            CompositeComponent newComposite = (CompositeComponent) newcc.getCandidate();
                            CompositeComponent currComposite = (CompositeComponent) newcc.getCandidate();
                            
                            URI newRBS = null;
                            URI currRBS = null;
                            
                            if(newComposite.getType().equals(CompositeType.PR)){
                                newRBS = getPRrbs(newcc);
                            } else if(newComposite.getType().equals(CompositeType.RC)){
                                newRBS = getRCrbs(newcc);
                            }
                            
                            if(currComposite.getType().equals(CompositeType.PR)){
                                currRBS = getPRrbs(currcc);
                            } else if(currComposite.getType().equals(CompositeType.RC)){
                                currRBS = getRCrbs(currcc);
                            }
                            
                            if(newRBS.equals(currRBS)){
                                return true; 
                            }
                            
                            
                        } else {
                            if(newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())){
                                return true;
                            }
                        }
                    } else if (cds.contains(newComp) && cds.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if(newcc.getCandidate() instanceof CompositeComponent){
                            if(getRCcds(newcc).equals(getRCcds(currcc))){
                                return true; 
                            }
                        } else {
                            if(newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())){
                                return true;
                            }
                        }
                    } else if (ter.contains(newComp) && ter.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if (newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static double acceptanceProbability(double currentScore, double newScore, double temperature, int hammingDistance){
        
        //System.out.println("Acceptance Probability Function");
        double deltaE = newScore - currentScore;
        //System.out.println("Current Score : " + currentScore);
        //System.out.println("New Score     : " + newScore);
        //System.out.println("Detla E       : " + deltaE);
        //System.out.println("Temperature   : " + temperature);
        //System.out.println("Difference    : " + hammingDistance);
        double exp = (-deltaE)/(temperature * hammingDistance);
        //System.out.println("Exp           : " + (Math.pow(Math.E, exp)));
        
        double prob = 1.0 / (1.0 + (Math.pow(Math.E, exp)));
        
        //System.out.println("Probability   : " + prob);
        //System.out.println("---------------------------------");
        return prob;
        
    }
    
    
    
    private String assignmentToString(Module module, Map<String, CandidateComponent> assignment) {
        String str = "";
        for (Component c : module.getComponents()) {
            if (assignment.containsKey(c.getName())) {
                str += (c.getName() + ":" + assignment.get(c.getName()).getCandidate().getName() + ";");
            }
        }

        return str;
    }


    private Map<String, CandidateComponent> generateFirstAssignment(Module module, Library library, TreeNode stl) {

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

    
    private static URI getPRprom(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(0);
    }
    
    private static URI getPRrbs(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(1);
    }

    
    
    private static URI getRCrbs(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(0);
    }

    private static URI getRCcds(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(1);
    }

}
