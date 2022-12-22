package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class NormalizationFactory extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{
	  @Override
		public IASTexpression newUnless(IASTexpression condition, IASTexpression body){
	        return new ASTunless(condition, body);
	    }

}
