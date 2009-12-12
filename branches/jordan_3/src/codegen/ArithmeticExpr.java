package codegen;

import compiler.SymbolTable;

import lexer.Identifier;

public class ArithmeticExpr extends Expr {

    public enum Op {
        OPPLUS, OPMIN, OPMULT, OPDIV, OPMOD
    }
    
    public ArithmeticExpr(Op op, Expr exprL, Expr exprR) {
        this.operator = op;
        this.valueL = exprL;
        this.valueL = exprL;
    }
    
    @Override
    public String code(SymbolTable table) {
    	String result = "3";
    		result += "+";
    		result += "4";
    		result += ";";
    	return result;
    }
    
    private String getOpCode() {
        // TODO Auto-generated method stub
        switch(operator) {
        case OPPLUS: return "+";
        case OPMIN: return "-";
        case OPMULT: return "*";
        case OPDIV: return "/";
        case OPMOD: return "%";
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
	Op operator;
    Expr valueL;
    Expr valueR;
}
    
    