package gradingTools.comp401f15.assignment10.testCases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.MoveCommandInterpretedTestCase;

public class MoveCommandPartiallyInterpretedTestCase extends MoveCommandInterpretedTestCase{
	
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
    	TestCaseResult aResult = super.test(project, autoGrade);
    	if (aResult.getPercentage() > 0)
    		return pass();
    	return aResult;
    }

}
