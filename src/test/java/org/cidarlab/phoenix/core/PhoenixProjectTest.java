/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sbolstandard.core2.AccessType;
import org.sbolstandard.core2.ComponentDefinition;
import org.sbolstandard.core2.DirectionType;
import org.sbolstandard.core2.FunctionalComponent;
import org.sbolstandard.core2.Interaction;
import org.sbolstandard.core2.ModuleDefinition;
import org.sbolstandard.core2.Participation;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;

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
    
    @Test
    public void testPhoenix() {
        
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
            
            //Component Definition for 2 terminators (choose 2). 
            ComponentDefinition ter1 = sbol.createComponentDefinition(baseurl,"ter1", version, dnaRegionURI);
            ter1.addRole(new URI(terSO));
            ter1.setName("Terminator 1");
            
            ComponentDefinition ter2 = sbol.createComponentDefinition(baseurl,"ter2", version, dnaRegionURI);
            ter2.addRole(new URI(terSO));
            ter2.setName("Terminator 2");
            
            
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
            FunctionalComponent fc9 = lasRmd.createFunctionalComponent("lasR", AccessType.PRIVATE, tetR.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc10 = lasRmd.createFunctionalComponent("lasR_prot", AccessType.PRIVATE, tetProt.getIdentity(), DirectionType.IN);
            Interaction int5 = lasRmd.createInteraction("lasR_production", productionURI);
            Participation part9 = int5.createParticipation("lasR_int", fc9.getIdentity(), templateURI);
            Participation part10 = int5.createParticipation("lasR_prot_int", fc10.getIdentity(), productURI);
            
            ModuleDefinition plasmd = sbol.createModuleDefinition(baseurl,"plasRmd",version);
            FunctionalComponent fc11 = plasmd.createFunctionalComponent("plasR_prot_rep", AccessType.PRIVATE, tetProt.getIdentity(), DirectionType.OUT);
            FunctionalComponent fc12 = plasmd.createFunctionalComponent("plasR", AccessType.PRIVATE, pTet.getIdentity(), DirectionType.IN);
            Interaction int6 = plasmd.createInteraction("lasR_repression", stimulationURI);
            Participation part11 = int6.createParticipation("lasR_prot_rep_int", fc11.getIdentity() , stimulationURI);
            Participation part12 = int6.createParticipation("plasR_int", fc12.getIdentity(),stimulationURI);

            ModuleDefinition cp1md = sbol.createModuleDefinition(baseurl,"cp1md",version);
            FunctionalComponent fc13 = cp1md.createFunctionalComponent("cp1", AccessType.PRIVATE, cp1.getIdentity(), DirectionType.IN);
            
            ModuleDefinition cp2md = sbol.createModuleDefinition(baseurl,"cp2md",version);
            FunctionalComponent fc14 = cp1md.createFunctionalComponent("cp2", AccessType.PRIVATE, cp2.getIdentity(), DirectionType.IN);
            
            
            sbol.write(basefp + "test.xml");
            
            //</editor-fold>
        } catch (URISyntaxException | SBOLValidationException | IOException | SBOLConversionException ex) {
            Logger.getLogger(PhoenixProjectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    }
    
}
