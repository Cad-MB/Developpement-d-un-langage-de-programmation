package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class Resume extends UnaryPrimitive{
	public Resume(){
		super("Resume");
	}

	@Override
	public Object apply(Object coroutine) throws EvaluationException {
		System.out.println("PONG\n");
		if (coroutine instanceof CoroutineInstance) {
			CoroutineInstance resume = (CoroutineInstance) coroutine;
			resume.resumeCoroutine();
		}
		return coroutine;
	}
	

}
