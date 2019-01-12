/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.File;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

/**
 *
 * @author prash
 */
public class SBMLAdaptorTest {
    
    public SBMLAdaptorTest() {
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

    static String basefp = Utilities.getLibFilepath() + "examples" + Utilities.getSeparater() + "sbmlDocs" + Utilities.getSeparater();
    
    
    public static void main(String[] args) throws XMLStreamException, IOException {
        System.out.println("ONE TU===========================================");
        String file1 = basefp + "oneTU.xml";
        SBMLDocument doc1 = SBMLReader.read(new File(file1));
        System.out.println(SBMLAdaptor.convertModelToLaTeXEquations(doc1)); 
        
        System.out.println("TWO TU===========================================");
        String file2 = basefp + "twoTU.xml";
        SBMLDocument doc2 = SBMLReader.read(new File(file2));
        System.out.println(SBMLAdaptor.convertModelToLaTeXEquations(doc2)); 
    }
    
  
}
