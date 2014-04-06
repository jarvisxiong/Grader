package grader.trace.file.sakai_bulk_folder;

import grader.trace.file.SerializableFileInfo;

public class FinalGradeFileLoaded extends SerializableFileInfo {

	public FinalGradeFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FinalGradeFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Final grade file loaded: " + aFileName;
		FinalGradeFileLoaded retVal = new FinalGradeFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
