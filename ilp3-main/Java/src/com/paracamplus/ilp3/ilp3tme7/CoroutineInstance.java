package com.paracamplus.ilp3.ilp3tme7;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import com.paracamplus.ilp3.interpreter.Interpreter;
import java.util.concurrent.Semaphore;

public class CoroutineInstance extends java.lang.Thread{
    private Interpreter interpreter;
    Semaphore resumeS = new Semaphore(0);
    Semaphore yieldS = new Semaphore(0);
    private boolean finish = false;
    private final Object function;
    private final Object[] arguments;
    
	public CoroutineInstance(Object function, Object[] arguments, Interpreter interpreter) {
		this.function = function;
		this.arguments = arguments;
		this.interpreter = interpreter;
		start();
	}
	
	public void run() {
		resumeS.acquireUninterruptibly();
		Invocable f = (Invocable)function;
		try {
			f.apply(interpreter, arguments);
			finish = true;
			resumeS.release();
			yieldS.release();
		} catch (EvaluationException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void resumeCoroutine() {
		if (finish == false) {
			resumeS.release();
			yieldS.acquireUninterruptibly();	
		}
		
	}
	
	public void yieldCoroutine(){
		yieldS.release();
		resumeS.acquireUninterruptibly();
	}
}
