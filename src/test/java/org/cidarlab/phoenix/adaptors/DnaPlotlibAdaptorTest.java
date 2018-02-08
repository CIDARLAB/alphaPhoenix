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
        
        Component c2 = new Component();
        c2.setRole(Component.ComponentRole.CDS);
        c2.setOrientation(Orientation.FORWARD);
        
        Component t2 = new Component();
        t2.setRole(Component.ComponentRole.TERMINATOR);
        t2.setOrientation(Orientation.FORWARD);
        
        Interaction i1 = new Interaction(c2,p2,InteractionType.INDUCES);
        Interaction i2 = new Interaction(c2,p1,InteractionType.REPRESSES);
        c2.addInteraction(i1);
        c2.addInteraction(i2);
        
        
        
        List<Component> components = new ArrayList<Component>();
        
        components.add(p1);
        components.add(r1);
        components.add(c1);
        components.add(t1);
        
        components.add(p2);
        components.add(r2);
        components.add(c2);
        components.add(t2);
        
        
        String dplstring = DnaPlotlibAdaptor.getUniqueString(components);
        
        System.out.println(dplstring);
        
    }
    
}
