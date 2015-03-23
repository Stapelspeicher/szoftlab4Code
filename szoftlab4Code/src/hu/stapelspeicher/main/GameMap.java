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
		xlength = x;
		ylength = y;
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
		
		Cell c;
		
		if(p.getX()>0){
			c=cells[p.getX()-1][p.getY()];
			if(c != null)
				if(c.isEmpty())
					return c;
		}

		if(p.getY()>0){
			c=cells[p.getX()][p.getY()-1];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getX()<xlength-1){
			c=cells[p.getX()+1][p.getY()];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getY()<ylength-1){
			c=cells[p.getX()][p.getY()+1];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getX()>0 && p.getY()>0){
			c=cells[p.getX()-1][p.getY()-1];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getX()<xlength-1 && p.getY()>0){
			c=cells[p.getX()+1][p.getY()-1];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getX()>0 && p.getY()<ylength-1){
			c=cells[p.getX()-1][p.getY()+1];
			if(c != null)
				if(c.isEmpty())
					return c;
		}
		
		if(p.getX()<xlength-1 && p.getY()<ylength-1){
			c=cells[p.getX()+1][p.getY()+1];
			if(c != null)
				if(c.isEmpty())
					return c;
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
