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
	
	public AnInteractionLogReader(String aFileName) {
		String fileName = aFileName;
		File file = new File(aFileName);
		if (!file.exists())
			throw InteractionLogFileNotFound.newCase(aFileName, this);
		InputStream input;
		try {
			input = new FileInputStream(file);
		
		CSVReader csvReader 	=	new CSVReader(new InputStreamReader(input));
		table = csvReader.readAll();
		csvReader.close();
		input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		InteractionLogWriter interactionLogWriter = InteractionLogWriterSelector.getInteractionLogWriter();
//		if (interactionLogWriter.get)
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof InteractionLogWriter) {
			table.add((String[])evt.getNewValue());
		}
		
	}
	
	public String[] nextRow (Class aTraceableClass, int fromIndex, int toIndex) {
		if (fromIndex >= table.size()) return null;
		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
			String[] row = table.get(rowIndex);
			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) return row;
		}
		return null;		
	}
	
	
	
	public String[] lastRow (Class aTraceableClass, int fromIndex, int toIndex) {
		if (fromIndex >= table.size()) return null;
		String[] retVal = null;
		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
			String[] row = table.get(rowIndex);
			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
				retVal = row;
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
	public String[] nextRow(Class aTraceableClass) {
		return nextRow(aTraceableClass, 0);
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
