package hu.stapelspeicher.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameStartPanel extends JPanel implements MouseListener{
	private SelectorButton selectorButtons[];
	private JLabel playerLabel;
	private int players;
	private GameController gc;
	private static final Color selectorButtonColors[]={
		new Color(27, 88, 184),
		new Color(184, 27, 108),
		new Color(105, 26, 184),
		new Color(184, 27, 27),
		new Color(21, 152, 42),
		new Color(228, 107, 25)
	};
	
	public GameStartPanel(GameController gc){
		this.gc = gc;
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(new Color(29, 29, 29));
		setVisible(true);
		setLayout(new GridBagLayout());
		
		JPanel innerPanel = new JPanel();
		innerPanel.setBackground(new Color(29, 29, 29));
		innerPanel.setPreferredSize(new Dimension(300, 150));
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=300;
		c.gridheight=200;
		add(innerPanel, c);
		innerPanel.setLayout(new BorderLayout());
		
		JPanel innerUpperPanel = new JPanel();
		innerUpperPanel.setBackground(new Color(29, 29, 29));
		innerUpperPanel.setPreferredSize(new Dimension(260, 75));
		innerUpperPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		GridLayout grid = new GridLayout();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setRows(1);
		grid.setColumns(6);
		innerUpperPanel.setLayout(grid);
		innerPanel.add(innerUpperPanel, BorderLayout.NORTH);
		
		selectorButtons = new SelectorButton[6];
		for(int i=0; i<6; i++){
			selectorButtons[i] = new SelectorButton(i+1);
			selectorButtons[i].setBackground(selectorButtonColors[i]);
			selectorButtons[i].setPreferredSize(new Dimension(35, 35));
			selectorButtons[i].setBorder(BorderFactory.createEmptyBorder());
			selectorButtons[i].addMouseListener(this);
			selectorButtons[i].addMouseListener(this);
			innerUpperPanel.add(selectorButtons[i]);
		}
		
		playerLabel = new JLabel("", SwingConstants.CENTER);
		playerLabel.setBackground(new Color(29, 29, 29));
		playerLabel.setForeground(Color.WHITE);
		playerLabel.setFont(new Font("Arial", 0, 30));
		innerPanel.add(playerLabel, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		gc.startGame(players);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		int i=0;
		while(!selectorButtons[i].equals(event.getSource())){
			selectorButtons[i].setBackground(RobotView.robotColors[i]);
			i++;
		}
		selectorButtons[i].setBackground(RobotView.robotColors[i]);
		players=i+1;
		playerLabel.setText(players+" player"+(i==0 ? "" : "s"));
	}

	@Override
	public void mouseExited(MouseEvent event) {
		int i=0;
		while(!selectorButtons[i].equals(event.getSource())){
			selectorButtons[i].setBackground(selectorButtonColors[i]);
			i++;
		}
		selectorButtons[i].setBackground(selectorButtonColors[i]);
		playerLabel.setText("");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}


}
