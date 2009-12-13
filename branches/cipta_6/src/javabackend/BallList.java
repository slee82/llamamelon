package javabackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BallList<T> extends ArrayList<T> {

    @SuppressWarnings("unchecked")
    public BallList(Object[] create) {
        super((List<T>)Arrays.asList((T[])create));
        orig = create;
    }
    
    public BallList() {
        super();
        orig = new Object[] {};
    }
    
    public String toString() {
        String begin = "[ ";
        boolean first = true;
        for (Object in : this) {
            if (!first) begin +=  ", ";
            else first = false;

            if (in instanceof String) {
                begin += "\"";
            }
            begin += in.toString();
            if (in instanceof String) {
                begin += "\"";
            }
        }
        begin += " ]";
        return begin;
    }
    
    @SuppressWarnings("unchecked")
    public BallList append(BallList<T> right) {
        BallList result = new BallList<T>();
        
        result.addAll(this);
        result.addAll(right);
        return result;
    }

    private Object[] orig;
    private static final long serialVersionUID = 1L;

}
