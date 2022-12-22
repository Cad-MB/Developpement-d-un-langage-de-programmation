package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    @Override
    public IASTexpression newBreak() {
        return new ASTbreak();
    }

    @Override
    public IASTexpression newContinue() {
        return new ASTcontinue();
    }
}