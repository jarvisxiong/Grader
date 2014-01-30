package gradingTools.comp110.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110.assignment1.testcases.TakeNumberTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Contains a main method", 10, new MainMethodTestCase());

		// Checks for prompts
		addFeature("Prompts for inputs", 10, new PromptTestCase());

		// //Combines Taking in Int and Double together into one test case
		addFeature("Takes in Double and Integer", 10, new TakeNumberTestCase());

	}
}
