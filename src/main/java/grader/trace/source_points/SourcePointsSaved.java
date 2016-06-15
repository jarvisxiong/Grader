package grader.trace.source_points;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class SourcePointsSaved extends SourcePointsInfo {
	String featureAutoScoreFileName;



public SourcePointsSaved(String aMessage,
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

	
	public static SourcePointsSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			double aScore,
			Object aFinder) {
		String aMessage = "Source Points Saved to File:" + anOverviewFileName + ". Score:" + aScore;
		SourcePointsSaved retVal = new SourcePointsSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
