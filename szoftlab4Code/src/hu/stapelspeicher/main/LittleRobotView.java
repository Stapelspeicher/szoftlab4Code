package hu.stapelspeicher.main;

import java.awt.Color;
import java.awt.Graphics;

public class LittleRobotView {
	private LittleRobot littleRobot;
	
	public LittleRobotView(LittleRobot lr){
		littleRobot = lr;
	}
	
	public void paint(Graphics g){

		g.setColor(Color.YELLOW);
		int x = littleRobot.getCell().getPositionForTest().getX();
		int y = littleRobot.getCell().getPositionForTest().getY();
		if(littleRobot.isAlive())
		g.fillRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+5,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+5,
				GameMapView.CELL_SIZE-10,
				GameMapView.CELL_SIZE-10);
	}
}
