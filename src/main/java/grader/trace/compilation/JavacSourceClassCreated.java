package grader.trace.compilation;

import grader.trace.file.FileInfo;

public class JavacSourceClassCreated extends FileInfo {

	public JavacSourceClassCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static JavacSourceClassCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Javac source class created: " + aFileName;
		JavacSourceClassCreated retVal = new JavacSourceClassCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
