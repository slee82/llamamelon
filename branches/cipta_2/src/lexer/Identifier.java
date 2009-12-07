/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Identifier.java - Lexer token for Identifiers
 */

package lexer;

public class Identifier extends Token {

    private String val;

    /**
     * Overrides equals method. Two identifiers are equal if the words they
     * contain are equal.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Identifier))
            return false; // not equal
        Identifier otheri = (Identifier) other;
        return (otheri.val.equals(this.val));
    }
    
    public String toString() {
        return "<ident " + val + ">";
    }

    public Identifier(String s) {
        super(Tag.IDENT);
        this.val = s;
    }

    public String getID() {
        return val;
    }

    public void setID(String s) {
        val = s;
    }

}
