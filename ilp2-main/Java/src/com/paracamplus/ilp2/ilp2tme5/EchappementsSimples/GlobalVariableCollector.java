package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTCvisitor;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTcontinue;

import java.util.Set;

public class GlobalVariableCollector extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTCvisitor<Set<IASTCglobalVariable>,
        Set<IASTCglobalVariable>,
        CompilationException> {
    @Override
    public Set<IASTCglobalVariable> visit(IASTbreak iast, Set<IASTCglobalVariable> result) throws CompilationException {
        return result;
    }

    @Override
    public Set<IASTCglobalVariable> visit(IASTcontinue iast, Set<IASTCglobalVariable> result) throws CompilationException {
        return result;
    }
}