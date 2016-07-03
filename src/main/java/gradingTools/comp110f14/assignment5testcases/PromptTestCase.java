package gradingTools.comp110f14.assignment5testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

//signature includses extends BasicTestCase in order to implement test
public class PromptTestCase extends BasicTestCase {
	//mandatory constructor to invoke test case
	public PromptTestCase() {
		//Call to super BasicTestCase to create instance
		super("Prompt Test Case");
		// TODO Auto-generated constructor stub
	}
	
	//actual test case with necessary exceptions
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		//invoke instance of code and time out
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		//grabs user prompt String and sets it to lower case so ignores case
		String prompt=Project0.await().toLowerCase();
		//initialize boolean
		boolean hasPrompt=false;
		//search for desired string
		if(prompt.contains("enter"))hasPrompt=true;
		//return pass or fail with prompt
		if(hasPrompt)return pass();
		return fail("Did not correctly prompt for user input");
	}
}
