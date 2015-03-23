package hu.stapelspeicher.main;


public class GameMap
{

	private Cell[][] cells;
	private int rounds;

	
	private int xlength;
	private int ylength;


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
		
		xlength = cells.length;
		ylength = cells[0].length;
		
		
		if ((cells[p.getX() - 1][p.getY()]).isEmpty() && cells[p.getX() - 1][p.getY()] != null && p.getX()-1 >= 0)
		{
			return cells[p.getX()-1][p.getY()];
		} else if ((cells[p.getX() + 1][p.getY()]).isEmpty() && cells[p.getX() + 1][p.getY()] != null && p.getX()+1 <= xlength)
		{
			return cells[p.getX() + 1][p.getY()];
		} else if ((cells[p.getX()][p.getY() - 1]).isEmpty() && cells[p.getX()][p.getY() - 1] != null && p.getY()-1 >= 0)
		{
			return cells[p.getX()][p.getY() - 1];
		} else if ((cells[p.getX()][p.getY() + 1]).isEmpty() && cells[p.getX()][p.getY() + 1] != null && p.getY() <= ylength)
		{
			return cells[p.getX()][p.getY() + 1];
		} else if ((cells[p.getX() + 1][p.getY() + 1]).isEmpty() && cells[p.getX() + 1][p.getY() + 1] != null && p.getX()+1 <= xlength && p.getY()+1 <= ylength )
		{
			return cells[p.getX() + 1][p.getY() + 1];
		} else if ((cells[p.getX() + 1][p.getY() - 1]).isEmpty() && cells[p.getX() + 1][p.getY() - 1] != null && p.getX()+1 <= xlength && p.getY()-1 >= 0)
		{
			return cells[p.getX() + 1][p.getY() - 1];
		} else if ((cells[p.getX() - 1][p.getY() + 1]).isEmpty() && cells[p.getX() - 1][p.getY() + 1] != null && p.getX()-1 >= 0 && p.getY()+1 <= ylength)
		{
			return cells[p.getX() - 1][p.getY() + 1];
		} else if ((cells[p.getX() - 1][p.getY() - 1]).isEmpty() && cells[p.getX() - 1][p.getY() - 1] != null && p.getX()-1 >= 0 && p.getY()-1 >= 0)
		{
			return cells[p.getX() - 1][p.getY() - 1];
		} 
		
		
		Logger.exitFunction();
		return null;

	}

	public boolean decrementRounds()
	{
		rounds--;
		if(rounds>0)
			return true;
		else
			return false;
	}
}
