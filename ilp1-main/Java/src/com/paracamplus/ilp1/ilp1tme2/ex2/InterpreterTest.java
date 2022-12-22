package com.paracamplus.ilp1.ilp1tme2.ex2;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.ast.ASTfactory;
import com.paracamplus.ilp1.interfaces.IASTfactory;
import com.paracamplus.ilp1.interpreter.GlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.GlobalVariableStuff;
import com.paracamplus.ilp1.interpreter.Interpreter;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.test.InterpreterRunner;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.xml.IXMLParser;
import com.paracamplus.ilp1.parser.xml.XMLParser;

public class InterpreterTest extends com.paracamplus.ilp1.interpreter.test.InterpreterTest{
	protected static String[] samplesDirName = { "SamplesTME1" };
	public ILPMLParser parser;
	public InterpreterTest(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	
	public void configureRunner(InterpreterRunner run) throws EvaluationException {
    	// configuration du parseur
        IASTfactory factory = new ASTfactory();
        IXMLParser xmlParser = new XMLParser(factory);
        xmlParser.setGrammar(new File(XMLgrammarFile));
        run.setXMLParser(xmlParser);
        run.setILPMLParser(parser = new ILPMLParser(factory));

        // configuration de l'interprète
        StringWriter stdout = new StringWriter();
        run.setStdout(stdout);
        IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
        GlobalVariableStuff.fillGlobalVariables(gve, stdout);
        IOperatorEnvironment oe = new OperatorEnvironment();
        OperatorStuff.fillUnaryOperators(oe);
        OperatorStuff.fillBinaryOperators(oe);
        Interpreter interpreter = new Interpreter(gve, oe);        
        run.setInterpreter(interpreter);
    }
	
	/* TRES IMPORTANT !!!
     *  Obligatoire pour que les tests marchent sur le nouveau samplesDirName
     */
    @Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
    	return InterpreterRunner.getFileList(samplesDirName, pattern);
    }
    
    @Test
    public void processFile() throws EvaluationException, ParseException, IOException {
    	InterpreterRunner run = new InterpreterRunner();
    	configureRunner(run);
    	run.testFile(file);
    	run.checkPrintingAndResult(file);
    	
    	// Ajout en plus pour tester:
    	assertEquals(parser.cpt, 1); // Importer assert !! 
    								 // Dans ce cas 1 puisque le dossier "SamplesTME1" contient que des .ilpml avec une seule constante 
    }
    
}
