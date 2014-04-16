package grader.interaction_logger.grading;

import framework.utils.GradingEnvironment;
import grader.interaction_logger.AnInteractionLogWriter;
import grader.interaction_logger.InteractionLogWriter;
import grader.interaction_logger.InteractionLogWriterSelector;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AGradingHistoryManager implements  GradingHistoryManager{
	String interactionDirectory;
	File[] interactionFiles;
	List<AllStudentsProblemHistory> problemHistory = new ArrayList();
	Map<String, AllStudentsProblemHistory> descriptionToHistory = new HashMap();
	GradingHistoryParser parser;
	GradingHistoryUnparser unparser;

	AllStudentsProblemHistory currentProblemHistory;
	InteractionLogWriter interactionLogWriter;
	
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#readInteractionDirectory()
	 */
	public AGradingHistoryManager() {
		interactionLogWriter = InteractionLogWriterSelector.getInteractionLogWriter();
		parser = GradingHistoryParserSelector.getSavedGradingHistoryParser();
		unparser = GradingHistoryUnparserSelector.getSavedGradingHistoryUnparser();
		interactionDirectory = AnInteractionLogWriter.getOrCreateInteractionFolder();

		
	}
	@Override
	public void readInteractionDirectory() {
		interactionFiles = FileTimeSorterAndComparator.sort(new File(interactionDirectory));
	

	}
	@Override
	public void connectToCurrentHistory() {
		buildCurrentProblemHistory();
		InteractionLogWriterSelector.getInteractionLogWriter().addLogListener(this);
	}
	
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#buildHistories()
	 */
	@Override
	public void buildHistories() {
		buildProblemHistories();
		
	}
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#buildProblemHistories()
	 */
	@Override
	public void buildProblemHistories() {
		for (File interactionFile:interactionFiles) {
			if (interactionFile.isDirectory() || 
					!interactionFile.getName().endsWith(".csv") ||
					interactionFile.getName().contains(AnInteractionLogWriter.SETTINGS_SUFFIX))
				continue;
			AllStudentsProblemHistory newVal = parser.parseAllStudentsProblemGradingHistory(interactionFile.getAbsolutePath());
			if (newVal != null) {
				String description = newVal.getModuleName() + ":" + newVal.getProblemName();
				AllStudentsProblemHistory oldProblemHistory = descriptionToHistory.get(description);
				if (oldProblemHistory != null) {
					oldProblemHistory.merge(newVal);
				} else {
					problemHistory.add(newVal);
					descriptionToHistory.put(description, newVal);
				}
			}
		}		
	}
	
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#unparseProblemHistories()
	 */
	@Override
	public void unparseProblemHistories() {
		for (AllStudentsProblemHistory history:problemHistory) {
			String unparsedValue = unparser.unparseAllStudentsProblemGradingHistory(history);
			System.out.println(unparsedValue);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#buildCurrentProblemHistory()
	 */
	@Override
	public void buildCurrentProblemHistory() {
		String fileName = interactionLogWriter.createModuleProblemInteractionLogName();
//		currentProblemHistory =  parser.parseAllStudentsProblemGradingHistory(interactionDirectory + "/" + fileName );
		currentProblemHistory =  parser.parseAllStudentsProblemGradingHistory(fileName );

	}
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#newCSVRow(java.lang.String[])
	 */
	
	@Override
	public void newCSVRow(String[] aRow) {
		StudentProblemGradingHistory studentHistory;
		
		System.out.println(aRow);
	}
	
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#buildStudentHistories()
	 */
	@Override
	public void buildStudentHistories() {
		
	}
	
	public static void main(String[] args) {
		GradingHistoryManager manager = new AGradingHistoryManager();
		manager.readInteractionDirectory();
		manager.buildHistories();
		manager.unparseProblemHistories();
//		manager.buildCurrentProblemHistory();
	}

	
	
	


}
