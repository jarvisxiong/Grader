package grader.spreadsheet.csv;

import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.FinalGradeRecorderFactory;

public class AFeatureAndFinalGradeRecorderFactory implements FinalGradeRecorderFactory {
	FinalGradeRecorder recorder;
	@Override
	public FinalGradeRecorder createGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new AFeatureAndFinalGradeRecorder(aSakaiProjectDatabase);
	}
	@Override
	public FinalGradeRecorder getGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		if (recorder == null)
			recorder = createGradeRecorder(aSakaiProjectDatabase);
		return  recorder;
	}
	

}
