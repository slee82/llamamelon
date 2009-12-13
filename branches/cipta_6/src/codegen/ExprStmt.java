package codegen;

import compiler.SymbolTable;

public class ExprStmt extends Stmt {
    
    public ExprStmt() {
        this(null);
    }

    public ExprStmt(Expr expression) {
        this.expr = expression;
    }

    @Override
    public String code(SymbolTable table) {
        table.setInsertPt(this);
        
        if (expr == null) return ";";
        String outcode = expr.code(table).concat(";");
        return super.insert + table.indent() + outcode;
    }
    
    private Expr expr;

}
