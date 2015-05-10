package hu.stapelspeicher.controller;

import hu.stapelspeicher.modell.Cell;
import hu.stapelspeicher.modell.GameMap;
import hu.stapelspeicher.modell.LittleRobot;
import hu.stapelspeicher.modell.Position;
import hu.stapelspeicher.modell.Robot;
import hu.stapelspeicher.ui.ControllerPanel;
import hu.stapelspeicher.ui.GamePanel;
import hu.stapelspeicher.ui.GameStartPanel;
import hu.stapelspeicher.ui.ResultPanel;
import hu.stapelspeicher.ui.Window;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author Barni
 * a jatek kontroller osztalya, osszekoti a modellt es a grafikus
 * feluletet es iranyitja a jatek menetet
 */
public class GameController{
	/**
	 * a JFrame, amiben a jatek zajlik 
	 */
	private Window mainWindow;
	/**
	 * a jatek logikaja
	 */
	private GameMap game;
	/**
	 * jatekosvalaszto panel
	 */
	private GameStartPanel gameStartPanel;
	/**
	 * eredmenyeket tartalmazo panel 
	 */
	private ResultPanel resultPanel;
	/**
	 * jatekosok szama 
	 */
	private int players;
	/**
	 * a jatekosok robotai, max 6 
	 */
	private Robot robots[];
	/**
	 * a plazan levo kisrobotok 
	 */
	private ArrayList<LittleRobot> littleRobots;
	/**
	 * randomgenerator a robotok es kisrobotok elhelyezesehez
	 */
	private Random randomGenerator = new Random();
	/**
	 * az eppen aktiv robot indexe
	 */
	private int currentRobot;
	/**
	 * a jatekban levo robotok szama 
	 */
	private int robotsAlive;
	/**
	 * a jatek menetet megjelenito panel 
	 */
	private GamePanel gamePanel;
	/**
	 * a palya merete (negyzat alaku)
	 */
	public static final int GAMEMAP_SIZE=16;
	/**
	 * a robotok szamara a kezdo olajkeszlet nagysaga 
	 */
	private static final int OILY = 5;
	/**
	 * a robotok szamara a kezdo ragacskeszlet nagysaga 
	 */
	private static final int STICKY = 5;
	
	/**
	 * a jatekosok szamanak kivalasztasat tolti be
	 */
	public void selectPlayers(){
		gameStartPanel = new GameStartPanel(this);
		mainWindow.setLeftPanel(gameStartPanel);
	}
	
	/**
	 * a jatekosok szamanak kivalasztasa utan elinditja a jatekot
	 * @param players a jatekosko szama
	 */
	public void startGame(int players){
		
		//inicializalas
		this.players = players;
		robotsAlive = players;
		currentRobot = 0;
		littleRobots = new ArrayList<LittleRobot>();
		
		//a palya es a cellak letrehozasa
		game = new GameMap(GAMEMAP_SIZE, GAMEMAP_SIZE);
		for(int i=0; i<GAMEMAP_SIZE; i++){
			for(int j=0; j<GAMEMAP_SIZE; j++){
				game.addCell(new Position(i, j));
			}
		}
		
		//robotok letrehozasa es elhelyezese
		robots = new Robot[players];
		for(int i=0; i<players; i++){
			robots[i]=new Robot(OILY, STICKY);
			int rnd_x = randomGenerator.nextInt(GAMEMAP_SIZE);
			int rnd_y = randomGenerator.nextInt(GAMEMAP_SIZE);
			Cell c = game.getCell(new Position(rnd_x, rnd_y));
			robots[i].setCell(c);
			c.add(robots[i]);
		}
		
		//felhasznaloi felulet beallitasa
		gamePanel = new GamePanel(this); 
		gamePanel.setGame(game);
		gamePanel.setRobots(robots);
		mainWindow.setLeftPanel(gamePanel);
		resultPanel = new ResultPanel(robots);
		mainWindow.setResultPanel(resultPanel);
		ControllerPanel controllerPanel = new ControllerPanel(this);
		mainWindow.setControllerPanel(controllerPanel);
		resultPanel.setActive(currentRobot);
	}
	
	/**
	 * a jatek vegen hivodik meg, betolti a kepernyot amin uj jatekot lehet kezdeni 
	 */
	private void endGame(){
		gamePanel.endGame();
		gamePanel.repaint();
	}
	
	/**
	 * a kor vegen elvegzendo tevekenysegeket vegzi el
	 * (a kor vege az, amikor minden jatekban levo robot lepett egyszer)
	 * kisrobotokat leptet, es minden harmadik kor vegen elhelyez a palyara
	 * egy uj kisrobotot 
	 */
	private void nextRound(){
		//a modellben a kor leptetese
		boolean playing = game.decrementRounds();
		//kisrobotok leptetese
		for(LittleRobot lr : littleRobots){
			lr.step();
		}
		//uj kisrobot elhelyezese
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
		//annak ellenorzese, hogy elfogytak-e a rendelkezesre allo korok
		if(!playing)
			endGame();
	}
	
	/**
	 * olajfoltot helyeztet el az aktiv robottal 
	 */
	public void placeOily(){
		robots[currentRobot].placeOily();
		mainWindow.repaint();
	}
	
	/**
	 * ragacsfoltot helyeztet el az aktiv robottal 
	 */
	public void placeSticky(){
		robots[currentRobot].placeSticky();
		mainWindow.repaint();
	}
	
	/**
	 * lepteti az aktov robotot, es vizsgalja, hogy a robotok szama
	 * alapjan veget erhetett-e a jatek
	 * a robot sebessegvaltozasat parameterkent kapja meg
	 * @param x a sebessegvaltozas x iranya (0 vagy 1)
	 * @param y a sebessegvaltozas y iranya (0 vagy 1)
	 */
	public void robotStep(int x, int y){
		//csak akkor lehessen leptetni ha meg nem fogytak el a robotok
		//egy jatekos eseten amig a robot meg nem hal
		//sok jatekos eseten amig egy robot nem marad csak
		if(robotsAlive>1 || (players==1 && robotsAlive>0)){
			//aktiv robot leptetese
			robots[currentRobot].addVelocity(new Position(x, y));
			robots[currentRobot].step();
			//eletben maradas ellenrozese
			if(!robots[currentRobot].isAlive()){
				robotsAlive--;
			}
			//annak ellenorzese hogy mehet-e tovabb a jatek
			if(robotsAlive==0 || (players>1 && robotsAlive==1)){
				endGame();
			}
			//aktiv robot leptetese a kovetkezore
			currentRobot = (currentRobot+1)%players;
			//ha a nullas robothoz ertunk el megint, akkor vege a kornek
			if(currentRobot==0){
				nextRound();
			}
			//addig lepegetunk a robotok kozott, amig elo robothoz nem erunk
			while(!robots[currentRobot].isAlive()){
				currentRobot = (currentRobot+1)%players;
				if(currentRobot==0){
					nextRound();
				}
			}
			//modositasok alkalmatasa a grafikus feluleten
			resultPanel.setActive(currentRobot);
			mainWindow.repaint();
		}
		
	}
	
	/**
	 * a jatekprogram indulasakor a fo inicializalast vegzi el
	 */
	public void startGame(){
		mainWindow = new Window();
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(GamePanel.BACKGROUND_COLOR);
		mainWindow.setResultPanel(blankPanel);
		selectPlayers();
	}
	
	/**
	 * main fuggveny, itt indul a program, peldanyositja a jatekot
	 * es elinditja
	 * @param args
	 */
	public static void main(String args[]){
		GameController gc = new GameController();
		gc.startGame();
		
	}
}
