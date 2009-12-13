package codegen;
import lexer.Type;

public class ListType extends Type {

    public ListType(Type contents) {
        super(null);
        this.contents = contents;
    }
    
    public boolean equals(Object other) {
        if (!(other instanceof ListType)) return false;
        ListType oth = (ListType)other;
        return oth.contents.equals(this.contents);
    }
    
    public String getType() {
        if (contents.equals(Type.number)) {
            // have to use the Float object.
            return "BallList<Float>";
        }
        return "BallList<" + contents.getType() + ">";
    }
    
    public String toString() {
        return "<list of " + contents.toString() + ">"; 
    }
    
    public final Type contents;
    
}
