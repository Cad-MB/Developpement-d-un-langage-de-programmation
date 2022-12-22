package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;

public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
        implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment);
    }
    @Override
    public Object visit(IASTloop iast, ILexicalEnvironment lexenv) throws EvaluationException {
    	while ( true ) {
            Object c = iast.getCondition().accept(this, lexenv);
            if ( c instanceof Boolean ) {
                Boolean b = (Boolean) c;
                if ( ! b ) {
                    break;
                }
            }
            try {
                iast.getBody().accept(this, lexenv);
            } catch (BreakLabelException e){
            	if (e.getLabel().equals(iast.getLabel()))	break;
            	else throw new BreakLabelException(e.getLabel());
            } catch (ContinueLabelException e){
                if (e.getLabel().equals(iast.getLabel()))	continue;
                else throw new ContinueLabelException(e.getLabel());
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Object visit(IASTbreakLabel iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        throw new BreakLabelException(iast.getLabel());
    }

    @Override
    public Object visit(IASTcontinueLabel iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        throw new ContinueLabelException(iast.getLabel());
    }
}