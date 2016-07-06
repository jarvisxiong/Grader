package grader.trace.interaction_logger;

import grader.basics.trace.SerializableFileInfo;

public class InteractionLogFileCreatedOrLoaded extends SerializableFileInfo {

	public InteractionLogFileCreatedOrLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static InteractionLogFileCreatedOrLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Interaction log data folder created or opened for append: " + aFileName;
		InteractionLogFileCreatedOrLoaded retVal = new InteractionLogFileCreatedOrLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
