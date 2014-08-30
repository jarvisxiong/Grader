package grader.requirements.interpreter.specification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import bus.uigen.Message;
import scala.xml.dtd.DEFAULT;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import framework.grading.FrameworkProjectRequirements;
import grader.file.FileProxy;
import grader.requirements.interpreter.InterpretedRequirements;
import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FinalGradeRecorder;

public class ACSVRequirementsSpecification implements CSVRequirementsSpecification {
	public static final String TYPE_NAME = "Type";
	public static final int TYPE_COLUMN = 0;
	public static final int DESCRIPTION_COLUMN = TYPE_COLUMN+1;
	public static final int MAX_SCORE_COLUMN = DESCRIPTION_COLUMN + 1;
	public static final  int INPUT_COLUMN = DESCRIPTION_COLUMN + 1;
	public static final  int TIMEOUT_COLUMN = INPUT_COLUMN + 1;
	public static final  int MODEL_OUTPUT_COLUMN = TIMEOUT_COLUMN + 1;
	public static final  int COMPARATOR_COLUMN = MODEL_OUTPUT_COLUMN  + 1;
	public static final  int START_COMPARATOR_ARGUMENTS_COLUMN = COMPARATOR_COLUMN + 1;
	
	protected int headerRow = 0;
	protected int numRequirements;
	FileProxy specificationSpreadsheet; // w
	
	List<String[]>  table;
	protected boolean valid;
	protected FrameworkProjectRequirements projectRequirements;
  


	public ACSVRequirementsSpecification(FileProxy aSpecificationSpreadsheet) {
		specificationSpreadsheet = aSpecificationSpreadsheet;	
		init();
	}
	
	public ACSVRequirementsSpecification(SakaiProjectDatabase aSakaiProjectDatabase) {
		specificationSpreadsheet = aSakaiProjectDatabase.getAssignmentDataFolder().getRequirementsSpreadsheetFile();
		init();
	}
	
	protected void init() {
		maybeCreateTable();
		headerRow = getHeaderRowNum(table);
		valid = headerRow >= 0;
		makeRequirements();
		

	}
	
	protected void makeRequirements() {
		if (isValid()) {
			numRequirements = table.size() - headerRow + 1;
			
		}
		
	}
	
	protected void maybeCreateTable() {
		if (table != null)
			return;
		createTable();
		
	}
	
	protected void createTable() {
		
		try {
			InputStream input = specificationSpreadsheet.getInputStream();
			CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
		     table = csvReader.readAll();
			csvReader.close();
			input.close();
			
		   
	    
	    
		} catch (Exception e) {
			e.printStackTrace();
		
			
		}
		
	}
	
	
	
	public int getHeaderRowNum(List<String[]> aSheet) {
		 for (int rowNum = 0; rowNum < aSheet.size(); rowNum ++) {
			 String[] aRow = aSheet.get(rowNum);
			 if (aRow[TYPE_COLUMN].equalsIgnoreCase(TYPE_NAME))
				 return rowNum;
		 }
		 return -1;
		
	}
	
	
	
	
	
	public FileProxy getSpecificationSpreadsheet() {
		return specificationSpreadsheet;
	}

	public String getFileName() {
		return specificationSpreadsheet.getMixedCaseAbsoluteName();
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValue) {
		this.valid = newValue;
	}

	public List<String[]> getTable() {
		return table;
	}
	@Override
	public int getNumberOfRequirements() {
		return numRequirements;
	}
	@Override
	public String getArg(int aRequirementNum, int anArgNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		int aColumnNum = START_COMPARATOR_ARGUMENTS_COLUMN + anArgNum;
		return table.get(aRowNum)[aColumnNum];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have arg " + anArgNum);
			return null;
		}
	}
	public String getType(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return table.get(aRowNum)[TYPE_COLUMN];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have type ");
			return null;
		}
	}
	
	public String getDescription(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return table.get(aRowNum)[DESCRIPTION_COLUMN];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have description ");
			return null;
		}
	}
	@Override
	public Double getMaxScore(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return Double.parseDouble(table.get(aRowNum)[MAX_SCORE_COLUMN]);
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have max score");
			return null;
		}
	}
	
	@Override
	public Integer getTimeOut(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return Integer.parseInt(table.get(aRowNum)[TIMEOUT_COLUMN]);
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have timeout");
			return null;
		}
	}

	
	public String getInput(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return table.get(aRowNum)[INPUT_COLUMN];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have max score ");
			return null;
		}
	}
	public String getModelOutput(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return table.get(aRowNum)[MODEL_OUTPUT_COLUMN];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have model output");
			return null;
		}
	}
	public String getComparator(int aRequirementNum) {
		try {
		int aRowNum = headerRow + 1 + aRequirementNum;
		return table.get(aRowNum)[COMPARATOR_COLUMN];
		} catch (Exception e) {
			System.out.println("Requirement " + aRequirementNum + " does not have comparator");
			return null;
		}
	}
	

}
