package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class VectorLength extends UnaryPrimitive{
	public VectorLength() {
		super("vectorLength");
	}

	@Override
	public Object apply(Object tableau) throws EvaluationException {
		if (tableau.getClass().isArray()) {
			Object[] tab = (Object[]) tableau;
			return tab.length;
		}
		else throw new EvaluationException ("Invalid argument, array expected");
	}
}
