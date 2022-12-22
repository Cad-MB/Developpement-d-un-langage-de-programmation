package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp2.interfaces.IASTfactory {
    IASTexpression newBreakLabel(String label);
    IASTexpression newContinueLabel(String label);
    IASTexpression newLoop(IASTexpression condition,IASTexpression body, String label);
}