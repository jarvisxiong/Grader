package gradingTools.comp110.assignment3.testCases;

import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.ArgsAndReturnValues;
import gradingTools.sharedTestCase.MethodFinderAndRunner;

import java.util.Random;

public class ComputeRoundedSumTestCase extends MethodFinderAndRunner {

	private static Random rand = new Random();

	public static ArgsAndReturnValues getRandomInputs(int minNums, int maxNums, double maxVal) {

		int totalNums = rand.nextInt(maxNums + 1 - minNums) + minNums;

		String inputStr = "";
		double sum = 0.0;

		for (int i = 0; i < totalNums; i++) {

			double num = rand.nextDouble() * maxVal;
			if (inputStr.length() > 0) {
				inputStr += " ";
			}
			inputStr += num;
			sum += num;
		}

		ArgsAndReturnValues toReturn = new ArgsAndReturnValues();
		Object[] args = new Object[1];
		args[0] = inputStr;
		double roundedSum = Math.round(sum);
		toReturn.addArgsAndReturnValues(args, roundedSum);

		return toReturn;
	}

	public ComputeRoundedSumTestCase(ArgsAndReturnValues argsAndRetVals, long timeout) {
		super(new ASimpleMethodMatcher("computeRoundedSum"), argsAndRetVals.getArgumentSets(),
				argsAndRetVals.getReturnValues(), timeout);
	}

}
