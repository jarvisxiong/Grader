package gradingTools.comp110.assignment4.testCases;

import framework.grading.testing.TestCaseResult;

public class TestAverageOutput extends TestGerbilInputWithCommand {

	public TestAverageOutput() {
		super("average");
	}
	
	String[] correctResults = {
		"SB1X3 (Prince Firstly) 67%",
		"SB2 (Malcolm) 32%",
		"SB329 (Hannibal) 57%",
	};
	
	String[] wrongRoundingResults = {
		"SB1X3 (Prince Firstly) 67%",
		"SB2 (Malcolm) 32%",
		"SB329 (Hannibal) 56%",
	};

	private String getAllCorrectResult() {
		String retVal = "";
		for (String result : correctResults) {
			if (retVal.length() > 0) {
				retVal += "\n";
			}
			retVal += result;
		}
		return retVal;
	}

	@Override
	protected TestCaseResult checkOutputString(String result) {
		result = result.trim();
		if (result.startsWith(getAllCorrectResult())) {
			return pass();
		}
		
		boolean correctVals = true;
		boolean correctOrder = true;
		boolean correctRounding = true;
		String testResult = result;
		for (int i=0; i<correctResults.length; i++) {
			if (testResult.startsWith(correctResults[i])) {
				testResult = testResult.substring(correctResults[i].length()).trim();
			} else if (testResult.startsWith(wrongRoundingResults[i])) {
				testResult = testResult.substring(wrongRoundingResults[i].length()).trim();
				correctRounding = false;
			} else {
				correctOrder = false;
				break;
			}
		}
		
		if(!correctOrder) {
			for (int i=0; i<correctResults.length; i++) {
				if (testResult.contains(correctResults[i])) {
					continue;
				} else if (testResult.contains(wrongRoundingResults[i])) {
					correctRounding = false;
				} else {
					correctVals = false;
					break;
				}
			}
		}
		
		if (!correctVals) {
			return fail("Did not print the correct values for average command");
		}
		
		double score = 1.0;
		String message = "";
		
		if (!correctOrder) {
			score -= 0.25;
			message += "Average values not in alphabetic order (-5)";
		}
		if (!correctRounding) {
			score -= 0.25;
			if (message.length() > 0) {
				message += "\n--";
			}
			message += "Average values not properly rounded (-5)";
		}
		return partialPass(score, message);
	}

}
