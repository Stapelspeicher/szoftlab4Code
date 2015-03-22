package hu.stapelspeicher.main;

public class Robot implements ActiveObject {

	private Position velocity;
	private Cell currCell;
	private boolean oily;
	private int stickyNum;
	private int oilNum;
	private double distance;
	private static int random = 0; // ez majd itt egy random szám kell hogy
									// legyen, ami a robot kezdõpozícióját adja
									// meg, de jelen esetben a tesztelés miatt
									// egy nulláról inkrementálódó szám lesz

	Robot(int stickyNum, int oilyNum) {
		Logger.enterFunction("Robot(int stickyNum, int oilyNum()", this);
		this.stickyNum = stickyNum;
		this.oilNum = oilyNum;
		Logger.exitFunction();
	}

	public void placeSticky() {
		Logger.enterFunction("placeSticky()", this);
		if (stickyNum > 0)
			currCell.add(new Sticky());
		Logger.exitFunction();
	}

	public void placeOily() {
		Logger.enterFunction("placeOily()", this);
		if (oilyNum > 0)
			currCell.add(new Oily());
		Logger.exitFunction();
	}

	public void addVelocity(Position p) {
		Logger.enterFunction(addVelocity(Position p));
		if(oily){
			return
		}
		else{
			velocity.add(p);
		}
		Logger.exitFunction();
	}

	@Override
	public void stepOn(ActiveObject ao) {
		Logger.enterFunction("stepOn(ActiveObject ao)");
		ao.collideWithRobot(this);
		Logger.exitFunction();
	}

	@Override
	public void oilyEffect() {
		Logger.enterFunction("oilyEffect()", this);
		oily = true;
		Logger.exitFunction();
	}

	@Override
	public void stickyEffect() {
		Logger.enterFunction("stickyEffect()", this);
		this.velocity.divide(new Position(2, 2));
		Logger.exitFunction();
	}

	@Override
	public void collideWithRobot(Robot other) {
		Logger.enterFunction("collideWithRobot(Robot other)", this);
		Cell freeCell = currCell.getFreeNeighbouringCell();
		if (freeCell == null) {
			other.die();
			this.die();
		} else {
			currCell.remove(this);
			freeCell.add(this);
		}
		Logger.exitFunction();
	}

	@Override
	public void die() {
		Logger.enterFunction("die()", this);
		currCell.remove(this);
		Logger.exitFunction();
	}

	@Override
	public boolean setCell() {
		Logger.enterFunction("setCell()", this);
		this.currCell = GameMap.getCell(new Position(random, random));
		random++;
		Logger.exitFunction();
		return true;
	}

	@Override
	public void step() {
		Logger.enterFunction("step()", this);
		Cell newCell = currCell.getCellFromHere(velocity);
		if (newCell == null) {
			this.die();
		} else {
			currCell.remove(this);
			newCell.add(this);
		}
		Logger.exitFunction();
	}

	@Override
	public Cell getCell() {
		Logger.enterFunction("getCell()", this);
		return this.currCell;
		Logger.exitFunction();
	}

}
