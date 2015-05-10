//package hu.stapelspeicher.main;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Scanner;
//import java.util.Set;
//
//public class ProtoController {
//	
//	private static Map<String, ActiveObject> AOList = new HashMap<String, ActiveObject>();
//	private static GameMap map = null;
//	private static Scanner sc = null;
//	private static PrintWriter out = null;
//	
//	private static void setMap(String[] command){
//		Integer x = Integer.parseInt(command[1]);
//		Integer y = Integer.parseInt(command[2]);
//		map = new GameMap(x,y);
//		for(int i = 0; i<x; i++)
//			for(int j=0; j<y; j++)
//				map.addCell(new Position(i, j));
//	}
//	
//	private static void addRobot(String[] command){
//		int sticky = Integer.parseInt(command[2]);
//		int oily = Integer.parseInt(command[3]);
//		int x = Integer.parseInt(command[4]);
//		int y = Integer.parseInt(command[5]);
//		Robot r = new Robot(sticky, oily);
//		Cell c = map.getCell(new Position(x, y));
//		r.setCell(c);
//		c.add(r);
//		AOList.put(command[1], r);
//	}
//	
//	private static void addLittleRobot(String[] command){
//		int x = Integer.parseInt(command[2]);
//		int y = Integer.parseInt(command[3]);
//		LittleRobot lr = new LittleRobot();
//		Cell c = map.getCell(new Position(x, y));
//		lr.setCell(c);
//		c.add(lr);
//		AOList.put(command[1], lr);
//	}
//	
//	private static void step(String[] command){
//		Robot r = (Robot) AOList.get(command[1]);
//		Position p = null;
//		if(command[2].equals("X"))
//			p = new Position(1, 0);
//		else if(command[2].equals("-X"))
//			p = new Position(-1, 0);
//		else if(command[2].equals("Y"))
//			p = new Position(0, 1);
//		else if(command[2].equals("-Y"))
//			p = new Position(0,-1);
//		else if(command[2].equals("I"))
//			p = new Position(0,0);
//		r.addVelocity(p);
//		r.step();
//	}
//	
//	private static void place(String[] command){
//		Robot r = (Robot) AOList.get(command[1]);
//		if(command[2].equals("STICKY"))
//			r.placeSticky();
//		else if(command[2].equals("OILY"))
//			r.placeOily();
//	}
//	
//	private static void endTurn(String[] command){
//		map.decrementRounds();
//		Set<Entry<String, ActiveObject>> AOSet = AOList.entrySet();
//		for(Entry<String, ActiveObject> e : AOSet){
//			if(e.getValue() instanceof LittleRobot)
//				e.getValue().step();
//		}
//	}
//	
//	private static void cellOut(String[] command){
//		int x = Integer.parseInt(command[1]);
//		int y = Integer.parseInt(command[2]);
//		Cell c = map.getCell(new Position(x, y));
//		
//		Trap t = c.getTrapForTest();
//		String trapInfo=" TRAP=";
//		if(t==null)
//			trapInfo = trapInfo + "NULL";
//		else if(t instanceof Sticky){
//			Sticky s = (Sticky) t;
//			trapInfo = trapInfo+"STICKY_"+s.getAbrasionForTest();
//		}
//		else if(t instanceof Oily){
//			Oily o = (Oily) t;
//			trapInfo = trapInfo+"OILY_"+o.getWetnessForTest();
//		}
//		
//		
//		String actorInfo=" ACTORS=";
//		List<ActiveObject> actors = c.getAOsForTest();
//		Set<Entry<String, ActiveObject>> AOSet = AOList.entrySet();
//		for(ActiveObject ao : actors){
//			for(Entry<String, ActiveObject> e : AOSet){
//				if(e.getValue().equals(ao))
//					actorInfo = actorInfo + e.getKey() + "_";
//			}
//		}
//		if(actors.size()==0)
//			actorInfo = actorInfo + "NULL_";
//		actorInfo = actorInfo.substring(0, actorInfo.length()-1);
//		
//		out.print("CELL ");
//		out.print(c.getPositionForTest().getX());
//		out.print("_");
//		out.print(c.getPositionForTest().getY());
//		out.print(trapInfo);
//		out.print(actorInfo);
//		out.println();
//	}
//	
//	private static void robotOut(String[] command){
//		ActiveObject ao = AOList.get(command[1]);
//		if(ao instanceof Robot){
//			Robot r = (Robot) ao;		
//			
//			out.print("ROBOT ");
//			out.print(command[1]);
//			out.print(" VELOCITY=");
//			out.print(r.getVelocityForTest().getX());
//			out.print("_");
//			out.print(r.getVelocityForTest().getY());
//			out.print(" STICKYNUM=");
//			out.print(r.getStickyForTest());
//			out.print(" OILYNUM=");
//			out.print(r.getOilyForTest());
//			out.print(" CURRCELL=");
//			out.print(r.getCell().getPositionForTest().getX());
//			out.print("_");
//			out.print(r.getCell().getPositionForTest().getY());
//			out.println();
//		}
//		else if(ao instanceof LittleRobot){
//			LittleRobot lr = (LittleRobot) ao;
//			
//			String state = null;
//			switch (lr.getStateForTest()) {
//			case NORMAL:
//				state = "NORMAL";
//				break;
//			case CLEANING:
//				state = "CLEANING";
//				break;
//			case DAZED:
//				state = "DAZED";
//				break;
//			default:
//				break;
//			}			
//			
//			out.print("LITTLEROBOT ");
//			out.print(command[1]);
//			out.print(" STATE=");
//			out.print(state);
//			out.print(" DAZEDCOUNTER=");
//			out.print(lr.getDazedCounterForTest());
//			out.print(" CLEANINGCOUNTER=");
//			out.print(lr.getCleaningCounterForTest());
//			out.print(" CURRCELL=");
//			out.print(lr.getCell().getPositionForTest().getX());
//			out.print("_");
//			out.print(lr.getCell().getPositionForTest().getY());
//			out.println();
//		}
//	}
//	
//	private static void addSticky(String[] command){
//		int x = Integer.parseInt(command[1]);
//		int y = Integer.parseInt(command[2]);
//		map.getCell(new Position(x, y)).add(new Sticky());
//	}
//	
//	private static void addOily(String[] command){
//		int x = Integer.parseInt(command[1]);
//		int y = Integer.parseInt(command[2]);
//		map.getCell(new Position(x, y)).add(new Oily());
//	}
//	
//	
//	private static void single(String args[]) throws IOException{
//		if(args.length<3){
//			throw new IOException("Illegal number of parameters!");
//		}
//		out = new PrintWriter(args[2]);
//		sc = new Scanner(new File(args[1]));
//		String[] command = new String[1];
//		command[0] = "START";
//		while (sc.hasNext() && !("ENDGAME".equals(command[0]))) {
//			command = sc.nextLine().split(" ");
//			if(command[0].equals("MAP"))
//				setMap(command);
//			else if(command[0].equals("ADDROBOT"))
//				addRobot(command);
//			else if(command[0].equals("ADDLITTLEROBOT"))
//				addLittleRobot(command);
//			else if(command[0].equals("STEP"))
//				step(command);
//			else if(command[0].equals("PLACE"))
//				place(command);
//			else if(command[0].equals("ENDTURN"))
//				endTurn(command);
//			else if(command[0].equals("CELLOUT"))
//				cellOut(command);
//			else if(command[0].equals("ROBOTOUT"))
//				robotOut(command);
//			else if(command[0].equals("ADDSTICKY"))
//				addSticky(command);
//			else if(command[0].equals("ADDOILY"))
//				addOily(command);
//		}
//		out.close();
//		sc.close();
//	}
//	
//	private static boolean resultMatches(File result, File expected) throws FileNotFoundException{
//		Scanner resultScanner = new Scanner(result);
//		Scanner expectedScanner = new Scanner(expected);
//		String resultLine;
//		String expectedLine;
//		while(resultScanner.hasNext()){
//			resultLine = resultScanner.nextLine();
//			expectedLine = expectedScanner.nextLine();
//			if(!resultLine.equals(expectedLine)){
//				resultScanner.close();
//				expectedScanner.close();
//				return false;
//			}
//		}
//		if(expectedScanner.hasNext())
//			return false;
//		resultScanner.close();
//		expectedScanner.close();
//		return true;
//	}
//	
//	private static void checkResults(File testDir, File resultDir, File expectedDir) throws FileNotFoundException{
//		PrintWriter results = new PrintWriter(new File(resultDir, "results.html"));
//		Scanner resultTemplate = null;
//		try{
//			resultTemplate = new Scanner(new File("../test/test.html"));
//		}
//		catch(Exception e){
//			resultTemplate = new Scanner(new File("test/test.html"));
//		}
//		String line = resultTemplate.nextLine();
//		while(!line.equals("<!--RESULTS-->")){
//			results.println(line);
//			line=resultTemplate.nextLine();
//		}
//		
//		File[] testCases = testDir.listFiles();
//		for(File f : testCases){
//			File resultFile = new File(resultDir, f.getName().replace(".txt", ".result.txt"));
//			File expectedFile = new File(expectedDir, f.getName().replace(".txt", ".result.txt"));
//			results.println("<tr bgcolor=\"" +
//						    (resultMatches(resultFile, expectedFile) ? "#CCFFCC" : "#FF9C9C") +
//						    "\">");
//			results.println("<td><a href=\"" +
//						    f.getAbsolutePath() +
//						    "\">" +
//						    f.getName() +
//						    "</a></td>");
//			results.println("<td><a href=\"" +
//						    resultFile.getAbsolutePath() +
//						    "\">" +
//						    resultFile.getName() +
//						    "</a></td>");
//			results.println("<td><a href=\"" +
//						    expectedFile.getAbsolutePath() +
//						    "\">" +
//						    expectedFile.getName() +
//						    "</a></td>");
//			results.println("</tr>");
//		}
//		
//		while(resultTemplate.hasNext()){
//			line=resultTemplate.nextLine();
//			results.println(line);
//		}
//		
//		results.close();
//		resultTemplate.close();
//	}
//	
//	private static void multiple(String[] args) throws IOException{
//		if(args.length<3){
//			throw new IOException("Illegal number of parameters!");
//		}
//		File testDir = new File(args[1]);
//		File resultDir = new File(args[2]);
//		File expectedDir = null;
//		if(args.length==4)
//			expectedDir = new File(args[3]);
//		if(!testDir.isDirectory() || !resultDir.isDirectory())
//			throw new IOException("The test or result path is not a directory!");
//		
//		File[] testCases = testDir.listFiles();
//		for(File f : testCases){
//			String[] singleParams = new String[3];
//			singleParams[0] = "single";
//			singleParams[1] = f.getCanonicalPath();
//			singleParams[2] = (new File(resultDir, f.getName().replace(".txt", ".result.txt"))).getCanonicalPath();
//			single(singleParams);
//		}
//		
//		if(expectedDir!=null)
//			checkResults(testDir, resultDir, expectedDir);
//	}
//	
///*	public static void main(String args[]) throws IOException{
//		if(args[0].equals("single"))
//			single(args);
//		else if(args[0].equals("multiple"))
//			multiple(args);
//		else
//			System.out.println("Illegal first parameter. Terminating.");
//	}*/
//}
