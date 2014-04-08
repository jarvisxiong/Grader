package gradingTools.comp110.assignment4;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110.assignment4.testCases.averageFoodTestCase;
import gradingTools.comp110.assignment4.testCases.searchForGerbilTestCase;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements(){
		addDueDate("04/11/2014 23:55:59", 1.0);
		addDueDate("04/12/2014 23:55:59", 0.5);
		
		// Checks for proper header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase());

		// Check for a main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		
		//Check for averageFood method
		addFeature("Has proper averageFood method",15,new averageFoodTestCase());
		
		//Check for searchForGerbil method
		addFeature("Has proper searchForGerbil method",15,new searchForGerbilTestCase());
		
		
	}

}
