package grader.interaction_logger.manual_grading_stats;

import grader.trace.interaction_logger.SavedAllStudentsProblemGradingHistoryMerged;
import grader.trace.interaction_logger.SavedStudentProblemGradingHistoryEntered;
import grader.trace.interaction_logger.SavedStudentProblemGradingHistoryMerged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnAllStudentsProblemHistory implements AllStudentsProblemHistory{
	long elapsedAutoTime;
	long elapsedManualTime;
	long averageAutoTime;
	long averageManualTime;
	int totalManualFeatureScoreOverrides;
	int totalOverallScoreOverrides;
	int totalManualFeatureNotes;
	int totalManualOverallNotes;
	double averagelManualFeatureScoreOverrides;
	double averageOverallScoreOverrides;
	double averageManualFeatureNotes;
	double averageManualOverallNotes;
	
	int totalSourceNotes;
	double averageSourceNotes;
	List<String> visitedStudents = new ArrayList();
	String graderName, moduleName, problemName;
	

	
	
	Map<String, StudentProblemGradingHistory> onyenToStudentHistory = new HashMap();
//	List<SavedStudentProblemGradingHistory> studentsHistory = new ArrayList();
	
	public AnAllStudentsProblemHistory(String aGraderName, String aModuleName, String aProblemName) {
		graderName = aGraderName;
		moduleName = aModuleName;
		problemName = aProblemName;
		
	}
	@Override
	public void computeAggregateStats() {
		elapsedAutoTime = 0;
		elapsedManualTime = 0;
		averageAutoTime = 0;
		averageManualTime = 0;
		totalManualFeatureScoreOverrides = 0;
		totalOverallScoreOverrides = 0;
		totalManualFeatureNotes = 0;
		 totalManualOverallNotes = 0;
		 averagelManualFeatureScoreOverrides = 0;
		 averageOverallScoreOverrides = 0;
		 averageManualFeatureNotes = 0;
		 averageManualOverallNotes = 0;
		 totalSourceNotes = 0;
		 for (String onyen:visitedStudents) {
			 StudentProblemGradingHistory history = onyenToStudentHistory.get(onyen);
			 elapsedManualTime += history.getManualVisitTime();
			 elapsedAutoTime += history.getAutoVisitTime();
			 String sourceComments = history.getSourceComments();
			 if (sourceComments != null)
			 totalSourceNotes += history.getSourceComments().split("\n").length;
			 if (!history.getManualOverallNotes().isEmpty()) {
				 totalManualOverallNotes++;
			 }
			 totalManualFeatureNotes += history.getFeatureToManualNotes().size();
		 }
		 int numStudents = visitedStudents.size();
		 averageManualTime = elapsedManualTime/numStudents;
		 averageAutoTime = elapsedAutoTime/numStudents;
		 averageManualOverallNotes = ((double) totalManualOverallNotes)/numStudents;
		 averageManualFeatureNotes = ((double) totalManualFeatureNotes)/numStudents;
		 averageSourceNotes = ((double) totalSourceNotes)/numStudents;
		 
		 
		
	}
	
	
	public long getElapsedAutoTime() {
		
		return elapsedAutoTime;
	}
	public void setElapsedAutoTime(long elapsedAutoTime) {
		this.elapsedAutoTime = elapsedAutoTime;
	}
	public long getElapsedManualTime() {
		return elapsedManualTime;
	}
	public void setElapsedManualTime(long elapsedManualTime) {
		this.elapsedManualTime = elapsedManualTime;
	}
	public long getAverageAutoTime() {
		return averageAutoTime;
	}
	public void setAverageAutoTime(long averageAutoTime) {
		this.averageAutoTime = averageAutoTime;
	}
	public long getAverageManualTime() {
		return averageManualTime;
	}
	public void setAverageManualTime(long averageManualTime) {
		this.averageManualTime = averageManualTime;
	}
	public int getTotalManualFeatureScoreOverrides() {
		return totalManualFeatureScoreOverrides;
	}
	public void setTotalManualFeatureScoreOverrides(
			int totalManualFeatureScoreOverrides) {
		this.totalManualFeatureScoreOverrides = totalManualFeatureScoreOverrides;
	}
	public int getTotalOverallScoreOverrides() {
		return totalOverallScoreOverrides;
	}
	public void setTotalOverallScoreOverrides(int totalOverallScoreOverrides) {
		this.totalOverallScoreOverrides = totalOverallScoreOverrides;
	}
	public int getTotalManualFeatureNotes() {
		return totalManualFeatureNotes;
	}
	public void setTotalManualFeatureNotes(int totalManualFeatureNotes) {
		this.totalManualFeatureNotes = totalManualFeatureNotes;
	}
	public int getTotalManualOverallNotes() {
		return totalManualOverallNotes;
	}
	public void setTotaleManualOverallNotes(int totaleManualOverallNotes) {
		this.totalManualOverallNotes = totaleManualOverallNotes;
	}
	public double getAveragelManualFeatureScoreOverrides() {
		return averagelManualFeatureScoreOverrides;
	}
	public void setAveragelManualFeatureScoreOverrides(
			double averagelManualFeatureScoreOverrides) {
		this.averagelManualFeatureScoreOverrides = averagelManualFeatureScoreOverrides;
	}
	public double getAverageOverallScoreOverrides() {
		return averageOverallScoreOverrides;
	}
	public void setAverageOverallScoreOverrides(double averageOverallScoreOverrides) {
		this.averageOverallScoreOverrides = averageOverallScoreOverrides;
	}
	public double getAverageManualFeatureNotes() {
		return averageManualFeatureNotes;
	}
	public void setAverageManualFeatureNotes(double averageManualFeatureNotes) {
		this.averageManualFeatureNotes = averageManualFeatureNotes;
	}
	public double getAverageManualOverallNotes() {
		return averageManualOverallNotes;
	}
	public void setAverageManualOverallNotes(double averageManualOverallNotes) {
		this.averageManualOverallNotes = averageManualOverallNotes;
	}
//	public List<SavedStudentProblemGradingHistory> getStudentsHistory() {
//		return studentsHistory;
//	}
//	public void setStudentsHistory(List<SavedStudentProblemGradingHistory> studentsHistory) {
//		this.studentsHistory = studentsHistory;
//	}
	public void newStudentHistory(String anOnyen, StudentProblemGradingHistory aHistory) {
		StudentProblemGradingHistory existingHistory = onyenToStudentHistory.get(anOnyen);
		if (existingHistory != null) {
			existingHistory.merge(aHistory);
			SavedStudentProblemGradingHistoryMerged.newCase(aHistory, this);

		} else {
			onyenToStudentHistory.put(anOnyen, aHistory);	
			visitedStudents.add(anOnyen);
			SavedStudentProblemGradingHistoryEntered.newCase(aHistory, this);
		}
	}
	public List<String> getVisitedStudents() {
		return visitedStudents;
	}
	public void setVisitedStudents(List<String> visitedStudents) {
		this.visitedStudents = visitedStudents;
	}
	public Map<String, StudentProblemGradingHistory> getOnyenToStudentHistory() {
		return onyenToStudentHistory;
	}
	public void setOnyenToStudentHistory(
			Map<String, StudentProblemGradingHistory> onyenToStudentHistory) {
		this.onyenToStudentHistory = onyenToStudentHistory;
	}
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public String getGraderName() {
		return graderName;
	}

	public void setGraderName(String graderName) {
		this.graderName = graderName;
	}
	
	
	public int getTotalSourceNotes() {
		return totalSourceNotes;
	}
	public void setTotalSourceNotes(int totalSourceNotes) {
		this.totalSourceNotes = totalSourceNotes;
	}
	public double getAverageSourceNotes() {
		return averageSourceNotes;
	}
	public void setAverageSourceNotes(double averageSourceNotes) {
		this.averageSourceNotes = averageSourceNotes;
	}
	@Override
	public void merge(AllStudentsProblemHistory other) {
		// TO DO the numerical statuses
		List<String> otherOnyens = other.getVisitedStudents();
		Map<String, StudentProblemGradingHistory> otherMap = other.getOnyenToStudentHistory();
		for (String onyen:otherOnyens) {
			newStudentHistory(onyen, otherMap.get(onyen));
		}
		SavedAllStudentsProblemGradingHistoryMerged.newCase(other, this);
	}
	
	

}
