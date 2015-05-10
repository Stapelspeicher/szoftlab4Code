package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Barni
 * a jatek inditasa elott a jatekoskivalasztast megjelenito panel
 * egyuttal a szines gombok mouselistenere is
 */
public class GameStartPanel extends JPanel implements MouseListener{
	/**
	 * a szines kivalasztogombok 
	 */
	private SelectorButton selectorButtons[];
	/**
	 * a jatekosok szamat tartalmazo label 
	 */
	private JLabel playerLabel;
	/**
	 * a jatekosok szama 
	 */
	private int players;
	/**
	 * a jatekot iranyito kontroller 
	 */
	private GameController gc;
	/**
	 * az inaktiv szines gombok szinei
	 */
	private static final Color selectorButtonColors[]={
		new Color(27, 88, 184),
		new Color(184, 27, 108),
		new Color(105, 26, 184),
		new Color(184, 27, 27),
		new Color(21, 152, 42),
		new Color(228, 107, 25)
	};
	
	/**
	 * az osztaly konstruktora, letrehozza a gombokat es a szoveget
	 * tartalmazo labelt
	 * @param gca jatekot vezerlo kontroller
	 */
	public GameStartPanel(GameController gc){
		this.gc = gc;
		//stilus es elrendezes beallitasa
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(new Color(29, 29, 29));
		setVisible(true);
		setLayout(new GridBagLayout());
		//a belso panel beallitasa, ami a gombokat es a szoveget tartalmazza
		JPanel innerPanel = new JPanel();
		innerPanel.setBackground(new Color(29, 29, 29));
		innerPanel.setPreferredSize(new Dimension(300, 150));
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=300;
		c.gridheight=200;
		add(innerPanel, c);
		innerPanel.setLayout(new BorderLayout());
		
		//a belso panelen belul egy uj panel beallitasa
		//ami a szines gombokat tartalmazza
		JPanel innerUpperPanel = new JPanel();
		innerUpperPanel.setBackground(new Color(29, 29, 29));
		innerUpperPanel.setPreferredSize(new Dimension(260, 75));
		innerUpperPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		GridLayout grid = new GridLayout();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setRows(1);
		grid.setColumns(6);
		innerUpperPanel.setLayout(grid);
		innerPanel.add(innerUpperPanel, BorderLayout.NORTH);
		
		//a szines kivalaszto gombok letrehozasa es beallitasa
		selectorButtons = new SelectorButton[6];
		for(int i=0; i<6; i++){
			selectorButtons[i] = new SelectorButton(i+1);
			selectorButtons[i].setBackground(selectorButtonColors[i]);
			selectorButtons[i].setPreferredSize(new Dimension(35, 35));
			selectorButtons[i].setBorder(BorderFactory.createEmptyBorder());
			selectorButtons[i].addMouseListener(this);
			selectorButtons[i].addMouseListener(this);
			innerUpperPanel.add(selectorButtons[i]);
		}
		
		//a jatekosok szamat kiiro label beallitasa
		playerLabel = new JLabel("", SwingConstants.CENTER);
		playerLabel.setBackground(new Color(29, 29, 29));
		playerLabel.setForeground(Color.WHITE);
		playerLabel.setFont(new Font("Arial", 0, 30));
		innerPanel.add(playerLabel, BorderLayout.CENTER);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * a gombokra valo klikkelest figyeli, es atadja a kontrollernek
	 * a jatekosok szamat
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		gc.startGame(players);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 * ha belepett az egyik gombba az egermutato, akkor az
	 * osszes addigi gomb szinet megvaltoztatja az elenkebbre
	 * valamitn kiirja a jatekosok szamat
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		int i=0;
		while(!selectorButtons[i].equals(event.getSource())){
			selectorButtons[i].setBackground(RobotView.robotColors[i]);
			i++;
		}
		selectorButtons[i].setBackground(RobotView.robotColors[i]);
		players=i+1;
		playerLabel.setText(players+" player"+(i==0 ? "" : "s"));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 * ha az egermutato kilepett egy gombbol akkor gombok szinenek 
	 * visszaallitasa fakora es a szoveg eltuntetese
	 */
	@Override
	public void mouseExited(MouseEvent event) {
		int i=0;
		while(!selectorButtons[i].equals(event.getSource())){
			selectorButtons[i].setBackground(selectorButtonColors[i]);
			i++;
		}
		selectorButtons[i].setBackground(selectorButtonColors[i]);
		playerLabel.setText("");
	}

	//a kovetkezo metodusok nem csinlanak semmit, csak a 
	//mouselistener interface implementalasa miatt van szukseg rajuk
	
	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}


}
