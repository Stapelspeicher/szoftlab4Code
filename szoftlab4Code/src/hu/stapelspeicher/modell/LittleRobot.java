package hu.stapelspeicher.modell;



public class LittleRobot implements ActiveObject {

	private LittleRobotState state=LittleRobotState.NORMAL;
	private int dazedCounter=0;
	private int cleaningCounter=0;
	private Cell currCell=null;
	private boolean alive=true;
	
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * A kisrobot ....
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert ....
	 */	
	public int getDazedCounterForTest(){
		return dazedCounter;
	}
	
	/**
	 * A kisrobot ....
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert ....
	 */	
	public int getCleaningCounterForTest(){
		return cleaningCounter;
	}
	
	/**
	 * A kisrobot allapotat adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert allapot
	 */	
	public LittleRobotState getStateForTest(){
		return state;
	}
	
	
	/**
	 * ha szukseges, a kisrobot elkezdi a takaritast
	 */
	private void startCleaningIfNeeded(){
		Position p = currCell.getNearestTrapRelativePosition();
		if(p!=null){
			if(p.getX()==0 && p.getY()==0){
				cleaningCounter=2;
				state = LittleRobotState.CLEANING;
			}
		}

	}
	
	/**
	 * beallitja a kisrobot allapotat
	 * @param newState az uj allapot
	 */
	public void setState(LittleRobotState newState){
		state = newState;
	}
	
	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#stepOn(hu.stapelspeicher.modell.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao) {
		ao.collideWithLittleRobot(this);
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#oilyEffect()
	 */
	@Override
	public void oilyEffect() {
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#stickyEffect()
	 */
	@Override
	public void stickyEffect() {
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#collideWithRobot(hu.stapelspeicher.modell.Robot)
	 */
	@Override
	public void collideWithRobot(Robot other) {
		state = LittleRobotState.DAZED;
		dazedCounter=2;
		Cell freeCell = currCell.getFreeNeighbouringCell();
		if(freeCell != null){
			currCell.remove(this);
			currCell=freeCell;
			freeCell.add(this);
		}
		else die();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#die()
	 */
	@Override
	public void die() {
		currCell.remove(this);
		alive = false;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#setCell(hu.stapelspeicher.modell.Cell)
	 */
	@Override
	public void setCell(Cell c) {
		currCell = c;
		Position p = c.getNearestTrapRelativePosition();
		if(p==null){
			state = LittleRobotState.NORMAL;
		}
		else{
			if(p.getX()==0 && p.getY()==0){
				state = LittleRobotState.CLEANING;
				cleaningCounter = 2;
			}
		}	
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#step()
	 */
	@Override
	public void step() {
		Cell c = null;
		switch (state) {
		case DAZED:
			c = currCell.getFreeNeighbouringCell();
			currCell.remove(this);
			currCell = c;
			c.add(this);
			dazedCounter--;
			if(dazedCounter==0)
				state = LittleRobotState.NORMAL;
			startCleaningIfNeeded();
			break;
		case NORMAL:
			Position p = currCell.getNearestTrapRelativePosition();
			if(p!=null){
				if(Math.abs(p.getX())>Math.abs(p.getY()))
				c = currCell.getCellFromHere(new Position(p.getX()/Math.abs(p.getX()),0));
			else
				c = currCell.getCellFromHere(new Position(0,p.getY()/Math.abs(p.getY())));
			currCell.remove(this);
			currCell=c;
			c.add(this);
			startCleaningIfNeeded();
			}
			break;
		case CLEANING:
			cleaningCounter--;
			if(cleaningCounter==0){
				state = LittleRobotState.NORMAL;
				currCell.removeTrap();
			}
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#getCell()
	 */
	@Override
	public Cell getCell() {
		return currCell;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.ActiveObject#collideWithLittleRobot(hu.stapelspeicher.modell.LittleRobot)
	 */
	@Override
	public void collideWithLittleRobot(LittleRobot other) {
		state = LittleRobotState.DAZED;
		dazedCounter = 2;
		Cell freeCell = currCell.getFreeNeighbouringCell();
		if(freeCell!=null){
			currCell.remove(this);
			currCell=freeCell;
			freeCell.add(this);
		}
		else die();
	}

}
