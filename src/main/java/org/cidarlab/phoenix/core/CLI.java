/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core;

import hyness.stl.TreeNode;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.cidarlab.gridtli.dom.Grid;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.gridtli.tli.TemporalLogicInference;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.cidarlab.phoenix.core.PhoenixProject.Simulation;
import org.cidarlab.phoenix.utils.Utilities;

/**
 *
 * @author prash
 */
public class CLI {
    
    public static void main(String[] args) throws TLIException {
        if(args.length == 0){
            System.out.println("Please specify a command.");
            System.out.println("To view options or usage try: java -jar phoenix.jar --help");
        } else {
            String mode = args[0];
            switch(mode){
                case "--help":
                    if(args.length > 2){
                        wrongFormatMessage("Unrecognized format.");
                        System.exit(-1);
                    }
                    
                    String tab = "  ";
                    
                    if(args.length == 2){
                        String helpswitch = args[1];
                        switch(helpswitch){
                            case "-gridtli":
                                System.out.println("Usage to run GridTLI:");
                                System.out.println("java -jar phoenix.jar --gridtli [parameter set]");
                                System.out.println("Grid TLI can take 1 or more parameter sets");
                                System.out.println("Each parameter must include the following 4 options");
                                System.out.println(tab + "-x                Signal threshold value. You must enter a double after this option.");
                                System.out.println(tab + "-t                Temporal threshold value. You must enter a double after this option.");
                                System.out.println(tab + "-c                Cluster threshold value. You must enter a double after this option.");
                                System.out.println(tab + "-file             Filepath. You must enter a filepath for a csv.");
                                System.out.println(tab + "-outputDir        Filepath. You must enter a folder path where the output txt file will be stored.");
                                break;
                            case "-phoenix":
                                System.out.println("Usage to run stochastic simulations:");
                                System.out.println("java -jar phoenix.jar --phoenix --run -stochastic -confidence <confidence value> -threshold <threshold value> -runCount <Stochastic simulation count> -circSize <Size of the circuit produced by Eugene> -eugeneSolutions <Number of solutions to be produced by Eugene> -structure <Filepath of the Eugene file> -library <JSON file containing the information regarding the repository> -performance <Filepath of the STL file>");
                                System.out.println("");
                                System.out.println("Usage to run deterministic simulations:");
                                System.out.println("java -jar phoenix.jar --phoenix --run -deterministic -circSize <Size of the circuit produced by Eugene> -eugeneSolutions <Number of solutions to be produced by Eugene> -structure <Filepath of the Eugene file> -library <JSON file containing the information regarding the repository> -performance <Filepath of the STL file>");
                                System.out.println("");
                                System.out.println("NOTE : You can use the -noplots option to prevent creating plots created by gnuplot.");
                                break;
                            default:
                                wrongFormatMessage("Unrecognized format.");
                                System.exit(-1);
                                break;
                        }
                    } else{
                        System.out.println("Usage : java -jar phoenix.jar [--modes] [--submodes] [-options]");
                        System.out.println("where modes include");
                        System.out.println(tab + "--help            Shows options and usage");
                        System.out.println(tab + "--gridtli         Lets you generate an STL formula from traces");
                        System.out.println(tab + "--phoenix         Runs the current workflow");
                        System.out.println("");
                        System.out.println("options for --help include");
                        System.out.println(tab + "-gridtli          Shows the usage for grid TLI");
                        System.out.println(tab + "-phoenix          Shows the usage for the current workflow");
                        System.out.println("");
                        System.out.println("options for --gridtli include");
                        System.out.println(tab + "-x                Signal threshold value. You must enter a double after this option.");
                        System.out.println(tab + "-t                Temporal threshold value. You must enter a double after this option.");
                        System.out.println(tab + "-c                Cluster threshold value. You must enter a double after this option.");
                        System.out.println(tab + "-file             Filepath. You must enter a filepath for a csv.");
                        System.out.println(tab + "-outputDir        Filepath. You must enter a folder path where the output txt file will be stored.");
                        System.out.println("");
                        System.out.println("submodes for --phoenix include");
                        System.out.println(tab + "--run             This will run the current workflow. Other submodes are currently not supported.");
                        System.out.println("");
                        System.out.println("options for --phoenix --run include");
                        System.out.println(tab + "-stochastic       Runs stochastic simulations. ");
                        System.out.println(tab + "-confidence       Confidence value for Statistical model checking. You must enter a double after this option.");
                        System.out.println(tab + "-runCount         Number of stochastic simulations to be executed. You must enter a double after this option.");
                        System.out.println(tab + "-threshold        Threshold for assignments. You must enter a double after this option.");
                        System.out.println(tab + "-deterministic    Runs deterministic simulations.");
                        System.out.println(tab + "-circSize         Threshold for assignments. You must enter an integer after this option.");
                        System.out.println(tab + "-eugeneSolutions  Number of expected solutions in Eugene. You must enter an integer after this option.");
                        System.out.println(tab + "-structure        Structural specification. You must enter a filepath to a .eug file after this option.");
                        System.out.println(tab + "-performance      Performance specification. You must enter a filepath to a .txt file (containing an STL) after this option.");
                        System.out.println(tab + "-library          Repository details. You must enter a filepath to a .json file after this option.");
                        System.out.println(tab + "-noplots          Does not create plots.");
                        
                    }
                    break;
                case "--gridtli":
                    //<editor-fold desc="gridtli">
                    int paramSize = args.length-3;
                    if(paramSize%8 == 0){
                        String outSwitch = args[args.length-2];
                        if(outSwitch.equals("-outputDir")){
                            String outputDir = args[args.length-1];
                            if(Utilities.validFilepath(outputDir)){
                                String endCar = "" + Utilities.getSeparater();
                                if(!outputDir.endsWith(endCar)){
                                    outputDir += Utilities.getSeparater();
                                }
                                List<TreeNode> stlNodeList = new ArrayList<TreeNode>();
                                for(int i=1;i<args.length-2;i+=8){
                                    double xt = 0;
                                    double tt = 0;
                                    double ct = 0;
                                    Set<String> variables = new HashSet<String>();
                                    String gridtlifp = "";
                                    for(int j=i;j<(i+8);j+=2){
                                        String key = args[j];
                                        if(variables.contains(key)) {
                                            wrongFormatMessage("Unrecognized format. To read details about GridTLI, try: java -jar phoenix.jar --help -gridtli");
                                            System.exit(-1);
                                        } else {
                                            switch (key) {
                                                case "-x":
                                                    xt = Double.valueOf(args[j + 1]);
                                                    variables.add(key);
                                                    break;
                                                case "-t":
                                                    tt = Double.valueOf(args[j + 1]);
                                                    variables.add(key);
                                                    break;
                                                case "-c":
                                                    ct = Double.valueOf(args[j + 1]);
                                                    variables.add(key);
                                                    break;
                                                case "-file":
                                                    gridtlifp = args[j + 1];
                                                    variables.add(key);
                                                    break;
                                                default:
                                                    wrongFormatMessage("Unrecognized format. To read details about GridTLI, try: java -jar phoenix.jar --help -gridtli");
                                                    break;
                                            }
                                        }
                                        
                                    }
                                    List<Signal> signals = STLAdaptor.getSignalsFromFile(gridtlifp);
                                    Grid g = new Grid(signals,tt,xt);
                                    TreeNode stl = TemporalLogicInference.getSTL(g, ct);
                                    stlNodeList.add(stl);
                                }
                                TreeNode conjstl = TemporalLogicInference.reduceToSingleConjunction(stlNodeList);
                                String outputstl = outputDir + "stl.txt";
                                File outputstlfile = new File(outputstl);
                                Utilities.writeToFile(outputstl, conjstl.toString());
                                System.out.println("STL saved in " + outputstlfile.getAbsolutePath());
                                
                            } else {
                                System.out.println("Output Directory " + outputDir + " does not exist.");
                                System.exit(-1);
                            }
                        } else {
                            wrongFormatMessage("The output directory switch must always be the last switch. To read details about GridTLI, try: java -jar phoenix.jar --help -gridtli");
                            System.exit(-1);
                        }
                    } else {
                        wrongFormatMessage("Unrecognized mode. To read details about GridTLI, try: java -jar phoenix.jar --help -gridtli");
                    }
                    break;
                    //</editor-fold>
                case "--phoenix":
                    //<editor-fold desc="phoenix">
                    if(args.length < 3){
                        wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                        System.exit(-1); 
                    } else {
                        String submode = args[1];
                        switch(submode){
                            case "--run":
                                Set<String> runkeys = new HashSet<String>();
                                Set<String> runopts = new HashSet<String>();
                                Map<String,Double> runInMap = new HashMap<String,Double>();
                                boolean runplots = true;
                                String runeug = null;
                                String runstl = null;
                                String runlib = null;
                                double confidence = 0;
                                double threshold = 0;
                                int runCount = 0;
                                int eugCircSize = 0;
                                Integer eugNumSolutions = null;
                                Simulation runsim = Simulation.DETERMINISTIC;
                                for(int i=2;i<args.length;i++){
                                    if(args[i].equals("-noplots")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        runplots = false;
                                    } 
                                    else if(args[i].equals("-deterministic")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if(runkeys.contains("simulation")){
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runkeys.add("simulation");
                                        runsim = Simulation.DETERMINISTIC;
                                    } 
                                    else if(args[i].equals("-stochastic")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if(runkeys.contains("simulation")){
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runkeys.add("simulation");
                                        runsim = Simulation.STOCHASTIC;
                                    }
                                    else if(args[i].equals("-structure")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        runkeys.add("structure");
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            runeug = args[i+1];
                                            i++;
                                        }
                                    } else if(args[i].equals("-circSize")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        runkeys.add("circsize");
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            eugCircSize = Integer.valueOf(args[i+1]);
                                            i++;
                                        }
                                    } else if(args[i].equals("-eugeneSolutions")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            eugNumSolutions = Integer.valueOf(args[i+1]);
                                            i++;
                                        }
                                    } 
                                    else if(args[i].equals("-performance")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        runkeys.add("performance");
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            runstl = args[i+1];
                                            i++;
                                        }
                                    }
                                    else if(args[i].equals("-library")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        runkeys.add("library");
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            runlib = args[i+1];
                                            i++;
                                        }
                                    } else if(args[i].equals("-confidence")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            confidence = Double.valueOf(args[i+1]);
                                            i++;
                                        }
                                    } else if(args[i].equals("-threshold")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            threshold = Double.valueOf(args[i+1]);
                                            i++;
                                        }
                                    } else if(args[i].equals("-runCount")){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            runCount = Integer.valueOf(args[i+1]);
                                            i++;
                                        }
                                    } else if(validInputValue(args[i])){
                                        if (runopts.contains(args[i])) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        }
                                        runopts.add(args[i]);
                                        if (i == args.length - 1) {
                                            wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                            System.exit(-1);
                                        } else{
                                            String runInStr = args[i].substring(1);
                                            double runInVal = Integer.valueOf(args[i+1]);
                                            runInMap.put(runInStr, runInVal);
                                            i++;
                                        }
                                    }
                                }
                                if((!runkeys.contains("library")) || (!runkeys.contains("simulation")) || (!runkeys.contains("performance")) || (!runkeys.contains("circsize"))|| (!runkeys.contains("structure"))){
                                    wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                    System.exit(-1);
                                }
                                if(runopts.contains("-stochastic")){
                                    if(!runopts.contains("-confidence")){
                                        wrongFormatMessage("Stochastic simulations require a confidence value. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                        System.exit(-1);
                                    }
                                    if(!runopts.contains("-threshold")){
                                        wrongFormatMessage("Stochastic simulations require a threshold value. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                        System.exit(-1);
                                    }
                                    if(!runopts.contains("-runCount")){
                                        wrongFormatMessage("Stochastic simulations require a run Count. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                        System.exit(-1);
                                    }
                                } else {
                                    System.out.println("Deterministic");
                                    if(runopts.contains("-confidence") || runopts.contains("-threshold") || runopts.contains("-runCount")){
                                        wrongFormatMessage("Deterministic simulations cannot have confidence, threshold or runCount values. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                        System.exit(-1);
                                    }
                                }
                                //String eugfp, int eugCircSize, Integer eugNumSolutions, String stlfp,String libraryfp, Simulation simulation, boolean plot
                                PhoenixProject proj = new PhoenixProject(runeug,eugCircSize,eugNumSolutions,runstl,runlib,runsim,runCount,confidence,threshold,runInMap,runplots);
                                break;
                            case "--override":
                                break;
                            case "--rerun":
                                break;
                            default:
                                wrongFormatMessage("Unrecognized format. To read details about options to use Phoenix, try: java -jar phoenix.jar --help -phoenix");
                                System.exit(-1);
                                break;
                        }
                    }
                    break;
                    //</editor-fold>
                default:
                    wrongFormatMessage("Unrecognized mode.");
                    break;
            }
        }
    }
    
    
    private static boolean validInputValue(String arg){
        if(arg.startsWith("-in")){
            String dig = arg.substring(3);
            if(isDigit(dig)){
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean isDigit(String str){
        
        if(str.isEmpty()){
            return false;
        }
        for(char c:str.toCharArray()){
            switch(c){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    return false;
            }
        }
        
        return true;
    }
    
    public static void wrongFormatMessage(String firstmessage){
        System.out.println(firstmessage);
        System.out.println("To view all options/usage try: java -jar phoenix.jar --help");
    }
    
}
