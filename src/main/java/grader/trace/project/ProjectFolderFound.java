package grader.trace.project;

import grader.basics.trace.FileInfo;

public class ProjectFolderFound extends FileInfo {

	public ProjectFolderFound(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static ProjectFolderFound newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Project folder not found: " + aFileName;
		ProjectFolderFound retVal = new ProjectFolderFound(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
