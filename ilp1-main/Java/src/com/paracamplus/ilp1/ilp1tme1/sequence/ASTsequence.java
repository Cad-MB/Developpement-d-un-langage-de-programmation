package com.paracamplus.ilp1.ilp1tme1.sequence;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ASTsequence extends com.paracamplus.ilp1.ast.ASTsequence implements com.paracamplus.ilp1.ilp1tme1.sequence.IASTsequence {

	public ASTsequence(IASTexpression[] expressions) {
		super(expressions);
	}

	
	public IASTexpression[] getAllButLastInstructions() throws EvaluationException {
		IASTexpression[] recup = this.getExpressions();
		IASTexpression[] res = new IASTexpression[recup.length-1];
		for(int i = 0; i < recup.length-1; ++i)
			res[i] = recup[i];
		
		return res;
	}
}
