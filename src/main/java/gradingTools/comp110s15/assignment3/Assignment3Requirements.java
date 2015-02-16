package gradingTools.comp110s15.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("02/13/2015 23:55:59", 1.0);
		addDueDate("02/14/2015 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 20, new MainMethodTestCase());
		//Check for welcome message
		
	}
}
