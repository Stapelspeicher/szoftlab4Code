package hu.stapelspeicher.ui;

import hu.stapelspeicher.modell.Robot;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author Barni
 * az eredmenyeket tarolo panel
 */
public class ResultPanel extends JPanel {
	/**
	 * az egyes robotok eredmenyei tarolo panelek
	 */
	private RobotResultPanel panels[];
	/**
	 * az aktualis robot indexe
	 */
	private int currentRobot;
	
	/**
	 * beallit egy robotot aktiv robotnak, azaz megjeleniti
	 * a reszletesebb adatait, az elozonek pedig elrejti
	 * @param x a beallitando robot indexe
	 */
	public void setActive(int x){
		panels[currentRobot].displayInactive();
		currentRobot=x;
		panels[currentRobot].displayActive();
	}
	
	/**
	 * az osztaly konstruktora, beallitja a panel stilusat
	 * es letrehozza az egyes robotok eredmenyeit megjelenito paneleket
	 * @param robots a robotok amiknek az eredmenyeit meg kell jeleniteni
	 */
	public ResultPanel(Robot robots[]){
		if(robots!=null){
			setBackground(new Color(29,29,29));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			panels = new RobotResultPanel[robots.length];
			for(int i=0; i<robots.length; i++){
				RobotResultPanel panel = new RobotResultPanel(robots[i],i);
				panel.displayInactive();
				panels[i] = panel;
				add(panel);
			}
		}
		
	}
}
