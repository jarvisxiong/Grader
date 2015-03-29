package grader.execution;


import framework.execution.NotRunnableException;
//import framework.project.ClassDescription;
//import framework.project.ClassesManager;
//import framework.project.Project;
import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.project.Project;
import grader.project.folder.RootCodeFolder;
import grader.sakai.project.SakaiProject;
import util.misc.Common;
import wrappers.framework.project.ProjectWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnExecutableFinder implements MainClassFinder {
	public static String EXECUTABLE_SUFFIX = ".exe";
    public AnExecutableFinder() {
    	
    }
    

    public Map<String, String> getEntryPoints(framework.project.Project frameworkProject) throws NotRunnableException {
        try {
    	File binaryFolder = frameworkProject.getBuildFolder("");
    	File[] binaryChildren =  binaryFolder.listFiles();
//        List<FileProxy> binaryChildren =  binaryFolder.getC
    	Map<String, String> retVal = new HashMap();
        for ( File child:binaryChildren) {
        	if (child.getName().endsWith(EXECUTABLE_SUFFIX)) {
        		retVal.put(MAIN_ENTRY_POINT, child.getName());
        		return retVal;
//        		return child.getName();
        	}
        	
        }
        return retVal;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    	
//    	if (frameworkProject instanceof ProjectWrapper) {
//    		ProjectWrapper projectWrapper = (ProjectWrapper) frameworkProject;
//    		SakaiProject sakaiProject = projectWrapper.getProject();
//    		
//    		RootCodeFolder aRootCodeFolder = sakaiProject.getRootCodeFolder();
//    		RootFolderProxy binaryFolder = aRootCodeFolder.getBinaryFolder();
////    		String binaryFolderName = aRootCodeFolder.getBinaryProjectFolderName();
//            List<FileProxy> binaryChildren =  aRootCodeFolder.getRootFolder().getChildrenOf(
//            		binaryFolder.getAbsoluteName());
////            List<FileProxy> binaryChildren =  binaryFolder.getC
//            for ( FileProxy child:binaryChildren) {
//            	if (child.getMixedCaseLocalName().endsWith(EXECUTABLE_SUFFIX)) {
//            		String localName = child.getParentRelativeMixedCaseName();
//            		return localName;
//            	}
//            	
//            }
//            
//    	}
    }


	@Override
	public Class mainClass(RootCodeFolder aRootCodeFolder,
			ProxyClassLoader aProxyClassLoader, String expectedName,
			Project aProject) {
		return null;
	}
    
    

}
