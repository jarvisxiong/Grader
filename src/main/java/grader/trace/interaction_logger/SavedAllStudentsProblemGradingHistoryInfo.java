package grader.trace.interaction_logger;

import grader.basics.trace.GraderInfo;
import grader.interaction_logger.manual_grading_stats.AllStudentsProblemHistory;

public class SavedAllStudentsProblemGradingHistoryInfo extends GraderInfo{
	AllStudentsProblemHistory savedProblemGradingHistory;
	
	public SavedAllStudentsProblemGradingHistoryInfo(String aMessage, 
			AllStudentsProblemHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public AllStudentsProblemHistory getSavedProblemGradingHistory() {
		return savedProblemGradingHistory;
	}

	public void setSavedProblemGradingHistory(
			AllStudentsProblemHistory savedProblemGradingHistory) {
		this.savedProblemGradingHistory = savedProblemGradingHistory;
	}

	
	
	

}
