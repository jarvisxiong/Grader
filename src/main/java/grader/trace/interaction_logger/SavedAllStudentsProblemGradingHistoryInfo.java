package grader.trace.interaction_logger;

import grader.interaction_logger.SavedAllStudentsProblemGradingHistory;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedAllStudentsProblemGradingHistoryInfo extends GraderInfo{
	SavedAllStudentsProblemGradingHistory savedProblemGradingHistory;
	
	public SavedAllStudentsProblemGradingHistoryInfo(String aMessage, 
			SavedAllStudentsProblemGradingHistory aSavedProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public SavedAllStudentsProblemGradingHistory getSavedProblemGradingHistory() {
		return savedProblemGradingHistory;
	}

	public void setSavedProblemGradingHistory(
			SavedAllStudentsProblemGradingHistory savedProblemGradingHistory) {
		this.savedProblemGradingHistory = savedProblemGradingHistory;
	}

	
	
	

}
