package gradingTools.comp110f14.assignment3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment3testcases.NegativeBalanceTest;
import gradingTools.comp110f14.assignment3testcases.OpenAccountTest;
import gradingTools.comp110f14.assignment3testcases.SecondTransactionPromptTest;
import gradingTools.comp110f14.assignment3testcases.SharedOutput;
import gradingTools.comp110f14.assignment3testcases.ThankYouTest;
import gradingTools.comp110f14.assignment3testcases.TransactionPromptTest;
import gradingTools.comp110f14.assignment3testcases.TransactionResultTest;
import gradingTools.comp110f14.assignment3testcases.WelcomeTest;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements(){
		addDueDate("10/02/2014 23:55:59", 1.0);
		addDueDate("10/03/2014 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		
		int minValue = 30;
		int maxAmount = 1000000;
		Random rand = new Random();
		int firstDeposit = rand.nextInt(maxAmount-minValue)+minValue;
		int firstWithdawal = rand.nextInt(maxAmount-minValue)+minValue;
		int secondWithdrawl1 = rand.nextInt(firstDeposit);
		int secondWithdrawl2 = rand.nextInt(maxAmount-(firstDeposit+1))+firstDeposit+1;
		
		//For sharing output of initial prompt
		SharedOutput initialPromptSharer = new SharedOutput("", 1);
		//For sharing output when no account is opened
		SharedOutput noAccountSharer = new SharedOutput("no", 1);
		//For sharing prompt for type of transaction
		SharedOutput transactionTypePromptSharer = new SharedOutput("yes", 1);
		//For output for transactions
		SharedOutput firstTransactionDeposit = new SharedOutput("yes\nDeposit\n"+firstDeposit, 1);
		SharedOutput firstTransactionWithdrawal = new SharedOutput("yes\nWithdrawal\n"+firstWithdawal, 1);
		SharedOutput secondTransactionWithdrawal1 = new SharedOutput("yes\nDeposit\n"+firstDeposit+"\nWithdrawal\n"+secondWithdrawl1+"\n", 1);
		SharedOutput secondTransactionWithdrawal2 = new SharedOutput("yes\nDeposit\n"+firstDeposit+"\nWithdrawal\n"+secondWithdrawl2+"\n", 1);
		
		//Check for Welcome Message
		addFeature("Has Welcome Message",5,new WelcomeTest(initialPromptSharer));
		//Check for Account prompt
		addFeature("Prompt for opening account",10,new OpenAccountTest(initialPromptSharer));
		//Check for type of transaction prompt
		addFeature("Prompt for first transaction",10,new TransactionPromptTest(transactionTypePromptSharer));
		
		//Check for result of first transaction
		Map<SharedOutput, Integer> firstTransactionOutputs = new HashMap<>();
		firstTransactionOutputs.put(firstTransactionDeposit, firstDeposit);
		firstTransactionOutputs.put(firstTransactionWithdrawal, -1*firstWithdawal);
		addFeature("Print balance for first transaction", 20, new TransactionResultTest(firstTransactionOutputs));
		
		//Check for prompt of second transaction
		addFeature("Prompt for second transaction", 10, new SecondTransactionPromptTest(initialPromptSharer, firstTransactionDeposit));
		
		//Check for result of second transaction
		Map<SharedOutput, Integer> secondTransactionOutputs = new HashMap<>();
		secondTransactionOutputs.put(secondTransactionWithdrawal1, firstDeposit - secondWithdrawl1);
		secondTransactionOutputs.put(secondTransactionWithdrawal2, firstDeposit - secondWithdrawl2);
		addFeature("Print balance for second transaction", 20, new TransactionResultTest(secondTransactionOutputs));
		
		//Check for detection of overdraft
		Map<SharedOutput, Integer> overdrafts = new HashMap<>();
		//overdrafts.put(firstTransactionWithdrawal, -1*firstWithdawal);
		overdrafts.put(secondTransactionWithdrawal2, firstDeposit - secondWithdrawl2);
		Map<SharedOutput, Integer> nonOverdrafts = new HashMap<>();
		nonOverdrafts.put(firstTransactionDeposit, firstDeposit);
		nonOverdrafts.put(secondTransactionWithdrawal1, firstDeposit - secondWithdrawl1);
		addFeature("Detects negative balance and assigns overdraft fee", 15, new NegativeBalanceTest(overdrafts, nonOverdrafts, 20));
		
		//Check for thank you message
		Collection<SharedOutput> twoTransactionOutputs = new ArrayList<>();
		twoTransactionOutputs.add(secondTransactionWithdrawal1);
		twoTransactionOutputs.add(secondTransactionWithdrawal2);
		addFeature("Thank user for visiting the bank", 5, new ThankYouTest(initialPromptSharer, noAccountSharer, twoTransactionOutputs));
		
	}
} 
