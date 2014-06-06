package grader.execution;

import util.trace.TraceableError;

public class TagNotFound extends TraceableError{

	public TagNotFound(String aMessage, String aTag, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public static TagNotFound newCase (String aTag, Object aFinder) {
		String aMessage = "Entry point not found";
		TagNotFound retVal = new TagNotFound(aMessage, aTag, aFinder);
		retVal.announce();
		return retVal;
	}

}
