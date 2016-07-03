package gradingTools.comp110f14lab.lab5.testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptTestCase extends BasicTestCase {

	public PromptTestCase() {
		super("Prompt Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 = RunningProjectUtils.runProject(project, 10,"");
		String output0 = run0.await();
		Pattern prompt = Pattern.compile(".*pl|in|en|num|val|size|len.*");
		boolean hasPrompt=prompt.matcher(output0.toLowerCase()).find();
		if(hasPrompt)return pass();
		return fail("No prompt found.");
		
	}

}
