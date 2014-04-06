package grader.trace.file.sakai_bulk_folder;

import grader.trace.file.SerializableFileInfo;

public class StudentFolderLoaded extends SerializableFileInfo {

	public StudentFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static StudentFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Student folder loaded: " + aFileName;
		StudentFolderLoaded retVal = new StudentFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
