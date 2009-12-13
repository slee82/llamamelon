package javabackend;

import java.util.ArrayList;
import java.util.Arrays;

public class BallList extends ArrayList {

    @SuppressWarnings("unchecked")
    public BallList(Object[] create) {
        super(Arrays.asList(create));
        orig = create;
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
    public BallList append(BallList right) {
        BallList result = new BallList(Arrays.copyOf(this.orig, this.orig.length));
        if (this.size() > 0 && right.size() > 0) {
            // align types
            Object ol = this.get(0);
            Object or = right.get(0);
            if (!(ol.getClass().equals(or.getClass()))) {
                throw new RuntimeException("run: type of lists do not match.");
            }
        }
        result.addAll(right);
        return result;
    }

    private Object[] orig;
    private static final long serialVersionUID = 1L;

}
