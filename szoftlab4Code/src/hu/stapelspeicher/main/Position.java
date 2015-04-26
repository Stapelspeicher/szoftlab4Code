package hu.stapelspeicher.main;

/**
 * 
 * A jatek koordinatait reprezentalo osztaly
 * 
 * @author Kkari
 *
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * @param x Az x koordináta
	 * @param y Az y koordináta
	 */
	public Position(int x, int y) {
		Logger.enterFunction("Position(int x, int y)", this);
		this.x = x;
		this.y = y;
		Logger.exitFunction();
	}
	
	/**
	 * @return a pozicio x koordinátája
	 */
	public int getX() {
		Logger.enterFunction("getX()", this);
		Logger.exitFunction();
		return x;
	}
	/**
	 * @return a pozicio y koordinátája
	 */
	public int getY() {
		Logger.enterFunction("getY()", this);
		Logger.exitFunction();
		return y;
	}
	
	/**
	 * @param p a Position amivel elosztjuk azt, amire meghívtuk a függvényt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position divide(Position p) {
		Logger.enterFunction("divide(Position p)", this);
		Logger.exitFunction();
		return new Position(x / p.x, y / p.y);
	}
	
	/**
	 * @param p a Position amihez hozzáadjuk azt, amire meghívtuk a függvényt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position add(Position p) {
		Logger.enterFunction("add(Position p)", this);
		Logger.exitFunction();
		return new Position(x + p.x, y + p.y);
	}
	
	/**
	 * @param p a Position amihez hozzáadjuk azt, amire meghívtuk a függvényt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position subtract(Position p) {
		Logger.enterFunction("add(Position p)", this);
		Logger.exitFunction();
		return new Position(x - p.x,y - p.y);
	}
	
	/**
	 * @param p a másik pont, amihez kepest a tavolsagot ellenorizni akarjuk
	 * @return a ket pont kozotti tavolsag
	 */
	public double getDistance(Position p) {
		Logger.enterFunction("getDistance()", this);
		Logger.exitFunction();
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * ket position objektumot hasonlit ossze a vektor hossza alapjan.
	 * ha az aktuális objektum hosszabb mint a parameterkent kapott,
	 * akkor 1 a visszateresei ertek, ha egyenloek akkor 0,
	 * kulonben -1
	 * @param other a masik position objektum
	 * @return 1, ha az aktualis objektum hosszabb, 0 ha egyenloek, kulonben -1
	 */
	public int compareTo(Position other){
		int this_len = x*x+y*y;
		int other_len = other.x*other.x+other.y*other.y;
		if(this_len==other_len)
			return 0;
		if(this_len>other_len)
			return 1;
		return -1;
	}
}
