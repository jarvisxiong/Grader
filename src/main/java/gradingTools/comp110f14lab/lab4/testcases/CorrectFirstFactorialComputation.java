package gradingTools.comp110f14lab.lab4.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectFirstFactorialComputation extends BasicTestCase {

	public CorrectFirstFactorialComputation() {
		super("Correct first factorial computation");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		String case1 = "3\n";
		String result1 = "6";
		
		String case2 = "9\n";
		String result2 = "362880";
		
		String errorCase = "0\n";

		RunningProject project0 = RunningProjectUtils.runProject(project, 1,"");
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
		
		if (!output1.contains(result1)) {
			return fail("Does not return proper value for "+case1+" in first factorial (should be "+result1+")");
		}
		if (!output2.contains(result2)) {
			return fail("Does not return proper value for "+case2+" in first factorial (should be "+result2+")");
		}
		if (!errorOutput.toLowerCase().contains("err") && !output1.toLowerCase().contains("err")) {
			return fail("Does not print error at the correct times for first factorial");
		}
		
		return pass();
	}

}
