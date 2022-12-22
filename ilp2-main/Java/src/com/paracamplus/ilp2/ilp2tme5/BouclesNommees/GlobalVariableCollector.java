package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;

import java.util.Set;

public class GlobalVariableCollector extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTCvisitor<Set<IASTCglobalVariable>,
        Set<IASTCglobalVariable>,
        CompilationException> {

	@Override
	public Set<IASTCglobalVariable> visit(IASTbreakLabel iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTcontinueLabel iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTloop iast, Set<IASTCglobalVariable> data) throws CompilationException {
		result = iast.getCondition().accept(this, result);
        result = iast.getBody().accept(this, result);
        return result;
	}
}