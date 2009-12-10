package javabackend;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * SimFunction.java - the simulation function interface
 */


public interface SimFunction {
    public TeamObj doSim(TeamObj team1, TeamObj team2);
}
