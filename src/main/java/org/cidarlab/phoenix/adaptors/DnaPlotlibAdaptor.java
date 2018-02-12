/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.Orientation;

/**
 *
 * @author prash
 */
public class DnaPlotlibAdaptor {
    
    
    public static String generateScript(List<Component> components, String filename){
        String scr = "#!/usr/bin/env python\n";
        
        scr += "import dnaplotlib as dpl\n" +
               "from pylab import *\n" +
               "import matplotlib.pyplot as plt\n" +
               "from matplotlib import gridspec\n\n";
        
        double length = (0.2 * components.size());
        
        scr += "gs = gridspec.GridSpec(1, 1)\n\n";
        
        scr += "# Function to generate a ligher colour\n" +
               "def lighten_color (col, fac):\n" +
               "	r = col[0] + (fac*(1.0-col[0]))\n" +
               "	g = col[1] + (fac*(1.0-col[1]))\n" +
               "	b = col[2] + (fac*(1.0-col[2]))\n" +
               "	return (r,g,b)\n\n";
        
        scr += "# Colour map\n" +
               "col_map = {}\n" +
               "col_map['black']   = (0.00, 0.00, 0.00)\n" +
               "col_map['white']   = (1.00, 1.00, 1.00)\n" +
               "col_map['red']     = (0.95, 0.30, 0.25)\n" +
               "col_map['green']   = (0.38, 0.82, 0.32)\n" +
               "col_map['blue']    = (0.38, 0.65, 0.87)\n" +
               "col_map['orange']  = (1.00, 0.75, 0.17)\n" +
               "\n" +
               "# Global line width\n" +
               "lw = 0.8\n\n" + 
               "# How much to lighten OFF components\n" +
               "off_fac = 0.7\n\n";
        
        Map<String,String> partNameMap = new HashMap<String,String>();
        int partCount = 0;
        int arcCount = 0;
        
        scr += "# Component definitions\n";
        String designString = "design = [";
        String partName = "";
        for(Component c:components){
            partName = "part" + partCount++;
            scr += partName + " = " + createPartString(c.getName(),c.getRole(),c.getOrientation()) + "\n";
            System.out.println("Part name : " + c.getName());
            partNameMap.put(c.getName(), partName);
            designString += partName;
            if(partCount < components.size()){
                designString += ",";
            }
        }
        designString += "]\n";
        
        scr += designString;
        scr += "# Arc definitions\n";
        
        String regString = "regDesign = [";
        String arcName = "";
        List<String> arcList = new ArrayList<String>();
        int maxArcs = 0;
        for(Component c:components){
            int iCount = 0;
            for(Interaction i:c.getInteractions()){
                if(c.getInteractions().size() > maxArcs){
                    maxArcs = c.getInteractions().size();
                }
                double arcHeight = 20.0 + (5*iCount);
                arcName = "reg" + arcCount++;
                iCount++;
                scr += arcName + " = " + createArcString(partNameMap.get(i.getFrom().getName()),partNameMap.get(i.getTo().getName()),i.getType(),arcHeight) + "\n";
                //Add arcs to an array.
                arcList.add(arcName);
            }
        }
        if(!arcList.isEmpty()){
            regString += arcList.get(0);
            for(int i=1;i<arcCount;i++){
                regString += "," + arcList.get(i);
            }
            regString += "]\n\n";
            scr += regString;
        }
        
        double height = 0.2 + (0.2*maxArcs);
        double ylim = 15.0 + (5 * maxArcs);
        scr += "fig = plt.figure(figsize=(" + length + "," + height + "));\n";
        
        scr += "ax_dna = plt.subplot(gs[0])\n" +
               "\n" +
               "# Create the DNAplotlib renderer\n" +
               "dr = dpl.DNARenderer()\n\n";
        
        scr += "start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers(), \n" +
               "	                      regs=regDesign, reg_renderers=dr.std_reg_renderers())\n";
        
        scr += "ax_dna.set_xlim([start, end])\n" +
               "ax_dna.set_ylim([-" + ylim + "," + ylim +"])\n" +
               "ax_dna.set_aspect('equal')\n" +
               "ax_dna.axis('off')\n\n";
        
        scr += "fig.savefig('" + filename + ".png', dpi=300)\n" +
               "plt.close('all')";
        return scr;
        
    }
    
