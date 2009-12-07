/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Type.java - Lexer token for Types
 */

package lexer;

public class Type extends Token {

    public String val;

    /**
     * Creates a new type token.
     * 
     * @param s
     *            what type is written in the source program, which means it
     *            includes stuff like player, team and list
     */
    public Type(String s) {
        super(Tag.TYPE);
        this.val = s;
    }

    /**
     * @return the corresponding Java type of this type token.
     */
    public String getType() {
        if (val.equals("number"))
            return "float";
        if (val.equals("string"))
            return "String";
        if (val.equals("list"))
            return "ArrayList";
        if (val.equals("team"))
            return "TeamObj";
        if (val.equals("player"))
            return "playerObj";
        if (val.equals("stat"))
            return "stat";
        if (val.equals("nothing"))
            return null;

        return val;
    }

}
