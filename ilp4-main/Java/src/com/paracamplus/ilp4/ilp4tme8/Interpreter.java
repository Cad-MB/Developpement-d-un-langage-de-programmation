package com.paracamplus.ilp4.ilp4tme8;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.ILPInstance;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
	public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
			IClassEnvironment classEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
	}

	@Override
	public Object visit(IASTreadProperty iast, ILexicalEnvironment data) throws EvaluationException {
		Object fieldName = iast.getFieldName();
		String nom;
		if (fieldName instanceof String)
			nom = (String) fieldName;
		else {
			String msg = "Not a String " + fieldName;
			throw new EvaluationException(msg);
		}
        Object target = iast.getTarget().accept(this, data);
        if ( target instanceof ILPInstance ) {
            return ((ILPInstance) target).read(nom);
        } else {
            String msg = "Not an ILP instance " + target;
            throw new EvaluationException(msg);
        }
	}
	
//	PARTIE DYNAMIQUE
//	@Override
//	public Object visit(IASTreadProperty iast, ILexicalEnvironment data) throws EvaluationException {
//		Object res;
//		Object fieldName = iast.getFieldName();
//		String nom;
//		if (fieldName instanceof String)
//			nom = (String) fieldName;
//		else {
//			String msg = "Not a String " + fieldName;
//			throw new EvaluationException(msg);
//		}
//        Object target = iast.getTarget().accept(this, data);
//        if ( target instanceof ILPInstanceDynamique ) {
//            try{
//            	res = ((ILPInstanceDynamique) target).read(nom);
//            } catch (EvaluationException e) {
//            	res = ((ILPInstanceDynamique) target).prop.get(nom);
//            }
//        } else {
//            String msg = "Not an ILP instance " + target;
//            throw new EvaluationException(msg);
//        }
//        return res;
//	}

	@Override
	public Object visit(IASTwriteProperty iast, ILexicalEnvironment data) throws EvaluationException {
		Object fieldName = iast.getFieldName();
		String nom;
		if (fieldName instanceof String)
			nom = (String) fieldName;
		else {
			String msg = "Not a String " + fieldName;
			throw new EvaluationException(msg);
		}
		Object target = iast.getTarget().accept(this, data);
	    Object value = iast.getValue().accept(this, data);
	    if ( target instanceof ILPInstance ) {
	         return ((ILPInstance) target).write(nom, value);
	    } else {
	        String msg = "Not an ILP instance " + target;
	        throw new EvaluationException(msg);
	    }
	}

	@Override
	public Object visit(IASThasProperty iast, ILexicalEnvironment data) throws EvaluationException {
		boolean bool = true;
		Object fieldName = iast.getFieldName();
		String nom;
		if (fieldName instanceof String)
			nom = (String) fieldName;
		else {
			String msg = "Not a String " + fieldName;
			throw new EvaluationException(msg);
		}
        Object target = iast.getTarget().accept(this, data);
        if ( target instanceof ILPInstance ) {
            try{
            	((ILPInstance) target).read(nom);
            } catch (EvaluationException e) {
            	bool = false;
            }
        } else {
            String msg = "Not an ILP instance " + target;
            throw new EvaluationException(msg);
        }
        
        return bool;
	}
}

