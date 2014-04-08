package grader.trace.compilation;

import grader.trace.CheckedGraderException;

public class SourceFileCompiled extends CheckedGraderException {
	String fileName;
	
	public SourceFileCompiled(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	
	public static SourceFileCompiled newCase(String aFileName, Object aFinder) {
		String aMessage = "Source  file compiled:" + aFileName;
		SourceFileCompiled retVal = new SourceFileCompiled(aMessage, aFileName, aFinder);
		return retVal;
	}


}
