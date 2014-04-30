package grader.trace.interaction_logger;

import grader.interaction_logger.manual_grading_stats.AllStudentsProblemHistory;
import grader.interaction_logger.manual_grading_stats.StudentProblemHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryEntered extends SavedStudentProblemGradingHistoryInfo{
	
	public SavedStudentProblemGradingHistoryEntered(String aMessage, 
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

	
	
	public static SavedStudentProblemGradingHistoryEntered newCase(
			StudentProblemHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history created for student:" + aSavedStudentProblemGradingHistory.getProblemName();
		SavedStudentProblemGradingHistoryEntered retVal = new SavedStudentProblemGradingHistoryEntered(aMessage, aSavedStudentProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
