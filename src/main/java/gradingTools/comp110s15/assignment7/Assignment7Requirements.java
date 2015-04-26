package gradingTools.comp110s15.assignment7;

import java.util.ArrayList;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment7.testcases.AccountType;
import gradingTools.comp110s15.assignment7.testcases.Amount;
import gradingTools.comp110s15.assignment7.testcases.Optest;
import gradingTools.comp110s15.assignment7.testcases.ThankQuit;
import gradingTools.sharedTestCase.HasMainMethodMultClasses;
import gradingTools.sharedTestCase.Welcome;

public class Assignment7Requirements extends FrameworkProjectRequirements {

	public Assignment7Requirements() {
		addDueDate("04/24/2015 23:55:59", 1.0);
		addDueDate("04/25/2015 23:55:59", 0.5);
		
		// Classes not to check for our methods
				ArrayList<String> mainBad = new ArrayList<String>();
				mainBad.add("BankTransaction");
				ArrayList<String> mainGood = new ArrayList<String>();
				mainGood.add("Bank");
		// Check for Header
		addFeature("Has a proper header", 2.5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
				
		// Check for a main method
		addFeature("Contains a main method", 2.5, new HasMainMethodMultClasses(mainGood));
		//Check for welcome message
				addFeature("Contains welcome message",5, new Welcome());
		//Check for op prompt
				addFeature("Operation Prompt",5,new Optest());
		//check account type
				addFeature("Asks account type",5,new AccountType());
		//ask amount
				addFeature("Asks amount",5,new Amount());
		//thank/quit
				addFeature("quits/thanks user",10,new ThankQuit());
		
	}

}
