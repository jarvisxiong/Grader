package grader.trace.interaction_logger;

import grader.interaction_logger.SavedAllStudentsProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedAllStudentsProblemGradingHistoryFilled extends SavedAllStudentsProblemGradingHistoryInfo{
	
	public SavedAllStudentsProblemGradingHistoryFilled(String aMessage, 
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

	
	
	public static SavedAllStudentsProblemGradingHistoryFilled newCase(
			SavedAllStudentsProblemGradingHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history created for problem:" + aSavedProblemGradingHistory.getProblemName();
		SavedAllStudentsProblemGradingHistoryFilled retVal = new SavedAllStudentsProblemGradingHistoryFilled(aMessage, aSavedProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
