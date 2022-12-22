/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTloop extends com.paracamplus.ilp2.interfaces.IASTloop, IASTexpression {
	String getLabel();
}
