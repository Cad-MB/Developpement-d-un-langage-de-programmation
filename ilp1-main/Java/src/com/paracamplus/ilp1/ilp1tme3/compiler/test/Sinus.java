package com.paracamplus.ilp1.ilp1tme3.compiler.test;


import java.math.BigDecimal;
import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

public class Sinus extends UnaryPrimitive {
		public Sinus() {
			super("sinus");
		}
		
		@Override
		public Object apply(Object value) throws EvaluationException{
			if (value instanceof Double || value instanceof Float)
				return Math.sin((double) value);
			else if (value instanceof BigInteger) 
				return new BigDecimal (Math.sin(((BigInteger) value).doubleValue()));
			else if (value instanceof BigDecimal)
				return new BigDecimal (Math.sin(((BigDecimal) value).doubleValue()));
			
			else throw new EvaluationException ("Invalid argument, number expected  "+value);
		}
}


