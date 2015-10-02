package gradingTools.comp110f15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AEFK extends BasicTestCase {

	public AEFK() {
		super("This will test pathway AEFK");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 =RunningProjectUtils.runProject(project, 10, "2\n1");
		String out0 = run0.await();
		out0=out0.replaceAll("\n", " ");
		Pattern p = Pattern.compile("take.*A:.*E:.*F:.*K:.*");
		if(p.matcher(out0).find()) return pass();
		return fail("Did not print out message A,E,F,K when user takes that path.");
	}

}
