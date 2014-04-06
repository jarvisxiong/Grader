package grader.trace.file.sakai_bulk_folder;

import grader.trace.CheckedGraderException;

public class FinalGradeFileNotFound extends CheckedGraderException {
	String fileName;
	
	public FinalGradeFileNotFound(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	
	public static FinalGradeFileNotFound newCase(String aFileName, Object aFinder) {
		String aMessage = "Final Grade file not found:" + aFileName;
		FinalGradeFileNotFound retVal = new FinalGradeFileNotFound(aMessage, aFileName, aFinder);
		return retVal;
	}


}
