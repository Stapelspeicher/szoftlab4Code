package hu.stapelspeicher.main;

import java.util.ArrayList;
import java.util.Random;

public class GameController{
	private Window mainWindow;
	private GameMap game;
	private GameStartPanel gameStartPanel;
	private ResultPanel resultPanel;
	private int players;
	private Robot robots[];
	private ArrayList<LittleRobot> littleRobots;
	private Random randomGenerator = new Random();
	private int currentRobot;
	private int robotsAlive;
	private GamePanel gamePanel;
	public static final int GAMEMAP_SIZE=16;
	private static final int OILY = 5;
	private static final int STICKY = 5;
	
	public void selectPlayers(){
		mainWindow = new Window();
		gameStartPanel = new GameStartPanel(this);
		mainWindow.setLeftPanel(gameStartPanel);
		resultPanel = new ResultPanel(null);
		mainWindow.setResultPanel(resultPanel);
	}
	
	public void startGame(int players){
		this.players = players;
		robotsAlive = players;
		currentRobot = 0;
		littleRobots = new ArrayList<LittleRobot>();
		game = new GameMap(GAMEMAP_SIZE, GAMEMAP_SIZE);
		for(int i=0; i<GAMEMAP_SIZE; i++){
			for(int j=0; j<GAMEMAP_SIZE; j++){
				game.addCell(new Position(i, j));
			}
		}
		robots = new Robot[players];
		for(int i=0; i<players; i++){
			robots[i]=new Robot(OILY, STICKY);
			int rnd_x = randomGenerator.nextInt(GAMEMAP_SIZE);
			int rnd_y = randomGenerator.nextInt(GAMEMAP_SIZE);
			Cell c = game.getCell(new Position(rnd_x, rnd_y));
			robots[i].setCell(c);
			c.add(robots[i]);
		}
		
		gamePanel = new GamePanel(); 
		gamePanel.setGame(game);
		gamePanel.setRobots(robots);
		mainWindow.setLeftPanel(gamePanel);
		resultPanel = new ResultPanel(robots);
		mainWindow.setResultPanel(resultPanel);
		ControllerPanel controllerPanel = new ControllerPanel(this);
		mainWindow.setControllerPanel(controllerPanel);
	}
	
	private void endGame(){
		System.out.println("LELK");
	}
	
	private void nextRound(){
		game.decrementRounds();
		for(LittleRobot lr : littleRobots){
			lr.step();
		}
		if((game.getRounds()%3)==0){
			LittleRobot lr = new LittleRobot();
			int rnd_x = randomGenerator.nextInt(GAMEMAP_SIZE);
			int rnd_y = randomGenerator.nextInt(GAMEMAP_SIZE);
			Cell c = game.getCell(new Position(rnd_x, rnd_y));
			lr.setCell(c);
			c.add(lr);
			littleRobots.add(lr);
			gamePanel.addLittleRobot(lr);
		}
	}
	
	public void placeOily(){
		robots[currentRobot].placeOily();
		mainWindow.repaint();
	}
	
	public void placeSticky(){
		robots[currentRobot].placeSticky();
		mainWindow.repaint();
	}
	
	public void robotStep(int x, int y){
		if(robotsAlive>0){
			robots[currentRobot].addVelocity(new Position(x, y));
			robots[currentRobot].step();
			if(!robots[currentRobot].isAlive()){
				robotsAlive--;
			}
			if(robotsAlive==0){
				endGame();
			}
			int counter = 1;
			currentRobot = (currentRobot+1)%players;
			while(!robots[currentRobot].isAlive() && counter<players){
				counter++;
				currentRobot = (currentRobot+1)%players;
			}
			if(currentRobot==0){
				nextRound();
			}
			mainWindow.repaint();
		}
		
	}
	
	public static void main(String args[]){
		GameController gc = new GameController();
		
		gc.selectPlayers();
		
	}
}
