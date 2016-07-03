package gradingTools.comp110s15.assignment3.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;



public class RoseOutputPromptTest extends BasicTestCase {

	public RoseOutputPromptTest() {
		super("Test Case for Rose Output Prompt");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"roses\n3\n");
		String output0=Project0.await().toLowerCase();
		boolean hasRoseOutPrompt=true;
		if(!output0.contains("dozen") ){
				hasRoseOutPrompt=false;
		}
		if(hasRoseOutPrompt) {
			return pass();
		}
		else {
			return fail("no prompt seen for individual roses");		

		}	
	}

}
