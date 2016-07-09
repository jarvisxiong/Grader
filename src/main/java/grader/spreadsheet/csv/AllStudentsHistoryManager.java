package grader.spreadsheet.csv;

import java.beans.PropertyChangeListener;

public interface AllStudentsHistoryManager extends PropertyChangeListener{
	void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore);


}
