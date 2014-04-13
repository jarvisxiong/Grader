package grader.stats;

import grader.trace.stats.SavedAllStudentsProblemGradingHistoryMerged;
import grader.trace.stats.SavedStudentProblemGradingHistoryEntered;
import grader.trace.stats.SavedStudentProblemGradingHistoryMerged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASavedAllProblemsGradingHistory implements SavedAllStudentsProblemGradingHistory{
	double elapsedAutoTime;
	double elapsedManualTime;
	double averageAutoTime;
	double averageManualTime;
	double totalManualFeatureScoreOverrides;
	double totalOverallScoreOverrides;
	double totalManualFeatureNotes;
	double totaleManualOverallNotes;
	double averagelManualFeatureScoreOverrides;
	double averageOverallScoreOverrides;
	double averageManualFeatureNotes;
	double averageManualOverallNotes;
	List<String> visitedStudents = new ArrayList();
	String graderName, moduleName, problemName;
	

	
	
	Map<String, SavedStudentProblemGradingHistory> onyenToStudentHistory = new HashMap();
//	List<SavedStudentProblemGradingHistory> studentsHistory = new ArrayList();
	
	public ASavedAllProblemsGradingHistory(String aGraderName, String aModuleName, String aProblemName) {
		graderName = aGraderName;
		moduleName = aModuleName;
		problemName = aProblemName;
		
	}
	
	public double getElapsedAutoTime() {
		return elapsedAutoTime;
	}
	public void setElapsedAutoTime(double elapsedAutoTime) {
		this.elapsedAutoTime = elapsedAutoTime;
	}
	public double getElapsedManualTime() {
		return elapsedManualTime;
	}
	public void setElapsedManualTime(double elapsedManualTime) {
		this.elapsedManualTime = elapsedManualTime;
	}
	public double getAverageAutoTime() {
		return averageAutoTime;
	}
	public void setAverageAutoTime(double averageAutoTime) {
		this.averageAutoTime = averageAutoTime;
	}
	public double getAverageManualTime() {
		return averageManualTime;
	}
	public void setAverageManualTime(double averageManualTime) {
		this.averageManualTime = averageManualTime;
	}
	public double getTotalManualFeatureScoreOverrides() {
		return totalManualFeatureScoreOverrides;
	}
	public void setTotalManualFeatureScoreOverrides(
			double totalManualFeatureScoreOverrides) {
		this.totalManualFeatureScoreOverrides = totalManualFeatureScoreOverrides;
	}
	public double getTotalOverallScoreOverrides() {
		return totalOverallScoreOverrides;
	}
	public void setTotalOverallScoreOverrides(double totalOverallScoreOverrides) {
		this.totalOverallScoreOverrides = totalOverallScoreOverrides;
	}
	public double getTotalManualFeatureNotes() {
		return totalManualFeatureNotes;
	}
	public void setTotalManualFeatureNotes(double totalManualFeatureNotes) {
		this.totalManualFeatureNotes = totalManualFeatureNotes;
	}
	public double getTotaleManualOverallNotes() {
		return totaleManualOverallNotes;
	}
	public void setTotaleManualOverallNotes(double totaleManualOverallNotes) {
		this.totaleManualOverallNotes = totaleManualOverallNotes;
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
	public void newStudentHistory(String anOnyen, SavedStudentProblemGradingHistory aHistory) {
		SavedStudentProblemGradingHistory existingHistory = onyenToStudentHistory.get(anOnyen);
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
	public Map<String, SavedStudentProblemGradingHistory> getOnyenToStudentHistory() {
		return onyenToStudentHistory;
	}
	public void setOnyenToStudentHistory(
			Map<String, SavedStudentProblemGradingHistory> onyenToStudentHistory) {
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
	@Override
	public void merge(SavedAllStudentsProblemGradingHistory other) {
		// TO DO the numerical statuses
		List<String> otherOnyens = other.getVisitedStudents();
		Map<String, SavedStudentProblemGradingHistory> otherMap = other.getOnyenToStudentHistory();
		for (String onyen:otherOnyens) {
			newStudentHistory(onyen, otherMap.get(onyen));
		}
		SavedAllStudentsProblemGradingHistoryMerged.newCase(other, this);
	}
	
	

}
