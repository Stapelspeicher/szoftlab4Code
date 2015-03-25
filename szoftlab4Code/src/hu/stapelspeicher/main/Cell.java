package hu.stapelspeicher.main;

import java.util.ArrayList;
import java.util.List;

/**
 * A palyat felepito elemeket megvalosito osztaly
 * @author Csongor
 * 
 */

public class Cell {
	private List<ActiveObject> actors= new ArrayList<ActiveObject>();
	private Trap trap=null;
	private Position pos;
	private GameMap map;
	
	
	/**
	 * A cella poziciojat allitja be
	 * @param pos Az uj pozicio
	 */
	public void setPosition(Position pos){
		Logger.enterFunction("setPosition(Position pos)", this);
		
		this.pos = pos;
		
		Logger.exitFunction();
	}
	
	/**
	 * A cellahoz tartozo GameMap-et allitja be
	 * @param gm A jatek GameMap-je
	 */
	public void setMap(GameMap gm){
		Logger.enterFunction("setMap(GameMap gm)", this);
		
		this.map = gm;
		
		Logger.exitFunction();
	}
	
	/**
	 * A cellan egy ujabb mozgo objektumot helyez el
	 * es a szukseges utkozteteseket elvegzi a mozgo objektumok
	 * es foltok kozott
	 * @param ao Az uj mozgo objektum
	 */
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
	
	/**
	 * A cellan egy uj foltot helyez el
	 * @param t Az uj folt
	 */
	public void add(Trap t) {
		Logger.enterFunction("add(Trap t)", this);
		
		trap = t;
		
		Logger.exitFunction();
	}
	
	/**
	 * A cellatol megadott tavolsagra levo cellat adja meg
	 * @param p A megadott tavolsag
	 * @return Az uj cella
	 */
	public Cell getCellFromHere(Position p) {
		Logger.enterFunction("getCellFromHere(Position p)", this);		
		
		Cell c = map.getCell(pos.add(p));
		
		Logger.exitFunction();
		return c;
		}
	
	/**
	 * A cellarol eltavolit egy mozgo objektumot
	 * @param ao Az eltavolitando mozgo objektum
	 */
	public void remove(ActiveObject ao) {
		Logger.enterFunction("remove(ActiveObject ao)", this);
		
		actors.remove(ao);
		
		Logger.exitFunction();
	}
	
	/**
	 * Teszteli hogy a cellan vannak-e mozgo objektumok
	 * @return A cella ures (true)
	 */
	public boolean isEmpty() {
		Logger.enterFunction("isEmpty()", this);
		
		Logger.exitFunction();
		return actors.isEmpty();
	}
	
	/**
	 * Ket cella kozotti tavolsagot adja meg
	 * @param c A masodik cella
	 * @return A ket cella tavolsaga
	 */
	public double getDistanceFromCell(Cell c) {
		Logger.enterFunction("getDistanceFromCell(Cell c)", this);
		
		int xDistance = c.pos.getX() - pos.getX();
		int yDistance = c.pos.getY() - pos.getY();
		if(xDistance < 0) xDistance *= -1;
		if(yDistance < 0) yDistance *= -1;
		
		Logger.exitFunction();
		return xDistance + yDistance;
	}
	
	/**
	 * Egy szabad szomszedos cellat ad vissza
	 * @return A szabad szomszedos cella
	 */
	public Cell getFreeNeighbouringCell() {
		Logger.enterFunction("getFreeNeighbouringCell()", this);
		Logger.exitFunction();
		return map.getFreeNeighbouringCell(pos);
	}
	
}
