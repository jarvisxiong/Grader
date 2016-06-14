package gradingTools.comp110s15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PrintTotalHours extends BasicTestCase{

	public PrintTotalHours() {
		super("Print Total Hours Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10, "10");
		String output0=Project0.await().toLowerCase();
		BasicRunningProject Project1 = RunningProjectUtils.runProject(project,10,"12\n17\n");
		String output1=Project1.await().toLowerCase();
		String testblock=output1.substring(output0.length()-1);
		Pattern prompt = Pattern.compile(".*tot|hour|you|take.*");
		if(prompt.matcher(testblock).find())return pass();
		return fail("No printout for total hours taken found.");
	}

}
