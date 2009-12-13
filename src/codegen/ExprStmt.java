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
    public String stmtCode(SymbolTable table) {
        if (expr == null) return ";";
        String outcode = expr.code(table).concat(";");
        return table.indent() + outcode;
    }
    
    private Expr expr;

}
