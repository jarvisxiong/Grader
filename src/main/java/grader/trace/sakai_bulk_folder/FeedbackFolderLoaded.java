package grader.trace.sakai_bulk_folder;

import grader.basics.trace.FileInfo;

public class FeedbackFolderLoaded extends FileInfo {

	public FeedbackFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FeedbackFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Feedback folder loaded: " + aFileName;
		FeedbackFolderLoaded retVal = new FeedbackFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
