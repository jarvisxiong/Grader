package gradingTools.comp110s15.assignment3.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
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
		
		RunningProject runningProject = RunningProjectUtils.runProject(project,
				10, ""+ flowerType+ '\n');
		
		String flowerOutput=runningProject.await();
		
		if(flowerOutput.contains("do not carry")){
			return pass();
		}
		
		return fail("Did not respond correctly to unsupported flower type");
	}

}
