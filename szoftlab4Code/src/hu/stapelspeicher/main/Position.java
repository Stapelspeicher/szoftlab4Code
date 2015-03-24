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
	 * @param p a Position amivel elosztjuk az amire meghívtuk a függvényt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position divide(Position p) {
		Logger.enterFunction("divide(Position p)", this);
		Logger.exitFunction();
		return new Position(x / p.x, y / p.y);
	}
	
	/**
	 * @param p a Position amihez hozzáadjuk azt amire meghívtuk a függvényt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position add(Position p) {
		Logger.enterFunction("add(Position p)", this);
		Logger.exitFunction();
		return new Position(x + p.x,y + p.y);
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
}
