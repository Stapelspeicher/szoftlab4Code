package hu.stapelspeicher.modell;


public class GameMap
{

	private Cell[][] cells;
	private int rounds;

	
	private int xlength;
	private int ylength;
	
	public int getRounds(){
		return rounds;
	}

	public int getWidth(){
		return xlength;
	}
	
	public int getHeight(){
		return ylength;
	}
	
	/**
	 * Beallit egy bizonyos mennyisegu korszamot a jatek elejen
	 * @param rounds a korok kivant szama
	 */
	public void setRounds(int rounds){
		Logger.enterFunction("setRounds(int rounds)", this);
		this.rounds = rounds;
		Logger.exitFunction();
	}
	
	/**
	 * A GameMap ket parameteres konstruktora, beallitja a 
	 * a korok szamat egy elore definialt ertekre.
	 * @param x A palya nagysaga vizszintesen
	 * @param y A palya nagysaga fuggolegesen
	 */
	public GameMap(Integer x, Integer y)
	{
		Logger.enterFunction("GameMap(Integer x, Integer y)", this);
		cells = new Cell[x][y];
		xlength = x;
		ylength = y;
		rounds=10;
		Logger.exitFunction();
	}

	/**
	 * Visszaadja a palya egy cellajat, vagy null-t ha
	 * az adott helyen nincs cella
	 * @param p a kert cella pozicioja
	 * @return a kert cella vagy null
	 */
	public Cell getCell(Position p)
	{
		Logger.enterFunction("getCell(Position p)", this);
		Logger.exitFunction();
		if(p.getX()<0 || p.getX() >= xlength || p.getY()<0 || p.getY() >= ylength)
			return null;
		return cells[p.getX()][p.getY()];
	}

	/**
	 * Cella hozzaadasa a palyahoz
	 * @param p A cella kivant pozicioja
	 */
	public void addCell(Position p)
	{
		Logger.enterFunction("addCell(Position p)", this);
		Cell c = new Cell();
		c.setPosition(p);
		c.setMap(this);
		cells[p.getX()][p.getY()] = c;
		Logger.exitFunction();
	}

	/**
	 * Az atadott pozicio korul visszaad egy ures celat, vagy nullt,
	 * ha nincs ilyen.
	 * @param p A cella pozicioja, aminek egy ures szomszedjat keressuk
	 * @return egy ures szomszedos cella, vagy null ha nincs ilyen
	 */
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

	/**
	 * Korok szamanak csokkentese
	 * @return Ha meg nem fogytak el a korok, akkor true, ha mar elfogytak, akkor false
	 */
	public boolean decrementRounds()
	{
		rounds--;
		if(rounds==0)
			return false;
		for(int i=0; i<xlength ; i++){
			for(int j=0; j<ylength; j++){
				if(cells[i][j]!=null){
					cells[i][j].dry();
				}
			}
		}
		return true;
	}
}
