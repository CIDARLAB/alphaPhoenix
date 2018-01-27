/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.examples;

import java.util.List;
import org.cidarlab.phoenix.adaptors.MiniEugeneAdaptor;
import org.cidarlab.phoenix.dom.Module;
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
    }
    
    
    
}
