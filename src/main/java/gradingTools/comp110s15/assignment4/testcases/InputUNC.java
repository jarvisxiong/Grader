package gradingTools.comp110s15.assignment4.testcases;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class InputUNC extends BasicTestCase{

	public InputUNC() {
		super("handles unc input correctly");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"UNC\n");
		String out2=Project2.await().toLowerCase();
		int numright=0;
		BasicRunningProject Project3 = RunningProjectUtils.runProject(project, 10,
				"UNC\n5\n");
		String out3 = Project3.await().toLowerCase();
		BasicRunningProject Project4 = RunningProjectUtils.runProject(project, 10,
				"UNC\n4\n");
		String out4=Project4.await().toLowerCase();
		BasicRunningProject Project5 = RunningProjectUtils.runProject(project, 10,
				"UNC\n1000\n");
		String out5 = Project5.await().toLowerCase();
		if(out3.substring(out2.length()).contains("true fan"))numright++;
		if(out4.substring(out2.length()).contains("fake fan"))numright++;
		if(out5.substring(out2.length()).contains("day"))numright++;
		if(numright==3)return pass();
		if(numright==0)return fail("3 different input possibilites. Failed all of these requirements");
		else return partialPass(numright/3,"one or more requirements incorrect, added ignores case check here");
		
		
	}

}
