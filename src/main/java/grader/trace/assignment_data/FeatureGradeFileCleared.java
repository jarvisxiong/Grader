package grader.trace.assignment_data;

import grader.trace.file.FileInfo;

public class FeatureGradeFileCleared extends FileInfo {

	public FeatureGradeFileCleared(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static FeatureGradeFileCleared newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "feature grade file cleared: " + aFileName;
		FeatureGradeFileCleared retVal = new FeatureGradeFileCleared(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
