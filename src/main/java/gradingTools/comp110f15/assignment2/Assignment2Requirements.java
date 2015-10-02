package gradingTools.comp110f15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.program0.HelloWorldClassTestCase;
import gradingTools.comp110.program0.HelloWorldPrinterTestCase;
import gradingTools.comp110f15.assignment1.testcases.GameResult;
import gradingTools.comp110f15.assignment1.testcases.PromptTest;
import gradingTools.comp110f15.assignment1.testcases.VarsA1;
import gradingTools.comp110f15.assignment1.testcases.WelcometTest;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("09/22/2015 23:55:59", 1.0);
		addDueDate("09/23/2015 23:55:59", 0.5);
		// Check for a main method
		addFeature("Contains a main method",15, new MainMethodTestCase());
		addFeature("Contains proper Header",5,new ProperHeaderTestCase("Fall 2015"));
		
	}
}
