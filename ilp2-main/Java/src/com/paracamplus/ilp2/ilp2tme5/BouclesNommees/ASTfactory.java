package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
    @Override
    public IASTexpression newBreakLabel(String label) {
        return new ASTbreakLabel(label);
    }

    @Override
    public IASTexpression newContinueLabel(String label) {
        return new ASTcontinueLabel(label);
    }

	@Override
	public IASTexpression newLoop(IASTexpression condition, IASTexpression body, String label) {
		return new ASTloop(condition, body, label);
	}
}