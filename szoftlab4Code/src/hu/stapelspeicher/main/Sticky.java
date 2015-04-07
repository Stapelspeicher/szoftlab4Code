package hu.stapelspeicher.main;

/**
 * Ragacs osztaly
 * @author Kkari
 *
 */
public class Sticky implements Trap
{

	private int abrasion = 4;
	
	/**
	 * A ragacsfolt kopottsagat adja vissza.
	 * ----CSAK A TESZTELESHEZ HASZNALT!----
	 * @return a kert kopottsag
	 */
	public int getAbrasionForTest(){
		return abrasion;
	}
	
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

	@Override
	public boolean dry() {
		return false;
	}

	@Override
	public boolean abrade() {
		abrasion--;
		return (abrasion==0);
	}

}
