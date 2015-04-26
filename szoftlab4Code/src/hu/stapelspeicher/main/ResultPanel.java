package hu.stapelspeicher.main;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	public ResultPanel(Robot robots[]){
		if(robots!=null){
			setBackground(new Color(29,29,29));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			for(int i=0; i<robots.length; i++){
				RobotResultPanel panel = new RobotResultPanel(robots[i],i);
				add(panel);
			}
		}
		
	}
}
