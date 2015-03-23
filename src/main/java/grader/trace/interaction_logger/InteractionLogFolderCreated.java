package grader.trace.interaction_logger;

import grader.trace.file.SerializableFileInfo;

public class InteractionLogFolderCreated extends SerializableFileInfo {

	public InteractionLogFolderCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static InteractionLogFolderCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Interaction log data folder created: " + aFileName;
		InteractionLogFolderCreated retVal = new InteractionLogFolderCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
