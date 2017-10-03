/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.phoenix.core.PhoenixProjectTest;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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

/**
 *
 * @author prash
 */
public class SynbiohubAdaptorTest {
    
    public SynbiohubAdaptorTest() {
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

    
    @Test
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
        
        String inducerSO = "http://www.biopax.org/release/biopax-level3.owl#SmallMolecule";
        
        
        String basefp = Utilities.getResourcesFilepath() + "example" + Utilities.getSeparater();
        
        try {
            URI complexURI = new URI("http://www.biopax.org/release/biopax-level3.owl#Complex");
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
            
            ComponentDefinition ip1 = sbol.createComponentDefinition(baseurl,"ip1", version, dnaRegionURI);
            ip1.addRole(new URI(induciblePromSO));
            ip1.setName("Inducible Input Promoter 1");
            
            ComponentDefinition ip2 = sbol.createComponentDefinition(baseurl,"ip2", version, dnaRegionURI);
            ip2.addRole(new URI(induciblePromSO));
            ip2.setName("Inducible Input Promoter 2");
            
            ComponentDefinition ip3 = sbol.createComponentDefinition(baseurl,"ip3", version, dnaRegionURI);
            ip3.addRole(new URI(induciblePromSO));
            ip3.setName("Inducible Input Promoter 3");
            
            ComponentDefinition sm1 = sbol.createComponentDefinition(baseurl,"sm1", version, complexURI);
            sm1.addRole(new URI(inducerSO));
            sm1.setName("Small Molecule 1");
            
            ComponentDefinition sm2 = sbol.createComponentDefinition(baseurl,"sm2", version, complexURI);
            sm2.addRole(new URI(inducerSO));
            sm2.setName("Small Molecule 2");
            
            ComponentDefinition sm3 = sbol.createComponentDefinition(baseurl,"sm3", version, complexURI);
            sm3.addRole(new URI(inducerSO));
            sm3.setName("Small Molecule 3");
            
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
            ComponentDefinition cdsR1 = sbol.createComponentDefinition(baseurl,"cdsR1", version, dnaRegionURI);
            cdsR1.addRole(new URI(cdsSO));
            cdsR1.setName("cdsR1");
            
            ComponentDefinition cdsR1Prot = sbol.createComponentDefinition(baseurl,"cdsR1Protein", version, proteinURI);
            cdsR1Prot.setName("cdsR1 Protein");
            
            ComponentDefinition promR1 = sbol.createComponentDefinition(baseurl,"promR1", version, dnaRegionURI);
            promR1.addRole(new URI(induciblePromSO));
            promR1.setName("promR1");
           
            ComponentDefinition cdsR2 = sbol.createComponentDefinition(baseurl,"cdsR2", version, dnaRegionURI);
            cdsR2.addRole(new URI(cdsSO));
            cdsR2.setName("cdsR2");
            
            ComponentDefinition promR2 = sbol.createComponentDefinition(baseurl,"promR2", version, dnaRegionURI);
            promR2.addRole(new URI(induciblePromSO));
            promR2.setName("promR2");
            
            ComponentDefinition promR2Prot = sbol.createComponentDefinition(baseurl,"promR2Protein", version, proteinURI);
            promR2Prot.setName("cdsR2 Protein");
            
            ComponentDefinition cdsA1 = sbol.createComponentDefinition(baseurl,"cdsA1", version, dnaRegionURI);
            cdsA1.addRole(new URI(cdsSO));
            cdsA1.setName("cdsA1");
            
            ComponentDefinition promA1 = sbol.createComponentDefinition(baseurl,"promA1", version, dnaRegionURI);
            promA1.addRole(new URI(induciblePromSO));
            promA1.setName("promA1");
            
            ComponentDefinition cdsA1Prot = sbol.createComponentDefinition(baseurl,"cdsA1Protein", version, proteinURI);
            cdsA1Prot.setName("cdsA1 Protein");
            
            //Component Definition for 3 FPs (choose 1). 
            ComponentDefinition gfp = sbol.createComponentDefinition(baseurl, "gfp", version, dnaRegionURI);
            gfp.addRole(new URI(cdsSO));
            gfp.setName("GFP");
            
            ComponentDefinition bfp = sbol.createComponentDefinition(baseurl, "bfp", version, dnaRegionURI);
            bfp.addRole(new URI(cdsSO));
            bfp.setName("BFP");
            
            ComponentDefinition rfp = sbol.createComponentDefinition(baseurl, "rfp", version, dnaRegionURI);
            rfp.addRole(new URI(cdsSO));
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
            
            ModuleDefinition cdsR1md = sbol.createModuleDefinition(baseurl,"cdsR1md",version);
            FunctionalComponent fc1 = cdsR1md.createFunctionalComponent("cdsR1", AccessType.PRIVATE, cdsR1.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc2 = cdsR1md.createFunctionalComponent("cdsR1_prot", AccessType.PRIVATE, cdsR1Prot.getIdentity(), DirectionType.IN);
            Interaction int1 = cdsR1md.createInteraction("cdsR1_production", productionURI);
            Participation part1 = int1.createParticipation("cdsR1_int", fc1.getIdentity(), templateURI);
            Participation part2 = int1.createParticipation("cdsR1_prot_int", fc2.getIdentity(), productURI);
            
            ModuleDefinition promR1md = sbol.createModuleDefinition(baseurl,"promR1md",version);
            FunctionalComponent fc3 = promR1md.createFunctionalComponent("promR1_prot_rep", AccessType.PRIVATE, cdsR1Prot.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc4 = promR1md.createFunctionalComponent("promR1", AccessType.PRIVATE, promR1.getIdentity(), DirectionType.IN);
            Interaction int2 = promR1md.createInteraction("promR1_repression", inhibitionURI);
            Participation part3 = int2.createParticipation("promR1_prot_rep_int", fc3.getIdentity() , inhibitorURI);
            Participation part4 = int2.createParticipation("promR1_int", fc4.getIdentity(),inhibitedURI);

            ModuleDefinition cdsR2md = sbol.createModuleDefinition(baseurl,"cdsR2md",version);
            FunctionalComponent fc5 = cdsR2md.createFunctionalComponent("cdsR2", AccessType.PRIVATE, cdsR2.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc6 = cdsR2md.createFunctionalComponent("cdsR2_prot", AccessType.PRIVATE, promR2Prot.getIdentity(), DirectionType.IN);
            Interaction int3 = cdsR2md.createInteraction("cdsR2_production", productionURI);
            Participation part5 = int3.createParticipation("cdsR2_int", fc5.getIdentity(), templateURI);
            Participation part6 = int3.createParticipation("cdsR2_prot_int", fc6.getIdentity(), productURI);
            
            ModuleDefinition promR2md = sbol.createModuleDefinition(baseurl,"promR2md",version);
            FunctionalComponent fc7 = promR2md.createFunctionalComponent("promR2_prot_rep", AccessType.PRIVATE, promR2Prot.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc8 = promR2md.createFunctionalComponent("promR2", AccessType.PRIVATE, promR2.getIdentity(), DirectionType.IN);
            Interaction int4 = promR2md.createInteraction("promR2_repression", inhibitionURI);
            Participation part7 = int4.createParticipation("promR2_prot_rep_int", fc7.getIdentity() , inhibitorURI);
            Participation part8 = int4.createParticipation("promR2_int", fc8.getIdentity(),inhibitedURI);

            ModuleDefinition cdsA1md = sbol.createModuleDefinition(baseurl,"cdsA1md",version);
            FunctionalComponent fc9 = cdsA1md.createFunctionalComponent("cdsA1", AccessType.PRIVATE, cdsA1.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc10 = cdsA1md.createFunctionalComponent("cdsA1_prot", AccessType.PRIVATE, cdsA1Prot.getIdentity(), DirectionType.IN);
            Interaction int5 = cdsA1md.createInteraction("cdsA1_production", productionURI);
            Participation part9 = int5.createParticipation("cdsA1_int", fc9.getIdentity(), templateURI);
            Participation part10 = int5.createParticipation("cdsA1_prot_int", fc10.getIdentity(), productURI);
            
            ModuleDefinition promA1md = sbol.createModuleDefinition(baseurl,"promA1Rmd",version);
            FunctionalComponent fc11 = promA1md.createFunctionalComponent("promA1_prot_act", AccessType.PRIVATE, cdsA1Prot.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc12 = promA1md.createFunctionalComponent("promA1", AccessType.PRIVATE, promA1.getIdentity(), DirectionType.IN);
            Interaction int6 = promA1md.createInteraction("promA1_activation", stimulationURI);
            Participation part11 = int6.createParticipation("promA1_prot_act_int", fc11.getIdentity() , stimulatorURI);
            Participation part12 = int6.createParticipation("promA1_int", fc12.getIdentity(),stimulatedURI);

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
            
            ModuleDefinition ip1md = sbol.createModuleDefinition(baseurl,"ip1md",version);
            FunctionalComponent fc18 = ip1md.createFunctionalComponent("sm1", AccessType.PRIVATE, sm1.getIdentity(), DirectionType.OUT);            
            FunctionalComponent fc19 = ip1md.createFunctionalComponent("ip1", AccessType.PRIVATE, ip1.getIdentity(), DirectionType.IN);            
            Interaction int7 = ip1md.createInteraction("ip1_activation", stimulationURI);
            int7.createParticipation("ip1_sm1_act_int", fc18.getIdentity() , stimulatorURI);
            int7.createParticipation("ip1_int", fc19.getIdentity(),stimulatedURI);
            
            ModuleDefinition ip2md = sbol.createModuleDefinition(baseurl,"ip2md",version);
            FunctionalComponent fc20 = ip2md.createFunctionalComponent("sm2", AccessType.PRIVATE, sm2.getIdentity(), DirectionType.OUT);            
            FunctionalComponent fc21 = ip2md.createFunctionalComponent("ip2", AccessType.PRIVATE, ip2.getIdentity(), DirectionType.IN);            
            Interaction int8 = ip2md.createInteraction("ip2_activation", stimulationURI);
            int8.createParticipation("ip2_sm2_act_int", fc20.getIdentity() , stimulatorURI);
            int8.createParticipation("ip2_int", fc21.getIdentity(),stimulatedURI);
            
            ModuleDefinition ip3md = sbol.createModuleDefinition(baseurl,"ip3md",version);
            FunctionalComponent fc22 = ip3md.createFunctionalComponent("sm3", AccessType.PRIVATE, sm3.getIdentity(), DirectionType.OUT);            
            FunctionalComponent fc23 = ip3md.createFunctionalComponent("ip3", AccessType.PRIVATE, ip3.getIdentity(), DirectionType.IN);            
            Interaction int9 = ip3md.createInteraction("ip3_activation", stimulationURI);
            int9.createParticipation("ip3_sm3_act_int", fc22.getIdentity() , stimulatorURI);
            int9.createParticipation("ip3_int", fc23.getIdentity(),stimulatedURI);
            
            Model cp1model = sbol.createModel(baseurl, "cp1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EhakLBReEVjqyLg/1"), sbmlURI, frameworkURI);
            Model cp2model = sbol.createModel(baseurl, "cp2_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EhajzClMeUdggnQ/1"), sbmlURI, frameworkURI);
            
            Model ip1model = sbol.createModel(baseurl, "ip1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8Ei9recFIJQYXo6i/1"), sbmlURI, frameworkURI);
            Model ip2model = sbol.createModel(baseurl, "ip2_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8Ei9rIdZ0jPSNWYS/1"), sbmlURI, frameworkURI);
            Model ip3model = sbol.createModel(baseurl, "ip3_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8Ei9qagCRZNG2xRw/1"), sbmlURI, frameworkURI);
            
            
            Model gfpmodel = sbol.createModel(baseurl, "gfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EkaAzl9tPbykN4z/1"), sbmlURI, frameworkURI);
            Model rfpmodel = sbol.createModel(baseurl, "rfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EkaAzl9tPbykN4y/1"), sbmlURI, frameworkURI);
            Model bfpmodel = sbol.createModel(baseurl, "bfp_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EkaAdmTbpasa5Wi/1"), sbmlURI, frameworkURI);
            
