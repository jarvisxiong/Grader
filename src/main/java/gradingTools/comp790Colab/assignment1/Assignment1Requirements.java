package gradingTools.comp790Colab.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp790Colab.assignment1.testcases.MultiInputAddAndMultiplyTestCase;
import gradingTools.comp790Colab.assignment1.testcases.MultiInputMainMethodTestCase;
import gradingTools.comp790Colab.assignment1.testcases.MultiInputPrintAddAndMultiplyTestCase;
import gradingTools.comp790Colab.assignment1.testcases.MultiInputPromptTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp790Colab.assignment1.testcases.MultiInputTakeNumberTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Contains a main method", 10, new MultiInputMainMethodTestCase());

		// Checks for prompts
		addFeature("Prompts for inputs", 10, new MultiInputPromptTestCase());

		// Combines Taking in Int and Double together into one test case
		addFeature("Takes in Double and Integer", 10, new MultiInputTakeNumberTestCase());

		// Check for adding and multiplying of numbers
		addFeature("Adds and multiplies the numbers", 10, new MultiInputAddAndMultiplyTestCase());

		// Check for the results of addition and multiplication
		addFeature("Displays results of addition and multiplication", 40,
				new MultiInputPrintAddAndMultiplyTestCase());

		addFeature("Has a proper header", 10, new ProperHeaderTestCase());

		// Check for the messages with the outputs
		// manual feature, not extra credit
//		addManualFeature(true, "Messages with output", 10, false, new ManualTestCase(
//				"Prints clear messages for each of the outputs"));
		addManualFeature("Clear messages with output", 10, false);
//		addFeature("Messages with output", 10,  new ManualTestCase(
//				"Prints clear messages for each of the outputs"));
	}
}
