package grader.trace.project;

import grader.trace.UncheckedGraderException;

public class ProjectFolderNotFound extends UncheckedGraderException {
	String fileName;
	
	public ProjectFolderNotFound(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	
	public static ProjectFolderNotFound newCase(String aFileName, Object aFinder) {
		String aMessage = "Project folder not found in submission folder:" + aFileName;
		ProjectFolderNotFound retVal = new ProjectFolderNotFound(aMessage, aFileName, aFinder);
		return retVal;
	}


}
