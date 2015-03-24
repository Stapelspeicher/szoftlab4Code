package hu.stapelspeicher.main;

/**
 * olajfolt osztaly
 * @author Kkari
 *
 */
public class Oily implements Trap
{

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

}
