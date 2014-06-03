package grader.project;

import util.trace.TraceableError;

public class MultipleClassesWithTag extends TraceableError{
	String classTag;

	public MultipleClassesWithTag(String aMessage, String aClassTag, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	public String getClassTag() {
		return classTag;
	}
	
	public static MultipleClassesWithTag newCase (Object aFinder, String aClassTag) {
		String aMessage = "Multiple classes found with tag:" + aClassTag;
		MultipleClassesWithTag retVal = new MultipleClassesWithTag(aMessage, aClassTag, aFinder);
		retVal.announce();
		return retVal;
		
	}

}
