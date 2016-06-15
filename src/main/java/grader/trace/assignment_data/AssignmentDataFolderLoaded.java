package grader.trace.assignment_data;

import grader.trace.file.FileInfo;

public class AssignmentDataFolderLoaded extends FileInfo {

	public AssignmentDataFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static AssignmentDataFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Assignments data folder loaded: " + aFileName;
		AssignmentDataFolderLoaded retVal = new AssignmentDataFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
