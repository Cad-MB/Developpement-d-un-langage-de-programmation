package com.paracamplus.ilp3.ilp3tme7;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import antlr4.ILPMLgrammar3TME7Lexer;
import antlr4.ILPMLgrammar3TME7Parser;
import com.paracamplus.ilp3.interfaces.IASTprogram;
import com.paracamplus.ilp1.parser.ParseException;

public class ILPMLParser
extends com.paracamplus.ilp3.parser.ilpml.ILPMLParser {
	
	public ILPMLParser(IASTfactory factory) {
		super(factory);
	}
		
	@Override
    public IASTprogram getProgram() throws ParseException {
		try {
			ANTLRInputStream in = new ANTLRInputStream(input.getText());
			// flux de caractères -> analyseur lexical
			ILPMLgrammar3TME7Lexer lexer = new ILPMLgrammar3TME7Lexer(in);
			// analyseur lexical -> flux de tokens
			CommonTokenStream tokens =	new CommonTokenStream(lexer);
			// flux tokens -> analyseur syntaxique
			ILPMLgrammar3TME7Parser parser = new ILPMLgrammar3TME7Parser(tokens);
			// démarage de l'analyse syntaxique
			ILPMLgrammar3TME7Parser.ProgContext tree = parser.prog();		
			// parcours de l'arbre syntaxique et appels du Listener
			ParseTreeWalker walker = new ParseTreeWalker();
			ILPMLListener extractor = new ILPMLListener((IASTfactory)factory);
			walker.walk(extractor, tree);	
			return tree.node;
		} catch (Exception e) {
			throw new ParseException(e);
		}
    }

}
