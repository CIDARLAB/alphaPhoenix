/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import org.junit.Test;

/**
 *
 * @author prash
 */
public class UtilTest {
    
    @Test
    public void testCoolingRate(){
        double temp = 10000;
        double coolingRate = 0.004593;
        double runs = Math.log(1/temp) / Math.log(1-coolingRate);
        System.out.println("Initial Temperature : " + temp);
        System.out.println("Cooling Rate        : " + coolingRate);
        System.out.println("Runs                : " + runs);
        
    }
    
    //@Test
    public void testHalfLife(){
        double logHalf = Math.log10(0.5);
        double halftime = 40;
        double loss = 1 - (Math.pow(10, (logHalf/halftime)));
        System.out.println(loss);
    }
    
}
