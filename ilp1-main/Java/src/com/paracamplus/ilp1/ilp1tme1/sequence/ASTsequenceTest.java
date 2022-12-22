package com.paracamplus.ilp1.ilp1tme1.sequence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.paracamplus.ilp1.ast.ASTboolean;
import com.paracamplus.ilp1.ast.ASTconstant;
import com.paracamplus.ilp1.ast.ASTfloat;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ASTsequenceTest {
	
	@Test
	public void test() throws EvaluationException {
		ASTboolean b1 = new ASTboolean("B1");
		ASTboolean b2 = new ASTboolean("B2");
		ASTfloat f1 = new ASTfloat("1.0");
		IASTexpression[] tab = {b1, b2, f1};
		IASTexpression[] tabAttendu = {b1, b2};
		
		ASTsequence test = new ASTsequence(tab);
		
		assertEquals(tabAttendu, test.getAllButLastInstructions());
	}
}
