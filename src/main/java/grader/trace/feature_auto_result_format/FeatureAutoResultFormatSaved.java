package grader.trace.feature_auto_result_format;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoResultFormatSaved extends FeatureAutoResultFomatInfo {
	String featureAutoResultsFileName;



public FeatureAutoResultFormatSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aResults, aFinder);
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
			SakaiProject aProject,
			String anOverviewFileName,
			String aResults,
			Object aFinder) {
		String aMessage = "Feature Auto Results Saved to File:" + anOverviewFileName + ". Results:" + aResults;
		FeatureAutoResultFormatSaved retVal = new FeatureAutoResultFormatSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aResults, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
