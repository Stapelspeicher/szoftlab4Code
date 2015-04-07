package hu.stapelspeicher.main;

/**
 * olajfolt osztaly
 * @author Kkari
 *
 */
public class Oily implements Trap
{
	private int wetness = 3;
	
	/**
	 * Az olajfolt nedvesseget adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert nedvesseg
	 */
	public int getWetnessForTest(){
		return wetness;
	}
	
	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.Trap#stepOn(hu.stapelspeicher.main.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao)
	{
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.oilyEffect();
		Logger.exitFunction();
	}

	@Override
	public boolean dry() {
		wetness--;
		return (wetness==0);
	}

	@Override
	public boolean abrade() {
		return false;
	}

}
