/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Tools.java - Miscellaneous functions predefined in BALL
 */

package javabackend;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.lang.Math;

public class Tools {
    
    public static int randomInt(int min, int max) {
        if (min > max) return randomInt(max, min);
        return myRand.nextInt(max-min) + min;
    }
    
    public static float randomFloat(float min, float max) {
    	if (min > max) return randomFloat(max, min);
    	return myRand.nextFloat() * (max - min) + min;
    }
    
    public static String fixFloat(float input) {
    	String str ="";
    	if (input > 1.0 && input != (float)Math.floor(input)) {
    		str = String.format("%.2f",input);
    	}
    	
    	else if (input == (float)Math.floor(input))
    	{ str = String.format("%.0f",input); }
    	
    	else if (input < 1.0){ 
    		str = String.format("%.3f",input); 
    	}
    	return str;
    }
    
    public static float max(float low, float high){
    	return Math.max(low,high);
    }
    
    public static float min(float low, float high){
    	return Math.min(low,high);
    }

    public static <T> BallList<T> top(float num, BallList<T> list, Object stat) {
        if (list.size() == 0) return list;
        T entry = list.get(0);
        BallList<T> copy = new BallList<T>();
        for (T ent : list) copy.add(ent);
               if (entry instanceof PlayerObj) {
            if (!(stat instanceof PlayerStat)) {
                final PlayerStat st = (PlayerStat)stat;
                Collections.sort(copy, new Comparator<T>() {
                    public int compare(T arg0, T arg1) {
                        PlayerObj p0 = (PlayerObj)arg0;
                        PlayerObj p1 = (PlayerObj)arg1;
                        float p0stat = st.get(p0);
                        float p1stat = st.get(p1);
                        if (p0stat < p1stat) return -1;
                        if (p0stat == p1stat) return 0;
                        return 1;
                    }
                });
                return (BallList<T>) copy.subList(0, (int)num);
            }
                   } else if (entry instanceof TeamObj) {
            if (!(stat instanceof TeamStat)) {
                final TeamStat st = (TeamStat)stat;
                Collections.sort(copy, new Comparator<T>() {
                    public int compare(T arg0, T arg1) {
                        TeamObj t0 = (TeamObj)arg0;
                        TeamObj t1 = (TeamObj)arg1;
                        float t0stat = st.get(t0);
                        float t1stat = st.get(t1);
                        if (t0stat < t1stat) return -1;
                        if (t0stat == t1stat) return 0;
                        return 1;
                    }
                });
                return (BallList<T>) copy.subList(0, (int)num);
                           }
        }
        throw new IllegalArgumentException("arguments can't be 'top'-ed");
    }
    
    private static final Random myRand = new Random(new java.util.Date().getTime());
    private static final Random myRandFloat = new Random(new java.util.Date().getTime());

}
