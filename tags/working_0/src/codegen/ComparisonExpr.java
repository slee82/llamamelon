package codegen;

import compiler.SymbolTable;
import lexer.Type;

/**
 * Logical Expressions, extend Expr
 */
public class ComparisonExpr extends Expr {

    public enum Op {
        IS, ISNOT, GT, LT, GTE, LTE
    }
    
    /**
     * Constructor - Takes an operator, left expression and right expression
     * If Right expression is null, the NOT op was used (!)
     * @param op
     * @param exprL
     * @param exprR
     */
    public ComparisonExpr(Op op, Expr exprL, Expr exprR) {
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
    	if(valueL.getType(table).equals(Type.string)) {
    		String result = valueL.code(table);
    		result += ".equals(";
    		result += valueR.code(table);
    		result += " );";
    	}
    	else {
    	    	
    		String result = valueL.code(table);
    			result += " " + getOpCode() + " ";
    			result += valueR.code(table);
    		return result;
    	}
    	
    	return "";
    	
    }
    
    @Override
    public Type getType(SymbolTable table) {
    	return Type.bool ;
    }
    
    private String getOpCode() {
        // TODO Auto-generated method stub
        switch(operator) {
        case IS: return "==";
        case ISNOT: return "!=";
        case GT: return ">";
        case LT: return "<";
        case GTE: return ">=";
        case LTE: return "<=";
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
	Op operator;
    Expr valueL;
    Expr valueR;
}
    
    