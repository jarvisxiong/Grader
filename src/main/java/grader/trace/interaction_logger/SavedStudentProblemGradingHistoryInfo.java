package grader.trace.interaction_logger;

import grader.interaction_logger.manual_grading_stats.StudentProblemHistory;
import grader.trace.GraderInfo;

public class SavedStudentProblemGradingHistoryInfo extends GraderInfo{
	StudentProblemHistory savedStudentProblemGradingHistory;
	
	public SavedStudentProblemGradingHistoryInfo(String aMessage, 
			StudentProblemHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public StudentProblemHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			StudentProblemHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	

}
