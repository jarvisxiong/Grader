package grader.spreadsheet.csv;

import grader.spreadsheet.FeatureGradeRecorder;

public interface IndividualStidentHistoryManager extends FeatureGradeRecorder{
	public void resetHistory();
	public String[] addNewRow();


}
