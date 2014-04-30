package grader.trace.file.open;

import grader.trace.file.SerializableFileInfo;

public class DefaultProgramOpenedFile extends SerializableFileInfo {

	public DefaultProgramOpenedFile(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static void newCase(String aFileName,
			Object aFinder) {
		String aMessage = aFileName + " opened using default program";
		DefaultProgramOpenedFile retVal = new DefaultProgramOpenedFile(aMessage, aFileName, aFinder);
		retVal.announce();
	}

}
