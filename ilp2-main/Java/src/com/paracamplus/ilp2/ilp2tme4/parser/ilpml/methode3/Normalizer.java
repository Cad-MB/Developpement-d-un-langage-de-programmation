package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public class Normalizer extends com.paracamplus.ilp2.compiler.normalizer.Normalizer 
implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {
	public Normalizer(INormalizationFactory factory) {
		super(factory);
	}

	@Override
	public IASTexpression visit(IASTunless iast, INormalizationEnvironment env) throws CompilationException {
        IASTexpression c = iast.getCondition().accept(this, env);
        IASTexpression b = iast.getBody().accept(this, env);
        return ((INormalizationFactory)factory).newUnless(c, b);
        }
    }
	
