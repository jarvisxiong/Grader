package grader.trace.file.assignment;

import grader.trace.file.SerializableFileInfo;

public class FeatureGradeFileCreatedFromFinalGradeFile extends SerializableFileInfo {
	String finalGradeFile;
	public FeatureGradeFileCreatedFromFinalGradeFile(String aMessage, String aFileName, String aFinalGradeFile,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
		finalGradeFile =aFinalGradeFile;
	}
	
	
	
	public String getFinalGradeFile() {
		return finalGradeFile;
	}



	public void setFinalGradeFile(String finalGradeFile) {
		this.finalGradeFile = finalGradeFile;
	}



	public static FeatureGradeFileCreatedFromFinalGradeFile newCase(String aFileName, String aFinalGradeFile,
			Object aFinder) {
		String aMessage =  "Feature grade file created: " + aFileName + " from: " + aFinalGradeFile;
		FeatureGradeFileCreatedFromFinalGradeFile retVal = new FeatureGradeFileCreatedFromFinalGradeFile(aMessage, aFileName, aFinalGradeFile, aFinder);
		retVal.announce();
		return retVal;
	}

}
