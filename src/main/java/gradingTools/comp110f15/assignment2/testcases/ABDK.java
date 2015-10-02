package gradingTools.comp110f15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ABDK extends BasicTestCase{

	public ABDK() {
		super("This will test pathway ABDK");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject run0 =RunningProjectUtils.runProject(project, 10, "1\n2");
		String out0 = run0.await();
		out0=out0.replaceAll("\n", " ");
		Pattern p = Pattern.compile("take.*A:.*B:.*D:.*K:.*");
		if(p.matcher(out0).find()) return pass();
		return fail("Did not print out message A,B,D,K when user takes that path.");
	}

}
