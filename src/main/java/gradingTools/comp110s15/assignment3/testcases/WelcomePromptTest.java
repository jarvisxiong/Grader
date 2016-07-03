/**
 * 
 */
package gradingTools.comp110s15.assignment3.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class WelcomePromptTest extends BasicTestCase{

	public WelcomePromptTest() {
		super("WelcomePromptTestCase");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		boolean hasWelcome=false;
		try{
			RunningProject runningProject = RunningProjectUtils.runProject(project, 10,"");
			String output=runningProject.await().toLowerCase();
			if( output.contains("welcome") ) {
				hasWelcome=true;
			}				
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasWelcome) {
			return pass();
		}
		else {
			return fail("No Welcome message");
		}
	}

}