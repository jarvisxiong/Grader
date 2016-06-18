package framework.execution;

import framework.project.Project;

public interface RunnerFactory {
    public Runner createProcessRunner(Project aProject, String aSpecifiedProxyMainClass);
}
