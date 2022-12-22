package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp3.ast.ASTfactory implements IASTfactory{

	@Override
	public IASTcostart newCostart(IASTexpression function, IASTexpression[] arguments) {
		return new ASTcostart(function, arguments);
	}

}
