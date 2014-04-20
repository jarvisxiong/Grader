package grader.interaction_logger.manual_grading_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AStudentProblemHistory  implements StudentProblemGradingHistory{
	List<String> graderNames = new ArrayList();	
	String moduleName;
	String problemName;
	String onyen;
	String name;
	int numVisits = 1;
	Double manualOverallScore;
	String manualOverallNotes = "";
	Map<String, Double> featureToManualScore = new HashMap();
	Map<String, String> featureToManualNotes = new HashMap();
//	long visitPeriod;
	double totalScore;
	
	long visitEndTime;
	long autoVisitTime;
	
	long manualVisitTime;
	String sourceComments;
	double multiplier;
	double sourcePoints;
	double featuresScore;
	Set<String> tabsVisited = new HashSet();
	
	

	

	

	public  AStudentProblemHistory(String aGraderName, String aModuleName, String aProblemName, String anOnyen) {
		graderNames.add(aGraderName);
		moduleName = aModuleName;
		problemName = aProblemName;
		onyen = anOnyen;
//		name = aName;
	}
	
	public String getOnyen() {
		return onyen;
	}

	public void setOnyen(String onyen) {
		this.onyen = onyen;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
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
//	public long getVisitPeriod() {
//		return visitPeriod;
//	}
//	public void setVisitPeriod(long visitPeriod) {
//		this.visitPeriod = visitPeriod;
//	}
//	@Override
//	public void addVisitPeriod(long aNewPeriod) {
//		visitPeriod += aNewPeriod;
//	}
	@Override
	public void incNumVisits() {
		numVisits++;
	}
	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	@Override
	public long getVisitEndTime() {
		return visitEndTime;
	}
	@Override
	public void setVisitEndTime(long visitEndTime) {
		this.visitEndTime = visitEndTime;
	}
	@Override
	public void merge(StudentProblemGradingHistory other) {
		incNumVisits();
		if (other.getVisitEndTime() > getVisitEndTime()) {
//		if (!other.getManualOverallNotes().isEmpty())
			manualOverallNotes = other.getManualOverallNotes();
//		if (other.getManualOverallScore() != null)
			manualOverallScore = other.getManualOverallScore();
		totalScore = other.getTotalScore();
		featuresScore = other.getFeaturesScore();
		multiplier = other.getMultiplier();
		sourcePoints = other.getSourcePoints();
		sourceComments = other.getSourceComments();
		
		Map<String, Double> otherFeatureToManualScore = other.getFeatureToManualScore();
		Set<String> otherFeatures = otherFeatureToManualScore.keySet();
		for (String onyen:otherFeatures) {
			featureToManualScore.put(onyen, otherFeatureToManualScore.get(onyen));			
		}
		Map<String, String> otherFeatureToManualNotes = other.getFeatureToManualNotes();
		otherFeatures = otherFeatureToManualNotes.keySet();
		for (String onyen:otherFeatures) {
			featureToManualNotes.put(onyen, otherFeatureToManualNotes.get(onyen));			
		}
		}
		tabsVisited.addAll(other.getTabsVisited());
		for (String grader:other.getGraderNames()) {
			if (graderNames.contains(grader)) continue;
			graderNames.add(grader);
		}
//		visitPeriod += other.getVisitPeriod();	
		manualVisitTime += other.getManualVisitTime();
		autoVisitTime += other.getAutoVisitTime();
		
	}
	@Override
	public void addGraderName(String newName) {
		if (graderNames.contains(newName))
			return;
		graderNames.add(newName);
	}
	public List<String> getGraderNames() {
		return graderNames;
	}

	public void setGraderNames(List<String> newValue) {
		this.graderNames = newValue;
	}
	
	public long getAutoVisitTime() {
		return autoVisitTime;
	}

	public void addAutoVisitTime(long autoVisitTime) {
		this.autoVisitTime += autoVisitTime;
	}

	public long getManualVisitTime() {
		return manualVisitTime;
	}

	public void addManualVisitTime(long manualVisitTime) {
		this.manualVisitTime += manualVisitTime;
	}

	public String getSourceComments() {
		return sourceComments;
	}

	public void setSourceComments(String sourceComments) {
		this.sourceComments = sourceComments;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public double getSourcePoints() {
		return sourcePoints;
	}

	public void setSourcePoints(double sourcePoints) {
		this.sourcePoints = sourcePoints;
	}
	@Override
	public double getFeaturesScore() {
		return featuresScore;
	}
	@Override
	public void setFeaturesScore(double featuresScore) {
		this.featuresScore = featuresScore;
	}
	
	public Set<String> getTabsVisited() {
		return tabsVisited;
	}

	public void setTabsVisited(Set<String> tabsVisited) {
		this.tabsVisited = tabsVisited;
	}
	
	public void isSourceVisited() {
		tabsVisited.contains(SOURCE_TAB);
	}
	public void setSourceVisited() {
		tabsVisited.add(SOURCE_TAB);
	}
	public void isFeedbackVisited() {
		tabsVisited.contains(FEEDBACK_TAB);
	}
	public void setFeedbackVisited() {
		tabsVisited.add(FEEDBACK_TAB);
	}

	public void isSourceOpened() {
		tabsVisited.contains(SOURCE_OPEN);
	}
	public void setSourceOpened() {
		tabsVisited.add(SOURCE_OPEN);
	}

}
