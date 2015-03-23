package gradingTools.comp110f14lab.lab3;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;

public class Lab3Requirements extends FrameworkProjectRequirements {
	public Lab3Requirements(){
		addDueDate("09/25/2014 23:55:59", 1.0);//temp
		addDueDate("09/26/2014 23:55:59", 0.5);//temp
	
		//check for main method
				addFeature("Contains a main method", 5, new MainMethodTestCase());
				addFeature("The Rest Works", 5, new gradingTools.comp110f14lab.lab3.testcases.DoesRestTestCase());
	}
}
