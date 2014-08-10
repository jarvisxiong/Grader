package gradingTools.comp790ColabInc.example1;

import im.AnIMInputGenerator;
import im.IMInputGenerator;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp110.assignment1.testcases.AddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.PrintAddAndMultiplyTestCase;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment1.testcases.TakeNumberTestCase;
import gradingTools.comp110Inc.assignment1.testcases.IncrementalInputPromptTestCase;
import gradingTools.comp790ColabInc.example1.testcases.ConsistentOutputsTestCase;
import gradingTools.comp790ColabInc.example1.testcases.InitiatingJoinTestCase;
import gradingTools.comp790ColabInc.example1.testcases.RemoteEditsTestCase;

public class Example1Requirements extends FrameworkProjectRequirements {
	public Example1Requirements() {
		IMInputGenerator anOutputBasedInputGenerator = new AnIMInputGenerator();

		addDueDate("02/4/2014 23:55:59", 1.0);
		addDueDate("02/5/2014 23:55:59", 0.5);

		// Check for a main method
		addFeature("Threee Clients Join Server", 10, new InitiatingJoinTestCase(anOutputBasedInputGenerator));
		addFeature("The Clients Exchange Edits", 30, new RemoteEditsTestCase(anOutputBasedInputGenerator));
		addFeature("The Clients Have Consistent Histories", 60, new ConsistentOutputsTestCase(anOutputBasedInputGenerator));

	
	}
}
