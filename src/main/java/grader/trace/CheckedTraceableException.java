package grader.trace;

import java.util.Date;

import util.trace.TraceableError;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class CheckedTraceableException extends TraceableError {

	public CheckedTraceableException(String aMessage, Object aFinder) {
		super("", CheckedTraceableException.class);
	}
	public CheckedTraceableException(String aMessage) {
		this(aMessage, null);
	}
	public CheckedTraceableException() {
		this("", null);
	}
	
	protected
	void maybePrintMessage(String aMessage, boolean isDuplicate) {
		// do not print as the catcher will probably do so

//		if (!isDuplicate) {
//			Tracer.error(aMessage);
//
//		}
	}

	

}
