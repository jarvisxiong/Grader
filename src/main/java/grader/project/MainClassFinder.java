package grader.project;

import java.util.List;

import framework.execution.NotRunnableException;
import grader.project.file.RootCodeFolder;

public interface MainClassFinder {
    public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, Project aProject);
    public List<String> getEntryPoints(framework.project.Project project) throws NotRunnableException;


}
