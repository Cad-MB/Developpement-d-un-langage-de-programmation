package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class VectorGet extends BinaryPrimitive{
	public VectorGet(){
		super("vectorGet");
	}

	@Override
	public Object apply(Object vecteur, Object index) throws EvaluationException {
		if ((index instanceof BigInteger) && (vecteur.getClass().isArray())){
			Object[] tab = (Object[]) vecteur;
			return tab[((BigInteger) index).intValue()];
		} else throw new EvaluationException ("Invalid argument for vector or index, Array or int expected ");
	}

}
