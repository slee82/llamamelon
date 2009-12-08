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
    
    public String code() {
        String begin = "System.out.println(";
        begin += toprint.code();
        begin += (");\n");
        return begin;
    }
    
    private Expr toprint;

}
