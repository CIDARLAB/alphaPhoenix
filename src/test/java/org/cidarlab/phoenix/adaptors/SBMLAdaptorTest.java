/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import edu.utah.ece.async.ibiosim.dataModels.util.dataparser.TSDParser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.tli.Utilities;
import org.cidarlab.gridtli.visualize.JavaPlotAdaptor;
import org.cidarlab.phoenix.dom.ModelPart;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.Species;

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

    @Test
    public void testNotModel(){
        try {
            String basefp = Utilities.getResourcesFilepath() + "SampleModels" + Utilities.getSeparater();
            String p1fp = basefp + "Prom1.xml";
            String p2fp = basefp + "Prom2.xml";
            String c1fp = basefp + "CDS1.xml";
            String c2fp = basefp + "CDS2.xml";
            String not = basefp + "FullModel.xml";
            
            ModelPart p1m  = new ModelPart(SBMLReader.read(new File(p1fp)));
            ModelPart p2m  = new ModelPart(SBMLReader.read(new File(p2fp)));
            ModelPart c1m  = new ModelPart(SBMLReader.read(new File(c1fp)));
            ModelPart c2m  = new ModelPart(SBMLReader.read(new File(c2fp)));
            
            ModelPart tu1 = new ModelPart(SBMLAdaptor.composeModels(p1m.getSbml().getModel(), c1m.getSbml().getModel()));
            ModelPart tu2 = new ModelPart(SBMLAdaptor.composeModels(p2m.getSbml().getModel(), c2m.getSbml().getModel()));
            
            ModelPart notTest = new ModelPart(SBMLAdaptor.composeModels(tu1.getSbml().getModel(), tu2.getSbml().getModel()));
            
            String outNotTest = basefp + "composedNot.xml";
            SBMLWriter writer = new SBMLWriter();
            writer.write(notTest.getSbml(), outNotTest);
            
            String stochComp = basefp + "Stochastic" + Utilities.getSeparater() + "Composed";
            String stochOrg = basefp + "Stochastic" + Utilities.getSeparater() + "Original";
            
            String detComp = basefp + "Deterministic" + Utilities.getSeparater() + "Composed";
            String detOrg = basefp + "Deterministic" + Utilities.getSeparater() + "Original";
            
            
            IBioSimAdaptor.simulateODE(outNotTest, detComp, 100, 1, 1);
            IBioSimAdaptor.simulateODE(not, detOrg, 100, 1, 1);
            
            int simCount = 10;
            
            IBioSimAdaptor.simulateStocastic(outNotTest, stochComp, 100, 1, 1, simCount);
            IBioSimAdaptor.simulateStocastic(not, stochOrg, 100, 1, 1, simCount);
            
            String csvdetOrg = detOrg + "run-1.csv";
            String csvdetComp = detComp + "run-1.csv";
            List<Signal> stoOrgSig = new ArrayList<Signal>();
            List<Signal> stoComSig = new ArrayList<Signal>();
            File f;
            
            for(int i=1;i<simCount;i++){
                String csvstochOrg = stochOrg + "run-" + i + ".csv";
                String csvstochComp = stochComp + "run-" + i + ".csv";
                TSDParser tsdParser3 = new TSDParser(stochOrg + "run-" + i + ".tsd",false);
                tsdParser3.outputCSV(csvstochOrg);
            
                TSDParser tsdParser4 = new TSDParser(stochComp + "run-" + i + ".tsd",false);
                tsdParser4.outputCSV(csvstochComp);
                stoOrgSig.addAll(Utilities.getiBioSimSignals(csvstochOrg));
                stoComSig.addAll(Utilities.getiBioSimSignals(csvstochComp));
                
                f = new File(csvstochOrg);
                f.delete();
                f = new File(csvstochComp);
                f.delete();
                f = new File(stochOrg + "run-" + i + ".tsd");
                f.delete();
                f = new File(stochComp + "run-" + i + ".tsd");
                f.delete();
                
            }
            
            f = new File(stochOrg + "run-" + simCount + ".tsd");
            f.delete();
            f = new File(stochComp + "run-" + simCount + ".tsd");
            f.delete();
            
            TSDParser tsdParser1 = new TSDParser(detOrg + "run-1.tsd",false);
            tsdParser1.outputCSV(csvdetOrg);
            
            TSDParser tsdParser2 = new TSDParser(detComp + "run-1.tsd",false);
            tsdParser2.outputCSV(csvdetComp);
            
            List<Signal> odeOrgSig = Utilities.getiBioSimSignals(csvdetOrg);
            List<Signal> odeComSig = Utilities.getiBioSimSignals(csvdetComp);
            
            
            
            Grid odeOrgG = new Grid(odeOrgSig,1,1);
            Grid odeComG = new Grid(odeComSig,1,1);
            Grid stoOrgG = new Grid(stoOrgSig,1,1);
            Grid stoComG = new Grid(stoComSig,1,1);
            
            String odeOrgGfp = detOrg+ ".png";
            String odeComGfp = detComp+ ".png";
            String stoOrgGfp = stochOrg+ ".png";
            String stoComGfp = stochComp+ ".png";
            
            JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGrid(odeOrgG), odeOrgGfp);
            JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGrid(odeComG), odeComGfp);
            JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGrid(stoOrgG), stoOrgGfp);
            JavaPlotAdaptor.plotToFile(JavaPlotAdaptor.plotGrid(stoComG), stoComGfp);
            
            
        } catch (XMLStreamException | IOException ex) {
            Logger.getLogger(SBMLAdaptorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
