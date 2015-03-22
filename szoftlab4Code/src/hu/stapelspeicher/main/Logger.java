package hu.stapelspeicher.main;

import java.util.HashMap;
import java.util.Map;

public class Logger {
	private static int depth = 0;
	public static Map<String, String> objectNames = new HashMap<String, String>();
	
	private static void addSpaces() {
		for (int i = 0; i < depth*4; i++)
			System.out.print(" ");
	}
	public static void enterFunction(String s, Object o) {
		addSpaces();
		depth += 1;
		String name = objectNames.get(o.toString());
		
		String short_id = o.toString().substring(o.toString().lastIndexOf(".") + 1,
													o.toString().length());
		
		if(short_id.contains("@")) {
			if (name == null) name = " id: " + short_id;
			else {
				name = " name: " + name + "Object id: " + short_id;
			}
			System.out.println(o.getClass().getSimpleName() + "." + s + name);
		} else {
			System.out.println(short_id + "." + s);
		}
	}
	
	public static void exitFunction() {
		if (depth != 0) depth -= 1;
	}
}
