package hu.stapelspeicher.modell;


/**
 * Ragacs osztaly
 * @author Kkari
 *
 */
public class Sticky implements Trap
{

	private int abrasion = 4;
	
	/* (non-Javadoc)
	 * @see hu.stapelspeicher.main.Trap#stepOn(hu.stapelspeicher.main.ActiveObject)
	 */
	@Override
	public void stepOn(ActiveObject ao)
	{
		ao.stickyEffect();;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.Trap#dry()
	 */
	@Override
	public boolean dry() {
		return false;
	}

	/* (non-Javadoc)
	 * @see hu.stapelspeicher.modell.Trap#abrade()
	 */
	@Override
	public boolean abrade() {
		abrasion--;
		return (abrasion==0);
	}

}
