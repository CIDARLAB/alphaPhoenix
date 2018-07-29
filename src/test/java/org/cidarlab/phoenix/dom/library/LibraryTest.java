/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.cidarlab.phoenix.utils.Args;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;
import org.synbiohub.frontend.SynBioHubException;
import org.synbiohub.frontend.SynBioHubFrontend;

/**
 *
 * @author prash
 */
public class LibraryTest {
    
    public LibraryTest() {
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
    public void testPR_C_T() throws URISyntaxException, SynBioHubException, SBOLValidationException, MalformedURLException{
        String synbiohuburl = "https://synbiohub.programmingbiology.org";
        String phoenixliburl = "https://synbiohub.programmingbiology.org/public/PhoenixParts/PhoenixParts_collection/1";
        String phoenixSampleurl = "https://synbiohub.programmingbiology.org/public/AlphaSample/AlphaSample_collection/1";
        
        String rulesfp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "ucf" + Utilities.getSeparater();
        
        SynBioHubFrontend shub = new SynBioHubFrontend(synbiohuburl);
        URI u = new URI(phoenixliburl);
        SBOLDocument sbol = shub.getSBOL(u);
        Library lib = new Library(sbol, Args.Decomposition.PR_C_T,rulesfp);
        
        System.out.println("\n");
        System.out.println("############################################");
        System.out.println("Library Details ");
        System.out.println("Constitutive Promoters       : " + lib.getConstitutivePromoters().size());
        System.out.println("Activatible Promoters        : " + lib.getActivatiblePromoters().size());
        System.out.println("Repressible Promoters        : " + lib.getRepressiblePromoters().size());
        System.out.println("Activatible Input Promoters  : " + lib.getIndActPromoters().size());
        System.out.println("Repressible Input Promoters  : " + lib.getIndRepPromoters().size());
        System.out.println("RBS                          : " + lib.getRbs().size());
        System.out.println("Output CDS                   : " + lib.getOutputCDS().size());
        System.out.println("Connector CDS                : " + lib.getConnectorCDS().size());
        System.out.println("Terminators                  : " + lib.getTerminators().size());
        System.out.println("Proteins                     : " + lib.getProteins().size());
        System.out.println("Small Molecules              : " + lib.getSmallMolecules().size());
        System.out.println("############################################\n");
        System.out.println("Composite PR                 : " + lib.getPr().size());
        System.out.println("############################################\n");

        
        
    }
    
    
}
