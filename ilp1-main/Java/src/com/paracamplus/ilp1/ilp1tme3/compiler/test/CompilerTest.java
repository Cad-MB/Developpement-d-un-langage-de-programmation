package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.ast.ASTfactory;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.Compiler;
import com.paracamplus.ilp1.compiler.GlobalVariableEnvironment;
import com.paracamplus.ilp1.ilp1tme3.compiler.test.GlobalVariableStuff;
import com.paracamplus.ilp1.compiler.OperatorEnvironment;
import com.paracamplus.ilp1.compiler.OperatorStuff;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.compiler.optimizer.IdentityOptimizer;
import com.paracamplus.ilp1.compiler.test.CompilerRunner;
import com.paracamplus.ilp1.interfaces.IASTfactory;
import com.paracamplus.ilp1.interpreter.test.InterpreterRunner;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.ilpml.ILPMLParser;
import com.paracamplus.ilp1.parser.xml.IXMLParser;
import com.paracamplus.ilp1.parser.xml.XMLParser;

public class CompilerTest extends com.paracamplus.ilp1.compiler.test.CompilerTest{
	protected static String scriptCommand = "Java/src/com/paracamplus/ilp1/ilp1tme3/C/compileThenRun.sh";
	protected static String[] samplesDirName = { "SamplesTME3" };
	public CompilerTest(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	  public void configureRunner(CompilerRunner run) throws CompilationException {
	    	// configuration du parseur
	        IASTfactory factory = new ASTfactory();
	        IXMLParser xmlparser = new XMLParser(factory);
	        xmlparser.setGrammar(new File(XMLgrammarFile));
	        run.setXMLParser(xmlparser);
	        run.setILPMLParser(new ILPMLParser(factory));
	  	
	        // configuration du compilateur
	        IOperatorEnvironment ioe = new OperatorEnvironment();
	        OperatorStuff.fillUnaryOperators(ioe);
	        OperatorStuff.fillBinaryOperators(ioe);
	        IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
	        GlobalVariableStuff.fillGlobalVariables(gve);
	        Compiler compiler = new Compiler(ioe, gve);
	        compiler.setOptimizer(new IdentityOptimizer());
	        run.setCompiler(compiler);

	        // configuration du script de compilation et exécution
	        run.setRuntimeScript(scriptCommand);    	
	    }
	
	
	
	@Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
    	return InterpreterRunner.getFileList(samplesDirName, pattern);
    } 
}
