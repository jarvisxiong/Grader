package gradingTools.comp110f15.assignment0;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.program0.HelloWorldClassTestCase;
import gradingTools.comp110.program0.HelloWorldPrinterTestCase;

public class Assignment0Requirements extends FrameworkProjectRequirements {
	public Assignment0Requirements() {
		addDueDate("08/28/2015 23:55:59", 1.0);
		addDueDate("08/29/2015 23:55:59", 0.5);

		// Check for a HelloWorld class
		addFeature("Contains the HelloWorld class", 30, new HelloWorldClassTestCase());

		// Check for a main method
		addFeature("Contains a main method", 30, new MainMethodTestCase());

		// Check that the output is correct
		addFeature("Prints Hello World", 40, new HelloWorldPrinterTestCase());
	}
}
