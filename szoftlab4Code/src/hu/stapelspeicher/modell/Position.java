package hu.stapelspeicher.modell;

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
	 * @param x Az x koordin�ta
	 * @param y Az y koordin�ta
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return a pozicio x koordin�t�ja
	 */
	public int getX() {
		return x;
	}
	/**
	 * @return a pozicio y koordin�t�ja
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param p a Position amivel elosztjuk azt, amire megh�vtuk a f�ggv�nyt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position divide(Position p) {
		return new Position(x / p.x, y / p.y);
	}
	
	/**
	 * @param p a Position amihez hozz�adjuk azt, amire megh�vtuk a f�ggv�nyt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position add(Position p) {
		return new Position(x + p.x, y + p.y);
	}
	
	/**
	 * @param p a Position amihez hozz�adjuk azt, amire megh�vtuk a f�ggv�nyt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position subtract(Position p) {
		return new Position(x - p.x,y - p.y);
	}
	
	/**
	 * @param p a m�sik pont, amihez kepest a tavolsagot ellenorizni akarjuk
	 * @return a ket pont kozotti tavolsag
	 */
	public double getDistance(Position p) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * ket position objektumot hasonlit ossze a vektor hossza alapjan.
	 * ha az aktu�lis objektum hosszabb mint a parameterkent kapott,
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
