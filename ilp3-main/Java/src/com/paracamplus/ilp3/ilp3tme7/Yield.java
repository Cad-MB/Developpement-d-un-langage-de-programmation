package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;

public class Yield  extends Primitive {
	public Yield() {
		super("Yield");
	}

	@Override
	public int getArity() {
		return 0;
	}
	
	@Override
	public Object apply() throws EvaluationException {
		CoroutineInstance yield = (CoroutineInstance) Thread.currentThread();
		yield.yieldCoroutine();
		return yield;
	}

}
