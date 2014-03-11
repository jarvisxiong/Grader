package gradingTools.comp110.assignment3.testCases;

import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.ArgsAndReturnValues;
import gradingTools.sharedTestCase.MethodFinderAndRunner;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CountUniqueCharactersTestCase extends MethodFinderAndRunner {

	private static Random rand = new Random();

	private static ArrayList<Character> possibleChars = null;

	private static void initializePossibleChars() {
		possibleChars = new ArrayList<Character>();
		for (int i = 0; i < 26; i++) {
			possibleChars.add((char) ((int) 'a' + i));
			possibleChars.add((char) ((int) 'A' + i));
		}
		for (int i = 0; i < 10; i++) {
			possibleChars.add((char) ((int) '0' + i));
		}
	}

	public static ArgsAndReturnValues getRandomInputs(int minLength, int maxLength) {

		int length = rand.nextInt(maxLength + 1 - minLength) + minLength;

		String inputStr = "";
		for (int i = 0; i < length; i++) {
			char c = getRandomCharacter();
			inputStr += c;
		}

		Set<Character> characters = new TreeSet<Character>();
		String lowerCaseInputStr = inputStr.toLowerCase();
		for (int i = 0; i < lowerCaseInputStr.length(); i++) {
			characters.add(lowerCaseInputStr.charAt(i));
		}

		ArgsAndReturnValues toReturn = new ArgsAndReturnValues();
		Object[] args = new Object[1];
		args[0] = inputStr;
		int numCharacters = characters.size();
		toReturn.addArgsAndReturnValues(args, numCharacters);

		return toReturn;
	}

	private static char getRandomCharacter() {
		if (possibleChars == null) {
			initializePossibleChars();
		}
		int pos = rand.nextInt(possibleChars.size());
		return possibleChars.get(pos);
	}

	public CountUniqueCharactersTestCase(ArgsAndReturnValues argsAndRetVals, long timeout) {
		super(new ASimpleMethodMatcher("countUniqueCharacters"), argsAndRetVals.getArgumentSets(),
				argsAndRetVals.getReturnValues(), timeout);
	}

}
