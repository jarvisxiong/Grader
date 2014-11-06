package gradingTools.comp110f14lab.lab5.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
