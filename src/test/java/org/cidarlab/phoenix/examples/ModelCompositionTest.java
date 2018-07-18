/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.phoenix.adaptors.SBMLAdaptor;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.Test;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;

/**
 *
 * @author prash
 */
public class ModelCompositionTest {
    
    private static final String tested_circuitsFP = Utilities.getTestedCircuitsFilepath();
    private static final String sampleCircuitsFP = tested_circuitsFP + "circuits" + Utilities.getSeparater() + "modeltest" + Utilities.getSeparater();
    
    private static final String prom0fp = sampleCircuitsFP + "prom0.xml";
    private static final String cds0fp = sampleCircuitsFP + "cds0.xml";
    
    private static final String prom1fp = sampleCircuitsFP + "prom1.xml";
    private static final String cds1fp = sampleCircuitsFP + "cds1.xml";
    
    private static final String tu0fp = sampleCircuitsFP + "tu0.xml";
    private static final String tu1fp = sampleCircuitsFP + "tu1.xml";
    
    @Test
    public void testModuleModelComposition() throws XMLStreamException, IOException{
        SBMLDocument tu0 = SBMLReader.read(new File(tu0fp));
        SBMLDocument tu1 = SBMLReader.read(new File(tu1fp));
        List<org.sbml.jsbml.Model> models = new ArrayList<>();
        models.add(tu0.getModel());
        models.add(tu1.getModel());
        SBMLDocument model = SBMLAdaptor.composeModels(models);
        SBMLWriter writer = new SBMLWriter();
        System.out.println("########################################################");
        System.out.println("Test Module Model Composition");
        System.out.println("--------------------------------------------------------");
        System.out.println(writer.writeSBMLToString(model));
        System.out.println("########################################################");
    }
    
    @Test
    public void testTU0ModelComposition() throws XMLStreamException, IOException{
        
        SBMLDocument prom0 = SBMLReader.read(new File(prom0fp));
        SBMLDocument cds0 = SBMLReader.read(new File(cds0fp));
        List<org.sbml.jsbml.Model> models = new ArrayList<>();
        models.add(prom0.getModel());
        models.add(cds0.getModel());
        SBMLDocument tu = SBMLAdaptor.composeModels(models);
        SBMLWriter writer = new SBMLWriter();
        System.out.println("########################################################");
        System.out.println("Test TU0 Model Composition");
        System.out.println("--------------------------------------------------------");
        System.out.println(writer.writeSBMLToString(tu));
        System.out.println("########################################################");
    }
    
    @Test
    public void testTU1ModelComposition() throws XMLStreamException, IOException{
        SBMLDocument prom1 = SBMLReader.read(new File(prom1fp));
        SBMLDocument cds1 = SBMLReader.read(new File(cds1fp));
        List<org.sbml.jsbml.Model> models = new ArrayList<>();
        models.add(prom1.getModel());
        models.add(cds1.getModel());
        SBMLDocument tu = SBMLAdaptor.composeModels(models);
        SBMLWriter writer = new SBMLWriter();
        System.out.println("########################################################");
        System.out.println("Test TU1 Model Composition");
        System.out.println("--------------------------------------------------------");
        System.out.println(writer.writeSBMLToString(tu));
        System.out.println("########################################################");
    }
    
}
