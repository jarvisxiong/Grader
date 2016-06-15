package grader.trace.feature.auto_result_format;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

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
