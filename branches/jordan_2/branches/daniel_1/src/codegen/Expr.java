/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Expr.java - Code Generator for expressions
 */

package codegen;
import lexer.*;

public class Expr extends ParseTreeNode {

    public Expr(StringConst const1) {
        this.stringval = const1.val;
    }

    public Expr() {
    }
    
    public void gen() {
        // later needs to differ by type
        System.out.print(stringval);
    }

    private int type;
    private String stringval;
}
