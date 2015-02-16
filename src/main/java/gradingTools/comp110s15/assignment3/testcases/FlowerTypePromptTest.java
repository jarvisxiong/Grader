package gradingTools.comp110s15.assignment3.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class FlowerTypePromptTest extends BasicTestCase{

	public FlowerTypePromptTest() {
		super("Test Case for Flower Type Prompt");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		String output0=Project0.await().toLowerCase();
		boolean hasFlowerTypePrompt=false;
		if( output0.contains("type") ||
			output0.contains("flower") ||
			output0.contains("buy")) {
			hasFlowerTypePrompt=true;
		}
		if(hasFlowerTypePrompt) {
			return pass();
		}
		else {
			return fail("no prompt seen for flower type");		

		}		
	}
	
}

///**
// * @author jphong
// *
// */
//public class PreviousHoursPromptTestCase extends BasicTestCase {
//
//	public PreviousHoursPromptTestCase() {
//		// TODO Auto-generated constructor stub
//		super("Prompt Test Case for Hours taken upto this semester");
//	}
//
//	@Override
//	public TestCaseResult test(Project project, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		// TODO Auto-generated method stub
//		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
//		String output0=Project0.await().toLowerCase();
//		boolean hasPrevHoursPrompt=false;
//		if(output0.contains("until")||output0.contains("semester")) {
//			hasPrevHoursPrompt=true;
//		}
//		if(hasPrevHoursPrompt) {
//			return pass();
//		}
//		else {
//			return fail("no prompt seen for hours taken upto this semester");		
//
//		}
//	}
//
//}
