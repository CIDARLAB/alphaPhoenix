/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.adaptors.IBioSimAdaptor;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.adaptors.SynbiohubAdaptor;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.library.Library;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.Model;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.Participation;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class PhoenixProjectTest {
    
    public PhoenixProjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //@Test
    public void testCreateProject(){
        for(int i=0;i<10;i++){
            PhoenixProject proj = new PhoenixProject();
            System.out.println(proj.getJobId());
            System.out.println(proj.getJobFolder());
            System.out.println(proj.getResultsFolder());
        }
    }
    
    //@Test
    public void testCreateEmptySBOL(){
        String basefp = Utilities.getResourcesFilepath() + "example" + Utilities.getSeparater();
        SBOLDocument sbol = new SBOLDocument();
        try {
            sbol.write(basefp + "empty.xml");
        } catch (IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //@Test
    public void testGetSBOLfromSynbiohub(){
        String synbiohuburl = "https://synbiohub.cidarlab.org";
        String phoenixliburl = "https://synbiohub.cidarlab.org/public/AlphaPhoenix/AlphaPhoenix_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        try {
            URI u = new URI(phoenixliburl);
            SBOLDocument sbol = shub.getSBOL(u);
            Library lib = new Library(sbol);
            System.out.println("Lib Constitutive Proms ::" + lib.getConstitutivePromoters().size());
        } catch (SynBioHubException | URISyntaxException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testPhoenix(){
        try {
            String synbiohuburl = "https://synbiohub.cidarlab.org";
            String phoenixliburl = "https://synbiohub.cidarlab.org/public/AlphaPhoenix/AlphaPhoenix_collection/1";
            String stlfp = Utilities.getResourcesFilepath() + "stl" + Utilities.getSeparater() + "inv.txt";
            TreeNode stl = STLAdaptor.getSTL(stlfp);
            SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
            PhoenixProject proj = new PhoenixProject();
            URI u = new URI(phoenixliburl);
            SBOLDocument sbol = shub.getSBOL(u);
            Library lib = new Library(sbol);
            double bestval = Double.MIN_VALUE;
            int bestindex = 0;
            String eug = Utilities.getResourcesFilepath() + "miniEugeneFiles" + Utilities.getSeparater() + "inverterCP.eug";
            int size = 8;
            List<Module> modules = MiniEugeneAdaptor.getStructures(eug, size, "inverter");
            
            int index = 0;
            Module test = Controller.decompose(PhoenixMode.MM, modules.get(index));
            List<Map<String,CandidateComponent>> assignments = Controller.assign(test, lib, sbol);
            for (int i = 0; i < assignments.size(); i++) {
                Map<String, CandidateComponent> assignment = assignments.get(i);
                System.out.print(i + ":");
                for (Component c : test.getComponents()) {
                    System.out.print(assignment.get(c.getName()).getCandidate().getDisplayId() + ";");
                }
                System.out.println("");
            

                Controller.assignLeafModels(PhoenixMode.MM, test, proj.getJobId(), sbol, assignment);
                Controller.composeModels(PhoenixMode.MM, test, proj.getJobId(), assignment);

                String model = Utilities.getResultsFilepath() + proj.getJobId() + Utilities.getSeparater() + "results" + Utilities.getSeparater();
                Utilities.makeDirectory(model);
                String deterministic = model + "deterministic" + Utilities.getSeparater();
                Utilities.makeDirectory(deterministic);
                String assignmentfp = deterministic + i + Utilities.getSeparater();
                Utilities.makeDirectory(assignmentfp);
                String modelFile = assignmentfp + "model.xml";
                SBMLWriter writer = new SBMLWriter();
                writer.write(test.getModel().getSbml(), modelFile);
                IBioSimAdaptor.simulateODE(modelFile, assignmentfp, 100, 1, 1);
                String tsdfp = assignmentfp + "run-1.csv";
                double robval = STLAdaptor.getRobustness(stl,tsdfp,assignmentfp);
                if(robval > bestval){
                    bestval = robval;
                    bestindex = i;
                }
                System.out.println("----------------------------------------------------");
            
            }
            
            System.out.println("##################################################################");
            System.out.println("Best Result is at " + bestindex +  " with robustness = " + bestval);
            
            
        } catch (URISyntaxException | SynBioHubException | XMLStreamException | FileNotFoundException | SBMLException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    //@Test
    public void testAssignments(){
        String synbiohuburl = "https://synbiohub.cidarlab.org";
        String phoenixliburl = "https://synbiohub.cidarlab.org/public/AlphaPhoenix/AlphaPhoenix_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        try {
            URI u = new URI(phoenixliburl);
            SBOLDocument sbol = shub.getSBOL(u);
            Library lib = new Library(sbol);
            
            System.out.println("\n");
            System.out.println("############################################");
            System.out.println("Library Details ");
            System.out.println("Constitutive Promoters       : " + lib.getConstitutivePromoters().size());
            System.out.println("Activatible Promoters        : " + lib.getActivatiblePromoters().size());
            System.out.println("Repressible Promoters        : " + lib.getRepressiblePromoters().size());
            System.out.println("Activatible Input Promoters  : " + lib.getInputActPromoters().size());
            System.out.println("Repressible Input Promoters  : " + lib.getInputRepPromoters().size());
            System.out.println("RBS                          : " + lib.getRbs().size());
            System.out.println("Output CDS                   : " + lib.getOutputCDS().size());
            System.out.println("Activators                   : " + lib.getActivatorCDS().size());
            System.out.println("Repressors                   : " + lib.getRepressorCDS().size());
            System.out.println("Terminators                  : " + lib.getTerminators().size());
            System.out.println("Proteins                     : " + lib.getProteins().size());
            System.out.println("############################################\n");
            
            String eug = Utilities.getResourcesFilepath() + "miniEugeneFiles" + Utilities.getSeparater() + "inverterCP.eug";
            int size = 8;
            List<Module> modules = MiniEugeneAdaptor.getStructures(eug, size, "inverter");

            int testindx = 0;
            for (int i = 0; i < modules.size(); i++) {
                Module test = Controller.decompose(PhoenixMode.MM, modules.get(i));
                Controller.assignLeafCandidates(test, lib);
                Module tu1 = test.getChildren().get(0);

                Module prom = tu1.getChildren().get(0);
                Module cds = tu1.getChildren().get(1);
                System.out.println(prom.getRole().toString() + " :: " + prom.getComponentString());
                Controller.assignPromCandidates(prom, lib, sbol, prom.getOrientation());
                Controller.assignCDSCandidates(cds);
                Controller.assignTUCandidates(tu1, lib, sbol, tu1.getOrientation());
                List<Map<String, CandidateComponent>> finalAssignments = Controller.assignCircuitCandidates(test, lib, sbol);

                System.out.println("Promoter Assignment :");
                for (List<CandidateComponent> assignment : prom.getAssignments()) {
                    for (CandidateComponent cc : assignment) {
                        System.out.print(cc.getCandidate().getDisplayId() + ";");
                    }
                    System.out.println("");
                }
                System.out.println("\n");

                System.out.println("CDS Assignment :");
                for (List<CandidateComponent> assignment : cds.getAssignments()) {
                    for (CandidateComponent cc : assignment) {
                        System.out.print(cc.getCandidate().getDisplayId() + ";");
                    }
                    System.out.println("");
                }
                System.out.println("\n");

                System.out.println(tu1.getRole().toString() + " :: " + tu1.getComponentString());
                System.out.println("TU Assignment :");
                for (List<CandidateComponent> assignment : tu1.getAssignments()) {
                    for (CandidateComponent cc : assignment) {
                        System.out.print(cc.getCandidate().getDisplayId() + ";");
                    }
                    System.out.println("");
                }
                

                System.out.println("############################################");
                System.out.println("Module Tree Structure :: ");
                test.printTree();
                System.out.println("############################################\n");

                System.out.println("############################################");
                System.out.println("CIRCUIT :: " + test.getComponentString());
                for (Map<String, CandidateComponent> assignment : finalAssignments) {
                    for (Component c : test.getComponents()) {
                        System.out.print(assignment.get(c.getName()).getCandidate().getDisplayId() + ";");
                    }
                    System.out.println("");
                }
            }
            
        } catch (SynBioHubException | URISyntaxException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //@Test
    public void testGetSBMLModel(){
        String url = "https://synbiohub.cidarlab.org/public/PhoenixModels/attachment_00009Oz51rh38Z8lz1NTKy/1/download";
        String fp = Utilities.getResultsFilepath();
        try {
            URL downloadlurl = new URL(url);           
            SBMLDocument sbml = SynbiohubAdaptor.getModel(downloadlurl,fp);
            
            System.out.println("End of Test!");
        } catch (IOException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
