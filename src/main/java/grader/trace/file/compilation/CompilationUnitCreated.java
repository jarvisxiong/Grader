package grader.trace.file.compilation;

import grader.trace.file.SerializableFileInfo;

public class CompilationUnitCreated extends SerializableFileInfo {

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
