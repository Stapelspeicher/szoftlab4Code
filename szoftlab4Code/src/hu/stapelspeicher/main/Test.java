package hu.stapelspeicher.main;

public class Test {

	public static void main(String[] args) {
		Logger.enterFunction("main(String[])", Test.class);
		lol("lol");
		lol("lol");
		
		Position p = new Position(1,2);
	}
	
	public static void lol(String s) {
		Logger.enterFunction("lol(String)", Test.class);
		Logger.exitFunction();
	}

}
