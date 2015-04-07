package hu.stapelspeicher.main;


public class LittleRobot implements ActiveObject {

	private LittleRobotState state=LittleRobotState.NORMAL;
	private int dazedCounter=0;
	private int cleaningCounter=0;
	private Cell currCell=null;
	
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
	
	
	private void startCleaningIfNeeded(){
		Position p = currCell.getNearestTrapRelativePosition();
		if(p.getX()==0 && p.getY()==0){
			cleaningCounter=2;
			state = LittleRobotState.CLEANING;
		}
	}
	
	public void setState(LittleRobotState newState){
		state = newState;
	}
	
	@Override
	public void stepOn(ActiveObject ao) {
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.collideWithLittleRobot(this);
		Logger.exitFunction();
	}

	@Override
	public void oilyEffect() {
	}

	@Override
	public void stickyEffect() {
	}

	@Override
	public void collideWithRobot(Robot other) {
		state = LittleRobotState.DAZED;
		dazedCounter=2;
		Cell freeCell = currCell.getFreeNeighbouringCell();
		if(freeCell != null){
			currCell.remove(this);
			freeCell.add(this);
			currCell=freeCell;
		}
		else die();
	}

	@Override
	public void die() {
		currCell.remove(this);
	}

	@Override
	public void setCell(Cell c) {
		currCell = c;
		if(c.getNearestTrapRelativePosition().getX()==0 &&
		   c.getNearestTrapRelativePosition().getY()==0){
			state = LittleRobotState.CLEANING;
			cleaningCounter = 2;
		}
			
	}

	@Override
	public void step() {
		Cell c = null;
		switch (state) {
		case DAZED:
			c = currCell.getFreeNeighbouringCell();
			currCell.remove(this);
			c.add(this);
			currCell = c;
			dazedCounter--;
			if(dazedCounter==0)
				state = LittleRobotState.NORMAL;
			startCleaningIfNeeded();
			break;
		case NORMAL:
			Position p = currCell.getNearestTrapRelativePosition();
			if(Math.abs(p.getX())>Math.abs(p.getY()))
				c = currCell.getCellFromHere(new Position(p.getX()/Math.abs(p.getX()),0));
			else
				c = currCell.getCellFromHere(new Position(0,p.getY()/Math.abs(p.getY())));
			currCell.remove(this);
			c.add(this);
			currCell=c;
			startCleaningIfNeeded();
			break;
		case CLEANING:
			cleaningCounter--;
			if(cleaningCounter==0){
				state = LittleRobotState.NORMAL;
				currCell.removeTrap();
			}
		default:
			break;
		}
	}

	@Override
	public Cell getCell() {
		return currCell;
	}

	@Override
	public void collideWithLittleRobot(LittleRobot other) {
		state = LittleRobotState.DAZED;
		dazedCounter = 2;
		Cell freeCell = currCell.getFreeNeighbouringCell();
		if(freeCell!=null){
			currCell.remove(this);
			freeCell.add(this);
			currCell=freeCell;
		}
		else die();
	}

}
