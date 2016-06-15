package grader.trace.overall_score;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class OverallScoreSaved extends OverallScoreInfo {
	String featureAutoScoreFileName;



public OverallScoreSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		featureAutoScoreFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoScoreFileName() {
	return featureAutoScoreFileName;
}



public void setFeatureAutoScoreFileName(String featureAutoScoreFileName) {
	this.featureAutoScoreFileName = featureAutoScoreFileName;
}

	
	public static OverallScoreSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			double aScore,
			Object aFinder) {
		String aMessage = "Overall Score Saved to File:" + anOverviewFileName + ". Score:" + aScore;
		OverallScoreSaved retVal = new OverallScoreSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
