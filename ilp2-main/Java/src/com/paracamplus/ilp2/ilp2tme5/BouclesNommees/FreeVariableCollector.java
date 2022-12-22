package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;

import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;

import java.util.Set;

public class FreeVariableCollector extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTCvisitor<Void, Set<IASTClocalVariable>, CompilationException> {
    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }

	@Override
	public Void visit(IASTbreakLabel iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}

	@Override
	public Void visit(IASTcontinueLabel iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}

	@Override
	public Void visit(IASTloop iast, Set<IASTClocalVariable> data) throws CompilationException {
		iast.getCondition().accept(this, data);
        iast.getBody().accept(this, data);
		return null;
	}
}