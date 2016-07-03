package gradingTools.comp110f14.assignment2testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class WelcomeThankYouTest extends BasicTestCase {

	public WelcomeThankYouTest() {
		super("WelcomeThankYouTestCase");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean hasWelcome=false;
		boolean hasThankYou=false;
		try{
			RunningProject runningProject = RunningProjectUtils.runProject(project, 10,"200\n55");
			String output=runningProject.await().toLowerCase();
			if(output.contains("welcome")||output.contains("hi")||output.contains("hello"))
				hasWelcome=true;
			if(output.contains("thank")) hasThankYou=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasWelcome && hasThankYou)return pass();
		if(!hasWelcome&&hasThankYou)return partialPass(0.5,"missing Welcome message");
		if(hasWelcome&&!hasThankYou)return partialPass(0.5,"missing Thank You message");
		else return fail("No Welcome or Thank You message");
	}

}
