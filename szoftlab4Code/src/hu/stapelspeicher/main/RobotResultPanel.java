package hu.stapelspeicher.main;

import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RobotResultPanel extends JPanel {
	private Robot robot;
	private JPanel distancePanel;
	private JPanel xPanel;
	private JPanel yPanel;
	private JPanel oilyPanel;
	private JPanel stickyPanel;
	
	private JLabel distanceValueLabel;
	
	public RobotResultPanel(Robot r, int i){
		robot=r;
		setBackground(RobotView.robotColors[i]);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		distancePanel = new JPanel();
		distancePanel.setBackground(RobotView.robotColors[i]);
		JLabel distanceLabel = new JLabel("Distance", SwingConstants.LEFT);
		distanceValueLabel = new JLabel(new Integer(robot.getDistance()).toString(), SwingConstants.RIGHT);
		distancePanel.add(distanceLabel);
		distancePanel.add(distanceValueLabel);
		add(distancePanel);
	}
	
	public void paintComponent(Graphics g){
		distanceValueLabel.setText(new Integer(robot.getDistance()).toString());
		super.paintComponent(g);
	}
}
