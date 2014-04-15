package grader.trace.interaction_logger;

import grader.interaction_logger.SavedAllStudentsProblemGradingHistory;
import grader.interaction_logger.SavedStudentProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryMerged extends SavedStudentProblemGradingHistoryInfo{
	
	public SavedStudentProblemGradingHistoryMerged(String aMessage, 
			SavedStudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aSavedStudentProblemGradingHistory, aFinder);
	}

	public SavedStudentProblemGradingHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			SavedStudentProblemGradingHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	public static SavedStudentProblemGradingHistoryMerged newCase(
			SavedStudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history merged for student:" + aSavedStudentProblemGradingHistory.getProblemName();
		SavedStudentProblemGradingHistoryMerged retVal = new SavedStudentProblemGradingHistoryMerged(aMessage, aSavedStudentProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
