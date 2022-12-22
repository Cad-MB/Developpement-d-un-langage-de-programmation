package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NoSuchLocalVariableException;
import com.paracamplus.ilp1.interfaces.IAST;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;

public class RenameTransform 
extends CopyTransform<INormalizationEnvironment> {

    int compt_variables = 1;

    public RenameTransform(IASTfactory factory) {
        super(factory);
    }

    @Override
    public IAST visit(IASTblock iast, INormalizationEnvironment data) throws CompilationException{
        INormalizationEnvironment newdata = data;
        IASTbinding[] oldbindings = iast.getBindings();
        IASTbinding[] newbindings =
                new IASTbinding[oldbindings.length];

        for ( int i=0 ; i<oldbindings.length ; i++ ) {
            IASTbinding binding = oldbindings[i];

            IASTvariable variable = binding.getVariable();
            IASTvariable newvariable = factory.newVariable(variable.getMangledName()+ "_" + compt_variables);
            
            IASTexpression newexpr = (IASTexpression) binding.getInitialisation().accept(this, data);
            newbindings[i] = factory.newBinding(newvariable, newexpr);

            newdata = newdata.extend(variable, newvariable);

            compt_variables++;
        }
        IASTexpression newbody = (IASTexpression) iast.getBody().accept(this, newdata);
        return factory.newBlock(newbindings, newbody);
    }

    @Override
    public IAST visit(IASTfunctionDefinition iast, INormalizationEnvironment data) throws CompilationException{

        IASTvariable functionVariable  =  (IASTvariable) iast.getFunctionVariable().accept(this, data);

        IASTvariable[] oldvariables = iast.getVariables();
        IASTvariable[] newvariables = new IASTvariable[oldvariables.length];

        INormalizationEnvironment newdata = data;

        for ( int i=0 ; i<oldvariables.length ; i++ ) {
            IASTvariable variable = oldvariables[i];
            IASTvariable newvariable = factory.newVariable(variable.getMangledName() + "_" + compt_variables);

            newdata = newdata.extend(variable, newvariable);
            newvariables[i] = newvariable;
            compt_variables++;
        }

        IASTexpression newbody = (IASTexpression) iast.getBody().accept(this, newdata);

        return factory.newFunctionDefinition(functionVariable, newvariables, newbody);
    }

    @Override
    public IAST visit(IASTvariable iast, INormalizationEnvironment data) throws CompilationException{
        try{
            return data.renaming(iast);
        }catch(NoSuchLocalVariableException e){
            return iast;
        }
    }
}

