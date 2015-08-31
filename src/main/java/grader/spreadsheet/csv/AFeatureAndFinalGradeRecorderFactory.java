package grader.spreadsheet.csv;

import grader.assignment.GradingFeature;
import grader.file.FileProxy;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.FinalGradeRecorderFactory;

import java.util.List;

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
