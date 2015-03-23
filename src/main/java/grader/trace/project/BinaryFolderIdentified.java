package grader.trace.project;

import grader.trace.file.FileInfo;

public class BinaryFolderIdentified extends FileInfo {

	public BinaryFolderIdentified(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static BinaryFolderIdentified newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Binary folder identified: " + aFileName;
		BinaryFolderIdentified retVal = new BinaryFolderIdentified(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
