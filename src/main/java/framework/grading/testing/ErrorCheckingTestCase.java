package framework.grading.testing;

import java.util.List;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.project.Project;
import gradingTools.assignment1.FlexibleProgramRunner;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ErrorCheckingTestCase extends BasicTestCase {
	public enum OutputErrorStatus {
		CORRECT_OUTPUT_NO_ERRORS,
		CORRECT_OUTPUT_ERRORS,
		INCORRECT_OUTPUT_NO_ERRORS,
		INCORRECT_OUTPUT_ERRORS
	}
    public ErrorCheckingTestCase(String aMessage) {
        super(aMessage);
    }


   protected boolean validOutput(String anOutput, String[] anExpectedStrings){
    	for (String anExpectedString: anExpectedStrings) {
    		if (!anOutput.contains(anExpectedString))
    			return false;
    	}
    	return true;
    }
   protected  boolean hasError(String anError){
    	return !anError.isEmpty();
    }
    
   protected  OutputErrorStatus test (Project project, String anInput, String[] anExpectedStrings, boolean autoGrade) {
    	RunningProject runner = project.launch(anInput, 1);
        String output = runner.await();
        boolean validOutput = validOutput(output, anExpectedStrings);
        String error = runner.getErrorOutput();
        boolean hasError = hasError(error);
        if (validOutput && !hasError) {
        	return OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS;
        }
        if (validOutput && hasError) {
        	return OutputErrorStatus.CORRECT_OUTPUT_ERRORS;
        }
        if (!validOutput && !hasError) {
        	return OutputErrorStatus.INCORRECT_OUTPUT_NO_ERRORS;
        }
        return OutputErrorStatus.INCORRECT_OUTPUT_ERRORS;        
    }
    
}

