package gradingTools.comp110f14lab.lab4.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectSecondFactorialComputation extends BasicTestCase {

	public CorrectSecondFactorialComputation() {
		super("Correct second factorial computation");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		String firstFactorial = "4\n";
		
		
		String case1 = firstFactorial + "10\n";
		String result1 = "3628800";
		
		String case2 = firstFactorial + "2\n";
		String result2 = "2";
		
		String errorCase = firstFactorial + "-1\n";

		RunningProject project0 = RunningProjectUtils.runProject(project, 1, firstFactorial);
		String prompt = project0.await();
		
		RunningProject project1 = RunningProjectUtils.runProject(project, 1,case1);
		String output1 = project1.await();
		output1 = output1.substring(prompt.length());
		
		RunningProject project2 = RunningProjectUtils.runProject(project, 1,case2);
		String output2 = project2.await();
		output2 = output2.substring(prompt.length());
		
		RunningProject project3 = RunningProjectUtils.runProject(project, 1,errorCase);
		String errorOutput = project3.await();
		errorOutput = errorOutput.substring(prompt.length());
		
		if (output1.trim().length() == 0) {
			return fail("Does not compute second factorial");
		}
		
		if (!output1.contains(result1)) {
			return fail("Does not return proper value for "+case1+" in second factorial (should be "+result1+")");
		}
		if (!output2.contains(result2)) {
			return fail("Does not return proper value for "+case2+" in second factorial (should be "+result2+")");
		}
		if (!errorOutput.toLowerCase().contains("err") && !output1.toLowerCase().contains("err")) {
			return fail("Does not print error at the correct times for second factorial");
		}
		
		return pass();
	}

}
