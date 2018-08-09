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
import java.util.concurrent.ThreadLocalRandom;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.library.CDSComponent;
import org.cidarlab.phoenix.dom.library.ComplexComponent;
import org.cidarlab.phoenix.dom.library.CompositeComponent;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.dom.library.LibraryComponent;
import org.cidarlab.phoenix.dom.library.PrimitiveComponent;
import org.cidarlab.phoenix.dom.library.PromoterComponent;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class SimulatedAnnealing extends AbstractAssignment {

    private double coolingRate = 0.003;
    private double temperature = 1000;

    @Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {
        for (Module m : modules) {
            solve(m, library, stl, args);
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

    private void solve(Module module, Library library, TreeNode stl, Args args) {
        this.assignLeafCandidates(module, library);

        int count = 0;
        int outCount = getOutputCount(stl);
        List<Module> children = new ArrayList<>();
        for (Module tu : module.getChildren()) {
            children.addAll(tu.getChildren());
        }

        System.out.println("Candidate Assignments for each Leaf Module:");
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
        }
        Map<String, CandidateComponent> first = generateFirstAssignment(module, library, stl);

        System.out.println("First Assignment : " + assignmentToString(module, first));

        while (temperature > 1) {
            //System.out.println("Temperature :: " + temperature);
            temperature *= (1 - coolingRate);
            count++;
        }

        System.out.println("Total :: " + count);
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

    private int getRandom(int min, int max) {
        if (min == max) {
            return min;
        }
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
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
                        cc = subset.get(getRandom(0, subset.size() - 1));
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
                        cc = subset.get(getRandom(0, subset.size() - 1));

                    } else {
                        //Does not contain Prom & Does not contain RBS
                        cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));

                    }
                }

                assignment.put(prom.getName(), cc);
                assignment.put(rbs.getName(), cc);
            } 
            //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for Promoter - Do this later">
            else if (leaf.getRole().equals(ModuleRole.PROMOTER)) {
                cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));
                Component prom = leaf.getComponents().get(0);

                //Do this later..
                assignment.put(prom.getName(), cc);
            } 
            //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for Terminator">
            else if (leaf.getRole().equals(ModuleRole.TERMINATOR)) {
                Component ter = leaf.getComponents().get(0);
                cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));
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
                            cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));
                            System.out.println("Probably going to fail anyway. Will this happen though?");
                        } else {
                            cc = subset.get(getRandom(0, subset.size() - 1));
                        }
                    } else {
                        cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));
                    }
                    
                } else {
                    cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));
                }
                assignment.put(cds.getName(), cc);
            } //</editor-fold>
            
            //<editor-fold desc="Assign CandidateComponent for RBS_CDS - Do this later">
            else if (leaf.getRole().equals(ModuleRole.RBS_CDS)) {
                cc = leaf.getCandidates().get(getRandom(0, leaf.getCandidates().size() - 1));

                Component rbs = Module.getRbsInRC(leaf);
                Component cds = Module.getCDSInRC(leaf);
                assignment.put(rbs.getName(), cc);
                assignment.put(cds.getName(), cc);
            }
            //</editor-fold>

        }

        return assignment;
    }

    
    private URI getPRrbs(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(1);
    }

    private URI getPRprom(CandidateComponent cc) {
        CompositeComponent compcomp = (CompositeComponent) cc.getCandidate();
        return compcomp.getChildren().get(0);
    }

    private Map<String, CandidateComponent> findNeighbour() {
        Map<String, CandidateComponent> neighbour = new HashMap<>();

        return neighbour;
    }

}
