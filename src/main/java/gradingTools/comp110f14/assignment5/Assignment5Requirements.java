package gradingTools.comp110f14.assignment5;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment4testcases.CapitalizationStyle;


public class Assignment5Requirements extends FrameworkProjectRequirements {
	public Assignment5Requirements(){
		addDueDate("11/21/2014 23:55:59", 1.0);
		addDueDate("11/22/2014 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		//Check for Capitalization
		addFeature("Naming Style",5,new CapitalizationStyle());
		//Initial Prompt
		
	}
}
