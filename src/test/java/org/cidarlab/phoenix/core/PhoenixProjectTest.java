/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
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
        String synbiohuburl = "https://synbiohub.cidarlab.org";
        String phoenixliburl = "https://synbiohub.cidarlab.org/public/AlphaPhoenix/AlphaPhoenix_collection/1";
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        try {
            URI u = new URI(phoenixliburl);
            SBOLDocument sbol = shub.getSBOL(u);
            Library lib = new Library(sbol);
            
            String eug = Utilities.getResourcesFilepath() + "miniEugeneFiles" + Utilities.getSeparater() + "inverterCP.eug";
            int size = 8;
            List<Module> modules = MiniEugeneAdaptor.getStructures(eug, size, "inverter");
            Module test = Controller.decompose(PhoenixMode.MM, modules.get(1));
            Controller.assignLeafCandidates(test, lib, new HashMap<String,Component>());
            Module tu1 = test.getChildren().get(0);
            
            
            Module prom = tu1.getChildren().get(0);
            Module cds = tu1.getChildren().get(1);
            System.out.println(prom.getRole().toString() + " :: " + prom.getComponentString() );
            Controller.assignPromCandidates(prom, lib, sbol, prom.getOrientation());
            Controller.assignCDSCandidates(cds);
            Controller.assignTUCandidates(tu1, lib, sbol, tu1.getOrientation());
            System.out.println("Promoter Assignment :");
            for(List<CandidateComponent> assignment: prom.getAssignments()){
                for(CandidateComponent cc:assignment){
                    System.out.print(cc.getCandidate().getDisplayId() + ";");
                }
                System.out.println("");
            }
            System.out.println("\n");
            
            System.out.println("CDS Assignment :");
            for(List<CandidateComponent> assignment: cds.getAssignments()){
                for(CandidateComponent cc:assignment){
                    System.out.print(cc.getCandidate().getDisplayId() + ";");
                }
                System.out.println("");
            }
            System.out.println("\n");
            
            System.out.println(tu1.getRole().toString() + " :: " + tu1.getComponentString() );
            System.out.println("TU Assignment :");
            for(List<CandidateComponent> assignment: tu1.getAssignments()){
                for(CandidateComponent cc:assignment){
                    System.out.print(cc.getCandidate().getDisplayId() + ";");
                }
                System.out.println("");
            }
            System.out.println("\n");
            /*
            List<List<CandidateComponent>> assignments = Controller.getTUCandidates(tu1, lib, sbol, tu1.getOrientation());
            System.out.println(tu1.getRole().toString() + " :: " + tu1.getComponentString());
            System.out.println("TU Assignment:");
            for(List<CandidateComponent> assignment: assignments){
                for(CandidateComponent cc:assignment){
                    System.out.print(cc.getCandidate().getDisplayId() + ";");
                }
                System.out.println("");
            }*/
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
    
    //@Test
    public void testCreateSampleLib() {
        
        String so = "http://identifiers.org/so/";
        String sbo = "http://identifiers.org/biomodels.sbo/";
        String baseurl = "http://www.phoenix.org/sample/";
        String version = "0.1";
        String induciblePromSO = so + "SO:0002051";
        String constitutivePromSO = so + "SO:0002050";
        String rbsSO = so + "SO:0000139";
        String cdsSO = so + "SO:0000316";
        String terSO = so + "SO:0000141";
        
        String productionSBO = sbo + "SBO:0000589";
        String templateSBO = sbo + "SBO:0000645";
        String productSBO = sbo + "SBO:0000011";
        
        String inhibitionSO = sbo + "SBO:0000169";
        String inhibitorSO = sbo + "SBO:0000020";
        String inhibitedSO = sbo + "SBO:0000642";
        
        String stimulationSO = sbo + "SBO:0000170";
        String stimulatorSO = sbo + "SBO:0000459";
        String stimulatedSO = sbo + "SBO:0000643";
        
        
        String basefp = Utilities.getResourcesFilepath() + "example" + Utilities.getSeparater();
        
        try {
            URI dnaRegionURI = new URI("http://www.biopax.org/release/biopax-level3.owl#DnaRegion");
            URI proteinURI = new URI("http://www.biopax.org/release/biopax-level3.owl#Protein");
            URI productionURI = new URI(productionSBO);
            URI templateURI = new URI(templateSBO);
            URI productURI = new URI(productSBO);
            
            URI inhibitionURI = new URI(inhibitionSO);
            URI inhibitorURI = new URI(inhibitorSO);
            URI inhibitedURI = new URI(inhibitedSO);
            
            URI stimulationURI = new URI(stimulationSO);
            URI stimulatorURI = new URI(stimulatorSO);
            URI stimulatedURI = new URI(stimulatedSO);
            
            URI sbmlURI = new URI("http://identifiers.org/edam/format_2585");
            URI frameworkURI = new URI("http://www.ebi.ac.uk/sbo/main/SBO:0000062");
            
            //<editor-fold desc="Create a library">
            
            //Component Definition for 2 Constitutive promoters (Choose 1)
            SBOLDocument sbol = new SBOLDocument();
            
            ComponentDefinition cp1 = sbol.createComponentDefinition(baseurl,"cp1", version, dnaRegionURI);
            cp1.addRole(new URI(constitutivePromSO));
            cp1.setName("Constitutive Promoter 1");
            
            ComponentDefinition cp2 = sbol.createComponentDefinition(baseurl,"cp2", version, dnaRegionURI);
            cp2.addRole(new URI(constitutivePromSO));
            cp2.setName("Constitutive Promoter 2");
            
            //Component Definition for 2 RBS (Choose both). 
            ComponentDefinition rbs1 = sbol.createComponentDefinition(baseurl,"rbs1", version, dnaRegionURI);
            rbs1.addRole(new URI(rbsSO));
            rbs1.setName("RBS 1");
            
            ComponentDefinition rbs2 = sbol.createComponentDefinition(baseurl,"rbs2", version, dnaRegionURI);
            rbs2.addRole(new URI(rbsSO));
            rbs2.setName("RBS 2");
            
            //Component Definition for 2 CDS/Rep Prom (Choose 1) + 1 CDS/Act Prom (Choose 0) + prots
            ComponentDefinition tetR = sbol.createComponentDefinition(baseurl,"tetR", version, dnaRegionURI);
            tetR.addRole(new URI(cdsSO));
            tetR.setName("TetR");
            
            ComponentDefinition tetProt = sbol.createComponentDefinition(baseurl,"tetRProtein", version, proteinURI);
            tetProt.setName("TetR Protein");
            
            ComponentDefinition pTet = sbol.createComponentDefinition(baseurl,"pTet", version, dnaRegionURI);
            pTet.addRole(new URI(induciblePromSO));
            pTet.setName("pTet");
           
            ComponentDefinition srpR = sbol.createComponentDefinition(baseurl,"srpR", version, dnaRegionURI);
            srpR.addRole(new URI(cdsSO));
            srpR.setName("SrpR");
            
            ComponentDefinition pSrpR = sbol.createComponentDefinition(baseurl,"pSrpR", version, dnaRegionURI);
            pSrpR.addRole(new URI(induciblePromSO));
            pSrpR.setName("pSrpR");
            
            ComponentDefinition srprProt = sbol.createComponentDefinition(baseurl,"srpRProtein", version, proteinURI);
            srprProt.setName("SrpR Protein");
            
            ComponentDefinition lasR = sbol.createComponentDefinition(baseurl,"lasR", version, dnaRegionURI);
            lasR.addRole(new URI(cdsSO));
            lasR.setName("LasR");
            
            ComponentDefinition pLas = sbol.createComponentDefinition(baseurl,"pLas", version, dnaRegionURI);
            pLas.addRole(new URI(induciblePromSO));
            pLas.setName("pLas");
            
            ComponentDefinition lasProt = sbol.createComponentDefinition(baseurl,"lasRProtein", version, proteinURI);
            lasProt.setName("LasR Protein");
            
            //Component Definition for 3 FPs (choose 1). 
            ComponentDefinition gfp = sbol.createComponentDefinition(baseurl, "gfp", version, dnaRegionURI);
            gfp.setName("GFP");
            
            ComponentDefinition bfp = sbol.createComponentDefinition(baseurl, "bfp", version, dnaRegionURI);
            bfp.setName("BFP");
            
            ComponentDefinition rfp = sbol.createComponentDefinition(baseurl, "rfp", version, dnaRegionURI);
            rfp.setName("RFP");
                        
            //Component Definition for 2 terminators (choose 2). 
            ComponentDefinition ter1 = sbol.createComponentDefinition(baseurl,"ter1", version, dnaRegionURI);
            ter1.addRole(new URI(terSO));
            ter1.setName("Terminator 1");
            
            ComponentDefinition ter2 = sbol.createComponentDefinition(baseurl,"ter2", version, dnaRegionURI);
            ter2.addRole(new URI(terSO));
            ter2.setName("Terminator 2");
            
            ComponentDefinition testprom = sbol.createComponentDefinition(baseurl,"testProm", version, dnaRegionURI);
            testprom.addRole(new URI(constitutivePromSO));
            testprom.setName("Test Promoter");
            
            ComponentDefinition testrbs = sbol.createComponentDefinition(baseurl,"testRBS", version, dnaRegionURI);
            testrbs.addRole(new URI(rbsSO));
            testrbs.setName("Test RBS");
            
            ComponentDefinition testcds = sbol.createComponentDefinition(baseurl,"testCDS", version, dnaRegionURI);
            testcds.addRole(new URI(cdsSO));
            testcds.setName("Test CDS");
            
            ComponentDefinition testter = sbol.createComponentDefinition(baseurl,"testTer", version, dnaRegionURI);
            testter.addRole(new URI(terSO));
            testter.setName("Test Ter");
            
            ModuleDefinition tetmd = sbol.createModuleDefinition(baseurl,"tetRmd",version);
            FunctionalComponent fc1 = tetmd.createFunctionalComponent("tetR", AccessType.PRIVATE, tetR.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc2 = tetmd.createFunctionalComponent("tetR_prot", AccessType.PRIVATE, tetProt.getIdentity(), DirectionType.IN);
            Interaction int1 = tetmd.createInteraction("tetR_production", productionURI);
            Participation part1 = int1.createParticipation("tetR_int", fc1.getIdentity(), templateURI);
            Participation part2 = int1.createParticipation("tetR_prot_int", fc2.getIdentity(), productURI);
            
            ModuleDefinition ptetmd = sbol.createModuleDefinition(baseurl,"pTetmd",version);
            FunctionalComponent fc3 = ptetmd.createFunctionalComponent("tetR_prot_rep", AccessType.PRIVATE, tetProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc4 = ptetmd.createFunctionalComponent("pTet", AccessType.PRIVATE, pTet.getIdentity(), DirectionType.IN);
            Interaction int2 = ptetmd.createInteraction("tetR_repression", inhibitionURI);
            Participation part3 = int2.createParticipation("tetR_prot_rep_int", fc3.getIdentity() , inhibitorURI);
            Participation part4 = int2.createParticipation("pTet_int", fc4.getIdentity(),inhibitedURI);

            ModuleDefinition srprmd = sbol.createModuleDefinition(baseurl,"srpRmd",version);
            FunctionalComponent fc5 = srprmd.createFunctionalComponent("srpR", AccessType.PRIVATE, srpR.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc6 = srprmd.createFunctionalComponent("srpR_prot", AccessType.PRIVATE, srprProt.getIdentity(), DirectionType.IN);
            Interaction int3 = srprmd.createInteraction("srpR_production", productionURI);
            Participation part5 = int3.createParticipation("srpR_int", fc5.getIdentity(), templateURI);
            Participation part6 = int3.createParticipation("srpR_prot_int", fc6.getIdentity(), productURI);
            
            ModuleDefinition psrprmd = sbol.createModuleDefinition(baseurl,"psrpRmd",version);
            FunctionalComponent fc7 = psrprmd.createFunctionalComponent("srpR_prot_rep", AccessType.PRIVATE, srprProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc8 = psrprmd.createFunctionalComponent("psrpR", AccessType.PRIVATE, pSrpR.getIdentity(), DirectionType.IN);
            Interaction int4 = psrprmd.createInteraction("srpR_repression", inhibitionURI);
            Participation part7 = int4.createParticipation("srpR_prot_rep_int", fc7.getIdentity() , inhibitorURI);
            Participation part8 = int4.createParticipation("psrpR_int", fc8.getIdentity(),inhibitedURI);

            ModuleDefinition lasRmd = sbol.createModuleDefinition(baseurl,"lasRmd",version);
            FunctionalComponent fc9 = lasRmd.createFunctionalComponent("lasR", AccessType.PRIVATE, lasR.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc10 = lasRmd.createFunctionalComponent("lasR_prot", AccessType.PRIVATE, lasProt.getIdentity(), DirectionType.IN);
            Interaction int5 = lasRmd.createInteraction("lasR_production", productionURI);
            Participation part9 = int5.createParticipation("lasR_int", fc9.getIdentity(), templateURI);
            Participation part10 = int5.createParticipation("lasR_prot_int", fc10.getIdentity(), productURI);
            
            ModuleDefinition plasmd = sbol.createModuleDefinition(baseurl,"plasRmd",version);
            FunctionalComponent fc11 = plasmd.createFunctionalComponent("plasR_prot_rep", AccessType.PRIVATE, lasProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc12 = plasmd.createFunctionalComponent("plasR", AccessType.PRIVATE, pLas.getIdentity(), DirectionType.IN);
            Interaction int6 = plasmd.createInteraction("lasR_repression", stimulationURI);
            Participation part11 = int6.createParticipation("lasR_prot_rep_int", fc11.getIdentity() , stimulationURI);
            Participation part12 = int6.createParticipation("plasR_int", fc12.getIdentity(),stimulationURI);

            ModuleDefinition cp1md = sbol.createModuleDefinition(baseurl,"cp1md",version);
            FunctionalComponent fc13 = cp1md.createFunctionalComponent("cp1", AccessType.PRIVATE, cp1.getIdentity(), DirectionType.IN);
            
            ModuleDefinition cp2md = sbol.createModuleDefinition(baseurl,"cp2md",version);
            FunctionalComponent fc14 = cp2md.createFunctionalComponent("cp2", AccessType.PRIVATE, cp2.getIdentity(), DirectionType.IN);
            
            ModuleDefinition gfpmd = sbol.createModuleDefinition(baseurl, "gfpmd", version);
            FunctionalComponent fc15 = gfpmd.createFunctionalComponent("gfp", AccessType.PRIVATE, gfp.getIdentity(), DirectionType.OUT);
            
            ModuleDefinition bfpmd = sbol.createModuleDefinition(baseurl, "bfpmd", version);
            FunctionalComponent fc16 = bfpmd.createFunctionalComponent("bfp", AccessType.PRIVATE, bfp.getIdentity(), DirectionType.OUT);
            
            ModuleDefinition rfpmd = sbol.createModuleDefinition(baseurl, "rfpmd", version);
            FunctionalComponent fc17 = rfpmd.createFunctionalComponent("rfp", AccessType.PRIVATE, rfp.getIdentity(), DirectionType.OUT);            
            
            Model cp1model = sbol.createModel(baseurl, "cp1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1dsgbqrXrPiGE00m/1"), sbmlURI, frameworkURI);
            Model cp2model = sbol.createModel(baseurl, "cp2_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1dsgcunai9lYiqfY/1"), sbmlURI, frameworkURI);
            
            Model gfpmodel = sbol.createModel(baseurl, "gfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1dqcHtxZqZjJuG92/1"), sbmlURI, frameworkURI);
            Model rfpmodel = sbol.createModel(baseurl, "rfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1dqcIbuwPjlWEpFZ/1"), sbmlURI, frameworkURI);
            Model bfpmodel = sbol.createModel(baseurl, "bfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1dqcIbuwPjlWEpFY/1"), sbmlURI, frameworkURI);
            
            Model tetmodel = sbol.createModel(baseurl, "tetR_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1drJFEFOG6xTzERs/1"), sbmlURI, frameworkURI);
            Model ptetmodel = sbol.createModel(baseurl, "pTet_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1drJEsGhyWwNowtc/1"), sbmlURI, frameworkURI);
            
            Model lasmodel = sbol.createModel(baseurl, "lasR_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1ds8qYONQ100qGCu/1"), sbmlURI, frameworkURI);
            Model pLasmodel = sbol.createModel(baseurl, "pLas_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1ds8pqR0qqxoVh6O/1"), sbmlURI, frameworkURI);
            
            Model srpRmodel = sbol.createModel(baseurl, "srpR_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1drJEAJLPMuBUNn6/1"), sbmlURI, frameworkURI);
            Model pSrpRmodel = sbol.createModel(baseurl, "psrpR_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P1drJEAJLPMuBUNn7/1"), sbmlURI, frameworkURI);

            gfpmd.addModel(gfpmodel);
            rfpmd.addModel(rfpmodel);
            bfpmd.addModel(bfpmodel);
            
            cp1md.addModel(cp1model);
            cp2md.addModel(cp2model);
            
            tetmd.addModel(tetmodel);
            ptetmd.addModel(ptetmodel);
            
            lasRmd.addModel(lasmodel);
            plasmd.addModel(pLasmodel);
            
            srprmd.addModel(srpRmodel);
            psrprmd.addModel(pSrpRmodel);
            
            sbol.write(basefp + "test.xml");
            
            //</editor-fold>
        } catch (URISyntaxException | SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    }
    
}
