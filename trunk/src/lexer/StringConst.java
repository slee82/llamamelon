/*
 * COMS W4115 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Authors: Cipta Herwana, Daniel Lasry, Sam Lee, Nathan Miller, Jordan Schau 
 * StringConst.java - Lexer token for String Constants
 */

package lexer;

public class StringConst extends Token {
    
    public String val;

    public StringConst(String s) {
        super(Tag.STRINGCONST);
        this.val = s;
    }
    
    public String toString() {
        return ("(" + super.toString() + " " + val + ")");
    }

}
