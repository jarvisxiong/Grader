package gradingTools.comp110f15lab.lab1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f15lab.lab1.testcases.CorrectFormat;
import gradingTools.comp110f15lab.lab1.testcases.TheMath;
import gradingTools.comp110f15lab.lab1.testcases.Vars;

public class Lab1Requirements extends FrameworkProjectRequirements {//Labs out of 20
	public Lab1Requirements(){
		addDueDate("09/09/2015 23:55:59", 1.0);
		addDueDate("09/10/2015 23:55:59", 0.5);
		
		//check for main method
		addFeature("Contains proper Header",3,new ProperHeaderTestCase("Fall 2015"));
		addFeature("Contains a main method", 2, new MainMethodTestCase());
		addFeature("Correctly named variables",2,new Vars());
		addFeature("Correct computations",8,new TheMath());
		addFeature("Correct output format",5,new CorrectFormat());
	}

}
