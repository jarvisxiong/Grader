package grader.trace.interaction_logger;

import grader.interaction_logger.AllStudentsProblemHistory;
import grader.interaction_logger.StudentProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryEntered extends SavedStudentProblemGradingHistoryInfo{
	
	public SavedStudentProblemGradingHistoryEntered(String aMessage, 
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

	
	
	public static SavedStudentProblemGradingHistoryEntered newCase(
			StudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		String aMessage = "Problem grading history created for student:" + aSavedStudentProblemGradingHistory.getProblemName();
		SavedStudentProblemGradingHistoryEntered retVal = new SavedStudentProblemGradingHistoryEntered(aMessage, aSavedStudentProblemGradingHistory, aFinder);
		retVal.announce();		
		return retVal;
	}

}
