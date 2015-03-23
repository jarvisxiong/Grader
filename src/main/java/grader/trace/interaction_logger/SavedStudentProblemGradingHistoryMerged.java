package grader.trace.interaction_logger;

import grader.interaction_logger.manual_grading_stats.AllStudentsProblemHistory;
import grader.interaction_logger.manual_grading_stats.StudentProblemHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryMerged extends SavedStudentProblemGradingHistoryInfo{
	
	public SavedStudentProblemGradingHistoryMerged(String aMessage, 
			StudentProblemHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aSavedStudentProblemGradingHistory, aFinder);
	}

	public StudentProblemHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			StudentProblemHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	public static SavedStudentProblemGradingHistoryMerged newCase(
			StudentProblemHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history merged for student:" + aSavedStudentProblemGradingHistory.getProblemName();
		SavedStudentProblemGradingHistoryMerged retVal = new SavedStudentProblemGradingHistoryMerged(aMessage, aSavedStudentProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
