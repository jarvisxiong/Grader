package grader.trace.settings;

import grader.basics.trace.UncheckedGraderException;

public class InvalidOnyenRangeException extends UncheckedGraderException {

	public InvalidOnyenRangeException(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	} 
	
	public static InvalidOnyenRangeException newCase(String aMessage, Object aFinder) {
		InvalidOnyenRangeException retVal = new InvalidOnyenRangeException(aMessage,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
