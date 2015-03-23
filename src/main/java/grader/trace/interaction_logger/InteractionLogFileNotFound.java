package grader.trace.interaction_logger;

import grader.trace.file.SerializableFileInfo;

public class InteractionLogFileNotFound extends SerializableFileInfo {

	public InteractionLogFileNotFound(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static InteractionLogFileNotFound newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Interaction log file not found: " + aFileName;
		InteractionLogFileNotFound retVal = new InteractionLogFileNotFound(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
