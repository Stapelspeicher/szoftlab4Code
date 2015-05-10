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
	 * @return a robot sebessege
	 */	
	public Position getVelocity(){
		return velocity;
	}
	
	/**
	 * @return a robot ragacskeszlete
	 */	
	public int getSticky(){
		return stickyNum;
	}
	
	/**
	 * @return a robot olajkeszlete
	 */	
	public int getOily(){
		return oilNum;
	}
	
	/**
	 * A robot osztaly ketparameteres konstruktora
	 * @param stickyNum - a robot hany ragaccsal indul
	 * @param oilyNum - a robot hany olajjal indul
	 */
	public Robot(int stickyNum, int oilyNum) {
		this.stickyNum = stickyNum;
		this.oilNum = oilyNum;
		velocity = new Position(0, 0);
		alive = true;
	}

	/**
	 * A robot elhelyezi a ragacsot, amennyiben van neki legalabb egy elhelyezheto ragacsa
	 */
	public void placeSticky() {
		if (stickyNum > 0){
			currCell.add(new Sticky());
			stickyNum--;
		}
	}

	/**
	 * A robot elhelyezi az olajfoltot, amennyiben van neki legalabb egy elhelyezheto olaja
	 */
	public void placeOily() {
		if (oilNum > 0){
			currCell.add(new Oily());
			oilNum--;
		}
	}

	/**
	 * Amennyiben nem olajfolton all a robot, akkor megvaltozik a sebessege
	 * @param p - ez hatarozza meg, hogy mennyivel es milyen iranyba valtozik meg a sebesseg
	 */
	public void addVelocity(Position p) {
		if(oily){
			return;
		}
		else{
			velocity = velocity.add(p);
		}
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#stepOn(hu.stapelspeicher.main.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao) {
		ao.collideWithRobot(this);
	}

	@Override
	public void oilyEffect() {
		oily = true;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#stickyEffect()
	 */
	@Override
	public void stickyEffect() {
		velocity = velocity.divide(new Position(2, 2));
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#collideWithRobot(hu.stapelspeicher.main.Robot)
	 */
	@Override
	public void collideWithRobot(Robot other) {
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
	}
	
	
	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#die()
	 */
	@Override
	public void die() {
		currCell.remove(this);
		alive = false;
	}	

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#setCell(hu.stapelspeicher.main.Cell)
	 */
	@Override
	public void setCell(Cell c) {
		currCell = c;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#step()
	 */
	@Override
	public void step() {
		Cell newCell = currCell.getCellFromHere(velocity);
		if (newCell == null) {
			this.die();
		} else {
			distance += currCell.getDistanceFromCell(newCell);
			currCell.remove(this);
			currCell=newCell;
			newCell.add(this);
		}
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.ActiveObject#getCell()
	 */
	@Override
	public Cell getCell() {
		return this.currCell;
	}
	
	/**
	 * @return a robot altal megtett tavolsag
	 */
	public int getDistance() {
		return distance;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#collideWithLittleRobot(hu.stapelspeicher.modell.LittleRobot)
	 */
	@Override
	public void collideWithLittleRobot(LittleRobot other) {
		other.die();
		currCell.add(new Oily());
	}

}
