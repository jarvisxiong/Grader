package grader.trace.interaction_logger;

import grader.interaction_logger.SavedAllStudentsProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedAllStudentsProblemGradingHistoryMerged extends SavedAllStudentsProblemGradingHistoryInfo{
	
	public SavedAllStudentsProblemGradingHistoryMerged(String aMessage, 
			SavedAllStudentsProblemGradingHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aSavedProblemGradingHistory, aFinder);
	}

	public SavedAllStudentsProblemGradingHistory getSavedProblemGradingHistory() {
		return savedProblemGradingHistory;
	}

	public void setSavedProblemGradingHistory(
			SavedAllStudentsProblemGradingHistory savedProblemGradingHistory) {
		this.savedProblemGradingHistory = savedProblemGradingHistory;
	}

	
	
	public static SavedAllStudentsProblemGradingHistoryMerged newCase(
			SavedAllStudentsProblemGradingHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history merged for problem:" + aSavedProblemGradingHistory.getProblemName();
		SavedAllStudentsProblemGradingHistoryMerged retVal = new SavedAllStudentsProblemGradingHistoryMerged(aMessage, aSavedProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
