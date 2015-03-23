package gradingTools.comp110s15.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment4.testcases.BShasvars;
import gradingTools.comp110s15.assignment4.testcases.InputDook;
import gradingTools.comp110s15.assignment4.testcases.InputOther;
import gradingTools.comp110s15.assignment4.testcases.InputUNC;
import gradingTools.comp110s15.assignment4.testcases.ThankUser;
import gradingTools.comp110s15.assignment4.testcases.welcometest;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements() {
		addDueDate("03/03/2015 23:55:59", 1.0);
		addDueDate("03/04/2015 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 10, new MainMethodTestCase());
		//Check for welcome message
		addFeature("Contains welcome message",10, new welcometest());
		//handles UNC input
		addFeature("Handles UNC input",30,new InputUNC());
		//handles Dook input
		addFeature("Handless DOOK input",10,new InputDook());
		//handles other input
		addFeature("Handles other school",20,new InputOther());
		//thanks user
		addFeature("Thanks user",10,new ThankUser());
		//uses vars
		addFeature("uses vars",5,new BShasvars());
	}
}
