/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Token.java - Tokens passed to the symbol table
 */
package lexer;

/**
 * Primitive token type. Subclasses of these tokens will be put in the symbol
 * table. Subclasses of tokens are keywords (includes operators?) and types of
 * constants.
 */
public class Token {

    public enum Tag {
        PRINT, IDENT, STRINGCONST, TYPE, NUMERICCONST
    }

    public final Tag tag;

    /**
     * Constructor sets the type of the token
     * @param tag
     */
    public Token(Tag t) {
        this.tag = t;
    }

    public String toString() {
        return ("<tag " + tag.name() + ">");
    }

}
