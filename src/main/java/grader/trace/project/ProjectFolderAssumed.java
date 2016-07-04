package grader.trace.project;

import grader.basics.trace.FileInfo;

public class ProjectFolderAssumed extends FileInfo {

	public ProjectFolderAssumed(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static ProjectFolderAssumed newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Project folder assumed: " + aFileName;
		ProjectFolderAssumed retVal = new ProjectFolderAssumed(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
