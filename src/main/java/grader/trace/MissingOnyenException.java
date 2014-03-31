package grader.trace;

import grader.settings.GraderSettingsModel;

import java.io.IOException;

public class MissingOnyenException extends CheckedGraderException { // will be user error
	
	
	public MissingOnyenException (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static MissingOnyenException newCase(String aMessage, Object aFinder) {
		MissingOnyenException retVal = new MissingOnyenException(aMessage,  aFinder);
		retVal.announce();		
		return retVal;
	}

}
