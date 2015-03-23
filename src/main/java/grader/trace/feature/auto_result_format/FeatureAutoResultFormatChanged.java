package grader.trace.feature.auto_result_format;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoResultFormatChanged extends FeatureAutoResultFomatInfo {
//String overallResults;



public FeatureAutoResultFormatChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aResults, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static FeatureAutoResultFormatChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String aResults,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Results Changed to:" + aResults;
		FeatureAutoResultFormatChanged retVal = new FeatureAutoResultFormatChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aResults, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
