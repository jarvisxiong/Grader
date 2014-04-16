package gradingTools.comp110.assignment4;

import java.util.ArrayList;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment4.testCases.HasMainMethodMultClasses;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements() {
		addDueDate("04/16/2014 23:55:59", 1.0);
		addDueDate("04/17/2014 23:55:59", 0.5);

		// Checks for proper header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase());

		// Check for a main method
		ArrayList<String> badClasses = new ArrayList<String>();
		badClasses.add("gerbil");
		badClasses.add("food");
		addFeature("Contains a main method", 5, new HasMainMethodMultClasses(badClasses));

		// Classes not to check for our methods
		/*
		 * ArrayList<String> badClasses = new ArrayList<String>();
		 * badClasses.add("gerbil"); badClasses.add("food");
		 * 
		 * // Check for averageFood method
		 * addFeature("Has proper averageFood method", 15, new
		 * HasMethodTestCase("averageFood", badClasses, String.class));
		 * 
		 * // Check for searchForGerbil method Class<?>[]
		 * searchForGerbilParameterTypes = { String.class };
		 * addFeature("Has proper searchForGerbil method", 15, new
		 * HasMethodTestCase( "searchForGerbil", badClasses, String.class,
		 * searchForGerbilParameterTypes));
		 */}

}
