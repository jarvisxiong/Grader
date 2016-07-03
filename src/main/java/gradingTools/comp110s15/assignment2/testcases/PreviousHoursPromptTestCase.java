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

/**
 * @author jphong
 *
 */
public class PreviousHoursPromptTestCase extends BasicTestCase {

	public PreviousHoursPromptTestCase() {
		// TODO Auto-generated constructor stub
		super("Prompt Test Case for Hours taken upto this semester");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		String output0=Project0.await().toLowerCase();
		boolean hasPrevHoursPrompt=false;
		if(output0.contains("until")||output0.contains("semester")) {
			hasPrevHoursPrompt=true;
		}
		if(hasPrevHoursPrompt) {
			return pass();
		}
		else {
			return fail("no prompt seen for hours taken upto this semester");		

		}
	}

}
