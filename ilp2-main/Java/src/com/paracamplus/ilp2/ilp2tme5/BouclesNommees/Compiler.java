package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.compiler.AssignDestination;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.VoidDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.interfaces.IASTprogram;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

public class Compiler extends com.paracamplus.ilp2.compiler.Compiler
implements IASTCvisitor<Void, Compiler.Context, CompilationException> {
    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
        super(ioe, igve);
    }

    public IASTCprogram normalize(IASTprogram program)throws CompilationException {
        INormalizationFactory nf = new NormalizationFactory();
        Normalizer normalizer = new Normalizer(nf);
        IASTCprogram newprogram = normalizer.transform(program);
        return newprogram;
    }



    public String compile(IASTprogram program)
            throws CompilationException {

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
	public Void visit(IASTbreakLabel iast, Context data) throws CompilationException {
		emit("goto "+iast.getLabel()+"b;");
		return null;
	}

	@Override
	public Void visit(IASTcontinueLabel iast, Context data) throws CompilationException {
		emit("goto "+iast.getLabel()+"c;");
		return null;
	}

	@Override
	public Void visit(IASTloop iast, Context data) throws CompilationException {
		emit("while ( 1 ) { \n");
        IASTvariable tmp = data.newTemporaryVariable();
        emit("  ILP_Object " + tmp.getMangledName() + "; \n");
        Context c = data.redirect(new AssignDestination(tmp));
        iast.getCondition().accept(this, c);
        emit("  if ( ILP_isEquivalentToTrue(");
        emit(tmp.getMangledName());
        emit(") ) {\n");
        Context cb = data.redirect(VoidDestination.VOID_DESTINATION);
        iast.getBody().accept(this, cb);
        emit("\n} else { \n");
        emit(iast.getLabel()+"b:");
        emit("    break; \n");
        emit(iast.getLabel()+"c:    continue;\n");
        emit("\n}\n}\n");
        whatever.accept(this, data);
        return null;
    }
}