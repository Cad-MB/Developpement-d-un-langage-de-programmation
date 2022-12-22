package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp3.interfaces.IASTprogram;

public interface IASTfactory extends com.paracamplus.ilp3.interfaces.IASTfactory  {
	IASTcostart newCostart(IASTexpression function, IASTexpression[] arguments);
}