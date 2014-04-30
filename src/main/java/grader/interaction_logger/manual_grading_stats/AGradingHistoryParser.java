package grader.interaction_logger.manual_grading_stats;

import java.io.File;
import java.util.List;
import java.util.Map;

import grader.interaction_logger.AnInteractionLogReader;
import grader.interaction_logger.AnInteractionLogWriter;
import grader.interaction_logger.InteractionLogReader;
import grader.trace.file.open.DefaultProgramOpenedFile;
import grader.trace.interaction_logger.SavedAllStudentsProblemGradingHistoryCreated;
import grader.trace.interaction_logger.SavedAllStudentsProblemGradingHistoryFilled;
import grader.trace.settings.AutomaticNavigationEnded;
import grader.trace.settings.AutomaticNavigationStarted;
import grader.trace.settings.GraderSettingsInfo;
import grader.trace.settings.ManualNavigationEnded;
import grader.trace.settings.ManualNavigationStarted;
import grader.trace.settings.NavigationStarted;
import grader.trace.stepper.FeedbackVisited;
import grader.trace.stepper.ProblemHistoryVisited;
import grader.trace.stepper.ProjectStepAborted;
import grader.trace.stepper.ProjectStepEnded;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperEnded;
import grader.trace.stepper.ProjectStepperStarted;
import grader.trace.stepper.SourceVisited;
import grader.trace.stepper.StudentHistoryVisited;
import grader.trace.stepper.UserQuit;

public class AGradingHistoryParser implements GradingHistoryParser {
	
	InteractionLogReader logReader;
	int currentRowIndex;
	int endPhaseIndex;
	int endVisitIndex;
//	String[] currentRow;
	List<String[]> table;
	AllStudentsProblemHistory history;
	String currentOnyen = "";
	StudentProblemHistory currentStudentHistory;
	boolean isAutomaticPhase;
	String graderName;
	
