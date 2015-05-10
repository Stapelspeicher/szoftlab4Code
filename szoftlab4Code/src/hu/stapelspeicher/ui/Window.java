package hu.stapelspeicher.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	public static final int WINDOW_SIZE_X = 800; 
	public static final int WINDOW_SIZE_Y = 600;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel resultPanel;
	private JPanel controllerPanel;
	
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
	
	public void setLeftPanel(JPanel panel){
		if(leftPanel!=null)
			remove(leftPanel);
		leftPanel = panel;
		add(panel, BorderLayout.LINE_START);
		paintComponents(getGraphics());
	}
	
	public void setResultPanel(JPanel panel){
		if(resultPanel!=null)
			rightPanel.remove(resultPanel);
		resultPanel = panel;
		rightPanel.add(panel, BorderLayout.CENTER);
		paintComponents(getGraphics());
	}
	
	public void setControllerPanel(JPanel panel){
		if(controllerPanel!=null)
			rightPanel.remove(controllerPanel);
		controllerPanel = panel;
		rightPanel.add(controllerPanel, BorderLayout.SOUTH);
		paintComponents(getGraphics());
	}
}
