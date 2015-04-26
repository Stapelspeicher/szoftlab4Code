package hu.stapelspeicher.main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControllerPanel extends JPanel implements MouseListener{	
	private GameController gc;
	private JLabel upArrow;
	private JLabel downArrow;
	private JLabel leftArrow;
	private JLabel rightArrow;
	private JLabel center;
	private JButton oilyButton;
	private JButton stickyButton;
	
	public ControllerPanel(GameController gc){
		this.gc = gc;
		setBackground(new Color(255,29,29));
		setPreferredSize(new Dimension(200, 300));
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(grid);
	
		
		upArrow = new JLabel("\u2191");
		upArrow.setFont(new Font("Arial", 0, 30));
		upArrow.setBackground(new Color(29, 255, 29));
		upArrow.setForeground(Color.WHITE);
		upArrow.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(upArrow, constraints);
		
		downArrow = new JLabel("\u2193");
		downArrow.setFont(new Font("Arial", 0, 30));
		downArrow.setBackground(new Color(29, 255, 29));
		downArrow.setForeground(Color.WHITE);
		downArrow.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(downArrow, constraints);
		
		leftArrow = new JLabel("\u2190");
		leftArrow.setFont(new Font("Arial", 0, 30));
		leftArrow.setBackground(new Color(29, 255, 29));
		leftArrow.setForeground(Color.WHITE);
		leftArrow.addMouseListener(this);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(leftArrow, constraints);
		
		rightArrow = new JLabel("\u2192");
		rightArrow.setFont(new Font("Arial", 0, 30));
		rightArrow.setBackground(new Color(29, 255, 29));
		rightArrow.setForeground(Color.WHITE);
		rightArrow.addMouseListener(this);
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(rightArrow, constraints);
		
		center = new JLabel("X");
		center.setFont(new Font("Arial", 0, 30));
		center.setBackground(new Color(29, 255, 29));
		center.setForeground(Color.WHITE);
		center.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(center, constraints);
		
		oilyButton = new JButton("oily");
		oilyButton.addMouseListener(this);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(oilyButton, constraints);
		
		stickyButton = new JButton("sticky");
		stickyButton.addMouseListener(this);
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.gridx = 1;
		constraints.gridy = 4;
		add(stickyButton, constraints);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(upArrow)){
			gc.robotStep(0, 1);
		}
		else if(e.getSource().equals(downArrow)){
			gc.robotStep(0, -1);
		}
		else if(e.getSource().equals(rightArrow)){
			gc.robotStep(1, 0);
		}
		else if(e.getSource().equals(leftArrow)){
			gc.robotStep(-1, 0);
		}
		else if(e.getSource().equals(center)){
			gc.robotStep(0, 0);
		}
		else if(e.getSource().equals(oilyButton)){
			gc.placeOily();
		}
		else if(e.getSource().equals(stickyButton)){
			gc.placeSticky();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
