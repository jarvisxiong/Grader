package gradingTools.comp110s15.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp110.assignment1.testcases.AddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.PrintAddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment1.testcases.TakeNumberTestCase;
import gradingTools.comp110.program0.HelloWorldClassTestCase;
import gradingTools.comp110.program0.HelloWorldPrinterTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("01/20/2015 23:55:59", 1.0);
		addDueDate("01/21/2015 23:55:59", 0.5);

		// Check for a HelloWorld class
		addFeature("Contains the HelloWorld class", 30, new HelloWorldClassTestCase());

		// Check for a main method
		addFeature("Contains a main method", 30, new MainMethodTestCase());

		// Check that the output is correct
		addFeature("Prints Hello World", 40, new HelloWorldPrinterTestCase());
	}
}
