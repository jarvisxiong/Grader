package grader.execution;

import grader.basics.execution.MainClassFinder;
import grader.project.flexible.FlexibleProject;
import grader.project.folder.RootCodeFolder;

public interface FlexibleMainClassFinder extends MainClassFinder{
	public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, FlexibleProject aProject);
//    public Map<String, String> getEntryPoints(framework.project.Project project, String aSpecifiedMainClass) throws NotRunnableException;


}
