package grader.trace;

import java.util.Date;

import util.trace.UncheckedTraceableException;
import util.trace.TraceableError;
import util.trace.TraceableInfo;

public class UncheckedGraderException extends UncheckedTraceableException {
	public UncheckedGraderException(String aMessage) {
		super(aMessage);
	}
	public UncheckedGraderException() {
		super();
	}
	public UncheckedGraderException(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}

	

}
