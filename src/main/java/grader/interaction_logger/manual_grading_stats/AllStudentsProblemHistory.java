package grader.interaction_logger.manual_grading_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AllStudentsProblemHistory {
	public long getElapsedAutoTime() ;
	public void setElapsedAutoTime(long elapsedAutoTime);
	public long getElapsedManualTime() ;
	public void setElapsedManualTime(long elapsedManualTime);
	public long getAverageAutoTime() ;
	public void setAverageAutoTime(long averageAutoTime) ;
	public long getAverageManualTime() ;
	public void setAverageManualTime(long averageManualTime) ;
	public int getTotalManualFeatureScoreOverrides() ;
	public void setTotalManualFeatureScoreOverrides(
			int totalManualFeatureScoreOverrides) ;
	public int getTotalOverallScoreOverrides() ;
	public void setTotalOverallScoreOverrides(int totalOverallScoreOverrides) ;
	public int getTotalManualFeatureNotes() ;
	public void setTotalManualFeatureNotes(int totalManualFeatureNotes) ;
	public int getTotalManualOverallNotes() ;
	public void setTotaleManualOverallNotes(int totaleManualOverallNotes) ;
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
	public void newStudentHistory(String anOnyen, StudentProblemHistory aHistory) ;
	public List<String> getVisitedStudents() ;
	public void setVisitedStudents(List<String> visitedStudents) ;
	public Map<String, StudentProblemHistory> getOnyenToStudentHistory() ;
	public void setOnyenToStudentHistory(
			Map<String, StudentProblemHistory> onyenToStudentHistory) ;
	void merge(AllStudentsProblemHistory other);
	public String getModuleName() ;

	public void setModuleName(String moduleName) ;

	public String getProblemName() ;

	public void setProblemName(String problemName) ;
	public String getGraderName();
	public void setGraderName(String graderName) ;
	void computeAggregateStats();

	public int getTotalSourceNotes() ;
	public void setTotalSourceNotes(int totalSourceNotes) ;
	public double getAverageSourceNotes() ;
	public void setAverageSourceNotes(double averageSourceNotes) ;
	
	

}
