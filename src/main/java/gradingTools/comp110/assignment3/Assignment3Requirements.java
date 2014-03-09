package gradingTools.comp110.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment3.testCases.IsPalindromeTestCase;
import gradingTools.comp110.assignment3.testCases.PromptStringTestCase;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("03/25/2014 23:55:59", 1.0);
		addDueDate("03/26/2014 23:55:59", 0.5);

		// Checks for proper header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase());

		// Check for a main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());

		// Checks for prompt of String and Computation
		addFeature("Prompts for String and computation input", 10, new PromptStringTestCase());

		// Has the proper methods
		long methodsTimeout = 3000L; // 3 seconds
		addFeature("Has proper isPalindromeMethod method", 20, new IsPalindromeTestCase(
				IsPalindromeTestCase.getRandomInputs(9, 20), methodsTimeout));
	}
}
