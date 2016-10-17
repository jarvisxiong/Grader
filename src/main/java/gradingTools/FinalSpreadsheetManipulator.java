package gradingTools;

import java.util.Arrays;

import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.csv.ASakaiCSVFinalGradeManager;
import grader.spreadsheet.csv.SakaiCSVFinalGradeRecorder;

public class FinalSpreadsheetManipulator {
	public static void main (String[] args) {
		SakaiCSVFinalGradeRecorder aFinalGradeRecorder = new ASakaiCSVFinalGradeManager("tmp/grades.csv");
		aFinalGradeRecorder.createTable();
		for (int i=0; i < aFinalGradeRecorder.size(); i++) {
			System.out.println (Arrays.toString(aFinalGradeRecorder.getStudentRow(i)));
			System.out.println (aFinalGradeRecorder.getOnyen(i));
			System.out.println (aFinalGradeRecorder.getFullName(i));
			System.out.println (aFinalGradeRecorder.getGrade(i));
		}
		
	}

}
