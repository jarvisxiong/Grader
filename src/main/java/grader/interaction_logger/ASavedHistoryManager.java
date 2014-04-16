package grader.interaction_logger;

import framework.utils.GradingEnvironment;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASavedHistoryManager {
	String interactionDirectory;
	File[] interactionFiles;
	List<SavedAllStudentsProblemGradingHistory> problemHistory = new ArrayList();
	Map<String, SavedAllStudentsProblemGradingHistory> descriptionToHistory = new HashMap();
	SavedGradingHistoryParser parser;
	SavedGradingHistoryUnparser unparser;

	SavedAllStudentsProblemGradingHistory currentProblemHistory;
	InteractionLogWriter interactionLogWriter;
	
	public void readInteractionDirectory() {
		interactionDirectory = AnInteractionLogWriter.getOrCreateInteractionFolder();
		interactionFiles = FileTimeSorterAndComparator.sort(new File(interactionDirectory));
		interactionLogWriter = InteractionLogWriterSelector.getInteractionLogWriter();
		parser = SavedGradingHistoryParserSelector.getSavedGradingHistoryParser();
		unparser = SavedGradingHistoryUnparserSelector.getSavedGradingHistoryUnparser();

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
			if (interactionFile.isDirectory() || 
					!interactionFile.getName().endsWith(".csv") ||
					interactionFile.getName().contains(AnInteractionLogWriter.SETTINGS_SUFFIX))
				continue;
			SavedAllStudentsProblemGradingHistory newVal = parser.parseAllStudentsProblemGradingHistory(interactionFile.getAbsolutePath());
			if (newVal != null) {
				String description = newVal.getModuleName() + ":" + newVal.getProblemName();
				SavedAllStudentsProblemGradingHistory oldProblemHistory = descriptionToHistory.get(description);
				if (oldProblemHistory != null) {
					oldProblemHistory.merge(newVal);
				} else {
					problemHistory.add(newVal);
					descriptionToHistory.put(description, newVal);
				}
			}
		}		
	}
	
	public void unparseProblemHistories() {
		for (SavedAllStudentsProblemGradingHistory history:problemHistory) {
			String unparsedValue = unparser.unparseAllStudentsProblemGradingHistory(history);
			System.out.println(unparsedValue);
		}
		
	}
	
	public void buildCurrentProblemHistory() {
		String fileName = interactionLogWriter.createModuleProblemInteractionLogName();
		currentProblemHistory =  parser.parseAllStudentsProblemGradingHistory(interactionDirectory + "/" + fileName );
	}
	
	public void buildStudentHistories() {
		
	}
	
	public static void main(String[] args) {
		ASavedHistoryManager manager = new ASavedHistoryManager();
		manager.readInteractionDirectory();
		manager.buildHistories();
		manager.unparseProblemHistories();
//		manager.buildCurrentProblemHistory();
	}
	
	


}
