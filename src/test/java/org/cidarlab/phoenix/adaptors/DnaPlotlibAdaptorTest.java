/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prash
 */
public class DnaPlotlibAdaptorTest {

    private static Component p10;
    private static Component p11;
    private static Component r1;
    private static Component c1;
    private static Component t1;
    private static Component p20;
    private static Component p21;
    private static Component r2;
    private static Component c20;
    private static Component c21;
    private static Component t2;

    private static Interaction i10;
    private static Interaction i20;
    private static Interaction i11;
    private static Interaction i21;

    private static List<Component> components0;
    private static List<Component> components1;
    
    private static String dnaFigFilepath = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "dnaFigures" + Utilities.getSeparater();
    
    public DnaPlotlibAdaptorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        p10 = new Component();
        p10.setName("p1");
        p10.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        p10.setOrientation(Orientation.FORWARD);
        
        p11 = new Component();
        p11.setName("p1");
        p11.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        p11.setOrientation(Orientation.FORWARD);

        r1 = new Component();
        r1.setName("r1");
        r1.setRole(Component.ComponentRole.RBS);
        r1.setOrientation(Orientation.FORWARD);

        c1 = new Component();
        c1.setName("c1");
        c1.setRole(Component.ComponentRole.CDS);
        c1.setOrientation(Orientation.FORWARD);

        t1 = new Component();
        t1.setName("t1");
        t1.setRole(Component.ComponentRole.TERMINATOR);
        t1.setOrientation(Orientation.FORWARD);

        p20 = new Component();
        p20.setName("p2");
        p20.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        p20.setOrientation(Orientation.FORWARD);

        p21 = new Component();
        p21.setName("p2");
        p21.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        p21.setOrientation(Orientation.FORWARD);

        r2 = new Component();
        r2.setName("r2");
        r2.setRole(Component.ComponentRole.RBS);
        r2.setOrientation(Orientation.FORWARD);

        c20 = new Component();
        c20.setName("c20");
        c20.setRole(Component.ComponentRole.CDS);
        c20.setOrientation(Orientation.FORWARD);

        c21 = new Component();
        c21.setName("c21");
        c21.setRole(Component.ComponentRole.CDS);
        c21.setOrientation(Orientation.FORWARD);

        t2 = new Component();
        t2.setName("t2");
        t2.setRole(Component.ComponentRole.TERMINATOR);
        t2.setOrientation(Orientation.FORWARD);

        i10 = new Interaction(c20, p10, InteractionType.INDUCES);
        i20 = new Interaction(c20, p20, InteractionType.REPRESSES);
        c20.addInteraction(i10);
        c20.addInteraction(i20);
        p10.addInteraction(i10);
        p20.addInteraction(i20);
        
        i11 = new Interaction(c21, p11, InteractionType.INDUCES);
        i21 = new Interaction(c21, p21, InteractionType.REPRESSES);
        c21.addInteraction(i21);
        c21.addInteraction(i11);
        p11.addInteraction(i11);
        p21.addInteraction(i21);
        
        components0 = new ArrayList<Component>();

        components0.add(p10);
        components0.add(r1);
        components0.add(c1);
        components0.add(t1);

        components0.add(p20);
        components0.add(r2);
        components0.add(c20);
        components0.add(t2);

        components1 = new ArrayList<Component>();

        components1.add(p11);
        components1.add(r1);
        components1.add(c1);
        components1.add(t1);

        components1.add(p21);
        components1.add(r2);
        components1.add(c21);
        components1.add(t2);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUniqueString method, of class DnaPlotlibAdaptor.
     */
    @Test
    public void testGetUniqueString() {

        assertEquals(DnaPlotlibAdaptor.getUniqueString(components0), "+p0;+r1;+c2;+t3;+p4;+r5;+c6:act:p0:rep:p4;+t7;");
        assertEquals(DnaPlotlibAdaptor.getUniqueString(components0), DnaPlotlibAdaptor.getUniqueString(components1));

    }
    
     
    public static void testGenerateScript() throws InterruptedException, IOException {
        
        String script = DnaPlotlibAdaptor.generateScript(components1, true, dnaFigFilepath + "plots" + Utilities.getSeparater() + "testFigure");
        String fp = dnaFigFilepath + "scripts" + Utilities.getSeparater() + "test0.py";
        Utilities.writeToFile(dnaFigFilepath + "scripts" + Utilities.getSeparater() + "test0.py", script);
        DnaPlotlibAdaptor.runWebAppScript(fp);
        
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
        testGenerateScript();
    }
    
}
