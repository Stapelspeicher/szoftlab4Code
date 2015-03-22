package hu.stapelspeicher.main;

public class Sticky implements Trap
{

	@Override
	public void stepOn(ActiveObject ao)
	{
		// TODO Auto-generated method stub
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.stickyEffect();
		Logger.exitFunction();
	}

}
