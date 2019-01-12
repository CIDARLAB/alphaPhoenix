/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples.simulatedannealing;

import hyness.stl.TreeNode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.core.assignment.SimulatedAnnealing;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class Circuit3tuTest {
    private static final String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static final String sampleCircuitsFP = tested_circuitsFP + "circuits" + Utilities.getSeparater();
    
    private static final String three_tuFP = sampleCircuitsFP + "3tu" + Utilities.getSeparater();
    
    private static final String three_tu_results = three_tuFP + "allRuns" + Utilities.getSeparater()+ "saTest" + Utilities.getSeparater();
    
    private static final String three_tu_eug = three_tuFP + "tripleTU.eug";
    private static final String three_tu_stl = three_tuFP + "stl.txt";
    
    private static final int runCount = 20;
    
    private static final int threads = 20;
    
    public void testSimulatedAnnealing() throws URISyntaxException, SBOLValidationException, SynBioHubException, MalformedURLException, InterruptedException {
        
        Utilities.makeDirectory(three_tu_results);
        
        int size = 12;
        
        SimulatedAnnealing sa;
        
        
        TreeNode stl = STLAdaptor.getSTL(three_tu_stl);
        
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixParts/PhoenixParts_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T,three_tu_results);
        
        List<Module> modules;
        List<Module> decomposed;
        
        
        
        for(int i=0;i<threads;i++){
            
            Args args = new Args(Args.Decomposition.PR_C_T, Args.Simulation.STOCHASTIC, runCount, 0.99, 0.5, Args.Assignment.SIMULATED_ANNEALING);
        
            String iterationfp = three_tu_results + "iteration" + i + Utilities.getSeparater();
            Utilities.makeDirectory(iterationfp);
            args.setProjectFolder(iterationfp);
            
            modules = MiniEugeneAdaptor.getStructures(three_tu_eug, size, "threetu");
            decomposed = new ArrayList<>();
            for (Module m : modules) {
                decomposed.add(Controller.decompose(m, Args.Decomposition.PR_C_T));
            }
            
            sa = new SimulatedAnnealing();
            sa.solve(decomposed, lib, stl, args);
        }
        
    }
    
    public static void main(String[] args) throws URISyntaxException, SBOLValidationException, SynBioHubException, MalformedURLException, InterruptedException {
        Circuit3tuTest circ = new Circuit3tuTest();
        circ.testSimulatedAnnealing();
    }
}