package grader.trace.logger;

import grader.trace.file.SerializableFileInfo;

public class InteractionLogFileCreatedOrLoaded extends SerializableFileInfo {

	public InteractionLogFileCreatedOrLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static InteractionLogFileCreatedOrLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Interaction log data folder created: " + aFileName;
		InteractionLogFileCreatedOrLoaded retVal = new InteractionLogFileCreatedOrLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
