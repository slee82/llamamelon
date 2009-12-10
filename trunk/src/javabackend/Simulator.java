package javabackend;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Simulator.java - the sim() wrapper function
 */

import java.util.*;
import java.io.*;

public class Simulator {
    //Main function just for testing
    public static void main(String args[]){
	theSimFunction = new SimFunction(){
			public TeamObj doSim(TeamObj team1, TeamObj team2){
				return team1;
			}
		};

	System.out.println(sim(Loader.load("dodgers.team"), Loader.load("astros.team"), 1));
    }

    public static TeamObj sim(TeamObj team1, TeamObj team2, float number){
	int team1Wins=0;
	int team2Wins=0;

	int timesToPlay = (int) number;

	int plays=0;
	while(team1Wins==team2Wins || plays < timesToPlay){
		if(theSimFunction.doSim(team1, team2) == team1)
		    team1Wins++;
		else if(theSimFunction.doSim(team1, team2) == team2)
		    team2Wins++;
		plays++;
		if (plays > timesToPlay+20){
		    System.out.println("Could not determine a winner after 20 tries, returning team1.");
		    return team1;
		}
		
	}
	
	if(team1Wins>team2Wins)
		return team1;
	else
		return team2;
    }

    public static SimFunction theSimFunction;
}
