package grader.trace.compilation;

import grader.trace.UncheckedGraderException;

public class ClassFileCouldNotBeCompiled extends UncheckedGraderException {
	String fileName;
	
	
	public ClassFileCouldNotBeCompiled(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public static ClassFileCouldNotBeCompiled newCase(String aFileName, Object aFinder) {
		String aMessage = "Class  file could not be compiled correctlty:" + aFileName;
		ClassFileCouldNotBeCompiled retVal = new ClassFileCouldNotBeCompiled(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}


}
