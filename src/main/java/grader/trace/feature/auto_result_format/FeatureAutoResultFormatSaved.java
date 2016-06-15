package grader.trace.feature.auto_result_format;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class FeatureAutoResultFormatSaved extends FeatureAutoResultFomatInfo {
	String featureAutoResultsFileName;



public FeatureAutoResultFormatSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aResults, aFinder);
		featureAutoResultsFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoResultsFileName() {
	return featureAutoResultsFileName;
}



public void setFeatureAutoResultsFileName(String featureAutoResultsFileName) {
	this.featureAutoResultsFileName = featureAutoResultsFileName;
}

	
	public static FeatureAutoResultFormatSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anOverviewFileName,
			String aResults,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Results Saved to File:" + anOverviewFileName + ". Results:" + aResults;
		FeatureAutoResultFormatSaved retVal = new FeatureAutoResultFormatSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anOverviewFileName, aResults, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
