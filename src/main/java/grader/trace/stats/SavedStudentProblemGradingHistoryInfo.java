package grader.trace.stats;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.stats.SavedAllStudentsProblemGradingHistory;
import grader.stats.SavedStudentProblemGradingHistory;
import grader.trace.GraderInfo;
import grader.trace.stepper.AutoAutoGradeSet;

public class SavedStudentProblemGradingHistoryInfo extends GraderInfo{
	SavedStudentProblemGradingHistory savedStudentProblemGradingHistory;
	
	public SavedStudentProblemGradingHistoryInfo(String aMessage, 
			SavedStudentProblemGradingHistory aSavedStudentProblemGradingHistory,			
			Object aFinder) {
		super(aMessage, aFinder);
	}

	public SavedStudentProblemGradingHistory getSavedStudentProblemGradingHistory() {
		return savedStudentProblemGradingHistory;
	}

	public void setSavedStudentProblemGradingHistory(
			SavedStudentProblemGradingHistory savedStudentProblemGradingHistory) {
		this.savedStudentProblemGradingHistory = savedStudentProblemGradingHistory;
	}

	
	
	

}
