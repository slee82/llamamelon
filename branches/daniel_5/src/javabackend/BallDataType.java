package javabackend;

public interface BallDataType {
    
    /**
     * Used by the 'from' expression in BALL.
     * @param other
     * @return
     */
    public boolean match(Object other);
    
}
