package gradingTools.comp790Colab.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp790Colab.assignment1.testcases.CollaborativeInputAddAndMultiplyTestCase;
import gradingTools.comp790Colab.assignment1.testcases.CollaborativeInputMainMethodTestCase;
import gradingTools.comp790Colab.assignment1.testcases.CollaborativeInputPrintAddAndMultiplyTestCase;
import gradingTools.comp790Colab.assignment1.testcases.CollaborativeInputPromptTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp790Colab.assignment1.testcases.CollaborativeiInputTakeNumberTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Contains a main method", 10, new CollaborativeInputMainMethodTestCase());

		// Checks for prompts
		addFeature("Prompts for inputs", 10, new CollaborativeInputPromptTestCase());

		// Combines Taking in Int and Double together into one test case
		addFeature("Takes in Double and Integer", 10, new CollaborativeiInputTakeNumberTestCase());

		// Check for adding and multiplying of numbers
		addFeature("Adds and multiplies the numbers", 10, new CollaborativeInputAddAndMultiplyTestCase());

		// Check for the results of addition and multiplication
		addFeature("Displays results of addition and multiplication", 40,
				new CollaborativeInputPrintAddAndMultiplyTestCase());

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
