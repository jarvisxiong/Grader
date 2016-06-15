package gradingTools.comp110f14.assignment3testcases;

import framework.execution.BasicRunningProject;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class SharedOutput {

	String input;
	int timeout;
	
	Project project;
	String output;
	
	public SharedOutput(String input, int timeout) {
		this.input = input;
		this.timeout = timeout;
	}
	
	public synchronized String getOutput(Project project) {
		if (this.project != project) {
			BasicRunningProject runningProject = RunningProjectUtils.runProject(project, timeout, input);
			output = runningProject.await();
			this.project = project;
		}
		return output;
	}

}
