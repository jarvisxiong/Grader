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
import grader.trace.stepper.ProjectStepEnded;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperStarted;

public class AProblemManualGradingHistoryParser {
	
	InteractionLogReader logReader;
	int currentRowIndex;
	int endPhaseIndex;
	int endVisitIndex;
//	String[] currentRow;
	List<String[]> table;
	ProblemManualGradingHistory history;
	String currentOnyen = "";
	StudentManualGradingHistory currentStudentHistory;
	boolean isAutomaticPhase;
	
	public AProblemManualGradingHistoryParser(String aFileName) {
		logReader = new AnInteractionLogReader(aFileName);
		table = logReader.getTable();
		String[] fileParts = aFileName.split(AnInteractionLogWriter.SEPARATOR);
		String[] csvParts = fileParts[3].split("\\.");
		history = new AProblemManualGradingHistory(fileParts[0], fileParts[2], csvParts[0]);
	
	}
	
	public void parseHistory() {
		while (hasMoreNavigations()) {
			parseNavigation();
		}
		
	}
	
	public void parseNavigation() {
		while (hasMoreVisits()) {
			processNextVisit();
		}
			
		
	}
	
	public boolean hasMoreNavigations() {	
		
			currentRowIndex = logReader.nextRowIndex(ProjectStepperStarted.class, currentRowIndex); 
			if (currentRowIndex < 0)
				return false;
			currentRowIndex++;
			if (currentRowIndex >= table.size())
				return false;
			String[] navigationDescription = table.get(currentRowIndex);
			String aClassName = GraderSettingsInfo.classNameFromCSVRow(navigationDescription);
			if (aClassName.equals(ManualNavigationStarted.class.getSimpleName())) {
				isAutomaticPhase = false;
				endPhaseIndex = logReader.nextRowIndex(ManualNavigationEnded.class);
			} else if (aClassName.equals(AutomaticNavigationStarted.class.getSimpleName())) {
				isAutomaticPhase = true;
				endPhaseIndex = logReader.nextRowIndex(AutomaticNavigationEnded.class);
			} else
				return false;
			
			if (endPhaseIndex < 0)
				logReader.nextRowIndex(ProjectStepperStarted.class, currentRowIndex); 
			if (endPhaseIndex < 0)
				endPhaseIndex = table.size() - 1;
			
			return true;
		
	}
	
	
	
	public boolean hasMoreVisits() {
		currentRowIndex = logReader.nextRowIndex(ProjectStepStarted.class, currentRowIndex, endPhaseIndex);
		if (currentRowIndex < 0) return false;
		return true;
		
	}
	
	public void processNextVisit() {
		String[] beginRow = table.get(currentRowIndex);
		String currentOnyen =  ProjectStepStarted.onyenFromCSVRow(beginRow);
		currentStudentHistory = new AStudentManualGradingHistory(history.getModuleName(), history.getProblemName());
		 endVisitIndex = logReader.nextRowIndex(ProjectStepEnded.class, currentRowIndex);
		if (endVisitIndex < - 1)
			endVisitIndex = endPhaseIndex;
		else {
			String[] endRow = table.get(endVisitIndex);
			double totalScore = ProjectStepEnded.totalScoreFromCSVRow(endRow);
			currentStudentHistory.setTotalScore(totalScore);
		}
		
		history.newStudentHistory(currentOnyen, currentStudentHistory);
		
	}
	
	
	public static void main (String[] args) {
		AProblemManualGradingHistoryParser parser = new AProblemManualGradingHistoryParser("log/AssignmentsData/interactionLogs/Dewan_interactionLog_Comp110_Assignment3.csv");
		parser.parseHistory();
	}
	
	
	

}
