package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTreadProperty extends ASTexpression
implements IASTreadProperty {
	private final String fieldName;
    private final IASTexpression target;
    
    public ASTreadProperty (String fieldName, IASTexpression target) {
        this.fieldName = fieldName;
        this.target = target;
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
    public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
        return ((IASTvisitor<Result, Data, Anomaly>) visitor).visit(this, data);
    }

}
