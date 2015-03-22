package hu.stapelspeicher.main;

public class Oily implements Trap
{

	@Override
	public void stepOn(ActiveObject ao)
	{
		Logger.enterFunction("stepOn(ActiveObject ao)", this);
		ao.oilyEffect();
		Logger.exitFunction();
	}

}
