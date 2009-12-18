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
        print = new Keyword(Tag.PRINT, "print"),
        activate = new Keyword(Tag.ACTIVATE, "activate"),
        function = new Keyword(Tag.FUNCTION, "function"),
        simfunction = new Keyword(Tag.SIMFUNCTION, "simfunction"),
        stat = new Keyword(Tag.STAT, "stat"),
        end = new Keyword(Tag.END, "end"),
        ret = new Keyword(Tag.RETURN, "return"),
        returns = new Keyword(Tag.RETURNS, "returns"),
        where = new Keyword(Tag.WHERE, "where"),
        self = new Keyword(Tag.SELF, "self"),
    	mydo = new Keyword(Tag.DO, "do"),
    	times = new Keyword(Tag.TIMES, "times"),
    	in = new Keyword(Tag.IN, "in"),
    	foreach = new Keyword(Tag.FOREACH, "foreach");
}
