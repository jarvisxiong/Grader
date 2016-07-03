package framework.execution;

import grader.basics.execution.Runner;
import grader.basics.execution.RunnerFactory;
import grader.basics.project.Project;

public class ProcessRunnerFactory implements RunnerFactory{

	@Override
	public Runner createProcessRunner(Project aProject,
			String aSpecifiedProxyMainClass) {
		return new ProcessRunner(aProject, aSpecifiedProxyMainClass);
	}

}
