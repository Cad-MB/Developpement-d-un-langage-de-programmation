package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTwriteProperty extends ASTexpression
implements IASTwriteProperty {
	private final String fieldName;
	private final IASTexpression target;
	private final IASTexpression value;
	
	public ASTwriteProperty (String fieldName,
          IASTexpression target,
          IASTexpression value) {
		this.fieldName = fieldName;
		this.target = target;
		this.value = value;
	}
	
	@Override
	public IASTexpression getTarget() {
		return target;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public IASTexpression getValue() {
		return value;
	}
	
	@Override
    public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
        return ((IASTvisitor<Result, Data, Anomaly>) visitor).visit(this, data);
    }
}
