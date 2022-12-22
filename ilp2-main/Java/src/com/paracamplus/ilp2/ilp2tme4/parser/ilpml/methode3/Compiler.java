package com.paracamplus.ilp2.ilp2tme4.parser.ilpml.methode3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import com.paracamplus.ilp1.compiler.AssignDestination;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler 
implements IASTCvisitor<Void, Compiler.Context, CompilationException>{
	public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
		super(ioe, igve);
	}
	
	 public IASTCprogram normalize(IASTprogram program) throws CompilationException {
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }
	
	 public String compile(IASTprogram program) throws CompilationException {
        IASTCprogram newprogram = normalize(program);
        newprogram = ((IASTCprogram) optimizer.transform(newprogram));

        GlobalVariableCollector gvc = new GlobalVariableCollector();
        Set<IASTCglobalVariable> gvs = gvc.analyze(newprogram);
        newprogram.setGlobalVariables(gvs);
        
        FreeVariableCollector fvc = new FreeVariableCollector(newprogram);
        newprogram = (fvc.analyze());
      
        Context context = new Context(NoDestination.NO_DESTINATION);
        StringWriter sw = new StringWriter();
        try {
            out = new BufferedWriter(sw);
            visit(newprogram, context);
            out.flush();
        } catch (IOException exc) {
            throw new CompilationException(exc);
        }
        return sw.toString();
    }
	
	@Override
	public Void visit(IASTunless iast, Context context) throws CompilationException {
		IASTvariable tmp1 = context.newTemporaryVariable();
        emit("{ \n");
        emit("  ILP_Object " + tmp1.getMangledName() + "; \n");
        Context c = context.redirect(new AssignDestination(tmp1));
        iast.getCondition().accept(this, c);
        emit("  if ( !ILP_isEquivalentToTrue(");
        emit(tmp1.getMangledName());
        emit(" ) ) {\n");
        iast.getBody().accept(this, context);
        emit("} \n");
        whatever.accept(this, context);
        emit("}\n");
        return null;
    }

}
