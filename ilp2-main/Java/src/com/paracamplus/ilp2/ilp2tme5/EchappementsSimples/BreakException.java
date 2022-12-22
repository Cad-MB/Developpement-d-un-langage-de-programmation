package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

@SuppressWarnings("serial")
public class BreakException extends EvaluationException {

    public BreakException(String msg) {
        super(msg);
    }
}
