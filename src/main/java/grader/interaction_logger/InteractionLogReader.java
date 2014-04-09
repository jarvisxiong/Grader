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

public interface InteractionLogReader extends PropertyChangeListener{
	
	
	
	
	
	public String[] nextRow (Class aTraceableClass, int fromIndex, int toIndex) ;
	
	
	
	public String[] lastRow (Class aTraceableClass, int fromIndex, int toIndex) ;
	

	public List<String[]> allRows (Class aTraceableClass, int fromIndex, int toIndex) ;
	
	
	
	public String[] nextRow(Class aTraceableClass, int fromIndex) ;
	public String[] nextRow(Class aTraceableClass);
	public String[] lastRow(Class aTraceableClass, int fromIndex) ;
	public String[] lastRow(Class aTraceableClass) ;
	
	
	public String getFileName() ;


	public void setFileName(String fileName) ;


	public File getFile() ;


	public void setFile(File file) ;


	public List<String[]> getTable();


	public void setTable(List<String[]> table);
    
	

}
