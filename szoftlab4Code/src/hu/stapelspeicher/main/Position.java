package hu.stapelspeicher.main;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		Logger.enterFunction("Position(int x, int y)", this);
		this.x = x;
		this.y = y;
		Logger.exitFunction();
	}
	
	public int getX() {
		Logger.enterFunction("getX()", this);
		Logger.exitFunction();
		return x;
	}
	public int getY() {
		Logger.enterFunction("getY()", this);
		Logger.exitFunction();
		return y;
	}
	
	public Position divide(Position p) {
		Logger.enterFunction("divide(Position p)", this);
		Logger.exitFunction();
		return new Position(x / p.x, y / p.y);
	}
	
	public Position add(Position p) {
		Logger.enterFunction("add(Position p)", this);
		Logger.exitFunction();
		return new Position(x + p.x,y + p.y);
	}
	
	public double getDistance(Position p) {
		Logger.enterFunction("getDistance()", this);
		Logger.exitFunction();
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
