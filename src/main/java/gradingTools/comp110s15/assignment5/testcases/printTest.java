package gradingTools.comp110s15.assignment5.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class printTest extends BasicTestCase {

	public printTest() {
		super("printTest test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0=RunningProjectUtils.runProject(project, 10, "");
		String out0=Project0.await().toLowerCase();
		boolean printwhile=false;
		boolean printdowhile=false;
		Pattern goowhile= Pattern.compile(".*i am a while loop.*i am a while loop.*i am a while loop.*");
		Pattern goodowhile=Pattern.compile(".*i am a do while loop.*i am a do while loop.*i am a do while loop.*");
		if(goowhile.matcher(out0.replaceAll("\n"," ")).find())printwhile=true;
		if(goodowhile.matcher(out0.replaceAll("\n"," ")).find())printdowhile=true;
		if(printwhile&&printdowhile)return pass();
		if(!printwhile&&!printdowhile)return fail("did not print I am a while loop 3 times and did not print I am a do while loop 3 times");
		if(!printwhile)return partialPass(0.5,"did not print I am a while loop 3 times");
		else return partialPass(0.5,"did not print out I am a do while loop 3 times");
	}

}
