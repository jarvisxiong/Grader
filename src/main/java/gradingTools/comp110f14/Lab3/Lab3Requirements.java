package gradingTools.comp110f14.Lab3;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14.Lab3.testcases.DoesRestTestCase;

public class Lab3Requirements extends FrameworkProjectRequirements {
	public Lab3Requirements(){
		addDueDate("09/16/2014 23:55:59", 1.0);//temp
		addDueDate("09/17/2014 23:55:59", 0.5);//temp
	
		//check for main method
				addFeature("Contains a main method", 5, new MainMethodTestCase());
				addFeature("The Rest Works", 5, new DoesRestTestCase());
	}
}
