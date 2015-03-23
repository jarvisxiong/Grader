package grader.trace.interaction_logger;

import grader.interaction_logger.manual_grading_stats.AllStudentsProblemHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedAllStudentsProblemGradingHistoryCreated extends SavedAllStudentsProblemGradingHistoryInfo{
	
	public SavedAllStudentsProblemGradingHistoryCreated(String aMessage, 
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

	
	
	public static SavedAllStudentsProblemGradingHistoryCreated newCase(
			AllStudentsProblemHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history instantiated for problem:" + aSavedProblemGradingHistory.getProblemName();
		SavedAllStudentsProblemGradingHistoryCreated retVal = new SavedAllStudentsProblemGradingHistoryCreated(aMessage, aSavedProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
