package hu.stapelspeicher.main;


public class GameMap
{

	private Cell[][] cells;
	private int rounds;

	public GameMap(Integer i1, Integer i2)
	{
		Logger.enterFunction("GameMap(Integer i1, Integer i2)", this);
		cells = new Cell[i1][i2];
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
		} else if ((cells[p.getX() + 1][p.getY() + 1]).isEmpty() == true)
		{
			return cells[p.getX() + 1][p.getY() + 1];
		} else if ((cells[p.getX() + 1][p.getY() - 1]).isEmpty() == true)
		{
			return cells[p.getX() + 1][p.getY() - 1];
		} else if ((cells[p.getX() - 1][p.getY() + 1]).isEmpty() == true)
		{
			return cells[p.getX() - 1][p.getY() + 1];
		} else if ((cells[p.getX() - 1][p.getY() - 1]).isEmpty() == true)
		{
			return cells[p.getX() - 1][p.getY() - 1];
		} else if (cells[p.getX()][p.getY()] == null)
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
