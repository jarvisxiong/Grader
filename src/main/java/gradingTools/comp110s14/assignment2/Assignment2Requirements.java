package gradingTools.comp110s14.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110s14.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110s14.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s14.assignment2.testcases.ErrorTestCase;
import gradingTools.comp110s14.assignment2.testcases.PalindromeIgnoreCaseTestCase;
import gradingTools.comp110s14.assignment2.testcases.PalindromeTestCase;
import gradingTools.comp110s14.assignment2.testcases.PromptTestCase;
import gradingTools.comp110s14.assignment2.testcases.TakeStingTestCase;
import gradingTools.sharedTestCase.RestrictedStringOutsideComments;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("02/21/2014 23:55:59", 1.0);
		addDueDate("02/22/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Contains a main method", 10, new MainMethodTestCase());

		// Checks for proper header
		addFeature("Has a proper header", 10, new ProperHeaderTestCase());

		// Checks for prompts
		addFeature("Prompts for inputs", 10, new PromptTestCase());

		// Check that it accepts String input
		addFeature("Takes in a String", 10, new TakeStingTestCase());

		// Check for error message with incorrect input
		addFeature("Prints an error message", 10, new ErrorTestCase());

		// Check that palindromes are properly checked
		addFeature("Correctly checks for palindrome", 40, new PalindromeTestCase());

		// Check that it ignores case when checking palindromes
		addFeature("Ignores case when checking for palindromes", 10,
				new PalindromeIgnoreCaseTestCase());

		// Deduct points for using StringBuffer
		addRestriction("No StringBuffer allowed", 30, new RestrictedStringOutsideComments(
				"StringBuffer"));

	}
}