	public AGradingHistoryParser() {
		
	
	}
	public static final int PARTS_IN_LOG_FILE_NAME = 4;

	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#parseHistory()
	 */
	@Override
	public AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(String aFullFileName) {
		try {
			currentRowIndex = 0;
			endPhaseIndex = 0;
			endVisitIndex = 0;
//		logReader = new AnInteractionLogReader(aFullFileName);
//		table = logReader.getTable();
		table = AnInteractionLogReader.toCSVTable(aFullFileName);
		File file = new File(aFullFileName);
		String aFileName = file.getName();
		String[] fileParts = aFileName.split(AnInteractionLogWriter.SEPARATOR);
		if (fileParts.length != PARTS_IN_LOG_FILE_NAME) return null;
		String[] csvParts = fileParts[fileParts.length - 1].split("\\.");
		if (csvParts.length != 2) return null;
		return parseAllStudentsProblemGradingHistory(table, fileParts[fileParts.length - 4], fileParts[fileParts.length - 2], csvParts[0]);
//		history = new AnAllStudentsProblemHistory(fileParts[fileParts.length - 4], fileParts[fileParts.length - 2], csvParts[0]);
//		SavedAllStudentsProblemGradingHistoryCreated.newCase(history, this);
//		while (hasMoreNavigations()) {
//			parseNavigation();
//		}
//		SavedAllStudentsProblemGradingHistoryFilled.newCase(history, this);
//		return history;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}
	public AllStudentsProblemHistory parseAllStudentsProblemGradingHistory(List<String[]> aTable, String aUserName, String aModuleName, String aProblemName) {
		try {
//			currentRowIndex = 0;
//			endPhaseIndex = 0;
//			endVisitIndex = 0;
//		logReader = new AnInteractionLogReader(aFullFileName);
			graderName = aUserName;
		table = aTable;
		logReader = new AnInteractionLogReader(aTable);

		
		history = new AnAllStudentsProblemHistory(aUserName, aModuleName, aProblemName);
		SavedAllStudentsProblemGradingHistoryCreated.newCase(history, this);
		while (hasMoreNavigations()) {
			parseNavigation();
		}
		SavedAllStudentsProblemGradingHistoryFilled.newCase(history, this);
		return history;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#parseNavigation()
	 */
	@Override
	public void parseNavigation() {
		while (hasMoreVisits()) {
			processNextVisit();
		}
		currentRowIndex = endPhaseIndex;
			
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#hasMoreNavigations()
	 */
	@Override
	public boolean hasMoreNavigations() {	
			if (currentRowIndex < 0)
				return false;
		
			currentRowIndex = logReader.nextRowIndex(NavigationStarted.class, currentRowIndex); 
			if (currentRowIndex < 0)
				return false;
			currentRowIndex++;
			if (currentRowIndex >= table.size())
				return false;
			String[] navigationDescription = table.get(currentRowIndex);
			String aClassName = GraderSettingsInfo.classNameFromCSVRow(navigationDescription);
			if (aClassName.equals(ManualNavigationStarted.class.getSimpleName())) {
				isAutomaticPhase = false;
				endPhaseIndex = logReader.nextRowIndex(UserQuit.class, currentRowIndex);
			} else if (aClassName.equals(AutomaticNavigationStarted.class.getSimpleName())) {
				isAutomaticPhase = true;
				endPhaseIndex = logReader.nextRowIndex(AutomaticNavigationEnded.class, currentRowIndex);
			} else
				return false;
			
			if (endPhaseIndex < 0)
				logReader.nextRowIndex(ProjectStepperStarted.class, currentRowIndex); 
			if (endPhaseIndex < 0)
				endPhaseIndex = table.size() - 1;
			
			return true;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#hasMoreVisits()
	 */
	@Override
	public boolean hasMoreVisits() {
		currentRowIndex = logReader.nextRowIndex(ProjectStepStarted.class, currentRowIndex, endPhaseIndex);
		if (currentRowIndex < 0) return false;
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#processNextVisit()
	 */
	@Override
	public void processNextVisit() {
//		String[] beginRow = table.get(currentRowIndex);
//		long beginTime = ProjectStepStarted.timeStampFromCSVRow(beginRow);
//		long endTime;
//
//		String currentOnyen =  ProjectStepStarted.onyenFromCSVRow(beginRow);
//		currentStudentHistory = new AStudentProblemHistory(history.getModuleName(), history.getProblemName(), currentOnyen);
//		 endVisitIndex = logReader.nextRowIndex(ProjectStepEnded.class, currentRowIndex, endPhaseIndex);
//		
//		if (endVisitIndex < 0) {
//			 endVisitIndex = logReader.nextRowIndex(ProjectStepAborted.class, currentRowIndex, endPhaseIndex);
//			 if (endVisitIndex >= 0)  { // aborted
//					String[] endRow = table.get(endVisitIndex);
//
//					 currentStudentHistory.setManualOverallNotes("Unable to  grade student");
//					 endTime = ProjectStepAborted.timeStampFromCSVRow(endRow);
//
//				 }
//			 else {
//
//			endVisitIndex = endPhaseIndex;
//			String[] endRow = table.get(endVisitIndex);
//			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
//			currentStudentHistory.setVisitEndTime(endTime);
//			 } 
//
//	
//		} else {
//			String[] endRow = table.get(endVisitIndex);
//			double totalScore = ProjectStepEnded.totalScoreFromCSVRow(endRow);
//			double featuresScore = ProjectStepEnded.featuresScoreFromCSVRow(endRow);
//			double multiplier = ProjectStepEnded.multiplierFromCSVRow(endRow);
//			double sourcePoints = ProjectStepEnded.sourcePointsFromCSVRow(endRow);
//			currentStudentHistory.setTotalScore(totalScore);
//			currentStudentHistory.setFeaturesScore(featuresScore);
//			currentStudentHistory.setMultiplier(multiplier);
//			currentStudentHistory.setSourcePoints(sourcePoints);
//			currentStudentHistory.setVisitEndTime(ProjectStepEnded.timeStampFromCSVRow(endRow));
//			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
//			String currentName = ProjectStepStarted.nameFromCSVRow(endRow);
//			currentStudentHistory.setName(currentName);
//			String overviewComments = ProjectStepEnded.overallNotesFromCSVRow(endRow);
//			String sourceComments = ProjectStepEnded.sourceNotesFromCSVRow(endRow);
//			Map<String, String> featureNotes = ProjectStepEnded.featureNotesFromCSVRow(endRow);
//			if (!overviewComments.isEmpty())
//				currentStudentHistory.setManualOverallNotes(overviewComments);
//			if (!sourceComments.isEmpty())
//				currentStudentHistory.setSourceComments(sourceComments);
//			if (featureNotes.size() > 0)
//				currentStudentHistory.setFeatureToManualNotes(featureNotes);
//		}
//		
//		long visitTime = endTime - beginTime;
//		if (isAutomaticPhase)
//			currentStudentHistory.addAutoVisitTime(visitTime);
//		else {
//			currentStudentHistory.addManualVisitTime(visitTime);
//			int index = logReader.nextRowIndex(SourceVisited.class, currentRowIndex, endPhaseIndex);
//			if (index >= 0) {
//				currentStudentHistory.setSourceVisited();
//			}
//			index = logReader.nextRowIndex(DefaultProgramOpenedFile.class, currentRowIndex, endPhaseIndex);
//			if (index >= 0) {
//				currentStudentHistory.setSourceOpened();
//			}
//			index = logReader.nextRowIndex(FeedbackVisited.class, currentRowIndex, endPhaseIndex);
//			if (index >= 0) {
//				currentStudentHistory.setFeedbackVisited();
//			}
//		}
		currentStudentHistory = parseNextStudentHistory();
		
		history.newStudentHistory(currentOnyen, currentStudentHistory);
		currentRowIndex = endVisitIndex;
		
	}
	
	void setTable (List<String[]> aTable) {
		table = aTable;
		currentRowIndex = 0;
		logReader.setTable(aTable);
	}
	@Override
	public boolean initNewNavigation(List<String[]> aTable) {
		setTable(aTable);
//		table = aTable;
//		currentRowIndex = 0;
//		logReader.setTable(aTable);
		return hasMoreNavigations();
	}
	
	
	@Override
	public StudentProblemHistory parseStudentHistory(List<String[]> aTable) {
		setTable(aTable);
		endPhaseIndex = aTable.size(); 
//		table = aTable;
//		currentRowIndex = 0;
//		logReader.setTable(aTable);
		if (hasMoreVisits()) // consume header info
		return parseNextStudentHistory();
		else
			return null;
		
	}
	
	
	 StudentProblemHistory parseNextStudentHistory() {
		String[] beginRow = table.get(currentRowIndex);
		long beginTime = ProjectStepStarted.timeStampFromCSVRow(beginRow);
		long endTime;

		 currentOnyen =  ProjectStepStarted.onyenFromCSVRow(beginRow);
//		 String name = ProjectStepStarted.nameFromCSVRow(beginRow);
		StudentProblemHistory retVal = new AStudentProblemHistory(graderName, history.getModuleName(), history.getProblemName(), currentOnyen);
		 endVisitIndex = logReader.nextRowIndex(ProjectStepEnded.class, currentRowIndex, endPhaseIndex);
		
		if (endVisitIndex < 0) {
			 endVisitIndex = logReader.nextRowIndex(ProjectStepAborted.class, currentRowIndex, endPhaseIndex);
			 if (endVisitIndex >= 0)  { // aborted
					String[] endRow = table.get(endVisitIndex);

					 retVal.setManualOverallNotes("Unable to  grade student");
					 endTime = ProjectStepAborted.timeStampFromCSVRow(endRow);

				 }
			 else {

			endVisitIndex = endPhaseIndex;
			String[] endRow = table.get(endVisitIndex);
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			retVal.setVisitEndTime(endTime);
			 } 

	
		} else {
			String[] endRow = table.get(endVisitIndex);
			double totalScore = ProjectStepEnded.totalScoreFromCSVRow(endRow);
			double featuresScore = ProjectStepEnded.featuresScoreFromCSVRow(endRow);
			double multiplier = ProjectStepEnded.multiplierFromCSVRow(endRow);
			double sourcePoints = ProjectStepEnded.sourcePointsFromCSVRow(endRow);
			retVal.setTotalScore(totalScore);
			retVal.setFeaturesScore(featuresScore);
			retVal.setMultiplier(multiplier);
			retVal.setSourcePoints(sourcePoints);
			retVal.setVisitEndTime(ProjectStepEnded.timeStampFromCSVRow(endRow));
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			String currentName = ProjectStepStarted.nameFromCSVRow(endRow);
			retVal.setName(currentName);
			String overviewComments = ProjectStepEnded.overallNotesFromCSVRow(endRow);
			String sourceComments = ProjectStepEnded.sourceNotesFromCSVRow(endRow);
			Map<String, String> featureNotes = ProjectStepEnded.featureNotesFromCSVRow(endRow);
			if (!overviewComments.isEmpty())
				retVal.setManualOverallNotes(overviewComments);
			if (!sourceComments.isEmpty())
				retVal.setSourceComments(sourceComments);
			if (featureNotes.size() > 0)
				retVal.setFeatureToManualNotes(featureNotes);
		}
		
		long visitTime = endTime - beginTime;
		if (isAutomaticPhase)
			retVal.addAutoVisitTime(visitTime);
		else {
			retVal.addManualVisitTime(visitTime);
			int index = logReader.nextRowIndex(SourceVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				retVal.setSourceVisited();
			}
			index = logReader.nextRowIndex(DefaultProgramOpenedFile.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				retVal.setSourceOpened();
			}
			index = logReader.nextRowIndex(FeedbackVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				retVal.setFeedbackVisited();
			}
			index = logReader.nextRowIndex(ProblemHistoryVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				retVal.setProblemHistoryVisited();
			}
			index = logReader.nextRowIndex(StudentHistoryVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				retVal.setStudentHistoryVisited();
			}
		}
		
		return retVal;
		
	}
	
	
	public static void main (String[] args) {
		GradingHistoryParser parser = new AGradingHistoryParser();
		parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
	}
	
	
	

}
