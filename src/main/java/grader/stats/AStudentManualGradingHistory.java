package grader.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AStudentManualGradingHistory  implements StudentManualGradingHistory{
	String moduleName;
	String problemName;
	int numVisits;
	Double manualOverallScore;
	String manualOverallNotes = "";
	Map<String, Double> featureToManualScore = new HashMap();
	Map<String, String> featureToManualNotes = new HashMap();
	long visitPeriod;
	
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
	public int getNumVisits() {
		return numVisits;
	}
	public void setNumVisits(int numVisits) {
		this.numVisits = numVisits;
	}
	public Double getManualOverallScore() {
		return manualOverallScore;
	}
	public void setManualOverallScore(Double manualOverallScore) {
		this.manualOverallScore = manualOverallScore;
	}
	public String getManualOverallNotes() {
		return manualOverallNotes;
	}
	public void setManualOverallNotes(String manualOverallNotes) {
		this.manualOverallNotes = manualOverallNotes;
	}
	public Map<String, Double> getFeatureToManualScore() {
		return featureToManualScore;
	}
	public void setFeatureToManualScore(Map<String, Double> featureToManualScore) {
		this.featureToManualScore = featureToManualScore;
	}
	public Map<String, String> getFeatureToManualNotes() {
		return featureToManualNotes;
	}
	public void setFeatureToManualNotes(Map<String, String> featureToManualNotes) {
		this.featureToManualNotes = featureToManualNotes;
	}
	public long getVisitPeriod() {
		return visitPeriod;
	}
	public void setVisitPeriod(long visitPeriod) {
		this.visitPeriod = visitPeriod;
	}
	@Override
	public void addVisitPeriod(long aNewPeriod) {
		visitPeriod += aNewPeriod;
	}
	@Override
	public void incNumVisits() {
		numVisits++;
	}
	@Override
	public void merge(StudentManualGradingHistory other) {
		incNumVisits();
		if (!other.getManualOverallNotes().isEmpty())
			manualOverallNotes = other.getManualOverallNotes();
		if (other.getManualOverallScore() != null)
			manualOverallScore = other.getManualOverallScore();
		Map<String, Double> otherFeatureToManualScore = other.getFeatureToManualScore();
		Set<String> otherOnyens = otherFeatureToManualScore.keySet();
		for (String onyen:otherOnyens) {
			featureToManualScore.put(onyen, otherFeatureToManualScore.get(onyen));			
		}
		Map<String, String> otherFeatureToManualNotes = other.getFeatureToManualNotes();
		otherOnyens = otherFeatureToManualNotes.keySet();
		for (String onyen:otherOnyens) {
			featureToManualNotes.put(onyen, otherFeatureToManualNotes.get(onyen));			
		}
		visitPeriod += other.getVisitPeriod();		
	}

}
