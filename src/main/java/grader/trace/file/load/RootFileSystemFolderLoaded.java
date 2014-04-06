package grader.trace.file.load;

import grader.trace.file.SerializableFileInfo;

public class RootFileSystemFolderLoaded extends SerializableFileInfo {

	public RootFileSystemFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static RootFileSystemFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Root folder loaded: " + aFileName;
		RootFileSystemFolderLoaded retVal = new RootFileSystemFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
