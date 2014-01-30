package gradingTools.utils;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.project.Project;

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
		return runProject(project, "\n", timeout, inputs);
	}

	/*
	 * Runs the project with the inputs separated by the given separator string
	 * 
	 * The running project also timesout based on the given timeout
	 */
	public static RunningProject runProject(Project project, String inputSeparator, int timeout,
			String... inputs) throws NotRunnableException {
		String allInputsStr = "";
		for (int i = 0; i < inputs.length; i++) {
			if (i > 0) {
				allInputsStr += inputSeparator;
			}
			allInputsStr += inputs[i];
		}
		return project.launch(allInputsStr, timeout);
	}

}
