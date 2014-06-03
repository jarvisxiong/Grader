package grader.project;

import util.trace.TraceableError;

public class NoClassWithTag extends TraceableError{
	String classTag;

	public NoClassWithTag(String aMessage, String aClassTag, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public String getClassTag() {
		return classTag;
	}
	
	public static NoClassWithTag newCase (Object aFinder, String aClassTag) {
		String aMessage = "No class found with tag:" + aClassTag;
		NoClassWithTag retVal = new NoClassWithTag(aMessage, aClassTag, aFinder);
		retVal.announce();
		return retVal;
		
	}

}
