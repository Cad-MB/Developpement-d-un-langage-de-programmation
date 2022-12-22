package com.paracamplus.ilp1.ilp1tme2.ex2;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASToperator;
import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperator;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;


public class CountConstants implements com.paracamplus.ilp1.interfaces.IASTvisitor<Integer, Void, Exception>{
	public int cpt = 0;
	
	public CountConstants(IASTprogram tree) throws Exception {
		cpt = tree.getBody().accept(this, null);
	}

	@Override
	public Integer visit(IASTalternative iast, Void data) throws Exception {
		if (iast.isTernary()){
			return iast.getCondition().accept(this, null) + iast.getConsequence().accept(this, null) + iast.getAlternant().accept(this, null);
		}
		return iast.getCondition().accept(this, null) + iast.getConsequence().accept(this, null);

	}

	@Override
	public Integer visit(IASTbinaryOperation iast, Void data) throws Exception {
		return iast.getLeftOperand().accept(this, data) + iast.getRightOperand().accept(this, data);
	}

	@Override
	public Integer visit(IASTblock iast, Void data) throws Exception {
	        int val = 0;
			for ( IASTbinding binding : iast.getBindings() ) {
	            val += binding.getInitialisation().accept(this, data);
	        }
	        return val + iast.getBody().accept(this, data);
	}

	@Override
	public Integer visit(IASTboolean iast, Void data) throws Exception {
		return 1;
	}

	@Override
	public Integer visit(IASTfloat iast, Void data) throws Exception {
		return 1;
	}

	@Override
	public Integer visit(IASTinteger iast, Void data) throws Exception {
		return 1;
	}

	@Override
	public Integer visit(IASTinvocation iast, Void data) throws Exception {
		 int val = 0;
		 val = iast.getFunction().accept(this, data);
        for ( IASTexpression arg : iast.getArguments() ) {
        	val += arg.accept(this, data);
        }
		return val; 
	}

	@Override
	public Integer visit(IASTsequence iast, Void data) throws Exception {
		IASTexpression[] expressions = iast.getExpressions();
        int val = 0;
		for ( IASTexpression e : expressions ) {
            val += e.accept(this, data);
        }
        
        return val;
        
	}

	@Override
	public Integer visit(IASTstring iast, Void data) throws Exception {
		return 1;
	}

	@Override
	public Integer visit(IASTunaryOperation iast, Void data) throws Exception {
		return  iast.getOperand().accept(this, data);
	}

	@Override
	public Integer visit(IASTvariable iast, Void data) throws Exception {
		return 0;
	}
    
   
    
   
}
