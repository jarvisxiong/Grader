package grader.interaction_logger;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface InteractionLogReader extends PropertyChangeListener{
	
	
	
	
	
	public String[] nextRow (Class aTraceableClass, int fromIndex, int toIndex) ;
	
	
	
	public String[] lastRow (Class aTraceableClass, int fromIndex, int toIndex) ;
	

	public List<String[]> allRows (Class aTraceableClass, int fromIndex, int toIndex) ;
	
	
	
	public String[] nextRow(Class aTraceableClass, int fromIndex) ;
	public String[] nextRow(Class aTraceableClass);
	public String[] lastRow(Class aTraceableClass, int fromIndex) ;
	public String[] lastRow(Class aTraceableClass) ;
	
	
//	public String getFileName() ;
//
//
//	public void setFileName(String fileName) ;
//
//
//	public File getFile() ;
//
//
//	public void setFile(File file) ;


	public List<String[]> getTable();


	public void setTable(List<String[]> table);



	int nextRowIndex(Class aTraceableClass);



	int nextRowIndex(Class aTraceableClass, int fromIndex);



	int nextRowIndex(Class aTraceableClass, int fromIndex, int toIndex);



	int lastRowIndex(Class aTraceableClass, int fromIndex, int toIndex);



	int lastRowIndex(Class aTraceableClass, int fromIndex);



	int lastRowIndex(Class aTraceableClass);
    
	

}
