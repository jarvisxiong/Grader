package grader.trace.sakai_bulk_folder;

import grader.trace.file.FileInfo;

public class AssignmentRootFolderLoaded extends FileInfo {

	public AssignmentRootFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static AssignmentRootFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Assignments root folder loaded: " + aFileName;
		AssignmentRootFolderLoaded retVal = new AssignmentRootFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
