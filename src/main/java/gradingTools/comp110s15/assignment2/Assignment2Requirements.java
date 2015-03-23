package gradingTools.comp110s15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment2.testcases.CalculateRemainingHours;
import gradingTools.comp110s15.assignment2.testcases.CalculateTotalHours;
import gradingTools.comp110s15.assignment2.testcases.CurrentHoursPromptTestCase;
import gradingTools.comp110s15.assignment2.testcases.HasVar;
import gradingTools.comp110s15.assignment2.testcases.PreviousHoursPromptTestCase;
import gradingTools.comp110s15.assignment2.testcases.PrintHoursLeft;
import gradingTools.comp110s15.assignment2.testcases.PrintTotalHours;
import gradingTools.comp110s15.assignment2.testcases.WelcomePromptTest;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("02/04/2015 23:55:59", 1.0);
		addDueDate("02/05/2015 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 20, new MainMethodTestCase());
		//Check for welcome message
		addFeature("Contains welcome message",10,new WelcomePromptTest());
		//Check for up until prompt
		addFeature("Contains up until prompt",10,new PreviousHoursPromptTestCase());
		//Check for current semester prompt
		addFeature("Contains current enrolled hours prompt",10,new CurrentHoursPromptTestCase());
		//Check for total hours print
		addFeature("Contains printout for total hours",10,new PrintTotalHours());
		//Check for hours left print
		addFeature("Contains printout for hours left",10,new PrintHoursLeft());
		//Check correct total hours
		addFeature("Correct total hours calculation",10,new CalculateTotalHours());
		//Check for correct remaining hours
		addFeature("Correct hours remaining",10,new CalculateRemainingHours());
		//Check for hoursRequired variable
		addFeature("Uses hoursRequired variable",5,new HasVar());
	}
}
