package codegen;

import compiler.SymbolTable;
import lexer.Type;

/**
 * Arithmetic Expressions, extend Expr
 */
public class ArithmeticExpr extends Expr {

    public enum Op {
        PLUS, MIN, MULT, DIV, MOD
    }
    
    /**
     * Constructor - Takes an operator, left expression and right expression
     * @param op
     * @param exprL
     * @param exprR
     */
    public ArithmeticExpr(Op op, Expr exprL, Expr exprR) {
        this.operator = op;
        this.valueL = exprL;
        this.valueR = exprR;
    }
    
    @Override
    public String code(SymbolTable table) {
    	
    	if(! valueL.getType(table).equals(valueR.getType(table))) {
    		throw new RuntimeException("expr: type mismatch " + valueL.getType(table) + " and " + valueR.getType(table));
    	}
    	if (valueL.getType(table).equals(Type.number)) {
            /* Number addition */
            String result = valueL.code(table);
    		result += " " + getOpCode() + " ";
    		result += valueR.code(table);
    		return result;
        } else if (valueL.getType(table) instanceof ListType) {
            /* List append */
            String result = "(" + valueL.code(table) + ").append(";
            result += valueR.code(table);
            return result + ")";
        } else if (valueL.getType(table).equals(Type.string)) {
            /* List append */
            String result = "(" + valueL.code(table) + ").concat(";
            result += valueR.code(table);
            return result + ")";
        } else {
            throw new RuntimeException("expr: type " + valueL.getType(table) + " unsuitable for addition.");
        }
    }
    
    @Override
    public Type getType(SymbolTable table) {
    	return new Type("number") ;
    }
    
    private String getOpCode() {
        // TODO Auto-generated method stub
        switch(operator) {
        case PLUS: return "+";
        case MIN: return "-";
        case MULT: return "*";
        case DIV: return "/";
        case MOD: return "%";
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
	Op operator;
    Expr valueL;
    Expr valueR;
}
    
    