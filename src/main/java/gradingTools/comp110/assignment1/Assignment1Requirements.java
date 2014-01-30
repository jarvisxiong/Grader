package gradingTools.comp110.assignment1;

import framework.grading.FrameworkProjectRequirements;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Contains a main method", 10, new MainMethodTestCase());

		// Checks for prompt for integer
		addFeature("Prompts for an integer", 5, new IntegerPromptTestCase());

		// //Checks if integer was taken in
		addFeature("Prompts for Double", 5, new DoublePromptTestCase());

		// //Combines Taking in Int and Double together into one test case
		// addFeature("Takes in Double and Integer",10, new
		// TakeNumberTestCase());

	}
}
