package grader.trace.file.load;

import grader.trace.file.SerializableFileInfo;

public class ZipFileUnzipped extends SerializableFileInfo {

	public ZipFileUnzipped(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static ZipFileUnzipped newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Root zip file folder loaded: " + aFileName;
		ZipFileUnzipped retVal = new ZipFileUnzipped(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
