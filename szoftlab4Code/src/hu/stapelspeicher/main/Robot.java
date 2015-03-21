package hu.stapelspeicher.main;

public class Robot implements ActiveObject{
	
	private Position velocity;
	private Cell currCell;
	private boolean oily;
	private int stickyNum;
	private int oilNum;
	private double distance;
	
	
	Robot(int stickyNum, int oilyNum) {}
	public void placeSticky() {
	}
	public void placeOily() {
	}
	public void addVelocity() {
	}
	
	@Override
	public void stepOn(ActiveObject ao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oilyEffect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stickyEffect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWithRobot(Robot other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCell() {
		// TODO Auto-generated method stub
		
	}
	
}
