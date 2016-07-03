package framework.execution;

import grader.basics.execution.BasicProcessRunner;
import grader.basics.execution.Runner;
import grader.basics.project.Project;

public class BasicProcessRunnerFactory implements RunnerFactory{

	@Override
	public Runner createProcessRunner(Project aProject,
			String aSpecifiedProxyMainClass) {
		return new BasicProcessRunner(aProject, aSpecifiedProxyMainClass);
	}

}
