package gradingTools.comp110f14.assignment6;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment4testcases.CapitalizationStyle;
import gradingTools.comp110f14.assignment6testcases.VariableTest;

public class Assignment6Requirements extends FrameworkProjectRequirements {
	
	public Assignment6Requirements(){
		addDueDate("12/03/2014 23:55:59", 1.0);
		addDueDate("12/04/2014 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		//Check for Capitalization
		addFeature("Naming Style",5,new CapitalizationStyle());
		//Check variables
		addFeature("Contains desired variables in KitKatFactory",10,new VariableTest());
		
	}
}
