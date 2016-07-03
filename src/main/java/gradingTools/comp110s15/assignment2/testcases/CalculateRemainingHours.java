package gradingTools.comp110s15.assignment2.testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CalculateRemainingHours extends BasicTestCase {

	public CalculateRemainingHours() {
		super("Calculate Remaining Hours Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		
		int hoursRequired = 120;
		int taken = 83;
		int enrolled = 20;
		int remaining = hoursRequired-(taken+enrolled);
		
		RunningProject runningProject = RunningProjectUtils.runProject(project, 10,""+taken+'\n'+ enrolled);
		
		String hoursOutput=runningProject.await();
		
		if(hoursOutput.contains(""+remaining)){
			return pass();
		}
		
		/*
		Scanner goo=new Scanner(hoursOutput);
		while(goo.hasNext()){
			String line=goo.nextLine();
			if(hoursOutput.contains(""+remaining+ " hours"))
				return pass();
		}
		*/
		
		return fail("Did not Calculate Remaining Hours correctly");
	}

}
