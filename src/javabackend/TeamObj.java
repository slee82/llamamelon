package javabackend;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * TeamObj.java - Team Class
 */

import java.util.ArrayList;


public class TeamObj {

	public TeamObj(String name,int w, int l) {
		this.name = name;
		this.w = w;
		this.l = l;
	}
	
	public void addPlayer(PlayerObj p) {
		if (p.getType() == PlayerObj.BATTER)
			batters.add(p);
		if (p.getType() == PlayerObj.PITCHER)
			pitchers.add(p);
	}
	
	public void setBatters(ArrayList<PlayerObj> a) {
		batters = a;
	}
	
	public void setPitchers(ArrayList<PlayerObj> a) {
		pitchers = a;
	}
	
	public ArrayList<PlayerObj> getBatters() {
		return batters;
	}
	
	public ArrayList<PlayerObj> getPitchers() {
		return pitchers;
	}
	
	public ArrayList<PlayerObj> getPlayers() {
		ArrayList<PlayerObj> players = new ArrayList<PlayerObj>();
		players.addAll(batters);
		players.addAll(pitchers);
		return players;
		
	}
	
	public String getName() {
		return name;
	}
	public int getW() {
		return w;
	}
	public int getL() {
		return l;
	}
	
	public String toString(){
		String toReturn = "";
		toReturn += "Team Name: " + name + "\n" + "Header:W,L\n";
		toReturn += Integer.toString(w) + "," + Integer.toString(l) + "\n\n";
		
		toReturn += "Type:Batter\nHeader:Name,AB,R,H,2B,3B,HR,BB\n";
		int i;
		for(i=0; i<batters.size(); i++){
			toReturn += batters.get(i) + "\n";
		}
		
		toReturn += "\nType:Pitcher\nHeader:Name,IP,H,ER,BB,K\n";
		for(i=0; i<pitchers.size(); i++){
			toReturn += pitchers.get(i) + "\n";
		}
		
		return toReturn;
	}

	private String name;
	private int w;
	private int l;
	private ArrayList<PlayerObj> batters;
	private ArrayList<PlayerObj> pitchers;
}
