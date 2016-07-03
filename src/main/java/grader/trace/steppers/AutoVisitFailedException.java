package grader.trace.steppers;

import grader.basics.trace.UncheckedGraderException;

public class AutoVisitFailedException extends UncheckedGraderException { // will be user error
	
	
	public AutoVisitFailedException (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static AutoVisitFailedException newCase(String aMessage, Object aFinder) {
		AutoVisitFailedException retVal = new AutoVisitFailedException(aMessage,  aFinder);
		retVal.announce();		
		return retVal;
	}

}
