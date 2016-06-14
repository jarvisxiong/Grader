package gradingTools.comp110f14.assignment4testcases;

import java.util.regex.Pattern;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class askAgain extends BasicTestCase {

	public askAgain() {
		super("Did they ask again?");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
	Pattern again = Pattern.compile(".*again|another|examine|rep.*");
	BasicRunningProject againnoInfo = RunningProjectUtils.runProject(project, 10, "ac");
	BasicRunningProject againInfo = RunningProjectUtils.runProject(project, 10, "BBC");
	String outagainnoInfo=againnoInfo.await();
	String outagainInfo= againInfo.await();
	int numright=2;
	String partialMessage="";
	if(!again.matcher(outagainnoInfo).find()){
		numright--;
		partialMessage+="did not ask for another expression after not enough info\n";
	}
	if(!again.matcher(outagainInfo).find()){
		numright--;
		partialMessage+="did not ask for another expression after valid input";
	}
	if(numright==2)return pass();
	if(numright==0)return fail(partialMessage);
	return partialPass(0.5,partialMessage);
	}

}
