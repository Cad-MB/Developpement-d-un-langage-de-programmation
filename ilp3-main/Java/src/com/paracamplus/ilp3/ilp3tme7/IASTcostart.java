package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTcostart extends IASTexpression {
	IASTexpression getFunction();
	IASTexpression[] getArguments();	
}
