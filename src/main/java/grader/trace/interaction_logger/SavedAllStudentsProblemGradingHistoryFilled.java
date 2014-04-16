package grader.trace.interaction_logger;

import grader.interaction_logger.grading.AllStudentsProblemHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedAllStudentsProblemGradingHistoryFilled extends SavedAllStudentsProblemGradingHistoryInfo{
	
	public SavedAllStudentsProblemGradingHistoryFilled(String aMessage, 
			AllStudentsProblemHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aSavedProblemGradingHistory, aFinder);
	}

	public AllStudentsProblemHistory getSavedProblemGradingHistory() {
		return savedProblemGradingHistory;
	}

	public void setSavedProblemGradingHistory(
			AllStudentsProblemHistory savedProblemGradingHistory) {
		this.savedProblemGradingHistory = savedProblemGradingHistory;
	}

	
	
	public static SavedAllStudentsProblemGradingHistoryFilled newCase(
			AllStudentsProblemHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history created for problem:" + aSavedProblemGradingHistory.getProblemName();
		SavedAllStudentsProblemGradingHistoryFilled retVal = new SavedAllStudentsProblemGradingHistoryFilled(aMessage, aSavedProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
