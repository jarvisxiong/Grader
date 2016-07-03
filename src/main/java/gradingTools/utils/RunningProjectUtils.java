package gradingTools.utils;

import java.util.Map;

import util.pipe.InputGenerator;
import framework.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;

/*
 * Class that contains various static helper methods for running projects and processing their output
 */
public class RunningProjectUtils {

	/*
	 * Convenience method for running projects where inputs are separated by the
	 * newline character
	 */
	public static RunningProject runProject(Project project, int timeout, String... inputs)
			throws NotRunnableException {
		return runProject(project, DEFAULT_INPUT_SEPARATOR, timeout, inputs);
	}
	
	public static final String DEFAULT_INPUT_SEPARATOR = "\n";
	
	public static RunningProject runProject(Project project, int timeout, InputGenerator anOutputBasedInputGenerator,
			String... inputs)
			throws NotRunnableException {
		return runProject(project, DEFAULT_INPUT_SEPARATOR, timeout, anOutputBasedInputGenerator, inputs);
	}
	public static RunningProject runProject(Project project, String inputSeparator, int timeout, String... inputs) throws NotRunnableException {
		return runProject(project, timeout, null, inputs);
	}
	
	public static String toString(String inputSeparator, String... inputs) {
		String allInputsStr = "";
		for (int i = 0; i < inputs.length; i++) {
			if (i > 0) {
				allInputsStr += inputSeparator;
			}
			allInputsStr += inputs[i];
		}
		return allInputsStr;
	}
	
	public static String toInputString(String... inputs) {
		return toString(DEFAULT_INPUT_SEPARATOR, inputs);
	}

	/*
	 * Runs the project with the inputs separated by the given separator string
	 * 
	 * The running project also timesout based on the given timeout
	 */
	public static RunningProject runProject(Project project, String inputSeparator, int timeout, InputGenerator anOutputBasedInputGenerator, String... inputs) throws NotRunnableException {
//		String allInputsStr = "";
//		for (int i = 0; i < inputs.length; i++) {
//			if (i > 0) {
//				allInputsStr += inputSeparator;
//			}
//			allInputsStr += inputs[i];
//		}
		return project.launch(anOutputBasedInputGenerator, toString(inputSeparator, inputs), timeout);
	}
	public static RunningProject runProject(Project project,  int timeout, InputGenerator anOutputBasedInputGenerator, Map<String, String> aProcessToInput) throws NotRunnableException {
//		String allInputsStr = "";
//		for (int i = 0; i < inputs.length; i++) {
//			if (i > 0) {
//				allInputsStr += inputSeparator;
//			}
//			allInputsStr += inputs[i];
//		}
		return project.launch(anOutputBasedInputGenerator, aProcessToInput, timeout);
	}
	public static RunningProject runProject(Project project, int timeout,  Map<String, String> aProcessToInput) throws NotRunnableException {
//		String allInputsStr = "";
//		for (int i = 0; i < inputs.length; i++) {
//			if (i > 0) {
//				allInputsStr += inputSeparator;
//			}
//			allInputsStr += inputs[i];
//		}
		return project.launch(null, aProcessToInput, timeout);
	}

}
