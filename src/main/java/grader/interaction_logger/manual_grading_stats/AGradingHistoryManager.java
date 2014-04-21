package grader.interaction_logger.manual_grading_stats;

import framework.utils.GradingEnvironment;
import grader.interaction_logger.AnInteractionLogWriter;
import grader.interaction_logger.InteractionLogWriter;
import grader.interaction_logger.InteractionLogWriterSelector;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.misc.Common;

public class AGradingHistoryManager implements  GradingHistoryManager{
	public static final String PROBLEM_STATS = "problems";
	public static final String STUDENT_STATS = "students";
	public static final String SUFFIUX = ".txt";

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
	public  String getProblemHistoryFileName (String aModule, String aProblem ) {
		return interactionDirectory + "/" + PROBLEM_STATS + "/" + aModule + "/" + aProblem + ".txt";
		
	}
	@Override
	public  File getOrCreateProblemHistoryFile(String aModule, String aProblem ) {
		File file = new File (getProblemHistoryFileName(aModule, aProblem));
		if (!file.exists()) {
			boolean success = file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	@Override
	public String getSavedProblemHistoryTextOfCurrentModuleProblem() {
		String aModule = GraderSettingsModelSelector.getGraderSettingsModel().getCurrentModule();
		String aProblem = GraderSettingsModelSelector.getGraderSettingsModel().getCurrentProblem();
		return getProblemHistoryText(aModule, aProblem);
	}
	@Override
	public void setProblemHistoryTextOfCurrentModuleProblem(String newVal) {
		String aModule = GraderSettingsModelSelector.getGraderSettingsModel().getCurrentModule();
		String aProblem = GraderSettingsModelSelector.getGraderSettingsModel().getCurrentProblem();
		setProblemHistoryText(aModule, aProblem, newVal);
	}
	@Override
	public void setProblemHistoryTextOfCurrentModuleProblem() {
		String log = getProblemHistoryTextOfCurrentModuleProblem();
		String aggregate = getAggregateProblemHistoryTextOfCurrentModuleProblem();
		setProblemHistoryTextOfCurrentModuleProblem(aggregate + "\n" + log);
	}
	@Override
	public String getProblemHistoryTextOfCurrentModuleProblem() {
		return unparser.unparseAllStudentsProblemGradingHistory(currentProblemHistory);
		
	}
	@Override
	public String getAggregateProblemHistoryTextOfCurrentModuleProblem() {
		return unparser.getAggregateStatistics(currentProblemHistory);
		
	}
	@Override
	public String getProblemHistoryText(String aModule, String aProblem) {
		File file = getOrCreateProblemHistoryFile(aModule, aProblem);
		return Common.toText(file);
	}
	@Override
	public void setProblemHistoryText(String aModule, String aProblem, String aText) {
		File file = getOrCreateProblemHistoryFile(aModule, aProblem);
		try {
			Common.writeText(file, aText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//		System.out.println(unparser.unparseAllStudentsProblemGradingHistory(currentProblemHistory));

	}
	/* (non-Javadoc)
	 * @see grader.interaction_logger.InteractionHistoryManager#newCSVRow(java.lang.String[])
	 */
	
	public static String[] scanCSVRow(String aCSVRow) {
		return aCSVRow.split(",");
	}
	
	public static List<String[]> scanCSVRow(List<String> aCSVRows) {
		List<String[]> retVal = new ArrayList(aCSVRows.size());
		for (String aCSVRow: aCSVRows)
			retVal.add(scanCSVRow(aCSVRow));
		return retVal;
	}
	
	@Override
	public void newStep(List<String> aRows) {
		
//		System.out.println(aRows);
//		System.out.println(scanCSVRow(aRows));
		StudentProblemGradingHistory newVisit = parser.parseStudentHistory(scanCSVRow(aRows));
		currentProblemHistory.newStudentHistory(newVisit.getOnyen(), newVisit);
	
//		String newState = unparser.unparseAllStudentsProblemGradingHistory(currentProblemHistory);
//		System.out.println(newState);
	}
	@Override
	public void newNavigation(List<String> aRows) {
		parser.initNewNavigation(scanCSVRow(aRows));
		
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
		manager.getOrCreateProblemHistoryFile("comp110", "Assignment 3");
//		manager.buildCurrentProblemHistory();
	}
	

	
	
	


}
