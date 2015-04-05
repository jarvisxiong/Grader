package gradingTools.comp110s15.assignment5;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment4.testcases.BShasvars;
import gradingTools.comp110s15.assignment5.testcases.addtorialTest;
import gradingTools.comp110s15.assignment5.testcases.printTest;
import gradingTools.sharedTestCase.BlankTestCase;

public class Assignment5Requirements extends FrameworkProjectRequirements {
	public Assignment5Requirements() {
		addDueDate("03/06/2015 23:55:59", 1.0);
		addDueDate("03/07/2015 23:55:59", 0.5);
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 10, new MainMethodTestCase());
		//printing tests
		addFeature("Prints the while loop and dowhile loop thrice",30,new printTest());
		//uses vars
		addFeature("uses vars",5,new BShasvars());
		//addtorial
		addFeature("correct addtorial of given number",20,new addtorialTest());
		//loops
		addFeature("uses while loop",10,new BlankTestCase());
		addFeature("uses do-while loop",10,new BlankTestCase());
		addFeature("uses for loop",10,new BlankTestCase());
	}
}
