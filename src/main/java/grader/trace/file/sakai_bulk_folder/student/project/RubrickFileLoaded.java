package grader.trace.file.sakai_bulk_folder.student.project;

import grader.trace.file.SerializableFileInfo;

public class RubrickFileLoaded extends SerializableFileInfo {

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
