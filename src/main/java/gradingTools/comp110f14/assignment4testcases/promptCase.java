package gradingTools.comp110f14.assignment4testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class promptCase extends BasicTestCase {
	public promptCase() {
		super("Prompt Test Case");
		// TODO Auto-generated constructor stub
	}
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		String prompt=Project0.await().toLowerCase();
		boolean hasPrompt=false;
		if(prompt.contains("gene"))hasPrompt=true;
		if(hasPrompt)return pass();
		return fail("Did not prompt for gene input");
	}
}
