package gradingTools.comp110s15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment2.testcases.PreviousHoursPromptTestCase;
import gradingTools.comp110s15.assignment2.testcases.WelcomePromptTest;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("01/27/2015 23:55:59", 1.0);
		addDueDate("01/28/2015 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Sprint 2015"));
		// Check for a main method
		addFeature("Contains a main method", 20, new MainMethodTestCase());
		//Check for welcome message
		addFeature("Contains welcome message",10,new WelcomePromptTest());
		//Check for up until prompt
		addFeature("Contains up until prompt",10,new PreviousHoursPromptTestCase());
		
	}
}
