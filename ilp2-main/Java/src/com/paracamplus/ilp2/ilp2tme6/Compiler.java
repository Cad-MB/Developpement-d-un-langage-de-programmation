package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp2.compiler.normalizer.Normalizer;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp2.ast.ASTfactory;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler {

    protected RenameTransform renameTransform;
    //protected InlineTransform inlineTransform;
    protected IASTfactory factory;
    protected INormalizationEnvironment env;

    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
        super(ioe, igve);
        factory = new ASTfactory();
        renameTransform = new RenameTransform(factory);
        //inlineTransform = new InlineTransform(factory);
        env = NormalizationEnvironment.EMPTY; 
    }

    @Override
    public IASTCprogram normalize(IASTprogram program) 
            throws CompilationException {
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        
        program = (IASTprogram) renameTransform.visit(program,env);
        
        //program = (IASTprogram) inlineTransform.visit(program,env);
        
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }

    
}
