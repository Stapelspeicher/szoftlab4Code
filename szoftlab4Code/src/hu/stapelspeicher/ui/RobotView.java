package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;
import hu.stapelspeicher.modell.Robot;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Barni
 * egy robot megjeleniteseert felelos osztaly
 */
public class RobotView {
	/**
	 * a robotok szinei
	 */
	public static final Color robotColors[]={
		new Color(31, 173, 255),
		new Color(255, 29, 119),
		new Color(170, 64, 255),
		new Color(255, 46, 18),
		new Color(0, 193, 63),
		new Color(255, 152, 29)
	};
	/**
	 * a megjelenitendo robot
	 */
	private Robot robot;
	/**
	 * a robot indexe, megadja hogy milyen szinu lesz
	 */
	private int colorIndex;
	
	/**
	 * az osztaly konstruktora, beallitja a megjelenitendo robotot
	 * @param r a megjelenitendo robot
	 * @param i a robot szinenek indexe
	 */
	public RobotView(Robot r, int i){
		robot = r;
		colorIndex = i;
	}
	
	/**
	 * a robot kirajzolasa
	 * @param g a grafikus objektum amire rajzolni kell
	 */
	public void paint(Graphics g){
		g.setColor(robotColors[colorIndex]);
		int x = robot.getCell().getPositionForTest().getX();
		int y = robot.getCell().getPositionForTest().getY();
		if(robot.isAlive())
		g.fillRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE),
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE),
				GameMapView.CELL_SIZE,
				GameMapView.CELL_SIZE);
	}
}
