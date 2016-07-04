package grader.trace.checkStyle;

import grader.basics.trace.FileInfo;

public class CheckStyleFileLoaded extends FileInfo {

	public CheckStyleFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static CheckStyleFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "CheckStyle file loaded: " + aFileName;
		CheckStyleFileLoaded retVal = new CheckStyleFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
