package codegen;

public class PrintStmt extends Stmt {

    public PrintStmt(Expr expr) {
        this.toprint = expr;
    }
    
    public void gen() {
        System.out.print("System.out.println(");
        toprint.gen();
        System.out.println(");");
    }
    
    private Expr toprint;

}
