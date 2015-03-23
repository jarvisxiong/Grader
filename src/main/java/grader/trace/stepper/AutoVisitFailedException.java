package grader.trace.stepper;

import grader.settings.GraderSettingsModel;
import grader.trace.UncheckedGraderException;

import java.io.IOException;

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
