package grader.project.flexible;

import util.trace.TraceableError;

public class NoClassWithTag extends TraceableError{
	Object classTag;

	public NoClassWithTag(String aMessage, Object aClassTag, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public Object getClassTag() {
		return classTag;
	}
	
	public static NoClassWithTag newCase (Object aFinder, Object aClassTag) {
		String aMessage = "No class found with tag:" + aClassTag;
		NoClassWithTag retVal = new NoClassWithTag(aMessage, aClassTag, aFinder);
		retVal.announce();
		return retVal;
		
	}

}
