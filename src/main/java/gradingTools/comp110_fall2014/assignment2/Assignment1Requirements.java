package gradingTools.comp110_fall2014.assignment2;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110_fall2014.assignment2testcases.BalancePrintingTestCase;
import gradingTools.comp110_fall2014.assignment2testcases.BankTransactionClassTestCase;
import gradingTools.comp110_fall2014.assignment2testcases.PromptTestCase;
import gradingTools.comp110_fall2014.assignment2testcases.WelcomeThankYouTest;

public class Assignment1Requirements extends FrameworkProjectRequirements {
	public Assignment1Requirements(){
		addDueDate("09/16/2014 23:55:59", 1.0);
		addDueDate("09/17/2014 23:55:59", 0.5);
		
		// Check for a main method
				addFeature("Contains a main method", 10, new MainMethodTestCase());
		// Check for Header		
				addFeature("Has a proper header", 10, new ProperHeaderTestCase());
		//Check for BankTransaction Classname
				addFeature("Has BankTransaction class",10,new BankTransactionClassTestCase());
		//Check for Welcome and Thank You Message
				addFeature("Has Welcome and Thank You Message",15,new WelcomeThankYouTest());
		//Check for Deposit and Withdrawl Prompts
				addFeature("Has Prompts for Deposit and Withdrawl",20, new PromptTestCase());
		//Check for balance printing
				addFeature("Balance Printings",35,new BalancePrintingTestCase());
	}
} 
