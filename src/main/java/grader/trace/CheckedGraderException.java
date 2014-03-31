package grader.trace;

import java.util.Date;

import util.trace.TraceableError;
import util.trace.TraceableInfo;

public class CheckedGraderException extends CheckedTraceableException {

	public CheckedGraderException(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}

	

}
