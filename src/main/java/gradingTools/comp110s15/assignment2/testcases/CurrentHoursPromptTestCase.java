/**
 * 
 */
package gradingTools.comp110s15.assignment2.testcases;
import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;


public class CurrentHoursPromptTestCase extends BasicTestCase {

	public CurrentHoursPromptTestCase() {
		// TODO Auto-generated constructor stub
		super("Prompt Test Case for Hours enrolled in this semester");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		String output0=Project0.await().toLowerCase();
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10, "10");
		String output1=Project1.await().toLowerCase();
		String testblock=output1.substring(output0.length()-1);
		if(testblock.contains("semester")||testblock.contains("enrolled")||testblock.contains("hour")){
			return pass();
		}return fail("no prompt for hours enrolled in this semester");
	}

}
