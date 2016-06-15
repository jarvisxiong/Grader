package gradingTools.comp110s15.assignment3.testcases;

import framework.execution.BasicRunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10,"roses\n3\n");
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
