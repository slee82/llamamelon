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
        type = new Type("number");
    }

    public Expr(Identifier id) {
        this.stringval = id.getID();
        // TODO: find out identifier type through symbol table.
        type = new Type("identifier");
    }

    public Expr(NumericConst n) {
        this.stringval = n.val;
        type = new Type("string");
    }

    public Expr() {
    }
    
    /**
     * Outputs the Java code for this expression to the standard output.
     */
    public void gen() {
        // later needs to differ by type
        System.out.print(this.code());
    }
    
    /**
     * @return Java code for this expression.
     */
    public String code() { return this.stringval; }

    public String toString(){
	return stringval;
    }

    private Type type;
    private String stringval;

    public static final int STRINGCONST=1;
    public static final int IDENTIFIER=2;
    public static final int NUMERICCONST=3;
}
