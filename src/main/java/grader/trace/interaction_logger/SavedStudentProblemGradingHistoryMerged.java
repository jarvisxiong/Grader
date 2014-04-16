package grader.trace.interaction_logger;

import grader.interaction_logger.AllStudentsProblemHistory;
import grader.interaction_logger.StudentProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryMerged extends SavedStudentProblemGradingHistoryInfo{
	
	public SavedStudentProblemGradingHistoryMerged(String aMessage, 
			StudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aSavedStudentProblemGradingHistory, aFinder);
	}

	public StudentProblemGradingHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			StudentProblemGradingHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	public static SavedStudentProblemGradingHistoryMerged newCase(
			StudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history merged for student:" + aSavedStudentProblemGradingHistory.getProblemName();
		SavedStudentProblemGradingHistoryMerged retVal = new SavedStudentProblemGradingHistoryMerged(aMessage, aSavedStudentProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
