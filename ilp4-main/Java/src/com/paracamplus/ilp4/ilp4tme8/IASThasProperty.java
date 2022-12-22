package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASThasProperty extends IASTexpression{
	IASTexpression getTarget();
    String getFieldName();
}
