package codegen;

import lexer.*;

public class ReturnStmt extends Stmt {

    public ReturnStmt(Expr expr) {
        this.expression = expr;
        if (expr == null) {
            this.type = new Type("nothing");
        } else {
            this.type = expr.getType();
        }
    }

    public Expr expression;
    public Type type;
    
    @Override
    public String code() {
        String stmt = "return ";
        stmt += expression.code();
        stmt += " ;";
        return stmt;
    }
    @Override
    public void gen() {
        System.out.println(this.code());
    }

}
