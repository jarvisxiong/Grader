package grader.trace.feature_auto_result_format;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.feature.SerializableFeatureInfo;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableFeatureAutoResultFormatInfo extends SerializableFeatureInfo {
String featureAutoResults;



public SerializableFeatureAutoResultFormatInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureAutoResults = aResults;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoResults() {
	return featureAutoResults;
}



public void setFeatureAutoResults(String featureAutoResults) {
	this.featureAutoResults = featureAutoResults;
}

@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + featureAutoResults;
}

	
//	public static SerializableFeatureAutoResultsInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject, GradingFeature aFeature,
//			String aResults,
//			Object aFinder) {
//		String aMessage = "Overview Results Autoly Changed to:" + aResults;
//		SerializableFeatureAutoResultsInfo retVal = new SerializableFeatureAutoResultsInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aResults, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
