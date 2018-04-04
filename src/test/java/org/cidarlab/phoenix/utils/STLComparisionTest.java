/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.TreeNode;
import hyness.stl.grammar.STLAbstractSyntaxTreeExtractor;
import hyness.stl.grammar.STLLexer;
import hyness.stl.grammar.STLParser;
import java.io.IOException;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.cidarlab.phoenix.adaptors.STLAdaptor;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class STLComparisionTest {
    
    
    private static String constitutive = "(((G[0.0,300.0](x <= 41.5206640959)) && (G[0.0,240.0](x >= 21.5206640959))) && ((G[240.0,260.0](x >= 1.520664095899999)) && (G[260.0,300.0](x >= 21.5206640959))))";
    
    private static String induction = "((((((G[0.0,160.0](x <= 20.0)) && (G[160.0,200.0](x <= 40.0))) && ((G[200.0,300.0](x <= 20.0)) && (G[0.0,220.0](x >= 0.0)))) && ((G[220.0,260.0](x >= -20.0)) && (G[260.0,300.0](x >= 0.0)))) || (((((G[0.0,20.0](x <= 20.0)) && (G[20.0,80.0](x <= 40.0))) && ((G[80.0,140.0](x <= 60.0)) && (G[140.0,160.0](x <= 80.0)))) && (((G[160.0,180.0](x <= 100.0)) && (G[180.0,220.0](x <= 120.0))) && ((G[220.0,280.0](x <= 140.0)) && (G[280.0,300.0](x <= 120.0))))) && ((((G[0.0,40.0](x >= 0.0)) && (G[40.0,100.0](x >= 20.0))) && ((G[100.0,160.0](x >= 40.0)) && (G[160.0,180.0](x >= 60.0)))) && (((G[180.0,200.0](x >= 80.0)) && (G[200.0,240.0](x >= 100.0))) && ((G[240.0,260.0](x >= 120.0)) && (G[260.0,300.0](x >= 100.0))))))) || ((((((G[0.0,20.0](x <= 40.0)) && (G[20.0,40.0](x <= 80.0))) && ((G[40.0,60.0](x <= 100.0)) && (G[60.0,80.0](x <= 140.0)))) && (((G[80.0,100.0](x <= 160.0)) && (G[100.0,120.0](x <= 180.0))) && ((G[120.0,140.0](x <= 220.0)) && (G[140.0,160.0](x <= 260.0))))) && ((((G[160.0,180.0](x <= 280.0)) && (G[180.0,220.0](x <= 300.0))) && ((G[220.0,260.0](x <= 320.0)) && (G[260.0,280.0](x <= 280.0)))) && (((G[280.0,300.0](x <= 260.0)) && (G[0.0,20.0](x >= 0.0))) && ((G[20.0,40.0](x >= 20.0)) && (G[40.0,60.0](x >= 40.0)))))) && ((((G[60.0,120.0](x >= 60.0)) && (G[120.0,140.0](x >= 80.0))) && ((G[140.0,160.0](x >= 100.0)) && (G[160.0,180.0](x >= 140.0)))) && (((G[180.0,200.0](x >= 160.0)) && (G[200.0,280.0](x >= 180.0))) && (G[280.0,300.0](x >= 160.0))))))";
            
    private static String desired = "((G[0,300](x<40) && G[0,300](x>0))|| (G[0,125](x<200) && G[125,300](x<320) && G[0,150](x>0) && G[150,200](x>100) && G[200,300](x>150)))";
    
    private static String desiredlow = "(G[0,300](x<40) && G[0,300](x>0))";
            
    private static String desiredhigh = "(G[0,125](x<200) && G[125,300](x<320) && G[0,150](x>0) && G[150,200](x>100) && G[200,300](x>150))";
    
    private static final String rootFolder = Utilities.getFilepath() + Utilities.getSeparater() + "lib" + Utilities.getSeparater() + "examples" + Utilities.getSeparater() + "stlComparision" + Utilities.getSeparater();
    
    @Test
    public void testSMC() throws TLIException, IOException{
        TreeNode constitutiveSTL = getSTL(constitutive);
        TreeNode inductionSTL = getSTL(induction);
        TreeNode desiredSTL = getSTL(desired);
        TreeNode desiredlowSTL = getSTL(desiredlow);
        TreeNode desiredhighSTL = getSTL(desiredhigh);
        
        String inductionfp = rootFolder + "induction.csv";
        String constitutivefp = rootFolder + "constitutive.csv";
        List<Signal> constitutiveSignals = org.cidarlab.gridtli.tli.Utilities.getSignals(constitutivefp, false, true);
        List<Signal> inductionSignals = org.cidarlab.gridtli.tli.Utilities.getSignals(inductionfp, false, true);
        
        System.out.println("SMC for Constitutive Circuit :: " + getSMC(desiredlowSTL,constitutiveSignals));
        System.out.println("SMC for Induction Circuit Low :: " + getSMC(desiredlowSTL,inductionSignals));
        System.out.println("SMC for Induction Circuit High :: " + getSMC(desiredhighSTL,inductionSignals));
        System.out.println("SMC for Induction Circuit Desired :: " + getSMC(desiredSTL,inductionSignals));
        
        
    }
    
    public double getSMC(TreeNode node, List<Signal> signals) throws IOException{
        double val = 0.0;
        double satper = STLAdaptor.computeSatisfyingPercent(signals, node);
        double err = STLAdaptor.computeError(satper, signals.size(), 0.95);
        System.out.println("Error:" + err);
        return satper;
    }
            
    public TreeNode getSTL(String stlString){
        STLLexer lexer = new STLLexer(new ANTLRInputStream(stlString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        STLParser parser = new STLParser(tokens);
        ParserRuleContext t = parser.property();
        return new STLAbstractSyntaxTreeExtractor().visit(t);
    }
    
    
}
