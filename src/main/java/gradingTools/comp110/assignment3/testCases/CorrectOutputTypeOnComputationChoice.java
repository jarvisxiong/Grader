package gradingTools.comp110.assignment3.testCases;

import java.util.regex.Pattern;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
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
//			RunningProject runningProject = RunningProjectUtils.runProject(project, 1, input);
//			String prompt = runningProject.await();
			String prompt = ExecutionUtil.callMain(input); // writing to a file replaces tab with spaces
			if (prompt.endsWith("\n")) {
				prompt = prompt.substring(0, prompt.length() - 1);
			}
//			if (!prompt.equals(prompt2)) {
//				System.out.println (prompt + "\n != \n" + prompt2);
//			}

//			runningProject = RunningProjectUtils.runProject(project, 1, input, choice);
//			String output = runningProject.await();
			String output = ExecutionUtil.callMain(input, choice);
//			if (!output.equals(output2)) {
//				System.out.println (output + "\n != \n" + output2);
//			}
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
