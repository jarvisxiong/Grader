package grader.spreadsheet.csv;

import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FeatureGradeRecorderFactory;
import grader.spreadsheet.FinalGradeRecorder;
// chnage its name to have SakaiCSV in it
public class AFeatureGradeRecorderFactory implements FeatureGradeRecorderFactory{
	FeatureGradeRecorder recorder;
//	@Override
//	public FinalGradeRecorder createFinalGradeRecorder(
//			SakaiProjectDatabase aSakaiProjectDatabase) {
//		return new ASakaiCSVFinalGradeManager(aSakaiProjectDatabase);
//	}

	@Override
	public FeatureGradeRecorder createGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		return new ASakaiCSVFeatureGradeManager(aSakaiProjectDatabase);
	}

	@Override
	public FinalGradeRecorder getGradeRecorder(
			SakaiProjectDatabase aSakaiProjectDatabase) {
		if (recorder == null)
			recorder = createGradeRecorder(aSakaiProjectDatabase);
		return recorder;
	}
}
