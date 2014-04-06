package grader.trace.file.compilation;

import grader.trace.CheckedGraderException;

public class ClassFileNotFound extends CheckedGraderException {
	String fileName;
	
	public ClassFileNotFound(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	
	public static ClassFileNotFound newCase(String aFileName, Object aFinder) {
		String aMessage = "Class  file not found:" + aFileName;
		ClassFileNotFound retVal = new ClassFileNotFound(aMessage, aFileName, aFinder);
		return retVal;
	}


}
