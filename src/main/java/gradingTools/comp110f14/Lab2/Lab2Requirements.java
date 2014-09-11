package gradingTools.comp110f14.Lab2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14.Lab2.testcases.RestTestCase;

public class Lab2Requirements extends FrameworkProjectRequirements {//Labs out of 10
	public Lab2Requirements(){
		addDueDate("09/16/2014 23:55:59", 1.0);//temp
		addDueDate("09/17/2014 23:55:59", 0.5);//temp
		
		//check for main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		
		//check for rest
		addFeature("Is the rest good?",5,new RestTestCase());
		
		
	}

}
