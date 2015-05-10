package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;
import hu.stapelspeicher.modell.GameMap;
import hu.stapelspeicher.modell.Oily;
import hu.stapelspeicher.modell.Position;
import hu.stapelspeicher.modell.Sticky;
import hu.stapelspeicher.modell.Trap;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Barni
 * a jatekterulet megjeleniteset elvegzo osztaly
 */
public class GameMapView{
	/**
	 * a jatek objektum, aminek a kirajzolasat vegzi az osztaly
	 */
	GameMap game;
	/**
	 * a jatekterulet es a panel szele kozotti tavolsag 
	 */
	public static final int BORDER_WIDTH = 29;
	/**
	 * egy cella merete
	 */
	public static final int CELL_SIZE = 32;
	/**
	 * a cellak kozotti res merete
	 */
	public static final int CELL_GAP = 2;
	/**
	 * a cellak szine
	 */
	public static final Color CELL_COLOR = new Color(69, 69, 69);
	
	/**
	 * az osztaly konstruktora, beallitja a kirajzolando jatekot
	 * @param g a kirajzolando jatek
	 */
	public GameMapView(GameMap g){
		game = g;
	}
	
	/**
	 * a jatektable kirajzolasat vegzi el
	 * @param g a grafikus objektum, amire rajzolni kell
	 */
	public void paintMap(Graphics g){
		g.setColor(CELL_COLOR);
		for(int i=0; i<game.getHeight(); i++){
			for(int j=0; j<game.getWidth(); j++){
				g.fillRect(BORDER_WIDTH+j*(CELL_GAP+CELL_SIZE),
						BORDER_WIDTH+i*(CELL_GAP+CELL_SIZE),
						CELL_SIZE,
						CELL_SIZE);
			}
		}
	}
	
	/**
	 * egy olajfolt kirajzolasat vegzi el az adott koordinatara
	 * @param g a grafikus objektum amire rajzolni kell
	 * @param x a folt cellajanak x koordinataja
	 * @param y a folt cellajanak y koordinataja
	 */
	private void paintOily(Graphics g, int x, int y){
		g.fillRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.CELL_SIZE-20,
				GameMapView.CELL_SIZE-20);
	}
	
	/**
	 * egy ragacsfolt kirajzolasat vegzi el az adott koordinatara
	 * @param g a grafikus objektum amire rajzolni kell
	 * @param x a folt cellajanak x koordinataja
	 * @param y a folt cellajanak y koordinataja
	 */
	private void paintSticky(Graphics g, int x, int y){
		g.drawRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.CELL_SIZE-20,
				GameMapView.CELL_SIZE-20);
	}
	
	/**
	 * az osszes jatekban szereplo csapdat rajzolja ki
	 * @param g a grafikus objektum, amire rajzolni kell
	 */
	public void paintTraps(Graphics g){
		g.setColor(Color.WHITE);
		for(int i=0; i<game.getHeight(); i++){
			for(int j=0; j<game.getWidth(); j++){
				Trap t = game.getCell(new Position(i, j)).getTrapForTest();
				if(t!=null){
					if(t instanceof Oily){
						paintOily(g, i, j);
					}
					if(t instanceof Sticky){
						paintSticky(g, i, j);
					}
				}
			}
		}
	}
}
