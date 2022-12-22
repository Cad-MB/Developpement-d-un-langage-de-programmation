package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;

import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTCvisitor;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.EchappementsSimples.IASTcontinue;

import java.util.Set;

public class FreeVariableCollector extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTCvisitor<Void, Set<IASTClocalVariable>, CompilationException> {
    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }

    @Override
    public Void visit(IASTbreak iast, Set<IASTClocalVariable> variables) throws CompilationException {
        return null;
    }

    @Override
    public Void visit(IASTcontinue iast, Set<IASTClocalVariable> variables) throws CompilationException {
        return null;
    }
}