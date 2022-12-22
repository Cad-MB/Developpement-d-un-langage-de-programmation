package com.paracamplus.ilp1.ilp1tme2.ex2;

import com.paracamplus.ilp1.interfaces.IASTfactory;

import antlr4.ILPMLgrammar1Parser.ConstFalseContext;
import antlr4.ILPMLgrammar1Parser.ConstFloatContext;
import antlr4.ILPMLgrammar1Parser.ConstIntegerContext;
import antlr4.ILPMLgrammar1Parser.ConstStringContext;
import antlr4.ILPMLgrammar1Parser.ConstTrueContext;

public class ILPMLListener extends com.paracamplus.ilp1.parser.ilpml.ILPMLListener{
	public int cpt = 0;
	public ILPMLListener(IASTfactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}
	
	@Override	public void enterConstInteger(ConstIntegerContext ctx) {cpt++;}
	@Override	public void enterConstFloat(ConstFloatContext ctx) {cpt++;}
	@Override	public void enterConstFalse(ConstFalseContext ctx) {cpt++;}
	@Override	public void enterConstTrue(ConstTrueContext ctx) {cpt++;}
	@Override	public void enterConstString(ConstStringContext ctx) {cpt++;}


}
