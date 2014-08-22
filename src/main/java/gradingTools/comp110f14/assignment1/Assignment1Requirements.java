package gradingTools.comp110f14.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp110s14.assignment1.testcases.AddAndMultiplyTestCase;
import gradingTools.comp110s14.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110s14.assignment1.testcases.PrintAddAndMultiplyTestCase;
import gradingTools.comp110s14.assignment1.testcases.PromptTestCase;
import gradingTools.comp110s14.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s14.assignment1.testcases.TakeNumberTestCase;
import gradingTools.comp110s14.program0.HelloWorldClassTestCase;
import gradingTools.comp110s14.program0.HelloWorldPrinterTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("08/27/2014 23:55:59", 1.0);
		addDueDate("08/28/2014 23:55:59", 0.5);

		// Check for a HelloWorld class
		addFeature("Contains the HelloWorld class", 30, new HelloWorldClassTestCase());

		// Check for a main method
		addFeature("Contains a main method", 30, new MainMethodTestCase());

		// Check that the output is correct
		addFeature("Prints Hello World", 40, new HelloWorldPrinterTestCase());
	}
}
