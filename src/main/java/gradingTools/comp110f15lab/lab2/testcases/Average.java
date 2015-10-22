package gradingTools.comp110f15lab.lab2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class Average extends BasicTestCase {

	public Average() {
		super("Correctly computes average when prompted to do so");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException,
			NoSuchFieldException {
		// TODO Auto-generated method stub
		RunningProject p0 = RunningProjectUtils.runProject(project, 10,
				"3\n3\n6\n12\n");
		String out0=p0.await();
		RunningProject p1 = RunningProjectUtils.runProject(project, 10,
				"3\n3\n6\n12\n2\n");
		String out1=p1.await();
		out1=out1.substring(out0.length()-2);
		if(out1.contains("7")&&!out1.contains("21"))return pass();
		return fail("Either you did not compute average correctly, or you also printed the sum when we did not ask for it.");	
	}

}
