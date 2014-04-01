package grader.trace.feature_auto_result_format;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoResultFomatInfo extends StepperInfo {
String featureAutoResults;



public FeatureAutoResultFomatInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureAutoResults = aResults;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoResults() {
	return featureAutoResults;
}



public void setFeatureAutoResults(String featureAutoResults) {
	this.featureAutoResults = featureAutoResults;
}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + featureAutoResults;
//}

	
//	public static SerializableFeatureAutoResultsInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aResults,
//			Object aFinder) {
//		String aMessage = "Overview Results Autoly Changed to:" + aResults;
//		SerializableFeatureAutoResultsInfo retVal = new SerializableFeatureAutoResultsInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aResults, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
