package gradingTools.assignment1.testCases;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.assignment1.FlexibleProgramRunner;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class TerminateWithPeriodTestCase extends BasicTestCase {

    public TerminateWithPeriodTestCase() {
        super("Terminates with period test case");
    }

//    @Override
//    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//        try {
//            // First check that no period keeps it running
//            FlexibleProgramRunner runner1 = new FlexibleProgramRunner(project, "19 28", false);
//            String output = runner1.runWithSpaces();
//            String output2 = runner1.runNoSpaces();
//            if (!output.contains("*** TIMEOUT ***") && !output2.contains("*** TIMEOUT ***"))
//                return fail("Program terminates prematurely", autoGrade);
//
//            // Check that a period terminates it
//            FlexibleProgramRunner runner2 = new FlexibleProgramRunner(project, "19 28");
//
//            output = runner2.runWithSpaces();
//            if (!output.contains("*** TIMEOUT ***"))
//                return pass(autoGrade);
//            output = runner2.runNoSpaces();
//            if (!output.contains("*** TIMEOUT ***"))
//                return pass(autoGrade);
//            return fail("Program should terminate with period.", autoGrade);
//
//        } catch (NotRunnableException e) {
//            throw new NotGradableException();
//        }
//    }
    boolean validOutput(String anOutput){
    	return anOutput.contains("30") && anOutput.contains("50");
    }
    boolean hasError(String anError){
    	return !anError.isEmpty();
    }
    TestCaseResult test (Project project, String anInput, boolean autoGrade) {
    	FlexibleProgramRunner runner = new FlexibleProgramRunner(project, anInput, true);
        String output = runner.runWithSpaces();
        if (validOutput(output)) {
        	String error = runner.getRunningProject().getErrorOutput();
        	if (hasError(error)) {
        		return fail("Program terminates with errors", autoGrade);
        	} else {
        		return pass(autoGrade);
        	}
        }
        return null;
    }
    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            TestCaseResult retVal = test(project, "10 20 \n20 30 ", autoGrade);
            if (retVal == null) {
            	retVal = test(project, "10 20\20 30", autoGrade);
            }
            if (retVal == null) {
            	return fail("Program terminates with errors", autoGrade);
            }
            return retVal;

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

