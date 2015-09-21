package gradingTools.comp110f15.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.program0.HelloWorldClassTestCase;
import gradingTools.comp110.program0.HelloWorldPrinterTestCase;
import gradingTools.comp110f15.assignment1.testcases.GameResult;
import gradingTools.comp110f15.assignment1.testcases.PromptTest;
import gradingTools.comp110f15.assignment1.testcases.VarsA1;
import gradingTools.comp110f15.assignment1.testcases.WelcometTest;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("09/22/2015 23:55:59", 1.0);
		addDueDate("09/23/2015 23:55:59", 0.5);
		// Check for a main method
		addFeature("Contains a main method",15, new MainMethodTestCase());
		addFeature("Contains proper Header",5,new ProperHeaderTestCase("Fall 2015"));
		addFeature("Contains proper Welcome Message",5,new WelcometTest());
		addFeature("Contains proper prompts",20,new PromptTest());
		addFeature("Contains correct variables",10,new VarsA1());
		addFeature("Correctly handles all Game Results and Prints correct information",45,new GameResult());
	}
}
