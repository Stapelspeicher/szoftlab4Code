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

/**
 * @author Barni
 * a jatektablat es az elemeit tartalmazo/megjelenito panel
 * egyben az uj jatek gomb listenere
 */
public class GamePanel extends JPanel implements MouseListener{
	/**
	 * hatterszin
	 */
	public static final Color BACKGROUND_COLOR = new Color(29, 29, 29);
	/**
	 * a jatekot vezerlo kontroller
	 */
	private GameController gc;
	/**
	 * a gamemap obejktum, amit ki kell rajzolni
	 */
	private GameMapView gameMap;
	/**
	 * a jatekban reszt vevo robotok grafikus objektumai
	 */
	private RobotView robots[];	
	/**
	 * a jatekban reszt vevo kisorobotok grafikus objektumai
	 */
	private ArrayList<LittleRobotView> littleRobots;
	/**
	 * megy-e meg a jatek, vagy mar veget ert
	 */
	private boolean ended = false;
	
	/**
	 * az osztaly konstruktora, beallitja a panel stilusat
	 * @param g a jatekot vezerlo kontroller
	 */
	public GamePanel(GameController g){
		gc=g;
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(BACKGROUND_COLOR);
		setLayout(new GridBagLayout());
		littleRobots = new ArrayList<LittleRobotView>();
	}
	
	/**
	 * a megjelenitendo jatekot allitja be
	 * @param g a megejelenitendo jatek
	 */
	public void setGame(GameMap g){
		gameMap = new GameMapView(g);
	}
	
	/**
	 * a megjelenitendo robotok grafikus objektumait hozza
	 * letre es allitja be
	 * @param robots a megjelenitendo robotokat tartalmazo tomb
	 */
	public void setRobots(Robot robots[]){
		this.robots = new RobotView[robots.length];
		for(int i=0; i<robots.length; i++){
			this.robots[i] = new RobotView(robots[i], i);
		}
	}
	
	/**
	 * egy kisrobotot ad a felulethez letrehozva neki
	 * egy sajat grafikus objektumot
	 * @param lr a hozzaadando kisrobot
	 */
	public void addLittleRobot(LittleRobot lr){
		littleRobots.add(new LittleRobotView(lr));
	}
	
	/**
	 * a jatek vegen kirajzolja az uj jatek gombot
	 */
	public void endGame(){
		ended = true;
		//az uj jatek gomb letrehozasa es stilusanak beallitasa
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
	
	/**
	 * ha veget ert a jatek egy atlatszo fekete reteget
	 * rajzol a palya fole
	 * @param g a grafikus objektum amire rajzolni kell
	 */
	private void paintEnd(Graphics g){
		g.setColor(new Color(30, 30, 30, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * kirajzolja a palyat, a robotokat, kisorobotokat, csapdakat
	 * es a jatek vegen az atfedo reteget
	 */
	public void paintComponent(Graphics g){
		//az elozo rajzol torlese
		super.paintComponent(g);
		//palya kirajzolasa
		gameMap.paintMap(g);
		//robotok kirajzolasa
		for(RobotView r : robots){
			r.paint(g);
		}
		//kisrobotok kirajzolasa
		for(LittleRobotView lr : littleRobots){
			lr.paint(g);
		}
		//csapdak kirajzolasa
		gameMap.paintTraps(g);
		//ha veget ert a jatek az atlatszo fekete reteg kirajzolasa
		if(ended){
			paintEnd(g);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * az uj jatek gomb megnyomasa eseten ertesiti a kontrollert
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		gc.selectPlayers();
	}

	//ezekre a metodusokra csak a mouselistener interface
	//miatt volt szukseg, funkciot nem latnek el
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
