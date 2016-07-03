package grader.execution;

import grader.basics.execution.MainClassFinder;
import grader.basics.execution.NotRunnableException;
import grader.basics.project.BasicProject;
import grader.project.flexible.FlexibleProject;
import grader.project.folder.RootCodeFolder;

import java.util.Map;

public interface FlexibleMainClassFinder extends MainClassFinder{
	public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, FlexibleProject aProject);
//    public Map<String, String> getEntryPoints(framework.project.Project project, String aSpecifiedMainClass) throws NotRunnableException;


}
