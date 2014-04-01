package grader.trace.feature_score;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureScoreLoaded extends StepperInfo {
	String featureAutoScoreFileName;



public FeatureScoreLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureAutoScoreFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoScoreFileName() {
	return featureAutoScoreFileName;
}



public void setFeatureAutoScoreFileName(String featureAutoScoreFileName) {
	this.featureAutoScoreFileName = featureAutoScoreFileName;
}

	
	public static FeatureScoreLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anFeatureAutoFileName,
			double aScore,
			Object aFinder) {
		String aMessage = "Feature Auto Score Loaded from File:" + anFeatureAutoFileName + ". Score:" + aScore;
		FeatureScoreLoaded retVal = new FeatureScoreLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anFeatureAutoFileName, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
