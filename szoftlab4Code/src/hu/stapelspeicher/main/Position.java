package hu.stapelspeicher.main;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		Logger.enterFunction("Position(int x, int y)", this);
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
		
		Position temp = new Position(0,0);
		temp.x = this.x / p.x;
		temp.y = this.y / p.y;
		
		Logger.exitFunction();
		return temp;
	}
	
	public Position add(Position p) {
		Logger.enterFunction("add(Position p)", this);
		
		Position temp = new Position(0,0);
		temp.x = this.x / p.x;
		temp.y = this.y / p.y;
		
		Logger.exitFunction();
		return temp;
	}
	
	public double getDistance(Position p) { //TODO
		Logger.enterFunction("getDistance()", this);
		Logger.exitFunction();
		return 1;
	}
}
