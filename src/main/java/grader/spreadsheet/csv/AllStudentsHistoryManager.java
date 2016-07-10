package grader.spreadsheet.csv;

import grader.spreadsheet.FeatureGradeRecorder;

import java.beans.PropertyChangeListener;

public interface AllStudentsHistoryManager extends PropertyChangeListener, FeatureGradeRecorder{
	void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore);


}
