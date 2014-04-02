package grader.trace.feature_auto_result_format;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.feature.FeatureInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoResultFormatLoaded extends FeatureInfo {
	String featureAutoResultsFileName;



public FeatureAutoResultFormatLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureAutoResultsFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoResultsFileName() {
	return featureAutoResultsFileName;
}



public void setFeatureAutoResultsFileName(String featureAutoResultsFileName) {
	this.featureAutoResultsFileName = featureAutoResultsFileName;
}

	
	public static FeatureAutoResultFormatLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anFeatureAutoFileName,
			String aResults,
			Object aFinder) {
		String aMessage = "Feature Auto Results Loaded from File:" + anFeatureAutoFileName + ". Results:" + aResults;
		FeatureAutoResultFormatLoaded retVal = new FeatureAutoResultFormatLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  aFeature, anFeatureAutoFileName, aResults, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
