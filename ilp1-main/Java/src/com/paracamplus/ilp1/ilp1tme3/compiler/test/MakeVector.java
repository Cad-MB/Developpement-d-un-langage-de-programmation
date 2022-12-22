package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class MakeVector extends BinaryPrimitive{
	public MakeVector() {
		super("makeVector");
	}

	@Override
	public Object apply(Object taille, Object valeur) throws EvaluationException {
		if (taille instanceof BigInteger){
			if (valeur instanceof Object){ // Utile? 
				Object[] tab = new Object[((BigInteger) taille).intValue()];
				for (int i=0; i<((BigInteger) taille).intValue(); ++i)
					tab[i] = valeur;
				return tab;
			} else throw new EvaluationException ("Invalid argument for valeur, not an Object");
		} else throw new EvaluationException ("Invalid argument for taille, int expected ");

	}
}

