package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;


import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalInvocation;
import com.paracamplus.ilp2.compiler.interfaces.IASTCglobalFunctionVariable;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTvisitor;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable> extends IASTvisitor<Result, Data, Anomaly> 
{
    Result visit(IASTCglobalFunctionVariable iast, Data data) throws Anomaly;
    Result visit(IASTCglobalInvocation iast, Data data) throws Anomaly;
}