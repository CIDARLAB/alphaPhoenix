// Generated from EugeneRules.g4 by ANTLR 4.5.1

    package org.cidarlab.phoenix.dom.library.rules;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EugeneRulesParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, VARIABLE=7, WS=8, NL=9;
	public static final int
		RULE_eugeneRule = 0, RULE_op = 1;
	public static final String[] ruleNames = {
		"eugeneRule", "op"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'NOTWITH'", "'notwith'", "'BEFORE'", "'before'", "'AFTER'", "'after'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "VARIABLE", "WS", "NL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EugeneRules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EugeneRulesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class EugeneRuleContext extends ParserRuleContext {
		public Token left;
		public OpContext erule;
		public Token right;
		public List<TerminalNode> VARIABLE() { return getTokens(EugeneRulesParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(EugeneRulesParser.VARIABLE, i);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public EugeneRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eugeneRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EugeneRulesListener ) ((EugeneRulesListener)listener).enterEugeneRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EugeneRulesListener ) ((EugeneRulesListener)listener).exitEugeneRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EugeneRulesVisitor ) return ((EugeneRulesVisitor<? extends T>)visitor).visitEugeneRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EugeneRuleContext eugeneRule() throws RecognitionException {
		EugeneRuleContext _localctx = new EugeneRuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_eugeneRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			((EugeneRuleContext)_localctx).left = match(VARIABLE);
			setState(5);
			((EugeneRuleContext)_localctx).erule = op();
			setState(6);
			((EugeneRuleContext)_localctx).right = match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EugeneRulesListener ) ((EugeneRulesListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EugeneRulesListener ) ((EugeneRulesListener)listener).exitOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EugeneRulesVisitor ) return ((EugeneRulesVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13\r\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\2\3\2\3\3\3\3\3\3\2\2\4\2\4\2\3\3\2\3\b\n\2\6\3\2\2\2\4"+
		"\n\3\2\2\2\6\7\7\t\2\2\7\b\5\4\3\2\b\t\7\t\2\2\t\3\3\2\2\2\n\13\t\2\2"+
		"\2\13\5\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}