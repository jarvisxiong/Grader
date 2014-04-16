package grader.interaction_logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ASavedHistoryManager {
	String interactionDirectory;
	File[] interactionFiles;
	List<SavedAllStudentsProblemGradingHistory> problemHistory = new ArrayList();
	SavedGradingHistoryParser parser = SavedGradingHistoryParserSelector.getSavedGradingHistoryParser();
	
	public void readInteractionDirectory() {
		interactionDirectory = AnInteractionLogWriter.getOrCreateInteractionFolder();
		interactionFiles = FileTimeSorterAndComparator.sort(new File(interactionDirectory));
	}
	
	public void buildHistories() {
		buildProblemHistories();
		
	}
	public static final int PARTS_IN_LOG_FILE_NAME = 4;
//	public  SavedAllStudentsProblemGradingHistory createSavedAllStudentsProblemGradingHistory(File aFile) {
//		String fileName = aFile.getName();
//		String[] parts = fileName.split(AnInteractionLogWriter.SEPARATOR);
//		if (parts.length != PARTS_IN_LOG_FILE_NAME)
//			return null;
//		String aGraderName = parts[0];
//		String aModuleName = parts[2];
//		String aProblemName = parts[3];
//		return new ASavedAllStudentsProblemGradingHistory(aGraderName, aModuleName, aProblemName);
//		
//		
//	}
	
	
	
	public void buildProblemHistories() {
		for (File interactionFile:interactionFiles) {
			SavedAllStudentsProblemGradingHistory newVal = parser.parseAllStudentsProblemGradingHistory(interactionFile.getAbsolutePath());
			if (newVal != null)
				problemHistory.add(newVal);
		}
		
		
	}
	
	public void buildStudentHistories() {
		
	}
	
	public static void main(String[] args) {
		ASavedHistoryManager manager = new ASavedHistoryManager();
		manager.readInteractionDirectory();
		manager.buildHistories();
	}
	
	


}
