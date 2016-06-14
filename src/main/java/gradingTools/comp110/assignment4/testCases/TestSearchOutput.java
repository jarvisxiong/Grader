package gradingTools.comp110.assignment4.testCases;

import java.util.regex.Pattern;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TestSearchOutput extends TestGerbilInputWithCommand {

	public static final String CORRECT_OUTPUT_REGEX = ".*Malcolm.*(.*will not escape,.*will not bite.*).*"
			+ ".*Cheese.*20/20.*"
			+ "Fava Beans.*25/50.*"
			+ "Bacon.*30/50.*"
			+ "Gerbil Food.*20/60.*"
			+ "Gerbil.*1/120.*";

	@Override
	protected String getSetupInput() {
		return super.getSetupInput() + "\n" + command;
	}

	public TestSearchOutput() {
		super("search");
	}

	@Override
	protected TestCaseResult checkOutputString(String result) {
		result = result.trim();
		boolean containsCorrectResult= Pattern.compile(CORRECT_OUTPUT_REGEX).matcher(result).find()
				&& !result.toLowerCase().contains("hannibal")
				&& !result.toLowerCase().contains("prince firstly");
		boolean containsError = result.toLowerCase().contains("error");
		if (containsCorrectResult && !containsError) {
			return pass();
		} else {
			return fail("Did not correctly search for value");
		}
	}
	
	protected TestCaseResult checkErrorOutputString(String result) {
		result = result.trim();
		if (result.toLowerCase().contains("error")
				&& !result.toLowerCase().contains("malcolm")
				&& !result.toLowerCase().contains("hannibal")
				&& !result.toLowerCase().contains("prince firstly")) {
			return pass();
		} else {
			return fail("Did not correctly print error for missing Gerbil");
		}
	}
	
	protected TestCaseResult checkOutput(String prompt, String command, Project project) {
		// Check for correct result of search
		BasicRunningProject runningProject = RunningProjectUtils.runProject(project, 1, getSetupInput() + "\nSB2");
		String output = runningProject.await();
		if (!output.startsWith(prompt)) {
			throw new NotAutomatableException();
		}
		output = output.substring(prompt.length());
		TestCaseResult correctTestResult = checkOutputString(output);
		
		// Check for correct result of search
		runningProject = RunningProjectUtils.runProject(project, 1, getSetupInput() + "\nincorrectGerbilID");
		String errorOutput = runningProject.await();
		if (!errorOutput.startsWith(prompt)) {
			throw new NotAutomatableException();
		}
		errorOutput = errorOutput.substring(prompt.length());
		TestCaseResult errorTestResult = checkErrorOutputString(errorOutput);
		
		if (correctTestResult.getPercentage() > 0 && errorTestResult.getPercentage() > 0) {
			return pass();
		} else {
			double score = 1.0;
			String message = "";
			if (correctTestResult.getPercentage() == 0) {
				score -= 0.5;
				message = correctTestResult.getNotes();
			}
			if (errorTestResult.getPercentage() == 0) {
				score -= 0.5;
				if (message.length() > 0) {
					message += "\n--";
				}
				message += errorTestResult.getNotes();
			}
			return partialPass(score, message);
		}
	}
}
