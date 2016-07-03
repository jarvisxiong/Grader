package gradingTools.comp110f14.assignment6testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

//signature includes extends BasicTestCase in order to implement test
public class PromptTest extends BasicTestCase {
	//mandatory constructor to invoke test case
	public PromptTest() {
		//Call to super BasicTestCase to create instance
		super("Prompt Test");
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
		//in case it catches greeting on separate line than prompt
		if(prompt.contains("order")||prompt.contains("welcome"))hasPrompt=true;
		//return pass or fail with prompt
		if(hasPrompt)return pass();
		return fail("Did not correctly prompt for user input");
	}
}
