/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * PrintStmt.java - Code Generator for print statements
 */

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
