/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class ConstitutiveExpressionTest {
    
    
    public static String rootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
    public static String expRootFolder = rootFolder + "constitutive_expression" + Utilities.getSeparater();
    
    @Test
    public void testMiniEugeneResult(){
        String eugFilepath = expRootFolder + "constitutiveExpression.eug";
        
        List<Module> modules = MiniEugeneAdaptor.getStructures(eugFilepath, 4, "constExpression");
        assertEquals(modules.size(),1);
        Module m = modules.get(0);
        
        Component p = new Component();
        p.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        p.setOrientation(Orientation.FORWARD);
        
        Component r = new Component();
        r.setRole(Component.ComponentRole.RBS);
        r.setOrientation(Orientation.FORWARD);
        
        Component c = new Component();
        c.setRole(Component.ComponentRole.CDS);
        c.setOrientation(Orientation.FORWARD);
        
        Component t = new Component();
        t.setRole(Component.ComponentRole.TERMINATOR);
        t.setOrientation(Orientation.FORWARD);
        
        assertEquals(m.getComponents().get(0).getRole(),Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        assertEquals(m.getComponents().get(1).getRole(),Component.ComponentRole.RBS);
        assertEquals(m.getComponents().get(2).getRole(),Component.ComponentRole.CDS);
        assertEquals(m.getComponents().get(3).getRole(),Component.ComponentRole.TERMINATOR);
        
        String str = "f" + p.getRole().toString() + ";" + "f" + r.getRole().toString() + ";" + "f" +  c.getRole().toString() + ";" + "f" + t.getRole().toString() + ";";
        
        assertEquals(str,m.getComponentString());
    }
    
    
    
}
