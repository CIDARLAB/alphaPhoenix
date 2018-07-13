/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import hyness.stl.TreeNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.Exhaustive;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.Test;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class CircuitTUTest3 {
    private static String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static String sampleCircuitsFP = tested_circuitsFP + "circuits" + Utilities.getSeparater();
    
    private static String three_tuFP = sampleCircuitsFP + "3tu" + Utilities.getSeparater();
    
    private static String three_tu_results = three_tuFP + "results" + Utilities.getSeparater();
    
    private static String three_tu_eug = three_tu_results + "tripleTU.eug";
    private static String three_tu_stl = three_tu_results + "stl.txt";
    
    private static int runCount = 20;
    
    @Test
    public void testExhaustiveAssignment() throws URISyntaxException, SBOLValidationException, SynBioHubException {
        
        int size = 8;
        
        List<Module> modules = MiniEugeneAdaptor.getStructures(three_tu_eug, size, "inverter");
        System.out.println("Number of modules : " + modules.size());
        TreeNode stl = STLAdaptor.getSTL(three_tu_stl);
        Args args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.STOCHASTIC, runCount, 0.99, 0.5, Args.Assignment.EXHAUSTIVE);
        args.setProjectFolder(three_tu_results);
        Exhaustive exhaustive = new Exhaustive();
        
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixParts/PhoenixParts_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T);
        
        List<Module> decomposed = new ArrayList<Module>();
        
        for(Module m:modules){
            decomposed.add(Controller.decompose(m, Args.Decomposition.PR_C_T));
        }
        
        exhaustive.solve(decomposed, lib, stl, args);
        
        Module m1 = decomposed.get(0);
        System.out.println("Number of Transcriptional Units : " + m1.getChildren().size());
        
        Module tu1 = m1.getChildren().get(0);
        Module pr1 = tu1.getChildren().get(0);
        System.out.println("PR candidate assignment : " + pr1.getCandidates().size());
        Module c1 = tu1.getChildren().get(1);
        System.out.println("C candidate assignment  : " + c1.getCandidates().size());
        Module t1 = tu1.getChildren().get(2);
        System.out.println("T candidate assignment  : " + t1.getCandidates().size());
        System.out.println("TU candidate assignment : " + tu1.getAssignments().size());
        
        Module tu2 = m1.getChildren().get(1);
        Module pr2 = tu1.getChildren().get(0);
        System.out.println("PR candidate assignment : " + pr2.getCandidates().size());
        Module c2 = tu1.getChildren().get(1);
        System.out.println("C candidate assignment  : " + c2.getCandidates().size());
        Module t2 = tu1.getChildren().get(2);
        System.out.println("T candidate assignment  : " + t2.getCandidates().size());
        System.out.println("TU candidate assignment : " + tu2.getAssignments().size());
    
        System.out.println("###############################");
        System.out.println("Final Number of assignments  :: " + m1.getAssignments().size());
    
    }
}
