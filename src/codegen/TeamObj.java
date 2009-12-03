import java.util.ArrayList;


public class TeamObj {

	public TeamObj(String name,int w, int l) {
		this.name = name;
		this.w = w;
		this.l = l;
	}
	
	public void addPlayer(playerObj p) {
		if (p.getType() == p.BATTER)
			batters.add(p);
		if (p.getType() == p.PITCHER)
			pitchers.add(p);
	}
	
	public ArrayList<playerObj> getBatters() {
		return batters;
	}
	
	public ArrayList<playerObj> getPitchers() {
		return pitchers;
	}
	
	public ArrayList<playerObj> getPlayers() {
		ArrayList<playerObj> players = new ArrayList<playerObj>();
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

	private String name;
	private int w;
	private int l;
	private ArrayList<playerObj> batters;
	private ArrayList<playerObj> pitchers;
}
