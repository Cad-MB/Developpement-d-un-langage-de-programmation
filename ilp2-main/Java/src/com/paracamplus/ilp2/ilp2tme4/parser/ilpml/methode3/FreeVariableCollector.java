package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;

public class FreeVariableCollector extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTCvisitor<Void, Set<IASTClocalVariable>, CompilationException>{
	public FreeVariableCollector(IASTCprogram program) {
		super(program);
	}
	
	   @Override
	   public Void visit(IASTunless iast, Set<IASTClocalVariable> variables) throws CompilationException {
           iast.getCondition().accept(this, variables);
           iast.getBody().accept(this, variables);
           return null;
       }

}
