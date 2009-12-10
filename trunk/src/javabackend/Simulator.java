package javabackend;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Simulator.java - the sim() wrapper function
 */

import java.util.*;
import java.io.*;

public class Simulator {
    public static void main(String args[]){
	theSimFunction = new SimFunction(){
			public TeamObj doSim(TeamObj team1, TeamObj team2){
				return team1;
			}
		};

	System.out.println(sim(Loader.load("dodgers.team"), Loader.load("astros.team"), 1));
    }

    public static TeamObj sim(TeamObj team1, TeamObj team2, float number){
    	return theSimFunction.doSim(team1, team2);
    }

    public static SimFunction theSimFunction;
}
