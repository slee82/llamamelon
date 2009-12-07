package codegen;

import lexer.*;

public class ReturnStmt extends Stmt {

    public ReturnStmt(Expr expr) {
        this.expression = expr;
        if (expr == null) {
            this.type = new Type("nothing");
        } else {
            this.type = new Type("expr");
        }
    }

    public Expr expression;
    public Type type;

}
