package gradingTools.comp790Colab.example1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp110.assignment1.testcases.AddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.PrintAddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment1.testcases.TakeNumberTestCase;
import gradingTools.comp110Inc.assignment1.testcases.IncrementalInputPromptTestCase;
import gradingTools.comp790Colab.example1.testcases.IMTestCase;

public class Example1Requirements extends FrameworkProjectRequirements {
	public Example1Requirements() {
		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Test IM", 10, new IMTestCase());

	
	}
}
