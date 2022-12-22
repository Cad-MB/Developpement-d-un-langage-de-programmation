package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTwriteProperty extends IASTexpression {
	 IASTexpression getTarget();
	 String getFieldName();
	 IASTexpression getValue();
}