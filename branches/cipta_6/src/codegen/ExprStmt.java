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
        return super.insert + table.indent() + expr.code(table).concat(";");
    }
    
    private Expr expr;

}
