package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;
import hu.stapelspeicher.modell.LittleRobot;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Barni
 * egy kisrobot kirajzolasaert felelos osztaly
 */
public class LittleRobotView {
	/**
	 * a kirajzolando kisrobot
	 */
	private LittleRobot littleRobot;
	
	/**
	 * az osztaly konstuktora, beallitja a kirajzolando kisrobotot
	 * @param lr a kirajzolando kisrobot
	 */
	public LittleRobotView(LittleRobot lr){
		littleRobot = lr;
	}
	
	/**
	 * a kisrobotot rajzolja ki
	 * @param g a grafikus objektum amire rajzolni kell
	 */
	public void paint(Graphics g){
		g.setColor(Color.YELLOW);
		int x = littleRobot.getCell().getPosition().getX();
		int y = littleRobot.getCell().getPosition().getY();
		if(littleRobot.isAlive())
		g.fillRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+5,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+5,
				GameMapView.CELL_SIZE-10,
				GameMapView.CELL_SIZE-10);
	}
}
