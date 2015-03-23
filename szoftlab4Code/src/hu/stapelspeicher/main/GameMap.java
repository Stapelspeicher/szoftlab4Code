package hu.stapelspeicher.main;


public class GameMap
{

	private Cell[][] cells;
	private int rounds;

	
	private int xlength;
	private int ylength;

	
	public void setRounds(int rounds){
		Logger.enterFunction("setRounds(int rounds)", this);
		this.rounds = rounds;
		Logger.exitFunction();
	}
	
	public GameMap(Integer x, Integer y)
	{
		Logger.enterFunction("GameMap(Integer x, Integer y)", this);
		cells = new Cell[x][y];
		rounds=10;
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
		Cell c = new Cell();
		c.setPosition(p);
		cells[p.getX()][p.getY()] = c;
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
