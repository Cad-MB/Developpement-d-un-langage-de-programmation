package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface INormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory {
    IASTexpression newBreak();
    IASTexpression newContinue();
}