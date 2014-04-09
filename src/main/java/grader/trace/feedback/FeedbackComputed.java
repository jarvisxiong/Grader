package grader.trace.feedback;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeedbackComputed extends StepperInfo {
	String feedbackFileName;



public FeedbackComputed(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		feedbackFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeedbackFileName() {
	return feedbackFileName;
}



public void setFeedbackFileName(String feedbackFileName) {
	this.feedbackFileName = feedbackFileName;
}

	
	public static FeedbackComputed newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feedback Loaded from File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeedbackComputed retVal = new FeedbackComputed(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
