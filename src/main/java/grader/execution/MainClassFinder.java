package grader.execution;

import java.util.List;
import java.util.Map;

import framework.execution.NotRunnableException;
import grader.project.Project;
import grader.project.file.RootCodeFolder;

public interface MainClassFinder {
	public static final String MAIN_ENTRY_POINT = "main";

    public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, Project aProject);
    public Map<String, String> getEntryPoints(framework.project.Project project) throws NotRunnableException;


}
