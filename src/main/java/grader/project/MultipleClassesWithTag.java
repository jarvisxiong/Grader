package grader.project;

import util.trace.TraceableError;

public class MultipleClassesWithTag extends TraceableError{
	Object classTag;

	public MultipleClassesWithTag(String aMessage, Object aClassTag, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public Object getClassTag() {
		return classTag;
	}
	
	public static MultipleClassesWithTag newCase (Object aFinder, Object aClassTag) {
		String aMessage = "Multiple classes found with tag:" + aClassTag;
		MultipleClassesWithTag retVal = new MultipleClassesWithTag(aMessage, aClassTag, aFinder);
		retVal.announce();
		return retVal;
		
	}

}
