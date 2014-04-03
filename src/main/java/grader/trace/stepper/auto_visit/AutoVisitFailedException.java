package grader.trace.stepper.auto_visit;

import grader.settings.GraderSettingsModel;
import grader.trace.CheckedGraderException;

import java.io.IOException;

public class AutoVisitFailedException extends CheckedGraderException { // will be user error
	
	
	public AutoVisitFailedException (String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static AutoVisitFailedException newCase(String aMessage, Object aFinder) {
		AutoVisitFailedException retVal = new AutoVisitFailedException(aMessage,  aFinder);
		retVal.announce();		
		return retVal;
	}

}
