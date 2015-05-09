package hu.stapelspeicher.main;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	private RobotResultPanel panels[];
	private int currentRobot;
	
	public void setActive(int x){
		panels[currentRobot].displayInactive();
		currentRobot=x;
		panels[currentRobot].displayActive();
	}
	
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
