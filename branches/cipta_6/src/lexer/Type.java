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
    
    public boolean equals(Object other) {
        if (!(other instanceof Type)) return false;
        Type o = (Type)other;
        return this.val.equals(o.val);
    }
    
    public String toString() {
        return "<type '" + val + "'>";
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
            return "BallList";
        if (val.equals("team"))
            return "TeamObj";
        if (val.equals("player"))
            return "PlayerObj";
        if (val.equals("nothing"))
            return "void";

        return val;
    }
    
    public final static Type number = new Type("number");
    public final static Type string = new Type("string");
    public final static Type list = new Type("list");
    public final static Type team = new Type("team");
    public final static Type player = new Type("player");
    public final static Type playerStat = new Type("PlayerStat");
    public final static Type teamStat = new Type("TeamStat");
    public final static Type bool = new Type("bool");

}
