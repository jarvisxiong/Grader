package grader.spreadsheet.csv;

import grader.file.FileProxy;
import grader.file.filesystem.AFileSystemFileProxy;
import grader.sakai.project.SakaiProjectDatabase;
import grader.spreadsheet.FinalGradeRecorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import util.misc.Common;
import util.trace.Tracer;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
//import bus.uigen.Message;
// This is the class that manages the grades.csv file. Josh's stuff might interfere with this
public class ASakaiCSVGradeBookManager implements SakaiCSVFinalGradeRecorder {
	public static final int ONYEN_COLUMN = 0;
	public static final int LAST_NAME_COLUMN = 2;
	public static final int FIRT_NAME_COLUMN = 3;
	public static final int GRADE_COLUMN = 4;
	public static final int TITLE_ROW = 2;
	public static final int FIRST_STUDENT_ROW = TITLE_ROW + 1;
	 public static final String DEFAULT_CHAR = "";
	 public static final double  DEFAULT_VALUE = -1;

//	InputStream input; // this may have to be reinitialized each time
//	OutputStream output; // may have to reinitialized and closed each time
	FileProxy gradeSpreadsheet;
	
	List<String[]>  table;
  
	int originalTableSize;
	public ASakaiCSVGradeBookManager(FileProxy aGradeSpreadsheet) {
		gradeSpreadsheet = aGradeSpreadsheet;		
	}
	public ASakaiCSVGradeBookManager(File aGradeSpreadsheet) {
		gradeSpreadsheet = new AFileSystemFileProxy(aGradeSpreadsheet)	;	
	}
	public ASakaiCSVGradeBookManager(String aFileName) {
		gradeSpreadsheet = new AFileSystemFileProxy(new File(aFileName))	;	
	}
	public ASakaiCSVGradeBookManager(SakaiProjectDatabase aSakaiProjectDatabase) {
		gradeSpreadsheet = aSakaiProjectDatabase.getBulkAssignmentFolder().getSpreadsheet();		
	}
	public List<String[]> getTable() {
		return table;
	}
	public int size() {
		return table.size() - FIRST_STUDENT_ROW;
	}
	protected int toActualRow(int aRowNum) {
		return aRowNum + FIRST_STUDENT_ROW;
	}
	
	public String getOnyen(int aRowIndex) {
		return table.get(toActualRow(aRowIndex))[onyenColumn()];		
	}
	public String getFirstName(int aRowIndex) {
		return table.get(toActualRow(aRowIndex))[FIRT_NAME_COLUMN];
	}
	public String getLastName(int aRowIndex) {
		return table.get(toActualRow(aRowIndex))[LAST_NAME_COLUMN];
	}
	public String getFullName(int aRowIndex) {
		return getFirstName(aRowIndex) + " " + getLastName(aRowIndex);
	}
	public double getGrade (int aRowIndex) {
		return getGrade(table.get(toActualRow(aRowIndex)), GRADE_COLUMN);

	}
	public String[] getStudentRow(int aRowIndex) {
		return table.get(toActualRow(aRowIndex));
	}
	
	protected void maybeCreateTable() {
		if (table != null)
			return;
		createTable();
		
	}
	
	public void createTable() {
		
		try {
			InputStream input = gradeSpreadsheet.getInputStream();
			CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
		     table = csvReader.readAll();
		     System.out.println ("Read spreadsheet table of size:" + table.size());
		     originalTableSize = table.size();
		     
			csvReader.close();
			input.close();
			
		   
	    
	    
		} catch (Exception e) {
			e.printStackTrace();
		
			
		}
		
	}
	
	public String[] getStudentRow(List<String[]> aSheet, String aStudentName, String anOnyen) {
		 for (int rowNum = 0; rowNum < aSheet.size(); rowNum ++) {
			 String[] aRow = aSheet.get(rowNum);
			 if (aRow[ONYEN_COLUMN].equals(anOnyen))
				 return aRow;
		 }
		 return null;
		
	}
	
	protected int onyenColumn() {
		return ONYEN_COLUMN;
	}
	protected int gradeColumn() {
		return GRADE_COLUMN;
	}
	protected int firstNameColumn() {
		return FIRT_NAME_COLUMN;
	}
	
	public void clearStudentRow(List<String[]> aSheet, String aStudentName, String anOnyen) {
		 String[] aRow = getStudentRow(aSheet, aStudentName, anOnyen);
		 if (aRow != null) {
			 for (int aColumn = 0; aColumn < aRow.length; aColumn++) {
//				 if (aColumn == ONYEN_COLUMN) continue;
				 if (aColumn == onyenColumn()) continue;

				 aRow[aColumn] = "";
			 }
		 }
		
	}
	
	public void recordGrade (String[] aRow, double aScore) {
		recordGrade(aRow, gradeColumn(), aScore);

	}
	
