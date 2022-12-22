package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

@SuppressWarnings("serial")
public class ContinueLabelException extends EvaluationException {
	protected final String label;
	
    public ContinueLabelException(String label) {
        super(label);
        this.label = label;
    }
    
    public String getLabel() {
    	return label;
    }
}