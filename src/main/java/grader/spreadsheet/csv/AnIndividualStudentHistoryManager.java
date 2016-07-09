package grader.spreadsheet.csv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;
import grader.assignment.GradingFeature;
import grader.file.FileProxy;
import grader.sakai.project.ProjectStepperFactory;
import grader.sakai.project.SakaiProjectDatabase;

public class AnIndividualStudentHistoryManager extends ASakaiCSVFeatureGradeManager implements IndividualStidentHistoryManager {
	public AnIndividualStudentHistoryManager(FileProxy aGradeSpreadsheet, List<GradingFeature> aGradingFeatures) {
		super(aGradeSpreadsheet, aGradingFeatures);
		maybeCreateTable();
		resetTable();
		writeTable();
	}
//	public AnIndividualStudentHistoryManager(SakaiProjectDatabase aSakaiProjectDatabase) {
//		super(aSakaiProjectDatabase);
//		maybeCreateTable();
//		resetTable();
//		writeTable();		
////		gradeSpreadsheet = aGradeSpreadsheet;		
//	}
	
	
	public void resetTable() {
		List<String[]>  newTable = new ArrayList();
		for (int i = 0; i <= TITLE_ROW; i++) {
			newTable.add(table.get(i));
		}
//		extendTable();
		String[] aHeaderRow = newTable.get(TITLE_ROW);
		aHeaderRow[0]= "#";
		aHeaderRow[1] = "Date";
		aHeaderRow[2] = "Change";
		aHeaderRow[3] = "";
		table = newTable;
//		addNewRow(); // for the first entry
	}
	// should be called by all student manager
	public String[] addNewRow() {
		String[] aPreviousRow = table.get(table.size()-1);
		String[] aNewRow = new String[aPreviousRow.length];
		for (int i=GRADE_COLUMN; i < aNewRow.length; i++) {
			aNewRow[i] = "";
		}
		table.add(aNewRow);
		return aNewRow;
		
	}
	@Override
	public void setGrade(String aStudentName, String anOnyen, String aFeature, double aScore) {
            try {
//                maybeCreateTable();
//                String[] row = getStudentRow(table, aStudentName, anOnyen);
            	String[] row= table.get(table.size() - 1);
//                if (row == null) {
//                    System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
//                    return;
//                }

                recordGrade(row, aFeature, aScore);
                writeTable();
            } catch (Exception e) {
                    e.printStackTrace();
            }
	}
	
	@Override
	public void setResultFormat(String aStudentName, String anOnyen, String aFeature,
			String aResult) {
		try {

//			maybeCreateTable();
			
//		    String[] row = getStudentRow(table, aStudentName, anOnyen);
        	String[] row= table.get(table.size() - 1);

//		    if (row == null) {
//				System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
//				return;
//		    }
		    
		    recordResult(row, aFeature, aResult);
		    writeTable();



	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
	}

	

}
