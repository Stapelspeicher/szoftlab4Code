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
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				addCell(new Position(i, j));
			}
		}
		Robot r = new Robot(5, 5);
		getCell().add(r);
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
		cells[p.getX()][p.getY()] = new Cell();
		Logger.exitFunction();
	}

	public Cell getFreeNeighbouringCell(Position p)
	{
		Logger.enterFunction("getFreeNeigbouring(Position p)", this),
		for(neighbour p : Position)
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
