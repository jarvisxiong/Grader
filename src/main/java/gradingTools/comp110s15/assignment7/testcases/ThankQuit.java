package gradingTools.comp110s15.assignment7.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ThankQuit extends BasicTestCase {

	public ThankQuit() {
		super("Quit/Thank Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"a\n");
		String output0 = Project0.await().toLowerCase();
		boolean thankgood=false;
		boolean quitgood=false;
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"max\nquit\n");
		String output1 = Project1.await().toLowerCase();
		output1=output1.substring(output0.length()-1);
		if(output1.contains("thank"))thankgood=true;
		RunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"a\nquit\nINCORRECT");
		String output2 = Project2.await().toLowerCase();
		output2=output2.substring(output0.length()-1);
		if(output2.length()<=output1.length())quitgood=true;
		if(thankgood&&quitgood)return pass();
		else if(!thankgood&&quitgood)return partialPass(0.5,"did not thank user");
		else if(thankgood&&!quitgood)return partialPass(0.5,"did not terminate on quit command");
		else return fail("Did not quit or thank user");
		
	}

}
