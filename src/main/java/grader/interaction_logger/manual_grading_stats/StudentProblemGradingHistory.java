package grader.interaction_logger.manual_grading_stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentProblemGradingHistory {
	public static final String SOURCE_TAB = "source";
	public static final String FEEDBACK_TAB = "feedback";
	public static final String PROBLEM_HISTORY_TAB = "problem_history";

	public static final String NAVIGATION_TAB = "navigation";
	public static final String SOURCE_OPEN = "source_open";

	
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
//	public long getVisitPeriod() ;
//	public void setVisitPeriod(long visitPeriod) ;
//	void addVisitPeriod(long aNewPeriod);
	void incNumVisits();
	void merge(StudentProblemGradingHistory other);
	public double getTotalScore() ;

	public void setTotalScore(double totalScore) ;
	public String getOnyen() ;

	public void setOnyen(String onyen) ;
	
	public List<String> getGraderNames() ;
	public void setGraderNames(List<String> newValue) ;
	public long getVisitEndTime() ;
	public void setVisitEndTime(long visitEndTime);
	void addGraderName(String newName);
	String getName();
	void setName(String name);
	public long getAutoVisitTime() ;

	public void addAutoVisitTime(long autoVisitTime) ;
	public long getManualVisitTime() ;

	public void addManualVisitTime(long manualVisitTime) ;
	public String getSourceComments() ;

	public void setSourceComments(String sourceComments);

	public double getMultiplier() ;

	public void setMultiplier(double multiplier) ;

	public double getSourcePoints() ;

	public void setSourcePoints(double sourcePoints) ;
	double getFeaturesScore();
	void setFeaturesScore(double featuresScore);
	
	public Set<String> getTabsVisited() ;

	public void setTabsVisited(Set<String> tabsVisited);
	public void isSourceVisited() ;
	public void setSourceVisited() ;
	public void isFeedbackVisited() ;
	public void setFeedbackVisited() ;
	
	public void isProblemHistoryVisited() ;
	public void setProblemHistoryVisited() ;

	public void isSourceOpened() ;
	public void setSourceOpened() ;
	
	
	

}
