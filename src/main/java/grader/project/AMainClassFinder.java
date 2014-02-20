package grader.project;


import framework.execution.NotRunnableException;
import grader.file.FileProxy;
import grader.project.file.RootCodeFolder;
import util.misc.Common;

import java.util.List;

public class AMainClassFinder implements MainClassFinder {
    public static final String DEFAULT_MAIN_PACKAGE_NAME = "main";
    private String getEntryPoint(ProxyClassLoader aLoader, Project project) throws NotRunnableException {
		if (project.getClassesManager() == null)
			throw new NotRunnableException();

		ClassesManager manager = project.getClassesManager();
		for (ClassDescription description : manager.getClassDescriptions()) {
			try {
				description.getJavaClass().getMethod("main", String[].class);
				return description.getJavaClass().getCanonicalName();
			} catch (NoSuchMethodException e) {
				// Move along
			}
		}
		throw new NotRunnableException();
	}
    
    public Class nonPackagedMainClass ( ProxyClassLoader aProxyClassLoader, Project aProject) {
    	try {
			return  aProxyClassLoader.loadClass(getEntryPoint(aProxyClassLoader, aProject));
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
			return null;
		} catch (NotRunnableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
    }
    

    public Class mainClass(RootCodeFolder aRootCodeFolder, ProxyClassLoader aProxyClassLoader, String expectedName, Project aProject) {
        
    	
    	String binaryFolderName = aRootCodeFolder.getBinaryProjectFolderName();
        String mainFolderName = binaryFolderName + "/" + DEFAULT_MAIN_PACKAGE_NAME;
        List<FileProxy> mainBinaryChildren = aRootCodeFolder.getRootFolder().getChildrenOf(
                mainFolderName);
        if (mainBinaryChildren.size() != 1) {
        	return  nonPackagedMainClass(aProxyClassLoader, aProject);
        }

        else if (mainBinaryChildren.size() == 1) {
            String mainClassAbsoluteFileName = mainBinaryChildren.get(0).getMixedCaseAbsoluteName();
            String classFileName = Common.absoluteNameToLocalName(mainClassAbsoluteFileName);
            int dotIndex = classFileName.indexOf('.');
            String className = classFileName.substring(0, dotIndex);

            String mainClassFileName = DEFAULT_MAIN_PACKAGE_NAME + "." + className;
            try {
                return aProxyClassLoader.loadClass(mainClassFileName);
            } catch (ClassNotFoundException e) {
            	// not sure of this makes sense
            	return  nonPackagedMainClass(aProxyClassLoader, aProject);
//                try {
//					return  aProxyClassLoader.loadClass(getEntryPoint(aProject));
//				} catch (ClassNotFoundException e1) {
//					
//					e1.printStackTrace();
//					return null;
//				} catch (NotRunnableException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					return null;
//				}
            }
        }

        return null;
    }

}
