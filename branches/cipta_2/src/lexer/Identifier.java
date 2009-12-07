/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Identifier.java - Lexer token for Identifiers
 */

package lexer;

public class Identifier extends Token {
    
    private String val;

    public Identifier(String s) {
        super(Tag.IDENT);
        this.val = s;
    }

    public String getID(){
	return val;
    }

    public void setID(String s){
	val = s;
    }

}
