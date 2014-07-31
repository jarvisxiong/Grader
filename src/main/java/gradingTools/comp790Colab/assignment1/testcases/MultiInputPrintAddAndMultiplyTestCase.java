package gradingTools.comp790Colab.assignment1.testcases;

import java.util.regex.Pattern;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class MultiInputPrintAddAndMultiplyTestCase extends BasicTestCase {

	public MultiInputPrintAddAndMultiplyTestCase() {
		super("Add and Multiply Test Case");
	}

	public String getOutput(Project project, int input1, double input2, boolean input1First)
			throws NotRunnableException {

		// Get the output when we have integer input from the user
		RunningProject oneInputRunningProject = MultiInputPromptTestCase.runAliceBobProject(project, 5, input1);
		String oneInputOutput = oneInputRunningProject.await();

		// Get the output when we have double input from the user
		RunningProject twoInputsRunningProject;
		
		String twoInputsOutput;
		twoInputsRunningProject = MultiInputPromptTestCase.runAliceBobProject(project, 5, input1,
				input2);
		twoInputsOutput = twoInputsRunningProject.await();
		if (twoInputsOutput != null)
		twoInputsOutput = twoInputsOutput.substring(oneInputOutput.length());

		if ((twoInputsOutput == null) || twoInputsOutput.length() == 0
				&& (oneInputRunningProject.getErrorOutput().contains("InputMismatchException") || twoInputsRunningProject
						.getErrorOutput().contains("InputMismatchException"))) {
			return null;
		}
		return twoInputsOutput;
	}
//	public String getOutput(Project project, double input1, int input2)
//			throws NotRunnableException {
//
//		// Get the output when we have integer input from the user
//		RunningProject oneInputRunningProject = MultiInputPromptTestCase.runAliceBobProject(project, 1, input1);
//		String oneInputOutput = oneInputRunningProject.await();
//
//		// Get the output when we have double input from the user
//		RunningProject twoInputsRunningProject = MultiInputPromptTestCase.runAliceBobProject(project, 1, input2,
//				input1);
//		String twoInputsOutput = twoInputsRunningProject.await();
//		twoInputsOutput = twoInputsOutput.substring(oneInputOutput.length());
//
//		if (twoInputsOutput.length() == 0
//				&& (oneInputRunningProject.getErrorOutput().contains("InputMismatchException") || twoInputsRunningProject
//						.getErrorOutput().contains("InputMismatchException"))) {
//			return null;
//		}
//		return twoInputsOutput;
//	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {

			int integerInput = 4;
			double doubleInput = 5.213025;

			String integerAddResultREGEX = "[^\\d]9[^\\d.]";
			String doubleAddResultREGEX = "[^\\d]9.213025[^\\d.]";

			String integerMultiplyResultREGEX = "[^\\d]20[^\\d.]";
			String doubleMultiplyResultREGEX = "[^\\d]20.8521[^\\d.]";

			// Try getting the results with integer, then double
			String output = getOutput(project, integerInput, doubleInput, true);

			// If that is null, get reverse the order of the inputs
			if (output == null) {
				output = getOutput(project, integerInput, doubleInput, false);
			}

			if (output == null) {
				throw new NotAutomatableException();
			}

			// Check for the expected results
			boolean hasIntegerAdd = Pattern.compile(integerAddResultREGEX).matcher(output).find();
			boolean hasDoubleAdd = Pattern.compile(doubleAddResultREGEX).matcher(output).find();
			boolean hasIntegerMultiply = Pattern.compile(integerMultiplyResultREGEX)
					.matcher(output).find();
			boolean hasDoubleMultiply = Pattern.compile(doubleMultiplyResultREGEX).matcher(output)
					.find();

			if (hasIntegerAdd && hasDoubleAdd && hasIntegerMultiply && hasDoubleMultiply) {
				return pass();
			} else {
				String message = "";
				double score = 1.0;

				if (!hasIntegerAdd) {
					message += "Does not output the correct integer sum of the numbers\n";
					score -= 0.25;
				}
				if (!hasDoubleAdd) {
					message += "Does not output the correct double sum of the numbers\n";
					score -= 0.25;
				}
				if (!hasIntegerMultiply) {
					message += "Does not output the correct integer product of the numbers\n";
					score -= 0.25;
				}
				if (!hasDoubleMultiply) {
					message += "Does not output the correct double product of the numbers\n";
					score -= 0.25;
				}

				return partialPass(score, message);
			}

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
