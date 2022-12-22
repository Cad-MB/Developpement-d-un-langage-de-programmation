package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;

import com.paracamplus.ilp2.ast.ASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;


public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter {

    protected RenameTransform renameTransform;
    //protected InlineTransform inlineTransform;
    protected IASTfactory factory;
    protected INormalizationEnvironment env;

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment);
        factory = new ASTfactory();
        renameTransform = new RenameTransform(factory);
        //inlineTransform = new InlineTransform(factory);
        env = NormalizationEnvironment.EMPTY;   
    }

    @Override
    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
    		throws EvaluationException {
        try {
            
        	iast = (IASTprogram) renameTransform.visit(iast, env);
            
        	//iast = (IASTprogram) inlineTransform.visit(iast,env);
            
        	for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
                Object f = this.visit(fd, lexenv);
                String v = fd.getName();
                getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
            }
            return iast.getBody().accept(this, lexenv);
       } catch (Exception exc) {
            return exc;
        }
    }
}
