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
        String lcode = valueL.code(table);
        String rcode = valueR.code(table);
        Type ltype = valueL.getType(table);
        Type rtype = valueR.getType(table);
        
    	if(! ltype.equals(rtype)) {
    		throw new RuntimeException("expr: type mismatch " + valueL.getType(table) + " and " + valueR.getType(table));
    	}
    	if (ltype.equals(Type.number)) {
            /* Number addition */
            String result = lcode;
    		result += " " + getOpCode() + " ";
    		result += rcode;
    		return result;
        } else if (ltype instanceof ListType) {
            /* List append */
            String result = "(" + lcode + ").append(";
            result += rcode;
            return result + ")";
        } else if (valueL.getType(table).equals(Type.string)) {
            /* String concat */
            String result = "(" + lcode + ").concat(";
            result += rcode;
            return result + ")";
        } else {
            throw new RuntimeException("expr: type " + ltype + " unsuitable for addition.");
        }
    }
    
    @Override
    public Type getType(SymbolTable table) {
        Type ltype = valueL.getType(table);
        if(! valueL.getType(table).equals(valueR.getType(table))) {
            throw new RuntimeException("expr: type mismatch " + ltype + " and " + valueR.getType(table));
        }
        
        if (ltype.equals(Type.number) || 
                (ltype instanceof ListType) ||
                ltype.equals(Type.string)) {
            return ltype;
        } else {
            throw new RuntimeException("expr: type " + valueL.getType(table) + " unsuitable for addition.");
        }
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
    
    