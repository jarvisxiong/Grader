package gradingTools.sharedTestCase;

import framework.execution.BasicRunningProject;
import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public abstract class OutputMatcher extends BasicTestCase {

	public OutputMatcher(String description) {
		super(description);
	}
	
	/**
	 * Returns true if the output for the given input contains the required parts and does not include the missing parts
	 * @param project The project to run
	 * @param prompt The prompt that the program gives before the user types in the given input
	 * @param input The input the user types in
	 * @param requiredParts The parts that the output should contain
	 * @param missingParts The parts that the output should not contain
	 * @return true if all required parts in output and all missing points not in output
	 * 	otherwise false
	 * @throws NotRunnableException
	 * @throws NotAutomatableException
	 */
	protected boolean resultOfInputMatches(Project project, String prompt, String input,
			String[] requiredParts, String[] missingParts) throws NotRunnableException,
			NotAutomatableException {

		BasicRunningProject runningProjectWithInput = RunningProjectUtils.runProject(project, 1, input);
		String output = runningProjectWithInput.await();

		if (!output.startsWith(prompt)) {
			throw new NotAutomatableException();
		}

		for (String requiredPart : requiredParts) {
			if (!output.substring(prompt.length()).toLowerCase()
					.contains(requiredPart.toLowerCase())) {
				return false;
			}
		}

		for (String missingPart : missingParts) {
			if (output.substring(prompt.length()).toLowerCase().contains(missingPart.toLowerCase())) {
				return false;
			}
		}

		return true;
	}
	
}
