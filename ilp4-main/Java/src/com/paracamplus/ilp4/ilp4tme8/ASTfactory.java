package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends  com.paracamplus.ilp4.ast.ASTfactory
implements IASTfactory {
	
	@Override
	public IASTexpression newReadPropery(String fieldName, IASTexpression target) {
		return new ASTreadProperty(fieldName, target);
	}
	
	@Override
	public IASTexpression newWritePropery(String fieldName, IASTexpression target, IASTexpression value) {
		return new ASTwriteProperty(fieldName, target, value);
	}
	
	@Override
	public IASTexpression newHasPropery(String fieldName, IASTexpression target) {
		return new ASThasProperty(fieldName, target);
	}
}
