package gradingTools.comp401f15.assignment10.testCases;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.SayCommandInterpretedTestCase;

public class SayCommandPartiallyInterpretedTestCase extends SayCommandInterpretedTestCase{
	
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
    	TestCaseResult aResult = super.test(project, autoGrade);
    	if (aResult.getPercentage() > 0)
    		return pass();
    	return aResult;
    }

}
