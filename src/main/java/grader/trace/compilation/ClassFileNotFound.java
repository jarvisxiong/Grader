package grader.trace.compilation;

import grader.trace.UncheckedGraderException;

public class ClassFileNotFound extends UncheckedGraderException {
	String fileName;
	
	
	public ClassFileNotFound(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public static ClassFileNotFound newCase(String aFileName, Object aFinder) {
		String aMessage = "Class  file not found:" + aFileName;
		ClassFileNotFound retVal = new ClassFileNotFound(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}


}
