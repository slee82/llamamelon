/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
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
    
    public static String fixFloat(float input) {
    	String str ="";
    	if (input > 1.0 && input != Math.floor(input)) {
    		str = String.format("%.2f",input);
    	}
    	
    	else if (input == Math.floor(input))
    	{ str = String.format("%.0f",input); }
    	
    	else if (input < 1.0){ 
    		str = String.format("%.3f",input); 
    	}
    	return str;
    }

    
    private static final Random myRand = new Random(new java.util.Date().getTime());

}
