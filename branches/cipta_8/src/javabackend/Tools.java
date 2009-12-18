/*
 * COMS W4115 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Tools.java - Miscellaneous functions predefined in BALL
 */

package javabackend;

import java.util.Random;

public class Tools {
    
    public static int randomInt(int min, int max) {
        if (min > max) return randomInt(max, min);
        return myRand.nextInt(max-min) + min;
    }
    
    private static final Random myRand = new Random(new java.util.Date().getTime());

}
