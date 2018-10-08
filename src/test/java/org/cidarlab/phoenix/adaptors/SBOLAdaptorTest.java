/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLValidationException;

/**
 *
 * @author prash
 */
public class SBOLAdaptorTest {
    
    public SBOLAdaptorTest() {
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

    
    /**
     * Test of convertUCFtoSBOL method, of class SBOLAdaptor.
     * @throws org.sbolstandard.core2.SBOLValidationException
     * @throws java.net.URISyntaxException
     */
    
    public static void testConvertSampleUCFtoSBOL() throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException {
        String ucfFP = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() +  "sampleUCF.json";
        String outputfp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "sampleUCF" + Utilities.getSeparater();
        JSONObject ucf = new JSONObject(Utilities.getFileContentAsString(ucfFP)); 
        SBOLDocument doc = SBOLAdaptor.convertUCFtoSBOL(ucf,outputfp);
    }
    
    
    /**
     * Test of convertUCFtoSBOL method, of class SBOLAdaptor.
     * @throws org.sbolstandard.core2.SBOLValidationException
     * @throws java.net.URISyntaxException
     */
    public static void testConvertUCFtoSBOL() throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException {
        String ucfFP = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() +  "ucf.json";
        String outputfp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "ucf" + Utilities.getSeparater();
        JSONObject ucf = new JSONObject(Utilities.getFileContentAsString(ucfFP)); 
        SBOLDocument doc = SBOLAdaptor.convertUCFtoSBOL(ucf,outputfp);
    }
    
    
    public static void testConvertReduced0toSBOL() throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException {
        String ucfFP = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() +  "reducedUCF0.json";
        String outputfp = Utilities.getTestedCircuitsFilepath() + "ucf" + Utilities.getSeparater() + "reducedUCF0" + Utilities.getSeparater();
        JSONObject ucf = new JSONObject(Utilities.getFileContentAsString(ucfFP)); 
        SBOLDocument doc = SBOLAdaptor.convertUCFtoSBOL(ucf,outputfp);
    }
    
    public static void main(String[] args) throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException {
        
        testConvertSampleUCFtoSBOL();
        testConvertUCFtoSBOL();
        testConvertReduced0toSBOL();
    }
}
