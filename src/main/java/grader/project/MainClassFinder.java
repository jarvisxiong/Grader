package grader.project;

import framework.execution.NotRunnableException;
import grader.project.file.RootCodeFolder;

public interface MainClassFinder {
    public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, Project aProject);
    public String getEntryPoint(framework.project.Project project) throws NotRunnableException;


}
