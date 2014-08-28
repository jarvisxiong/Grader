package gradingTools.comp110.assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment4.testCases.TestAverageOutput;
import gradingTools.comp110.assignment4.testCases.TestFoodClass;
import gradingTools.comp110.assignment4.testCases.TestQuit;
import gradingTools.comp110.assignment4.testCases.TestRestart;
import gradingTools.comp110.assignment4.testCases.TestSearchOutput;
import gradingTools.sharedTestCase.HasMainMethodMultClasses;
import gradingTools.sharedTestCase.HasMethodTestCase;
import gradingTools.sharedTestCase.MultipleRestrictedValsOutsideComments;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements() {
		addDueDate("04/18/2014 23:55:59", 1.0);
		addDueDate("04/19/2014 23:55:59", 0.5);

		// Checks for proper header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase());

		// Classes not to check for our methods
		ArrayList<String> badClasses = new ArrayList<String>();
		badClasses.add("gerbil");
		badClasses.add("food");

		// Check for a main method
		addFeature("Contains a main method", 5, new HasMainMethodMultClasses(badClasses));

		// Check for averageFood method
		addFeature("Has proper averageFood method", 15, new HasMethodTestCase("averageFood",
				badClasses, String.class));

		// Check for searchForGerbil method
		Class<?>[] searchForGerbilParameterTypes = { String.class };
		addFeature("Has proper searchForGerbil method", 15, new HasMethodTestCase(
				"searchForGerbil", badClasses, "gerbil", searchForGerbilParameterTypes));
		
		// Check result of user commands
		addFeature("Correct output for user typing average", 20, new TestAverageOutput());
		addFeature("Correct output for user typing search", 20, new TestSearchOutput());
		addFeature("Correct output for user typing restart", 10, new TestRestart());
		addFeature("Correct output for user typing quit", 10, new TestQuit());
		
		// Deduct points for using advanced javaClasses
		Set<String> restrictedClasses = new TreeSet<String>();
		List<String> restrictedRegexes = new ArrayList<String>();
		List<String> regexLabels = new ArrayList<String>();
		restrictedRegexes.add("java[.]util[.](?!Scanner)");
		regexLabels.add("advanced java classes");
		addRestriction("No advanced Java classes allowed", 30,
				new MultipleRestrictedValsOutsideComments(restrictedClasses, restrictedRegexes,
						regexLabels));
		
		// Extra credit
		addFeature("EXTRA CREDIT: Food Class", 20, new TestFoodClass());;
	}

}
