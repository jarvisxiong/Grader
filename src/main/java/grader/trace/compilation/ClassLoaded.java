package grader.trace.compilation;

import grader.trace.file.FileInfo;
import grader.trace.file.SerializableFileInfo;

public class ClassLoaded extends FileInfo {

	public ClassLoaded(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static ClassLoaded newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Class  loaded: " + aFileName;
		ClassLoaded retVal = new ClassLoaded(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
