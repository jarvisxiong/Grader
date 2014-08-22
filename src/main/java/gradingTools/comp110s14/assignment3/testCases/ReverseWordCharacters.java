package gradingTools.comp110s14.assignment3.testCases;

import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.ArgsAndReturnValues;
import gradingTools.sharedTestCase.MethodFinderAndRunner;

import java.util.ArrayList;
import java.util.Random;

public class ReverseWordCharacters extends MethodFinderAndRunner {

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

	public static ArgsAndReturnValues getRandomInputs(int minWords, int maxWords, int maxWordLength) {

		int numWords = rand.nextInt(maxWords + 1 - minWords) + minWords;

		String inputStr = "";
		String outputStr = "";
		for (int i = 0; i < numWords; i++) {
			String word = getWord(maxWordLength);
			String reverseWord = new StringBuffer(word).reverse().toString();

			if (i > 0) {
				inputStr += " ";
				outputStr += " ";
			}

			inputStr += word;
			outputStr += reverseWord;
		}

		ArgsAndReturnValues toReturn = new ArgsAndReturnValues();
		Object[] args = new Object[1];
		args[0] = inputStr;
		toReturn.addArgsAndReturnValues(args, outputStr);

		return toReturn;
	}

	private static String getWord(int maxLength) {
		int length = rand.nextInt(maxLength) + 1;
		String word = "";
		for (int i = 0; i < length; i++) {
			word += getRandomCharacter();
		}
		return word;
	}

	private static char getRandomCharacter() {
		if (possibleChars == null) {
			initializePossibleChars();
		}
		int pos = rand.nextInt(possibleChars.size());
		return possibleChars.get(pos);
	}

	public ReverseWordCharacters(ArgsAndReturnValues argsAndRetVals, long timeout) {
		super(new ASimpleMethodMatcher("reverseWordCharacters"), argsAndRetVals.getArgumentSets(),
				argsAndRetVals.getReturnValues(), timeout);
	}

}
