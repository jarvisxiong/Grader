package grader.trace.assignment_data;

import grader.basics.trace.SerializableFileInfo;

public class FeatureGradeFileRestored extends SerializableFileInfo {

	public FeatureGradeFileRestored(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FeatureGradeFileRestored newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Feature grade file restored: " + aFileName;
		FeatureGradeFileRestored retVal = new FeatureGradeFileRestored(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
