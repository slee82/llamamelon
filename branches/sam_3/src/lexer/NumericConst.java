/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * NumericConst.java - Lexer token for Numeric Constants
 */

package lexer;

public class NumericConst extends Token {
    
    public String val;

    public NumericConst(String s) {
        super(Tag.NUMERICCONST);
        this.val = "((float) " + s + " )";
    }
    
    public String toString() {
        return ("(" + super.toString() + " " + val + ")");
    }

}
