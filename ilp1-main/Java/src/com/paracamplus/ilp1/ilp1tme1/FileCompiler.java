package com.paracamplus.ilp1.ilp1tme1;

import java.io.File;
import java.io.IOException;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.test.CompilerTest;
import com.paracamplus.ilp1.parser.ParseException;

public class FileCompiler extends CompilerTest{  
	public FileCompiler(File file) {
		super(file);
	}

	public static void main (String[] args) throws CompilationException, ParseException, IOException {
		File fichierCompl = new File(args[0]);
		FileCompiler compiler = new FileCompiler(fichierCompl);
		compiler.processFile();
	}
}
