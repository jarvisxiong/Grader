package gradingTools.comp110s14.assignment3.testCases;

import java.util.regex.Pattern;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectOutputTypeOnComputationChoice extends BasicTestCase {

	String input;
	String choice;
	String[] mutuallyExclusiveOutputs;
	String outputRegex;
	String[] incorrectOutputs;

	public CorrectOutputTypeOnComputationChoice(String input, String choice,
			String[] mutuallyExclusiveOutputs, String[] incorrectOutputs) {
		super("Correct computation for " + choice + " choice");
		this.choice = choice;
		this.input = input;
		this.mutuallyExclusiveOutputs = mutuallyExclusiveOutputs;
		this.incorrectOutputs = incorrectOutputs;
	}

	public CorrectOutputTypeOnComputationChoice(String input, String choice, String outputRegex,
			String[] incorrectOutputs) {
		super("Correct computation for " + choice + " choice");
		this.input = input;
		this.choice = choice;
		this.outputRegex = outputRegex;
		this.incorrectOutputs = incorrectOutputs;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 1, input);
			String prompt = runningProject.await();
			if (prompt.endsWith("\n")) {
				prompt = prompt.substring(0, prompt.length() - 1);
			}

			runningProject = RunningProjectUtils.runProject(project, 1, input, choice);
			String output = runningProject.await();
			if (output.startsWith(prompt)) {
				output = output.substring(prompt.length());

				boolean correct = true;
				if (outputRegex != null) {
					correct = Pattern.compile(outputRegex).matcher(output).find();
				} else {
					boolean containsOne = false;
					boolean containsMany = false;
					for (String mutuallyExclusiveOutput : mutuallyExclusiveOutputs) {
						if (output.contains(mutuallyExclusiveOutput)) {
							if (containsOne) {
								containsMany = true;
							} else {
								containsOne = true;
							}
						}
					}
					correct = containsOne && !containsMany;
				}

				if (correct) {
					for (String incorrectOutput : incorrectOutputs) {
						if (output.contains(incorrectOutput)) {
							correct = false;
							break;
						}
					}
				}

				if (correct) {
					return pass();
				} else {
					return fail("Incorrect output for computation " + choice);
				}

			} else {
				throw new NotAutomatableException();
			}
		} catch (NotRunnableException e) {
			throw new NotAutomatableException();
		}
	}
}
