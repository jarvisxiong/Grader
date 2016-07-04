package grader.trace.sakai_bulk_folder;

import grader.basics.trace.FileInfo;

public class SubmissionFolderLoaded extends FileInfo {

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
