package hu.stapelspeicher.modell;

/**
 * A palyat felepito elemeket megvalosito osztaly
 * @author Csongor
 * 
 */

public class Cell {
	private ActiveObject presentAO = null;
	private ActiveObject incomingAO = null;
	private Trap trap=null;
	private Position pos;
	private GameMap map;
	
	/**
	 * @return a cellan talalhato csapda
	 */
	public Trap getTrap(){
		return trap;
	}
	
	/**
	 * @return a cella koordinatai
	 */
	public Position getPosition(){
		return pos;
	}
	
	/**
	 * A cella poziciojat allitja be
	 * @param pos Az uj pozicio
	 */
	public void setPosition(Position pos){
		this.pos = pos;
	}
	
	/**
	 * A cellahoz tartozo GameMap-et allitja be
	 * @param gm A jatek GameMap-je
	 */
	public void setMap(GameMap gm){
		this.map = gm;
	}
	
	/**
	 * A cellan egy ujabb mozgo objektumot helyez el,
	 * ha a cellan folt talahato kifejti hatasat a mozgo objektumra,
	 * majd a mozgo objektumokat utkozteti
	 * es foltok kozott
	 * @param ao Az uj mozgo objektum
	 */
	public void add(ActiveObject ao) {
		incomingAO = ao;
		if(trap!=null){
			trap.stepOn(ao);
			if(trap.abrade())
				trap=null;
		}	
		
		if(presentAO!=null)
			presentAO.stepOn(incomingAO);
		
		if(incomingAO!=null)
			presentAO = incomingAO;
	}
	
	/**
	 * A cellan egy uj foltot helyez el, az elozo folt eltunik
	 * @param t Az uj folt
	 */
	public void add(Trap t) {
		trap = t;
	}
	
	/**
	 * A cellatol megadott vektor tavolsagra levo cellat adja meg,
	 * amit a GameMap-tol ker el
	 * @param p A megadott tavolsag vektor
	 * @return A kert cella
	 */
	public Cell getCellFromHere(Position p) {
		Cell c = map.getCell(pos.add(p));
		return c;
	}
	
	/**
	 * A cellarol eltavolitja a megadott mozgo objektumot
	 * @param ao Az eltavolitando mozgo objektum
	 */
	public void remove(ActiveObject ao) {
		if(presentAO!=null)
			if(presentAO.equals(ao))
				presentAO=null;
		if(incomingAO!=null)
			if(incomingAO.equals(ao))
				incomingAO=null;
	}
	
	/**
	 * Teszteli hogy a cellan vannak-e mozgo objektumok
	 * @return A cella ures (true)
	 */
	public boolean isEmpty() {
		return (presentAO==null && incomingAO==null);
	}
	
	/**
	 * Ket cella kozotti tavolsagot adja meg, a koordinatakulonbsegek osszegebol
	 * @param c A masodik cella
	 * @return A ket cella tavolsaga
	 */
	public double getDistanceFromCell(Cell c) {
		int xDistance = c.pos.getX() - pos.getX();
		int yDistance = c.pos.getY() - pos.getY();
		if(xDistance < 0) xDistance *= -1;
		if(yDistance < 0) yDistance *= -1;
		return xDistance + yDistance;
	}
	
	/**
	 * Egy szabad szomszedos cellat ad vissza, amit a GameMap-tol ker el,
	 * ha nincs ilyen nullaval ter vissza
	 * @return A szabad szomszedos cella
	 */
	public Cell getFreeNeighbouringCell() {
		return map.getFreeNeighbouringCell(pos);
	}

	/**
	 * Szaritja a csapdat, es ha kiszaradt, akkor torli.
	 */
	public void dry() {
		if(trap!=null){
			if(trap.dry())
				trap=null;
		}
	}

	/**
	 * Megkeresi a legkozelebbi csapdat.
	 * @return A legkozelebbi csapda relativ pozicioja.
	 */
	public Position getNearestTrapRelativePosition() {
		if(trap!=null) return new Position(0,0);
		boolean limitNotReached = true;
		int distance = 1;
		Cell c = null;
		while(limitNotReached){
			limitNotReached=false;
			for(int i=0; i<=distance; i++){
				c = getCellFromHere(new Position(i, distance-i));
				if(c!=null){
					limitNotReached=true;
					if(c.trap!=null)
						return c.pos.subtract(pos);
				}
				c = getCellFromHere(new Position(-i, distance-i));
				if(c!=null){
					limitNotReached=true;
					if(c.trap!=null)
						return c.pos.subtract(pos);
				}
				c = getCellFromHere(new Position(i, i-distance));
				if(c!=null){
					limitNotReached=true;
					if(c.trap!=null)
						return c.pos.subtract(pos);
				}
				c = getCellFromHere(new Position(-i, i-distance));
				if(c!=null){
					limitNotReached=true;
					if(c.trap!=null)
						return c.pos.subtract(pos);
				}
			}
			distance++;
		}
		return null;
	}

	/**
	 * Eltavolitja a csapdat a cellarol.
	 */
	public void removeTrap() {
		trap=null;
	}
	
}
