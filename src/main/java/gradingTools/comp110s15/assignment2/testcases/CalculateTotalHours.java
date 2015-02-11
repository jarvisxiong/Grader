package gradingTools.comp110s15.assignment2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
