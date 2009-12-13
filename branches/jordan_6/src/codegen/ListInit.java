package codegen;

import java.util.ArrayList;

import lexer.Type;
import compiler.SymbolTable;

/**
 * Implements list declarations.
 * 
 * Lists in BALL is its own data type. The contents of the list must have the
 * same type, but lists that contain different types can be put in the same
 * list. (List contents are checked in more detail in runtime)
 */
public class ListInit extends AtomicExpr {

    public ListInit() {
        this.args = new ArrayList<Expr>();
    }

    public ListInit(ArrayList<Expr> args) {
        this.args = args;
    }
    
    public String code(SymbolTable table) {
        
        Type t = null;
        for (Expr expr : args) {
            if (t == null) t = expr.getType(table);
            if (!t.equals(expr.getType(table))) {   
                throw new RuntimeException("listinit: type mismatch, expected " 
                        + t + ", got " + expr.getType(table));
            }
        }
        
        if (t.equals(Type.number))
            t = new Type("Float");
        
        // type checking done
        // new BallList<T>(new T[] { a, b, c })
        String code = "new " + Type.list.getType() + "(new " + t.getType() + "[] {\n";
        
        table.increaseIndent(1);
        for (Expr content : args) {
            code += table.indent() + content.code(table) + " ,\n";
        }
        table.decreaseIndent(1);
        return code + table.indent() + "})";
            
    }
    
    public Type getType(SymbolTable table) {
        return Type.list;
    }
    
    private ArrayList<Expr> args;

}