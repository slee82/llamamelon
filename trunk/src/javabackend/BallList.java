package javabackend;

import java.util.ArrayList;
import java.util.Arrays;

public class BallList extends ArrayList {

    @SuppressWarnings("unchecked")
    public BallList(Object[] create) {
        super(Arrays.asList(create));
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

    private static final long serialVersionUID = 1L;

}
