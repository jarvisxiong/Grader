package gradingTools.comp110f14lab.lab4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14lab.lab4.testcases.CorrectFirstFactorialComputation;
import gradingTools.comp110f14lab.lab4.testcases.CorrectSecondFactorialComputation;
import gradingTools.comp110f14lab.lab4.testcases.HasForLoop;
import gradingTools.comp110f14lab.lab4.testcases.HasWhileLoop;

public class Lab4Requirements extends FrameworkProjectRequirements {
	public Lab4Requirements(){
		addDueDate("10/06/2014 23:55:59", 1.0);
		addDueDate("10/07/2014 23:55:59", 0.5);
	
		//check for main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		addFeature("The Rest Works", 5, new HasForLoop(), new HasWhileLoop(),
				new CorrectFirstFactorialComputation(), new CorrectSecondFactorialComputation());
	}
}