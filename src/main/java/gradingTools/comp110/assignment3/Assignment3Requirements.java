package gradingTools.comp110.assignment3;

import java.util.Set;
import java.util.TreeSet;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment3.testCases.ComputeRoundedSumTestCase;
import gradingTools.comp110.assignment3.testCases.CorrectOutputTypeOnComputationChoice;
import gradingTools.comp110.assignment3.testCases.CountUniqueCharactersTestCase;
import gradingTools.comp110.assignment3.testCases.InfiniteLoopComputationTestCase;
import gradingTools.comp110.assignment3.testCases.IsPalindromeTestCase;
import gradingTools.comp110.assignment3.testCases.PromptStringTestCase;
import gradingTools.sharedTestCase.MultipleRestrictedStringOutsideComments;
import gradingTools.sharedTestCase.RestrictedStringOutsideComments;

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
		addFeature("Has proper computeRoundedSum method", 20, new ComputeRoundedSumTestCase(
				ComputeRoundedSumTestCase.getRandomInputs(2, 50, 500.0), methodsTimeout));
		addFeature(
				"Has proper countUniqueCharacters method",
				20,
				new CountUniqueCharactersTestCase(CountUniqueCharactersTestCase.getRandomInputs(10,
						30), methodsTimeout));

		// Correct computation based on user selection
		String[] palindromeOutputs = { "true", "false" };
		String[] incorrectPalindromeOutputs = {};
		addFeature("Program runs computation specified by the user", 20,
				new CorrectOutputTypeOnComputationChoice("abcdefg", "check if palindrome",
						palindromeOutputs, incorrectPalindromeOutputs),
				new CorrectOutputTypeOnComputationChoice("0.1 1.2 0.3", "compute rounded sum",
						"[0-9]+([.][0-9]+)?", palindromeOutputs),
				new CorrectOutputTypeOnComputationChoice("abcdefg", "count unique characters",
						"[0-9]+[^.0-9]", palindromeOutputs), new InfiniteLoopComputationTestCase(
						"abcdefg", "check if palindrome", "0.1 1.2 0.3", "compute rounded sum"));

		// Deduct points for using StringBuffer
		addRestriction("No StringBuffer allowed", 30, new RestrictedStringOutsideComments(
				"StringBuffer"));

		// Deduct points for using Map, HashMap, TreeMap, Set, HashSet, or
		// TreeSet
		Set<String> restrictedClasses = new TreeSet<String>();
		restrictedClasses.add("Map");
		restrictedClasses.add("HashMap");
		restrictedClasses.add("TreeMap");
		restrictedClasses.add("Set");
		restrictedClasses.add("HashSet");
		restrictedClasses.add("TreeSet");
		addRestriction("No advanced Java classes allowed", 30,
				new MultipleRestrictedStringOutsideComments(restrictedClasses));

	}
}
