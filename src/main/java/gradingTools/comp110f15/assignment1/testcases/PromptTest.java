package gradingTools.comp110f15.assignment1.testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptTest extends BasicTestCase{

	public PromptTest() {
		super("Correct Prompts?");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject p0=RunningProjectUtils.runProject(project, 10,"");
		String UNCInfo=p0.await();
		RunningProject p1=RunningProjectUtils.runProject(project, 10,"10\n10\n10\n10");
		String DukeInfo=p1.await();
		UNCInfo=UNCInfo.toLowerCase();
		boolean unc=false;
		if(UNCInfo.contains("unc")||UNCInfo.contains("heel"))unc=true;
		DukeInfo=DukeInfo.substring(UNCInfo.length()-1);
		DukeInfo=DukeInfo.toLowerCase();
		boolean duke=false;
		if(DukeInfo.contains("duke")||DukeInfo.contains("dook")||DukeInfo.contains("dev")||DukeInfo.contains("blu")) duke=true;
		if(duke&&unc)return pass();
		if(!duke)return partialPass(0.5,"did not catch prompt for Duke score");
		if(!unc)return partialPass(0.5,"did not catch prompt for UNC score");
		return fail("did not catch prompts for either team scores");
	}

}
