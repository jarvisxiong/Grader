package gradingTools.comp110s15.assignment3.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class NameLengthTest extends BasicTestCase {

	public NameLengthTest() {
		super("Name Length Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		String disclaimer="Note that during this test we check if the program gives a desired keyword, this insinuates we take off all points"
				+ "if student tries to trick the grader by simply printing both keywords instead of using conditionals.";
		RunningProject proj0 = RunningProjectUtils.runProject(project, 10, "LILIES\n");
		String out0=proj0.await();
		RunningProject proj1 = RunningProjectUtils.runProject(project,10,"LILIES\nmaxwell\n");
		String out1=proj1.await();
		RunningProject proj2 = RunningProjectUtils.runProject(project,10,"LILIES\nmax\n");
		String out2 = proj2.await();
		boolean goodshort,goodlong;
		if(out1.substring(out0.length()-1).contains("long"))goodlong=true;
		else goodlong=false;
		if(out2.substring(out0.length()-1).contains("short"))goodshort=true;
		else goodshort=false;
		if(goodlong&&goodshort)return pass();
		else if(!goodlong&&!goodshort)return fail("Incorrect output for long name and short name. "+disclaimer);
		else return partialPass(0.5,"Result: Correct output for long name: "+goodlong+"\nCorrect output for short name: "+goodshort);
	}

}
