package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

@SuppressWarnings("serial")
public class ContinueException extends EvaluationException {

    public ContinueException(String msg) {
        super(msg);
    }
}
