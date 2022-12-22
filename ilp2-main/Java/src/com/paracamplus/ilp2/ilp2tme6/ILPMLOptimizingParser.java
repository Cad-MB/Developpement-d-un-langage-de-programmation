package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.parser.ilpml.ILPMLListener;
import com.paracamplus.ilp2.parser.ilpml.ILPMLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import antlr4.ILPMLgrammar2Lexer;
import antlr4.ILPMLgrammar2Parser;

public class ILPMLOptimizingParser 
extends ILPMLParser {
    
	public ILPMLOptimizingParser(IASTfactory factory) {
        super(factory);
    }

    @Override
    public IASTprogram getProgram() throws ParseException {
        try {
            ANTLRInputStream in = new ANTLRInputStream(input.getText());
            // flux de caractères -> analyseur lexical
            ILPMLgrammar2Lexer lexer = new ILPMLgrammar2Lexer(in);
            // analyseur lexical -> flux de tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // flux tokens -> analyseur syntaxique
            ILPMLgrammar2Parser parser = new ILPMLgrammar2Parser(tokens);
            // démarage de l'analyse syntaxique
            ILPMLgrammar2Parser.ProgContext tree = parser.prog();
            // parcours de l'arbre syntaxique et appels du Listener
            ParseTreeWalker walker = new ParseTreeWalker();
            ILPMLListener extractor = new ILPMLListener((IASTfactory) factory);
            walker.walk(extractor, tree);
            
            
            CopyTransform<Object> copy_transform = new CopyTransform<Object>((IASTfactory) factory);
            return (IASTprogram) copy_transform.visit(tree.node, null);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

}
