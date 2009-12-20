/*
 * COMS W4115 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Authors: Cipta Herwana, Daniel Lasry, Sam Lee, Nathan Miller, Jordan Schau 
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
