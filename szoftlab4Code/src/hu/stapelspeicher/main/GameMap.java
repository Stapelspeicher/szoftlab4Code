package hu.stapelspeicher.main;

import java.util.List;

import com.sun.istack.internal.logging.Logger;

public class GameMap
{
	private Cell[][] cells;
	private int rounds;

	public GameMap(Integer i1, Integer i2)
	{
		Logger.enterFunction("GameMap(Integer i1, Integer i2", this);
		GameMap gm = new GameMap(i1, i2);
		gm.addCell(p);
		Robot r = new Robot();
		gm.getCell(p).add(r);
		Logger.exitFunction();
	}

	public Cell getCell(Position p)
	{
		Logger.enterFunction("getCell(Position p)", this);
		return cells[p.x][p.y];
		Logger.exitFunction();
	}

	public void addCell(Position p)
	{
		Logger.enterFunction("addCell(Position p)", this);
		cells[][] = new Cell(p.getX(), p.getY());
		Logger.exitFunction();
	}

	public void terminate()
	{
	}

	public Cell getFreeNeighbouringCell(Position p)
	{
		Logger.enterFunction("getFreeNeigbouring(Position p)", this),
		for (neighbour p : Position)
		{
			c = cells.get(p);
			if (c.isEmpty())
				return c;
		}
		Logger.exitFunction();
		return null;

	}

	public boolean decrementRounds()
	{
		rounds--;
		return false;
	}
}
