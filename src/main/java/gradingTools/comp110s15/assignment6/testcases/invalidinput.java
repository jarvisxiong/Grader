package gradingTools.comp110s15.assignment6.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class invalidinput extends BasicTestCase{

	public invalidinput() {
		super("invalid input test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
				RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
						"");
				String output0 = Project0.await().toLowerCase();
				// deposit 20 and then withdraw 20.1
				RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
						"deposit\n20\ngoo");
				String output1 = Project1.await().toLowerCase();
				output1=output1.substring(output0.length()-1);
				if(output1.contains("invalid"))return pass();
				else return fail("did not contain string invalid in invalid input response");
	}

}
