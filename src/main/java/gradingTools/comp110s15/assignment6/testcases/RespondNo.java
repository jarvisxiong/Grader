package gradingTools.comp110s15.assignment6.testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class RespondNo extends BasicTestCase {

	public RespondNo() {
		super("RespondNo test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"deposit\n45\nyes\ndeposit");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);
		
		RunningProject Project2 = RunningProjectUtils.runProject(project, 10, "deposit\n45\nno\ndeposit");
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length()-1);
		
		if(output2.length()<output1.length()){
			return pass();
		}
		
		return fail("did not correctly operate when user entered No");
		
	}

}