package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode2;

import com.paracamplus.ilp1.ast.ASTalternative;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.ast.ASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfactory;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}

	@Override
	public Object visit(IASTunless iast, ILexicalEnvironment data) throws EvaluationException {
		IASTexpression condition = iast.getCondition();
		IASTexpression body = iast.getBody();
		IASTfactory factory = new ASTfactory();
		IASTalternative alternative = new ASTalternative(condition, factory.newBooleanConstant("false"), body);
		
		return visit(alternative, data); // Attention 
	}

}
