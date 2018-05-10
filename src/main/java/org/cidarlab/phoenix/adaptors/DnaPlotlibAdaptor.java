/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.minieugene.predicates.interaction.Interaction.InteractionType;
import org.cidarlab.phoenix.core.Controller;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Component.ComponentRole;
import org.cidarlab.phoenix.dom.Interaction;
import org.cidarlab.phoenix.dom.Orientation;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class DnaPlotlibAdaptor {
    
    private static List<String> colors = new ArrayList<String>();
    
    public static String generateNewColor(){
        DecimalFormat df = new DecimalFormat("#.##");
        Random random = new Random();
        double r = (double) (random.nextInt(101)/100.00);
        double g = (double) (random.nextInt(101)/100.00);
        double b = (double) (random.nextInt(101)/100.00);
        String str = "(" + Double.valueOf(df.format(r)) + "," + Double.valueOf(df.format(g)) + "," + Double.valueOf(df.format(b)) + ")";
            
        while(colors.contains(str) || ( (r==1.00) && (g == 1.00) && (b == 1.00)) ){
            r = (double) (random.nextInt(101)/100.00);
            g = (double) (random.nextInt(101)/100.00);
            b = (double) (random.nextInt(101)/100.00);
            str = "(" + Double.valueOf(df.format(r)) + "," + Double.valueOf(df.format(g)) + "," + Double.valueOf(df.format(b)) + ")";
        }
        
        return str;
    }
    
    public static String generateScript(List<Component> components, boolean labels, Map<String,String> colorMap, String filename){
        
        String scr = "#!/usr/bin/env python\n";
        
        scr += "import dnaplotlib as dpl\n" +
               "from pylab import *\n" +
               "import matplotlib.pyplot as plt\n" +
               "from matplotlib import gridspec\n\n";
        
        double length = (0.2 * components.size());
        
        int colorIndex = 0;
        if (colorMap.isEmpty()) {
            
            colors.add("(0.95, 0.30, 0.25)"); //red
            colors.add("(0.38, 0.82, 0.32)"); //green
            colors.add("(0.38, 0.65, 0.87)"); //blue
            colors.add("(1.00, 0.75, 0.17)"); //orange
            colors.add("(1.00, 1.00, 1.00)"); //white
            for (Component c : components) {
                if (Controller.isCDS(c) || Controller.isPromoter(c)) {
                    boolean colorfound = false;
                    if (colorMap.containsKey(c.getName())) {
                        for (Interaction i : c.getInteractions()) {
                            colorMap.put(i.getFrom().getName(), colorMap.get(c.getName()));
                            colorMap.put(i.getTo().getName(), colorMap.get(c.getName()));
                        }
                    } else {
                        for (Interaction i : c.getInteractions()) {
                            if (colorMap.containsKey(i.getFrom().getName())) {
                                colorMap.put(i.getTo().getName(), colorMap.get(i.getFrom().getName()));
                                colorfound = true;
                            }
                            if (!colorfound) {
                                if (colorMap.containsKey(i.getTo().getName())) {
                                    colorMap.put(i.getFrom().getName(), colorMap.get(i.getTo().getName()));
                                    colorfound = true;
                                }
                            }

                        }
                        if (!colorfound) {
                            if (colorIndex < 5) {
                                colorMap.put(c.getName(), colors.get(colorIndex));
                                colorIndex++;
                            } else {
                                String newColor = generateNewColor();
                                colorMap.put(c.getName(), newColor);
                            }
                            for (Interaction i : c.getInteractions()) {
                                colorMap.put(i.getFrom().getName(), colorMap.get(c.getName()));
                                colorMap.put(i.getTo().getName(), colorMap.get(c.getName()));
                            }
                        }
                    }
                } else {
                    colorMap.put(c.getName(), "(0.0,0.0,0.0)");
                }
            }
        } 
        else {
            for (Component c : components) {
                if (Controller.isCDS(c) || Controller.isPromoter(c)) {
                    if (colorMap.containsKey(c.getName())) {
                        for(Interaction i:c.getInteractions()){
                            if(!colorMap.containsKey(i.getFrom().getName())){
                                colorMap.put(i.getFrom().getName(), colorMap.get(c.getName()));
                            } 
                            if(!colorMap.containsKey(i.getTo().getName())){
                                colorMap.put(i.getTo().getName(), colorMap.get(c.getName()));
                            }
                        }
                    } else {
                        boolean colorfound = false;
                        for (Interaction i : c.getInteractions()) {
                            if (colorMap.containsKey(i.getFrom().getName())) {
                                if(!colorMap.containsKey(i.getTo().getName())){
                                    colorMap.put(i.getTo().getName(), colorMap.get(i.getFrom().getName()));
                                    colorfound = true;
                                }
                            }
                            if (!colorfound) {
                                if (colorMap.containsKey(i.getTo().getName())) {
                                    if(!colorMap.containsKey(i.getFrom().getName())){
                                        colorMap.put(i.getFrom().getName(), colorMap.get(i.getTo().getName()));
                                        colorfound = true;
                                    }
                                }
                            }
                        }
                        if (!colorfound) {
                                String newColor = generateNewColor();
                                colorMap.put(c.getName(), newColor);
                            for (Interaction i : c.getInteractions()) {
                                if(!colorMap.containsKey(i.getFrom().getName())){
                                    colorMap.put(i.getFrom().getName(), colorMap.get(c.getName()));
                                } 
                                if(!colorMap.containsKey(i.getTo().getName())){
                                    colorMap.put(i.getTo().getName(), colorMap.get(c.getName()));
                                }
                            }
                        }
                    }    
                }
                else {
                    if(!colorMap.containsKey(c.getName())){
                        colorMap.put(c.getName(), "(0.0,0.0,0.0)");
                    }
                }
            }
            
                    
        }
        
        
        
        scr += "gs = gridspec.GridSpec(1, 1)\n\n";
        
        scr += "# Function to generate a ligher colour\n" +
               "def lighten_color (col, fac):\n" +
               "	r = col[0] + (fac*(1.0-col[0]))\n" +
               "	g = col[1] + (fac*(1.0-col[1]))\n" +
               "	b = col[2] + (fac*(1.0-col[2]))\n" +
               "	return (r,g,b)\n\n";
        
        scr += "# Global line width\n" +
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
            scr += partName + " = " + createPartString(c.getName(), labels, c.getRole(), colorMap.get(c.getName()), c.getOrientation()) + "\n";
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
            if (Controller.isCDS(c)) {
                int iCount = 0;
                for (Interaction i : c.getInteractions()) {
                    if (c.getInteractions().size() > maxArcs) {
                        maxArcs = c.getInteractions().size();
                    }
                    double arcHeight = 20.0 + (5 * iCount);
                    arcName = "reg" + arcCount++;
                    iCount++;
                    scr += arcName + " = " + createArcString(partNameMap.get(i.getFrom().getName()), partNameMap.get(i.getTo().getName()), i.getType(), arcHeight) + "\n";
                    //Add arcs to an array.
                    arcList.add(arcName);
                }
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
        if(maxArcs == 0){
            scr += "start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers())\n";
        } else {
            scr += "start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers(), \n" +
               "	                      regs=regDesign, reg_renderers=dr.std_reg_renderers())\n";
        }
        
        
        scr += "ax_dna.set_xlim([start, end])\n" +
               "ax_dna.set_ylim([-" + ylim + "," + ylim +"])\n" +
               "ax_dna.set_aspect('equal')\n" +
               "ax_dna.axis('off')\n\n";
        
        scr += "fig.savefig('" + filename + ".png', dpi=300)\n" +
               "plt.close('all')";
        return scr;
        
    }
    
    public static String generateScript(List<Component> components, boolean labels, String filename){
        return generateScript(components, labels, new HashMap<String,String>(), filename);
    }
    
    private static String createPartString(String name, boolean label, ComponentRole role, Orientation o){
         return createPartString(name,label,role,"(0.00, 0.00, 0.00)",o);
    }
    
    
    private static String createPartString(String name, boolean label, ComponentRole role, String color, Orientation o){
        String scr = "";
        scr +=  "{";
        scr += "'type':'";
        String oString = "True";
        
        String labelString = "";
        
        if(label){
            labelString += " 'label':'" + name + "', 'label_y_offset':-14, 'label_size':4, ";
        }
        
        if(o.equals(Orientation.REVERSE)){
            oString = "False";
        }
        switch(getRoleCharacter(role)){
            case 'p':
                scr += "Promoter', 'fwd':" + oString + ", 'opts':{'linewidth':lw," + labelString + " 'color':" + color + ", 'edge_color':(0.00, 0.00, 0.00)}}";
                break;
            case 'r':
                scr += "RBS', 'fwd':" + oString + ", 'opts':{'linewidth':lw," + labelString + " 'color':" + color + ", 'edge_color':(0.00, 0.00, 0.00)}}";
                break;
            case 'c':
                scr += "CDS', 'fwd':" + oString + ", 'opts':{'linewidth':lw," + labelString + " 'color':" + color + ", 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}";
                break;
            case 't':
                scr += "Terminator', 'fwd':" + oString + ", 'opts':{'linewidth':lw," + labelString + " 'color':" + color + ", 'edge_color':(0.00, 0.00, 0.00)}}";
                break;
            case 'w':
                scr += "CDS', 'fwd':" + oString + ", 'opts':{'linewidth':lw," + labelString + " 'color':" + color + ", 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}";
                System.out.println("Tester form?");
                break;
                
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
            scr += "'Activation',"; 
        else 
            scr += "'Repression',";
        
        scr += "'from_part':" + from + ", 'to_part':" + to + ", 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw";  
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
    
    public static void runScript(String filepath) throws InterruptedException, IOException{
        System.out.println("Running python script for : " + filepath);
        StringBuilder commandBuilder = null;
        commandBuilder = new StringBuilder("python " + filepath);
        String[] clist = new String[2];
        String command = commandBuilder.toString();
        clist[0] = ("cd " + Utilities.getFilepath() + "lib" + Utilities.getSeparater() + "dnaFigures" + Utilities.getSeparater() + "plots" + Utilities.getSeparater());
        clist[1] = (command);
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        proc = runtime.exec(command);
        proc.waitFor();
        System.out.println("Script completed.");
    }
    
}
