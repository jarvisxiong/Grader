package gradingTools.comp110f14.assignment5testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AskingAgain extends BasicTestCase{

	public AskingAgain() {
		//mandatory super call to create instance of BasicTestCase w/ message
		super("Asking Again Test Case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		//pattern searching for
		Pattern again = Pattern.compile(".*another|message.*");
		//invoke instance of assignment five input string w/ timeout
		RunningProject againInfo = RunningProjectUtils.runProject(project, 10, "ToBeOrNotToBe");
		//grab everything after input up to point waiting for input
		String outagainInfo= againInfo.await();

		//If dont ask for another message - fail w/ message
		if(!again.matcher(outagainInfo).find()){
			return fail("Failed to ask again");
		}
		
		//asks for another message
		return pass();
		
	}

	
}
