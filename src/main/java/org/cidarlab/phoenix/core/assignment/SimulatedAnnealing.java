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

    private double coolingRate = 0.0045;
    private double temperature = 10000;

    //@Override
    public void solve(List<Module> modules, Library library, TreeNode stl, Args args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < modules.size(); i++) {
            try {
                Module m = modules.get(i);
                solve(m, library, stl, args, i);
            } catch (URISyntaxException | MalformedURLException | XMLStreamException | FileNotFoundException ex) {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | TLIException ex) {
                Logger.getLogger(SimulatedAnnealing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long end = System.currentTimeMillis();
        long timediff = (end - start) / 1000;
        System.out.println("Simulated Annealing took :: " + timediff + " seconds.");

    }

    private void solve(Module module, Library library, TreeNode stl, Args args, int moduleindex) throws URISyntaxException, MalformedURLException, XMLStreamException, FileNotFoundException, IOException, TLIException {
        this.assignLeafCandidates(module, library);

        int count = 0;
        int outCount = getOutputCount(stl);

        int candidateCount = 1;
        for (Module tu : module.getChildren()) {
            for (Module leaf : tu.getChildren()) {
                candidateCount *= leaf.getCandidates().size();
            }
        }

        if (candidateCount < 40000) {
            setCoolingRate(candidateCount);
        }

        String resultsfp = args.getProjectFolder() + "saResults" + Utilities.getSeparater();
        Utilities.makeDirectory(resultsfp);
        Map<URI, SBMLDocument> modelmap = AbstractSimulation.downloadAllModels(library, resultsfp);

        String simfp = resultsfp + "0" + Utilities.getSeparater();
        Utilities.makeDirectory(simfp);

        Map<String, CandidateComponent> currentAssignment = getRandomAssignment(module, library, stl);
        AssignmentNode currentNode = new AssignmentNode(module, currentAssignment, library);
        currentNode.assignRandomConcentrations(library);

        double currentScore = currentNode.robustness(module, stl, library, modelmap, args.getDecomposition(), simfp);
        double newScore = 0.0;

        double maxScore = currentScore;
        AssignmentNode bestnode = currentNode;

        List<String> lines = new ArrayList<>();

        lines.add(currentNode.toString(library) + "," + currentScore);
        System.out.println("First Assignment : " + currentNode.toString(library));
        System.out.println("Current Score : " + currentScore);
        System.out.println("----------------------------------------------------");
        System.out.println("----------------------------------------------------");
        int loopThreshold = 1000;
        int loopCount = 0;
        boolean cooledDown = true;
        while (temperature > 1) {
            //System.out.println("Temperature :: " + temperature);
            AssignmentNode newNode;
            loopCount = 0;

            do {
                newNode = findNeighbor(module, currentNode, library, outCount, stl);
                loopCount++;
            } while ((newNode == null) && (loopCount < loopThreshold));

            if ((newNode == null) || (loopCount >= loopThreshold)) {
                cooledDown = false;
                break;
            }

            simfp = resultsfp + (count + 1) + Utilities.getSeparater();
            Utilities.makeDirectory(simfp);

            newNode.updateConcentrations(module, currentNode.getConcs(), library);
            newScore = newNode.robustness(module, stl, library, modelmap, args.getDecomposition(), simfp);

            lines.add(newNode.toString(library) + "," + newScore);
            if (newScore > maxScore) {
                maxScore = newScore;
                bestnode = newNode;
            }

            int hammingDistance = currentNode.hammingDistance(newNode);
            double ap = acceptanceProbability(currentScore, newScore, temperature, hammingDistance);
            double random = Math.random();

            System.out.println("Current Node  : " + currentNode.toString(library));
            System.out.println("Current Score : " + currentScore);
            System.out.println("Next Node     : " + newNode.toString(library));
            System.out.println("New Score     : " + newScore);
            System.out.println("Acceptance    : " + ap);
            if (ap > random) {
                System.out.println("Status        : Accepted");
                currentScore = newScore;
                currentNode = newNode; // or make this new AssignmentNode(newNode);
            } else {
                System.out.println("Status        : Rejected");
            }
            System.out.println("----------------------------------------------------");
            temperature *= (1 - coolingRate);
            count++;
        }

        if (!cooledDown) {
            System.out.println("Current thread was able to reach " + count + " nodes. This could be due to Random Number Generation. Please try again to check for better results");
        }

        Utilities.writeToFile(resultsfp + "results.csv", lines);

        System.out.println("Total :: " + count);
    }

    private boolean repeatRandom(int pos, Module module, AssignmentNode current, Library lib) {

        if (pos < current.getComponents().size()) {
            //Change the component
            Component curr = current.getComponents().get(pos);
            //curr's assignment needs to change. 
            List<Module> subsets = new ArrayList<>();
            for (Module tu : module.getChildren()) {
                for (Module leaf : tu.getChildren()) {
                    for (Component c : leaf.getComponents()) {
                        if (c.getName().equals(curr.getName())) {
                            subsets.add(leaf);
                            break;
                        }
                    }
                }
            }

            for (Module subset : subsets) {
                if (subset.getCandidates().size() == 1) {
                    return true;
                }

                switch (subset.getRole()) {
                    case PROMOTER_RBS:

                        if (curr.isPromoter()) {
                            Set<URI> promURIs = new HashSet<>();
                            for (CandidateComponent cc : subset.getCandidates()) {
                                promURIs.add(getPRprom(cc));
                            }
                            if (promURIs.size() == 1) {
                                return true;
                            }
                        } else if (curr.isRBS()) {
                            Set<URI> rbsURIs = new HashSet<>();
                            for (CandidateComponent cc : subset.getCandidates()) {
                                rbsURIs.add(getPRrbs(cc));
                            }
                            if (rbsURIs.size() == 1) {
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
            if (smc.getValues().size() == 1) {
                return true;
            }
        }

        return false;

    }

    private Map<URI, CandidateComponent> getOtherRBSCandidates(List<CandidateComponent> candidates, CandidateComponent pr) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        URI currprom = getPRprom(pr);
        URI currrbs = getPRrbs(pr);

        for (CandidateComponent cc : candidates) {
            URI otherprom = getPRprom(cc);
            URI otherrbs = getPRrbs(cc);
            if (currprom.equals(otherprom) && (!currrbs.equals(otherrbs))) {
                others.put(cc.getCandidate().getComponentDefintion(), cc);
            }
        }

        return others;

    }

    private Map<URI, CandidateComponent> getOtherPromCandidates(List<CandidateComponent> candidates, CandidateComponent pr) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        URI currprom = getPRprom(pr);
        URI currrbs = getPRrbs(pr);

        for (CandidateComponent cc : candidates) {
            URI otherprom = getPRprom(cc);
            //URI otherrbs = getPRrbs(cc);
            if (!currprom.equals(otherprom)) {
                others.put(cc.getCandidate().getComponentDefintion(), cc);
            }
        }

        return others;

    }

    private Map<URI, CandidateComponent> getOtherCandidates(List<CandidateComponent> candidates, LibraryComponent current) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        for (CandidateComponent cc : candidates) {
            if (!cc.getCandidate().getComponentDefintion().equals(current.getComponentDefintion())) {
                others.put(cc.getCandidate().getComponentDefintion(), cc);
            }
        }

        return others;

    }

    private Map<URI, CandidateComponent> getOtherOutputCDSCandidates(List<CandidateComponent> candidates, LibraryComponent current, Library lib) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        for (CandidateComponent cc : candidates) {
            if (!cc.getCandidate().getComponentDefintion().equals(current.getComponentDefintion())) {
                if (lib.getOutputCDS().containsKey(cc.getCandidate().getComponentDefintion())) {
                    others.put(cc.getCandidate().getComponentDefintion(), cc);
                }
            }
        }
        return others;
    }

    private Map<URI, CandidateComponent> getOtherActivatingCDSCandidates(List<CandidateComponent> candidates, LibraryComponent current, Library lib) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        for (CandidateComponent cc : candidates) {
            if (!cc.getCandidate().getComponentDefintion().equals(current.getComponentDefintion())) {
                if (lib.getActivatingCDS().containsKey(cc.getCandidate().getComponentDefintion())) {
                    others.put(cc.getCandidate().getComponentDefintion(), cc);
                }
            }
        }
        return others;
    }

    private Map<URI, CandidateComponent> getOtherRepressingCDSCandidates(List<CandidateComponent> candidates, LibraryComponent current, Library lib) {
        Map<URI, CandidateComponent> others = new HashMap<>();

        for (CandidateComponent cc : candidates) {
            if (!cc.getCandidate().getComponentDefintion().equals(current.getComponentDefintion())) {
                if (lib.getRepressingCDS().containsKey(cc.getCandidate().getComponentDefintion())) {
                    others.put(cc.getCandidate().getComponentDefintion(), cc);
                }
            }
        }
        return others;
    }

    private Map<URI, CandidateComponent> getOtherCDSCandidates(List<CandidateComponent> candidates, LibraryComponent current, Library lib) {

        if (lib.getOutputCDS().containsKey(current.getComponentDefintion())) {
            return getOtherOutputCDSCandidates(candidates, current, lib);
        } else if (lib.getActivatingCDS().containsKey(current.getComponentDefintion())) {
            return getOtherActivatingCDSCandidates(candidates, current, lib);
        } else if (lib.getRepressingCDS().containsKey(current.getComponentDefintion())) {
            return getOtherRepressingCDSCandidates(candidates, current, lib);
        }

        return null;

    }

    private AssignmentNode swapPromoter(Module module, AssignmentNode current, AssignmentNode nextNode, List<Module> prList, Component curr, CandidateComponent newcdscc, Library lib, TreeNode stl) {

        CDSComponent oldcds = (CDSComponent) lib.getAllLibraryComponents().get(current.getAssignment().get(curr.getName()).getCandidate().getComponentDefintion());
        CDSComponent newcds = (CDSComponent) lib.getAllLibraryComponents().get(newcdscc.getCandidate().getComponentDefintion());

        for (Component c : module.getComponents()) {
            if (current.getAssignment().get(c.getName()).getCandidate().getComponentDefintion().equals(oldcds.getComponentDefintion())) {
                nextNode.getAssignment().put(c.getName(), newcdscc);
            }
        }

        for (Module pr : prList) {
            Component prom = Module.getPromInPR(pr);
            Component rbs = Module.getRbsInPR(pr);
            CandidateComponent prcc = current.getAssignment().get(prom.getName());
            boolean regulated = false;
            PromoterComponent promcomp = lib.getAllPromoters().get(getPRprom(prcc));
            for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                if (tf instanceof ComplexComponent) {
                    ComplexComponent complex = (ComplexComponent) tf;
                    if (complex.getProtein().equals(oldcds.getProtein())) {
                        regulated = true;
                        break;
                    }
                } else {
                    if (tf.getComponentDefintion().equals(oldcds.getProtein())) {
                        regulated = true;
                        break;
                    }
                }
            }
            if (regulated) {
                //Swap this.
                URI rbsuri = getPRrbs(prcc);
                List<CandidateComponent> candidatesubset = new ArrayList<>();

                for (CandidateComponent cc : pr.getCandidates()) {
                    URI subsetRBSuri = getPRrbs(cc);
                    if (subsetRBSuri.equals(rbsuri) || (rbs.isGeneric())) {
                        URI newprot = newcds.getProtein();
                        PromoterComponent subsetpromcomp = lib.getAllPromoters().get(getPRprom(cc));
                        regulated = false;
                        for (LibraryComponent tf : subsetpromcomp.getTranscriptionFactors()) {
                            if (tf instanceof ComplexComponent) {
                                ComplexComponent complex = (ComplexComponent) tf;
                                if (complex.getProtein().equals(newprot)) {
                                    regulated = true;
                                }
                            } else {
                                if (tf.getComponentDefintion().equals(newprot)) {
                                    regulated = true;
                                }
                            }
                        }
                        if (regulated) {
                            candidatesubset.add(cc);
                            //Swap now..
                            nextNode.getAssignment().put(prom.getName(), cc);
                            nextNode.getAssignment().put(rbs.getName(), cc);
                            break;
                        } //Need a check to see if 
                    }
                }

                if (candidatesubset.isEmpty()) {
                    return null;
                }

            }
        }
        nextNode.updateConcentrations(module, current.getConcs(), lib);

        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
            return nextNode;
        }
        return null;
    }

    private AssignmentNode swapRBS(AssignmentNode current, AssignmentNode nextNode, Component prom, Component rbs, URI currentrbsuri, URI newrbsuri, CandidateComponent newcc, Module module, Library lib, TreeNode stl, List<Module> prList) {

        if (rbs.isGeneric() || (currentrbsuri.equals(newrbsuri))) {

        } else {
            boolean specificrbsassigned = false;
            for (Component c : module.getComponents()) {
                if (c.isRBS()) {
                    if (!c.isGeneric()) {
                        CandidateComponent cc = current.getAssignment().get(c.getName());
                        URI uri = getPRrbs(cc);
                        if (uri.equals(newrbsuri)) {
                            specificrbsassigned = true;
                        }
                    }
                }
            }

            if (specificrbsassigned) {
                for (Module pr : prList) {
                    Component otherprom = Module.getPromInPR(pr);
                    Component otherrbs = Module.getRbsInPR(pr);
                    CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                    URI otherpromuri = getPRprom(othercc);
                    URI otherrbsuri = getPRrbs(othercc);
                    if (otherrbsuri.equals(newrbsuri)) {
                        //This needs to be swapped with something where rbs is currentrbsuri and promuri is still otherpromuri
                        boolean alternatefound = false;
                        for (CandidateComponent alternatecc : pr.getCandidates()) {
                            URI alternaterbsuri = getPRrbs(alternatecc);
                            URI alternatepromuri = getPRprom(alternatecc);
                            if (alternaterbsuri.equals(currentrbsuri)) {
                                if (alternatepromuri.equals(otherpromuri)) {
                                    nextNode.getAssignment().put(otherprom.getName(), alternatecc);
                                    nextNode.getAssignment().put(otherrbs.getName(), alternatecc);
                                }
                            }
                        }
                        if (!alternatefound) {
                            return null;
                        }
                    }
                }
            }
        }
        nextNode.getAssignment().put(prom.getName(), newcc);
        nextNode.getAssignment().put(rbs.getName(), newcc);
        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
            return nextNode;
        } else {
            return null;
        }

    }

    private AssignmentNode findNeighbor(Module module, AssignmentNode current, Library lib, int outCount, TreeNode stl) {

        AssignmentNode nextNode = new AssignmentNode(current);
        int pos = current.getRandomPosition();
        int randomCount = 0;
        while (repeatRandom(pos, module, current, lib)) {
            pos = current.getRandomPosition();
            randomCount++;
            if (randomCount > 32) {
                return null;
            }
        }

        List<Module> prList = new ArrayList<>();
        List<Module> rcList = new ArrayList<>();
        List<Module> promList = new ArrayList<>();
        List<Module> cdsList = new ArrayList<>();
        List<Module> terList = new ArrayList<>();

        for (Module tu : module.getChildren()) {
            for (Module leaf : tu.getChildren()) {
                switch (leaf.getRole()) {
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

        if (pos < current.getComponents().size()) {
            //Change the component
            Component curr = current.getComponents().get(pos);
            LibraryComponent currlc = current.getAssignment().get(curr.getName()).getCandidate();
            //List<Module> subsets = new ArrayList<>();
            Module subset = null;
            for (Module tu : module.getChildren()) {
                for (Module leaf : tu.getChildren()) {
                    for (Component c : leaf.getComponents()) {
                        if (c.getName().equals(curr.getName()) && (c.getRole().equals(curr.getRole()))) {
                            subset = leaf;
                            break;
                        }
                    }
                }
            }
            if (subset == null) {
                //This should technically not happen
                return null;
            }

            //Module subset = subsets.get(0);
            switch (subset.getRole()) {
                case PROMOTER_RBS:
                    if (curr.isPromoter()) {
                        Component prom = Module.getPromInPR(subset);
                        Component rbs = Module.getRbsInPR(subset);
                        CandidateComponent currentcc = current.getAssignment().get(prom.getName());
                        URI currentpromuri = getPRprom(currentcc);
                        URI currentrbsuri = getPRrbs(currentcc);
                        
                        List<CandidateComponent> othercandidates = new ArrayList<>(getOtherPromCandidates(subset.getCandidates(), current.getAssignment().get(curr.getName())).values());

                        if (othercandidates.isEmpty()) {
                            return null;
                        }
                        CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));
                        URI newpromuri = getPRprom(newcc);
                        URI newrbsuri = getPRrbs(newcc);
                        if (curr.isGeneric()) {
                            //<editor-fold defaultstate="collapsed" desc="Swapping Generic Promoter"> 
                            if (constitutiveCandidate(currentcc, lib)) {
                                //Current Promoter is a constitutive Promoter. 
                                if (constitutiveCandidate(newcc, lib)) {
                                    //<editor-fold defaultstate="collapsed" desc="Current Promoter is Generic. Current Candidate: Consitutitve. New Candidate: Constitutive">
                                    //New Candidate Promoter is also constitutive. 
                                    if (rbs.isGeneric()) {
                                        //If current RBS is generic. Doesn't matter. Swap. 
                                        nextNode.getAssignment().put(prom.getName(), newcc);
                                        nextNode.getAssignment().put(rbs.getName(), newcc);
                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                            return nextNode;
                                        } else {
                                            return null;
                                        }
                                    } else {
                                        //Current RBS is specific. 

                                        if (currentrbsuri.equals(newrbsuri)) {
                                            //New RBS and Current RBS are the same. Swap.
                                            nextNode.getAssignment().put(prom.getName(), newcc);
                                            nextNode.getAssignment().put(rbs.getName(), newcc);
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                return nextNode;
                                            } else {
                                                return null;
                                            }
                                        } else {
                                            //New RBS and Current RBS are different. 
                                            boolean alreadyAssigned = false;
                                            for (Module pr : prList) {
                                                Component otherprom = Module.getPromInPR(pr);
                                                Component otherrbs = Module.getRbsInPR(pr);

                                                if (!otherrbs.isGeneric()) {
                                                    CandidateComponent othercc = current.getAssignment().get(otherrbs.getName());
                                                    URI otherrbsuri = getPRrbs(othercc);
                                                    URI otherpromuri = getPRprom(othercc);
                                                    if (otherrbsuri.equals(newrbsuri)) {
                                                        alreadyAssigned = true;
                                                        boolean foundAlternative = false;
                                                        for (CandidateComponent alternatecc : pr.getCandidates()) {
                                                            URI alternatepromuri = getPRprom(alternatecc);
                                                            URI alternaterbsuri = getPRrbs(alternatecc);
                                                            if (alternatepromuri.equals(otherpromuri)) {
                                                                if (alternaterbsuri.equals(currentrbsuri)) {
                                                                    nextNode.getAssignment().put(otherprom.getName(), alternatecc);
                                                                    nextNode.getAssignment().put(otherrbs.getName(), alternatecc);
                                                                }
                                                            }
                                                        }
                                                        if (!foundAlternative) {
                                                            return null;
                                                        }
                                                    }
                                                }
                                            }
                                            nextNode.getAssignment().put(prom.getName(), newcc);
                                            nextNode.getAssignment().put(rbs.getName(), newcc);
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                return nextNode;
                                            } else {
                                                return null;
                                            }
                                        }

                                    }
                                    //</editor-fold>
                                } else {
                                    //<editor-fold defaultstate="collapsed" desc="Current Promoter is Generic. Current Candidate: Constitutive. New Candidate: Inducible">
                                    //Old Candidate Promoter was Constitutive
                                    //New Candidate Promoter is Inducible.
                                    boolean alreadyAssigned = false;
                                    for (Module pr : prList) {
                                        Component otherprom = Module.getPromInPR(pr);
                                        Component otherrbs = Module.getRbsInPR(pr);
                                        CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                                        URI otherpromuri = getPRprom(othercc);
                                        URI otherrbsuri = getPRrbs(othercc);

                                        if (otherpromuri.equals(newpromuri)) {
                                            alreadyAssigned = true;
                                            if (rbs.isGeneric()) {
                                                nextNode.getAssignment().put(prom.getName(), newcc);
                                                nextNode.getAssignment().put(rbs.getName(), newcc);
                                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                    return nextNode;
                                                } else {
                                                    return null;
                                                }
                                            } else {
                                                if (newrbsuri.equals(currentrbsuri)) {
                                                    //The RBS did not change.
                                                    nextNode.getAssignment().put(prom.getName(), newcc);
                                                    nextNode.getAssignment().put(rbs.getName(), newcc);
                                                    if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                        return nextNode;
                                                    } else {
                                                        return null;
                                                    }
                                                } else {
                                                    //RBS is different.
                                                    boolean rbsAssigned = false;

                                                    for (Module subpr : prList) {
                                                        Component subrbs = Module.getRbsInPR(subpr);
                                                        Component subprom = Module.getPromInPR(subpr);
                                                        for (CandidateComponent subcc : subpr.getCandidates()) {
                                                            URI subpromuri = getPRprom(subcc);
                                                            URI subrbsuri = getPRrbs(subcc);
                                                            if (!subrbs.isGeneric()) {
                                                                if (subrbsuri.equals(newrbsuri)) {
                                                                    rbsAssigned = true;
                                                                    boolean alternateFound = false;
                                                                    for (CandidateComponent finalcc : subpr.getCandidates()) {
                                                                        URI finalpromuri = getPRprom(finalcc);
                                                                        URI finalrbsuri = getPRrbs(finalcc);
                                                                        if (finalpromuri.equals(subpromuri)) {
                                                                            if (finalrbsuri.equals(currentrbsuri)) {
                                                                                nextNode.getAssignment().put(subprom.getName(), finalcc);
                                                                                nextNode.getAssignment().put(subrbs.getName(), finalcc);
                                                                                nextNode.getAssignment().put(prom.getName(), newcc);
                                                                                nextNode.getAssignment().put(rbs.getName(), newcc);
                                                                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                                    return nextNode;
                                                                                } else {
                                                                                    return null;
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                    if (!alternateFound) {
                                                                        return null;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }

                                                    if (!rbsAssigned) {
                                                        nextNode.getAssignment().put(prom.getName(), newcc);
                                                        nextNode.getAssignment().put(rbs.getName(), newcc);
                                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                            return nextNode;
                                                        } else {
                                                            return null;
                                                        }
                                                    }
                                                }
                                            }

                                        }

                                    }

                                    if (!alreadyAssigned) {
                                        boolean replacedcdsToOut = false;
                                        Map<String, Module> genericoutcdsMap = new HashMap<>();
                                        Map<URI, Set<String>> outcountMap = new HashMap<>();
                                        for (Module cdsModule : cdsList) {
                                            Component cds = cdsModule.getComponents().get(0);
                                            if (cds.isCDS()) {
                                                CandidateComponent cdscc = current.getAssignment().get(cds.getName());
                                                if (lib.getOutputCDS().containsKey(cdscc.getCandidate().getComponentDefintion())) {
                                                    if (!outcountMap.containsKey(cdscc.getCandidate().getComponentDefintion())) {
                                                        outcountMap.put(cdscc.getCandidate().getComponentDefintion(), new HashSet<String>());
                                                    }
                                                    outcountMap.get(cdscc.getCandidate().getComponentDefintion()).add(cds.getName());
                                                    if (cds.isGeneric()) {
                                                        genericoutcdsMap.put(cds.getName(), cdsModule);
                                                    }
                                                }
                                            }
                                        }
                                        for (URI key : outcountMap.keySet()) {
                                            if (outcountMap.get(key).size() > 1) {
                                                for (String compname : outcountMap.get(key)) {
                                                    if (genericoutcdsMap.containsKey(compname)) {
                                                        Module m = genericoutcdsMap.get(compname);
                                                        for (CandidateComponent cdscc : m.getCandidates()) {
                                                            CDSComponent cdscomp = (CDSComponent) lib.getAllLibraryComponents().get(cdscc.getCandidate().getComponentDefintion());
                                                            URI cdsprot = cdscomp.getProtein();
                                                            PromoterComponent promcomp = lib.getAllInduciblePromoters().get(newpromuri);
                                                            boolean regulated = false;
                                                            for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                                                                if (tf instanceof ComplexComponent) {
                                                                    ComplexComponent complex = (ComplexComponent) tf;
                                                                    if (complex.getProtein().equals(cdsprot)) {
                                                                        regulated = true;
                                                                    }
                                                                } else {
                                                                    if (tf.getComponentDefintion().equals(cdsprot)) {
                                                                        regulated = true;
                                                                    }
                                                                }
                                                            }
                                                            if (regulated) {
                                                                replacedcdsToOut = true;
                                                                nextNode.getAssignment().put(compname, cdscc);
                                                                nextNode.getAssignment().put(prom.getName(), newcc);
                                                                nextNode.getAssignment().put(rbs.getName(), newcc);

                                                                if (rbs.isGeneric()) {
                                                                    if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                        return nextNode;
                                                                    } else {
                                                                        return null;
                                                                    }
                                                                } else {
                                                                    if (newrbsuri.equals(currentrbsuri)) {
                                                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                            return nextNode;
                                                                        } else {
                                                                            return null;
                                                                        }
                                                                    } else {
                                                                        boolean specificrbsAssigned = false;

                                                                        for (Module pr : prList) {
                                                                            Component otherprom = Module.getPromInPR(pr);
                                                                            Component otherrbs = Module.getRbsInPR(pr);
                                                                            if (!otherrbs.isGeneric()) {
                                                                                CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                                                                                URI otherpromuri = getPRprom(othercc);
                                                                                URI otherrbsuri = getPRrbs(othercc);
                                                                                if (otherrbsuri.equals(newrbsuri)) {
                                                                                    specificrbsAssigned = true;
                                                                                    boolean alternatefound = false;
                                                                                    for (CandidateComponent subcc : pr.getCandidates()) {
                                                                                        URI subpromuri = getPRprom(subcc);
                                                                                        URI subrbsuri = getPRrbs(subcc);
                                                                                        if (subpromuri.equals(otherpromuri)) {
                                                                                            if (subrbsuri.equals(currentrbsuri)) {
                                                                                                alternatefound = true;
                                                                                                nextNode.getAssignment().put(otherprom.getName(), subcc);
                                                                                                nextNode.getAssignment().put(otherrbs.getName(), subcc);
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    if (!alternatefound) {
                                                                                        return null;
                                                                                    }
                                                                                }
                                                                            }

                                                                        }

                                                                        if (!specificrbsAssigned) {
                                                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                                return nextNode;
                                                                            } else {
                                                                                return null;
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                            }

                                                        }

                                                    }
                                                }
                                            }
                                        }

                                        if (!replacedcdsToOut) {
                                            return null;
                                        }

                                    }

                                    //</editor-fold>    
                                }
                            } else {
                                //Current Promoter is an Inducible promoter.
                                if (constitutiveCandidate(newcc, lib)) {
                                    //<editor-fold defaultstate="collapsed" desc="Current Promoter is Generic. Current Candidate:Inducible. New Candidate:Constitutive.">
                                    //New Promoter is Constitutive
                                    boolean alreadyAssigned = false;
                                    for (Module pr : prList) {
                                        Component otherprom = Module.getPromInPR(pr);
                                        Component otherrbs = Module.getRbsInPR(pr);
                                        CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                                        URI otherpromuri = getPRprom(othercc);
                                        URI otherrbsuri = getPRprom(othercc);
                                        if (otherpromuri.equals(newpromuri)) {
                                            if (rbs.isGeneric() || newrbsuri.equals(currentrbsuri)) {
                                                nextNode.getAssignment().put(prom.getName(), newcc);
                                                nextNode.getAssignment().put(rbs.getName(), newcc);
                                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                    return nextNode;
                                                } else {
                                                    return null;
                                                }
                                            } else {
                                                alreadyAssigned = true;

                                                for (Module alternatepr : prList) {
                                                    Component alternateprom = Module.getPromInPR(alternatepr);
                                                    Component alternaterbs = Module.getRbsInPR(alternatepr);
                                                    URI alternatepromcurrenturi = current.getAssignment().get(alternateprom.getName()).getCandidate().getComponentDefintion();
                                                    boolean alternateExists = false;

                                                    for (CandidateComponent alternatecc : alternatepr.getCandidates()) {
                                                        URI alternatepromuri = getPRprom(alternatecc);
                                                        URI alternaterbsuri = getPRrbs(alternatecc);
                                                        if (alternaterbsuri.equals(currentrbsuri)) {
                                                            if (alternatepromuri.equals(alternatepromcurrenturi)) {
                                                                alternateExists = true;
                                                                nextNode.getAssignment().put(alternateprom.getName(), alternatecc);
                                                                nextNode.getAssignment().put(alternaterbs.getName(), alternatecc);
                                                            }
                                                        }
                                                    }

                                                    if (!alternateExists) {
                                                        return null;
                                                    }

                                                }

                                                nextNode.getAssignment().put(prom.getName(), newcc);
                                                nextNode.getAssignment().put(rbs.getName(), newcc);
                                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                    return nextNode;
                                                } else {
                                                    return null;
                                                }
                                            }
                                        }
                                    }

                                    if (!alreadyAssigned) {
                                        //Replace any CDS that interacts with this promoter with any outCDS. 
                                        Map<URI, CandidateComponent> outcdsmap = new HashMap<>();
                                        for (String key : current.getAssignment().keySet()) {
                                            CandidateComponent outcc = current.getAssignment().get(key);
                                            if (lib.getOutputCDS().containsKey(outcc.getCandidate().getComponentDefintion())) {
                                                outcdsmap.put(outcc.getCandidate().getComponentDefintion(), outcc);
                                            }
                                        }
                                        List<URI> outcdsurilist = new ArrayList<>(outcdsmap.keySet());
                                        PromoterComponent promcomp = lib.getAllPromoters().get(currentpromuri);

                                        for (Component c : module.getComponents()) {
                                            boolean regulated = false;
                                            if (c.isCDS()) {
                                                if (c.isGeneric()) {
                                                    URI cdsprot = ((CDSComponent) current.getAssignment().get(c.getName()).getCandidate()).getProtein();
                                                    for (LibraryComponent tf : promcomp.getTranscriptionFactors()) {
                                                        if (tf instanceof ComplexComponent) {
                                                            ComplexComponent complex = (ComplexComponent) tf;
                                                            if (complex.getProtein().equals(cdsprot)) {
                                                                regulated = true;
                                                            }
                                                        } else {
                                                            if (tf.getComponentDefintion().equals(cdsprot)) {
                                                                regulated = true;
                                                            }
                                                        }
                                                    }
                                                    if (regulated) {
                                                        nextNode.getAssignment().put(c.getName(), outcdsmap.get(outcdsurilist.get(Utilities.getRandom(0, outcdsurilist.size() - 1))));
                                                    }
                                                }
                                            }
                                        }

                                        nextNode.getAssignment().put(prom.getName(), newcc);
                                        nextNode.getAssignment().put(rbs.getName(), newcc);
                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                            return nextNode;
                                        } else {
                                            return null;
                                        }

                                    }

                                    //</editor-fold>
                                } else {

                                    //<editor-fold defaultstate="collapsed" desc="Current Promoter is Generic. Current Candidate:Inducible. New Candidate:Inducible.">
                                    //New Promoter is Constitutive
                                    PromoterComponent currentpromcomp = lib.getAllPromoters().get(currentpromuri);
                                    PromoterComponent newpromcomp = lib.getAllPromoters().get(newpromuri);

                                    boolean alreadyAssigned = false;
                                    boolean currentAlreadyAssigned = false;
                                    for (Component c : module.getComponents()) {
                                        if (c.isPromoter()) {
                                            CandidateComponent cc = current.getAssignment().get(c.getName());
                                            URI uri = getPRprom(cc);
                                            if (uri.equals(newpromuri)) {
                                                alreadyAssigned = true;
                                            }
                                            if (!c.getName().equals(prom.getName())) {
                                                if (uri.equals(currentpromuri)) {
                                                    currentAlreadyAssigned = true;
                                                }
                                            }
                                        }
                                    }

                                    if (alreadyAssigned) {
                                        if (!currentAlreadyAssigned) {
                                            //Swap out all instances of oldCDS for newCDS.
                                            for (Module m : cdsList) {
                                                Component cds = m.getComponents().get(0);
                                                CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                                if (regulated(currentpromcomp, cdscomp)) {
                                                    //Swap to new CDS
                                                    boolean alternativefound = false;
                                                    for (CandidateComponent cc : m.getCandidates()) {
                                                        CDSComponent alternatecdscomp = (CDSComponent) cc.getCandidate();
                                                        if (regulated(newpromcomp, alternatecdscomp)) {
                                                            alternativefound = true;
                                                            nextNode.getAssignment().put(cds.getName(), cc);
                                                            break;
                                                        }
                                                    }
                                                    if (!alternativefound) {
                                                        return null;
                                                    }
                                                }

                                            }
                                        }
                                        return swapRBS(current, nextNode, prom, rbs, currentrbsuri, newrbsuri, newcc, module, lib, stl, prList);
                                    } else {
                                        //newprom not already Assigned.
                                        if (currentAlreadyAssigned) {
                                            //Swap just 1 instance of oldCDS to new CDS
                                            boolean oneoutswapped = false;

                                            for (Module m : cdsList) {
                                                Component cds = m.getComponents().get(0);
                                                CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                                if (regulated(currentpromcomp, cdscomp)) {
                                                    //Swap with something else
                                                    for (CandidateComponent newcdscc : m.getCandidates()) {
                                                        CDSComponent newcdscomp = (CDSComponent) newcdscc.getCandidate();
                                                        if (regulated(newpromcomp, newcdscomp)) {
                                                            nextNode.getAssignment().put(cds.getName(), newcdscc);
                                                            oneoutswapped = true;
                                                            break;
                                                        }
                                                    }
                                                    if (oneoutswapped) {
                                                        break;
                                                    }

                                                }
                                            }

                                            if (!oneoutswapped) {
                                                return null;
                                            }

                                        } else {
                                            //Swap all instances of oldCDS to newCDS
                                            for (Module m : cdsList) {
                                                Component cds = m.getComponents().get(0);
                                                CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                                if (regulated(currentpromcomp, cdscomp)) {
                                                    //Swap with something else
                                                    boolean alternatefound = false;
                                                    for (CandidateComponent newcdscc : m.getCandidates()) {
                                                        CDSComponent newcdscomp = (CDSComponent) newcdscc.getCandidate();
                                                        if (regulated(newpromcomp, newcdscomp)) {
                                                            nextNode.getAssignment().put(cds.getName(), newcdscc);
                                                            alternatefound = true;
                                                            break;
                                                        }

                                                    }
                                                    if (!alternatefound) {
                                                        return null;
                                                    }
                                                }
                                            }

                                        }
                                        return swapRBS(current, nextNode, prom, rbs, currentrbsuri, newrbsuri, newcc, module, lib, stl, prList);
                                    }

                                    //</editor-fold>
                                }
                            }
                            //</editor-fold>

                        } else {
                            //Specific prom being swapped.
                            if (constitutiveCandidate(currentcc, lib)) {
                                //<editor-fold defaultstate="collapsed" desc="Current Promoter Specific Constitutive">
                                boolean alreadyAssigned = false;
                                for(Module pr:prList){
                                    Component otherprom = Module.getPromInPR(pr);
                                    Component otherrbs = Module.getRbsInPR(pr);
                                    CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                                    URI otherrbsuri = getPRrbs(othercc);
                                    URI otherpromuri = getPRprom(othercc);

                                    if((!otherrbs.isGeneric()) && (otherrbsuri.equals(newrbsuri))){
                                        
                                        boolean alternatefound = false;
                                        
                                        for(CandidateComponent alternatecc:pr.getCandidates()){
                                            URI alternatepromuri = getPRprom(alternatecc);
                                            URI alternaterbsuri = getPRrbs(alternatecc);
                                            if(alternatepromuri.equals(otherpromuri)){
                                                if(alternaterbsuri.equals(currentrbsuri)){
                                                    alternatefound = true;
                                                    nextNode.getAssignment().put(otherprom.getName(), alternatecc);
                                                    nextNode.getAssignment().put(otherrbs.getName(), alternatecc);
                                                    break;
                                                }
                                            }
                                            
                                        }
                                        
                                        if (!alternatefound) {
                                            return null;
                                        }
                                    }  
                                }
                                
                                return swapRBS(current, nextNode, prom, rbs, currentrbsuri, newrbsuri, newcc, module, lib, stl, prList);
                                //</editor-fold>
                            } else {
                                //<editor-fold defaultstate="collapsed" desc="Current Promoter Specific Inducible">

                                PromoterComponent currentpromcomp = lib.getAllPromoters().get(currentpromuri);
                                PromoterComponent newpromcomp = lib.getAllPromoters().get(newpromuri);

                                boolean alreadyAssigned = false;
                                boolean currentAlreadyAssigned = false;
                                for (Component c : module.getComponents()) {
                                    if (c.isPromoter()) {
                                        CandidateComponent cc = current.getAssignment().get(c.getName());
                                        URI uri = getPRprom(cc);
                                        if (uri.equals(newpromuri)) {
                                            alreadyAssigned = true;
                                        }
                                        if (!c.getName().equals(prom.getName())) {
                                            if (uri.equals(currentpromuri)) {
                                                currentAlreadyAssigned = true;
                                            }
                                        }
                                    }
                                }

                                if (alreadyAssigned) {
                                    if (!currentAlreadyAssigned) {
                                        //Swap out all instances of oldCDS for newCDS.
                                        for (Module m : cdsList) {
                                            Component cds = m.getComponents().get(0);
                                            CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                            if (regulated(currentpromcomp, cdscomp)) {
                                                //Swap to new CDS
                                                boolean alternativefound = false;
                                                for (CandidateComponent cc : m.getCandidates()) {
                                                    CDSComponent alternatecdscomp = (CDSComponent) cc.getCandidate();
                                                    if (regulated(newpromcomp, alternatecdscomp)) {
                                                        alternativefound = true;
                                                        nextNode.getAssignment().put(cds.getName(), cc);
                                                        break;
                                                    }
                                                }
                                                if (!alternativefound) {
                                                    return null;
                                                }
                                            }

                                        }
                                    }
                                    return swapRBS(current, nextNode, prom, rbs, currentrbsuri, newrbsuri, newcc, module, lib, stl, prList);
                                } else {
                                    //newprom not already Assigned.
                                    if (currentAlreadyAssigned) {
                                        //Swap just 1 instance of oldCDS to new CDS
                                        boolean oneoutswapped = false;

                                        for (Module m : cdsList) {
                                            Component cds = m.getComponents().get(0);
                                            CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                            if (regulated(currentpromcomp, cdscomp)) {
                                                //Swap with something else
                                                for (CandidateComponent newcdscc : m.getCandidates()) {
                                                    CDSComponent newcdscomp = (CDSComponent) newcdscc.getCandidate();
                                                    if (regulated(newpromcomp, newcdscomp)) {
                                                        nextNode.getAssignment().put(cds.getName(), newcdscc);
                                                        oneoutswapped = true;
                                                        break;
                                                    }
                                                }
                                                if (oneoutswapped) {
                                                    break;
                                                }

                                            }
                                        }

                                        if (!oneoutswapped) {
                                            return null;
                                        }

                                    } else {
                                        //Swap all instances of oldCDS to newCDS
                                        for (Module m : cdsList) {
                                            Component cds = m.getComponents().get(0);
                                            CDSComponent cdscomp = (CDSComponent) current.getAssignment().get(cds.getName()).getCandidate();
                                            if (regulated(currentpromcomp, cdscomp)) {
                                                //Swap with something else
                                                boolean alternatefound = false;
                                                for (CandidateComponent newcdscc : m.getCandidates()) {
                                                    CDSComponent newcdscomp = (CDSComponent) newcdscc.getCandidate();
                                                    if (regulated(newpromcomp, newcdscomp)) {
                                                        nextNode.getAssignment().put(cds.getName(), newcdscc);
                                                        alternatefound = true;
                                                        break;
                                                    }

                                                }
                                                if (!alternatefound) {
                                                    return null;
                                                }
                                            }
                                        }

                                    }
                                    return swapRBS(current, nextNode, prom, rbs, currentrbsuri, newrbsuri, newcc, module, lib, stl, prList);
                                }

                                //</editor-fold>
                            }
                        }
                    } else if (curr.isRBS()) {
                        Component prom = Module.getPromInPR(subset);
                        Component rbs = Module.getRbsInPR(subset);
                        CandidateComponent currentcc = current.getAssignment().get(prom.getName());
                        URI currentpromuri = getPRprom(currentcc);
                        URI currentrbsuri = getPRrbs(currentcc);
                        //System.out.println("Swapping an RBS");
                        List<CandidateComponent> othercandidates = new ArrayList<>(getOtherRBSCandidates(subset.getCandidates(), current.getAssignment().get(curr.getName())).values());
                        if (curr.isGeneric()) {
                            //<editor-fold defaultstate="collapsed" desc="Generic RBS">
                            if (othercandidates.isEmpty()) {
                                return null;
                            } else {
                                CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));
                                nextNode.getAssignment().put(prom.getName(), newcc);
                                nextNode.getAssignment().put(rbs.getName(), newcc);
                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                    return nextNode;
                                }
                            }
                            //</editor-fold>
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="Specific RBS">
                            if (othercandidates.isEmpty()) {
                                return null;
                            } else {
                                CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));

                                URI newpromuri = getPRprom(newcc);
                                URI newrbsuri = getPRrbs(newcc);

                                boolean specificAssigned = false;
                                boolean alreadyAssigned = false;
                                for (Module pr : prList) {
                                    Component otherprom = Module.getPromInPR(pr);
                                    Component otherrbs = Module.getRbsInPR(pr);

                                    CandidateComponent othercc = current.getAssignment().get(otherprom.getName());
                                    URI otherpromuri = getPRprom(othercc);
                                    URI otherrbsuri = getPRrbs(othercc);

                                    if (!otherrbs.isGeneric()) {
                                        if (otherrbsuri.equals(newrbsuri)) {
                                            if (!otherrbs.getName().equals(rbs.getName())) {

                                                //This check should be redundant.
                                                specificAssigned = true;

                                                for (CandidateComponent cc : pr.getCandidates()) {

                                                    Component othersubsetprom = Module.getPromInPR(pr);
                                                    Component othersubsetrbs = Module.getRbsInPR(pr);

                                                    URI othersubsetpromuri = getPRprom(othercc);
                                                    URI othersubsetrbsuri = getPRrbs(othercc);
                                                    if (othersubsetpromuri.equals(otherpromuri)) {
                                                        if (othersubsetrbsuri.equals(currentrbsuri)) {

                                                            alreadyAssigned = true;
                                                            nextNode.getAssignment().put(othersubsetprom.getName(), cc);
                                                            nextNode.getAssignment().put(othersubsetrbs.getName(), cc);

                                                            nextNode.getAssignment().put(prom.getName(), newcc);
                                                            nextNode.getAssignment().put(rbs.getName(), newcc);
                                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                return nextNode;
                                                            }

                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }

                                }

                                if (specificAssigned) {
                                    return null;
                                }

                                //This is where you perform a simple swap?
                                if (!alreadyAssigned) {
                                    nextNode.getAssignment().put(prom.getName(), newcc);
                                    nextNode.getAssignment().put(rbs.getName(), newcc);
                                    if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                        return nextNode;
                                    }
                                }

                            }
                            //</editor-fold>
                        }
                    }
                    break;
                case CDS:
                    if (curr.isGeneric()) {
                        //<editor-fold defaultstate="collapsed" desc="Generic CDS. This can be anything else.">
                        if (lib.getOutputCDS().containsKey(currlc.getComponentDefintion())) {
                            //This is an output CDS
                            if (outCount == 1) {
                                //if there are other outCDS options then use that. 
                                Map<URI, CandidateComponent> otheroptions = getOtherOutputCDSCandidates(subset.getCandidates(), currlc, lib);
                                if (!otheroptions.isEmpty()) {
                                    //There is another Output CDS
                                    List<CandidateComponent> otheroutcdscclist = new ArrayList<>(otheroptions.values());
                                    CandidateComponent newOutcds = otheroutcdscclist.get(Utilities.getRandom(0, otheroutcdscclist.size() - 1));
                                    for (Component c : module.getComponents()) {
                                        //Ensure that all components that are of type Output CDS now have the new FP CDS.
                                        if (current.getAssignment().get(c.getName()).getCandidate().getComponentDefintion().equals(currlc.getComponentDefintion())) {
                                            nextNode.getAssignment().put(c.getName(), newOutcds);
                                        }
                                    }
                                    if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {//You probably don't need to check if this node is valid. It should be valid by default.
                                        return nextNode;
                                    }
                                } else {
                                    for (Module cdsModule : cdsList) {
                                        Component othercdscomp = cdsModule.getComponents().get(0);
                                        LibraryComponent othercdslc = current.getAssignment().get(othercdscomp.getName()).getCandidate();
                                        if (othercdscomp.isCDS() && othercdscomp.isGeneric() && (!(othercdslc.getComponentDefintion().equals(currlc.getComponentDefintion())))) {
                                            nextNode.getAssignment().put(curr.getName(), current.getAssignment().get(othercdscomp.getName()));
                                            nextNode.getAssignment().put(othercdscomp.getName(), current.getAssignment().get(curr.getName()));
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {//You probably don't need to check if this node is valid. It should be valid by default.
                                                return nextNode;
                                            } else {
                                                return null;
                                            }
                                        }
                                    }
                                    return null;
                                }
                            } else {
                                CandidateComponent currentcc = current.getAssignment().get(curr.getName());
                                URI currentcdsuri = currentcc.getCandidate().getComponentDefintion();
                                Map<URI,CandidateComponent> alternatecdsmap = new HashMap<>();
                                Map<URI,CandidateComponent> alternateoutcdsmap = new HashMap<>();
                                boolean anotheroutexists = false;
                                for(Component c:module.getComponents()){
                                    if (c.isCDS()) {
                                        if (!c.getName().equals(curr.getName())) {
                                            CandidateComponent cc = current.getAssignment().get(c.getName());
                                            alternatecdsmap.put(cc.getCandidate().getComponentDefintion(), cc);
                                            if(lib.getOutputCDS().containsKey(cc.getCandidate().getComponentDefintion())){
                                                alternateoutcdsmap.put(cc.getCandidate().getComponentDefintion(), cc);
                                            }
                                            if(cc.getCandidate().getComponentDefintion().equals(currentcdsuri)){
                                                anotheroutexists = true;
                                            }
                                        }
                                    }
                                }
                                List<CandidateComponent> alternatecdslist = new ArrayList<>();
                                if(anotheroutexists){
                                    for(CandidateComponent cc:subset.getCandidates()){
                                        if(alternatecdsmap.containsKey(cc.getCandidate().getComponentDefintion())){
                                            alternatecdslist.add(cc);
                                        }
                                    }
                                } else {
                                    for(CandidateComponent cc:subset.getCandidates()){
                                        if(alternateoutcdsmap.containsKey(cc.getCandidate().getComponentDefintion())){
                                            alternatecdslist.add(cc);
                                        }
                                    }
                                }
                                nextNode.getAssignment().put(curr.getName(), alternatecdslist.get(Utilities.getRandom(0, alternatecdslist.size()-1)));
                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {//You probably don't need to check if this node is valid. It should be valid by default.
                                    return nextNode;
                                } else {
                                    return null;
                                }
                            }

                        } else {
                            //This is a connector CDS
                            System.out.println("Swapping a generic Connector CDS");
                            boolean othercompSpecific = false;
                            for (Module cdsModule : cdsList) {
                                Component c = cdsModule.getComponents().get(0);
                                if (!c.isGeneric() && (current.getAssignment().get(c.getName()).getCandidate().getComponentDefintion().equals(currlc.getComponentDefintion()))) {
                                    if (!c.getName().equals(curr.getName())) {
                                        othercompSpecific = true;
                                    }
                                }
                            }

                            if (othercompSpecific) {
                                //Another component with the same assignment is a specific component
                                List<CandidateComponent> otherCandidates = new ArrayList<>(getOtherCDSCandidates(subset.getCandidates(), currlc, lib).values());
                                CandidateComponent newcc = otherCandidates.get(Utilities.getRandom(0, otherCandidates.size() - 1));
                                boolean alreadyAssigned = false;
                                for (Module cdsModule : cdsList) {
                                    Component othercomp = cdsModule.getComponents().get(0);
                                    if (!othercomp.getName().equals(curr.getName())) {
                                        if (current.getAssignment().get(othercomp.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate().getComponentDefintion())) {
                                            //Already assigned.. 
                                            alreadyAssigned = true;
                                            nextNode.getAssignment().put(curr.getName(), newcc);
                                            nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                return nextNode;
                                            }
                                        }
                                    }
                                }
                                if (!alreadyAssigned) {
                                    return swapPromoter(module, current, nextNode, prList, curr, newcc, lib, stl);
                                }
                            } else {
                                //Free to choose any "other candidate"
                                List<CandidateComponent> otherCandidates = new ArrayList<>(getOtherCandidates(subset.getCandidates(), currlc).values());
                                CandidateComponent newcc = otherCandidates.get(Utilities.getRandom(0, otherCandidates.size() - 1));
                                if (lib.getOutputCDS().containsKey(newcc.getCandidate().getComponentDefintion())) {
                                    //New CDS is Output
                                    for (Module cdsModule : cdsList) {
                                        Component othercomp = cdsModule.getComponents().get(0);
                                        if (othercomp.isGeneric()) {
                                            if (!othercomp.getName().equals(curr.getName())) {
                                                //The nested if condition makes the current if statement redundant. 
                                                if (current.getAssignment().get(othercomp.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate())) {
                                                    //New candidate was already assigned elsewhere to another generic cds component. Hence swap them now. 
                                                    nextNode.getAssignment().put(curr.getName(), newcc);
                                                    nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                                    if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                        return nextNode;
                                                    }
                                                }
                                            }

                                        }
                                    }
                                    return null;
                                } else {
                                    //New CDS is a connector
                                    boolean alreadyAssigned = false;
                                    for (Component othercomp : module.getComponents()) {
                                        if (othercomp.isCDS()) {
                                            if (!othercomp.getName().equals(curr.getName())) {
                                                //This check should be redundant...
                                                if (current.getAssignment().get(othercomp.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate().getComponentDefintion())) {
                                                    //Already assigned.. 
                                                    alreadyAssigned = true;
                                                    LibraryComponent otherlc = current.getAssignment().get(othercomp.getName()).getCandidate();
                                                    if (othercomp.isGeneric()) {
                                                        //The other component is Generic
                                                        nextNode.getAssignment().put(curr.getName(), newcc);
                                                        nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                            return nextNode;
                                                        }
                                                    } else {
                                                        //The other component is Specific
                                                        if (sameTypeLibraryComponent(otherlc, newcc.getCandidate(), lib)) {
                                                            //They are both of the same type. Hence swapping is possible. 
                                                            nextNode.getAssignment().put(curr.getName(), newcc);
                                                            nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                                return nextNode;
                                                            }
                                                        } else {
                                                            return null;
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                    if (!alreadyAssigned) {
                                        //Find all promoters that are related and swap them too. 
                                        return swapPromoter(module, current, nextNode, prList, curr, newcc, lib, stl);
                                    }
                                }

                            }

                        }
                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="Specific CDS. In this case it has to have a specific role."> 
                        List<CandidateComponent> othercandidates = new ArrayList<>(getOtherCandidates(subset.getCandidates(), currlc).values());
                        CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));
                        boolean alreadyAssigned = false;
                        if (lib.getOutputCDS().containsKey(currlc.getComponentDefintion())) {
                            //This is an output CDS
                            for (Component othercomp : module.getComponents()) {
                                if (othercomp.isCDS()) {
                                    if (!othercomp.getName().equals(curr.getName())) {
                                        if (current.getAssignment().get(othercomp.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate().getComponentDefintion())) {
                                            //alreadyAssigned 
                                            alreadyAssigned = true;
                                            nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                            nextNode.getAssignment().put(curr.getName(), newcc);
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                return nextNode;
                                            }
                                        }
                                    }
                                }
                            }

                            if (!alreadyAssigned) {
                                for (Component c : module.getComponents()) {
                                    if (current.getAssignment().get(c.getName()).getCandidate().getComponentDefintion().equals(currlc.getComponentDefintion())) {
                                        nextNode.getAssignment().put(c.getName(), newcc);
                                    }
                                }
                                if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                    return nextNode;
                                }
                            }

                        } else {
                            //This is a connector CDS
                            for (Component othercomp : module.getComponents()) {
                                if (othercomp.isCDS()) {
                                    if (!othercomp.getName().equals(curr.getName())) {
                                        if (current.getAssignment().get(othercomp.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate().getComponentDefintion())) {
                                            //alreadyAssigned 
                                            alreadyAssigned = true;
                                            nextNode.getAssignment().put(othercomp.getName(), current.getAssignment().get(curr.getName()));
                                            nextNode.getAssignment().put(curr.getName(), newcc);
                                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                                return nextNode;
                                            }
                                        }
                                    }
                                }
                            }

                            if (!alreadyAssigned) {
                                return swapPromoter(module, current, nextNode, prList, curr, newcc, lib, stl);
                            }
                        }
                        //</editor-fold>
                    }
                    break;
                case TERMINATOR:
                    //System.out.println("Swapping Terminator");
                    if (curr.isGeneric()) {
                        //<editor-fold desc="Generic Terminator" defaultstate="collapsed">
                        List<CandidateComponent> othercandidates = new ArrayList<>(getOtherCandidates(subset.getCandidates(), currlc).values());
                        CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));
                        nextNode.getAssignment().put(curr.getName(), newcc);
                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                            return nextNode;
                        }
                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="Specific Terminator">
                        List<CandidateComponent> othercandidates = new ArrayList<>(getOtherCandidates(subset.getCandidates(), currlc).values());
                        CandidateComponent newcc = othercandidates.get(Utilities.getRandom(0, othercandidates.size() - 1));
                        boolean alreadyAssigned = false;
                        for (Component c : module.getComponents()) {
                            if (c.isTerminator()) {
                                if (!c.getName().equals(curr.getName())) {
                                    if (current.getAssignment().get(c.getName()).getCandidate().getComponentDefintion().equals(newcc.getCandidate().getComponentDefintion())) {
                                        alreadyAssigned = true;
                                        nextNode.getAssignment().put(curr.getName(), newcc);
                                        nextNode.getAssignment().put(c.getName(), current.getAssignment().get(curr.getName()));
                                        if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                            return nextNode;
                                        }
                                    }
                                }
                            }
                        }

                        if (!alreadyAssigned) {
                            nextNode.getAssignment().put(curr.getName(), newcc);
                            if (validAssignment(module, nextNode.getAssignment(), lib, stl)) {
                                return nextNode;
                            }
                        }
                        //</editor-fold>
                    }
                    break;
                case PROMOTER:
                    break;
                case RBS_CDS:
                    break;
            }

        } else {
            //<editor-fold desc="Change the Small Molecule Concentration.">
            //System.out.println("Swapping SM Conc.");
            int smIndex = pos - current.getComponents().size();
            String sm = current.getSmallMolecules().get(smIndex);
            double currentConc = current.getConcs().get(sm);

            SmallMoleculeComponent smc = lib.getSmallMolecules().get(current.getSmURImap().get(sm));

            if (smc.getValues().size() == 1) {
                return null;
            }

            double newConc = smc.getValues().get(Utilities.getRandom(0, (smc.getValues().size() - 1)));
            while (newConc == currentConc) {
                newConc = smc.getValues().get(Utilities.getRandom(0, (smc.getValues().size() - 1)));
            }

            nextNode.changeConc(sm, newConc);
            return nextNode;
            //</editor-fold>
        }

        return null;
    }

    private static boolean sameTypeLibraryComponent(LibraryComponent lc1, LibraryComponent lc2, Library lib) {
        if (lib.getOutputCDS().containsKey(lc1.getComponentDefintion()) && lib.getOutputCDS().containsKey(lc2.getComponentDefintion())) {
            return true;
        }
        if (lib.getActivatingCDS().containsKey(lc1.getComponentDefintion()) && lib.getActivatingCDS().containsKey(lc2.getComponentDefintion())) {
            return true;
        }
        if (lib.getRepressingCDS().containsKey(lc1.getComponentDefintion()) && lib.getRepressingCDS().containsKey(lc2.getComponentDefintion())) {
            return true;
        }

        return false;
    }

    

    private static Set<URI> getAssignmentURIs(Module m, Map<String, CandidateComponent> assignment) {
        Set<URI> uris = new HashSet<>();
        for (Component c : m.getComponents()) {
            CandidateComponent cc = assignment.get(c.getName());

            if (cc.getCandidate() instanceof CompositeComponent) {
                CompositeComponent composite = (CompositeComponent) cc.getCandidate();
                if (c.isPromoter()) {
                    uris.add(getPRprom(cc));
                } else if (c.isRBS()) {
                    if (composite.getType().equals(CompositeType.PR)) {
                        uris.add(getPRrbs(cc));
                    } else if (composite.getType().equals(CompositeType.RC)) {
                        uris.add(getRCrbs(cc));
                    }
                } else if (c.isCDS()) {
                    uris.add(getRCcds(cc));
                }
            } else {
                uris.add(cc.getCandidate().getComponentDefintion());
            }

        }

        return uris;
    }

    private static boolean clashes(Module module, Map<String, CandidateComponent> newAssignment, Map<String, CandidateComponent> currAssignment) {

        Set<String> newSpec = new HashSet<>();
        Set<String> currSpec = new HashSet<>();
        Set<String> prom = new HashSet<>();
        Set<String> rbs = new HashSet<>();
        Set<String> cds = new HashSet<>();
        Set<String> ter = new HashSet<>();

        for (Component c : module.getComponents()) {
            if (!c.isGeneric()) {
                if (newAssignment.containsKey(c.getName())) {
                    newSpec.add(c.getName());
                }
                if (currAssignment.containsKey(c.getName())) {
                    currSpec.add(c.getName());
                }
                if (c.isPromoter()) {
                    prom.add(c.getName());
                } else if (c.isRBS()) {
                    rbs.add(c.getName());
                } else if (c.isCDS()) {
                    cds.add(c.getName());
                } else if (c.isTerminator()) {
                    ter.add(c.getName());
                }
            }
        }

        for (String newComp : newSpec) {
            for (String currComp : currSpec) {
                if (!newComp.equals(currComp)) {
                    if (prom.contains(newComp) && prom.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if (newcc.getCandidate() instanceof CompositeComponent) {
                            if (getPRprom(newcc).equals(getPRprom(currcc))) {
                                return true;
                            }
                        } else {
                            if (newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())) {
                                return true;
                            }
                        }

                    } else if (rbs.contains(newComp) && rbs.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if (newcc.getCandidate() instanceof CompositeComponent) {

                            CompositeComponent newComposite = (CompositeComponent) newcc.getCandidate();
                            CompositeComponent currComposite = (CompositeComponent) newcc.getCandidate();

                            URI newRBS = null;
                            URI currRBS = null;

                            if (newComposite.getType().equals(CompositeType.PR)) {
                                newRBS = getPRrbs(newcc);
                            } else if (newComposite.getType().equals(CompositeType.RC)) {
                                newRBS = getRCrbs(newcc);
                            }

                            if (currComposite.getType().equals(CompositeType.PR)) {
                                currRBS = getPRrbs(currcc);
                            } else if (currComposite.getType().equals(CompositeType.RC)) {
                                currRBS = getRCrbs(currcc);
                            }

                            if (newRBS.equals(currRBS)) {
                                return true;
                            }

                        } else {
                            if (newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())) {
                                return true;
                            }
                        }
                    } else if (cds.contains(newComp) && cds.contains(currComp)) {
                        CandidateComponent newcc = newAssignment.get(newComp);
                        CandidateComponent currcc = currAssignment.get(currComp);
                        if (newcc.getCandidate() instanceof CompositeComponent) {
                            if (getRCcds(newcc).equals(getRCcds(currcc))) {
                                return true;
                            }
                        } else {
                            if (newcc.getCandidate().getComponentDefintion().equals(currcc.getCandidate().getComponentDefintion())) {
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

    public static double acceptanceProbability(double currentScore, double newScore, double temperature, int hammingDistance) {
        
        if(newScore == currentScore){
            return 0.5;
        }
        
        //System.out.println("Acceptance Probability Function");
        double deltaE = newScore - currentScore;
        //System.out.println("Current Score : " + currentScore);
        //System.out.println("New Score     : " + newScore);
        //System.out.println("Detla E       : " + deltaE);
        //System.out.println("Temperature   : " + temperature);
        //System.out.println("Difference    : " + hammingDistance);
        double exp = (-deltaE) / (temperature * hammingDistance);
        //System.out.println("Exp           : " + (Math.pow(Math.E, exp)));

        double prob = 1.0 / (1.0 + (Math.pow(Math.E, exp)));

        //System.out.println("Probability   : " + prob);
        //System.out.println("---------------------------------");
        return prob;

    }

    public void setCoolingRate(int candidateCount) {
        double runs = candidateCount * 0.05;
        System.out.println("Estimated Runs :: " + runs);
        coolingRate = (1 - (Math.pow((1 / temperature), (1 / runs))));
        //temperature = 1 /(Math.pow((1 -coolingRate), runs));
        System.out.println("Cooling Rate :: " + coolingRate);
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

}
