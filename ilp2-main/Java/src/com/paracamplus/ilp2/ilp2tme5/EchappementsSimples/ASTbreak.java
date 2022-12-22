package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interfaces.IASTvisitor;

public class ASTbreak implements IASTbreak {
    @Override
    public <Result, Data, Anomaly extends Throwable> Result accept(IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
        return ((com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTvisitor<Result, Data, Anomaly>)visitor).visit(this,data);
    }
}