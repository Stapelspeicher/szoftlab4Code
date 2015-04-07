package hu.stapelspeicher.main;

public interface ActiveObject {
	/**
	 * Ez a fuggveny kezeli le azt az esetet, amikor 2 ActiveObject utkozik egymassal
	 * @param ao - a this-hez kepest ez az objektum lesz a masik, amelyik az utkozestben reszt vesz
	 */
	void stepOn(ActiveObject ao);
	
	
	/**
	 * oilyEffect azt jelenti, hogy a robot, amelyik egy olajos cellara lepett, ebben a korben nem valtoztathatja sebesseget
	 */
	void oilyEffect();
	
	
	/**
	 * stickyEffect hatasara a robot sebessege felezodik
	 */
	void stickyEffect();
	
	
	/**
	 * Ez a fuggveny azt kezeli le, amikor egy ActiveObject utkozik egy robottal
	 * @param other - az a robot, amivel az activeObject utkozik
	 */
	void collideWithRobot(Robot other);
	
	/**
	 * Ez a fuggveny azt kezeli le, amikor egy ActiveObject utkozik egy kisrobottal
	 * @param other - az a kisrobot, amivel az activeObject utkozik
	 */
	void collideWithLittleRobot(LittleRobot other);
	
	
	/**
	 * az objektum "meghal", azaz leesik a palyarol, vagy olyan modon utkozik egy/tobb masik robottal, hogy mind meghalnak
	 */
	void die();
	
	
	/**
	 * Beallitjuk az ActiveObject jelenlegi cellajat, azaz azt, hogy eppen melyik cellan tartozkodik
	 * @param c - az a cella, ahol jelenleg tartozkodik
	 */
	void setCell(Cell c);
	
	
	/**
	 * A lepes tenyleges lebonyolitasaert ez a fuggveny felelos
	 */
	void step();
	
	
	/**
	 * Ezzel a fuggvennyel kerdezheto le, hogy jelenleg melyik cellan all az ActiveObjectunk
	 * @return - a visszateresi ertek ez a cella lesz
	 */
	Cell getCell();
}
