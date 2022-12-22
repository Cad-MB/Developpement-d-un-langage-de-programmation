package com.paracamplus.ilp4.ilp4tme8;

import java.util.HashMap;
import java.util.Map;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp4.interpreter.ILPInstance;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;

public class ILPInstanceDynamique extends ILPInstance{
	public Map<String, IASTexpression> prop; 
	public ILPInstanceDynamique(IClass clazz, Object[] fields) throws EvaluationException {
		super(clazz, fields);
		prop = new HashMap<>();
	}
}
