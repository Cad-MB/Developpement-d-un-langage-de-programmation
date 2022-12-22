/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.BouclesNommees;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvisitable;

public class ASTloop extends ASTexpression 
implements IASTloop, IASTvisitable {
	private final IASTexpression condition;
    private final IASTexpression body;
    private final String label;
	    
    public ASTloop (IASTexpression condition, IASTexpression body, String label) {
        this.condition = condition;
        this.body = body;
        this.label = label;
    }

    @Override
	public IASTexpression getCondition() {
        return condition;
    }

    @Override
	public IASTexpression getBody() {
        return body;
    }
    
    @Override
	public String getLabel() {
        return label;
    }

   @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data); // Faire attention a prendre le IASTvisitor courant
	}
}
