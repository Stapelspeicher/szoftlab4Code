package hu.stapelspeicher.main;

import java.util.List;

import org.omg.PortableServer.ForwardRequestHelper;

public class GameMap
{
	private Cell[][] cells;
	private int rounds;
	// private Cell c;
	private Position pos = new Position(1, 1);

	public GameMap(Integer i1, Integer i2)
	{
		Logger.enterFunction("GameMap(Integer i1, Integer i2)", this);
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				addCell(new Position(i, j));
			}
		}
		// Robot r = new Robot(5, 5);
		getCell(pos);
		Logger.exitFunction();
	}

	public Cell getCell(Position p)
	{
		Logger.enterFunction("getCell(Position p)", this);
		Logger.exitFunction();
		return cells[p.getX()][p.getY()];
	}

	public void addCell(Position p)
	{
		Logger.enterFunction("addCell(Position p)", this);
		cells[p.getX()][p.getY()] = new Cell();
		Logger.exitFunction();
	}

	public Cell getFreeNeighbouringCell(Position p)
	{
		Logger.enterFunction("getFreeNeigbouring(Position p)", this);

		if ((cells[p.getX() - 1][p.getY()]).isEmpty() == true)
		{
			return cells[p.getX()][p.getY()];
		} else if ((cells[p.getX() + 1][p.getY()]).isEmpty() == true)
		{
			return cells[p.getX() + 1][p.getY()];
		} else if ((cells[p.getX()][p.getY() - 1]).isEmpty() == true)
		{
			return cells[p.getX()][p.getY() - 1];
		} else if ((cells[p.getX()][p.getY() + 1]).isEmpty() == true)
		{
			return cells[p.getX()][p.getY() + 1];
		}
		else if ((cells[p.getX()+1][p.getY() + 1]).isEmpty() == true)
		{
			return cells[p.getX()+1][p.getY() + 1];
		}
		else if ((cells[p.getX()+1][p.getY() - 1]).isEmpty() == true)
		{
			return cells[p.getX()+1][p.getY() - 1];
		}
		else if ((cells[p.getX()-1][p.getY() + 1]).isEmpty() == true)
		{
			return cells[p.getX()-1][p.getY() + 1];
		}
		else if ((cells[p.getX()-1][p.getY() -1]).isEmpty() == true)
		{
			return cells[p.getX()-1][p.getY() -1];
		}
		else if (cells[p.getX()][p.getY()]==null)
		{
			
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
