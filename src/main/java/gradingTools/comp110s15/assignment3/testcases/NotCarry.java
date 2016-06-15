package gradingTools.comp110s15.assignment3.testcases;

import framework.execution.BasicRunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class NotCarry extends BasicTestCase {

	public NotCarry() {
		super("Print Not Carried Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		
		String flowerType = "tulips";
		
		BasicRunningProject runningProject = RunningProjectUtils.runProject(project,
				10, ""+ flowerType+ '\n');
		
		String flowerOutput=runningProject.await();
		
		if(flowerOutput.contains("do not carry")){
			return pass();
		}
		
		return fail("Did not respond correctly to unsupported flower type");
	}

}
