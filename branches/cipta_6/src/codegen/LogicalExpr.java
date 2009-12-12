package codegen;

import compiler.SymbolTable;
import lexer.Type;

/**
 * Logical Expressions, extend Expr
 */
public class LogicalExpr extends Expr {

    public enum Op {
        AND, OR, NOT
    }
    
    /**
     * Constructor - Takes an operator, left expression and right expression
     * If Right expression is null, the NOT op was used (!)
     * @param op
     * @param exprL
     * @param exprR
     */
    public LogicalExpr(Op op, Expr exprL, Expr exprR) {
        this.operator = op;
        this.valueL = exprL;
        this.valueR = exprR;
    }
    
    @Override
    public String code(SymbolTable table) {
    	if (valueR == null)
    		return "! " + valueL.code(table);
    	if(! valueL.getType(table).equals(valueR.getType(table))) {
    		throw new RuntimeException("expr: type mismatch " + valueL.getType(table) + " and " + valueR.getType(table));
    	}
    	if(!valueL.getType(table).equals(Type.number)) {
    		throw new RuntimeException("expr: type is not number: " + valueL.getType(table) + " and " + valueR.getType(table));
    	}
    	
    	String result = valueL.code(table);
    		result += " " + getOpCode() + " ";
    		result += valueR.code(table);
    	return result;
    }
    
    @Override
    public Type getType(SymbolTable table) {
    	return new Type("number") ;
    }
    
    private String getOpCode() {
        // TODO Auto-generated method stub
        switch(operator) {
        case AND: return "&&";
        case OR: return "||";
        case NOT: return "!";
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
	Op operator;
    Expr valueL;
    Expr valueR;
}
    
    