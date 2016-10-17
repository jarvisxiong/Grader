package grader.spreadsheet.csv;

import java.util.List;
import java.util.Set;

import grader.spreadsheet.FeatureGradeRecorder;

public interface SakaiCSVFeatureGradeManager extends FeatureGradeRecorder, SakaiCSVFinalGradeRecorder{

	double getGrade(int aRowNum, String aFeature);

	String getResult(int aRowNum, String aFeature);

	List<String> getFeatureNames();

	void readFeatureNames();

}
