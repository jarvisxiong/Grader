package gradingTools.comp110f15lab.lab2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f15lab.lab2.testcases.ArrayChecker;
import gradingTools.comp110f15lab.lab2.testcases.Average;
import gradingTools.comp110f15lab.lab2.testcases.Both;
import gradingTools.comp110f15lab.lab2.testcases.CorrectVars;
import gradingTools.comp110f15lab.lab2.testcases.NotMultOfThree;
import gradingTools.comp110f15lab.lab2.testcases.Sum;
import gradingTools.comp110f15lab.lab2.testcases.Unrec;
import gradingTools.sharedTestCase.BlankTestCase;
import gradingTools.sharedTestCase.Welcome;



public class Lab2Requirements extends FrameworkProjectRequirements {//Labs out of 20
	public Lab2Requirements(){
		addDueDate("10/21/2015 23:55:59", 1.0);
		addDueDate("10/22/2015 23:55:59", 0.5);
		addFeature("Contains proper Header",1,new ProperHeaderTestCase("Fall 2015"));
		addFeature("Prints welcome (lenient mode)",1, new Welcome());
		addFeature("Correct variable names",2,new CorrectVars());
		addFeature("Uses loop to fill array (can use any kind of loop)",3,new BlankTestCase());
		addFeature("Correctly Populates array w/ multiples of three",5,new ArrayChecker());
		addFeature("Correctly notifies user when they do not enter mult of 3",2,new NotMultOfThree());
		addFeature("Correctly computes sum when prompted to do so",2,new Sum());
		addFeature("Correctly computes average when prompted to do so",2,new Average());
		addFeature("Gives both average and sum when prompted.",1,new Both());
		addFeature("Correct handling of Unrecognized option.",1,new Unrec());
	}

}
