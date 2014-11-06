package gradingTools.comp110.assignment3;

import java.util.ArrayList;
import java.util.List;
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
import gradingTools.comp110.assignment3.testCases.ReverseWordCharacters;
import gradingTools.sharedTestCase.MultipleRestrictedValsOutsideComments;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("03/25/2014 23:55:59", 1.0);
		addDueDate("03/26/2014 23:55:59", 0.5);

		// Checks for proper header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-002, Spring 2014"));

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

		// Deduct points for using StringBuffer or StringBuilder
		Set<String> restrictedStringClasses = new TreeSet<String>();
		restrictedStringClasses.add("StringBuffer");
		restrictedStringClasses.add("StringBuilder");
		addRestriction("No StringBuffer or StringBuilder allowed", 30,
				new MultipleRestrictedValsOutsideComments(restrictedStringClasses,
						new ArrayList<String>(), new ArrayList<String>()));

		// Deduct points for using Map, HashMap, TreeMap, Set, HashSet, or
		// TreeSet, or other advanced javaClasses
		Set<String> restrictedClasses = new TreeSet<String>();
		restrictedClasses.add("Map");
		restrictedClasses.add("HashMap");
		restrictedClasses.add("TreeMap");
		restrictedClasses.add("Set");
		restrictedClasses.add("HashSet");
		restrictedClasses.add("TreeSet");
		List<String> restrictedRegexes = new ArrayList<String>();
		List<String> regexLabels = new ArrayList<String>();
		restrictedRegexes.add("java[.]util[.](?!Scanner)");
		regexLabels.add("advanced java classes");
		addRestriction("No advanced Java classes allowed", 30,
				new MultipleRestrictedValsOutsideComments(restrictedClasses, restrictedRegexes,
						regexLabels));

		String reverseWordInput = "Computer programming can be a difficult task or it can be as easy as 123";
		String[] reverseWordOutputs = { "retupmoC gnimmargorp nac eb a tluciffid ksat ro ti nac eb sa ysae sa 321" };
		String[] incorrectReverseWordOutputs = {};
		addFeature("EXTRA CREDIT: reverseWordCharacters", 20, true, new ReverseWordCharacters(
				ReverseWordCharacters.getRandomInputs(10, 30, 10), methodsTimeout),
				new CorrectOutputTypeOnComputationChoice(reverseWordInput,
						"reverse word characters", reverseWordOutputs, incorrectReverseWordOutputs));
	}
}
