package grader.stats;

import java.util.List;

import grader.interaction_logger.AnInteractionLogReader;
import grader.interaction_logger.AnInteractionLogWriter;
import grader.interaction_logger.InteractionLogReader;
import grader.interaction_logger.InteractionLogWriter;
import grader.trace.settings.AutomaticNavigationEnded;
import grader.trace.settings.AutomaticNavigationStarted;
import grader.trace.settings.GraderSettingsInfo;
import grader.trace.settings.ManualNavigationEnded;
import grader.trace.settings.ManualNavigationStarted;
import grader.trace.settings.NavigationStarted;
import grader.trace.stats.SavedAllStudentsProblemGradingHistoryCreated;
import grader.trace.stats.SavedAllStudentsProblemGradingHistoryFilled;
import grader.trace.stepper.ProjectStepEnded;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperStarted;
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
	
	/* (non-Javadoc)
	 * @see grader.stats.SavedAllStudentsProblemGradingHistoryParser#parseHistory()
	 */
	@Override
	public SavedAllStudentsProblemGradingHistory parseAllStudentsProblemGradingHistory(String aFileName) {
		logReader = new AnInteractionLogReader(aFileName);
		table = logReader.getTable();
		String[] fileParts = aFileName.split(AnInteractionLogWriter.SEPARATOR);
		String[] csvParts = fileParts[3].split("\\.");
		history = new ASavedAllProblemsGradingHistory(fileParts[0], fileParts[2], csvParts[0]);
		SavedAllStudentsProblemGradingHistoryCreated.newCase(history, this);
		while (hasMoreNavigations()) {
			parseNavigation();
		}
		SavedAllStudentsProblemGradingHistoryFilled.newCase(history, this);
		return history;

		
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
			endVisitIndex = endPhaseIndex;
			String[] endRow = table.get(endVisitIndex);
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			currentStudentHistory.setVisitEndTime(endTime);

	
		} else {
			String[] endRow = table.get(endVisitIndex);
			double totalScore = ProjectStepEnded.totalScoreFromCSVRow(endRow);
			currentStudentHistory.setTotalScore(totalScore);
			currentStudentHistory.setVisitEndTime(ProjectStepEnded.timeStampFromCSVRow(endRow));
			endTime = ProjectStepEnded.timeStampFromCSVRow(endRow);
			String currentName = ProjectStepStarted.nameFromCSVRow(endRow);
			currentStudentHistory.setName(currentName);
		}
		
		long visitTime = endTime - beginTime;
		if (isAutomaticPhase)
			currentStudentHistory.addAutoVisitTime(visitTime);
		else
			currentStudentHistory.addManualVisitTime(visitTime);
		
		history.newStudentHistory(currentOnyen, currentStudentHistory);
		currentRowIndex = endVisitIndex;
		
	}
	
	
	public static void main (String[] args) {
		SavedGradingHistoryParser parser = new ASavedGradingHistoryParser();
		parser.parseAllStudentsProblemGradingHistory("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
	}
	
	
	

}
