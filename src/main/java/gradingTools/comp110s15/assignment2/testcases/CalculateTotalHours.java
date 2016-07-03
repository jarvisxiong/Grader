package gradingTools.comp110s15.assignment2.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CalculateTotalHours extends BasicTestCase {

	public CalculateTotalHours() {
		super("Calculate Total Hours Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		
		int taken = 81;
		int enrolled = 16;
		int total = taken+enrolled;
		
		RunningProject runningProject = RunningProjectUtils.runProject(project, 10,""+taken+'\n'+ enrolled);
		
		String hoursOutput=runningProject.await();
		
		if(hoursOutput.contains(""+total+" total")){
			return pass();
		}
		
		/*
		Scanner goo=new Scanner(hoursOutput);
		if(goo.hasNext()){
			String line=goo.nextLine();
			if(hoursOutput.contains(""+total+" total"))
				return pass();
		}
		*/
		
		return fail("Did not Calculate Total Hours correctly");
	}

}
