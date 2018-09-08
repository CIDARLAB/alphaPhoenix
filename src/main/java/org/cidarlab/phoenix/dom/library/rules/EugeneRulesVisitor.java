// Generated from EugeneRules.g4 by ANTLR 4.5.1

    package org.cidarlab.phoenix.dom.library.rules;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EugeneRulesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EugeneRulesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EugeneRulesParser#eugeneRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEugeneRule(EugeneRulesParser.EugeneRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link EugeneRulesParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(EugeneRulesParser.OpContext ctx);
}