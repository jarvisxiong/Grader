package grader.trace.file.compilation;

import grader.trace.file.SerializableFileInfo;

public class QDoxClassCreated extends SerializableFileInfo {

	public QDoxClassCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static QDoxClassCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "QDox class created: " + aFileName;
		QDoxClassCreated retVal = new QDoxClassCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
