package grader.trace.sakai_bulk_folder;

import grader.trace.file.FileInfo;

public class TimestampFileLoaded extends FileInfo {

	public TimestampFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static TimestampFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Timestamp file loaded: " + aFileName;
		TimestampFileLoaded retVal = new TimestampFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
