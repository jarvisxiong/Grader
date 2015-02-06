package gradingTools.comp110s15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PrintHoursLeft extends BasicTestCase{

	public PrintHoursLeft() {
		super("Print Hours Left Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10, "12\n17\n");
		String output0=Project0.await().toLowerCase();
		RunningProject Project1 = RunningProjectUtils.runProject(project,10,"12\n17\n");
		String output1=Project1.await().toLowerCase();
		Pattern prompt = Pattern.compile(".*grad|hour|you|left.*");
		if(prompt.matcher(output1).find())return pass();
		return fail("No printout for hours left needed found.");
	}

}
