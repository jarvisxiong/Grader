package grader.trace.sakai_bulk_folder;

import grader.trace.file.FileInfo;

public class CommentsFileLoaded extends FileInfo {

	public CommentsFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static CommentsFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Comments file loaded: " + aFileName;
		CommentsFileLoaded retVal = new CommentsFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
