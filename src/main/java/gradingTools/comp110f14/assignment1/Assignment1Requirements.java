package gradingTools.comp110f14.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.program0.HelloWorldClassTestCase;
import gradingTools.comp110.program0.HelloWorldPrinterTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("09/09/2014 23:55:59", 1.0);
		addDueDate("09/10/2014 23:55:59", 0.5);

		// Check for a HelloWorld class
		addFeature("Contains the HelloWorld class", 30, new HelloWorldClassTestCase());

		// Check for a main method
		addFeature("Contains a main method", 30, new MainMethodTestCase());

		// Check that the output is correct
		addFeature("Prints Hello World", 40, new HelloWorldPrinterTestCase());
	}
}
