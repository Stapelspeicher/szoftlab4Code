package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;
import hu.stapelspeicher.modell.GameMap;
import hu.stapelspeicher.modell.LittleRobot;
import hu.stapelspeicher.modell.Robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener{
	public static final Color BACKGROUND_COLOR = new Color(29, 29, 29);
	private GameController gc;
	private GameMapView gameMap;
	private RobotView robots[];
	private ArrayList<LittleRobotView> littleRobots;
	private boolean ended = false;
	
	public GamePanel(GameController g){
		gc=g;
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(BACKGROUND_COLOR);
		setLayout(new GridBagLayout());
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
	
	public void endGame(){
		ended = true;
		JButton newGameButton = new JButton("New game");
		newGameButton.setFont(new Font("Arial", 0, 26));
		newGameButton.addMouseListener(this);
		newGameButton.setBackground(new Color(30, 30, 30, 110));
		newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		newGameButton.setFocusable(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setForeground(Color.white);
		newGameButton.setPreferredSize(new Dimension(160, 50));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.insets = new Insets(225, 220, 325, 220);
		this.add(newGameButton, constraints);
	}
	
	private void paintEnd(Graphics g){
		g.setColor(new Color(30, 30, 30, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
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
		if(ended){
			paintEnd(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gc.selectPlayers();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
