package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp2.interfaces.IASTvisitor;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;

public class CopyTransform<Data> implements IASTvisitor<IAST, Data, CompilationException> {

    protected IASTfactory factory;

    public CopyTransform(IASTfactory factory) {
        this.factory = factory;
    }
    
    public IAST visit(IASTprogram iast, Data data) 
			throws CompilationException {
		IASTexpression expression = (IASTexpression) iast.getBody().accept(this, data);
		IASTfunctionDefinition[] oldfunctions = iast.getFunctionDefinitions();
		IASTfunctionDefinition[] functions = new IASTfunctionDefinition[oldfunctions.length];
		for (int i = 0; i < oldfunctions.length; i++) {
			functions[i] = (IASTfunctionDefinition) visit(oldfunctions[i], data);
		}
		return factory.newProgram(functions, expression);
    }
    
    public IAST visit(IASTfunctionDefinition iast, Data data) 
			throws CompilationException {
		IASTvariable functionVariable  = (IASTvariable) iast.getFunctionVariable().accept(this,data);
		IASTvariable[] oldvariables = iast.getVariables();
		IASTvariable[] variables = new IASTvariable[oldvariables.length];
		IASTexpression body = (IASTexpression) iast.getBody().accept(this, data);
		for (int i = 0; i < oldvariables.length; i++) {
			variables[i] = (IASTvariable) oldvariables[i].accept(this,data);
		}
		return factory.newFunctionDefinition(functionVariable, variables, body);
	}
    

    @Override
    public IAST visit(IASTalternative iast, Data data) throws CompilationException {
        return factory.newAlternative(
                (IASTexpression) iast.getCondition().accept(this, data),
                (IASTexpression) iast.getConsequence().accept(this, data),
                iast.isTernary() ? (IASTexpression) iast.getAlternant().accept(this,data) : null);

    }

    @Override
    public IAST visit(IASTbinaryOperation iast, Data data) throws CompilationException {
        return factory.newBinaryOperation(
                iast.getOperator(),
                (IASTexpression) iast.getLeftOperand().accept(this, data),
                (IASTexpression) iast.getRightOperand().accept(this, data));
    }

    @Override
    public IAST visit(IASTblock iast, Data data) throws CompilationException {
        IASTbinding[] bindings = new IASTbinding[iast.getBindings().length];
        for(int i = 0; i < iast.getBindings().length; i++){
            bindings[i] = factory.newBinding(
                    (IASTvariable) iast.getBindings()[i].getVariable().accept(this, data),
                    (IASTexpression) iast.getBindings()[i].getInitialisation().accept(this, data));
        }
        return factory.newBlock(
                bindings,
                (IASTexpression) iast.getBody().accept(this, data));
    }

    @Override
    public IAST visit(IASTboolean iast, Data data) throws CompilationException {
        return factory.newBooleanConstant(iast.getValue().toString());
    }

    @Override
    public IAST visit(IASTfloat iast, Data data) throws CompilationException {
        return factory.newFloatConstant(iast.getValue().toString());
    }

    @Override
    public IAST visit(IASTinteger iast, Data data) throws CompilationException {
        return factory.newIntegerConstant(iast.getValue().toString());
    }

    @Override
    public IAST visit(IASTinvocation iast, Data data) throws CompilationException {
        IASTexpression[] arguments = new IASTexpression[iast.getArguments().length];
        for(int i = 0; i < iast.getArguments().length; i++){
            arguments[i] = (IASTexpression) iast.getArguments()[i].accept(this, data);
        }

        return factory.newInvocation(
                (IASTexpression) iast.getFunction().accept(this, data),
                arguments);
    }

    @Override
    public IAST visit(IASTsequence iast, Data data) throws CompilationException {
        IASTexpression[] instructions = new IASTexpression[iast.getExpressions().length];
        for(int i = 0; i < iast.getExpressions().length; i++){
            instructions[i] = (IASTexpression) iast.getExpressions()[i].accept(this, data);
        }
        return factory.newSequence(instructions);
    }

    @Override
    public IAST visit(IASTstring iast, Data data) throws CompilationException {
        return factory.newStringConstant(iast.getValue());
    }

    @Override
    public IAST visit(IASTunaryOperation iast, Data data) throws CompilationException {
        return factory.newUnaryOperation(
                iast.getOperator(),
                (IASTexpression) iast.getOperand().accept(this, data));
    }

    @Override
    public IAST visit(IASTvariable iast, Data data) throws CompilationException {
        return factory.newVariable(iast.getName());
    }

    @Override
    public IAST visit(IASTassignment iast, Data data) throws CompilationException {
        return factory.newAssignment(
                (IASTvariable) iast.getVariable().accept(this,data),
                (IASTexpression) iast.getExpression().accept(this, data));
    }

    @Override
    public IAST visit(IASTloop iast, Data data) throws CompilationException {
        return factory.newLoop(
                (IASTexpression) iast.getCondition().accept(this, data),
                (IASTexpression) iast.getBody().accept(this, data));
    }
}

