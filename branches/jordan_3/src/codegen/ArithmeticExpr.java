package codegen;

import compiler.SymbolTable;
import lexer.Type;

public class ArithmeticExpr extends Expr {

    public enum Op {
        PLUS, MIN, MULT, DIV, MOD
    }
    
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
    
    