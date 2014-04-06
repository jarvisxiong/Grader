package grader.trace.file.assignment;

import grader.trace.file.SerializableFileInfo;

public class AssignmentDataFolderLoaded extends SerializableFileInfo {

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
