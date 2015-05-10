package hu.stapelspeicher.ui;

import hu.stapelspeicher.modell.Robot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Barni
 * egy robot eredmenyeit megjelento panel
 */
public class RobotResultPanel extends JPanel {
	/**
	 * a robot, aminek az eredmenyeit meg kell jeleniteni
	 */
	private Robot robot;
	/**
	 * a robot es a panel szine
	 */
	private Color bgColor;
	//az egyes adatokat megjelenito panelek
	private JPanel distancePanel;
	private JPanel xPanel;
	private JPanel yPanel;
	private JPanel oilyPanel;
	private JPanel stickyPanel;
	
	//az iranyokat megjelenito cimkek
	private JLabel xLabel;
	private JLabel yLabel;
	
	//az ertekeket megjelenito cimkek
	private JLabel distanceValueLabel;
	private JLabel xValueLabel;
	private JLabel yValueLabel;
	private JLabel oilyValueLabel;
	private JLabel stickyValueLabel;
	
	/**
	 * letrehoz egy olyan panelt, ami a reszletes adatok
	 * kozul tud tarolni egyet
	 * @param type a panel bal oldali cimkeje (tipus)
	 * @param value a panel jobb oldali cimkeje (ertek)
	 * @return a kert panel, ami tartalmazza a labeleket
	 */
	private JPanel setUpPanel(JLabel type, JLabel value){
		//a panel letrehozasa es stilusanak beallitasa
		JPanel retPanel = new JPanel();
		retPanel.setBackground(bgColor);
		retPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//a bal oldali label hozzaadasa a megfelelo modon
		constraints.weightx=0.8;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(3,5,3,5);
		retPanel.add(type, constraints);
		
		//a jobb oldali laben hozzaadasa a megfelelo modon
		constraints.weightx=0.2;
		constraints.gridx=1;
		constraints.gridy=0;	
		retPanel.add(value, constraints);
		add(retPanel);
		return retPanel;
	}
	
	/**
	 * beallitja egy cimke betuinek formatumat
	 * @param label a beallitando cimke
	 */
	private void formatLabel(JLabel label){
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", 0, 14));
	}
	
	/**
	 * inaktiv robotkent jeleniti meg az adott robot eredmenyeit
	 * azaz csak a megtett tavolsag jelenik meg
	 */
	public void displayInactive(){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		xPanel.setVisible(false);
		yPanel.setVisible(false);
		oilyPanel.setVisible(false);
		stickyPanel.setVisible(false);
	}
	
	/**
	 * aktiv robotkent jeleniti meg az adott robotot, azaz a 
	 * tavolsagon kivul a tobbi adatot is kiirja
	 */
	public void displayActive(){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		
		xLabel.setText(xLabelChar(robot.getVelocityForTest().getX()));
		xValueLabel.setText(new Integer(Math.abs(robot.getVelocityForTest().getX())).toString());
		xPanel.setVisible(true);
		
		yLabel.setText(yLabelChar(robot.getVelocityForTest().getY()));
		yValueLabel.setText(new Integer(Math.abs(robot.getVelocityForTest().getY())).toString());
		yPanel.setVisible(true);
		
		oilyValueLabel.setText(new Integer(robot.getOilyForTest()).toString());
		oilyPanel.setVisible(true);
		
		stickyValueLabel.setText(new Integer(robot.getStickyForTest()).toString());
		stickyPanel.setVisible(true);
	}
	
	/**
	 * visszaadja a megfelelo iranyba mutato nyilat az x
	 * sebessegtol fuggoen
	 * @param x az x iranyu sebesseg
	 * @return a megfelelo nyil stringkent
	 */
	private String xLabelChar(int x){
		if(x>0) return new String("\u2192");
		if(x<0) return new String("\u2190");
		return new String("\u2194");
	}
	
	/**
	 * visszaadja a megfelelo iranyba mutato nyilat az y
	 * sebessegtol fuggoen
	 * @param y az y iranyu sebesseg
	 * @return a megfelelo nyil stringkent
	 */
	private String yLabelChar(int y){
		if(y>0) return new String("\u2191");
		if(y<0) return new String("\u2193");
		return new String("\u2195");
	}
	
	/**
	 * az osztaly konstruktora, letrehozza es beallitja az egyes
	 * adatokat megjelenikto paneleket es cimkeket
	 * @param r a robot aminek az adatait meg kell jeleniteni
	 * @param i a robot indexe
	 * 
	 */
	public RobotResultPanel(Robot r, int i){
		robot=r;
		bgColor = RobotView.robotColors[i];
		//a panel beallitasa
		setBackground(bgColor);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//a tavolsag megjelenitesenek beallitasa
		JLabel distanceLabel = new JLabel("Distance", SwingConstants.LEFT);
		distanceLabel.setForeground(Color.WHITE);
		distanceLabel.setFont(new Font("Arial", 0, 20));		
		distanceValueLabel = new JLabel(new Integer(robot.getDistance()).toString(), SwingConstants.RIGHT);
		distanceValueLabel.setFont(new Font("Arial", 0, 20));
		distanceValueLabel.setForeground(Color.WHITE);		
		distancePanel = setUpPanel(distanceLabel, distanceValueLabel);
		
		//az x sebesseg megjelenitesenek beallitasa
		xLabel = new JLabel(xLabelChar(robot.getVelocityForTest().getX()), SwingConstants.LEFT);
		xValueLabel = new JLabel(new Integer(Math.abs(robot.getVelocityForTest().getX())).toString(), SwingConstants.RIGHT);
		formatLabel(xLabel);
		formatLabel(xValueLabel);
		xPanel = setUpPanel(xLabel, xValueLabel);
		
		//az y sebesseg megjelenitesenek beallitasa
		yLabel = new JLabel(yLabelChar(robot.getVelocityForTest().getY()), SwingConstants.LEFT);
		yValueLabel = new JLabel(new Integer(Math.abs(robot.getVelocityForTest().getY())).toString(), SwingConstants.RIGHT);
		formatLabel(yLabel);
		formatLabel(yValueLabel);
		yPanel = setUpPanel(yLabel, yValueLabel);
		
		//az olajfoltok szamanak megjelenitesenek beallitasa
		oilyValueLabel = new JLabel(new Integer(robot.getOilyForTest()).toString(), SwingConstants.RIGHT);
		JLabel oilyLabel = new JLabel("Oily patches", SwingConstants.LEFT);
		formatLabel(oilyLabel);
		formatLabel(oilyValueLabel);
		oilyPanel = setUpPanel(oilyLabel, oilyValueLabel);
		
		//a ragacsfoltok szamanak megjelenitesenek beallitasa
		stickyValueLabel = new JLabel(new Integer(robot.getStickyForTest()).toString(), SwingConstants.RIGHT);
		JLabel stickyLabel = new JLabel("Sticky patches", SwingConstants.LEFT);
		formatLabel(stickyLabel);
		formatLabel(stickyValueLabel);
		stickyPanel = setUpPanel(stickyLabel, stickyValueLabel);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * ujrarajzolas eseten a tavolsag frissitese
	 */
	public void paintComponent(Graphics g){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		super.paintComponent(g);
	}
}
