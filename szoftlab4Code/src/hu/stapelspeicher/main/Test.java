package hu.stapelspeicher.main;

import java.util.Scanner;

public class Test {

	private static Scanner in;
	
	public static void main(String[] args) {
		
		in = new Scanner(System.in);
		int chosenIndex=0;
		chosenIndex = printIndex();
		
		switch (chosenIndex) {
		case 1:
			launchGame();
			break;
		case 2:
			changeRobotSpeed();
			break;
		case 3:
			placeOily();
			break;
		case 4:
			placeSticky();
			break;
		case 5:
			nextRound();
			break;
		case 6:
			step();
			break;
		default:
			break;
		}
		
		in.close();
	}
	
	private static void step(){
		int chosenIndex=0;
		
		while(chosenIndex!=5){
			System.out.println("6.1 Ures sima cellara lep a robot");
			System.out.println("6.2 Ragacsos cellara lep a robot");
			System.out.println("6.3 Olajos cellara lep a robot");
			System.out.println("6.4 Palyan kivulre lep a robot");
			System.out.println("6.5 Fomenube lepes");
			chosenIndex=getIndex(5);
			
			switch (chosenIndex) {
			case 1:
				stepsOnPlainCell();
				break;
			case 2:
				stepsOnStickyCell();
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			default:
				break;
			}
		}
	}
	
	private static void stepsOnStickyCell(){
		Logger.enterFunction("stepsOnStickyCell()", Test.class);
		
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(0, 0));
		gm.addCell(new Position(0, 1));
		Robot r = new Robot(0,0);
		Cell c = gm.getCell(new Position(0, 0));
		r.setCell(c);
		Sticky s = new Sticky();
		c.setMap(gm);
		c.add(s);
		r.addVelocity(new Position(0, 1));
		r.step();	
		
		Logger.exitFunction();
	}
	
	private static void stepsOnPlainCell(){
		Logger.enterFunction("stepsOnPlainCell()", Test.class);
		
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(0, 0));
		gm.addCell(new Position(0, 1));
		Robot r = new Robot(0,0);
		Cell c = gm.getCell(new Position(0, 0));
		r.setCell(c);
		c.setMap(gm);
		r.addVelocity(new Position(0, 1));
		r.step();	
		
		Logger.exitFunction();
	}
	
	private static void nextRound(){
		Logger.enterFunction("nextRound()", Test.class);
		System.out.print("Ez volt az utolso kor? (I/N) ");
		char c = in.next().charAt(0);
		
		GameMap gm = new GameMap(10, 10);
		switch (c) {
		case 'I':
			gm.setRounds(1);
			gm.decrementRounds();
			break;
		case 'N':
			gm.setRounds(10);
			gm.decrementRounds();
			break;
		default:
			break;
		}
		
		Logger.exitFunction();
	}
	
	private static void placeSticky(){	
		System.out.print("Van ragacsfoltja a robotnak? (I/N) ");
		char c = in.next().charAt(0);
		
		Logger.enterFunction("placeSticky()", Test.class);
		
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(0,0));
		Robot r;
		Cell cell = gm.getCell(new Position(0, 0));
		
		switch (c) {
		case 'I':
			r = new Robot(0, 1);
			cell.add(r);
			r.setCell(cell);
			r.placeSticky();
			break;
		case 'N':
			r = new Robot(0, 0);
			cell.add(r);
			r.setCell(cell);
			r.placeSticky();
			break;
		default:
			break;
		}
		
		Logger.exitFunction();
	}
	
	private static void placeOily(){	
		System.out.print("Van olajfoltja a robotnak? (I/N) ");
		char c = in.next().charAt(0);
		
		Logger.enterFunction("placeOily()", Test.class);
		
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(0,0));
		Robot r;
		Cell cell = gm.getCell(new Position(0, 0));
		
		switch (c) {
		case 'I':
			r = new Robot(0, 1);
			cell.add(r);
			r.setCell(cell);
			r.placeOily();
			break;
		case 'N':
			r = new Robot(0, 0);
			cell.add(r);
			r.setCell(cell);
			r.placeOily();
			break;
		default:
			break;
		}
		
		Logger.exitFunction();
	}
	
	private static void changeRobotSpeed(){
		Logger.enterFunction("changeRobotSpeed()", Test.class);
		
		Robot r = new Robot(0,0);
		r.addVelocity(new Position(1, 1));
		
		Logger.exitFunction();
	}
	
	private static int getIndex(int bound){
		int chosenIndex=0;
		System.out.print("A valasztott menupont: ");
		while(chosenIndex<1 || chosenIndex>bound){
			try{
				chosenIndex=Integer.parseInt(in.next());
				if(chosenIndex>bound || chosenIndex<1){
					System.out.println("Kerem egy valos indexet adjon meg!");
					System.out.print("A valasztott menupont: ");
				}
			}
			catch(NumberFormatException e){
				chosenIndex=0;
				System.out.println("Kerem egy szamot adjon meg!");
				System.out.print("A valasztott menupont: ");
			}
		}
		return chosenIndex;
	}
	
	private static void launchGame(){
		int chosenIndex=0;
		
		while(chosenIndex!=3){
			System.out.println("1.1. Cella hozzaadasa");
			System.out.println("1.2. Robot hozzaadasa");
			System.out.println("1.3. Fomenube lepes");
			chosenIndex=getIndex(3);
			
			switch (chosenIndex) {
			case 1:
				createCell();
				break;
				
			case 2:
				createRobot();
				break;
				
			default:
				break;
			}
		}
		
	}
	
	private static void createRobot(){
		Logger.enterFunction("createRobot()", Test.class);
		
		System.out.println("Ragacs es olaj szama szokozzel elvalasztva:");
		int sticky = in.nextInt();
		int oily = in.nextInt();
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(0,0));
		Robot r = new Robot(sticky, oily);
		Cell c = gm.getCell(new Position(0, 0));
		c.add(r);
		
		Logger.exitFunction();
	}
	
	private static void createCell(){
		Logger.enterFunction("createCell()", Test.class);
		
		System.out.println("Cella koordinatai szokozzel elvalasztva:");
		int x = in.nextInt();
		int y = in.nextInt();
		GameMap gm = new GameMap(10, 10);
		gm.addCell(new Position(x, y));
		
		Logger.exitFunction();
	}
	
	private static int printIndex(){
		System.out.println("1. Jatekinditas");
		System.out.println("2. Robot sebessegenek modositasa");
		System.out.println("3. Olajfolt elhelyezese");
		System.out.println("4. Ragacsfolt elhelyezese");
		System.out.println("5. Kovetkezo kor");
		System.out.println("6. Lepes");
		System.out.println("7. Utkozes");
		System.out.println("8. Kilepes a jatekbol");
		return getIndex(8);
	}

}
