// Generated from EugeneRules.g4 by ANTLR 4.5.1

    package org.cidarlab.phoenix.dom.library.rules;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EugeneRulesParser}.
 */
public interface EugeneRulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EugeneRulesParser#eugeneRule}.
	 * @param ctx the parse tree
	 */
	void enterEugeneRule(EugeneRulesParser.EugeneRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link EugeneRulesParser#eugeneRule}.
	 * @param ctx the parse tree
	 */
	void exitEugeneRule(EugeneRulesParser.EugeneRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link EugeneRulesParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(EugeneRulesParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link EugeneRulesParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(EugeneRulesParser.OpContext ctx);
}