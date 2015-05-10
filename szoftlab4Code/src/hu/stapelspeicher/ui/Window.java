package hu.stapelspeicher.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Barni
 * a jatek ablakat reprezentalo osztaly, felelossege az
 * egyes komponensek tarolasa es az alap elrendezes
 * biztositasa
 */
public class Window extends JFrame{
	/**
	 * az ablak vizszintes merete
	 */
	public static final int WINDOW_SIZE_X = 800; 
	/**
	 * az ablak fuggoleges merete
	 */
	public static final int WINDOW_SIZE_Y = 600;
	/**
	 * a bal oldali panel, amiben a jatekosok kivalasztasa
	 * es a jatek zajlik
	 */
	private JPanel leftPanel;
	/**
	 * a jobb oldali panel, ami az eredmenyeket es a vezerlest tartalmazza
	 */
	private JPanel rightPanel;
	/**
	 * az eredmenyeket tartalmazo panel
	 */
	private JPanel resultPanel;
	/**
	 * a vezerlest tartalmazo panel
	 */
	private JPanel controllerPanel;
	
	/**
	 * az osztaly konstruktora, beallitja az ablak elrendezeset
	 */
	public Window(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("stapelspeicher sucks");
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(false);
		setLayout(new BorderLayout());
		setVisible(true);
		setSize(2*WINDOW_SIZE_X-getContentPane().getSize().width,
				2*WINDOW_SIZE_Y-getContentPane().getSize().height);
		leftPanel = null;
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		add(rightPanel, BorderLayout.CENTER);
	}
	
	/**
	 * beallitja a bal oldali panel a parameterkent kapottra
	 * @param panel a beallitando panel
	 */
	public void setLeftPanel(JPanel panel){
		if(leftPanel!=null)
			remove(leftPanel);
		leftPanel = panel;
		add(panel, BorderLayout.LINE_START);
		paintComponents(getGraphics());
	}
	
	/**
	 * beallitja az eredmenypanelt a parameterkent kapottra
	 * @param panel a beallitando panel
	 */
	public void setResultPanel(JPanel panel){
		if(resultPanel!=null)
			rightPanel.remove(resultPanel);
		resultPanel = panel;
		rightPanel.add(panel, BorderLayout.CENTER);
		paintComponents(getGraphics());
	}
	
	/**
	 * beallitja a kontrollerpanelt a parameterkent kapottra
	 * @param panel a beallitando panel
	 */
	public void setControllerPanel(JPanel panel){
		if(controllerPanel!=null)
			rightPanel.remove(controllerPanel);
		controllerPanel = panel;
		rightPanel.add(controllerPanel, BorderLayout.SOUTH);
		paintComponents(getGraphics());
	}
}
