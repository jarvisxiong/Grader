package gradingTools.comp401f16.assignment1.testcases;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Explanation;
import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.assignment1.FlexibleProgramRunner;
@Explanation("Multiple Tokens")
public class MultipleTokensOutputTest extends AbstractScanningTest {
	protected String[][] tokenLines = {{"22", "44", "66"}, {"11" , "33", "55"}};
	
	
	@Override
	protected String[] expectedOutputs() {
		return expectedTokenOutputs();
		
	}  

	
//    
//    @Override
//    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//        try {
//            FlexibleProgramRunner runner = new FlexibleProgramRunner(project, "22 44 66\n11 33 55");
//
//            // Check that the program
//            String output = runner.runWithSpaces();
//            if (isValidOutput(output))
//                return pass(autoGrade);
//            output = runner.runNoSpaces();
//            if (isValidOutput(output))
//                return pass(autoGrade);
//            return fail("Program should work with multiple token and multiple lines.", autoGrade);
//
//        } catch (NotRunnableException e) {
//            throw new NotGradableException();
//        }
//    }
//
//    private String[] outputParts = new String[] {"22", "44", "66", "11", "33", "55"};
//
//    private boolean isValidOutput(String output) {
//        boolean result = true;
//        for (String part : outputParts)
//            result = result && output.contains(part);
//        return result;
//    }
	public static void main (String[] args) {
		String aTestOutput = "The tokens are:22\n44\n66\n11\n33\n55\n Sum: 231 \n Product: 1275523920";
		String aTest1Output = "The tokens are:22\n44\n66\n1i\n33\n55\n Sum: 23 \n Product: 127552392";

		MultipleTokensOutputTest aTestCase = new MultipleTokensOutputTest();
		String anInputWithNoSpace = aTestCase.inputWithNoEndingSpace();
		String anInputWithSpace = aTestCase.inputWithEndingSpace();
		String[] anExpectedOutputs = aTestCase.expectedOutputs();
		String[] anExpectedTokens = aTestCase.expectedTokenOutputs();
		String[] anExpectedSumProduct = aTestCase.expectedSumProductOutputs();
		String[] anExpectedSum = aTestCase.expectedSumOutput();
		String[] anExpectedProduct = aTestCase.expectedProductOutput();
		boolean aCorectTokens = aTestCase.isValidOutput(aTestOutput, anExpectedTokens);
		boolean aCorrectSumProduct = aTestCase.isValidOutput(aTestOutput, anExpectedSumProduct);
		boolean aCorrectSum = aTestCase.isValidOutput(aTestOutput, anExpectedSum);
		boolean aCorrectProduct = aTestCase.isValidOutput(aTest1Output, anExpectedProduct);
		boolean aCorectTokens1 = aTestCase.isValidOutput(aTest1Output, anExpectedTokens);
		boolean aCorrectSumProduct1 = aTestCase.isValidOutput(aTest1Output, anExpectedSumProduct);
		boolean aCorrectSum1 = aTestCase.isValidOutput(aTest1Output, anExpectedSum);
		boolean aCorrectProduct1 = aTestCase.isValidOutput(aTest1Output, anExpectedProduct);


		
		
		
	}
}