	public void recordGrade (String[] aRow, int aColumn, double aScore) {
		
		String aGradeCell = aRow[aColumn];
		if (aColumn >= aRow.length) {
			System.err.println("No column:" + aColumn + " in row:" + Arrays.toString(aRow));
		}
		aRow[aColumn] = Double.toString(aScore);
		
	}
	
	public void recordResult (String[] aRow, int aColumn, String aResult) {
		String aGradeCell = aRow[aColumn];
		aRow[aColumn] = aResult;
		
	}
	
	public double getGrade (String[] aRow, int aColumn) {
		try {
		String aGradeCell = aRow[aColumn];
		if (aGradeCell.equals(DEFAULT_CHAR))
			return DEFAULT_VALUE; 
		
		return Double.parseDouble(aGradeCell);
		} catch (Exception e) {
//			e.printStackTrace();
			return DEFAULT_VALUE;
		}
	}
	
	public String getResult (String[] aRow, int aColumn) {
		try {
		return aRow[aColumn];
		
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}
	
	
	public double getGrade (String[] aRow) {
		return getGrade(aRow, GRADE_COLUMN);

	}
	
	
	public double getGrade(String aStudentName, String anOnyen) {
		try {
//		InputStream input = gradeSpreadsheet.getInputStream();
//		CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
//		List<String[]>  table = csvReader.readAll();
//		csvReader.close();
			maybeCreateTable();
		
	   
    String[] row = getStudentRow(table, aStudentName, anOnyen);
    if (row == null) {
		System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
		return -1;
    }
   double retVal =  getGrade(row);

//
//    input.close();
    return retVal;
    
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
		
	}
	
		
	}
	protected void checkSizes() {
		if (table.size() != originalTableSize) {
			System.err.println ("Spreadsheet table size:" + table.size() + " != " + originalTableSize); // should we delete extra rows?
		}
	}
	void writeTable() {
		OutputStream output = gradeSpreadsheet.getOutputStream();
		if (output == null) {
			System.out.println("Cannot write grade as null output stream");
			return;
		}
		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(output));
		checkSizes();
//		if (table.size() != originalTableSize) {
//			System.err.println ("Spreadsheet table size:" + table.size() + " != " + originalTableSize); // should we delete extra rows?
//		}
		csvWriter.writeAll(table);
		try {
			csvWriter.close();
			output.close();
			removeQuotesAndTrim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	@Override
	public void setGrade(String aStudentName, String anOnyen, double aScore) {
		try {
			if (aScore < 0) {
				Tracer.error("negative score!");
//				JOptionPane.showMessageDialog(null, "Negative score! Not saving it.");
				return;
				
			}
//			InputStream input = gradeSpreadsheet.getInputStream();
//			CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
//			List<String[]>  table = csvReader.readAll();
//			csvReader.close();
			maybeCreateTable();
			
		    String[] row = getStudentRow(table, aStudentName, anOnyen);
		    if (row == null) {
				System.out.println("Cannot find row for:" + aStudentName + " " + anOnyen);
				return;
		    }
		    
		    recordGrade(row, aScore);
		    writeTable();

//		OutputStream output = gradeSpreadsheet.getOutputStream();
//		if (output == null) {
//			System.out.println("Cannot write grade as null output stream");
//			return;
//		}
//		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(output));
//		csvWriter.writeAll(table);
//		csvWriter.close();
	    
    

//
//    Row row = sheet.getRow(2);
//    Cell cell1 = row.getCell(4);
//    if (cell1 == null)
//    	cell1 = row.createCell(5);
//    double doubleCell = cell1.getNumericCellValue();
//    int i = 0;
//    cell1.setCellValue (doubleCell + 2);
//    Cell cell2 = row.getCell(0);
//    if (cell2 != null) {
//    	String stringCell = cell2.getStringCellValue();
//    	System.out.println(stringCell);
//    }

    /*
    if (cell == null)
        cell = row.createCell(3);
    cell.setCellType(Cell.CELL_TYPE_STRING);
    cell.setCellValue("a test");
    */
    /*
    // Write the output to a file
    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
    wb.write(fileOut);
    fileOut.close();
    */
	} catch (Exception e) {
		e.printStackTrace();
		
	}
		
	}
	
	void removeQuotesAndTrim() {
//		String aFileName = gradeSpreadsheet.getAbsoluteName();
		String aFileName = gradeSpreadsheet.getMixedCaseAbsoluteName();

		StringBuffer aText = Common.toText(aFileName);
		String aNewText = aText.toString().replaceAll("\"", "");
//		 aNewText = aNewText.toString().replaceAll("\\n\\n", "\\n");
		 aNewText = aNewText.trim(); //an extra line is added when graded
		try {
			Common.writeText(aFileName, aNewText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FileProxy getGradeSpreadsheet() {
		return gradeSpreadsheet;
	}

	public String getFileName() {
		return gradeSpreadsheet.getAbsoluteName();
	}
	

}
