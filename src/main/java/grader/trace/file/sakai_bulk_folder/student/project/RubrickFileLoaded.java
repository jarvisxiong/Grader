package grader.trace.file.sakai_bulk_folder.student.project;

import grader.trace.file.FileInfo;

public class RubrickFileLoaded extends FileInfo {

	public RubrickFileLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static RubrickFileLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Rubrick file loaded: " + aFileName;
		RubrickFileLoaded retVal = new RubrickFileLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
