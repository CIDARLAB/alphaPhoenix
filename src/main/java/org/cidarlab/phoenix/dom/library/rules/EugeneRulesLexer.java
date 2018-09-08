// Generated from EugeneRules.g4 by ANTLR 4.5.1

    package org.cidarlab.phoenix.dom.library.rules;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EugeneRulesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, VARIABLE=7, WS=8, NL=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "VARIABLE", "WS", "NL"
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


	public EugeneRulesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EugeneRules.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13S\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\5\bA\n\b\3\b\7\bD\n\b\f\b\16\bG\13\b\3\t\3\t"+
		"\3\t\3\t\3\n\5\nN\n\n\3\n\3\n\3\n\3\n\2\2\13\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\3\2\5\4\2C\\c|\6\2\62;C\\aac|\4\2\13\13\"\"T\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5\35\3\2\2\2\7%\3\2\2"+
		"\2\t,\3\2\2\2\13\63\3\2\2\2\r9\3\2\2\2\17@\3\2\2\2\21H\3\2\2\2\23M\3\2"+
		"\2\2\25\26\7P\2\2\26\27\7Q\2\2\27\30\7V\2\2\30\31\7Y\2\2\31\32\7K\2\2"+
		"\32\33\7V\2\2\33\34\7J\2\2\34\4\3\2\2\2\35\36\7p\2\2\36\37\7q\2\2\37 "+
		"\7v\2\2 !\7y\2\2!\"\7k\2\2\"#\7v\2\2#$\7j\2\2$\6\3\2\2\2%&\7D\2\2&\'\7"+
		"G\2\2\'(\7H\2\2()\7Q\2\2)*\7T\2\2*+\7G\2\2+\b\3\2\2\2,-\7d\2\2-.\7g\2"+
		"\2./\7h\2\2/\60\7q\2\2\60\61\7t\2\2\61\62\7g\2\2\62\n\3\2\2\2\63\64\7"+
		"C\2\2\64\65\7H\2\2\65\66\7V\2\2\66\67\7G\2\2\678\7T\2\28\f\3\2\2\29:\7"+
		"c\2\2:;\7h\2\2;<\7v\2\2<=\7g\2\2=>\7t\2\2>\16\3\2\2\2?A\t\2\2\2@?\3\2"+
		"\2\2AE\3\2\2\2BD\t\3\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\20\3"+
		"\2\2\2GE\3\2\2\2HI\t\4\2\2IJ\3\2\2\2JK\b\t\2\2K\22\3\2\2\2LN\7\17\2\2"+
		"ML\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\f\2\2PQ\3\2\2\2QR\b\n\2\2R\24\3\2\2"+
		"\2\7\2@CEM\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}