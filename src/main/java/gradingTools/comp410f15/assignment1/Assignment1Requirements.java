package gradingTools.comp410f15.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410f15.assignment1.testcases.QueueTestCase;
import gradingTools.comp410f15.assignment1.testcases.SpecialStackTestCase;
import gradingTools.comp410f15.assignment1.testcases.StackTestCase;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements() {
		addDueDate("09/16/2014 17:00:00", 1.0);

		// Stack correctly implemented
		addFeature("Correct stack", 15, new StackTestCase());
		
		// Queue correctly implemented
		addFeature("Correct queue ", 15, new QueueTestCase());

		// SpecialStack correctly implemented
		addFeature("Correct SpecialStack", 50, new SpecialStackTestCase());
		
		// Clean code
		addManualFeature("Clean Code", 10, false);
		
		// Use of generics
		addManualFeature("Generics used", 10, false);
	}
}
