package hu.stapelspeicher.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private GameMapView gameMap;
	private RobotView robots[];
	private ArrayList<LittleRobotView> littleRobots;
	
	public GamePanel(){
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(new Color(29, 29, 29));
		littleRobots = new ArrayList<LittleRobotView>();
	}
	
	public void setGame(GameMap g){
		gameMap = new GameMapView(g);
	}
	
	public void setRobots(Robot robots[]){
		this.robots = new RobotView[robots.length];
		for(int i=0; i<robots.length; i++){
			this.robots[i] = new RobotView(robots[i], i);
		}
	}
	
	public void addLittleRobot(LittleRobot lr){
		littleRobots.add(new LittleRobotView(lr));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gameMap.paintMap(g);
		for(RobotView r : robots){
			r.paint(g);
		}
		for(LittleRobotView lr : littleRobots){
			lr.paint(g);
		}
		gameMap.paintTraps(g);
	}
}
