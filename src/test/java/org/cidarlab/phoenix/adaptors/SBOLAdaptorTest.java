/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.utils.Utilities;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
     * Test of writeCircuitSBOL method, of class SBOLAdaptor.
     */
    @Test
    public void testWriteCircuitSBOL() {
    }

    /**
     * Test of convertUCFtoSBOL method, of class SBOLAdaptor.
     * @throws org.sbolstandard.core2.SBOLValidationException
     * @throws java.net.URISyntaxException
     */
    @Test
    public void testConvertUCFtoSBOL() throws SBOLValidationException, URISyntaxException, IOException, SBOLConversionException, FileNotFoundException, XMLStreamException {
        String ucfFP = Utilities.getTestedCircuitsFilepath() + "cascade_min" + Utilities.getSeparater() + "cascadeMinUCF.json";
        String outputfp = Utilities.getTestedCircuitsFilepath() + "cascade_min" + Utilities.getSeparater() + "library" + Utilities.getSeparater();
        JSONObject ucf = new JSONObject(Utilities.getFileContentAsString(ucfFP)); 
        SBOLDocument doc = SBOLAdaptor.convertUCFtoSBOL(ucf,outputfp);
    
    }
}
