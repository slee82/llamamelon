package javabackend;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * playerObj.java - Players class
 */

public class PlayerObj implements BallDataType {
    
    public boolean match(Object other) {
        return this == other; // reference equality
    }

	/* This is the batter constructor */
	public PlayerObj (String name, int type, int ab, int r,
			int h, int dbl, int tpl, int hr, int bb) {
		if (type != BATTER) {
			System.err.println("Incorrect Player Type Found");
			return;
		}
		this.name = name;
		this.type = type;
		this.ab = ab;
		this.r = r;
		this.h = h;
		this.dbl = dbl;
		this.tpl = tpl;
		this.hr = hr;
		this.bb = bb;
	}
	
	// This is the pitcher constructor
	public PlayerObj (String name, int type, double ipp, int h,
			int er, int bb, int k) {
		if (type != PITCHER) {
			System.err.println("Incorrect Player Type Found");
			return;
		}
		float ip = (float)ipp;
		this.name = name;
		this.type = type;
		if ((int)ip == ip)
			this.ip = ip;
		else {
			this.ip = (int)ip;
			if (ip - (int)ip == .1)
				this.ip += (1/3);
			else if (ip - (int)ip == .2)
				this.ip += (2/3);
			else {
				System.err.println("Incorrect Innings Pitched Value");
				return;
			}
		}
		this.h = h;
		this.er = er;
		this.bb = bb;
		this.k = k;
	}
	
	private int type;
	public int getType() {
		return type;
	}

	public int getAb() {
		if (type != BATTER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return ab;
	}

	public int getR() {
		if (type != BATTER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return r;
	}

	public int getH() {
		return h;
	}

	public int getDbl() {
		if (type != BATTER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return dbl;
	}

	public int getTpl() {
		if (type != BATTER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return tpl;
	}

	public int getHr() {
		if (type != BATTER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return hr;
	}

	public int getBb() {
		return bb;
	}

	public float getIp() {
		if (type != PITCHER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return ip;
	}

	public int getEr() {
		if (type != PITCHER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return er;
	}

	public int getK() {
		if (type != PITCHER) {
			System.err.println("Incorrect Statistic Requested");
			return -1;
		}
		return k;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString(){
		String toReturn = "";
		
		if(type == BATTER){	//Create concatenation depending on player type
			toReturn = name+","+ab+","+r+","+h+","+dbl+","+tpl+","+hr+","+bb;
		}else {
			toReturn += name+","+ip+","+h+","+er+","+bb+","+k;
		}
		
		return toReturn;
	}
	
	public static final PlayerStat AB = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getAb();
		}
	};
	public static final PlayerStat R = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getR();
		}
	};
	public static final PlayerStat H = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getH();
		}
	};
	public static final PlayerStat DBL = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getDbl();
		}
	};
	public static final PlayerStat TPL = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getTpl();
		}
	};
	public static final PlayerStat HR = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getHr();
		}
	};
	public static final PlayerStat BB = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getBb();
		}
	};
	public static final PlayerStat IP = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getIp();
		}
	};
	public static final PlayerStat ER = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getEr();
		}
	};
	public static final PlayerStat K = new PlayerStat() {
		public float get(PlayerObj player) {
			return player.getK();
		}
	};

	private String name;

	private int ab;
	private int r;
	private int h;
	private int dbl;
	private int tpl;
	private int hr;
	private int bb;
	private float ip;
	private int er;
	private int k;
	
	public final static int PITCHER = 1;
	public final static int BATTER = 2;
}
