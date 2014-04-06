package grader.trace.file.load;

import grader.trace.file.SerializableFileInfo;

public class FileUnzipped extends SerializableFileInfo {

	public FileUnzipped(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FileUnzipped newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Root zip file folder loaded: " + aFileName;
		FileUnzipped retVal = new FileUnzipped(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
