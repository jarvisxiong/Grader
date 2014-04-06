package grader.trace.file.project;

import grader.trace.file.SerializableFileInfo;

public class BinaryFolderIdentified extends SerializableFileInfo {

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
