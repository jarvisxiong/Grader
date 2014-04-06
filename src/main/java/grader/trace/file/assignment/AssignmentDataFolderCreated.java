package grader.trace.file.assignment;

import grader.trace.file.FileInfo;
import grader.trace.file.SerializableFileInfo;

public class AssignmentDataFolderCreated extends FileInfo {

	public AssignmentDataFolderCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static AssignmentDataFolderCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Assignments data folder created: " + aFileName;
		AssignmentDataFolderCreated retVal = new AssignmentDataFolderCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
