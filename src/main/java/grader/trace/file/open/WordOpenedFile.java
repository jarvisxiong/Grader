package grader.trace.file.open;

import grader.basics.trace.SerializableFileInfo;

public class WordOpenedFile extends SerializableFileInfo {

	public WordOpenedFile(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static void newCase(String aFileName,
			Object aFinder) {
		String aMessage = aFileName + " opened using Word";
		WordOpenedFile retVal = new WordOpenedFile(aMessage, aFileName, aFinder);
		retVal.announce();
	}

}
