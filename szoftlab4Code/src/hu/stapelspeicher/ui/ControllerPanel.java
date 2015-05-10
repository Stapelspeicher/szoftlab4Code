package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		setBackground(GameMapView.CELL_COLOR);
		setPreferredSize(new Dimension(200, 180));
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(grid);
		
		constraints.weightx = 0.2;
		
		upArrow = new JLabel("\u2191", SwingConstants.CENTER);
		upArrow.setFont(new Font("Arial", 0, 30));
		upArrow.setBackground(new Color(29, 255, 29));
		upArrow.setForeground(Color.WHITE);
		upArrow.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(upArrow, constraints);
		
		downArrow = new JLabel("\u2193", SwingConstants.CENTER);
		downArrow.setFont(new Font("Arial", 0, 30));
		downArrow.setBackground(new Color(29, 255, 29));
		downArrow.setForeground(Color.WHITE);
		downArrow.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(downArrow, constraints);
		
		constraints.weightx = 0.4;
		
		leftArrow = new JLabel("\u2190", SwingConstants.RIGHT);
		leftArrow.setFont(new Font("Arial", 0, 30));
		leftArrow.setBackground(new Color(29, 255, 29));
		leftArrow.setForeground(Color.WHITE);
		leftArrow.addMouseListener(this);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(leftArrow, constraints);
		
		rightArrow = new JLabel("\u2192", SwingConstants.LEFT);
		rightArrow.setFont(new Font("Arial", 0, 30));
		rightArrow.setBackground(new Color(29, 255, 29));
		rightArrow.setForeground(Color.WHITE);
		rightArrow.addMouseListener(this);
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(rightArrow, constraints);
		
		center = new JLabel("\u25a0");
		center.setFont(new Font("Arial", 0, 30));
		center.setBackground(new Color(29, 255, 29));
		center.setForeground(Color.WHITE);
		center.addMouseListener(this);
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(center, constraints);
		
		JPanel spaceHolder = new JPanel();
		spaceHolder.setBackground(new Color(0, true));
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(spaceHolder, constraints);
		
		oilyButton = new JButton("Place oily patch");
		oilyButton.setFont(new Font("Arial", 0, 14));
		oilyButton.addMouseListener(this);
		oilyButton.setBackground(new Color(0, true));
		oilyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		oilyButton.setFocusable(false);
		oilyButton.setContentAreaFilled(false);
		oilyButton.setForeground(Color.white);
		oilyButton.setPreferredSize(new Dimension(170, 25));
		constraints.insets = new Insets(3, 7, 3, 7);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridwidth=3;
		constraints.gridy = 4;
		add(oilyButton, constraints);
		
		stickyButton = new JButton("Place sticky patch");
		stickyButton.setFont(new Font("Arial", 0, 14));
		stickyButton.setBackground(new Color(0, true));
		stickyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		stickyButton.setFocusable(false);
		stickyButton.addMouseListener(this);
		stickyButton.setContentAreaFilled(false);
		stickyButton.setForeground(Color.WHITE);
		stickyButton.setPreferredSize(new Dimension(170, 25));
		constraints.insets = new Insets(3, 7, 3, 7);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 3;
		constraints.gridx = 0;
		constraints.gridy = 5;
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
