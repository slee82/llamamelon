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
	type = STRINGCONST;
    }

    public Expr(Identifier id) {
        this.stringval = id.getID();
	type = IDENTIFIER;
    }

    public Expr() {
    }
    
    public void gen() {
        // later needs to differ by type
        System.out.print(stringval);
    }

    public String toString(){
	return stringval;
    }

    private int type;
    private String stringval;

    public static final int STRINGCONST=1;
    public static final int IDENTIFIER=2;
}
