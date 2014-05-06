package grader.project;


import framework.execution.NotRunnableException;
//import framework.project.ClassDescription;
//import framework.project.ClassesManager;
//import framework.project.Project;
import grader.file.FileProxy;
import grader.project.file.RootCodeFolder;
import grader.sakai.project.SakaiProject;
import util.misc.Common;
import wrappers.framework.project.ProjectWrapper;

import java.util.List;

public class AnExecutableFinder implements MainClassFinder {
	public static String EXECUTABLE_SUFFIX = ".exe";
    public AnExecutableFinder() {
    	
    }
    

    public String getEntryPoint(framework.project.Project frameworkProject) throws NotRunnableException {
        
    	if (frameworkProject instanceof ProjectWrapper) {
    		ProjectWrapper projectWrapper = (ProjectWrapper) frameworkProject;
    		SakaiProject sakaiProject = projectWrapper.getProject();
    		RootCodeFolder aRootCodeFolder = sakaiProject.getRootCodeFolder();
    		String binaryFolderName = aRootCodeFolder.getBinaryProjectFolderName();
            List<FileProxy> binaryChildren = aRootCodeFolder.getRootFolder().getChildrenOf(
            		binaryFolderName);
            for ( FileProxy child:binaryChildren) {
            	if (child.getMixedCaseLocalName().endsWith(EXECUTABLE_SUFFIX))
            		return child.getMixedCaseLocalName();
            	
            }
            
    	}
    	return null;
    }


	@Override
	public Class mainClass(RootCodeFolder aRootCodeFolder,
			ProxyClassLoader aProxyClassLoader, String expectedName,
			Project aProject) {
		return null;
	}
    
    

}
