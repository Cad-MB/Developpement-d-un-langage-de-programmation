/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp1.interpreter.operator;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class Add extends BinaryOperator {
    
    public Add () {
        super("+");
    }
    
    @Override
	public Object apply (Object arg1, Object arg2) 
            throws EvaluationException {
    	if ( arg1 instanceof String || arg2 instanceof String) {
    		return arg1.toString() + arg2.toString();
        } else if ( arg1 instanceof BigInteger ) {
            BigInteger bi1 = (BigInteger) arg1;
            if ( arg2 instanceof BigInteger ) {
                BigInteger bi2 = (BigInteger) arg2;
                return bi1.add(bi2);
            } else if ( arg2 instanceof BigDecimal ) {
                BigDecimal bd2 = (BigDecimal) arg2;
                BigDecimal bd1 = new BigDecimal(bi1);
                return bd1.add(bd2);
            } else {
                String msg = "Non numeric argument2";
                throw new EvaluationException(msg);
            }
        } else if ( arg1 instanceof BigDecimal ) {
            BigDecimal bd1 = (BigDecimal) arg1;
            if ( arg2 instanceof BigInteger ) {
                BigInteger bi2 = (BigInteger) arg2;
                BigDecimal bd2 = new BigDecimal(bi2);
                return bd1.add(bd2);
            } else if ( arg2 instanceof BigDecimal ) {
                BigDecimal bd2 = (BigDecimal) arg2;
                return bd1.add(bd2);
            } else {
                String msg = "Non numeric argument2";
                throw new EvaluationException(msg);
            }
        } else {
            String msg = "Non numeric nor string argument1";
            throw new EvaluationException(msg);
        }
    }
}
