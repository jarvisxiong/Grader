package gradingTools.comp110f15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AHIIII extends BasicTestCase {

	public AHIIII() {
		super("This will test pathway AHIIIII");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 =RunningProjectUtils.runProject(project, 10, "3\n1\n1\n2\n3"); //should have 4 I prints
		String out0 = run0.await();
		String[] lines = out0.split("\n");
		int icounter=0;
		for (String string : lines) {
			if(string.contains("I:"))icounter++;
		}
		if(icounter==4)return pass();
		else return fail("I put in output such that I should see statement I printed 4 times. This program printed I "+icounter+" times instead.");
	}

}
