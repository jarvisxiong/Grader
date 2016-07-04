package grader.trace.compilation;

import grader.basics.trace.FileInfo;

public class QDoxClassCreated extends FileInfo {

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
