package grader.requirements.interpreter.checkers;

import java.util.HashMap;
import java.util.Map;

public class InterpretedCheckerRegistry {
	public static final String DIFF_NAME = "diff";
	static Map<String, InterpretedChecker> nameToChecker = new HashMap<>();
	public static void register(String aName, InterpretedChecker aChecker) {
		nameToChecker.put(aName, aChecker);
	}
	
	public static InterpretedChecker getInterpretedChecker(String aName) {
		return nameToChecker.get(aName);
	}
	

}
