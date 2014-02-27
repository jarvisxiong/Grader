package grader.spreadsheet.csv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import framework.grading.testing.TestCaseResult;
import grader.assignment.AssignmentDataFolder;
import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;
import grader.file.FileProxy;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FinalGradeRecorder;

public class ASakaiCSVFeatureGradeManager extends ASakaiCSVFinalGradeManager implements FeatureGradeRecorder {
//	SakaiProjectDatabase projectDatabase;
//	public static final int ONYEN_COLUMN = 0;
//	public static final int GRADE_COLUMN = 4;
//	InputStream input; // this may have to be reinitialized each time
//	OutputStream output; // may have to reinitialized and closed each time
//	FileProxy gradeSpreadsheet;
//	List<String[]>  table;
	 List<GradingFeature> gradingFeatures;
	 Map<String, Integer> featureToColumnNumber = new HashMap();
	 Map<String, Integer> resultToColumnNumber = new HashMap();
	 public static final int EARLY_LATE_COLUMN = GRADE_COLUMN + 1;
	 public static final String LATE_TITLE = "Early/Late";
	 public static final int PRE_FEATURE_COLUMN = EARLY_LATE_COLUMN;

	public ASakaiCSVFeatureGradeManager(FileProxy aGradeSpreadsheet, List<GradingFeature> aGradingFeatures) {
		super(aGradeSpreadsheet);
		gradingFeatures = aGradingFeatures;
		
//		gradeSpreadsheet = aGradeSpreadsheet;		
	}
	
	public ASakaiCSVFeatureGradeManager(SakaiProjectDatabase aSakaiProjectDatabase) {
		super(aSakaiProjectDatabase.getAssigmentDataFolder().getFeatureGradeFile());
		gradingFeatures = aSakaiProjectDatabase.getGradingFeatures();
		
//		gradeSpreadsheet = aGradeSpreadsheet;		
	}
	
	protected void createTable() {
		super.createTable();
		if (gradingFeatures == null) return;
		String[] headers = table.get(TITLE_ROW);
		if (headers.length < PRE_FEATURE_COLUMN + gradingFeatures.size() + 1) {
			
		extendTable();
		makeTitles();
		writeTable();
		}
		makeMap();
		
	}
	
	void makeTitles() {
		String[] titleRow = table.get(TITLE_ROW);
		titleRow[EARLY_LATE_COLUMN] = LATE_TITLE;
		for (int i = 0; i < gradingFeatures.size(); i++) {
			int featureColumn = PRE_FEATURE_COLUMN + 1 + i;
//			featureToColumnNumber.put(gradingFeatures.get(i).getFeature(), featureColumn);
			titleRow[featureColumn] = gradingFeatures.get(i).getFeature();
			int resultsColumn = PRE_FEATURE_COLUMN + 1 + gradingFeatures.size() + i;
			titleRow[resultsColumn] = gradingFeatures.get(i).getFeature();

			
		}
	}
	
	void makeMap() {
		for (int i = 0; i < gradingFeatures.size(); i++) {
			int featureColumn = PRE_FEATURE_COLUMN + 1 + i;
			featureToColumnNumber.put(gradingFeatures.get(i).getFeature(), featureColumn);
			int resultColumn = PRE_FEATURE_COLUMN + 1 + gradingFeatures.size() + i;
			resultToColumnNumber.put(gradingFeatures.get(i).getFeature(), resultColumn);
			
		}
	}
	
	void extendTable() {
		for (int i = 0; i < table.size(); i++) {
			table.set (i, extendedRow(table.get(i)));
		}
	}

	String[] extendedRow(String[] anExistinRow) {
		// adding late penalty column also and results column
		String[] retVal = new String[anExistinRow.length + 1 + 2*gradingFeatures.size()];
		for (int index = 0; index < anExistinRow.length; index++) {
			retVal[index] = anExistinRow[index];
		}
		for (int index = anExistinRow.length; index < retVal.length; index++) {
//			retVal[index] = "0";
			retVal[index] = DEFAULT_CHAR;
		}
		return retVal;
		
	}
	public double getGrade (String[] aRow, String aFeatureName) {
		return getGrade(aRow, featureToColumnNumber.get(aFeatureName));

	}
	
	public String getResult (String[] aRow, String aFeatureName) {
		return getResult(aRow, resultToColumnNumber.get(aFeatureName));

	}
	
	public void recordGrade (String[] aRow, String aFeature, double aScore) {
		recordGrade(aRow, featureToColumnNumber.get(aFeature), aScore);

	}
	
	public void recordResult (String[] aRow, String aFeature, String aScore) {
		recordResult(aRow, resultToColumnNumber.get(aFeature), aScore);

	}

	@Override
	public void setGrade(String aStudentName, String anOnyen, String aFeature,
			double aScore) {
		try {

			maybeCreateTable();
			
		    String[] row = getStudentRow(table, aStudentName, anOnyen);
		    if (row == null) {
				System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
				return;
		    }
		    
		    recordGrade(row, aFeature, aScore);
		    writeTable();



	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
	}
	
	@Override
	public void setResult(String aStudentName, String anOnyen, String aFeature,
			String aResult) {
		try {

			maybeCreateTable();
			
		    String[] row = getStudentRow(table, aStudentName, anOnyen);
		    if (row == null) {
				System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
				return;
		    }
		    
		    recordResult(row, aFeature, aResult);
		    writeTable();



	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
	}

	@Override
	public double getGrade(String aStudentName, String anOnyen, String aFeature) {
		try {

				maybeCreateTable();
			
		   
	    String[] row = getStudentRow(table, aStudentName, anOnyen);
	    if (row == null) {
			System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
			return -1;
	    }
	   double retVal =  getGrade(row, aFeature);	
	    return retVal;
	    
		} catch (Exception e) {
			e.printStackTrace();
			return DEFAULT_VALUE;
			
		}		
	}
	
	@Override
	public String getResult(String aStudentName, String anOnyen, String aFeature) {
		try {

				maybeCreateTable();
			
		   
	    String[] row = getStudentRow(table, aStudentName, anOnyen);
	    if (row == null) {
			System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
			return "";
	    }
	   String retVal =  getResult(row, aFeature);	
	    return retVal;
	    
		} catch (Exception e) {
			e.printStackTrace();
			return "";
			
		}		
	}
	

	@Override
	public void setEarlyLatePoints(String aStudentName, String anOnyen,
			double aScore) {
		maybeCreateTable();
		
	    String[] row = getStudentRow(table, aStudentName, anOnyen);
	    if (row == null) {
			System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
			return;
	    }
	    
	    recordGrade(row, EARLY_LATE_COLUMN, aScore);
	    writeTable();

		
	}

	@Override
	public double getEarlyLatePoints(String aStudentName, String anOnyen) {
		 String[] row = getStudentRow(table, aStudentName, anOnyen);
		    if (row == null) {
				System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
				return -1;
		    }
		   return getGrade(row, EARLY_LATE_COLUMN);

		
	}

	@Override
	public void setNotes(String aStudentName, String anOnyen, String aFeature,
			String aNotes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNotes(String aStudentName, String anOnyen, String aFeature) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void newSession(String onyen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(double gradePercentage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFeatureComments(String comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFeatureResults(List<TestCaseResult> results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String computeSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comment(GradingFeature aGradingFeature) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGradingFeatures(GradingFeatureList newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GradingFeatureList getGradingFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStoredSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

}
