package grader.trace.file.sakai_bulk_folder.student;

import grader.trace.file.SerializableFileInfo;

public class SubmissionFolderLoaded extends SerializableFileInfo {

	public SubmissionFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static SubmissionFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Submission folder loaded: " + aFileName;
		SubmissionFolderLoaded retVal = new SubmissionFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
