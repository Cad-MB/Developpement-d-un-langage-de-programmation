package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.ASTbreak;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.ASTcontinue;

public class NormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{
    @Override
    public IASTexpression newBreak() {
        return new ASTbreak();
    }

    @Override
    public IASTexpression newContinue() {
        return new ASTcontinue();
    }
}
