package grader.interaction_logger.manual_grading_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AllStudentsProblemHistory {
	public double getElapsedAutoTime() ;
	public void setElapsedAutoTime(double elapsedAutoTime);
	public double getElapsedManualTime() ;
	public void setElapsedManualTime(double elapsedManualTime);
	public double getAverageAutoTime() ;
	public void setAverageAutoTime(double averageAutoTime) ;
	public double getAverageManualTime() ;
	public void setAverageManualTime(double averageManualTime) ;
	public double getTotalManualFeatureScoreOverrides() ;
	public void setTotalManualFeatureScoreOverrides(
			double totalManualFeatureScoreOverrides) ;
	public double getTotalOverallScoreOverrides() ;
	public void setTotalOverallScoreOverrides(double totalOverallScoreOverrides) ;
	public double getTotalManualFeatureNotes() ;
	public void setTotalManualFeatureNotes(double totalManualFeatureNotes) ;
	public double getTotaleManualOverallNotes() ;
	public void setTotaleManualOverallNotes(double totaleManualOverallNotes) ;
	public double getAveragelManualFeatureScoreOverrides() ;
	public void setAveragelManualFeatureScoreOverrides(
			double averagelManualFeatureScoreOverrides) ;
	public double getAverageOverallScoreOverrides() ;
	public void setAverageOverallScoreOverrides(double averageOverallScoreOverrides) ;
	public double getAverageManualFeatureNotes() ;
	public void setAverageManualFeatureNotes(double averageManualFeatureNotes) ;
	public double getAverageManualOverallNotes() ;
	public void setAverageManualOverallNotes(double averageManualOverallNotes) ;
//	public List<SavedStudentProblemGradingHistory> getStudentsHistory() ;
//	public void setStudentsHistory(List<SavedStudentProblemGradingHistory> studentsHistory) ;
	public void newStudentHistory(String anOnyen, StudentProblemGradingHistory aHistory) ;
	public List<String> getVisitedStudents() ;
	public void setVisitedStudents(List<String> visitedStudents) ;
	public Map<String, StudentProblemGradingHistory> getOnyenToStudentHistory() ;
	public void setOnyenToStudentHistory(
			Map<String, StudentProblemGradingHistory> onyenToStudentHistory) ;
	void merge(AllStudentsProblemHistory other);
	public String getModuleName() ;

	public void setModuleName(String moduleName) ;

	public String getProblemName() ;

	public void setProblemName(String problemName) ;
	public String getGraderName();
	public void setGraderName(String graderName) ;
	
	

}
