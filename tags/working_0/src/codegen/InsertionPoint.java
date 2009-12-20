package codegen;

/**
 * Defines a location inside the java code where statements can be inserted.
 * Usually used for inserting new statements _before_ the current point, as
 * in folding a piece of a long expression into more manageable chunks.
 */
public interface InsertionPoint {
    
    public void insert(String code);

}
