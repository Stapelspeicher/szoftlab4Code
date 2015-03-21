package hu.stapelspeicher.main;

public interface ActiveObject {
	void stepOn(ActiveObject ao);
	void oilyEffect();
	void stickyEffect();
	void collideWithRobot(Robot other);
	void die();
	void setCell();
	void step();
	void getCell();
}
