package gradingTools.comp790ColabInc.assignment1.testcases;

import framework.execution.NotRunnableException;
import framework.execution.OutputBasedInputGenerator;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.comp110.assignment1.testcases.PromptTestCase;
import gradingTools.comp110Inc.assignment1.testcases.OutputBasedMixedArithmeticInputGenerator;
import gradingTools.utils.RunningProjectUtils;

public class CollaborativeIncrementalInputPromptTestCase extends PromptTestCase {
	public CollaborativeIncrementalInputPromptTestCase() {
//		super("Prompt printer test case");
		super();
	}
	
	public static boolean hasIntegerPrompt(String output) {
		return output.trim().toLowerCase().contains("int");
	}
	
	public static boolean hasDoublePrompt(String output) {
		return output.toLowerCase().contains("decimal") || output.toLowerCase().contains("double");
	}

//	private TestCaseResult testForIntegerPrompt(OutputBasedMixedArithmeticInputGenerator anOutputBasedInputGenerator) {
////		if (output.trim().toLowerCase().contains("int"))
//		if (anOutputBasedInputGenerator.foundIntPrompt())
//			return pass();
//		else
//			return fail("Program does not contain prompt for integer");
//	}
//
//	private TestCaseResult testForDoublePrompt(OutputBasedMixedArithmeticInputGenerator anOutputBasedInputGenerator) {
//		if (anOutputBasedInputGenerator.foundDoublePrompt())
//			return pass();
//		else
//			return fail("Program does not contain prompt for double");
//	}
//	private TestCaseResult testForIntegerPrompt(String output) {
//		if (output.trim().toLowerCase().contains("int"))
//			return pass();
//		else
//			return fail("Program does not contain prompt for integer");
//	}
//
//	private TestCaseResult testForDoublePrompt(String output) {
//		if (output.toLowerCase().contains("decimal") || output.toLowerCase().contains("double"))
//			return pass();
//		else
//			return fail("Program does not contain prompt for double");
//	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {

			// Get the output when we have no input from the user
//			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			OutputBasedMixedArithmeticInputGenerator anOutputBasedInputGenerator = new ACollaborativeOutputBasedMixedArithmeticInputGenerator(1, 1.4);
			RunningProject interactiveInputProject = RunningProjectUtils.runProject(project, 1,
					anOutputBasedInputGenerator);
			String incOutput = interactiveInputProject.await();
			if (anOutputBasedInputGenerator.foundDoublePrompt() && anOutputBasedInputGenerator.foundIntPrompt())
				return pass();
			else
				return 	super.test(project, autoGrade);
//			String noInputPrompt = noInputRunningProject.await();
//
//			// Get the output when we have integer input from the user
//			RunningProject integerInputRunningProject = RunningProjectUtils.runProject(project, 1,
//					"1");
//			String integerInputPrompt = integerInputRunningProject.await();
//			integerInputPrompt = integerInputPrompt.substring(noInputPrompt.length());
//
//			// Get the output when we have double input from the user
//			RunningProject doubleInputRunningProject = RunningProjectUtils.runProject(project, 1,
//					"1.4");
//			String doubleInputPrompt = doubleInputRunningProject.await();
//			doubleInputPrompt = doubleInputPrompt.substring(noInputPrompt.length());

			// See if the initial prompt is an int or double prompt
//			boolean hasIntPrompt = testForIntegerPrompt(anOutputBasedInputGenerator).getPercentage() > 0;
//			boolean hasDoublePrompt = testForDoublePrompt(anOutputBasedInputGenerator).getPercentage() > 0;
////			boolean samePromptForBoth = hasIntPrompt && hasDoublePrompt;
//			String noInputPrompt = "";
//			String integerInputPrompt = "";
//			String doubleInputPrompt = "";
//			// If we have not seen prompts for ints or doubles, check if they
//			// show up after giving input
//			if (!hasIntPrompt || !hasDoublePrompt) {
//				// Get the output when we have no input from the user
//				RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
//				 noInputPrompt = noInputRunningProject.await();
//			}
//			
//			if (!hasIntPrompt) {
//				// Get the output when we have double input from the user
//				RunningProject doubleInputRunningProject = RunningProjectUtils.runProject(project, 1,
//						"1.4");
//				 doubleInputPrompt = doubleInputRunningProject.await();
//				doubleInputPrompt = doubleInputPrompt.substring(noInputPrompt.length());
//
//				hasIntPrompt = testForIntegerPrompt(doubleInputPrompt).getPercentage() > 0;
//			}
//			if (!hasDoublePrompt) {
//				// Get the output when we have integer input from the user
//				RunningProject integerInputRunningProject = RunningProjectUtils.runProject(project, 1,
//						"1");
//				 integerInputPrompt = integerInputRunningProject.await();
//				integerInputPrompt = integerInputPrompt.substring(noInputPrompt.length());
//				hasDoublePrompt = testForDoublePrompt(integerInputPrompt).getPercentage() > 0;
//
//			}
//			
//
//			// Create an error message based on our findings
//			String errorMessage = "";
//			double credit = 1.0;
//			if (!hasIntPrompt) {
//				errorMessage += "Program does not prompt for integer inputs\n";
//				credit = 0.5;
//			}
//			if (!hasDoublePrompt) {
//				errorMessage += "Program does not prompt for double inputs\n";
//				if (credit == 0.5) {
//					credit = 0;
//				} else {
//					credit = 0.5;
//				}
//			}
//			if (samePromptForBoth) {
//				errorMessage = "Program does not prompt separately for int and double inputs\n";
//				credit = 0.5;
//			}
//
//			if (credit == 1.0) {
//				return pass();
////			} else if ((noInputPrompt.length()) > 0
////					&& ((integerInputPrompt.length() > 0) || (doubleInputPrompt.length() > 0))) {
////				throw new NotAutomatableException();
////			} else {
//			} else if ((noInputPrompt.length() <= 0 && integerInputPrompt.length() <= 0 && doubleInputPrompt.length() <=0) ) {
//				throw new NotAutomatableException();
//			} else {
//				return partialPass(credit, errorMessage);
//			}
//		}
//
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
