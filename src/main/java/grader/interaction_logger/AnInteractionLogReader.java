package grader.interaction_logger;

import grader.trace.SerializableGraderInfo;
import grader.trace.interaction_logger.InteractionLogFileNotFound;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class AnInteractionLogReader implements InteractionLogReader{
	
//	String fileName;
//
//	File file;
	List<String[]>  table;
	
	public AnInteractionLogReader(List<String[]> aTable) {
		table = aTable;		
	}
	

	
	public static List<String[]> toCSVTable(String aFileName) {
		List<String[]> retVal = null;
		String fileName = aFileName;
		File file = new File(aFileName);
		if (!file.exists())
			throw InteractionLogFileNotFound.newCase(aFileName, AnInteractionLogReader.class);
		InputStream input;
		try {
			input = new FileInputStream(file);
		
		CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
		 retVal = csvReader.readAll();
		csvReader.close();
		input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
		
	}
	public AnInteractionLogReader(String aFileName) {
		table = toCSVTable(aFileName);
//		String fileName = aFileName;
//		File file = new File(aFileName);
//		if (!file.exists())
//			throw InteractionLogFileNotFound.newCase(aFileName, this);
//		InputStream input;
//		try {
//			input = new FileInputStream(file);
//		
//		CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
//		table = csvReader.readAll();
//		csvReader.close();
//		input.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		InteractionLogWriter interactionLogWriter = InteractionLogWriterSelector.getInteractionLogWriter();
////		if (interactionLogWriter.get)
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof InteractionLogWriter) {
			table.add((String[])evt.getNewValue());
		}
		
	}
	
	public String[] nextRow (Class aTraceableClass, int fromIndex, int toIndex) {
		int nextRowIndex = nextRowIndex(aTraceableClass, fromIndex, toIndex);
		if (nextRowIndex < 0)
			return null;
		return table.get(nextRowIndex);
//		if (fromIndex >= table.size()) return null;
//		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
//			String[] row = table.get(rowIndex);
//			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) return row;
//		}
//		return null;		
	}
	@Override
	public int nextRowIndex (Class aTraceableClass, int fromIndex, int toIndex) {
		if (fromIndex >= table.size()) return -1;
		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
			String[] row = table.get(rowIndex);
			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) return rowIndex;
		}
		return -1;		
	}
	
	
	
	public String[] lastRow (Class aTraceableClass, int fromIndex, int toIndex) {
		int index = lastRowIndex(aTraceableClass, fromIndex, toIndex);
		if (index < 0) return null;
		return table.get(index);
		
//		if (fromIndex >= table.size()) return null;
//		String[] retVal = null;
//		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
//			String[] row = table.get(rowIndex);
//			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
//				retVal = row;
//			}
//		}
//		return retVal;		
	}
	@Override
	public int lastRowIndex (Class aTraceableClass, int fromIndex, int toIndex) {
		if (fromIndex >= table.size()) return -1;
		int retVal = -1;
		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
			String[] row = table.get(rowIndex);
			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
				retVal = rowIndex;
			}
		}
		return retVal;		
	}
	

	public List<String[]> allRows (Class aTraceableClass, int fromIndex, int toIndex) {
		List<String[]> retVal = new ArrayList();
		if (fromIndex >= table.size()) return null;
		
		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
			String[] row = table.get(rowIndex);
			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
				retVal.add(row);
			}
		}
		return retVal;		
	}
	
	
	
	public String[] nextRow(Class aTraceableClass, int fromIndex) {
		return nextRow(aTraceableClass, fromIndex, table.size());
	}
	@Override
	public int nextRowIndex(Class aTraceableClass, int fromIndex) {
		return nextRowIndex(aTraceableClass, fromIndex, table.size());
	}
	public String[] nextRow(Class aTraceableClass) {
		return nextRow(aTraceableClass, 0);
	}
	@Override
	public int nextRowIndex(Class aTraceableClass) {
		return nextRowIndex(aTraceableClass, 0);
	}
	@Override
	public int lastRowIndex(Class aTraceableClass, int fromIndex) {
		return lastRowIndex(aTraceableClass, fromIndex, table.size());
	}
	@Override
	public int lastRowIndex(Class aTraceableClass) {
		return lastRowIndex(aTraceableClass, 0);
	}
	
	public String[] lastRow(Class aTraceableClass, int fromIndex) {
		return lastRow(aTraceableClass, fromIndex, table.size());
	}
	public String[] lastRow(Class aTraceableClass) {
		return lastRow(aTraceableClass, 0);
	}
	
	
//	public String getFileName() {
//		return fileName;
//	}
//
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//
//	public File getFile() {
//		return file;
//	}
//
//
//	public void setFile(File file) {
//		this.file = file;
//	}


	public List<String[]> getTable() {
		return table;
	}


	public void setTable(List<String[]> table) {
		this.table = table;
	}
    
	

}
