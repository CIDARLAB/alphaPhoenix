/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.ArrayList;
import java.util.List;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Orientation;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUniqueString method, of class DnaPlotlibAdaptor.
     */
    @Test
    public void testGetUniqueString() {
        
        Component p1 = new Component();
        p1.setRole(Component.ComponentRole.PROMOTER);
        p1.setOrientation(Orientation.FORWARD);
        
        Component r1 = new Component();
        r1.setRole(Component.ComponentRole.RBS);
        r1.setOrientation(Orientation.FORWARD);
        
        Component c1 = new Component();
        c1.setRole(Component.ComponentRole.CDS);
        c1.setOrientation(Orientation.FORWARD);
        
        Component t1 = new Component();
        t1.setRole(Component.ComponentRole.TERMINATOR);
        t1.setOrientation(Orientation.FORWARD);
        
        Component p2 = new Component();
        p2.setRole(Component.ComponentRole.PROMOTER);
        p2.setOrientation(Orientation.FORWARD);
        
        Component r2 = new Component();
        r2.setRole(Component.ComponentRole.RBS);
        r2.setOrientation(Orientation.FORWARD);
        
        Component c20 = new Component();
        c20.setRole(Component.ComponentRole.CDS);
        c20.setOrientation(Orientation.FORWARD);
        
        Component c21 = new Component();
        c21.setRole(Component.ComponentRole.CDS);
        c21.setOrientation(Orientation.FORWARD);
        
        Component t2 = new Component();
        t2.setRole(Component.ComponentRole.TERMINATOR);
        t2.setOrientation(Orientation.FORWARD);
        
        Interaction i10 = new Interaction(c20,p2,InteractionType.INDUCES);
        Interaction i20 = new Interaction(c20,p1,InteractionType.REPRESSES);
        c20.addInteraction(i10);
        c20.addInteraction(i20);
        
        Interaction i11 = new Interaction(c21,p2,InteractionType.INDUCES);
        Interaction i21 = new Interaction(c21,p1,InteractionType.REPRESSES);
        c21.addInteraction(i21);
        c21.addInteraction(i11);
        
        
        List<Component> components0 = new ArrayList<Component>();
        
        components0.add(p1);
        components0.add(r1);
        components0.add(c1);
        components0.add(t1);
        
        components0.add(p2);
        components0.add(r2);
        components0.add(c20);
        components0.add(t2);
        
        List<Component> components1 = new ArrayList<Component>();
        
        components1.add(p1);
        components1.add(r1);
        components1.add(c1);
        components1.add(t1);
        
        components1.add(p2);
        components1.add(r2);
        components1.add(c21);
        components1.add(t2);
        
        assertEquals(DnaPlotlibAdaptor.getUniqueString(components0),"+p0;+r1;+c2;+t3;+p4;+r5;+c6:rep:p0:act:p4;+t7;");
        assertEquals(DnaPlotlibAdaptor.getUniqueString(components0),DnaPlotlibAdaptor.getUniqueString(components1));
    
    }
    
}
