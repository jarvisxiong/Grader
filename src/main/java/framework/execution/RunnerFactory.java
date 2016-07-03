package framework.execution;

import grader.basics.execution.Runner;
import grader.basics.project.Project;

public interface RunnerFactory {
    public Runner createProcessRunner(Project aProject, String aSpecifiedProxyMainClass);
}
