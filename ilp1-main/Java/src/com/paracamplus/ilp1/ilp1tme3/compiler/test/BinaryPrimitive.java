package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import java.util.Vector;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;

public abstract class BinaryPrimitive extends Primitive{
	public BinaryPrimitive(String name) {
		super(name);
	}
	
	@Override
	public int getArity() {
		return 2;
	}
	
	@Override
	public abstract Object apply(Object arg1, Object arg2) throws EvaluationException; 
}
