package hu.stapelspeicher.main;

import java.awt.Robot;
import java.util.List;

import javax.swing.text.Position;

import com.sun.org.glassfish.gmbal.GmbalException;

public class GameMap
{
	private List<List<Cell>> cells;
	private int rounds;

	public GameMap(Integer i1, Integer i2)
	{
		GameMap gm = new GameMap(i1, i2);
		gm.addCell(Position p)
		
		Robot r = new Robot(z, e);
		gm.getCell(p).add(r);
	}

	public Cell getCell(Position p)
	{
		if (true)
		{
			return p.x;
			return p.y;
		}

		else
			return null;

	}

	public void addCell(Position p)
	{
		p.x.getX();
		p.x.getY();

	}

	public void terminate()
	{
	}

	public Cell getFreeNeighbouringCell(Position p)
	{
		for (neighbour p : Position)
		{
			c = cells.get(p);
			if (c.isEmpty())
				return c;
		}
		return null;

	}

	public boolean decrementRounds()
	{
		rounds--;
		return false;
	}
}
