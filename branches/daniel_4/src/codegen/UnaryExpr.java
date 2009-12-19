package codegen;

import compiler.SymbolTable;
import lexer.Identifier;
import lexer.Type;

/**
 * Arithmetic Expressions, extend Expr
 */
public class UnaryExpr extends Expr {

    public enum Op {
        PPLUS, MMIN
    }
    
    public enum Fix {
        PRE, POST
    }
    /**
     * Constructor - Takes an operator, left expression and right expression
     * @param op
     * @param exprL
     * @param exprR
     */
    public UnaryExpr(Op op, Expr expr, Fix f) {
        this.operator = op;
        this.value = expr;
        this.fix = f;
    }
    
    @Override
    public String code(SymbolTable table) {
        String code = value.code(table);
        Type type = value.getType(table);
        InsertionPoint insert = table.getIP();
        Identifier tempID = table.newID();
        
        /* MUST BE OF TYPE NUMBER */
        if (type !=null && type.equals(Type.number)) {
        	//prefix
        	if (getFix()) {
        		insert.insert(table.indent() + "float " + tempID.getID() + " = " + getOpCode() + code + ";\n");
        		return tempID.getID();
        	}
        	else {
        		insert.insert(table.indent() + "float " + tempID.getID() + " = " + code + getOpCode() + ";\n");
        		return tempID.getID();
        	}
        } else {
            throw new RuntimeException("expr: type " + type + " unsuitable for unary operation " + getOpCode());
        }
    }
    
    @Override
    public Type getType(SymbolTable table) {
        Type type = value.getType(table);
        
        if (type.equals(Type.number)) {
            return Type.number;
        } else {
            throw new RuntimeException("expr: type " + value.getType(table) + " unsuitable for unary operation " + getOpCode());
        }
    }
    
    private String getOpCode() {
        // TODO Auto-generated method stub
        switch(operator) {
        case PPLUS: return "++";
        case MMIN: return "--";
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
    /**
     * 
     * @return true for prefix, false for postfix
     */
    private boolean getFix() {
        // TODO Auto-generated method stub
        switch(fix) {
        case PRE: return true;
        case POST: return false;
        default:
            throw new IllegalArgumentException("assignment: unknown operator");
        }
    }
    
	Op operator;
    Expr value;
    Fix fix;
}
    
    