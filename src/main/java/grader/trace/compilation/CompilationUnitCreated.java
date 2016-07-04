package grader.trace.compilation;

import grader.basics.trace.FileInfo;

public class CompilationUnitCreated extends FileInfo {

	public CompilationUnitCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static CompilationUnitCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Compilation unit created: " + aFileName;
		CompilationUnitCreated retVal = new CompilationUnitCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
