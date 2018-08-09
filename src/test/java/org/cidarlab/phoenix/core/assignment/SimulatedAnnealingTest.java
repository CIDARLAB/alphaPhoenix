/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class SimulatedAnnealingTest {
    
    private Library sample;
    private Library lib;
    private Library localSample;
    private Library localLib;
    
    
    private Module m0; //1 TU All Generic
    private Module m1; //2 TU All Generic
    private Module m2; //2 TU 1 Generic/ 1 OutCDS 
    private Module m3; //2 TU 1 Generic/ 1 OutCDS (same as m2)
    private Module m4; //2 TU 2 OutCDS 
    
    private List<Module> pulse = new ArrayList<>();
    
    private TreeNode stl0;
    private TreeNode stl1;
    
    private Args args;
    
    
    
    public SimulatedAnnealingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SynBioHubException, URISyntaxException, SBOLValidationException, MalformedURLException, IOException, SBOLConversionException {
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixParts/PhoenixParts_collection/1";
        String sampleliburl = "https://synbiohub.programmingbiology.org/public/AlphaSample/AlphaSample_collection/1";
        
        
        String libfp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "ucf" + Utilities.getSeparater();
        String samplefp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "sampleUCF" + Utilities.getSeparater();
        
        String localLiburl = libfp + "sbol.xml";
        String localSampleurl = samplefp + "sbol.xml";
        
        localLib = new Library(SBOLReader.read(localLiburl), Args.Decomposition.PR_C_T,libfp);
        localSample = new Library(SBOLReader.read(localSampleurl),Args.Decomposition.PR_C_T,samplefp);
        
        SynBioHubFrontend shublib = new SynBioHubFrontend(synbiohuburl);
        URI ulib = new URI(phoenixliburl);
        SBOLDocument sbollib = shublib.getSBOL(ulib);
        lib = new Library(sbollib, Args.Decomposition.PR_C_T,libfp);
        
        SynBioHubFrontend shubsample = new SynBioHubFrontend(synbiohuburl);
        URI usample = new URI(sampleliburl);
        SBOLDocument sbolsample = shublib.getSBOL(usample);
        sample = new Library(sbolsample, Args.Decomposition.PR_C_T,samplefp);
        
        LinearPredicateLeaf out0g0 = new LinearPredicateLeaf(RelOperation.GE, "out0", 0);
        LinearPredicateLeaf out1g0 = new LinearPredicateLeaf(RelOperation.GE, "out1", 0);
        
        LinearPredicateLeaf out0l500 = new LinearPredicateLeaf(RelOperation.LT, "out0", 500);
        LinearPredicateLeaf out1l500 = new LinearPredicateLeaf(RelOperation.LT, "out1", 500);
        
        ConjunctionNode out0cn = new ConjunctionNode(out0g0,out0l500);
        ConjunctionNode out1cn = new ConjunctionNode(out1g0,out1l500);
        
        AlwaysNode out0an = new AlwaysNode(out0cn,0,500);
        AlwaysNode out1an = new AlwaysNode(out1cn,0,500);
        
        ConjunctionNode cn = new ConjunctionNode(out0an,out1an);
        
        args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.STOCHASTIC, 100, 0.5, 0.5, Args.Assignment.SIMULATED_ANNEALING);
        
        stl0 = out0an;
        stl1 = cn;
    
        
        Component c0 = new Component();
        Component c1 = new Component();
        Component c2 = new Component();
        Component c3 = new Component();
        c0.setName("p0");
        c0.setOrientation(Orientation.FORWARD);
        c0.setRole(Component.ComponentRole.GENERIC_PROMOTER);
        c1.setName("r0");
        c1.setOrientation(Orientation.FORWARD);
        c1.setRole(Component.ComponentRole.GENERIC_RBS);
        c2.setName("c0");
        c2.setOrientation(Orientation.FORWARD);
        c2.setRole(Component.ComponentRole.GENERIC_CDS);
        c3.setName("t0");
        c3.setOrientation(Orientation.FORWARD);
        c3.setRole(Component.ComponentRole.GENERIC_TERMINATOR);
        
        Module _m0 = new Module("m0");
        _m0.addComponent(c0);
        _m0.addComponent(c1);
        _m0.addComponent(c2);
        _m0.addComponent(c3);
        
        m0 = Controller.decompose(_m0, args.getDecomposition());
        
        
        Component c4 = new Component();
        Component c5 = new Component();
        Component c6 = new Component();
        Component c7 = new Component();
        c4.setName("p1");
        c4.setOrientation(Orientation.FORWARD);
        c4.setRole(Component.ComponentRole.GENERIC_PROMOTER);
        c5.setName("r1");
        c5.setOrientation(Orientation.FORWARD);
        c5.setRole(Component.ComponentRole.GENERIC_RBS);
        c6.setName("c1");
        c6.setOrientation(Orientation.FORWARD);
        c6.setRole(Component.ComponentRole.GENERIC_CDS);
        c7.setName("t1");
        c7.setOrientation(Orientation.FORWARD);
        c7.setRole(Component.ComponentRole.TERMINATOR);
        
        Component c8 = new Component();
        c8.setName("fc0");
        c8.setOrientation(Orientation.FORWARD);
        c8.setRole(Component.ComponentRole.CDS_FLUORESCENT);
        
        Component c9 = new Component();
        c9.setName("fc1");
        c9.setOrientation(Orientation.FORWARD);
        c9.setRole(Component.ComponentRole.CDS_FLUORESCENT);
        
        
        
        Module _m1 = new Module("m1");
        _m1.addComponent(c0);
        _m1.addComponent(c1);
        _m1.addComponent(c2);
        _m1.addComponent(c3);
        _m1.addComponent(c4);
        _m1.addComponent(c5);
        _m1.addComponent(c6);
        _m1.addComponent(c7);
        
        m1 = Controller.decompose(_m1, args.getDecomposition());
        
        
        Module _m2 = new Module("m2");
        _m2.addComponent(c0);
        _m2.addComponent(c1);
        _m2.addComponent(c2);
        _m2.addComponent(c3);
        _m2.addComponent(c4);
        _m2.addComponent(c5);
        _m2.addComponent(c8);
        _m2.addComponent(c7);
        
        m2 = Controller.decompose(_m2, args.getDecomposition());
        
        
        Module _m3 = new Module("m2");
        _m3.addComponent(c0);
        _m3.addComponent(c1);
        _m3.addComponent(c2);
        _m3.addComponent(c3);
        _m3.addComponent(c4);
        _m3.addComponent(c5);
        _m3.addComponent(c8);
        _m3.addComponent(c7);
        
        m3 = Controller.decompose(_m3, args.getDecomposition());
        
        
        
        Module _m4 = new Module("m3");
        _m4.addComponent(c0);
        _m4.addComponent(c1);
        _m4.addComponent(c8);
        _m4.addComponent(c3);
        _m4.addComponent(c4);
        _m4.addComponent(c5);
        _m4.addComponent(c9);
        _m4.addComponent(c7);
        
        m4 = Controller.decompose(_m4, args.getDecomposition());
        
        
        String examplesRootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
        String pulseRootFolder = examplesRootFolder + "pulse_circuit" + Utilities.getSeparater();
        String pulseEugFilepath = pulseRootFolder + "cascadeMin.eug";
        List<Module> _pulse = MiniEugeneAdaptor.getStructures(pulseEugFilepath, 16, null, "pulse");
        for(Module p:_pulse){
            pulse.add(Controller.decompose(p, args.getDecomposition()));
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of solve method, of class SimulatedAnnealing.
     */
    @Test
    public void testSolve() {
        
        System.out.println("########################################################");
        System.out.println("Module 0 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules0 = new ArrayList<>();
        modules0.add(m0);
        SimulatedAnnealing sa0 = new SimulatedAnnealing();
        sa0.solve(modules0, lib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 1 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules1 = new ArrayList<>();
        modules1.add(m1);
        SimulatedAnnealing sa1 = new SimulatedAnnealing();
        sa1.solve(modules1, lib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 2 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules2 = new ArrayList<>();
        modules2.add(m2);
        SimulatedAnnealing sa2 = new SimulatedAnnealing();
        sa2.solve(modules2, lib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 2 - Sample---------------------------------------");
        System.out.println("########################################################");
        List<Module> modules3 = new ArrayList<>();
        SimulatedAnnealing sa3 = new SimulatedAnnealing();
        modules3.add(m3);
        sa3.solve(modules3, sample, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 3 - Sample---------------------------------------");
        System.out.println("########################################################");
        List<Module> modules4 = new ArrayList<>();
        modules4.add(m4);
        SimulatedAnnealing sa4 = new SimulatedAnnealing();
        sa4.solve(modules4, sample, stl1, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        /*for(Component c:pulse.get(0).getComponents()){
            System.out.println(c.getName() +  ":" + c.getRole());
        }*/
        System.out.println("########################################################");
        System.out.println("Pulse - Library-----------------------------------------");
        System.out.println("########################################################");
        SimulatedAnnealing sa5 = new SimulatedAnnealing();
        sa5.solve(pulse, lib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        
        
    }
    
    /**
     * Test of local Solve method, of class SimulatedAnnealing.
     */
    //@Test
    public void testLocalSolve() {
        
        System.out.println("########################################################");
        System.out.println("Module 0 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules0 = new ArrayList<>();
        modules0.add(m0);
        SimulatedAnnealing sa0 = new SimulatedAnnealing();
        sa0.solve(modules0, localLib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 1 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules1 = new ArrayList<>();
        modules1.add(m1);
        SimulatedAnnealing sa1 = new SimulatedAnnealing();
        sa1.solve(modules1, localLib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 2 - Library--------------------------------------");
        System.out.println("########################################################");
        List<Module> modules2 = new ArrayList<>();
        modules2.add(m2);
        SimulatedAnnealing sa2 = new SimulatedAnnealing();
        sa2.solve(modules2, localLib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 2 - Sample---------------------------------------");
        System.out.println("########################################################");
        SimulatedAnnealing sa3 = new SimulatedAnnealing();
        sa3.solve(modules2, localSample, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        System.out.println("########################################################");
        System.out.println("Module 3 - Sample---------------------------------------");
        System.out.println("########################################################");
        List<Module> modules3 = new ArrayList<>();
        modules3.add(m4);
        SimulatedAnnealing sa4 = new SimulatedAnnealing();
        sa4.solve(modules3, localSample, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        System.out.println("");
        
        /*for(Component c:pulse.get(0).getComponents()){
            System.out.println(c.getName() +  ":" + c.getRole());
        }*/
        System.out.println("########################################################");
        System.out.println("Pulse - Library-----------------------------------------");
        System.out.println("########################################################");
        SimulatedAnnealing sa5 = new SimulatedAnnealing();
        sa5.solve(pulse, localLib, stl0, args);
        System.out.println("--------------------------------------------------------");
        
        
        
    }
    
    
}
