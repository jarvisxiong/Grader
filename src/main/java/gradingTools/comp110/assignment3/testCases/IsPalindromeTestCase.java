package gradingTools.comp110.assignment3.testCases;

import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.MethodFinderAndRunner;

import java.util.ArrayList;
import java.util.Random;

public class IsPalindromeTestCase extends MethodFinderAndRunner {

	private static Random rand = new Random();
	private static ArgsAndRetVals argsAndRetVals = getRandomInputs(10, 20);

	private static ArrayList<Character> possibleChars = null;

	private static void initializePossibleChars() {
		possibleChars = new ArrayList<Character>();
		for (int i = 0; i < 26; i++) {
			possibleChars.add((char) ((int) 'a' + i));
		}
	}

	private static class ArgsAndRetVals {
		ArrayList<Object[]> argSets = new ArrayList<Object[]>();
		ArrayList<Object> retVals = new ArrayList<Object>();

		private void addArgsAndRetVal(Object[] args, Object retVal) {
			argSets.add(args);
			retVals.add(retVal);
		}
	}

	private static ArgsAndRetVals getRandomInputs(int minLength, int maxLength) {

		int length = rand.nextInt(maxLength + 1 - minLength) + minLength;
		String palindrome = createPalindrome(length);
		String nonPalindrome = createNonPalindrome(length);

		ArgsAndRetVals toReturn = new ArgsAndRetVals();
		Object[] args = new Object[1];
		args[0] = palindrome;
		toReturn.addArgsAndRetVal(args, Boolean.TRUE);

		args = new Object[1];
		args[0] = nonPalindrome;
		toReturn.addArgsAndRetVal(args, Boolean.FALSE);

		return toReturn;
	}

	private static char getRandomCharacter() {
		if (possibleChars == null) {
			initializePossibleChars();
		}
		int pos = rand.nextInt(possibleChars.size());
		return possibleChars.get(pos);
	}

	private static String createPalindrome(int length) {
		String result = "";
		if (length % 2 == 1) {
			char randomChar = getRandomCharacter();
			result += randomChar;
		}
		for (int i = 0; i < length / 2; i++) {
			char randomChar = getRandomCharacter();
			result = randomChar + result + randomChar;
		}
		return result;
	}

	private static String createNonPalindrome(int length) {
		// Construct a string of random characters
		String result = "";
		for (int i = 0; i < length; i++) {
			char randomChar = getRandomCharacter();
			result = result + randomChar;
		}

		// Randomly choose a set of positions that should not match
		int firstPos = rand.nextInt(result.length() / 2);
		int secondPos = result.length() - 1 - firstPos;

		while (result.charAt(firstPos) == result.charAt(secondPos)) {
			char randomChar = getRandomCharacter();
			String tempResult = result.substring(0, secondPos) + randomChar
					+ result.substring(secondPos + 1);
			if (secondPos != result.length() - 1) {
				tempResult += result.substring(secondPos + 1);
			}
			return result = tempResult;
		}
		return result;
	}

	public IsPalindromeTestCase(long timeout) {
		super(new ASimpleMethodMatcher("isPalindrome"), argsAndRetVals.argSets
				.toArray(new Object[0][0]), argsAndRetVals.retVals.toArray(), timeout);
	}

}
