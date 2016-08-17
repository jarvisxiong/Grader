package grader.spreadsheet.csv;

import grader.spreadsheet.FeatureGradeRecorder;

public interface IndividualStudentHistoryManager extends FeatureGradeRecorder{
	public void resetHistory();
	public String[] addNewRow();


}
