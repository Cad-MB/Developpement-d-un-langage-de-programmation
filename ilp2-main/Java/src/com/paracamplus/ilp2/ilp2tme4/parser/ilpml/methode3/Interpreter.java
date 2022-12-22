package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;


public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

	private static Object rien = "rien";
	@Override
	public Object visit(IASTunless iast, ILexicalEnvironment data) throws EvaluationException {
		Object c = iast.getCondition().accept(this, data);
		if ((c != null ) && c instanceof Boolean) {
			Boolean b = (Boolean) c;
			if (b.booleanValue()) {
				return rien;
			} else {
				return iast.getBody().accept(this, data);
			}	
		} else {
			return iast.getBody().accept(this, data);
		}
	}
}