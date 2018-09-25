/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.Exhaustive;
import org.cidarlab.phoenix.dom.Module;
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
public class PulseCircuitTest {
    private static final String rootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
    private static final String pulsefp = rootFolder + "pulse_circuit" + Utilities.getSeparater();
    //private static String eugfp = pulsefp + "pulseCircuit.eug";
    private static String eugfp = pulsefp + "pulseCircuitSpecific.eug";
    private static String stlfp = pulsefp + "stl.txt";
    private static String libfp = pulsefp + "lib.json";
    private static String resultsfp = pulsefp + "allRuns" + Utilities.getSeparater();
    
    
    
    @Test
    public void testPulseEugene(){
        int eugCircSize = 16;
        List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, eugCircSize, "pulse");
        Module module = modules.get(0);
        
        assertEquals(modules.size(),1);
        assertEquals(module.getComponents().size(),16);
        
        System.out.println("Components :");
        System.out.println(module.getComponentString());
        
    }
    
    
    
    public void testPulseDeterministic() throws URISyntaxException, SynBioHubException, XMLStreamException, IOException, FileNotFoundException, MalformedURLException, TLIException, InterruptedException, SBOLValidationException{
        
        int runCount = 100;
        
        int size = 16;
        
        Utilities.makeDirectory(resultsfp);
        
        List<Module> modules = MiniEugeneAdaptor.getStructures(eugfp, size, "pulse");
        TreeNode stl = STLAdaptor.getSTL(stlfp);
        Args args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.DETERMINISTIC, runCount, 0, 0, Args.Assignment.EXHAUSTIVE);
        args.setProjectFolder(resultsfp);
        Exhaustive exhaustive = new Exhaustive();
        
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixReduced/PhoenixReduced_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T,resultsfp);
        
        List<Module> decomposed = new ArrayList<Module>();
        
        for(Module m:modules){
            decomposed.add(Controller.decompose(m, Args.Decomposition.PR_C_T));
        }
        exhaustive.solve(decomposed, lib, stl, args);
    }
    
    public static void main(String[] args) throws URISyntaxException, SynBioHubException, XMLStreamException, IOException, FileNotFoundException, MalformedURLException, TLIException, InterruptedException, SBOLValidationException {
        PulseCircuitTest pct = new PulseCircuitTest();
        pct.testPulseDeterministic();        
    }
    
    
    
    /*
    @Test
    public void testCascadeMinDeterministic() throws URISyntaxException{
        String eugfp = eugfp;
        int eugCircSize = 16;
        Integer eugNumSolutions = 10;
        String stlfp = stlfp;
        String libraryfp = libfp;
        Args.Simulation simulation = Args.Simulation.DETERMINISTIC;
        Args.Decomposition decomposition = Args.Decomposition.PR_C_T;
        int runCount = 100;
        double confidence = 0.0;
        double threshold = 0.0;
        Map<String,Double> inputMap = new HashMap<String,Double>(); 
        boolean plot = true;
        
        PhoenixProject newProj = new PhoenixProject( eugfp,  eugCircSize,  eugNumSolutions,  stlfp, libraryfp, simulation, decomposition, runCount, confidence, threshold,  inputMap, plot);
        
    }
    */
    
    
    /*
    
    @Test
    public void testCascadeMinStochastic() throws URISyntaxException{
        String eugfp = eugfp;
        int eugCircSize = 16;
        Integer eugNumSolutions = 10;
        String stlfp = stlfp;
        String libraryfp = libfp;
        Args.Simulation simulation = Args.Simulation.STOCHASTIC;
        Args.Decomposition decomposition = Args.Decomposition.PR_C_T;
        
        int runCount = 100;
        double confidence = 0.5;
        double threshold = 0.5;
        Map<String,Double> inputMap = new HashMap<String,Double>(); 
        boolean plot = true;
        
        PhoenixProject newProj = new PhoenixProject( eugfp,  eugCircSize,  eugNumSolutions,  stlfp, libraryfp, simulation, decomposition, runCount, confidence, threshold,  inputMap, plot);
        
    }
    */
    
}
