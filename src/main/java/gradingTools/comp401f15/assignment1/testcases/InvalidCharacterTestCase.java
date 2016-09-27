package gradingTools.comp401f15.assignment1.testcases;

import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.sharedTestCase.checkstyle.OldOutputAndErrorCheckingTestCase;


public class InvalidCharacterTestCase extends OldOutputAndErrorCheckingTestCase {

    public InvalidCharacterTestCase() {
        super("Invalid Character Test Case");
    }


    String inputWithNoEndingSpace = "10 ten 20 \n40 50 ! \n.";

    String inputWithEndingSpace = "10 ten 20\n40 forty 50 ! \n.";

    String[] expectedOutputs = {"(.*)30(.*)", "(.*)200(.*)", "(.*)90(.*)", "(.*)2000(.*)"};


    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            OutputErrorStatus retVal = test(project, inputWithEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return pass();
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(0.5, "Correct output but errors");
           retVal = test(project, inputWithNoEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return pass();
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	return partialPass(0.5, "Correct output but errors with invalid character");

            return fail("Program gives errors and wrong output with invalid characters", autoGrade);
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

