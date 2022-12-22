package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp2.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTbreakLabel iast, Data data) throws Anomaly;
    Result visit(IASTcontinueLabel iast, Data data) throws Anomaly;
    Result visit(IASTloop iast, Data data) throws Anomaly;
}