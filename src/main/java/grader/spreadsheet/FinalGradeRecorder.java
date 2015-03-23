package grader.spreadsheet;

import grader.file.FileProxy;

import org.apache.poi.ss.usermodel.Row;

public interface FinalGradeRecorder {
	void setGrade(String aStudentName, String anOnyen, double aScore);
//	public void recordGrade (Row aRow, int aColumn, double aScore);
	double getGrade(String aStudentName, String anOnyen);
//	 double getGrade (Row aRow, int aColumn);
	public String getFileName();
	public FileProxy getGradeSpreadsheet();


}
