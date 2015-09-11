package gradingTools.comp110f15lab.lab1.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectFormat extends BasicTestCase{

	public CorrectFormat() {
		super("Correct Printint Outing");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject p0=RunningProjectUtils.runProject(project, 10,"");
		String out0=p0.await();
		String[] spliced = out0.split("\n");
		int numright=0;
		for (String string : spliced) {
			string = string.toLowerCase();
			if(string.contains("he two integers are")) numright++;
			if(string.contains("heir addition is"))numright++;
			if(string.contains("he quotient is")) numright++;
			if(string.contains("heir product is"))numright++;
			if(string.contains("he remainder is")) numright++;
		}
		if(numright==5)return pass();
		if(numright==0)return fail("Could not find any of the key phrases in your output");
		
		else {
			double percentage=(double)numright/5;
			return partialPass(percentage,"Could not find one or more of the phrases in output");
		}
	}

}
