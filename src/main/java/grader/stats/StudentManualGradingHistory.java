package grader.stats;

import java.util.HashMap;
import java.util.Map;

public interface StudentManualGradingHistory {
	
	
	public String getModuleName() ;
	public void setModuleName(String moduleName) ;
	public String getProblemName();
	public void setProblemName(String problemName);
	public int getNumVisits() ;
	public void setNumVisits(int numVisits) ;
	public Double getManualOverallScore() ;
	public void setManualOverallScore(Double manualOverallScore) ;
	public String getManualOverallNotes() ;
	public void setManualOverallNotes(String manualOverallNotes) ;
	public Map<String, Double> getFeatureToManualScore() ;
	public void setFeatureToManualScore(Map<String, Double> featureToManualScore) ;
	public Map<String, String> getFeatureToManualNotes() ;
	public void setFeatureToManualNotes(Map<String, String> featureToManualNotes);
	public long getVisitPeriod() ;
	public void setVisitPeriod(long visitPeriod) ;
	void addVisitPeriod(long aNewPeriod);
	void incNumVisits();
	void merge(StudentManualGradingHistory other);
	
	

}
