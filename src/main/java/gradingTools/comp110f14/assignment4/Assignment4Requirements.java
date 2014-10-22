package gradingTools.comp110f14.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements(){
		addDueDate("10/02/2014 23:55:59", 1.0);//temp
		addDueDate("10/03/2014 23:55:59", 0.5);//temp
		

		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		
	}
}
