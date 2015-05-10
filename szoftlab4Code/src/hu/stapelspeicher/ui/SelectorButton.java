package hu.stapelspeicher.ui;

import javax.swing.JButton;

/**
 * @author Barni
 * a jatekosok kivalasztanal az egyes valasztasi lehetosegeket
 * ilyen osztalyu gombok reprezentaljak
 */
public class SelectorButton extends JButton {
	/**
	 * a gomb sorszama a sorban 
	 */
	private int id;
	
	/**
	 * konstruktor, beallitja a sorszamot
	 * @param id a kivant sorszam
	 */
	public SelectorButton(int id){
		this.id=id;
	}
	
	/**
	 * visszaadja a gomb sorszamat
	 * @return a gomb sorszama
	 */
	public int getID(){
		return id;
	}
}
