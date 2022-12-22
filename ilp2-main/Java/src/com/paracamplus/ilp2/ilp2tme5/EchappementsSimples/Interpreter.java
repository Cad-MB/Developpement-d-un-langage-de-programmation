package com.paracamplus.ilp2.ilp2tme5.EchappementsSimples;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTloop;

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
            } catch (BreakException e){
                break;
            } catch (ContinueException e){
                continue;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Object visit(IASTbreak iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        throw new BreakException("break");
    }

    @Override
    public Object visit(IASTcontinue iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        throw new ContinueException("continue");
    }
}