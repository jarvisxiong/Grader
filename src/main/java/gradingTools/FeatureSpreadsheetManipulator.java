package gradingTools;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;
import grader.spreadsheet.csv.ASakaiCSVFinalGradeManager;
import grader.spreadsheet.csv.SakaiCSVFeatureGradeManager;
import grader.spreadsheet.csv.SakaiCSVFinalGradeRecorder;

public class FeatureSpreadsheetManipulator {
	 
	public static void printFeatureInfo (int aRowNum, List<String> aFeatures, SakaiCSVFeatureGradeManager aRecorder) {
		for (String aFeature:aFeatures) {
			System.out.println (aFeature + ":" + aRecorder.getGrade(aRowNum, aFeature));

		}
		
	}
	public static void main (String[] args) {
		SakaiCSVFeatureGradeManager aRecorder = new ASakaiCSVFeatureGradeManager("tmp/dewan_FeatureGrades.csv");
		aRecorder.createTable();
		aRecorder.readFeatureNames();
		List<String> aFeatures = aRecorder.getFeatureNames();
		for (int aRowNum=0; aRowNum < aRecorder.size(); aRowNum++) {
			System.out.println (Arrays.toString(aRecorder.getStudentRow(aRowNum)));
			System.out.println (aRecorder.getOnyen(aRowNum));
			System.out.println (aRecorder.getFullName(aRowNum));
			System.out.println (aRecorder.getGrade(aRowNum));
			printFeatureInfo(aRowNum, aFeatures, aRecorder);
		}		
	}
}
