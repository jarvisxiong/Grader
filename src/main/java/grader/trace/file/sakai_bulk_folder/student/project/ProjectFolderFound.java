package grader.trace.file.sakai_bulk_folder.student.project;

import grader.trace.file.SerializableFileInfo;

public class ProjectFolderFound extends SerializableFileInfo {

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
