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
	 * @param x Az x koordin�ta
	 * @param y Az y koordin�ta
	 */
	public Position(int x, int y) {
		Logger.enterFunction("Position(int x, int y)", this);
		this.x = x;
		this.y = y;
		Logger.exitFunction();
	}
	
	/**
	 * @return a pozicio x koordin�t�ja
	 */
	public int getX() {
		Logger.enterFunction("getX()", this);
		Logger.exitFunction();
		return x;
	}
	/**
	 * @return a pozicio y koordin�t�ja
	 */
	public int getY() {
		Logger.enterFunction("getY()", this);
		Logger.exitFunction();
		return y;
	}
	
	/**
	 * @param p a Position amivel elosztjuk az amire megh�vtuk a f�ggv�nyt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position divide(Position p) {
		Logger.enterFunction("divide(Position p)", this);
		Logger.exitFunction();
		return new Position(x / p.x, y / p.y);
	}
	
	/**
	 * @param p a Position amihez hozz�adjuk azt amire megh�vtuk a f�ggv�nyt
	 * 		 mivel a Position immutable, ezert uj objektumot ad vissza
	 * @return az uj Position objektum
	 */
	public Position add(Position p) {
		Logger.enterFunction("add(Position p)", this);
		Logger.exitFunction();
		return new Position(x + p.x,y + p.y);
	}
	
	/**
	 * @param p a m�sik pont, amihez kepest a tavolsagot ellenorizni akarjuk
	 * @return a ket pont kozotti tavolsag
	 */
	public double getDistance(Position p) {
		Logger.enterFunction("getDistance()", this);
		Logger.exitFunction();
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
