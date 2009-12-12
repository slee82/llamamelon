package codegen;

import java.util.ArrayList;

import lexer.Type;
import compiler.SymbolTable;

public class ListInit extends AtomicExpr {

    public ListInit() {
        this.args = new ArrayList<Expr>();
    }

    public ListInit(ArrayList<Expr> args) {
        this.args = args;
    }
    
    public String code(SymbolTable table) {
        ListType myType = (ListType)getType(table);
        Type t = myType.contents;
        
        // type checking done
        // new BallList<T>(new T[] { a, b, c })
        String code = "new " + myType.getType() + "(new " + t.getType() + "[] {\n";
        
        table.increaseIndent(1);
        for (Expr content : args) {
            code += table.indent() + content.code(table) + " ,\n";
        }
        table.decreaseIndent(1);
        return code + table.indent() + "})";
            
    }
    
    public Type getType(SymbolTable table) {
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
        return new ListType(t);
    }
    
    private ArrayList<Expr> args;

}
