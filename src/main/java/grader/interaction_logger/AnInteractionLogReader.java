package grader.interaction_logger;

import grader.basics.trace.SerializableGraderInfo;
import grader.trace.interaction_logger.InteractionLogFileNotFound;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class AnInteractionLogReader implements InteractionLogReader {

    public static List<String[]> toCSVTable(String aFileName) {
    	return toCSVTable(new File(aFileName));
//        List<String[]> retVal = null;
//        String fileName = aFileName;
//        File file = new File(aFileName);
//        if (!file.exists()) {
//            throw InteractionLogFileNotFound.newCase(aFileName, AnInteractionLogReader.class);
//        }
//
//        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {
//            retVal = csvReader.readAll();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return retVal;
    }
    public static List<String[]> toCSVTable(File file) {
        List<String[]> retVal = null;
//        String fileName = aFileName;
//        File file = new File(aFileName);
        if (!file.exists()) {
            throw InteractionLogFileNotFound.newCase(file.getName(), AnInteractionLogReader.class);
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file)))) {
            retVal = csvReader.readAll();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retVal;
    }

//	String fileName;
//
//	File file;
    List<String[]> table;

    public AnInteractionLogReader(List<String[]> aTable) {
        table = aTable;
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
    public List<String[]> allRows(Class aTraceableClass, int fromIndex, int toIndex) {
        List<String[]> retVal = new ArrayList<>(100);
        if (fromIndex >= table.size()) {
            return null;
        }

        for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
            String[] row = table.get(rowIndex);
            if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
                retVal.add(row);
            }
        }
        return retVal;
    }

//	public String getFileName() {
    @Override
    public List<String[]> getTable() {
        return table;
    }

    @Override
    public void setTable(List<String[]> table) {
        this.table = table;
    }

    @Override
    public String[] lastRow(Class aTraceableClass, int fromIndex, int toIndex) {
        int index = lastRowIndex(aTraceableClass, fromIndex, toIndex);
        if (index < 0) {
            return null;
        }
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
    public String[] lastRow(Class aTraceableClass, int fromIndex) {
        return lastRow(aTraceableClass, fromIndex, table.size());
    }

    @Override
    public String[] lastRow(Class aTraceableClass) {
        return lastRow(aTraceableClass, 0);
    }

    @Override
    public int lastRowIndex(Class aTraceableClass, int fromIndex, int toIndex) {
        if (fromIndex >= table.size()) {
            return -1;
        }
        int retVal = -1;
        for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
            String[] row = table.get(rowIndex);
            if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
                retVal = rowIndex;
            }
        }
        return retVal;
    }

    @Override
    public int lastRowIndex(Class aTraceableClass, int fromIndex) {
        return lastRowIndex(aTraceableClass, fromIndex, table.size());
    }

    @Override
    public int lastRowIndex(Class aTraceableClass) {
        return lastRowIndex(aTraceableClass, 0);
    }

    @Override
    public String[] nextRow(Class aTraceableClass, int fromIndex, int toIndex) {
        int nextRowIndex = nextRowIndex(aTraceableClass, fromIndex, toIndex);
        if (nextRowIndex < 0) {
            return null;
        }
        return table.get(nextRowIndex);
//		if (fromIndex >= table.size()) return null;
//		for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
//			String[] row = table.get(rowIndex);
//			if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) return row;
//		}
//		return null;
    }

    @Override
    public String[] nextRow(Class aTraceableClass, int fromIndex) {
        return nextRow(aTraceableClass, fromIndex, table.size());
    }

    @Override
    public String[] nextRow(Class aTraceableClass) {
        return nextRow(aTraceableClass, 0);
    }

    @Override
    public int nextRowIndex(Class aTraceableClass, int fromIndex, int toIndex) {
        if (fromIndex >= table.size()) {
            return -1;
        }
        for (int rowIndex = fromIndex; rowIndex < toIndex; rowIndex++) {
            String[] row = table.get(rowIndex);
            if (aTraceableClass.getSimpleName().equals(SerializableGraderInfo.classNameFromCSVRow(row))) {
                return rowIndex;
            }
        }
        return -1;
    }

    @Override
    public int nextRowIndex(Class aTraceableClass, int fromIndex) {
        return nextRowIndex(aTraceableClass, fromIndex, table.size());
    }

    @Override
    public int nextRowIndex(Class aTraceableClass) {
        return nextRowIndex(aTraceableClass, 0);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof InteractionLogWriter) {
            table.add((String[]) evt.getNewValue());
        }
        
    }


}
