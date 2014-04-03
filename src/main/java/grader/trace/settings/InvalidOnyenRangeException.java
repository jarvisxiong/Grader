package grader.trace.settings;

import grader.trace.CheckedGraderException;

import java.io.IOException;

public class InvalidOnyenRangeException extends CheckedGraderException {

	public InvalidOnyenRangeException(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	} 
	
	public static InvalidOnyenRangeException newCase(String aMessage, Object aFinder) {
		InvalidOnyenRangeException retVal = new InvalidOnyenRangeException(aMessage,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
