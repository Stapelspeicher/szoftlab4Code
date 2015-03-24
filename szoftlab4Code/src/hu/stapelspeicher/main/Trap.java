package hu.stapelspeicher.main;

/**
 * Csapda interface, amelyik osszefogja az egyes akadalyozo elemeket
 * es biztositja az egyseges kezelesuket
 * @author Kkari
 *
 */
public interface Trap {
	/**
	 * Ez a fuggveny intezi a csapda specifikus viselkedest
	 * 
	 * @param ao Az az ActiveObject, amelyik ralepett a csapdara.
	 */
	void stepOn(ActiveObject ao);
}
