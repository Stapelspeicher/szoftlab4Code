package hu.stapelspeicher.modell;


/**
 * olajfolt osztaly
 * @author Kkari
 *
 */
public class Oily implements Trap
{
	private int wetness = 4;
	
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
		ao.oilyEffect();
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.Trap#dry()
	 */
	@Override
	public boolean dry() {
		wetness--;
		return (wetness==0);
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.Trap#abrade()
	 */
	@Override
	public boolean abrade() {
		return false;
	}

}
