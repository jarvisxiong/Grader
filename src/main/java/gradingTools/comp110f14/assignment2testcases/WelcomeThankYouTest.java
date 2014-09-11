package gradingTools.comp110f14.assignment2testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
			RunningProject runningProject = RunningProjectUtils.runProject(project, 3,"200\n55");
			String output=runningProject.await();
			output.toLowerCase();
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