            Model cdsR1model = sbol.createModel(baseurl, "cdsR1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EjWKyY0ezPouPTN/1"), sbmlURI, frameworkURI);
            Model promR1model = sbol.createModel(baseurl, "promR1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EjWKyY0ezPouPTM/1"), sbmlURI, frameworkURI);
            
            Model cdsR2model = sbol.createModel(baseurl, "cdsR2_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EjWKGae5pNcZqMq/1"), sbmlURI, frameworkURI);
            Model promR2model = sbol.createModel(baseurl, "promR2_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EjWJubxoFMWPYoa/1"), sbmlURI, frameworkURI);
            
            Model cdsA1model = sbol.createModel(baseurl, "cdsA1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EiujoLDwmnqEhuK/1"), sbmlURI, frameworkURI);
            Model promA1model = sbol.createModel(baseurl, "promA1_model", version, new URI("https://synbiohub.cidarlab.org/public/AlphaPhoenixModels/attachment_00009P8EiukAJuEMowOzSa/1"), sbmlURI, frameworkURI);
            
            ip1md.addModel(ip1model);
            ip2md.addModel(ip1model);
            ip3md.addModel(ip1model);
            
            gfpmd.addModel(gfpmodel);
            rfpmd.addModel(rfpmodel);
            bfpmd.addModel(bfpmodel);
            
            cp1md.addModel(cp1model);
            cp2md.addModel(cp2model);
            
            cdsR1md.addModel(cdsR1model);
            promR1md.addModel(promR1model);
            
            cdsA1md.addModel(cdsA1model);
            promA1md.addModel(promA1model);
            
            cdsR2md.addModel(cdsR2model);
            promR2md.addModel(promR2model);
            
            sbol.write(basefp + "test.xml");
            
            //</editor-fold>
        } catch (URISyntaxException | SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    }
    
    
}
