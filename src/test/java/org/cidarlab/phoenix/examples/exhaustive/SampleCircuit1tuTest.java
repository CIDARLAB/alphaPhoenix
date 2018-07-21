/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples.exhaustive;

import hyness.stl.TreeNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.Exhaustive;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Module.ModuleRole;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class SampleCircuit1tuTest {

    private static String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static String sampleCircuitsFP = tested_circuitsFP + "sample_circuits" + Utilities.getSeparater();
    
    private static String one_tuFP = sampleCircuitsFP + "1tu" + Utilities.getSeparater();
    private static String two_tuFP = sampleCircuitsFP + "2tu" + Utilities.getSeparater();
    private static String three_tuFP = sampleCircuitsFP + "3tu" + Utilities.getSeparater();
    
    private static String one_tu_results = one_tuFP + "results" + Utilities.getSeparater();
    
    private static String one_tu_eug = one_tuFP + "singleTU.eug";
    private static String one_tu_stl = one_tuFP + "stl.txt";
    
    @Test
    public void testEugeneScript() {
        
        int size = 4;
        List<Module> modules = MiniEugeneAdaptor.getStructures(one_tu_eug, size, "inverter");
        assertEquals(modules.size(),1);
        Module module = modules.get(0);
        
        assertEquals(module.getComponents().get(0).getOrientation(),Orientation.FORWARD);
        assertEquals(module.getComponents().get(1).getOrientation(),Orientation.FORWARD);
        assertEquals(module.getComponents().get(2).getOrientation(),Orientation.FORWARD);
        assertEquals(module.getComponents().get(3).getOrientation(),Orientation.FORWARD);
        
        assertEquals(module.getComponents().get(0).getRole(),ComponentRole.GENERIC_PROMOTER);
        assertEquals(module.getComponents().get(1).getRole(),ComponentRole.GENERIC_RBS);
        assertEquals(module.getComponents().get(2).getRole(),ComponentRole.GENERIC_CDS);
        assertEquals(module.getComponents().get(3).getRole(),ComponentRole.GENERIC_TERMINATOR);
        
        assertEquals(module.getComponents().size(),4);
        
        assertEquals(module.getComponents().get(0).getName(),"p0");
        assertEquals(module.getComponents().get(1).getName(),"r0");
        assertEquals(module.getComponents().get(2).getName(),"c0");
        assertEquals(module.getComponents().get(3).getName(),"t0");
        
    }
    
    
    @Test
    public void testDecomposeTest(){
        int size = 4;
        List<Module> modules = MiniEugeneAdaptor.getStructures(one_tu_eug, size, "inverter");
        Module module = modules.get(0);
        
        Module decomposed = Controller.decompose(module, Args.Decomposition.PR_C_T);
        assertEquals(decomposed.getChildren().size(),1);
        assertEquals(decomposed.getRole(), ModuleRole.HIGHER_FUNCTION);
        
        Module tu = decomposed.getChildren().get(0);
        assertEquals(tu.getChildren().size(),3);
        assertEquals(tu.getRole(),ModuleRole.TRANSCRIPTIONAL_UNIT);
        
        Module pr = tu.getChildren().get(0);
        Module c = tu.getChildren().get(1);
        Module t = tu.getChildren().get(2);
        
        assertEquals(pr.getRole(), ModuleRole.PROMOTER_RBS);
        assertEquals(c.getRole(),ModuleRole.CDS);
        assertEquals(t.getRole(),ModuleRole.TERMINATOR);
        
        assertEquals(pr.getComponents().size(),2);
        assertEquals(c.getComponents().size(),1);
        assertEquals(t.getComponents().size(),1);
        
        assertEquals(pr.getComponents().get(0).getRole(), ComponentRole.GENERIC_PROMOTER);
        assertEquals(pr.getComponents().get(1).getRole(), ComponentRole.GENERIC_RBS);
        assertEquals(c.getComponents().get(0).getRole(), ComponentRole.GENERIC_CDS);
        assertEquals(t.getComponents().get(0).getRole(), ComponentRole.GENERIC_TERMINATOR);
        
    }
    
    @Test
    public void testExhaustiveAssignment() throws URISyntaxException, SBOLValidationException, SynBioHubException {
        
        int size = 4;
        List<Module> modules = MiniEugeneAdaptor.getStructures(one_tu_eug, size, "inverter");
        TreeNode stl = STLAdaptor.getSTL(one_tu_stl);
        Args args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.STOCHASTIC, 100, 0.99, 0.5, Args.Assignment.EXHAUSTIVE);
        args.setProjectFolder(one_tu_results);
        Exhaustive exhaustive = new Exhaustive();
        
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/AlphaSample/AlphaSample_collection/1";
        
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
        Module tu = m1.getChildren().get(0);
        
        Module pr = tu.getChildren().get(0);
        System.out.println("PR candidate assignment : " + pr.getCandidates().size());
        Module c = tu.getChildren().get(1);
        System.out.println("C candidate assignment  : " + c.getCandidates().size());
        Module t = tu.getChildren().get(2);
        System.out.println("T candidate assignment  : " + t.getCandidates().size());
        
        System.out.println("TU candidate assignment : " + tu.getAssignments().size());
        System.out.println("###############################");
        System.out.println("Final Number of assignments  :: " + m1.getAssignments().size());
        
    }
    
    
}
