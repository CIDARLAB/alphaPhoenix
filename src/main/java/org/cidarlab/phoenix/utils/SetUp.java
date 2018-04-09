/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Interaction;
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
    private static Component ip1;
    private static Component ip2;
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
    private static Component gfp;
    private static Component rfp;
    private static Component bfp;
    
    //Terminator
    private static Component ter1;
    private static Component ter2;
    
    
    public static void initializeComponents(){
        
        
        cp1 = new Component();
        cp1.setOrientation(Orientation.FORWARD);
        cp1.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        cp1.setName("ConstitutivePromoter1");
        
        cp2 = new Component();
        cp2.setOrientation(Orientation.FORWARD);
        cp2.setRole(Component.ComponentRole.PROMOTER_CONSTITUTIVE);
        cp2.setName("ConstitutivePromoter2");
        
        ip1 = new Component();
        ip1.setOrientation(Orientation.FORWARD);
        ip1.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        ip1.setName("InduciblePromoter1");
        
        ip2 = new Component();
        ip2.setOrientation(Orientation.FORWARD);
        ip2.setRole(Component.ComponentRole.PROMOTER_INDUCIBLE);
        ip2.setName("InduciblePromoter2");
        
        
        rp1 = new Component();
        rp1.setOrientation(Orientation.FORWARD);
        rp1.setRole(Component.ComponentRole.PROMOTER_REPRESSIBLE);
        rp1.setName("RepressiblePromoter1");
        
        rp2 = new Component();
        rp2.setOrientation(Orientation.FORWARD);
        rp2.setRole(Component.ComponentRole.PROMOTER_REPRESSIBLE);
        rp2.setName("RepressiblePromoter2");
        
        rbs1 = new Component();
        rbs1.setOrientation(Orientation.FORWARD);
        rbs1.setRole(Component.ComponentRole.RBS);
        rbs1.setName("RBS1");
        
        rbs2 = new Component();
        rbs2.setOrientation(Orientation.FORWARD);
        rbs2.setRole(Component.ComponentRole.RBS);
        rbs2.setName("RBS2");
        
        rcds1 = new Component();
        rcds1.setOrientation(Orientation.FORWARD);
        rcds1.setRole(Component.ComponentRole.CDS_REPRESSOR);
        rcds1.setName("RepCDS1");
        
        rcds2 = new Component();
        rcds2.setOrientation(Orientation.FORWARD);
        rcds2.setRole(Component.ComponentRole.CDS_REPRESSOR);
        rcds2.setName("RepCDS2");
        
        acds1 = new Component();
        acds1.setOrientation(Orientation.FORWARD);
        acds1.setRole(Component.ComponentRole.CDS_ACTIVATOR);
        acds1.setName("ActCDS1");
        
        acds2 = new Component();
        acds2.setOrientation(Orientation.FORWARD);
        acds2.setRole(Component.ComponentRole.CDS_ACTIVATOR);
        acds2.setName("ActCDS2");
        
        gfp = new Component();
        gfp.setOrientation(Orientation.FORWARD);
        gfp.setRole(Component.ComponentRole.CDS_FLUORESCENT);
        gfp.setName("GFP");
        
        bfp = new Component();
        bfp.setOrientation(Orientation.FORWARD);
        bfp.setRole(Component.ComponentRole.CDS_FLUORESCENT);
        bfp.setName("BFP");
        
        rfp = new Component();
        rfp.setOrientation(Orientation.FORWARD);
        rfp.setRole(Component.ComponentRole.CDS_FLUORESCENT);
        rfp.setName("RFP");
        
        Interaction ip1int = new Interaction(acds1,ip1,InteractionType.INDUCES);
        acds1.addInteraction(ip1int);
        ip1.addInteraction(ip1int);
        
        Interaction ip2int = new Interaction(acds2,ip2,InteractionType.INDUCES);
        acds2.addInteraction(ip2int);
        ip2.addInteraction(ip2int);
        
        Interaction rp1int = new Interaction(rcds1,rp1,InteractionType.REPRESSES);
        rcds1.addInteraction(rp1int);
        rp1.addInteraction(rp1int);
        
        Interaction rp2int = new Interaction(rcds2,rp2,InteractionType.REPRESSES);
        rcds2.addInteraction(rp2int);
        rp2.addInteraction(rp2int);
        
    }
    
    
    
}
