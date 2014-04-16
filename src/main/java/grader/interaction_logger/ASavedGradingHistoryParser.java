package grader.interaction_logger;

import java.io.File;
import java.util.List;
import java.util.Map;

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
import grader.trace.stepper.ProjectStepAborted;
import grader.trace.stepper.ProjectStepEnded;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperEnded;
import grader.trace.stepper.ProjectStepperStarted;
import grader.trace.stepper.SourceVisited;
import grader.trace.stepper.UserQuit;

public class ASavedGradingHistoryParser implements SavedGradingHistoryParser {
	
	InteractionLogReader logReader;
	int currentRowIndex;
	int endPhaseIndex;
	int endVisitIndex;
//	String[] currentRow;
	List<String[]> table;
	SavedAllStudentsProblemGradingHistory history;
	String currentOnyen = "";
	SavedStudentProblemGradingHistory currentStudentHistory;
	boolean isAutomaticPhase;
	
	public ASavedGradingHistoryParser() {
		
	
	}
	public static final int PARTS_IN_LOG_FILE_NAME = 4;

	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#parseHistory()
	 */
	@Override
	public SavedAllStudentsProblemGradingHistory parseAllStudentsProblemGradingHistory(String aFullFileName) {
		try {
		logReader = new AnInteractionLogReader(aFullFileName);
		table = logReader.getTable();
		File file = new File(aFullFileName);
		String aFileName = file.getName();
		String[] fileParts = aFileName.split(AnInteractionLogWriter.SEPARATOR);
		if (fileParts.length < PARTS_IN_LOG_FILE_NAME) return null;
		String[] csvParts = fileParts[fileParts.length - 1].split("\\.");
		if (csvParts.length != 2) return null;
		history = new ASavedAllStudentsProblemGradingHistory(fileParts[fileParts.length - 4], fileParts[fileParts.length - 2], csvParts[0]);
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
		String[] beginRow = table.get(currentRowIndex);
		long beginTime = ProjectStepStarted.timeStampFromCSVRow(beginRow);
		long endTime;

		String currentOnyen =  ProjectStepStarted.onyenFromCSVRow(beginRow);
		currentStudentHistory = new ASavedStudentProblemHistory(history.getModuleName(), history.getProblemName(), currentOnyen);
		 endVisitIndex = logReader.nextRowIndex(ProjectStepEnded.class, currentRowIndex, endPhaseIndex);
		
		if (endVisitIndex < 0) {
			 endVisitIndex = logReader.nextRowIndex(ProjectStepAborted.class, currentRowIndex, endPhaseIndex);
			 if (endVisitIndex >= 0)  { // aborted
					String[] endRow = table.get(endVisitIndex);

					 currentStudentHistory.setManualOverallNotes("Unable to  grade student");
					 endTime = ProjectStepAborted.timeStampFromCSVRow(endRow);

				 }
			 else {

			endVisitIndex = endPhaseIndex;
			String[] endRow = table.get(endVisitIndex);
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			currentStudentHistory.setVisitEndTime(endTime);
			 } 

	
		} else {
			String[] endRow = table.get(endVisitIndex);
			double totalScore = ProjectStepEnded.totalScoreFromCSVRow(endRow);
			double featuresScore = ProjectStepEnded.featuresScoreFromCSVRow(endRow);
			double multiplier = ProjectStepEnded.multiplierFromCSVRow(endRow);
			double sourcePoints = ProjectStepEnded.sourcePointsFromCSVRow(endRow);
			currentStudentHistory.setTotalScore(totalScore);
			currentStudentHistory.setFeaturesScore(featuresScore);
			currentStudentHistory.setMultiplier(multiplier);
			currentStudentHistory.setSourcePoints(sourcePoints);
			currentStudentHistory.setVisitEndTime(ProjectStepEnded.timeStampFromCSVRow(endRow));
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			String currentName = ProjectStepStarted.nameFromCSVRow(endRow);
			currentStudentHistory.setName(currentName);
			String overviewComments = ProjectStepEnded.overallNotesFromCSVRow(endRow);
			String sourceComments = ProjectStepEnded.sourceNotesFromCSVRow(endRow);
			Map<String, String> featureNotes = ProjectStepEnded.featureNotesFromCSVRow(endRow);
			if (!overviewComments.isEmpty())
				currentStudentHistory.setManualOverallNotes(overviewComments);
			if (!sourceComments.isEmpty())
				currentStudentHistory.setSourceComments(sourceComments);
			if (featureNotes.size() > 0)
				currentStudentHistory.setFeatureToManualNotes(featureNotes);
		}
		
		long visitTime = endTime - beginTime;
		if (isAutomaticPhase)
			currentStudentHistory.addAutoVisitTime(visitTime);
		else {
			currentStudentHistory.addManualVisitTime(visitTime);
			int index = logReader.nextRowIndex(SourceVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				currentStudentHistory.setSourceVisited();
			}
			index = logReader.nextRowIndex(DefaultProgramOpenedFile.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				currentStudentHistory.setSourceOpened();
			}
			index = logReader.nextRowIndex(FeedbackVisited.class, currentRowIndex, endPhaseIndex);
			if (index >= 0) {
				currentStudentHistory.setFeedbackVisited();
			}
		}
		
		history.newStudentHistory(currentOnyen, currentStudentHistory);
		currentRowIndex = endVisitIndex;
		
	}
	
	
	public static void main (String[] args) {
		SavedGradingHistoryParser parser = new ASavedGradingHistoryParser();
		parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
	}
	
	
	

}
