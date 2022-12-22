package com.paracamplus.ilp1.ilp1tme3.compiler.test;

import java.io.Writer;
import java.math.BigDecimal;

import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.primitive.Newline;
import com.paracamplus.ilp1.interpreter.primitive.Print;
import com.paracamplus.ilp1.interpreter.primitive.ToString;
import com.paracamplus.ilp1.interpreter.primitive.TypeOf;

public class GlobalVariableStuffInterpreter {
    public static void fillGlobalVariables (
            IGlobalVariableEnvironment env,
            Writer out ) {
        env.addGlobalVariableValue("pi", new BigDecimal("3.1415926535"));
        env.addGlobalVariableValue(new Print(out));
        env.addGlobalVariableValue(new Newline(out));
        env.addGlobalVariableValue(new ToString());
        env.addGlobalVariableValue(new TypeOf());
        env.addGlobalVariableValue(new Sinus());
        env.addGlobalVariableValue(new MakeVector());
        env.addGlobalVariableValue(new VectorLength());
        env.addGlobalVariableValue(new VectorGet());
  }
}