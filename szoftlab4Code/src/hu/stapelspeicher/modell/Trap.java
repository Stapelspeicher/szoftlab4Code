package hu.stapelspeicher.modell;


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
	
	/**
	 * A csapda szarazsagat hivatott novelni
	 * @return true, ha teljesen ebben a korben szaradt fel a csapda, kolunben false
	 */
	boolean dry();
	
	/**
	 * A csapda elhasznalodottsagat hivatott novelni
	 * @return true, ha teljesen ebben a korben elkopott a csapda, kolunben false
	 */
	boolean abrade();
}
