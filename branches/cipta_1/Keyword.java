
public class Keyword extends Token {
    
    public final String lexeme;

    public Keyword(Tag t, String lex) {
        super(t);
        this.lexeme = lex;
    }
    
    public String toString() {
        return ("(" + super.toString() + " " + lexeme + ")");
    }
    
    /*
     * Premade keywords
     */
    public static final Keyword
        print = new Keyword(Tag.PRINT, "print");
}
