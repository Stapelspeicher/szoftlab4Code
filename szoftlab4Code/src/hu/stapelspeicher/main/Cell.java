package hu.stapelspeicher.main;

import java.util.List;

public class Cell {
	private List<ActiveObject> actors;
	private Trap trap;
	private Position pos;
	private GameMap map;
	
	public void add(ActiveObject ao) {
		Logger.enterFunction("add(ActiveObject ao)", this);
		
		actors.add(ao);
		
		Logger.exitFunction();
	}
	
	public void add(Trap t) {
		Logger.enterFunction("add(Trap t)", this);
		
		trap = t;
		
		Logger.exitFunction();
	}
	
	public Cell getCellFromHere(Position p) {
		Logger.enterFunction("getCellFromHere(Position p)", this);		
		
		int newX = pos.getX() + p.getX();
		int newY = pos.getY() + p.getY();
		map.getCell(new Position(newX, newY));
		
		Logger.exitFunction();
		return null;}
	
	public void remove(ActiveObject ao) {
		Logger.enterFunction("remove(ActiveObject ao)", this);
		
		actors.remove(ao);
		
		Logger.exitFunction();
	}
	
	public boolean isEmpty() {
		Logger.enterFunction("isEmpty()", this);
		
		Logger.exitFunction();
		return actors.isEmpty();
	}
	
	public double getDistanceFromCell(Cell c) {
		Logger.enterFunction("getDistanceFromCell(Cell c)", this);
		
		int xDistance = c.pos.getX() - pos.getX();
		int yDistance = c.pos.getX() - pos-getY();
		if(xDistance < 0) xDistance *= -1;
		if(yDistance < 0) yDistance *= -1;
		
		Logger.exitFunction();
		return xDistance + yDistance;
	}
	
	public Cell getFreeNeighbouringCell() {
		Logger.enterFunction("getFreeNeighbouringCell()", this);
		
		Position newPos = new Position(pos.getX(), pos.getY());
		newPos.add(new Position(0,1));
		if(map.getCell(newPos).isEmpty()) {
			Logger.exitFunction();
			return map.getCell(newPos);
		}
		newPos.add(new Position(1,-1));
		if(map.getCell(newPos).isEmpty()) {
			Logger.exitFunction();
			return map.getCell(newPos);
		}
		newPos.add(new Position(-1,-1));
		if(map.getCell(newPos).isEmpty()) {
			Logger.exitFunction();
			return map.getCell(newPos);
		}
		newPos.add(new Position(-1,1));
		if(map.getCell(newPos).isEmpty()) {
			Logger.exitFunction();
			return map.getCell(newPos);
		}
			
		
		Logger.exitFunction();
		return null;
	}
	
}
