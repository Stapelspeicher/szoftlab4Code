package hu.stapelspeicher.main;

import java.awt.Color;
import java.awt.Graphics;

public class RobotView {
	public static final Color robotColors[]={
		new Color(31, 173, 255),
		new Color(255, 29, 119),
		new Color(170, 64, 255),
		new Color(255, 46, 18),
		new Color(0, 193, 63),
		new Color(255, 152, 29)
	};
	private Robot robot;
	private int colorIndex;
	
	public RobotView(Robot r, int i){
		robot = r;
		colorIndex = i;
	}
	
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
