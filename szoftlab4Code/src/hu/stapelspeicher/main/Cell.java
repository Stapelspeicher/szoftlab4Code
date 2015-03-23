package hu.stapelspeicher.main;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private List<ActiveObject> actors= new ArrayList<ActiveObject>();
	private Trap trap=null;
	private Position pos;
	private GameMap map;
	
	public void setPosition(Position pos){
		Logger.enterFunction("setPosition(Position pos)", this);
		
		this.pos = pos;
		
		Logger.exitFunction();
	}
	
	public void setMap(GameMap gm){
		Logger.enterFunction("setMap(GameMap gm)", this);
		
		this.map = gm;
		
		Logger.exitFunction();
	}
	
	public void add(ActiveObject ao) {
		Logger.enterFunction("add(ActiveObject ao)", this);
		
		actors.add(ao);
		if(trap!=null)
			trap.stepOn(ao);
		for(ActiveObject actor : actors) {
			if(actor != ao) {
				actor.stepOn(ao);
			}
		}
		Logger.exitFunction();
	}
	
	public void add(Trap t) {
		Logger.enterFunction("add(Trap t)", this);
		
		trap = t;
		
		Logger.exitFunction();
	}
	
	public Cell getCellFromHere(Position p) {
		Logger.enterFunction("getCellFromHere(Position p)", this);		
		
		Cell c = map.getCell(pos.add(p));
		
		Logger.exitFunction();
		return c;
		}
	
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
		int yDistance = c.pos.getY() - pos.getY();
		if(xDistance < 0) xDistance *= -1;
		if(yDistance < 0) yDistance *= -1;
		
		Logger.exitFunction();
		return xDistance + yDistance;
	}
	
	public Cell getFreeNeighbouringCell() {
		Logger.enterFunction("getFreeNeighbouringCell()", this);
		Logger.exitFunction();
		return map.getFreeNeighbouringCell(pos);
	}
	
}
