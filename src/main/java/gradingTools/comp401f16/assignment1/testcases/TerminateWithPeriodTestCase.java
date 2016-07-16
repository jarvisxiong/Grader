package gradingTools.comp401f16.assignment1.testcases;

import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.sharedTestCase.OutputAndErrorCheckingTestCase;


public class TerminateWithPeriodTestCase extends OutputAndErrorCheckingTestCase {

    public TerminateWithPeriodTestCase() {
        super("Terminates with period test case");
    }


//    String simplifiedInputWithNoEndingSpace = "10 20 \n40 50 \n.\n\n";
//    String simplifiedInputWithEndingSpace = "10 20\n40 50 \n.\n\n";
//    String inputWithEndingSpace = "10 20 \n40 50 \n.the end\n\n";
//    String inputWithNoEndingSpace = "10 20\n40 50 \n.the end\n\n";
//    String[] expectedOutputs = {".*30.*", ".*200.*", ".*90.*", ".*2000.*"};
    
    String simplifiedInputWithEndingSpace = "10 20 \n.\n";
    String simplifiedInputWithNoEndingSpace = "10 20\n.\n";
    String inputWithEndingSpace = "10 20 \n.the end\n";
    String inputWithNoEndingSpace = "10 20\n.the end\n";
//    String[] expectedOutputs = {"30", "200", "90", "2000"};
    String[] expectedOutputs = {".*30.*", ".*200.*"};
    


    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            OutputErrorStatus retVal = test(project, inputWithEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return pass();
            retVal = test(project, inputWithNoEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return pass();
            retVal = test(project, simplifiedInputWithEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return partialPass(0.5, "Cannot handle characters after .");            
            retVal = test(project, simplifiedInputWithNoEndingSpace, expectedOutputs, autoGrade);
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return partialPass(0.5, "Cannot handle characters after .");

            return fail("Program gives errors with ending .", autoGrade);
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

