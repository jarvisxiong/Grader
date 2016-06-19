package grader.execution;

import framework.execution.NotRunnableException;
import framework.project.BasicProject;
import grader.project.Project;
import grader.project.folder.RootCodeFolder;

import java.util.Map;

public interface MainClassFinder {
//	public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, Project aProject);
    public Map<String, String> getEntryPoints(framework.project.Project project, String aSpecifiedMainClass) throws NotRunnableException;


}
