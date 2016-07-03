package gradingTools.comp110f14.assignment4testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class EyeColorTest extends BasicTestCase {

	public EyeColorTest(){
		super("Eye Color Test Case ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject nocolor = RunningProjectUtils.runProject(project, 10, "baaBaab");
		String outputnocolor = nocolor.await();
		RunningProject blue = RunningProjectUtils.runProject(project, 10, "werbbaj");
		String outputblue = blue.await();
		RunningProject brown1 = RunningProjectUtils.runProject(project, 10, "abBaf");
		String outputbrown1 = brown1.await();
		RunningProject brown2 = RunningProjectUtils.runProject(project, 10, "aaffBbaow");
		String outputbrown2 = brown2.await();
		RunningProject brown3 = RunningProjectUtils.runProject(project, 10, "aBBwf");
		String outputbrown3 = brown3.await();
		boolean[] goo = new boolean[5];
		int numright=0;
		goo[0]=outputnocolor.toLowerCase().contains("brown")&&outputnocolor.contains("blue");
		goo[1]=outputblue.toLowerCase().contains("blue");
		goo[2]=outputbrown1.toLowerCase().contains("brown");
		goo[3]=outputbrown2.toLowerCase().contains("brown");
		goo[4]=outputbrown3.toLowerCase().contains("brown");
		for (int i = 0; i < goo.length; i++) {
			if(goo[i])numright++;
		}
		if(numright==5)return pass();
		if(numright==0)return fail("does not check color correctly");
		return partialPass(numright/5,"did not do all color checking correctly. 6 points taken off for each of the 5 ways we can tell eye color described in prompt. Note that no eye color ascertained is one of these ways.");
		
	}

}