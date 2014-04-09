package grader.trace.interaction_logger;

import grader.trace.file.SerializableFileInfo;

public class InteractionLogEntryAdded extends SerializableFileInfo {
	String entry;
	

	public InteractionLogEntryAdded(String aMessage, String aFileName, String anEntry,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	public static InteractionLogEntryAdded newCase(String aFileName, String anEntry,
			Object aFinder) {
		String aMessage =  "Interaction log entry added: " + anEntry + " to file:" + aFileName;
		InteractionLogEntryAdded retVal = new InteractionLogEntryAdded(aMessage, aFileName, anEntry, aFinder);
		retVal.announce();
		return retVal;
	}

}
