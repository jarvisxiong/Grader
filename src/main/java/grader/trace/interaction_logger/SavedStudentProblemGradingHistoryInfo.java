package grader.trace.interaction_logger;

import grader.interaction_logger.AllStudentsProblemHistory;
import grader.interaction_logger.StudentProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryInfo extends GraderInfo{
	StudentProblemGradingHistory savedStudentProblemGradingHistory;
	
	public SavedStudentProblemGradingHistoryInfo(String aMessage, 
			StudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public StudentProblemGradingHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			StudentProblemGradingHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	

}
