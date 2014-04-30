package grader.trace.feedback;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeedbackSaved extends FeedbackInfo {
	String feedbackFileName;



public FeedbackSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		feedbackFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeedbackFileName() {
	return feedbackFileName;
}



public void setFeedbackFileName(String feedbackFileName) {
	this.feedbackFileName = feedbackFileName;
}

	
	public static FeedbackSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feedback Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeedbackSaved retVal = new FeedbackSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
