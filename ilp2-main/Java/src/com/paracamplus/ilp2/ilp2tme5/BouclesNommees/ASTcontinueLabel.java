package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.ast.ASTnamed;
import com.paracamplus.ilp1.interfaces.IASTvisitor;

public class ASTcontinueLabel extends ASTnamed implements IASTcontinueLabel {
    public ASTcontinueLabel(String label) {
		super(label);
	}
    
    public String getLabel() {
    	return this.getName();
    }

	@Override
    public <Result, Data, Anomaly extends Throwable> Result accept(IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
        return ((com.paracamplus.ilp2.ilp2tme5.BouclesNommees.IASTvisitor<Result, Data, Anomaly>)visitor).visit(this,data);
    }
}