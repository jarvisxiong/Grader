package gradingTools.comp110s14.assignment3.testCases;

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
	private static ArrayList<Character> uncountedChars = null;

	private static void initializeChars() {
		possibleChars = new ArrayList<Character>();
		for (int i = 0; i < 26; i++) {
			possibleChars.add((char) ('a' + i));
			possibleChars.add((char) ('A' + i));
		}
		for (int i = 0; i < 10; i++) {
			possibleChars.add((char) ('0' + i));
		}

		uncountedChars = new ArrayList<Character>();
		uncountedChars.add(' ');
		uncountedChars.add('\t');
		uncountedChars.add('.');
		uncountedChars.add('?');
		uncountedChars.add('!');
		uncountedChars.add(':');
		uncountedChars.add(';');
		uncountedChars.add('-');
		uncountedChars.add('_');
	}

	public static ArgsAndReturnValues getRandomInputs(int minLength, int maxLength) {

		ArgsAndReturnValues countedInputs = getRandomCountedInputs(minLength, maxLength);
		ArgsAndReturnValues uncountedInputs = getZeroCountInputs(minLength, maxLength);

		ArgsAndReturnValues toReturn = new ArgsAndReturnValues();

		for (int i = 0; i < countedInputs.getReturnValues().length; i++) {
			toReturn.addArgsAndReturnValues(countedInputs.getArgumentSets()[i],
					countedInputs.getReturnValues()[i]);
		}
		for (int i = 0; i < uncountedInputs.getReturnValues().length; i++) {
			toReturn.addArgsAndReturnValues(uncountedInputs.getArgumentSets()[i],
					uncountedInputs.getReturnValues()[i]);
		}
		return toReturn;
	}

	private static ArgsAndReturnValues getZeroCountInputs(int minLength, int maxLength) {
		int length = rand.nextInt(maxLength + 1 - minLength) + minLength;

		String inputStr = "";
		for (int i = 0; i < length; i++) {
			char c = getRandomUncountedCharacter();
			inputStr += c;
		}

		ArgsAndReturnValues toReturn = new ArgsAndReturnValues();
		Object[] args = new Object[1];
		args[0] = inputStr;
		toReturn.addArgsAndReturnValues(args, 0);
		return toReturn;
	}

	private static ArgsAndReturnValues getRandomCountedInputs(int minLength, int maxLength) {

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
		if (possibleChars == null || uncountedChars == null) {
			initializeChars();
		}
		int pos = rand.nextInt(possibleChars.size());
		return possibleChars.get(pos);
	}

	private static char getRandomUncountedCharacter() {
		if (possibleChars == null || uncountedChars == null) {
			initializeChars();
		}
		int pos = rand.nextInt(uncountedChars.size());
		return uncountedChars.get(pos);
	}

	public CountUniqueCharactersTestCase(ArgsAndReturnValues argsAndRetVals, long timeout) {
		super(new ASimpleMethodMatcher("countUniqueCharacters"), argsAndRetVals.getArgumentSets(),
				argsAndRetVals.getReturnValues(), timeout);
	}

}
