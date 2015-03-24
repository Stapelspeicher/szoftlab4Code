package hu.stapelspeicher.main;

/**
 * Ragacs osztaly
 * @author Kkari
 *
 */
public class Sticky implements Trap
{

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.Trap#stepOn(hu.stapelspeicher.main.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao)
	{
		// TODO Auto-generated method stub
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.stickyEffect();
		Logger.exitFunction();
	}

}
