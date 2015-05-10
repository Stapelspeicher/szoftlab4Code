package hu.stapelspeicher.ui;

import javax.swing.JButton;

public class SelectorButton extends JButton {
	private int id;
	
	public SelectorButton(int id){
		this.id=id;
	}
	
	public int getID(){
		return id;
	}
}
