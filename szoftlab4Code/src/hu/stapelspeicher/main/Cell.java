package hu.stapelspeicher.main;

import java.util.List;

public class Cell {
	private List<ActiveObject> actors;
	private Trap trap;
	private Position pos;
	private GameMap map;
	
	public void add(ActiveObject ao) {}
	public void add(Trap t) {}
	public Cell getCellFromHere(Position p) {
		return null;}
	public void remove(ActiveObject ao) {}
	public boolean isEmpty() {
		return false;}
	public double getDistanceFromCell(Cell c) {
		return 0;}
	public Cell getFreeNeighbouringCell() {
		return null;}
	
}
