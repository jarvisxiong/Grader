package gradingTools.comp110.assignment4.testCases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public abstract class TestGerbilInputWithCommand extends BasicTestCase{
	
	private static final String[] SYSTEM_SETUP_COMMANDS = { "5", "Cheese", "20", "Fava Beans", "50", "Bacon", "50", "Gerbil Food",
			"60", "Gerbil", "120", "3", "SB329", "Hannibal", "0", "50", "0", "0", "120", "True",
			"True", "SB2", "Malcom", "20", "25", "30", "20", "1", "False", "False", "SB1X3",
			"Prince Firstly", "19", "25", "25", "30", "102", "False", "True" };

	private final String command;
	
	public TestGerbilInputWithCommand(String command) {
		super("Test user entering "+command+" command");
		this.command = command;
	}
	
	private String getSetupInput() {
		String retVal = "";
		for (String setupCommand : SYSTEM_SETUP_COMMANDS) {
			if (retVal.length() > 0) {
				retVal += "\n";
			}
			retVal += setupCommand;
		}
		return retVal;
	}
	
	protected abstract TestCaseResult checkResult(String result);

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		String setupInput = getSetupInput();
		RunningProject runningProject = RunningProjectUtils.runProject(project, 1, setupInput);
		String prompt = runningProject.await();
		if (prompt.endsWith("\n")) {
			prompt = prompt.substring(0, prompt.length() - 1);
		}
		
		runningProject = RunningProjectUtils.runProject(project, 1, setupInput + "\n" + command);
		String result = runningProject.await();
		if (!result.startsWith(prompt)) {
			throw new NotAutomatableException();
		}
		result = result.substring(prompt.length());
		
		return checkResult(result);
	}

}