    private static String createPartString(String name, ComponentRole role, Orientation o){
        String scr = "";
        scr +=  "{";
        scr += "'type':'";
        String oString = "True";
        if(o.equals(Orientation.REVERSE)){
            oString = "False";
        }
        switch(getRoleCharacter(role)){
            case 'p':
                scr += "Promoter', 'fwd':" + oString + ", 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}";
                break;
            case 'r':
                scr += "RBS', 'fwd':" + oString + ", 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}";
                break;
            case 'c':
                scr += "CDS', 'fwd':" + oString + ", 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black'],'x_extent':24}}";
                break;
            case 't':
                scr += "Terminator', 'fwd':" + oString + ", 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}";
                break;
            case 'w':
                System.out.println("Unidentified form?");
                return "";
                
            default:
                System.out.println("Error. This should not happen.");
                return "";
        }
        return scr; 
    }
    
    private static String createArcString(String from, String to, InteractionType type, double arcHeight){
        String scr = "";
        
        scr +=  "{";
        scr += "'type': ";
        
        if(type == InteractionType.INDUCES)
            scr += "'Repression',"; 
        else 
            scr += "'Activation',";
        
        scr += "'from_part':" + from + ", 'to_part':" + to + ", 'opts':{'color':col_map['red'], 'linewidth':lw";  
        if(arcHeight > 0){
            scr += ", 'arc_height':" + arcHeight +"}}";
        } else {
            scr += "}}";
        }
        
        
        
        return scr;
    }
    
    
    public static String getUniqueString(List<Component> components){
        
        String str = "";
        for(int k=0;k<components.size();k++){
            Component c = components.get(k);
            str += getOrientationCharacter(c.getOrientation());
            str += getRoleCharacter(c.getRole());
            str += k;
            if (Controller.isCDS(c)) {
                List<Integer> indices = new ArrayList<Integer>();
                List<String> intStrings = new ArrayList<String>();
                for (Interaction i : c.getInteractions()) {
                    if(!i.getType().equals(InteractionType.INDUCES) && !i.getType().equals(InteractionType.REPRESSES)){
                        continue;
                    }
                    
                    int indx = components.indexOf(i.getTo());
                    if(intStrings.isEmpty()){
                        indices.add(indx);
                        intStrings.add(getInteractionString(i.getType())+"p"+indx);
                    } else {
                        int insertAt = indices.size();
                        for (int j = 0; j < indices.size(); j++) {
                            if(indx < indices.get(j)){
                                insertAt = j;
                                break;
                            }
                        }
                        indices.add(insertAt,indx);
                        intStrings.add(insertAt,getInteractionString(i.getType())+"p"+indx);
                    }
                    
                }
                
                for(String intString:intStrings){
                    str += (":" + intString);
                }
            }
            
            
            str += ";";
        }
        
        return str;
    
    }
    
    private static String getInteractionString(InteractionType type){
        switch(type){
            case INDUCES:
                return "act:";
            case REPRESSES:
                return "rep:";
            default: 
                System.out.println("Probably throw an error here?");
                return "";
        }
    }
    
    private static char getOrientationCharacter(Orientation o){
        switch(o){
            case FORWARD:
                return '+';
            case REVERSE:
                return '-';
            default: 
                return '+';
        } 
    }
    
    private static char getRoleCharacter(ComponentRole role){
        switch(role){
            case PROTEIN:
                return 'w';
            case INDUCER:
                return 'w';
            case PROMOTER:
            case PROMOTER_REPRESSIBLE:
            case PROMOTER_INDUCIBLE:
            case PROMOTER_CONSTITUTIVE:
                return 'p';
            case RBS:
                return 'r';
            case CDS:
            case CDS_REPRESSOR:
            case CDS_ACTIVATOR:
            case CDS_REPRESSIBLE_REPRESSOR:
            case CDS_ACTIVATIBLE_ACTIVATOR:
            case CDS_LINKER:
            case CDS_TAG:
            case CDS_RESISTANCE:
            case CDS_FLUORESCENT:
            case CDS_FLUORESCENT_FUSION:
                return 'c';
            case TERMINATOR:
                return 't';
            case ORIGIN:
            case VECTOR:
            case TESTING:
            case MARKER:
            case WILDCARD:
                return 'w';
            default:
                return 'w';
        }
    }
    
    
}
