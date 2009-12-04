/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Keyword.java - Lexer token for keywords
 */

package lexer;

public class Keyword extends Token {
    
    public final String lexeme;

    public Keyword(Tag t, String lex) {
        super(t);
        this.lexeme = lex;
    }
    
    /*
     * Premade keywords
     */
    public static final Keyword
        print = new Keyword(Tag.PRINT, "print");
}
