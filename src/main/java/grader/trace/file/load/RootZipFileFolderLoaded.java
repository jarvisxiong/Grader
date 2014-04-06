package grader.trace.file.load;

import grader.trace.file.SerializableFileInfo;

public class RootZipFileFolderLoaded extends SerializableFileInfo {

	public RootZipFileFolderLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static RootZipFileFolderLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Root zip file folder loaded: " + aFileName;
		RootZipFileFolderLoaded retVal = new RootZipFileFolderLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
