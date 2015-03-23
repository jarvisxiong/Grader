package grader.execution;


import framework.execution.NotRunnableException;
//import framework.project.ClassDescription;
//import framework.project.ClassesManager;
//import framework.project.Project;
import grader.file.FileProxy;
import grader.project.ClassDescription;
import grader.project.ClassesManager;
import grader.project.Project;
import grader.project.file.RootCodeFolder;
import util.misc.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AMainClassFinder implements MainClassFinder {
    public static final String DEFAULT_MAIN_PACKAGE_NAME = "main";
    
    private List<String> getEntryPoints(ProxyClassLoader aLoader, Project project) throws NotRunnableException {
		if (project.getClassesManager() == null)
			throw new NotRunnableException();
		List<String> entryPoints = new ArrayList();

		ClassesManager manager = project.getClassesManager();
		for (ClassDescription description : manager.getClassDescriptions()) {
			try {
				description.getJavaClass().getMethod("main", String[].class);
				entryPoints.add(description.getJavaClass().getCanonicalName());
				return entryPoints;
//				return description.getJavaClass().getCanonicalName();
//				return description.getJavaClass().getCanonicalName();

			} catch (NoSuchMethodException e) {
				// Move along
			}
		}
		throw new NotRunnableException();
	}
    /**
     * This figures out what class is the "entry point", or, what class has main(args)
     * @param project The project to run
     * @return The class canonical name. i.e. "foo.bar.SomeClass"
     * @throws framework.execution.NotRunnableException
     * @see grader.execution.AMainClassFinder which repeats this code (sigh)
     * Both need to be kept consistent
     */
    public Map<String, String> getEntryPoints(framework.project.Project project) throws NotRunnableException {
        if (project.getClassesManager().isEmpty())
            throw new NotRunnableException();
		Map<String, String> entryPoints = new HashMap();

        framework.project.ClassesManager manager = project.getClassesManager().get();
        for (framework.project.ClassDescription description : manager.getClassDescriptions()) {
            try {
                description.getJavaClass().getMethod("main", String[].class);
                entryPoints.put(MAIN_ENTRY_POINT, description.getJavaClass().getCanonicalName());
                return entryPoints;
//                return description.getJavaClass().getCanonicalName();
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NotRunnableException();
    }
    
    public Class nonPackagedMainClass ( ProxyClassLoader aProxyClassLoader, Project aProject) {
    	try {
			return  aProxyClassLoader.loadClass(getEntryPoints(aProxyClassLoader, aProject).get(0));
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
