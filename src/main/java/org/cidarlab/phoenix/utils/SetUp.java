/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class SetUp {
    
    private static String libFilepath = Utilities.getFilepath() + "lib" + Utilities.getSeparater();
    private static String dnaFilepath = libFilepath + "dnaFigures" + Utilities.getSeparater();
    
    //Promoters
    private static Component cp1;
    private static Component cp2;
    private static Component ap1;
    private static Component ap2;
    private static Component rp1;
    private static Component rp2;
    
    //RBS
    private static Component rbs1;
    private static Component rbs2;
    
    //CDS
    private static Component acds1;
    private static Component acds2;
    private static Component rcds1;
    private static Component rcds2;
    
    //Terminator
    private static Component ter1;
    private static Component ter2;
    
    
    public SetUp(){
        
        
        cp1 = new Component();
        cp1.setOrientation(Orientation.FORWARD);
        cp1.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        cp1.setName("ConstitutivePromoter1");
        
        cp2 = new Component();
        cp2.setOrientation(Orientation.FORWARD);
        cp2.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        cp1.setName("ConstitutivePromoter2");
        
        ap1 = new Component();
        ap2 = new Component();
        rp1 = new Component();
        rp2 = new Component();
        
        
    }
    
}
