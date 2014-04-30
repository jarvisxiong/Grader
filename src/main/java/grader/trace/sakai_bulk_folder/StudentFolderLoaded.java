package grader.trace.sakai_bulk_folder;

import grader.trace.file.FileInfo;

public class StudentFolderLoaded extends FileInfo {

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
