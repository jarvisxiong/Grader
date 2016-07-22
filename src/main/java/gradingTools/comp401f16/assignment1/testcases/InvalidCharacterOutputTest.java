package gradingTools.comp401f16.assignment1.testcases;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Explanation;
import util.annotations.IsExtra;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.NotesAndScore;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp401f15.assignment1.testcases.InvalidCharacterTestCase;
import gradingTools.shared.testcases.OutputAndErrorCheckingTestCase;

@Explanation("Invalid Character Tokens")
@IsExtra(true)
public class InvalidCharacterOutputTest extends MultipleTokensOutputTest {
//	protected String[][] tokenLines = {{"2", "20"}, {"40" , "50"}};
	protected String[][] postTokenStrings = {{"two", "!@", ",;"}, {"^&", "!@@", "\"", " the end"}};


//    String inputWithNoEndingSpace = "10 ten 20\n40 forty 50!\n.";

//    String inputWithEndingSpace = "10 ten 20\n40 forty 50 ! \n.";

//    String[] expectedOutputs = {"(.*)30(.*)", "(.*)200(.*)", "(.*)90(.*)", "(.*)2000(.*)"};
	@Override
	protected String postTokenString(String[][] aTokenLines, String[] aLine, int aLineNumber, int aTokenNumber) {

		return super.postTokenString(aTokenLines, aLine, aLineNumber, aTokenNumber) + postTokenStrings[aLineNumber][aTokenNumber];
	}
	
    
    @Override
    protected String[] expectedOutputs() {
    	return expectedTokenOutputs();
    }
//    public static void main (String[] args) {
//		String aTestOutput = "The tokens are:2\n20\nSum: 22\n Product: 40\n 40\n 50\n Sum: 90 \n Product: 2000";
//		String aTest1Output = "The tokens are:11\n20\nSum: 22\n Product: 40\n 40\n 50\n Sum: 90 \n Product: 2000";
//
//		
//		InvalidCharacterOutputTest aTestCase = new InvalidCharacterOutputTest();
//		test(aTestCase, aTestOutput);
//		test (aTestCase, aTest1Output);
//    }
    public static void main (String[] args) {
		String aTestOutput = "The tokens are:22\n44\n66\n\nSum: 132\n Product: 63888\n 11\n33\n55\n Sum: 99 \n Product: 19965";
		String aTest1Output = "The tokens are:22\n44\n66\n1i\n33\n55\n Sum: 231 \n Product: 1275523920";
		String aTest2Output = "The tokens are:22 44 66 11 33 55";
		
		InvalidCharacterOutputTest aTestCase = new InvalidCharacterOutputTest();
		test(aTestCase, aTestOutput);
		test (aTestCase, aTest1Output);
		test (aTestCase, aTest2Output);
    }
    
    
	
}

