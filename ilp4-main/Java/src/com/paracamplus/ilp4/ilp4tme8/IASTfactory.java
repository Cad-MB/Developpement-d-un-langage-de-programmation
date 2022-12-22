package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
	IASTexpression newReadPropery(String fieldName, IASTexpression target);
	IASTexpression newWritePropery(String fieldName, IASTexpression target, IASTexpression value);
	IASTexpression newHasPropery(String fieldName, IASTexpression target);
}
