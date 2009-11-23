
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
