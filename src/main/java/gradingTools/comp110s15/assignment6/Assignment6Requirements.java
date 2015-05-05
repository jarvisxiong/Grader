package gradingTools.comp110s15.assignment6;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment6.testcases.DepositAmountPrompt;
import gradingTools.comp110s15.assignment6.testcases.OverdraftFee;
import gradingTools.comp110s15.assignment6.testcases.PrintCheck;
import gradingTools.comp110s15.assignment6.testcases.RespondNo;
import gradingTools.comp110s15.assignment6.testcases.RespondYes;
import gradingTools.comp110s15.assignment6.testcases.WithdrawAmountPrompt;
import gradingTools.comp110s15.assignment6.testcases.invalidinput;
import gradingTools.comp110s15.assignment6.testcases.thank;
import gradingTools.sharedTestCase.BlankTestCase;
import gradingTools.sharedTestCase.Welcome;

public class Assignment6Requirements extends FrameworkProjectRequirements {
	public Assignment6Requirements() {
		addDueDate("04/14/2015 23:55:59", 1.0);
		addDueDate("04/15/2015 23:55:59", 0.5);
		// Check for Header
		addFeature("Has a proper header", 2.5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 2.5, new MainMethodTestCase());
		//Check for welcome message
		addFeature("Contains welcome message",5, new Welcome());
		//Check deposit prompt
		addFeature("Deposit Prompt",5,new DepositAmountPrompt());
		//Check Withdraw Prompt
		addFeature("Widthdraw Prompt",5,new WithdrawAmountPrompt());
		//Check overdraft
		addFeature("Detects negative balance and applies penalty",10,new OverdraftFee());
		//Check print
		addFeature("prints balance,three transactions, avg depo and width",20,new PrintCheck());
		//Check yes
		addFeature("program loops when responding yes",10,new RespondYes());
		//Check no
		addFeature("program exits when responding no",10,new RespondNo());
		//Check invalid
		addFeature("invalid input resopnse",10,new invalidinput());
		//Check thank
		addFeature("thanks user",5,new thank());
		//Check array
		addFeature("uses array",15,new BlankTestCase());
		
	}

}
