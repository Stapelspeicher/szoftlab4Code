package hu.stapelspeicher.main;

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

public class RobotResultPanel extends JPanel {
	private Robot robot;
	private Color bgColor;
	private JPanel distancePanel;
	private JPanel xPanel;
	private JPanel yPanel;
	private JPanel oilyPanel;
	private JPanel stickyPanel;
	
	private JLabel xLabel;
	private JLabel yLabel;
	
	private JLabel distanceValueLabel;
	private JLabel xValueLabel;
	private JLabel yValueLabel;
	private JLabel oilyValueLabel;
	private JLabel stickyValueLabel;
	
	private JPanel setUpPanel(JLabel type, JLabel value){
		JPanel retPanel = new JPanel();
		retPanel.setBackground(bgColor);
		retPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.weightx=0.8;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(3,5,3,5);
		retPanel.add(type, constraints);
		
		constraints.weightx=0.2;
		constraints.gridx=1;
		constraints.gridy=0;	
		retPanel.add(value, constraints);
		add(retPanel);
		return retPanel;
	}
	
	private void formatLabel(JLabel label){
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", 0, 14));
	}
	
	public void displayInactive(){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		xPanel.setVisible(false);
		yPanel.setVisible(false);
		oilyPanel.setVisible(false);
		stickyPanel.setVisible(false);
	}
	
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
	
	private String xLabelChar(int x){
		if(x>0) return new String("\u2192");
		if(x<0) return new String("\u2190");
		return new String("\u2194");
	}
	
	private String yLabelChar(int y){
		if(y>0) return new String("\u2191");
		if(y<0) return new String("\u2193");
		return new String("\u2195");
	}
	
	public RobotResultPanel(Robot r, int i){
		robot=r;
		bgColor = RobotView.robotColors[i];
		setBackground(bgColor);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel distanceLabel = new JLabel("Distance", SwingConstants.LEFT);
		distanceLabel.setForeground(Color.WHITE);
		distanceLabel.setFont(new Font("Arial", 0, 20));		
		distanceValueLabel = new JLabel(new Integer(robot.getDistance()).toString(), SwingConstants.RIGHT);
		distanceValueLabel.setFont(new Font("Arial", 0, 20));
		distanceValueLabel.setForeground(Color.WHITE);		
		distancePanel = setUpPanel(distanceLabel, distanceValueLabel);
		
		xLabel = new JLabel(xLabelChar(robot.getVelocityForTest().getX()), SwingConstants.LEFT);
		xValueLabel = new JLabel(new Integer(Math.abs(robot.getVelocityForTest().getX())).toString(), SwingConstants.RIGHT);
		formatLabel(xLabel);
		formatLabel(xValueLabel);
		xPanel = setUpPanel(xLabel, xValueLabel);
		
		yLabel = new JLabel(yLabelChar(robot.getVelocityForTest().getY()), SwingConstants.LEFT);
		yValueLabel = new JLabel(new Integer(Math.abs(robot.getVelocityForTest().getY())).toString(), SwingConstants.RIGHT);
		formatLabel(yLabel);
		formatLabel(yValueLabel);
		yPanel = setUpPanel(yLabel, yValueLabel);
		
		oilyValueLabel = new JLabel(new Integer(robot.getOilyForTest()).toString(), SwingConstants.RIGHT);
		JLabel oilyLabel = new JLabel("Oily patches", SwingConstants.LEFT);
		formatLabel(oilyLabel);
		formatLabel(oilyValueLabel);
		oilyPanel = setUpPanel(oilyLabel, oilyValueLabel);
		
		stickyValueLabel = new JLabel(new Integer(robot.getStickyForTest()).toString(), SwingConstants.RIGHT);
		JLabel stickyLabel = new JLabel("Sticky patches", SwingConstants.LEFT);
		formatLabel(stickyLabel);
		formatLabel(stickyValueLabel);
		stickyPanel = setUpPanel(stickyLabel, stickyValueLabel);
	}
	
	public void paintComponent(Graphics g){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		super.paintComponent(g);
	}
}
