/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.dom.library.rules;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author prash
 */
public class ERuleParser {
    
    
    public static EugeneRule parse(String rule){
        ANTLRInputStream input = new ANTLRInputStream(rule);
        EugeneRulesLexer lexer = new EugeneRulesLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EugeneRulesParser parser = new EugeneRulesParser(tokens);
        ParseTree tree = parser.eugeneRule();
        
        EugeneRule er = new EugeneRule();
        ParseTreeWalker.DEFAULT.walk(er, tree);
        
        return er;
    }
    
}
