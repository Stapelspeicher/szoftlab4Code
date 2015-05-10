package hu.stapelspeicher.ui;

import hu.stapelspeicher.controller.GameController;
import hu.stapelspeicher.modell.GameMap;
import hu.stapelspeicher.modell.Oily;
import hu.stapelspeicher.modell.Position;
import hu.stapelspeicher.modell.Sticky;
import hu.stapelspeicher.modell.Trap;

import java.awt.Color;
import java.awt.Graphics;

public class GameMapView{
	GameMap game;
	public static final int BORDER_WIDTH = 29;
	public static final int CELL_SIZE = 32;
	public static final int CELL_GAP = 2;
	public static final Color CELL_COLOR = new Color(69, 69, 69);
	
	public GameMapView(GameMap g){
		game = g;
	}
	
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
	
	private void paintOily(Graphics g, int x, int y){
		g.fillRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.CELL_SIZE-20,
				GameMapView.CELL_SIZE-20);
	}
	
	private void paintSticky(Graphics g, int x, int y){
		g.drawRect(GameMapView.BORDER_WIDTH+x*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.BORDER_WIDTH+(GameController.GAMEMAP_SIZE-y-1)*(GameMapView.CELL_GAP+GameMapView.CELL_SIZE)+10,
				GameMapView.CELL_SIZE-20,
				GameMapView.CELL_SIZE-20);
	}
	
	public void paintTraps(Graphics g){
		g.setColor(Color.WHITE);
		for(int i=0; i<game.getHeight(); i++){
			for(int j=0; j<game.getWidth(); j++){
				Trap t = game.getCell(new Position(i, j)).getTrapForTest();
				if(t!=null)
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
