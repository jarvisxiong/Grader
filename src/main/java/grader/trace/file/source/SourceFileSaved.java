package grader.trace.file.source;

import grader.trace.file.FileInfo;
import grader.trace.project.SourceFolderIdentified;

public class SourceFileSaved extends SourceFileInfo {

	public SourceFileSaved(String aMessage, String aFileName, String aText,
			Object aFinder) {
		super(aMessage, aFileName, aText, aFinder);
	}

	public static SourceFileSaved newCase(String aFileName, String aText,
			Object aFinder) {
		String aMessage =  "Source file: " + aFileName + " saved with stored text: " + aText;
		SourceFileSaved retVal = new SourceFileSaved(aMessage, aFileName, aText, aFinder);
		retVal.announce();
		return retVal;
	}

	
}
