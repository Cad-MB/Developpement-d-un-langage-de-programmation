package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTvisitor;

public class Normalizer extends com.paracamplus.ilp2.compiler.normalizer.Normalizer
implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {

    public Normalizer(INormalizationFactory factory) {
        super(factory);
    }

    @Override
    public IASTexpression visit(IASTbreak iast, INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory)factory).newBreak();
    }

    @Override
    public IASTexpression visit(IASTcontinue iast, INormalizationEnvironment env) throws CompilationException {
        return ((INormalizationFactory)factory).newContinue();
    }
}