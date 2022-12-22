package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;


import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalInvocation;
import com.paracamplus.ilp2.compiler.interfaces.IASTCglobalFunctionVariable;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable> extends IASTvisitor<Result, Data, Anomaly> 
{
    Result visit(IASTCglobalFunctionVariable iast, Data data) throws Anomaly;
    Result visit(IASTCglobalInvocation iast, Data data) throws Anomaly;
}