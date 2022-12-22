package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;

public class GlobalVariableCollector extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTCvisitor<Set<IASTCglobalVariable>, 
		Set<IASTCglobalVariable>, 
		CompilationException>{

	@Override
	public Set<IASTCglobalVariable> visit(IASTunless iast, Set<IASTCglobalVariable> result) throws CompilationException {
        result = iast.getCondition().accept(this, result);
        result = iast.getBody().accept(this, result);
        return result;
    }
}
