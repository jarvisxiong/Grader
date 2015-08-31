package grader.spreadsheet.csv;

import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FeatureGradeRecorderFactory;
import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.FinalGradeRecorderFactory;

public class AFinalGradeRecorderFactory implements FinalGradeRecorderFactory{
	FinalGradeRecorder recorder;
//	@Override
//	public FeatureGradeRecorder createFeatureGradeRecorder(
//			SakaiProjectDatabase aSakaiProjectDatabase) {
//		return new ASakaiCSVFeatureGradeManager(aSakaiProjectDatabase);
//	}
	
	@Override
	public FinalGradeRecorder createGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new ASakaiCSVFinalGradeManager(aSakaiProjectDatabase);
	}
	@Override
	public FinalGradeRecorder getGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		if (recorder == null)
			recorder = createGradeRecorder(aSakaiProjectDatabase);
		return  recorder;
	}


}
