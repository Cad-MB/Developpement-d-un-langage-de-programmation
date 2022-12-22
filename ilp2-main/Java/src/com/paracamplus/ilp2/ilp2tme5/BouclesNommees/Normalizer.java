package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class Normalizer extends com.paracamplus.ilp2.compiler.normalizer.Normalizer
implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {

    public Normalizer(INormalizationFactory factory) {
        super(factory);
    }

	@Override
	public IASTexpression visit(IASTbreakLabel iast, INormalizationEnvironment data) throws CompilationException {
		return ((INormalizationFactory)factory).newBreakLabel(iast.getLabel());
	}

	@Override
	public IASTexpression visit(IASTcontinueLabel iast, INormalizationEnvironment data) throws CompilationException {
		return ((INormalizationFactory)factory).newContinueLabel(iast.getLabel());
	}

	@Override
	public IASTexpression visit(IASTloop iast, INormalizationEnvironment data) throws CompilationException {
		IASTexpression newcondition = iast.getCondition().accept(this, data);
        IASTexpression newbody = iast.getBody().accept(this, data);
        return ((INormalizationFactory)factory).newLoop(newcondition, newbody, iast.getLabel());
	}
}