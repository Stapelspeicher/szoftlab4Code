package hu.stapelspeicher.modell;


/**
 * @author Ă�dĂˇm
 *
 */
public class Robot implements ActiveObject {

	private Position velocity;
	private Cell currCell;
	private boolean oily;
	private int stickyNum;
	private int oilNum;
	private int distance = 0;
	private boolean alive;

	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * A robot sebesseget adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert sebesseg
	 */	
	public Position getVelocityForTest(){
		return velocity;
	}
	
	/**
	 * A robot ragacskeszletet adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert ragacskeszlet
	 */	
	public int getStickyForTest(){
		return stickyNum;
	}
	
	/**
	 * A robot olajkeszletet adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert olajkeszlet
	 */	
	public int getOilyForTest(){
		return oilNum;
	}
	
	/**
	 * A robot osztaly ketparameteres konstruktora
	 * @param stickyNum - a robot hany ragaccsal indul
	 * @param oilyNum - a robot hany olajjal indul
	 */
	public Robot(int stickyNum, int oilyNum) {
		Logger.enterFunction("Robot(int stickyNum, int oilyNum()", this);
		this.stickyNum = stickyNum;
		this.oilNum = oilyNum;
		velocity = new Position(0, 0);
		alive = true;
		Logger.exitFunction();
	}

	/**
	 * A robot elhelyezi a ragacsot, amennyiben van neki legalabb egy elhelyezheto ragacsa
	 */
	public void placeSticky() {
		Logger.enterFunction("placeSticky()", this);
		if (stickyNum > 0){
			currCell.add(new Sticky());
			stickyNum--;
		}
			
		Logger.exitFunction();
	}

	/**
	 * A robot elhelyezi az olajfoltot, amennyiben van neki legalabb egy elhelyezheto olaja
	 */
	public void placeOily() {
		Logger.enterFunction("placeOily()", this);
		if (oilNum > 0){
			currCell.add(new Oily());
			oilNum--;
		}
			
		Logger.exitFunction();
	}

	/**
	 * Amennyiben nem olajfolton all a robot, akkor megvaltozik a sebessege
	 * @param p - ez hatarozza meg, hogy mennyivel es milyen iranyba valtozik meg a sebesseg
	 */
	public void addVelocity(Position p) {
		Logger.enterFunction("addVelocity(Position p)", this);
		if(oily){
			return;
		}
		else{
			velocity = velocity.add(p);
		}
		Logger.exitFunction();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#stepOn(hu.stapelspeicher.main.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao) {
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.collideWithRobot(this);
		Logger.exitFunction();
	}

	@Override
	public void oilyEffect() {
		Logger.enterFunction("oilyEffect()", this);
		oily = true;
		Logger.exitFunction();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#stickyEffect()
	 */
	@Override
	public void stickyEffect() {
		Logger.enterFunction("stickyEffect()", this);
		velocity = velocity.divide(new Position(2, 2));
		Logger.exitFunction();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#collideWithRobot(hu.stapelspeicher.main.Robot)
	 */
	@Override
	public void collideWithRobot(Robot other) {
		Logger.enterFunction("collideWithRobot(Robot other)", this);
		if(velocity.compareTo(other.velocity)>0){
			other.die();
			velocity=velocity.add(other.velocity).divide(new Position(2, 2));
		}
		else if(velocity.compareTo(other.velocity)<0){
			other.velocity=other.velocity.add(velocity).divide(new Position(2, 2));
			die();
		}
		else{
			other.die();
			die();
		}
		Logger.exitFunction();
	}
	
	
	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#die()
	 */
	@Override
	public void die() {
		Logger.enterFunction("die()", this);
		currCell.remove(this);
		alive = false;
		Logger.exitFunction();
	}	

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#setCell(hu.stapelspeicher.main.Cell)
	 */
	@Override
	public void setCell(Cell c) {
		Logger.enterFunction("setCell(Cell c)", this);
		currCell = c;
		Logger.exitFunction();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#step()
	 */
	@Override
	public void step() {
		Logger.enterFunction("step()", this);
		Cell newCell = currCell.getCellFromHere(velocity);
		if (newCell == null) {
			this.die();
		} else {
			distance += currCell.getDistanceFromCell(newCell);
			currCell.remove(this);
			currCell=newCell;
			newCell.add(this);
		}
		Logger.exitFunction();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#getCell()
	 */
	@Override
	public Cell getCell() {
		Logger.enterFunction("getCell()", this);
		Logger.exitFunction();
		return this.currCell;
	}
	
	public int getDistance() {
		Logger.enterFunction("getDistance", this);
		Logger.exitFunction();
		return distance;
	}

	@Override
	public void collideWithLittleRobot(LittleRobot other) {
		other.die();
		currCell.add(new Oily());
	}

}
